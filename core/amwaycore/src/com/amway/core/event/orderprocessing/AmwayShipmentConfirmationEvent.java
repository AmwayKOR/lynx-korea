package com.amway.core.event.orderprocessing;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


/**
 * Event representing shipment confirmation.
 */
public class AmwayShipmentConfirmationEvent extends AbstractEvent
{
	private final OrderModel order;

	/**
	 * Instantiates a new shipment confirmation event.
	 *
	 * @param order
	 *           the target order
	 */
	public AmwayShipmentConfirmationEvent(final OrderModel order)
	{
		this.order = order;
	}

	public OrderModel getOrder()
	{
		return order;
	}
}
