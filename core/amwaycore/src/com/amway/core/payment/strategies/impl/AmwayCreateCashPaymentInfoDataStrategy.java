/**
 *
 */
package com.amway.core.payment.strategies.impl;

import de.hybris.platform.core.model.order.payment.PaymentModeModel;

import com.amway.core.cart.data.AmwayCashPaymentInfoData;
import com.amway.core.cart.data.AmwayPaymentInfoData;

/**
 * Implementation of {@link AbstractAmwayCreatePaymentInfoDataStrategy}. Creates
 * the Cash payment info data object.
 *
 * @author arjunduggal
 */
public class AmwayCreateCashPaymentInfoDataStrategy extends AbstractAmwayCreatePaymentInfoDataStrategy {

	@Override
	public AmwayPaymentInfoData createPaymentInfoData(final PaymentModeModel paymentMode, final double amount) {
		final AmwayCashPaymentInfoData amwayCashPaymentInfoData = new AmwayCashPaymentInfoData();
		setPaymentModeAndAmount(amwayCashPaymentInfoData, paymentMode, amount);
		return amwayCashPaymentInfoData;
	}

}
