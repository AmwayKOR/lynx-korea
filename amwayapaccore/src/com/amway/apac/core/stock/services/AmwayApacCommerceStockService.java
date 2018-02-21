/**
 *
 */
package com.amway.apac.core.stock.services;

import de.hybris.platform.core.model.order.OrderEntryModel;

import com.amway.core.stock.service.AmwayCommerceStockService;


/**
 * Interface to define new APIs for apac commerce stock service
 */
public interface AmwayApacCommerceStockService extends AmwayCommerceStockService
{

	/**
	 * This API is used to release the inventory that has been allocated for an order entry
	 * 
	 * @param orderEntry
	 */
	void releaseAllocationEvents(OrderEntryModel orderEntry);
}
