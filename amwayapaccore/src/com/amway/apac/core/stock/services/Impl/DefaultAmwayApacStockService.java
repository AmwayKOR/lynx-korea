package com.amway.apac.core.stock.services.Impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.stock.services.AmwayApacStockService;
import com.amway.core.model.AmwayKitEntryProductModel;
import com.amway.core.model.AmwayKitProductModel;
import com.amway.core.stock.service.impl.DefaultAmwayStockService;


/**
 * Stock service implementation for APAC specific APIs
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacStockService extends DefaultAmwayStockService implements AmwayApacStockService
{
	private CommerceStockService commerceStockService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isStockAvailable(final StockLevelStatus parentBundleStockStatus)
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
	 * {@inheritDoc}
	 */
	@Override
	public StockLevelStatus getUpdateBundleProductStockStatus(final AmwayKitProductModel parentBundle,
			final BaseStoreModel baseStore, final PointOfServiceModel pointOfService, final WarehouseModel warehouse)
	{
		StockLevelStatus stockStatus = null;
		boolean isBackOrder = true;
		boolean isAllMinor = true;
		for (final AmwayKitEntryProductModel kitEntryProduct : parentBundle.getKitEntry())
		{
			final StockLevelStatus childStockStatus = getChildStockStatus(baseStore, pointOfService, warehouse, kitEntryProduct);
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
	 * Returns Child Stock Status based upon different parameter
	 *
	 * @param baseStore
	 * @param pointOfService
	 * @param warehouse
	 * @param kitEntryProduct
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getChildStockStatus(final BaseStoreModel baseStore, final PointOfServiceModel pointOfService,
			final WarehouseModel warehouse, final AmwayKitEntryProductModel kitEntryProduct)
	{
		StockLevelStatus childStockStatus = null;
		if (Objects.nonNull(baseStore))
		{
			if (CollectionUtils.isNotEmpty(kitEntryProduct.getEntry().getVariants()))
			{
				childStockStatus = getStockLevelStatusForVariantProductAndBaseStore(kitEntryProduct, baseStore);
			}
			else
			{
				childStockStatus = getCommerceStockService().getStockLevelStatusForProductAndBaseStore(kitEntryProduct.getEntry(),
						baseStore);
			}
		}
		else if (Objects.nonNull(pointOfService))
		{
			if (CollectionUtils.isNotEmpty(kitEntryProduct.getEntry().getVariants()))
			{
				childStockStatus = getStockLevelStatusForVariantProductAndPointOfService(kitEntryProduct, pointOfService);
			}
			else
			{
				childStockStatus = getCommerceStockService()
						.getStockLevelStatusForProductAndPointOfService(kitEntryProduct.getEntry(), pointOfService);
			}
		}
		else if (Objects.nonNull(warehouse))
		{
			if (CollectionUtils.isNotEmpty(kitEntryProduct.getEntry().getVariants()))
			{
				childStockStatus = getStockLevelStatusForVariantProductWarehouse(kitEntryProduct, warehouse);
			}
			else
			{
				childStockStatus = super.getProductStatus(kitEntryProduct.getEntry(), warehouse);
			}
		}
		return childStockStatus;
	}

	/**
	 * Gets stock level status for variant product and warehouse.
	 *
	 * @param kitEntryProduct
	 * @param warehouse
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getStockLevelStatusForVariantProductWarehouse(final AmwayKitEntryProductModel kitEntryProduct,
			final WarehouseModel warehouse)
	{
		final StockLevelStatus stockStatus;
		// if product is Base product, fetch from all variants
		final Optional<VariantProductModel> product = kitEntryProduct.getEntry().getVariants().stream()
				.filter(variant -> isStockAvailable(super.getProductStatus(variant, warehouse))).findAny();
		// if any product having stock status other than TNA/ NLA/ NYA is present, set childStockStatus as its status
		if (product.isPresent())
		{
			stockStatus = super.getProductStatus(product.get(), warehouse);
		}
		else
		{
			// if all variants having disposition status as TNA/ NLA/ NYA, set childStockStatus as first variant product status
			stockStatus = super.getProductStatus(kitEntryProduct.getEntry().getVariants().iterator().next(), warehouse);
		}
		return stockStatus;
	}

	/**
	 * Gets stock level status for variant product and point of service.
	 *
	 * @param kitEntryProduct
	 * @param pointOfService
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getStockLevelStatusForVariantProductAndPointOfService(
			final AmwayKitEntryProductModel kitEntryProduct, final PointOfServiceModel pointOfService)
	{
		final StockLevelStatus stockStatus;
		// if product is Base product, fetch from all variants
		final Optional<VariantProductModel> product = kitEntryProduct.getEntry().getVariants().stream()
				.filter(variant -> isStockAvailable(
						getCommerceStockService().getStockLevelStatusForProductAndPointOfService(variant, pointOfService)))
				.findAny();
		// if any product having stock status other than TNA/ NLA/ NYA is present, set childStockStatus as its status
		if (product.isPresent())
		{
			stockStatus = getCommerceStockService().getStockLevelStatusForProductAndPointOfService(product.get(), pointOfService);
		}
		else
		{
			// if all variants having disposition status as TNA/ NLA/ NYA, set childStockStatus as first variant product status
			stockStatus = getCommerceStockService().getStockLevelStatusForProductAndPointOfService(
					kitEntryProduct.getEntry().getVariants().iterator().next(), pointOfService);
		}
		return stockStatus;
	}

	/**
	 * Gets stock level status for Variant product and base store
	 *
	 * @param kitEntryProduct
	 * @param baseStore
	 * @return StockLevelStatus
	 */
	protected StockLevelStatus getStockLevelStatusForVariantProductAndBaseStore(final AmwayKitEntryProductModel kitEntryProduct,
			final BaseStoreModel baseStore)
	{
		final StockLevelStatus stockStatus;
		// if product is Base product, fetch from all variants
		final Optional<VariantProductModel> product = kitEntryProduct.getEntry().getVariants().stream().filter(
				variant -> isStockAvailable(getCommerceStockService().getStockLevelStatusForProductAndBaseStore(variant, baseStore)))
				.findAny();
		// if any product having stock status other than TNA/ NLA/ NYA is present, set childStockStatus as its status
		if (product.isPresent())
		{
			stockStatus = getCommerceStockService().getStockLevelStatusForProductAndBaseStore(product.get(), baseStore);
		}
		else
		{
			// if all variants having disposition status as TNA/ NLA/ NYA, set childStockStatus as first variant product status
			stockStatus = getCommerceStockService()
					.getStockLevelStatusForProductAndBaseStore(kitEntryProduct.getEntry().getVariants().iterator().next(), baseStore);
		}
		return stockStatus;
	}

	/**
	 * @return the commerceStockService
	 */
	public CommerceStockService getCommerceStockService()
	{
		return commerceStockService;
	}

	/**
	 * @param commerceStockService
	 *           the commerceStockService to set
	 */
	@Required
	public void setCommerceStockService(final CommerceStockService commerceStockService)
	{
		this.commerceStockService = commerceStockService;
	}
}
