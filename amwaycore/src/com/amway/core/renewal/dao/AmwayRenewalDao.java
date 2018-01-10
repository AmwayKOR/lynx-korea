package com.amway.core.renewal.dao;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Optional;


public interface AmwayRenewalDao
{
	Optional<AbstractOrderModel> getLastRenewalOrder(UserModel user);
}
