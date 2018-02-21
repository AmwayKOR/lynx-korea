package com.amway.core.returns.services;

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.returns.ReturnService;
import de.hybris.platform.returns.model.ReplacementOrderModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import java.util.Date;
import java.util.List;


/**
 * Interface for Amway return service.
 */
public interface AmwayReturnService extends ReturnService
{
	/**
	 * Method to update return entry status from warehouse cockpit perform business actions
	 *
	 * @param returnEntryModel
	 * @param returnStatus
	 * @return boolean
	 */
	boolean updateReturnEntryStatus(ReturnEntryModel returnEntryModel, ReturnStatus returnStatus);

	/**
	 * Method to identify if Returns are possible on order
	 *
	 * @param orderModel
	 * @return
	 */
	boolean isReturnPossible(OrderModel orderModel);

	/**
	 * Retrieve latest shipdate from the consignments
	 *
	 * @param orderModel
	 * @return latest shipdate
	 */
	Date getLatestShipDate(OrderModel orderModel);

	/**
	 * Method to check if the return period expired based on consignment information
	 *
	 * @param orderModel
	 * @return boolean
	 */
	boolean isReturnPeriodExpired(OrderModel orderModel);


	/**
	 * @param order
	 * @param replacements
	 */
	void addReplaceOrderEntries(final ReplacementOrderModel order, final List<ReturnEntryModel> replacements);

	/**
	 * Method to retrieve ReturnRequestModel of a replacement order
	 *
	 * @param replacementOrderModel
	 * @return ReturnRequestModel
	 */
	ReturnRequestModel getReturnRequest(ReplacementOrderModel replacementOrderModel);
}
