package com.amway.core.ordermargin.services;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import com.amway.core.model.AmwayOrderMarginModel;


/**
 * Interface dedicated for assigning of {@link AmwayOrderMarginModel} to {@link AbstractOrderModel} and
 * {@link AbstractOrderEntryModel}
 *
 * @param <o>
 */
public interface AmwayReturnOrderMarginService<o extends ReturnRequestModel>
{
	/**
	 * Calculates the order margins for the given return order at entry level and order level and assigns those values.
	 *
	 * @param order return order for which the order margins have to be calculated
	 */
	void calculateAndAssignOrderMargin(o order);
}
