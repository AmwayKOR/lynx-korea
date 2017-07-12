package com.amway.amwayfulfillment.drop.events;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


/**
 * <p>Event represents drop confirmation.</p>
 * <p>Generated during after drop for particular order. Notifies the system that the
 * order information has been passed to warehouse.</p>
 */
public class AmwayDropConfirmationEvent extends AbstractEvent
{
	private static final long serialVersionUID = 1L;

	private final OrderModel order;

	/**
	 * Instantiates a new drop confirmation event.
	 *
	 * @param order
	 * 		the target order
	 */
	public AmwayDropConfirmationEvent(final OrderModel order)
	{
		this.order = order;
	}

	public OrderModel getOrder()
	{
		return order;
	}
}
