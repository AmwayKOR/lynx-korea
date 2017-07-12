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
package com.amway.core.message.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amway.core.message.dao.AmwayMessageDao;
import com.amway.core.model.AmwayMessageModel;


/**
 * Default implementation
 */
public class DefaultAmwayMessageDao extends DefaultGenericDao<AmwayMessageModel> implements AmwayMessageDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayMessageDao.class);//NOPMD

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayMessageDao()
	{
		super(AmwayMessageModel._TYPECODE);
	}

	/**
	 * To find message for the given code
	 *
	 * @param code
	 * @return List<AmwayMessageModel>
	 */
	@Override
	public List<AmwayMessageModel> findMessage(final String code)
	{
		final Map<String, Object> attributes = new HashMap();
		attributes.put("code", code);
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {am:").append(AmwayMessageModel.PK).append("} FROM { ").append(AmwayMessageModel._TYPECODE)
				.append(" AS am }");
		queryString.append(" WHERE {");
		queryString.append(AmwayMessageModel.CODE).append("}=?code");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayMessageModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}
}
