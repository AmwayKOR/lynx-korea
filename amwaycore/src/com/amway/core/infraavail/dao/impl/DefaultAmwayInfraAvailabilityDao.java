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
package com.amway.core.infraavail.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amway.core.infraavail.dao.AmwayInfraAvailabilityDao;
import com.amway.core.model.AmwayInfraAvailabilityModel;


/**
 * Default implementation
 */
public class DefaultAmwayInfraAvailabilityDao extends DefaultGenericDao<AmwayInfraAvailabilityModel>
		implements AmwayInfraAvailabilityDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayInfraAvailabilityDao.class);//NOPMD

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayInfraAvailabilityDao()
	{
		super(AmwayInfraAvailabilityModel._TYPECODE);
	}

	/**
	 * {@link #findInfraAvail(java.lang.String, java.lang.String)}
	 */
	@Override
	public List<AmwayInfraAvailabilityModel> findInfraAvail(final String code, final String baseStoreUid)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("code", code);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {aia:").append(AmwayInfraAvailabilityModel.PK).append("} FROM { ")
				.append(AmwayInfraAvailabilityModel._TYPECODE).append(" AS aia }");
		queryString.append(" WHERE {aia:");
		queryString.append(AmwayInfraAvailabilityModel.CODE).append("}=?code");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayInfraAvailabilityModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}
}
