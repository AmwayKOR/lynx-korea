package com.amway.core.order.services;


import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.OrderService;


/**
 * Interface for order service.
 */
public interface AmwayOrderService extends OrderService
{

	OrderModel getOrderDetailsForCode(final String code);

}
