package com.amway.core.event.orderprocessing;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


/**
 * Event representing payment confirmation.
 */
public class AmwayPaymentConfirmationEvent extends AbstractEvent
{
	private final OrderModel order;

	/**
	 * Instantiates a new payment confirmation event.
	 *
	 * @param order
	 *           the target order
	 */
	public AmwayPaymentConfirmationEvent(final OrderModel order)
	{
		this.order = order;
	}

	public OrderModel getOrder()
	{
		return order;
	}
}
