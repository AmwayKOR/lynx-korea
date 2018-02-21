package com.amway.core.user.services;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.UserService;

public interface AmwayUserService extends UserService
{
	CustomerModel getCurrentCustomer();

	CustomerModel getCustomerByPartyId(String partyId);
}
