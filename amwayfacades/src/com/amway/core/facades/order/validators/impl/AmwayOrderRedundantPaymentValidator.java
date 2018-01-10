/**
 *
 */
package com.amway.core.facades.order.validators.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.validation.Errors;

import com.amway.core.facades.order.validators.AmwayPlaceOrderPaymentsValidator;
import com.amway.core.util.AmwayCartHelper;


/**
 * Implementation of {@link AmwayPlaceOrderPaymentsValidator}. Checks if there is any redundant payment done for the
 * order to be placed.
 *
 * @author arjunduggal
 *
 */
public class AmwayOrderRedundantPaymentValidator implements AmwayPlaceOrderPaymentsValidator
{

	/**
	 * {@inheritDoc}
	 *
	 * This implementation checks for the redundant payments.
	 */
	@Override
	public boolean validate(final Map<PaymentModeModel, List<PaymentTransactionModel>> paymentTransactionsMap,
			final CartModel cartModel, final Errors errors)
	{
		boolean isValid = true;
		// Validating if any of the payments with payment modes allowing over payments is completely redundant
		for (final PaymentModeModel paymentModeModel : paymentTransactionsMap.keySet())
		{
			if (BooleanUtils.toBoolean(paymentModeModel.getAllowOverpay()))
			{
				for (final PaymentTransactionModel paymentTransactionModel : paymentTransactionsMap.get(paymentModeModel))
				{
					if (paymentTransactionModel.getPlannedAmount()
							.doubleValue() <= -AmwayCartHelper.getBalanceAmount(cartModel).doubleValue())
					{
						errors.reject("Redundant payment by" + paymentModeModel.getName());
						isValid = false;
						break;
					}
				}
			}
		}

		return isValid;
	}
}
