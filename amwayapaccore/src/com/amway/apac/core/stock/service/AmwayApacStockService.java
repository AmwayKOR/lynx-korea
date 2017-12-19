/**
 *
 */
package com.amway.apac.core.stock.service;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import com.amway.core.stock.service.AmwayStockService;


/**
 * Interface to define methods for stock at APAC level
 */
public interface AmwayApacStockService extends AmwayStockService
{
	/**
	 * Method to update stock level with a specified amount.This update will only change the AVAILABLE amount in the
	 * stock level
	 *
	 * @param product
	 * @param warehouse
	 * @param amount
	 */
	void updateAvailableAmount(final ProductModel product, final WarehouseModel warehouse, final int amount);
}
