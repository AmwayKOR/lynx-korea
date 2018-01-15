/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.apac.resourcecenter.enums;

/**
 *
 */
public enum AmwayApacAssetsSort
{
	LATEST_DATE("latestDate", "Latest Date", "ORDER BY {a.date} DESC"), TITTLE_ASCENDING("ATOZ", "A to Z",
			"ORDER BY {a.title}"), TITTLE_DESCENDING("ZTOA", "Z to A",
					"ORDER BY {a.title} DESC"), ID_ASCENDING("ID", "A to Z", "ORDER BY {a.assetId}");

	private String code;
	private String description;
	private String query;

	private AmwayApacAssetsSort(final String code, final String description, final String query)
	{
		this.code = code;
		this.description = description;
		this.query = query;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @return the query
	 */
	public String getQuery()
	{
		return query;
	}
}
