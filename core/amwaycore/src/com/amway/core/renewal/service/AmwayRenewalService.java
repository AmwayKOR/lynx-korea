package com.amway.core.renewal.service;

import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.InvalidCartException;


public interface AmwayRenewalService
{
	String placeRenewal(CartModel cart) throws InvalidCartException;

	String placeRenewal(final CartModel cart, final SalesApplication amwayWeb) throws InvalidCartException;

	String placeAndCapture(CartModel cart, SalesApplication channel) throws InvalidCartException;

	AbstractOrderModel getLastRenewalOrder(UserModel user);
}
