/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 *
 */
package com.amway.core.constants;

/**
 * Global class for all Amwaycommercewebservicesaddon web constants. You can add global constants for your extension
 * into this class.
 */
public final class AmwaycommercewebservicesaddonWebConstants
{
	//Dummy field to avoid pmd error - delete when you add the first real constant!
	public static final String deleteThisDummyField = "DELETE ME";

	private AmwaycommercewebservicesaddonWebConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension
	public static final String AUTORENEWAL_SERVICE_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATE_FORMAT_INCORRECT = "Date format is incorrect. Correct date format is yyyy-MM-dd";

	public static final String AUTORENEWAL_COUNTRY_REQUIRED = "autorenewal.country.field.required.msg";
	public static final String AUTORENEWAL_LANGUAGE_REQUIRED = "autorenewal.language.field.required.msg";
	public static final String AUTORENEWAL_PAYMENT_ALIAS_REQUIRED = "autorenewal.payment.alias.field.required.msg";

}
