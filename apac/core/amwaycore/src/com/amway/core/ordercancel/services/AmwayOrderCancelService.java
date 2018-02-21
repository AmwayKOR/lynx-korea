package com.amway.core.ordercancel.services;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordercancel.OrderCancelService;


/**
 * Interface for order cancel
 */
public interface AmwayOrderCancelService extends OrderCancelService
{
	/**
	 * To retrieve the snapshot of cancelled order.
	 *
	 * @param orderModel
	 * @return OrderModel
	 */
	OrderModel getOriginalSnapshopt(final OrderModel orderModel);
}
