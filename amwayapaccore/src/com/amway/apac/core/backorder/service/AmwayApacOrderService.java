/**
 *
 */
package com.amway.apac.core.backorder.service;

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
	 * @return true if order payment is captured.
	 */
	boolean isOrderPaymentCaptured(final OrderModel order);
}
