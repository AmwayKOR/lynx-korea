package com.amway.core.user.services.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.user.impl.DefaultUserService;

import com.amway.core.user.dao.AmwayUserDao;
import com.amway.core.user.services.AmwayUserService;


/**
 * Default Amway implementation of hybris User service
 * Extends the OOTB implementation to get customers by party ID
 * Initially used by AutoRenewal facade
 */
@AmwayBean(ext="amwaycore",docs={"https://jira.amway.com:8444/display/HC/Auto-Renewal+Order",
											"https://jira.amway.com:8444/display/HC/Auto-Renewal+OCC+Endpoint"})
public class DefaultAmwayUserService extends DefaultUserService implements AmwayUserService
{
	private AmwayUserDao amwayUserDao;

	@Override
	public CustomerModel getCurrentCustomer()
	{
		return (CustomerModel) getCurrentUser();
	}

	@Override
	public CustomerModel getCustomerByPartyId(String partyId)
	{
		return amwayUserDao.getCustomerByPartyId(partyId);
	}

	public AmwayUserDao getAmwayUserDao()
	{
		return amwayUserDao;
	}

	public void setAmwayUserDao(AmwayUserDao amwayUserDao)
	{
		this.amwayUserDao = amwayUserDao;
	}
}
