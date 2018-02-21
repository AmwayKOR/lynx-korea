/**
 *
 */
package com.amway.core.refund.amount.strategies.impl;

import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import org.apache.commons.collections.CollectionUtils;

import com.amway.core.refund.amount.strategies.AmwayRefundAmountCalculationStrategy;


/**
 * Default implementation
 */
public class DefaultAmwayRefundAmountCalculationStrategy implements AmwayRefundAmountCalculationStrategy
{

	/**
	 * Calculation of refund amount
	 * <p/>
	 * {@link #calculateRefundAmount(de.hybris.platform.returns.model.ReturnRequestModel)}
	 */
	@Override
	public Double calculateRefundAmount(final ReturnRequestModel returnRequestModel)
	{
		double refundAmount = 0;
		if (CollectionUtils.isNotEmpty(returnRequestModel.getReturnEntries()))
		{
			for (final ReturnEntryModel returnEntryModel : returnRequestModel.getReturnEntries())
			{
				refundAmount +=
						getUnitReturnPrice(returnEntryModel.getOrderEntry()) * returnEntryModel.getReceivedQuantity().doubleValue();
			}
		}

		return Double.valueOf(CoreAlgorithms.round(refundAmount, 2));
	}

	private double getUnitReturnPrice(final AbstractOrderEntryModel entry)
	{
		return entry.getProRatedPrice().doubleValue() / entry.getQuantity().longValue();
	}
}
