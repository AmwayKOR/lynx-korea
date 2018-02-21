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

import org.apache.commons.lang.BooleanUtils;
import org.springframework.validation.Errors;

import com.amway.core.facades.order.validators.AmwayPlaceOrderPaymentsValidator;
import com.amway.core.util.AmwayCartHelper;


/**
 * Implementation of {@link AmwayPlaceOrderPaymentsValidator}. Checks if the payments done using non overpay methods are
 * valid or not.
 *
 * @author arjunduggal
 *
 */
public class AmwayOrderNonOverpayMethodsPaymentValidator implements AmwayPlaceOrderPaymentsValidator
{

	/**
	 * {@inheritDoc}
	 *
	 * This implementation validates the non overpay payments.
	 */
	@Override
	public boolean validate(final Map<PaymentModeModel, List<PaymentTransactionModel>> paymentTransactionsMap,
			final CartModel cartModel, final Errors errors)
	{
		boolean isValid = true;
		// Validating if the sum total of payments with payment modes not allowing overpayments is less than order totals
		BigDecimal totalPaymentByNonOverPayMethods = new BigDecimal(0);
		for (final PaymentModeModel paymentModeModel : paymentTransactionsMap.keySet())
		{
			if (!BooleanUtils.toBoolean(paymentModeModel.getAllowOverpay()))
			{
				for (final PaymentTransactionModel paymentTransactionModel : paymentTransactionsMap.get(paymentModeModel))
				{
					totalPaymentByNonOverPayMethods = totalPaymentByNonOverPayMethods
							.add(paymentTransactionModel.getEntries().get(0).getAmount());
				}
			}
		}

		if (BigDecimal.valueOf(totalPaymentByNonOverPayMethods.doubleValue())
				.compareTo(AmwayCartHelper.getCartTotalPayablePrice(cartModel)) == 1)
		{
			errors.reject("Incorrect payment amount by payment methods that do not allow overpayment");
			isValid = false;
		}


		return isValid;
	}

}
