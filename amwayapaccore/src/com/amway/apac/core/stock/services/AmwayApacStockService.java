package com.amway.apac.core.stock.services;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import com.amway.core.model.AmwayKitProductModel;


/**
 * Stock service interface to define methods for stock at APAC level
 *
 * @author Ashish Sabal
 *
 */
public interface AmwayApacStockService
{
	/**
	 * Checks for stock availability
	 *
	 * @param stockStatus
	 * @return Checks for stock availability
	 */
	boolean isStockAvailable(final StockLevelStatus stockStatus);

	/**
	 * Returns stock status of bundle product calculated on basis of all major child products status
	 *
	 * @param parentBundle
	 * @param baseStore
	 * @param pointOfService
	 * @param warehouse
	 * @return Stock status of bundle product calculated on basis of all major child products status
	 */
	StockLevelStatus getUpdateBundleProductStockStatus(final AmwayKitProductModel parentBundle, final BaseStoreModel baseStore,
			final PointOfServiceModel pointOfService, final WarehouseModel warehouse);
}
