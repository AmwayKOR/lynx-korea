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
package com.amway.core.oauth2.constants;

/**
 * Global class for all Amwayoauth2 constants. You can add global constants for your extension into this class.
 */
public final class Amwayoauth2Constants extends GeneratedAmwayoauth2Constants
{
	public static final String EXTENSIONNAME = "amwayoauth2";

	private Amwayoauth2Constants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension

	public static final String PLATFORM_LOGO_CODE = "amwayoauth2PlatformLogo";

	public interface POS
	{
		String PARAM_MAC_ADDRESS = "mac_address";
		String EMPLOYEE_ROLE_GROUP = "posemployeegroup";
		String SCOPE_REPORTS = "reports";

	}
}
