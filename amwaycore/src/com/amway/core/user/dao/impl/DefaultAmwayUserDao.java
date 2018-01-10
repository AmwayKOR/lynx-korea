package com.amway.core.user.dao.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.commerceservices.search.flexiblesearch.PagedFlexibleSearchService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.user.daos.impl.DefaultUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import com.amway.core.user.dao.AmwayUserDao;


/**
 * Default Amway implementation of hybris User data access object
 * Supports the AmwayUserService, which is used initially in AutoRenewal Facade.
 */
@AmwayBean(ext="amwaycore",docs={"https://jira.amway.com:8444/display/HC/Auto-Renewal+Order",
											"https://jira.amway.com:8444/display/HC/Auto-Renewal+OCC+Endpoint"})
public class DefaultAmwayUserDao extends DefaultUserDao implements AmwayUserDao
{
	private static final String PARTY_ID = "partyId";
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayUserDao.class);

	// By default, Core stores the MAGIC DMS party ID in the out-of-the-box CustomerID attribute on the CustomerModel.
	private static final String FIND_CUSTOMER_BY_PARTY_ID = "SELECT {c:" + CustomerModel.PK + "} FROM {" + CustomerModel._TYPECODE
			+ " AS c} WHERE {c:" + CustomerModel.CUSTOMERID + "} = ?" + PARTY_ID;

	private static final String PARTYID_NOT_FOUND_ERROR_MSG = "Party ID %s was not found in the database.";
	private FlexibleSearchService flexibleSearchService;

	@Override
	public CustomerModel getCustomerByPartyId(String partyId)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put(PARTY_ID, partyId);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(FIND_CUSTOMER_BY_PARTY_ID, params);
		CustomerModel result = null;
		try
		{
			result = flexibleSearchService.searchUnique(searchQuery);
		}
		catch (ModelNotFoundException e)
		{
			LOGGER.warn(String.format(PARTYID_NOT_FOUND_ERROR_MSG, partyId), e);
			throw new ModelNotFoundException(PARTYID_NOT_FOUND_ERROR_MSG, e);
		}
		return result;
	}

	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

}
