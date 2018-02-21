/**
 *
 */
package com.amway.core.facades.payment.validators.impl;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.facades.order.validators.AmwayPlaceOrderPaymentsValidator;
import com.amway.core.facades.payment.validators.AmwayPaymentCaptureValidator;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.util.AmwayCartHelper;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Implementation of {@link AmwayPlaceOrderPaymentsValidator}. Checks if the split payment modes for the payment to be
 * made are valid or not.
 *
 * @author arjunduggal
 */
public class AmwaySplitPaymentModeValidator implements AmwayPaymentCaptureValidator
{

	/**
	 * {@inheritDoc}
	 *
	 * This implementation checks if the split payment method is valid.
	 */
	@Override
	public void validate(final AbstractOrderModel orderModel, final AmwayPaymentInfoData posPaymentInfoData,
			final Map<String, List<AmwayPaymentModeData>> supportedPaymentModeCombinations, final Errors errors)
	{
		boolean isValid = false;
		boolean isAmountValid = true;
		final boolean isPartialPayment = BigDecimal.valueOf(AmwayCartHelper.getBalanceAmount(orderModel))
				.compareTo(BigDecimal.valueOf(posPaymentInfoData.getAmount())) == 1;

		for (final List<AmwayPaymentModeData> paymentModeListValue : supportedPaymentModeCombinations.values())
		{
			isValid = false;
			if (CollectionUtils.isNotEmpty(paymentModeListValue))
			{
				for (final AmwayPaymentModeData paymentMode : paymentModeListValue)
				{
					if (isPartialPayment)
					{

						/*
						 * if it is partial payment, check that each payment mode of the payment config should not be used
						 * more that allowed repeatable count. In case it the the last allowed payment, it should be of full
						 * amount
						 */
						if (paymentMode.getRepeatableCount() > paymentMode.getUsedCount())
						{
							isValid = true;
							isAmountValid = true;
							break;
						}
						else if (paymentMode.getRepeatableCount() == paymentMode.getUsedCount())
						{
							isValid = true;
							isAmountValid = false;
						}
						else
						{
							isValid = false;
						}
					}
					else
					{
						/*
						 * if it is full payment, check every payment mode of the payment config has been used at least once
						 */
						if (paymentMode.getUsedCount() == 0)
						{
							isValid = false;
							break;
						}
						else
						{
							isValid = true;
						}

					}

				}
			}
			if (isValid && isAmountValid)
			{
				break;
			}
		}

		if (!isValid)
		{
			errors.reject("Invalid payment mode.");
		}
		else if (!isAmountValid)
		{
			errors.reject("Invalid payment amount. Last payment needs to be full.");
		}

	}

}
