/**
 *
 */
package com.amway.apac.core.stock.services;

import de.hybris.platform.basecommerce.enums.StockLevelStatus;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import com.amway.core.model.AmwayKitProductModel;


/**
 * Interface to define methods for stock at APAC level
 *
 * @author Ashish Sabal
 *
 */
public interface AmwayApacStockService
{

	/**
	 * @param stockStatus
	 * @return
	 */
	boolean isStockAvailable(StockLevelStatus stockStatus);

	/**
	 * @param parentBundle
	 * @param baseStore
	 * @param pointOfService
	 * @param warehouse
	 * @return
	 */
	StockLevelStatus updateParentBundleStockStatus(AmwayKitProductModel parentBundle, BaseStoreModel baseStore,
			PointOfServiceModel pointOfService, WarehouseModel warehouse);
}
