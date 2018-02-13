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
package com.amway.report.dao;

import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

import com.amway.report.model.AmwayReportModel;


/**
 * The dao for amway reports.
 */
public interface AmwayReportDao
{

	/**
	 * Method to get report for the code provided.
	 *
	 * @param code
	 *           the report code
	 * @param baseStore
	 *           the base store
	 * @return the report model list
	 */
	List<AmwayReportModel> getReportByReportCode(final String code, final BaseStoreModel baseStore);

}
