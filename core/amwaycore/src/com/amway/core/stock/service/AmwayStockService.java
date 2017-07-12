package com.amway.core.stock.service;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.stock.StockService;


/**
 * 
 * Interface for amway stock service.
 * 
 */
public interface AmwayStockService extends StockService
{
	/**
	 * Method to commit stock for an order entry
	 * 
	 * @param abstractOrderEntryModel
	 * @param warehouse
	 * @param amount
	 * @param skuId
	 */
	void commit(final AbstractOrderEntryModel abstractOrderEntryModel, final WarehouseModel warehouse, final int amount,
			final String skuId);

	/**
	 * Method to adjust/increase the stock level of returned item
	 * 
	 * @param returnEntryModel
	 */
	void adjust(final ReturnEntryModel returnEntryModel);
}
