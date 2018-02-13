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
package com.amway.report.service.impl;

import com.amway.report.dao.AmwayReportDao;
import com.amway.report.enums.AmwayReportType;
import com.amway.report.exceptions.AmwayReportFormatException;
import com.amway.report.generation.service.AmwayReportGenerationService;
import com.amway.report.model.AmwayReportModel;
import com.amway.report.service.AmwayReportService;
import de.hybris.platform.cockpit.reports.exceptions.JasperReportExportException;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessResourceFailureException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Default implementation of {@link AmwayReportService}
 */
public class DefaultAmwayReportService implements AmwayReportService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayReportService.class);

	private static final String BASE_STORE_PK = "baseStorePK";

	private static final String REPORT_GENERATION_ERROR = "Error while generating report.";

	private AmwayReportGenerationService reportGenerationService;

	private AmwayReportDao amwayReportDao;

	@Override
	public Set<AmwayReportModel> getAllReports(final BaseStoreModel baseStore)
	{
		ServicesUtil.validateParameterNotNull(baseStore, "BaseStore cannot be null");
		return baseStore.getReports();
	}

	@Override
	public AmwayReportModel getReportByReportCode(final String code, final BaseStoreModel baseStore)
	{
		ServicesUtil.validateParameterNotNull(code, "Code cannot be null");
		ServicesUtil.validateParameterNotNull(baseStore, "BaseStore cannot be null");

		final List<AmwayReportModel> amwayReport = amwayReportDao.getReportByReportCode(code, baseStore);
		ServicesUtil.validateIfSingleResult(amwayReport,
				String.format("No Report found with code = %s and base store = %s ", code, baseStore.getUid()),
				String.format("More than one Reports found with code = %s and base store = %s ", code, baseStore.getUid()));
		return amwayReport.get(0);
	}

	@Override
	public byte[] exportReport(final String code, final AmwayReportType reportType, final BaseStoreModel baseStore,
			final Map<String, Object> reportFilterParams) throws AmwayReportFormatException
	{
		ServicesUtil.validateParameterNotNull(code, "Code cannot be null");
		ServicesUtil.validateParameterNotNull(reportType, "Report type must not be null.");
		ServicesUtil.validateParameterNotNull(baseStore, "BaseStore cannot be null");

		final AmwayReportModel report = getReportByReportCode(code, baseStore);
		try
		{
			Map<String, Object> filterParams = populateBaseStore(reportFilterParams, baseStore);
			return reportGenerationService.generateReport(report.getJasperMedia(), reportType, filterParams);
		}
		catch (final DataAccessResourceFailureException dARFE)
		{
			LOG.error(REPORT_GENERATION_ERROR, dARFE);
			throw new AmwayReportFormatException(REPORT_GENERATION_ERROR);
		}
		catch (final JRException jRE)
		{
			LOG.error(REPORT_GENERATION_ERROR, jRE);
			throw new AmwayReportFormatException(REPORT_GENERATION_ERROR);
		}
		catch (final JasperReportExportException jREE)
		{
			LOG.error(REPORT_GENERATION_ERROR, jREE);
			throw new AmwayReportFormatException(REPORT_GENERATION_ERROR);
		}
	}

	/**
	 * Method to add the base store to the filter parameters
	 *
	 * @param params
	 *           the filter parameters
	 * @param baseStore
	 *           the base store
	 * @return the new filter parameters
	 */
	private Map<String, Object> populateBaseStore(final Map<String, Object> params, final BaseStoreModel baseStore)
	{
		final Map<String, Object> newParams = new HashMap<String, Object>(params == null ? MapUtils.EMPTY_MAP : params);
		newParams.put(BASE_STORE_PK, baseStore.getPk().toString());
		return newParams;
	}

	/**
	 * @return the reportGenerationService
	 */
	public AmwayReportGenerationService getReportGenerationService()
	{
		return reportGenerationService;
	}

	/**
	 * @param reportGenerationService
	 *           the reportGenerationService to set
	 */
	@Required
	public void setReportGenerationService(final AmwayReportGenerationService reportGenerationService)
	{
		this.reportGenerationService = reportGenerationService;
	}

	/**
	 * @return the amwayReportDao
	 */
	public AmwayReportDao getAmwayReportDao()
	{
		return amwayReportDao;
	}

	/**
	 * @param amwayReportDao
	 *           the amwayReportDao to set
	 */
	@Required
	public void setAmwayReportDao(final AmwayReportDao amwayReportDao)
	{
		this.amwayReportDao = amwayReportDao;
	}


}
