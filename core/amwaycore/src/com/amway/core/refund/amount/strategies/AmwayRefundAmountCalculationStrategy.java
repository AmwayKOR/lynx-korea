/**
 *
 */
package com.amway.core.refund.amount.strategies;

import de.hybris.platform.returns.model.ReturnRequestModel;


/**
 * Interface for Amway refund amount calculation.
 */
public interface AmwayRefundAmountCalculationStrategy
{
	/**
	 * Calculation of refund amount.
	 *
	 * @param returnRequestModel
	 * @return
	 */
	public Double calculateRefundAmount(final ReturnRequestModel returnRequestModel);
}
