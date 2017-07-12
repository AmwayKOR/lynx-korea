/**
 *
 */
package com.amway.core.stock.service;

import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;



/**
 * Interface for Amway commerce stock service.
 */
public interface AmwayCommerceStockService extends CommerceStockService
{

	public void reserve(final AbstractOrderModel abstractOrderModel);

	/**
	 * This method is used to finalize stock on order
	 *
	 * @param orderModel
	 */
	void commit(final OrderModel orderModel);
}
