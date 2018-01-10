package com.amway.core.validators;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.util.Config;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ValidationException;

import org.apache.commons.lang.StringUtils;

import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.util.AmwayCartHelper;


/**
 * Validator for Payment mode.
 */
public class AmwayPaymentModeValidator
{

	private AmwayPaymentModeService paymentModeService;


	/**
	 * Validation of payment methods.
	 *
	 * @param cart
	 * @param amwayProfileData
	 * @throws ValidationException
	 */
	public void validate(final CartModel cart, final AmwayProfileResponseData amwayProfileData) throws ValidationException
	{
		boolean isValidPaymentSelection = false;
		boolean isAmountAvailable = false;
		final Map<String, AmwayPaymentModeData> appliedPayments = getSelectedPaymentModesForCart(cart);

		final Map<String, List<AmwayPaymentModeData>> supportedPayment = getPaymentModeService()
				.getSupportedPaymentModesCombination(cart, amwayProfileData, true, StringUtils.EMPTY);
		for (final List<AmwayPaymentModeData> value : supportedPayment.values())
		{
			if (value.size() == appliedPayments.size())
			{
				final Map<String, AmwayPaymentModeData> validPayments = createPaymentsMap(value);
				for (final Entry<String, AmwayPaymentModeData> appliedPayment : appliedPayments.entrySet())
				{
					isValidPaymentSelection = false;
					if (validPayments.get(appliedPayment.getKey()) != null)
					{
						isValidPaymentSelection = true;
						if (appliedPayment.getValue().getRepeatableCount() > validPayments.get(appliedPayment.getKey())
								.getRepeatableCount())
						{
							isValidPaymentSelection = false;
							continue;
						}

						if (!compare(appliedPayment.getValue(), validPayments.get(appliedPayment.getKey())))
						{
							throw new ValidationException("inValidCreditAmount");
						}
						else
						{
							isAmountAvailable = true;
						}
					}
				}

			}
		}
		if (!isValidPaymentSelection && !isAmountAvailable)
		{
			throw new ValidationException("invalidPaymentmodeSelected");
		}
	}

	/**
	 * @param appliedValue
	 * @param validValue
	 * @return boolean
	 */
	private boolean compare(final AmwayPaymentModeData appliedValue, final AmwayPaymentModeData validValue)
	{
		final BigDecimal appliedVal = BigDecimal.valueOf(appliedValue.getAmount());
		final BigDecimal validVal = BigDecimal.valueOf(validValue.getAmount());

		if (appliedVal.compareTo(BigDecimal.ZERO) != 0)
		{
			//if applied value amount is <= valid value amount && applied value repeatable count is <= valid value repeatable count
			if (appliedVal.compareTo(validVal) <= 0 &&
					appliedValue.getRepeatableCount() <= validValue.getRepeatableCount())
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param value
	 * @return map
	 */
	private Map<String, AmwayPaymentModeData> createPaymentsMap(final List<AmwayPaymentModeData> value)
	{
		final Map<String, AmwayPaymentModeData> map = new HashMap<String, AmwayPaymentModeData>();
		for (final AmwayPaymentModeData val : value)
		{
			map.put(val.getCode(), val);
		}
		return map;
	}

	/**
	 * @param cart
	 * @return selectedPayments
	 */
	private Map<String, AmwayPaymentModeData> getSelectedPaymentModesForCart(final CartModel cart)
	{
		final Map<String, AmwayPaymentModeData> selectedPayments = new HashMap();
		final Map<String, Object> paymentDetails = AmwayCartHelper.getPaymentDetails(cart);
		for (final PaymentInfoModel paymentInfo : cart.getPaymentInfos())
		{
			final String key = Config.getString("paymode." + paymentInfo.getItemtype() + ".code", "");
			AmwayPaymentModeData modedata = selectedPayments.get(key);
			if (modedata == null)
			{
				modedata = new AmwayPaymentModeData();
				modedata.setCode(key);
				selectedPayments.put(key, modedata);
			}
			modedata.setRepeatableCount(modedata.getRepeatableCount() + 1);
			modedata.setAmount(modedata.getAmount() + Double
					.valueOf(((Map<String, String>) paymentDetails.get(paymentInfo.getPk().toString())).get("amount")).doubleValue());
		}
		return selectedPayments;
	}

	/**
	 * @return paymentModeService
	 */
	public AmwayPaymentModeService getPaymentModeService()
	{
		return paymentModeService;
	}

	/**
	 * @param paymentModeService the paymentModeService to set
	 */
	public void setPaymentModeService(final AmwayPaymentModeService paymentModeService)
	{
		this.paymentModeService = paymentModeService;
	}
}
