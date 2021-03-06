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
package com.amway.apac.facades.constants;

/**
 * Global class for all AmwayapacFacades constants.
 */
@SuppressWarnings("PMD")
public class AmwayapacFacadesConstants extends GeneratedAmwayapacFacadesConstants
{
	public static final String EXTENSIONNAME = "amwayapacfacades";

	//For order history
	public static final String SHOW_ALL = "SHOW_ALL";
	public static final String PARTNER_STORES = "PARTNER_STORES";
	public static final String ACTIVE = "ACTIVE";
	public static final String WEB = "WEB";
	public static final String DITTO = "DITTO";
	public static final String TELEPHONE = "TELEPHONE";
	public static final String MAIL = "MAIL";
	public static final String SERVICE_CENTER = "SERVICE_CENTER";
	public static final String AUTO_RENEW = "AUTO_RENEW";
	public static final String REPLACEMENT = "REPLACEMENT";
	public static final String FIRST_DAY_OF_MONTH = "-01";
	public static final String LAST_DAY_OF_MONTH = "-31";
	public static final String LAST_THIRTY_DAYS = "last30Days";
	public static final String ORDER_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	public static final String PRODUCT_MODEL = "productModel";
	public static final String STOCK_DATA = "stockData";

	/**
	 * Error message for when parameter category is empty.
	 */
	public static final String PARAMETER_CATEGORY_CAN_NOT_BE_EMPTY = "Parameter category can not be empty.";

	/**
	 * Error message for when parameter code is null
	 */
	public static final String PARAMETER_CODE_CAN_NOT_BE_NULL = "Parameter code can not be null.";

	public static final int ZERO = 0;

	/**
	 * Max number of results for product listing screens.
	 */
	public static final int PRODUCT_LISTING_MAX_NUMBER_OF_RECORDS = 150;

	private AmwayapacFacadesConstants()
	{
		//empty
	}
}
