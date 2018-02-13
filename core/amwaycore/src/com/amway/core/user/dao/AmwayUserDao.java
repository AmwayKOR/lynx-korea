package com.amway.core.user.dao;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.servicelayer.user.daos.UserDao;


public interface AmwayUserDao extends UserDao
{
	CustomerModel getCustomerByPartyId(String partyId);

}
