package com.amway.apac.core.backorder.service;

import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

import com.amway.apac.core.model.AmwayBackOrderModel;


public interface AmwayApacBackOrderService
{

	/**
	 * This method is used to release AmwayBackOrders on the bases of list of stockLevels.
	 *
	 * @param stockLevels
	 *           the Stock Levels for which all the AmwayBackOrder will be fetched and released.
	 * @return boolean - true if any AmwayBackOrder is released else false.
	 */
	boolean releaseBackOrdersForStockLevels(List<StockLevelModel> stockLevels);

	/**
	 * This method is used to release all available AmwayBackOrders for the baseStore.
	 *
	 * @param baseStore
	 *           the baseStore for which all the AmwayBackOrder will be fetched and released.
	 * @return boolean - true if any AmwayBackOrder is released else false.
	 */
	boolean releaseBackOrdersForBaseStore(BaseStoreModel baseStore);

	/**
	 * This method is used to Expire AmwayBackOrders on the basis of Date(current). It looks for all AmwayBackOrders
	 * which needs to mark as expired.
	 *
	 * @param backOrders
	 *           list of AmwayBackOrders for expiring.
	 * @return boolean - true if expired any else false.
	 */
	boolean expireBackOrders(List<AmwayBackOrderModel> backOrders);

	/**
	 * This method is used to fetch BackOrder on the basis of Consignment.
	 *
	 * @param consignmentModel
	 * @return AmwayBackOrder that refers to the given Consignment.
	 */
	AmwayBackOrderModel getBackOrderByConsignment(ConsignmentModel consignmentModel);

}
