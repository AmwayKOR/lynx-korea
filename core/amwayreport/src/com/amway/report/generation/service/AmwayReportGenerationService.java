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
package com.amway.report.generation.service;

import de.hybris.platform.cockpit.reports.model.JasperMediaModel;

import java.util.Map;

import org.springframework.dao.DataAccessResourceFailureException;

import com.amway.report.enums.AmwayReportType;

import net.sf.jasperreports.engine.JRException;


/**
 * Provides functionality to generate reports from jasper media
 */
public interface AmwayReportGenerationService
{
	/**
	 * Method to generate the report from jasper media in the provided report type
	 *
	 * @param jasperReportMedia
	 *           the jasper report media
	 * @param reportType
	 *           the report type
	 * @param params
	 *           the report filter params
	 * @return the report file byte array
	 * @throws JRException
	 *            the jasper report exception
	 * @throws DataAccessResourceFailureException
	 *            the data access resource failure exception
	 */
	public byte[] generateReport(final JasperMediaModel jasperReportMedia, final AmwayReportType reportType,
			final Map<String, Object> params) throws JRException, DataAccessResourceFailureException;

}
