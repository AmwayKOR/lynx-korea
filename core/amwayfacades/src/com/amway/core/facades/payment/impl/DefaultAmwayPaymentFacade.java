package com.amway.core.facades.payment.impl;

import de.hybris.platform.acceleratorfacades.payment.impl.DefaultPaymentFacade;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.util.Config;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;

import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.facades.cart.data.PaymentDetailsData;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.facades.payment.AmwayPaymentFacade;
import com.amway.core.facades.payment.validators.AmwayPaymentCaptureValidator;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.payment.service.AmwaySplitPaymentsService;
import com.amway.facades.data.CapturePaymentInfoData;


/**
 * Implementation of {@link AmwayPaymentFacade}.
 *
 * @author mohit2496
 *
 */
public class DefaultAmwayPaymentFacade extends DefaultPaymentFacade implements AmwayPaymentFacade
{
	private CartService cartService;

	private AmwaySplitPaymentsService amwaySplitPaymentsService;

	private Map<String, List<AmwayPaymentCaptureValidator>> paymentCaptureValidatorsMap;

	private AmwayPaymentModeService amwayPaymentModeService;

	private Converter<AbstractOrderModel, PaymentDetailsData> amwayPaymentDetailsConverter;

	private AmwayAccountBalanceService amwayAccountBalanceService;


	@Override
	public void capturePayment(final CapturePaymentInfoData capturePaymentInfoData, final Errors errors)
	{
		ServicesUtil.validateParameterNotNull(capturePaymentInfoData, "Capture payment info data cannot be null");
		final AbstractOrderModel orderModel = cartService.getSessionCart();

		final AmwayPaymentInfoData paymentInfo = getPaymentInfo(capturePaymentInfoData);

		validatePayment(orderModel, paymentInfo, errors);

		if (!errors.hasErrors())
		{
			//TODO: check to set customer model
			getAmwaySplitPaymentsService().savePaymentTransaction(orderModel.getUser(), orderModel, paymentInfo);
		}

	}

	@Override
	public void validatePayment(final AbstractOrderModel orderModel, final AmwayPaymentInfoData amwayPaymentInfoData,
			final Errors errors)
	{
		ServicesUtil.validateParameterNotNull(amwayPaymentInfoData, "POS payment info data cannot be null");

		// get the list of payment capture validators for current base site, fall back to default base site in case no
		//validators have been defined for current base site
		final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
		final List<AmwayPaymentCaptureValidator> amwayPaymentCaptureValidators = currentBaseSite == null
				|| paymentCaptureValidatorsMap.get(currentBaseSite.getUid()) == null
						? paymentCaptureValidatorsMap.get(Config.getParameter(AmwaycoreConstants.DEFAULT_BASE_SITE_ID))
						: paymentCaptureValidatorsMap.get(currentBaseSite.getUid());

		if (CollectionUtils.isNotEmpty(amwayPaymentCaptureValidators))
		{
			final AmwayProfileResponseData amwayProfileData = new AmwayProfileResponseData();
			final GetBalanceResponseData balanceResponseData = getAmwayAccountBalanceService().getAccountBalance(orderModel);
			amwayProfileData.setAccountBalance(balanceResponseData.getAccountBalance());

			final String paymentModeCode = amwayPaymentInfoData.getPaymentMode().getCode();

			final Map<String, List<AmwayPaymentModeData>> supportedPaymentModeCombinations = amwayPaymentModeService
					.getSupportedPaymentModesCombination(orderModel, amwayProfileData, false, paymentModeCode);

			for (final AmwayPaymentCaptureValidator posPaymentValidator : amwayPaymentCaptureValidators)
			{
				posPaymentValidator.validate(orderModel, amwayPaymentInfoData, supportedPaymentModeCombinations, errors);
			}
		}
	}

	@Override
	public PaymentDetailsData getCartPaymentInfoDetails(final AbstractOrderModel orderModel)
	{
		PaymentDetailsData cartPaymentInfoData = null;
		if (orderModel != null)
		{
			cartPaymentInfoData = amwayPaymentDetailsConverter.convert((CartModel) orderModel);
		}
		return cartPaymentInfoData;
	}

	/**
	 * method to get the specific current payment info details from the capture payment info data object
	 *
	 * @param capturePaymentInfoData
	 *           the capture payment info data
	 * @return the current payment info
	 */
	private AmwayPaymentInfoData getPaymentInfo(final CapturePaymentInfoData capturePaymentInfoData)
	{
		AmwayPaymentInfoData paymentInfo = null;
		if (null != capturePaymentInfoData.getCashPaymentInfo())
		{
			paymentInfo = capturePaymentInfoData.getCashPaymentInfo();
		}
		else if (null != capturePaymentInfoData.getCcPaymentInfo())
		{
			paymentInfo = capturePaymentInfoData.getCcPaymentInfo();
		}
		else if (null != capturePaymentInfoData.getDcPaymentInfo())
		{
			paymentInfo = capturePaymentInfoData.getDcPaymentInfo();
		}
		return paymentInfo;
	}


	public AmwaySplitPaymentsService getAmwaySplitPaymentsService()
	{
		return amwaySplitPaymentsService;
	}

	public void setAmwaySplitPaymentsService(final AmwaySplitPaymentsService amwaySplitPaymentsService)
	{
		this.amwaySplitPaymentsService = amwaySplitPaymentsService;
	}

	public CartService getCartService()
	{
		return cartService;
	}

	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}


	public Converter<AbstractOrderModel, PaymentDetailsData> getAmwayPaymentDetailsConverter()
	{
		return amwayPaymentDetailsConverter;
	}

	public void setAmwayPaymentDetailsConverter(
			final Converter<AbstractOrderModel, PaymentDetailsData> amwayPaymentDetailsConverter)
	{
		this.amwayPaymentDetailsConverter = amwayPaymentDetailsConverter;
	}


	public AmwayPaymentModeService getAmwayPaymentModeService()
	{
		return amwayPaymentModeService;
	}

	public void setAmwayPaymentModeService(final AmwayPaymentModeService amwayPaymentModeService)
	{
		this.amwayPaymentModeService = amwayPaymentModeService;
	}

	public void setPaymentCaptureValidatorsMap(final Map<String, List<AmwayPaymentCaptureValidator>> paymentCaptureValidatorsMap)
	{
		this.paymentCaptureValidatorsMap = paymentCaptureValidatorsMap;
	}

	public Map<String, List<AmwayPaymentCaptureValidator>> getPaymentCaptureValidatorsMap()
	{
		return paymentCaptureValidatorsMap;
	}

	/**
	 * @return the amwayAccountBalanceService
	 */
	public AmwayAccountBalanceService getAmwayAccountBalanceService()
	{
		return amwayAccountBalanceService;
	}

	/**
	 * @param amwayAccountBalanceService
	 *           the amwayAccountBalanceService to set
	 */
	@Required
	public void setAmwayAccountBalanceService(final AmwayAccountBalanceService amwayAccountBalanceService)
	{
		this.amwayAccountBalanceService = amwayAccountBalanceService;
	}
}
