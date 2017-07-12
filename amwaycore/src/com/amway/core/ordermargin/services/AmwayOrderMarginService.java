package com.amway.core.ordermargin.services;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;

import com.amway.core.model.AmwayOrderMarginModel;


/**
 * Interface dedicated for assigning of {@link AmwayOrderMarginModel} to {@link AbstractOrderModel} and
 * {@link AbstractOrderEntryModel}
 *
 * @param <o>
 */
public interface AmwayOrderMarginService<o extends AbstractOrderModel>
{
	/**
	 * Calculates the order margins for the given order at entry level and order level and assigns those values.
	 *
	 * @param order order for which the order margins have to be calculated
	 */
	void calculateAndAssignOrderMargin(o order);
}
