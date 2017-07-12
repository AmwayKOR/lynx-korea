package com.amway.core.validator;


import de.hybris.platform.commercefacades.order.data.CartData;

import de.hybris.platform.commerceservices.constants.GeneratedCommerceServicesConstants;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.servicelayer.session.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Default commerce web services cart validator. Checks if cart is calculated and if needed values are filled.
 */
public class AmwayPlaceOrderCartValidator extends PlaceOrderCartValidator
{
	@Autowired
	private SessionService sessionService;

	@Override
	public boolean supports(final Class<?> clazz)
	{
		return CartData.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors)
	{
		final CartData cart = (CartData) target;

		if (!cart.isCalculated())
		{
			errors.reject("cart.notCalculated");
		}

		if (cart.getDeliveryMode() == null)
		{
			errors.reject("cart.deliveryModeNotSet");
		}

		final SalesApplication currentChannel = this.sessionService.getCurrentSession().getAttribute("currentChannel");

		//skip payment validation for POS because payment method hasn't been set yet
		if (cart.getPaymentInfo() == null && currentChannel != SalesApplication.POS)
		{
			errors.reject("cart.paymentInfoNotSet");
		}
	}
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}
}

