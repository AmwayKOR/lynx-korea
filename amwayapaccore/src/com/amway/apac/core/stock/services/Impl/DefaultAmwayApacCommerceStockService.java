package com.amway.apac.core.stock.services.Impl;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.product.services.AmwayApacProductService;
import com.amway.apac.core.stock.services.AmwayApacStockService;
import com.amway.core.enums.AmwayKitProductType;
import com.amway.core.model.AmwayKitProductModel;
import com.amway.core.stock.service.impl.DefaultAmwayCommerceStockService;


/**
 * Commerce stock service implementation for APAC specific APIs
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacCommerceStockService extends DefaultAmwayCommerceStockService
{
	private AmwayApacProductService amwayApacProductService;
	private AmwayApacStockService amwayApacStockService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StockLevelStatus getStockLevelStatusForProductAndBaseStore(final ProductModel product, final BaseStoreModel baseStore)
	{
		// set stock status with product stock
		StockLevelStatus stockStatus = super.getStockLevelStatusForProductAndBaseStore(product, baseStore);

		// Replace stock status with child stock in case of bundled product
		if (getAmwayApacProductService().checkKitProductByType(product, AmwayKitProductType.BUNDLED))
		{
			if (getAmwayApacStockService().isStockAvailable(stockStatus))
			{
				// iterate for child and fetch stock level status from all major child
				final StockLevelStatus childStatus = getAmwayApacStockService()
						.getUpdateBundleProductStockStatus((AmwayKitProductModel) product, baseStore, null, null);
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
	public StockLevelStatus getStockLevelStatusForProductAndPointOfService(final ProductModel product,
			final PointOfServiceModel pointOfService)
	{
		StockLevelStatus stockStatus = super.getStockLevelStatusForProductAndPointOfService(product, pointOfService);

		// iterate for child and fetch stock level status from all major child
		if (getAmwayApacProductService().checkKitProductByType(product, AmwayKitProductType.BUNDLED))
		{
			if (getAmwayApacStockService().isStockAvailable(stockStatus))
			{
				// iterate for child and fetch stock level status from all major child
				final StockLevelStatus childStatus = getAmwayApacStockService()
						.getUpdateBundleProductStockStatus((AmwayKitProductModel) product, null, pointOfService, null);
				if (Objects.nonNull(childStatus))
				{
					stockStatus = childStatus;
				}
			}
		}
		return stockStatus;
	}

	/**
	 * @return the amwayApacProductService
	 */
	public AmwayApacProductService getAmwayApacProductService()
	{
		return amwayApacProductService;
	}

	/**
	 * @param amwayApacProductService
	 *           the amwayApacProductService to set
	 */
	@Required
	public void setAmwayApacProductService(final AmwayApacProductService amwayApacProductService)
	{
		this.amwayApacProductService = amwayApacProductService;
	}

	/**
	 * @return the amwayApacStockService
	 */
	public AmwayApacStockService getAmwayApacStockService()
	{
		return amwayApacStockService;
	}

	/**
	 * @param amwayApacStockService
	 *           the amwayApacStockService to set
	 */
	@Required
	public void setAmwayApacStockService(final AmwayApacStockService amwayApacStockService)
	{
		this.amwayApacStockService = amwayApacStockService;
	}
}
