/**
 *
 */
package com.amway.core.payment.strategies;

import de.hybris.platform.core.model.order.payment.PaymentModeModel;

import com.amway.core.cart.data.AmwayPaymentInfoData;

/**
 * The strategy to create payment info data.
 *
 * @author arjunduggal
 *
 */
public interface AmwayCreatePaymentInfoDataStrategy {

	/**
	 * Method to create payment info data for the payment mode and amount
	 * provided.
	 *
	 * @param paymentMode
	 *            the payment mode
	 * @param amount
	 *            the amount
	 * @return the payment info data
	 */
	AmwayPaymentInfoData createPaymentInfoData(final PaymentModeModel paymentMode, final double amount);
}
