package com.amway.amwayfinance.services;

import de.hybris.platform.core.model.order.OrderModel;


public interface AmwayOrderPaymentConfirmationValidationStrategy
{

	boolean validate(OrderModel orderModel);

}
