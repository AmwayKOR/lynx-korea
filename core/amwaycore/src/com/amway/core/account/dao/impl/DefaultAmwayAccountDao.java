/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.amway.core.account.dao.impl;

import com.amway.core.account.dao.AmwayAccountDao;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayBusinessRestrictionModel;
import com.amway.core.model.AmwayUserIdentityModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.session.SessionExecutionBody;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Default implementation
 */
public class DefaultAmwayAccountDao extends DefaultGenericDao<AmwayAccountModel> implements AmwayAccountDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayAccountDao.class);//NOPMD

	private SessionService sessionService;
	private UserService userService;

	private static final String CUSTOMER_BY_IDENTITY_QUERY = new StringBuilder().append("SELECT {").append(CustomerModel.PK)
			.append("} FROM {").append(CustomerModel._TYPECODE).append("} WHERE {").append(CustomerModel.IDENTITY).append("} = ?")
			.append(CustomerModel.IDENTITY).toString().intern();


	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayAccountDao()
	{
		super(AmwayAccountModel._TYPECODE);
	}

	/**
	 * {@link #getAccount(java.lang.String)}
	 */
	@Override
	public List<AmwayAccountModel> getAccount(final String code)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("code", code);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {am:").append(AmwayAccountModel.PK).append("} FROM {").append(AmwayAccountModel._TYPECODE)
				.append(" AS am}");
		queryString.append(" WHERE {");
		queryString.append(AmwayAccountModel.CODE).append("}=?code");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayAccountModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	/**
	 * {@link #getAccountsForUidOrName(java.lang.String)}
	 */
	@Override
	public List<AmwayAccountModel> getAccountsForUidOrName(final String searchKey)
	{
		final StringBuilder queryString = new StringBuilder(100);

		queryString.append(
				"SELECT {am:pk},{am:code} FROM {AmwayAccount AS am },{AmwayBusinessNature as amb} WHERE  {am:businessNature} = {amb:pk} AND {amb:code}= 'AmwayBusinessNature_1'  AND  ( p_code Like '"
						+ searchKey + "%' OR LOWER({am:name[en]}) Like '%" + searchKey + "%' ) ORDER BY {am:code} ");

		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public List<AmwayAccountModel> execute()
			{
				final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
				final SearchResult<AmwayAccountModel> result = getFlexibleSearchService().search(query);
				LOG.debug(queryString + " ---- " + result.getCount());
				return result.getResult();
			}
		}, getUserService().getAdminUser());
	}


	@Override
	public AmwayBusinessRestrictionModel getBusinessRestrictionFromCode(final String accountCode)
	{
		final StringBuilder queryString = new StringBuilder(100);

		queryString.append("SELECT {pk} FROM {AmwayBusinessRestriction} where {code}='" + accountCode + "'");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<AmwayBusinessRestrictionModel> result = this.getFlexibleSearchService().search(query);
		if (CollectionUtils.isEmpty(result.getResult()))
		{
			return null;
		}
		return result.getResult().get(0);
	}




	@Override
	public List<AmwayAccountModel> lookupAccountsByName(final String searchKey)
	{
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {am:pk},{am:code} FROM {AmwayAccount AS am } WHERE  LOWER({am:name[en]}) Like '%" + searchKey
				+ "%'  ORDER BY {am:name[en]} ");

		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public List<AmwayAccountModel> execute()
			{
				final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
				final SearchResult<AmwayAccountModel> result = getFlexibleSearchService().search(query);
				LOG.debug(queryString + " ---- " + result.getCount());
				return result.getResult();
			}
		}, getUserService().getAdminUser());
	}

	@Override
	public List<AmwayAccountModel> lookupAccountsById(final String searchKey)
	{
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append(
				"SELECT {am:pk},{am:code} FROM {AmwayAccount AS am } WHERE  p_code Like '%" + searchKey + "%'  ORDER BY p_code ");

		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public List<AmwayAccountModel> execute()
			{
				final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
				final SearchResult<AmwayAccountModel> result = getFlexibleSearchService().search(query);
				LOG.debug(queryString + " ---- " + result.getCount());
				return result.getResult();
			}
		}, getUserService().getAdminUser());
	}

	@Override
	public List<CustomerModel> lookupAccountsCustomersByPartyId(final String searchKey)
	{
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append(
				"SELECT {cust:pk} FROM {Customer AS cust } WHERE  p_customerid Like '%" + searchKey + "%'  ORDER BY p_customerid ");

		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public List<AmwayAccountModel> execute()
			{
				final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
				final SearchResult<AmwayAccountModel> result = getFlexibleSearchService().search(query);
				LOG.debug(queryString + " ---- " + result.getCount());
				return result.getResult();
			}
		}, getUserService().getAdminUser());
	}

	@Override
	public List<CustomerModel> lookupAccountsCustomersByPartyName(final String searchKey)
	{
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {cust:pk} FROM {Customer AS cust } WHERE  p_name Like '%" + searchKey + "%'  ORDER BY p_name ");

		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public List<AmwayAccountModel> execute()
			{
				final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
				final SearchResult<AmwayAccountModel> result = getFlexibleSearchService().search(query);
				LOG.debug(queryString + " ---- " + result.getCount());
				return result.getResult();
			}
		}, getUserService().getAdminUser());
	}

	@Override
	public List<CustomerModel> lookupAccountsCustomersByUid(final String searchKey)
	{
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {cust:pk} FROM {Customer AS cust } WHERE  p_uid Like '%" + searchKey + "%'  ORDER BY p_uid ");

		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public List<AmwayAccountModel> execute()
			{
				final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
				final SearchResult<AmwayAccountModel> result = getFlexibleSearchService().search(query);
				LOG.debug(queryString + " ---- " + result.getCount());
				return result.getResult();
			}
		}, getUserService().getAdminUser());
	}


	public List<CustomerModel> lookupAccountsCustomersByEmail(final String searchKey)
	{
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {cust:pk} FROM {Customer AS cust } WHERE  p_customeremail Like '%" + searchKey + "%'  ORDER BY p_customeremail ");

		return getSessionService().executeInLocalView(new SessionExecutionBody()
		{
			@Override
			public List<AmwayAccountModel> execute()
			{
				final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
				final SearchResult<AmwayAccountModel> result = getFlexibleSearchService().search(query);
				LOG.debug(queryString + " ---- " + result.getCount());
				return result.getResult();
			}
		}, getUserService().getAdminUser());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.core.account.dao.AmwayAccountDao#getCustomerForAmwayUserIdentity(java.util.List)
	 */
	@Override
	public List<CustomerModel> getCustomerForAmwayUserIdentity(final AmwayUserIdentityModel userIdentities)
	{
		final FlexibleSearchQuery query = new FlexibleSearchQuery(CUSTOMER_BY_IDENTITY_QUERY, Collections.singletonMap(
				CustomerModel.IDENTITY, userIdentities));
		LOG.debug("fetching user identity for code " + userIdentities);
		final SearchResult<CustomerModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();

	}

	/**
	 * @return the sessionService
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * @param sessionService the sessionService to set
	 */
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}
}
