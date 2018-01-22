package com.amway.apac.core.stock.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.stock.exception.StockLevelNotFoundException;
import de.hybris.platform.util.Utilities;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.Validate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.product.services.AmwayApacProductService;
import com.amway.apac.core.stock.dao.AmwayApacStockLevelDao;
import com.amway.apac.core.stock.service.AmwayApacStockService;
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
		if (getAmwayApacProductService().checkKitProductByType(product, AmwayKitProductType.BUNDLED)
				&& isStockAvailable(stockStatus))
		{
			final StockLevelStatus childStatus = getUpdatedBundleProductStockStatus((AmwayKitProductModel) product,
					new ArrayList<WarehouseModel>(Arrays.asList(warehouse)));
			// iterate for child and fetch stock level status from all major child
			if (Objects.nonNull(childStatus))
			{
				stockStatus = childStatus;
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
	 * @param code
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
		if (getAmwayApacProductService().checkKitProductByType(product, AmwayKitProductType.BUNDLED)
				&& isStockAvailable(stockStatus))
		{
			final StockLevelStatus childStatus = getUpdatedBundleProductStockStatus((AmwayKitProductModel) product, warehouses);
			// iterate for child and fetch stock level status from all major child
			if (Objects.nonNull(childStatus))
			{
				stockStatus = childStatus;
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
	 * Gets the updated bundle product stock status combined of all major minor child products.
	 *
	 * @param parentBundle
	 *           the parent bundle
	 * @param warehouses
	 *           the warehouses
	 * @return the updated bundle product stock status
	 */
	protected StockLevelStatus getUpdatedBundleProductStockStatus(final AmwayKitProductModel parentBundle,
			final Collection<WarehouseModel> warehouses)
	{
		StockLevelStatus stockStatus = null;
		boolean isBackOrder = true;
		boolean isAllMinor = true;
		for (final AmwayKitEntryProductModel kitEntryProduct : parentBundle.getKitEntry())
		{
			final StockLevelStatus childStockStatus = getChildStockStatus(warehouses, kitEntryProduct);
			if (kitEntryProduct.getIsMajor().booleanValue())
			{
				isAllMinor = false;
				if (isBackOrder && !StockLevelStatus.BACKORDER.equals(childStockStatus))
				{
					isBackOrder = false;
				}
				//update status in  order of TNA/NYA/NLA if child stock having any.
				if (StockLevelStatus.TEMPORARYNOTAVAILABLE.equals(childStockStatus))
				{
					stockStatus = childStockStatus;
					break;
				}
				else if (StockLevelStatus.NOTYETAVAILABLE.equals(childStockStatus))
				{
					stockStatus = childStockStatus;
				}
				else if (!StockLevelStatus.NOTYETAVAILABLE.equals(stockStatus)
						&& StockLevelStatus.NOLONGERAVAILABLE.equals(childStockStatus))
				{
					stockStatus = childStockStatus;
				}
			}
			else if (isAllMinor)
			{
				if (isBackOrder && !StockLevelStatus.BACKORDER.equals(childStockStatus))
				{
					isBackOrder = false;
				}
			}

		}
		//if all child are BackOrder than return BackOrder Status
		if (isBackOrder && CollectionUtils.isNotEmpty(parentBundle.getKitEntry()))
		{
			return StockLevelStatus.BACKORDER;
		}
		return stockStatus;
	}

	/**
	 * Returns Child Stock Status based upon different parameter.
	 *
	 * @param warehouses
	 *           the warehouses
	 * @param kitEntryProduct
	 *           the kit entry product
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getChildStockStatus(final Collection<WarehouseModel> warehouses,
			final AmwayKitEntryProductModel kitEntryProduct)
	{
		StockLevelStatus childStockStatus = null;

		if (Objects.nonNull(warehouses))
		{
			if (CollectionUtils.isNotEmpty(kitEntryProduct.getEntry().getVariants()))
			{
				childStockStatus = getStockLevelStatusForVariantProductWarehouse(kitEntryProduct, warehouses);
			}
			else
			{
				childStockStatus = super.getProductStatus(kitEntryProduct.getEntry(), warehouses);
			}
		}
		return childStockStatus;
	}

	/**
	 * Gets stock level status for variant product and warehouse.
	 *
	 * @param kitEntryProduct
	 *           the kit entry product
	 * @param warehouses
	 *           the warehouses
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getStockLevelStatusForVariantProductWarehouse(final AmwayKitEntryProductModel kitEntryProduct,
			final Collection<WarehouseModel> warehouses)
	{
		final StockLevelStatus stockStatus;
		// if product is Base product, fetch from all variants
		final Optional<VariantProductModel> product = kitEntryProduct.getEntry().getVariants().stream()
				.filter(variant -> isStockAvailable(super.getProductStatus(variant, warehouses))).findAny();
		// if any product having stock status other than TNA/ NLA/ NYA is present, set childStockStatus as its status
		if (product.isPresent())
		{
			stockStatus = super.getProductStatus(product.get(), warehouses);
		}
		else
		{
			// if all variants having disposition status as TNA/ NLA/ NYA, set childStockStatus as first variant product status
			stockStatus = super.getProductStatus(kitEntryProduct.getEntry().getVariants().iterator().next(), warehouses);
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

}
