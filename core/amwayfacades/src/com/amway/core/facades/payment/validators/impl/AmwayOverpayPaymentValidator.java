/**
 *
 */
package com.amway.core.facades.payment.validators.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.facades.order.validators.AmwayPlaceOrderPaymentsValidator;
import com.amway.core.facades.payment.validators.AmwayPaymentCaptureValidator;
import com.amway.core.model.AmwayPaymentConfigModel;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.util.AmwayCartHelper;


/**
 * Implementation of {@link AmwayPlaceOrderPaymentsValidator}. Checks that an over payment is made only with a payment
 * mode that allows an over payment
 *
 * @author mohit2496
 *
 */
public class AmwayOverpayPaymentValidator implements AmwayPaymentCaptureValidator
{
	private AmwayPaymentModeService amwayPaymentModeService;

	/**
	 * {@inheritDoc}
	 *
	 * This implementation checks if the overpay payment is valid.
	 */
	@Override
	public void validate(final AbstractOrderModel orderModel, final AmwayPaymentInfoData posPaymentInfoData,
			final Map<String, List<AmwayPaymentModeData>> supportedPaymentModeCombinations, final Errors errors)
	{
		final String paymentModeCode = posPaymentInfoData.getPaymentMode().getCode();
		boolean isOverpayPaymentAllowed = true;
		boolean isOverpayPaymentAmountValid = true;
		final Double paymentAmount = posPaymentInfoData.getAmount();
		final BigDecimal balanceAmount = BigDecimal.valueOf(AmwayCartHelper.getBalanceAmount(orderModel).doubleValue());
		final boolean isOverPayment = BigDecimal.valueOf(paymentAmount.doubleValue()).compareTo(balanceAmount) == 1;

		if (isOverPayment)
		{
			for (final String config : supportedPaymentModeCombinations.keySet())
			{
				final List<AmwayPaymentModeData> paymentModeListValue = supportedPaymentModeCombinations.get(config);
				if (CollectionUtils.isNotEmpty(paymentModeListValue))
				{
					for (final AmwayPaymentModeData paymentMode : paymentModeListValue)
					{
						if (paymentMode.getCode().equals(paymentModeCode))
						{
							//check for if over payment is allowed for the current payment mode
							if (!paymentMode.isAllowOverpay())
							{
								isOverpayPaymentAllowed = false;
								break;
							}
							//check for if over payment threshold does not get exceeded for the current payment mode
							else if (paymentMode.isAllowOverpay() && null != paymentMode.getOverpaymentThreshold()
									&& BigDecimal.valueOf(paymentAmount.doubleValue()).subtract(balanceAmount)
											.compareTo(BigDecimal.valueOf(paymentMode.getOverpaymentThreshold().doubleValue())) == 1)
							{
								isOverpayPaymentAmountValid = false;
								break;
							}
						}
					}
				}
				if (!(isOverpayPaymentAllowed && isOverpayPaymentAmountValid))
				{
					break;
				}
			}
		}

		if (!isOverpayPaymentAllowed)
		{
			errors.reject("The method used for payment does not allow an overpayment");
		}
		else if (!isOverpayPaymentAmountValid)
		{
			errors.reject(
					"Overpayment threshold reached. The method used for payment does not allow an overpayment for this much amount.");
		}
	}

	public AmwayPaymentModeService getAmwayPaymentModeService()
	{
		return amwayPaymentModeService;
	}

	@Required
	public void setAmwayPaymentModeService(final AmwayPaymentModeService amwayPaymentModeService)
	{
		this.amwayPaymentModeService = amwayPaymentModeService;
	}

}

