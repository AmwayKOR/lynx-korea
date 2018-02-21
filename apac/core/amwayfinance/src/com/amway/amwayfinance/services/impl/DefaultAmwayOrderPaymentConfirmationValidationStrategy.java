package com.amway.amwayfinance.services.impl;

import de.hybris.platform.core.model.order.OrderModel;

import com.amway.amwayfinance.services.AmwayOrderPaymentConfirmationValidationStrategy;


public class DefaultAmwayOrderPaymentConfirmationValidationStrategy
		implements AmwayOrderPaymentConfirmationValidationStrategy
{
	@Override
	public boolean validate(final OrderModel orderModel)
	{
		return true;
	}
}
