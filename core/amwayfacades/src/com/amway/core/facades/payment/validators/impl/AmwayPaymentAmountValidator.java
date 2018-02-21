/**
 *
 */
package com.amway.core.facades.payment.validators.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.facades.order.validators.AmwayPlaceOrderPaymentsValidator;
import com.amway.core.facades.payment.validators.AmwayPaymentCaptureValidator;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.util.AmwayCartHelper;


/**
 * Implementation of {@link AmwayPlaceOrderPaymentsValidator}. Checks for the validity of the payment amount.
 *
 * @author arjunduggal
 */
public class AmwayPaymentAmountValidator implements AmwayPaymentCaptureValidator
{

	/**
	 * {@inheritDoc}
	 *
	 * This implementation checks if the payment amount is valid.
	 */
	@Override
	public void validate(final AbstractOrderModel orderModel, final AmwayPaymentInfoData posPaymentInfoData,
			final Map<String, List<AmwayPaymentModeData>> supportedPaymentModeCombinations, final Errors errors)
	{
		final boolean isValid = (BigDecimal.valueOf(AmwayCartHelper.getBalanceAmount(orderModel).doubleValue())
				.compareTo(BigDecimal.ZERO) == 1);
		if (!isValid)
		{
			errors.reject("Payment denied. It has been done already.");
		}

	}

}
