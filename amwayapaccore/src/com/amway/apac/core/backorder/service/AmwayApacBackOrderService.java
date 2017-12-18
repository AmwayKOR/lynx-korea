/**
 *
 */
package com.amway.apac.core.backorder.service;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.List;

import com.amway.apac.core.model.AmwayBackOrderModel;



/**
 * Strategy for releasing AmwayBackOrders
 *
 * @author ankushbhatia
 *
 */
public interface AmwayApacBackOrderService
{

	/**
	 * This method is used to release AmwayBackOrders on the bases of stockLevel and backOrder Passed
	 *
	 * @param amwayBackOrders
	 * @param stockLevel
	 */
	void releaseBackOrders(List<AmwayBackOrderModel> amwayBackOrders, StockLevelModel stockLevel);

	/**
	 * This method is used to release AmwayBackOrders on the bases of list of stockLevels
	 *
	 * @param stockLevels
	 */
	void releaseBackOrdersForStocks(List<StockLevelModel> stockLevels);

}
