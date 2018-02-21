/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.report.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amway.report.dao.AmwayReportDao;
import com.amway.report.model.AmwayReportModel;


/**
 * Default implementation of {@link AmwayReportDao}
 */
public class DefaultAmwayReportDao extends DefaultGenericDao<AmwayReportModel> implements AmwayReportDao
{

	private static final Logger LOG = Logger.getLogger(DefaultAmwayReportDao.class);

	final String REPORT_BY_CODE_AND_BASE_STORE = new StringBuilder(150).append("SELECT {r.pk} from {")
			.append(BaseStoreModel._TYPECODE).append(" AS bs join ").append(AmwayReportModel._BASESTORE2REPORTREL)
			.append(" as bs2r on {bs.").append(BaseStoreModel.PK).append("} = {bs2r.source").append("} join ")
			.append(AmwayReportModel._TYPECODE).append(" as r on {r.").append(AmwayReportModel.PK)
			.append("} = {bs2r.target}} where {bs2r.source} = ?baseStore and {r.").append(AmwayReportModel.CODE).append("} = ?code")
			.toString();

	public DefaultAmwayReportDao()
	{
		super(AmwayReportModel._TYPECODE);
	}

	@Override
	public List<AmwayReportModel> getReportByReportCode(final String code, final BaseStoreModel baseStore)
	{
		ServicesUtil.validateParameterNotNull(code, "Code cannot be null");
		ServicesUtil.validateParameterNotNull(code, "BaseStore cannot be null");
		final Map queryParams = new HashMap(2);
		queryParams.put("code", code);
		queryParams.put("baseStore", baseStore);
		LOG.info(REPORT_BY_CODE_AND_BASE_STORE);
		LOG.info(queryParams);
		final FlexibleSearchQuery flexibleSearchQuery = new FlexibleSearchQuery(REPORT_BY_CODE_AND_BASE_STORE, queryParams);
		final SearchResult<AmwayReportModel> result = getFlexibleSearchService().search(flexibleSearchQuery);
		return result.getResult();
	}

}
