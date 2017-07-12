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
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.PaginationData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.model.AmwayBonusPeriodModel;
import com.amway.core.orderperiod.dao.AmwayBonusPeriodDao;


/**
 * Default implementation
 */
public class DefaultAmwayBonusPeriodDao extends DefaultGenericDao<AmwayBonusPeriodModel> implements AmwayBonusPeriodDao
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayBonusPeriodDao.class);//NOPMD

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayBonusPeriodDao()
	{
		super(AmwayBonusPeriodModel._TYPECODE);
	}

	/**
	 * find all active bonus periods for the given list of sites.
	 *
	 * {@link #findActiveBonusPeriods(java.util.Collection)}
	 */
	@Override
	public List<AmwayBonusPeriodModel> findActiveBonusPeriods(final Collection<BaseSiteModel> sites)
	{
		final Map<String, Object> attributes = new HashMap();
		for (final BaseSiteModel siteModel : sites)
		{
			attributes.put("key" + Integer.toString(siteModel.hashCode()), siteModel);
		}

		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {bp:").append(AmwayBonusPeriodModel.PK).append("} FROM { ")
				.append(AmwayBonusPeriodModel._TYPECODE).append(" AS bp ");
		queryString.append("JOIN BonusPeriodsForStore AS bps ON {bp:pk}={bps:target} ");
		queryString.append("JOIN ").append(BaseStoreModel._STORESFORCMSSITE).append(" AS bs4cs ON ");
		queryString.append("{bs4cs:").append("target").append("}={bps:source}}");
		queryString.append(" WHERE (");
		for (final BaseSiteModel siteModel : sites)
		{
			queryString.append("{bs4cs:source} = ?").append("key" + Integer.toString(siteModel.hashCode())).append(" OR ");
		}
		queryString.replace(queryString.length() - 3, queryString.length() + 1, ")");
		queryString.append(" AND {").append(AmwayBonusPeriodModel.STATUS)
				.append("}= ({{Select {pk} from {AmwayPeriodTypeEnum} where {code}='ACTIVE'}})");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		query.getQueryParameters().putAll(attributes);
		final SearchResult<AmwayBonusPeriodModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	/**
	 * find bonus periods.
	 *
	 * {@link #findBonusPeriods()}
	 */
	@Override
	public List<AmwayBonusPeriodModel> findBonusPeriods()
	{
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {bp:").append(AmwayBonusPeriodModel.PK).append("} FROM { ")
				.append(AmwayBonusPeriodModel._TYPECODE).append(" AS bp ");
		queryString.append("JOIN BonusPeriodsForStore AS bps ON {bp:pk}={bps:target} ");
		queryString.append("JOIN ").append(BaseStoreModel._STORESFORCMSSITE).append(" AS bs4cs ON ");
		queryString.append("{bs4cs:").append("target").append("}={bps:source}}");
		queryString.append(" WHERE ");

		queryString.append(" {").append(AmwayBonusPeriodModel.STATUS)
				.append("} IN ({{Select {pk} from {AmwayPeriodTypeEnum} where {code} IN ('ACTIVE','INACTIVE','CLOSED')}})");

		LOG.debug(queryString);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryString.toString());
		final SearchResult<AmwayBonusPeriodModel> result = this.getFlexibleSearchService().search(query);
		return result.getResult();
	}

	/**
	 * find all bonus periods for the site.
	 *
	 * {@link #findAllBonusPeriodsForSiteByPeriod(de.hybris.platform.commerceservices.search.pagedata.PageableData, java.util.List, de.hybris.platform.core.model.user.UserModel)}
	 */
	@Override
	public SearchPageData<AmwayBonusPeriodModel> findAllBonusPeriodsForSiteByPeriod(final PageableData pageableData,
			final List<Date> dates, final UserModel user)
	{
		final Map queryParams = new HashMap();
		final StringBuilder queryString = new StringBuilder(100);
		queryString.append("SELECT {bp:").append(AmwayBonusPeriodModel.PK).append("} FROM { ")
				.append(AmwayBonusPeriodModel._TYPECODE).append(" AS bp ");
		queryString.append("JOIN BonusPeriodsForStore AS bps ON {bp:pk}={bps:target} ");
		queryString.append("JOIN ").append(BaseStoreModel._STORESFORCMSSITE).append(" AS bs4cs ON ");
		queryString.append("{bs4cs:").append("target").append("}={bps:source}}");
		queryString.append(" WHERE ");
		queryString.append(" {").append(AmwayBonusPeriodModel.STATUS)
				.append("} IN ({{Select {pk} from {AmwayPeriodTypeEnum} where {code} IN ('CLOSED')}})");
		if (CollectionUtils.isNotEmpty(dates) && dates.size() == 2)
		{
			queryString.append(" AND {bp:").append(AmwayBonusPeriodModel.STARTDATE).append("} >= ?startDate");
			queryString.append(" AND {bp:").append(AmwayBonusPeriodModel.STARTDATE).append("} <= ?endDate");
			queryParams.put("endDate", dates.get(0));
			queryParams.put("startDate", dates.get(1));
		}
		queryString.append(" AND {bp:").append(AmwayBonusPeriodModel.ENDDATE).append("} >= ?registerDate");
		queryString.append(" ORDER BY {bp:").append(AmwayBonusPeriodModel.STARTDATE).append("} DESC");

		queryParams.put("registerDate", user.getCreationtime());

		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(queryString.toString());
		if (!(queryParams.isEmpty()))
		{
			searchQuery.addQueryParameters(queryParams);
		}

		searchQuery.setNeedTotal(true);
		searchQuery.setStart(pageableData.getCurrentPage() * pageableData.getPageSize());
		searchQuery.setCount(pageableData.getPageSize());

		final SearchResult searchResult = getFlexibleSearchService().search(searchQuery);

		final SearchPageData result = createSearchPageData();
		result.setResults(searchResult.getResult());
		result.setPagination(createPagination(pageableData, searchResult));
		LOG.debug(searchQuery);
		return result;
	}

	protected <T> SearchPageData<T> createSearchPageData()
	{
		return new SearchPageData();
	}

	protected PaginationData createPagination(final PageableData pageableData, final SearchResult searchResult)
	{
		final PaginationData paginationData = createPaginationData();
		paginationData.setPageSize(pageableData.getPageSize());
		paginationData.setSort(pageableData.getSort());
		paginationData.setTotalNumberOfResults(searchResult.getTotalCount());


		paginationData
				.setNumberOfPages((int) Math.ceil((double) paginationData.getTotalNumberOfResults() / paginationData.getPageSize()));

		paginationData.setCurrentPage(Math.max(0, Math.min(paginationData.getNumberOfPages(), pageableData.getCurrentPage())));

		return paginationData;
	}

	protected PaginationData createPaginationData()
	{
		return new PaginationData();
	}
}
