/**
 *
 */
package com.amway.core.payment.strategies.impl;

import de.hybris.platform.core.model.order.payment.PaymentModeModel;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.payment.strategies.AmwayCreatePaymentInfoDataStrategy;
import com.amway.core.order.data.PaymentModeData;

/**
 * Abstract Implementation of {@link AmwayCreatePaymentInfoDataStrategy}
 *
 * @author arjunduggal
 *
 */
public abstract class AbstractAmwayCreatePaymentInfoDataStrategy implements AmwayCreatePaymentInfoDataStrategy {

	/**
	 * Method to set the payment mode and amount
	 *
	 * @param amwayPaymentInfoData
	 *            the payment info data
	 * @param paymentMode
	 *            the payment mode
	 * @param amount
	 *            the amount
	 */
	protected void setPaymentModeAndAmount(final AmwayPaymentInfoData amwayPaymentInfoData,
			final PaymentModeModel paymentMode, final double amount) {
		amwayPaymentInfoData.setAmount(Double.valueOf(amount));
		amwayPaymentInfoData.setPaymentMode(getPaymentModeData(paymentMode));
	}

	/**
	 * Method to get the payment mode data object from the model object
	 *
	 * @param paymentMode
	 *            the payment mode model
	 * @return the payment mode data
	 */
	protected PaymentModeData getPaymentModeData(final PaymentModeModel paymentMode) {
		final PaymentModeData paymentModeData = new PaymentModeData();
		paymentModeData.setCode(paymentMode.getCode());
		return paymentModeData;
	}

}
