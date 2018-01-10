/**
 *
 */
package com.amway.core.facades.order.validators.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.amway.core.facades.order.validators.AmwayPlaceOrderPaymentsValidator;
import com.amway.core.util.AmwayCartHelper;


/**
 * Implementation of {@link AmwayPlaceOrderPaymentsValidator}. Checks if the complete payment has been made for the
 * order to be placed.
 *
 * @author arjunduggal
 *
 */
public class AmwayOrderCompletePaymentValidator implements AmwayPlaceOrderPaymentsValidator
{

	/**
	 * {@inheritDoc}
	 *
	 * This implementation checks if complete payment has been done for the cart or not.
	 */
	@Override
	public boolean validate(final Map<PaymentModeModel, List<PaymentTransactionModel>> paymentTransactionsMap,
			final CartModel cartModel, final Errors errors)
	{
		boolean isValid = true;
		// Validating if the sum total of payments is not less than order totals
		BigDecimal totalPayment = new BigDecimal(0);
		for (final PaymentModeModel paymentModeModel : paymentTransactionsMap.keySet())
		{
			for (final PaymentTransactionModel paymentTransactionModel : paymentTransactionsMap.get(paymentModeModel))
			{
				totalPayment = totalPayment.add(paymentTransactionModel.getEntries().get(0).getAmount());
			}
		}

		if (BigDecimal.valueOf(totalPayment.doubleValue()).compareTo(AmwayCartHelper.getCartTotalPayablePrice(cartModel)) == -1)
		{
			errors.reject("Insufficent payment for the Order !!");
			isValid = false;
		}
		return isValid;
	}

}
