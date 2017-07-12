/**
 *
 */
package com.amway.core.order.services;

import de.hybris.platform.core.model.order.AbstractOrderModel;


/**
 * Interface for Order proration
 */
public interface AmwayOrderProrationService
{
	/**
	 * proration calcuation
	 *
	 * @param abstractOrderModel
	 */
	void prorate(final AbstractOrderModel abstractOrderModel);
}
