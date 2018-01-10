/**
 *
 */
package com.amway.core.facades.order.validators.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.amway.core.facades.order.validators.AmwayPlaceOrderPaymentsValidator;


/**
 * Implementation of {@link AmwayPlaceOrderPaymentsValidator}. Checks if the AR payment done for the order to be placed
 * is valid.
 *
 * @author arjunduggal
 *
 */
public class AmwayOrderArPaymentValidator implements AmwayPlaceOrderPaymentsValidator
{

	/**
	 * {@inheritDoc}
	 *
	 * This implementation checks if the AR payment done is valid or not.
	 */
	@Override
	public boolean validate(final Map<PaymentModeModel, List<PaymentTransactionModel>> paymentTransactionsMap,
			final CartModel cartModel, final Errors errors)
	{
		// YTODO Auto-generated method stub
		return true;
	}

}
