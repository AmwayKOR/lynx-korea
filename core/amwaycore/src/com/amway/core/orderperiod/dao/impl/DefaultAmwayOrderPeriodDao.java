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
package com.amway.core.orderperiod.dao.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.core.orderperiod.dao.AmwayOrderPeriodDao;


/**
 * Default implementation
 */
public class DefaultAmwayOrderPeriodDao extends DefaultGenericDao<AmwayOrderPeriodModel> implements AmwayOrderPeriodDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayOrderPeriodDao.class);//NOPMD

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayOrderPeriodDao()
	{
		super(AmwayOrderPeriodModel._TYPECODE);
	}

	/**
	 * find all active order periods for the given list of sites
	 *
	 * @param sites
	 * @return List of order periods.
	 */
	@Override
	public List<AmwayOrderPeriodModel> findActiveOrderPeriods(final Collection<BaseSiteModel> sites)
	{
		final Map<String, Object> attributes = new HashMap();
		for (final BaseSiteModel siteModel : sites)
		{
			attributes.put("key" + Integer.toString(siteModel.hashCode()), siteModel);
		}

		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {op:").append(AmwayOrderPeriodModel.PK).append("} FROM { ")
				.append(AmwayOrderPeriodModel._TYPECODE).append(" AS op ");
		queryString.append("JOIN ").append(BaseStoreModel._TYPECODE).append(" AS bs ON ");
		queryString.append("{op:").append(AmwayOrderPeriodModel.STORE).append("}={bs:").append(BaseStoreModel.PK).append('}');
		queryString.append("JOIN ").append(BaseStoreModel._STORESFORCMSSITE).append(" AS bs4cs ON ");
		queryString.append("{bs4cs:").append("target").append("}={bs:").append(BaseStoreModel.PK).append('}');
		queryString.append(" }");
		queryString.append(" WHERE (");
		for (final BaseSiteModel siteModel : sites)
		{
			queryString.append("{bs4cs:source} = ?").append("key" + Integer.toString(siteModel.hashCode())).append(" OR ");
		}
		queryString.replace(queryString.length() - 3, queryString.length() + 1, ")");
		queryString.append(" AND {").append(AmwayOrderPeriodModel.STATUS)
				.append("}= ({{Select {pk} from {AmwayPeriodTypeEnum} where {code}='ACTIVE'}})");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayOrderPeriodModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}


	/**
	 * To find order periods.
	 *
	 * @return The list of order periods.
	 */
	@Override
	public List<AmwayOrderPeriodModel> findOrderPeriods()
	{
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {op:").append(AmwayOrderPeriodModel.PK).append("} FROM { ")
				.append(AmwayOrderPeriodModel._TYPECODE).append(" AS op ");
		queryString.append("JOIN ").append(BaseStoreModel._TYPECODE).append(" AS bs ON ");
		queryString.append("{op:").append(AmwayOrderPeriodModel.STORE).append("}={bs:").append(BaseStoreModel.PK).append('}');
		queryString.append("JOIN ").append(BaseStoreModel._STORESFORCMSSITE).append(" AS bs4cs ON ");
		queryString.append("{bs4cs:").append("target").append("}={bs:").append(BaseStoreModel.PK).append('}');
		queryString.append(" }");
		queryString.append(" WHERE ");

		queryString.append(" {").append(AmwayOrderPeriodModel.STATUS)
				.append("} IN ({{Select {pk} from {AmwayPeriodTypeEnum} where {code} IN ('ACTIVE','INACTIVE','CLOSED')}})");
		LOG.debug(queryString);
		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<AmwayOrderPeriodModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}
}
