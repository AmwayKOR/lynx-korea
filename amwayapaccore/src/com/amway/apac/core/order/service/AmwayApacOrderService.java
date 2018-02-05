/**
 *
 */
package com.amway.apac.core.order.service;

import de.hybris.platform.core.model.order.OrderModel;

import com.amway.core.order.services.AmwayOrderService;


/**
 * Interface for Order Services extending @AmwayOrderService
 */
public interface AmwayApacOrderService extends AmwayOrderService
{
	/**
	 * Identifies whether payment for a particular order is captured or not.
	 *
	 * @param order
	 * @return boolean - true if order payment is captured, false otherwise.
	 */
	boolean isOrderPaymentCaptured(final OrderModel order);
}
