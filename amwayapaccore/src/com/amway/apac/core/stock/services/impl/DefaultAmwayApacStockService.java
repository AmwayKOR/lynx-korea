package com.amway.apac.core.stock.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.stock.exception.StockLevelNotFoundException;
import de.hybris.platform.util.Utilities;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.product.services.AmwayApacProductService;
import com.amway.apac.core.stock.dao.AmwayApacStockLevelDao;
import com.amway.apac.core.stock.services.AmwayApacStockService;
import com.amway.apac.core.stock.strategies.AmwayApacCommerceStockLevelStatusStrategy;
import com.amway.core.enums.AmwayKitProductType;
import com.amway.core.model.AmwayKitEntryProductModel;
import com.amway.core.model.AmwayKitProductModel;
import com.amway.core.stock.service.impl.DefaultAmwayStockService;


/**
 * Stock service implementation for APAC specific APIs.
 *
 * @author Ashish Sabal
 */
public class DefaultAmwayApacStockService extends DefaultAmwayStockService implements AmwayApacStockService
{

	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacStockService.class.getName());

	private AmwayApacStockLevelDao amwayApacStockLevelDao;

	/** The Constant String PRODUCT. */
	private static final String PRODUCT = "Product";

	/** The Constant String WAREHOUSE. */
	private static final String WAREHOUSE = "Warehouse";

	/** The Constant String WAREHOUSE_LIST. */
	private static final String WAREHOUSE_LIST = "Warehouse List";

	/** The amway apac product service. */
	private AmwayApacProductService amwayApacProductService;

	private AmwayApacCommerceStockLevelStatusStrategy amwayApacCommerceStockLevelStatusStrategy;

	/**
	 * Returns stock level status for bundle product by warehouse.
	 *
	 * @param product
	 *           the product
	 * @param warehouse
	 *           the warehouse
	 * @return Stock level status for product
	 * @throws IllegalArgumentException
	 *            if product or warehouse is null.
	 */
	@Override
	public StockLevelStatus getProductStatus(final ProductModel product, final WarehouseModel warehouse)
	{
		validateParameterNotNullStandardMessage(PRODUCT, product);
		validateParameterNotNullStandardMessage(WAREHOUSE, warehouse);

		StockLevelStatus stockStatus = super.getProductStatus(product, warehouse);
		if (product instanceof AmwayKitProductModel)
		{
			final AmwayKitProductModel kitProduct = (AmwayKitProductModel) product;
			if ((kitProduct.getType().equals(AmwayKitProductType.BUNDLED)) && (isStockAvailable(stockStatus))
					&& (CollectionUtils.isNotEmpty(kitProduct.getKitEntry())))
			{
				final StockLevelStatus childStatus = getBundledProductStockStatus(kitProduct, warehouse);
				// iterate for child and fetch stock level status from all major child
				if (Objects.nonNull(childStatus))
				{
					stockStatus = childStatus;
				}
			}
		}
		return stockStatus;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateAvailableAmount(final ProductModel product, final WarehouseModel warehouse, final int amount)
	{
		Validate.notNull(product, "Parameter productCode cannot be null!");
		Validate.notNull(warehouse, "Parameter warehouse cannot be null!");
		final StockLevelModel currentStockLevel = checkAndGetStockLevel(product, warehouse, product.getCode());

		LOG.info("Updating stock amount : " + amount + " for product " + product.getCode() + " and skuId " + product.getCode());
		getAmwayApacStockLevelDao().updateAvailableAmount(currentStockLevel, amount);
		clearCacheForItem(currentStockLevel);
	}

	/**
	 * @param product
	 * @param warehouse
	 * @param skuId
	 * @return
	 */

	protected StockLevelModel checkAndGetStockLevel(final ProductModel product, final WarehouseModel warehouse, final String skuId)
	{
		for (final StockLevelModel stockLevelModel : getStockLevels(product, Arrays.asList(warehouse)))
		{
			if (stockLevelModel.getSkuId() != null && stockLevelModel.getSkuId().equals(skuId))
			{
				return stockLevelModel;
			}
		}
		throw new StockLevelNotFoundException(
				"no stock level for product [" + product + "] in warehouse [" + warehouse.getName() + "] found.");
	}


	protected void clearCacheForItem(final StockLevelModel stockLevel)
	{
		Utilities.invalidateCache(stockLevel.getPk());
		getModelService().refresh(stockLevel);
	}

	/**
	 * Returns stock level status for bundle product by list of warehouses.
	 *
	 * @param product
	 *           the product
	 * @param warehouses
	 *           the warehouses
	 * @return Stock level status for product
	 * @throws IllegalArgumentException
	 *            if product or warehouses is null.
	 */
	@Override
	public StockLevelStatus getProductStatus(final ProductModel product, final Collection<WarehouseModel> warehouses)
	{
		validateParameterNotNullStandardMessage(PRODUCT, product);
		validateParameterNotNullStandardMessage(WAREHOUSE_LIST, warehouses);

		StockLevelStatus stockStatus = super.getProductStatus(product, warehouses);
		if (product instanceof AmwayKitProductModel)
		{
			final AmwayKitProductModel kitProduct = (AmwayKitProductModel) product;
			if ((kitProduct.getType().equals(AmwayKitProductType.BUNDLED)) && (isStockAvailable(stockStatus))
					&& (CollectionUtils.isNotEmpty(kitProduct.getKitEntry())))
			{
				final StockLevelStatus childStatus = getBundledProductStockStatus(kitProduct, warehouses);
				// iterate for child and fetch stock level status from all major child
				if (Objects.nonNull(childStatus))
				{
					stockStatus = childStatus;
				}
			}
		}
		return stockStatus;
	}

	/**
	 * Checks if stock is not any of TNA, NYA, NLA.
	 *
	 * @param parentBundleStockStatus
	 *           the parent bundle stock status
	 * @return true, if is stock available
	 */
	protected boolean isStockAvailable(final StockLevelStatus parentBundleStockStatus)
	{
		boolean isParentStockAvailable = true;
		if (StockLevelStatus.TEMPORARYNOTAVAILABLE.equals(parentBundleStockStatus)
				|| StockLevelStatus.NOTYETAVAILABLE.equals(parentBundleStockStatus)
				|| StockLevelStatus.NOLONGERAVAILABLE.equals(parentBundleStockStatus))
		{
			isParentStockAvailable = false;
		}
		return isParentStockAvailable;
	}


	/**
	 * Gets the bundle product stock status combined of all major minor child products for warehouses list.
	 *
	 * @param parentBundle
	 *           the parent bundle
	 * @param warehouses
	 *           the warehouses
	 * @return the updated bundle product stock status
	 */
	protected StockLevelStatus getBundledProductStockStatus(final AmwayKitProductModel parentBundle,
			final Collection<WarehouseModel> warehouses)
	{
		final Set<StockLevelStatus> majorItemsStatusSet = new HashSet<>();
		final Set<StockLevelStatus> minorItemsStatusSet = new HashSet<>();

		for (final AmwayKitEntryProductModel kitEntryProduct : parentBundle.getKitEntry())
		{
			final StockLevelStatus childStockStatus = getChildStockStatus(warehouses, kitEntryProduct.getEntry());
			if (kitEntryProduct.getIsMajor().booleanValue())
			{
				majorItemsStatusSet.add(childStockStatus);
			}
			else
			{
				minorItemsStatusSet.add(childStockStatus);
			}
		}
		return getAmwayApacCommerceStockLevelStatusStrategy().evaluateKitProductStockStatus(majorItemsStatusSet,
				minorItemsStatusSet);
	}


	/**
	 * Gets the bundle product stock status combined of all major minor child products for a warehouse.
	 *
	 * @param parentBundle
	 *           the parent bundle
	 * @param warehouses
	 *           the warehouses
	 * @return the updated bundle product stock status
	 */
	protected StockLevelStatus getBundledProductStockStatus(final AmwayKitProductModel parentBundle,
			final WarehouseModel warehouse)
	{
		final Set<StockLevelStatus> majorItemsStatusSet = new HashSet<>();
		final Set<StockLevelStatus> minorItemsStatusSet = new HashSet<>();

		for (final AmwayKitEntryProductModel kitEntryProduct : parentBundle.getKitEntry())
		{
			final StockLevelStatus childStockStatus = getChildStockStatus(warehouse, kitEntryProduct.getEntry());
			if (kitEntryProduct.getIsMajor().booleanValue())
			{
				majorItemsStatusSet.add(childStockStatus);
			}
			else
			{
				minorItemsStatusSet.add(childStockStatus);
			}
		}
		return getAmwayApacCommerceStockLevelStatusStrategy().evaluateKitProductStockStatus(majorItemsStatusSet,
				minorItemsStatusSet);
	}

	/**
	 * Returns Child Stock Status based upon different parameter and warehouses.
	 *
	 * @param warehouses
	 *           the warehouses
	 * @param kitEntryProduct
	 *           the kit entry product
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getChildStockStatus(final Collection<WarehouseModel> warehouses, final ProductModel kitEntryProduct)
	{
		StockLevelStatus childStockStatus = null;

		if (CollectionUtils.isNotEmpty(kitEntryProduct.getVariants()))
		{
			childStockStatus = getStockLevelStatusForVariantProductWarehouse(kitEntryProduct, warehouses);
		}
		else
		{
			childStockStatus = super.getProductStatus(kitEntryProduct, warehouses);
		}

		return childStockStatus;
	}

	/**
	 * Returns Child Stock Status based upon different parameter and a warehouse.
	 *
	 * @param warehouses
	 *           the warehouses
	 * @param kitEntryProduct
	 *           the kit entry product
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getChildStockStatus(final WarehouseModel warehouse, final ProductModel kitEntryProduct)
	{
		StockLevelStatus childStockStatus = null;

		if (CollectionUtils.isNotEmpty(kitEntryProduct.getVariants()))
		{
			childStockStatus = getStockLevelStatusForVariantProductWarehouse(kitEntryProduct, warehouse);
		}
		else
		{
			childStockStatus = super.getProductStatus(kitEntryProduct, warehouse);
		}

		return childStockStatus;
	}

	/**
	 * Gets stock level status for variant product and warehouses.
	 *
	 * @param baseProduct
	 *           the kit entry product
	 * @param warehouses
	 *           the warehouses
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getStockLevelStatusForVariantProductWarehouse(final ProductModel baseProduct,
			final Collection<WarehouseModel> warehouses)
	{
		final StockLevelStatus stockStatus;
		// if product is Base product, fetch from all variants
		final Optional<VariantProductModel> product = baseProduct.getVariants().stream()
				.filter(variant -> isStockAvailable(super.getProductStatus(variant, warehouses))).findAny();
		// if any product having stock status other than TNA/ NLA/ NYA is present, set childStockStatus as its status
		if (product.isPresent())
		{
			stockStatus = super.getProductStatus(product.get(), warehouses);
		}
		else
		{
			// if all variants having disposition status as TNA/ NLA/ NYA, set childStockStatus as first variant product status
			stockStatus = super.getProductStatus(baseProduct.getVariants().iterator().next(), warehouses);
		}
		return stockStatus;
	}

	/**
	 * Gets stock level status for variant product and warehouse.
	 *
	 * @param baseProduct
	 *           the kit entry product
	 * @param warehouses
	 *           the warehouses
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getStockLevelStatusForVariantProductWarehouse(final ProductModel baseProduct,
			final WarehouseModel warehouse)
	{
		final StockLevelStatus stockStatus;
		// if product is Base product, fetch from all variants
		final Optional<VariantProductModel> product = baseProduct.getVariants().stream()
				.filter(variant -> isStockAvailable(super.getProductStatus(variant, warehouse))).findAny();
		// if any product having stock status other than TNA/ NLA/ NYA is present, set childStockStatus as its status
		if (product.isPresent())
		{
			stockStatus = super.getProductStatus(product.get(), warehouse);
		}
		else
		{
			// if all variants having disposition status as TNA/ NLA/ NYA, set childStockStatus as first variant product status
			stockStatus = super.getProductStatus(baseProduct.getVariants().iterator().next(), warehouse);
		}
		return stockStatus;
	}

	/**
	 * Gets the amway apac product service.
	 *
	 * @return the amwayApacProductService
	 */
	public AmwayApacProductService getAmwayApacProductService()
	{
		return amwayApacProductService;
	}

	/**
	 * Sets the amway apac product service.
	 *
	 * @param amwayApacProductService
	 *           the amwayApacProductService to set
	 */
	@Required
	public void setAmwayApacProductService(final AmwayApacProductService amwayApacProductService)
	{
		this.amwayApacProductService = amwayApacProductService;
	}

	/**
	 * @return the amwayApacStockLevelDao
	 */
	public AmwayApacStockLevelDao getAmwayApacStockLevelDao()
	{
		return amwayApacStockLevelDao;
	}

	/**
	 * @param amwayApacStockLevelDao
	 *           the amwayApacStockLevelDao to set
	 */
	@Required
	public void setAmwayApacStockLevelDao(final AmwayApacStockLevelDao amwayApacStockLevelDao)
	{
		this.amwayApacStockLevelDao = amwayApacStockLevelDao;
	}

	/**
	 * @return the amwayApacCommerceStockLevelStatusStrategy
	 */
	public AmwayApacCommerceStockLevelStatusStrategy getAmwayApacCommerceStockLevelStatusStrategy()
	{
		return amwayApacCommerceStockLevelStatusStrategy;
	}

	/**
	 * @param amwayApacCommerceStockLevelStatusStrategy
	 *           the amwayApacCommerceStockLevelStatusStrategy to set
	 */
	@Required
	public void setAmwayApacCommerceStockLevelStatusStrategy(
			final AmwayApacCommerceStockLevelStatusStrategy amwayApacCommerceStockLevelStatusStrategy)
	{
		this.amwayApacCommerceStockLevelStatusStrategy = amwayApacCommerceStockLevelStatusStrategy;
	}

}
