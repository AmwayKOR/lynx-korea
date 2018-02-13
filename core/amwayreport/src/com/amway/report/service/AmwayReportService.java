/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.report.service;

import com.amway.report.enums.AmwayReportType;
import com.amway.report.exceptions.AmwayReportFormatException;
import com.amway.report.model.AmwayReportModel;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Map;
import java.util.Set;


/**
 * Provides functionality to generate
 */
public interface AmwayReportService
{

	/**
	 * Method to get all reports.
	 *
	 * @param baseStore
	 *           the base store
	 * @return list of reports
	 */
	Set<AmwayReportModel> getAllReports(final BaseStoreModel baseStore);


	/**
	 * Method to get report for the provided code.
	 *
	 * @param code
	 *           the report code
	 * @param baseStore
	 *           the base store
	 * @return the report model
	 */
	AmwayReportModel getReportByReportCode(final String code, final BaseStoreModel baseStore);


	/**
	 * Method to export the report in the specified report type.
	 *
	 * @param code
	 *           the report code
	 * @param reportType
	 *           the report type
	 * @param baseStore
	 *           the base store
	 * @param reportFilterParams
	 *           the report filter param data
	 * @return the report file byte array
	 * @throws AmwayReportFormatException
	 *            the report format exception
	 */
	byte[] exportReport(final String code, final AmwayReportType reportType, final BaseStoreModel baseStore,
			final Map<String, Object> reportFilterParams) throws AmwayReportFormatException;

}
