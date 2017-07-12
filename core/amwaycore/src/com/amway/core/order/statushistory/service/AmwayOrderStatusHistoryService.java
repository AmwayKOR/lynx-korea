/**
 *
 */
package com.amway.core.order.statushistory.service;

import java.util.List;

import com.amway.core.model.AmwayOrderStatusHistoryEntryModel;


/**
 * Service to retrieve the details of AmwayOrderStatusHistoryEntryModel
 */
public interface AmwayOrderStatusHistoryService
{
	/**
	 * Method to retrieve the OrderStatus history of an order.
	 *
	 * @param orderCode
	 * @return List<AmwayOrderStatusHistoryEntryModel>
	 */
	List<AmwayOrderStatusHistoryEntryModel> getStatusHistory(String orderCode);
}
