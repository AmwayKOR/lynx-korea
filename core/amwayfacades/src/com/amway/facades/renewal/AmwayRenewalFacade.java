package com.amway.facades.renewal;


import com.amway.core.model.AmwayAccountModel;
import com.amway.facades.renewal.data.AmwayAutoRenewalRequestData;
import com.amway.facades.renewal.data.AmwayAutoRenewalResponseData;
import de.hybris.platform.core.model.order.CartModel;


public interface AmwayRenewalFacade
{
	AmwayAutoRenewalResponseData placeAutoRenewalOrder(String aboNum, String partyId,
			AmwayAutoRenewalRequestData autoRenewalRequest);

	void addServiceFeesToAutoRenewalCart(AmwayAccountModel account);

	void applyRenewalFeePriceAdjustments(CartModel cart, String registrationDate);
}
