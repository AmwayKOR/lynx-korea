package com.amway.apac.core.backorder.service;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;

import java.util.List;

import com.amway.apac.core.model.AmwayBackOrderModel;


public interface AmwayApacBackOrderService
{

	/**
	 * This method is used to release AmwayBackOrders for the stockLevels.
	 *
	 * @param amwayBackOrders
	 * @param stockLevel
	 */
	void releaseBackOrdersForStock(List<AmwayBackOrderModel> amwayBackOrders, StockLevelModel stockLevel);

	/**
	 * This method is used to release AmwayBackOrders on the bases of list of stockLevels.
	 *
	 * @param stockLevels
	 */
	void releaseBackOrders(List<StockLevelModel> stockLevels);

	/**
	 * This method is used to release all available AmwayBackOrders for the site.
	 *
	 * @param baseSite
	 * @return boolean
	 */
	boolean releaseBackOrders(BaseSiteModel baseSite);

	/**
	 * This method is used to Expire AmwayBackOrders on the bases of Date(current)
	 *
	 * @param backOrders
	 */
	Boolean expireBackOrder(List<AmwayBackOrderModel> backOrders);

	/**
	 * This method is used to fetch BackOrder on the basis of consignment
	 *
	 * @param consignmentModel
	 * @return AmwayBackOrder that refers to the given consignment
	 */
	AmwayBackOrderModel getBackOrderByConsignment(ConsignmentModel consignmentModel);



}
