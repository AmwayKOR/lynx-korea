/**
 *
 */
package com.amway.apac.core.backorder.service;

import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.List;


/**
 * Strategy for releasing AmwayBackOrders
 *
 * @author ankushbhatia
 *
 */
public interface AmwayApacBackOrderService
{

	/**
	 * This method is used to release AmwayBackOrders on the bases of AmwayBackOrderReleaseSelectionStrategy or
	 * StockLevels passed
	 *
	 * @param stockLevels
	 */
	Boolean release(List<StockLevelModel> stockLevels);

	Boolean expireBackOrder();

}
