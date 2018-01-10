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
package com.amway.report.generation.service.impl;

import de.hybris.platform.cockpit.reports.JasperReportExportService;
import de.hybris.platform.cockpit.reports.exceptions.JasperReportExportException;
import de.hybris.platform.cockpit.reports.factories.JasperReportConnectionFactory;
import de.hybris.platform.cockpit.reports.model.JasperMediaModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessResourceFailureException;

import com.amway.report.enums.AmwayReportType;
import com.amway.report.generation.service.AmwayReportGenerationService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


/**
 * Default implementation of {@link AmwayReportGenerationService}
 */
public class DefaultAmwayReportGenerationService implements AmwayReportGenerationService
{

	private static final Logger LOG = Logger.getLogger(DefaultAmwayReportGenerationService.class);

	private I18NService i18nService;

	private MediaService mediaService;

	private JasperReportConnectionFactory jasperReportConnectionFactory;

	private JasperReportExportService jasperReportExportService;

	@Override
	public byte[] generateReport(final JasperMediaModel jasperReportMedia, final AmwayReportType reportType,
			final Map<String, Object> params) throws JRException, DataAccessResourceFailureException, JasperReportExportException
	{
		ServicesUtil.validateParameterNotNull(jasperReportMedia, "Jasper report media must not be null.");
		ServicesUtil.validateParameterNotNull(reportType, "Report type must not be null.");
		byte[] reportContent = null;
		LOG.debug("Generating report for jasper report media : " + jasperReportMedia.getCode());

		final Map<String, Object> filterParams = addLocaleToParameters(params);
		final InputStream jasperTemplate = mediaService.getStreamFromMedia(jasperReportMedia);
		final JasperReport jasperReport = JasperCompileManager.compileReport(jasperTemplate);

		JasperPrint jasperPrint = null;
		Connection connection = null;
		try
		{
			connection = jasperReportConnectionFactory.createConnection();
			jasperPrint = JasperFillManager.fillReport(jasperReport, filterParams, connection);
			reportContent = export(jasperPrint, reportType);
		}
		finally
		{
			try
			{
				if (null != connection && !connection.isClosed())
				{
					connection.close();
				}
			}
			catch (final SQLException sQE)
			{
				LOG.error("Error in closing vjdbc connection.", sQE);
			}
		}

		LOG.debug("Contract generated ");

		return reportContent;
	}

	/**
	 * Method to add the locale to the filter parameters
	 *
	 * @param params
	 *           the filter parameters
	 * @return the new filter parameters
	 */
	private Map<String, Object> addLocaleToParameters(final Map<String, Object> params)
	{
		final Map<String, Object> newParams = new HashMap<String, Object>(params == null ? MapUtils.EMPTY_MAP : params);
		newParams.put(JRParameter.REPORT_LOCALE, i18nService.getCurrentLocale());
		return newParams;
	}

	/**
	 * Method to export the jasper print data in the specified report type
	 *
	 * @param jasperPrint
	 *           the jasper print data
	 * @param reportType
	 *           the report type
	 * @return the exported report file byte array
	 * @throws JasperReportExportException
	 *            the jasper report export exception
	 */
	private byte[] export(final JasperPrint jasperPrint, final AmwayReportType reportType) throws JasperReportExportException
	{
		byte[] reportContent = null;
		switch (reportType)
		{
			case EXCEL:
				reportContent = jasperReportExportService.exportToExcel(jasperPrint);
		}
		return reportContent;

	}

	/**
	 * @return the i18nService
	 */
	public I18NService getI18nService()
	{
		return i18nService;
	}

	/**
	 * @param i18nService
	 *           the i18nService to set
	 */
	@Required
	public void setI18nService(final I18NService i18nService)
	{
		this.i18nService = i18nService;
	}

	/**
	 * @return the mediaService
	 */
	public MediaService getMediaService()
	{
		return mediaService;
	}

	/**
	 * @param mediaService
	 *           the mediaService to set
	 */
	@Required
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	/**
	 * @return the jasperReportConnectionFactory
	 */
	public JasperReportConnectionFactory getJasperReportConnectionFactory()
	{
		return jasperReportConnectionFactory;
	}

	/**
	 * @param jasperReportConnectionFactory
	 *           the jasperReportConnectionFactory to set
	 */
	@Required
	public void setJasperReportConnectionFactory(final JasperReportConnectionFactory jasperReportConnectionFactory)
	{
		this.jasperReportConnectionFactory = jasperReportConnectionFactory;
	}

	/**
	 * @return the jasperReportExportService
	 */
	public JasperReportExportService getJasperReportExportService()
	{
		return jasperReportExportService;
	}

	/**
	 * @param jasperReportExportService
	 *           the jasperReportExportService to set
	 */
	@Required
	public void setJasperReportExportService(final JasperReportExportService jasperReportExportService)
	{
		this.jasperReportExportService = jasperReportExportService;
	}

}
