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
package com.amway.core.events.constants;

/**
 * Global class for all Amwayevents constants. You can add global constants for your extension into this class.
 */
public final class AmwayeventsConstants extends GeneratedAmwayeventsConstants
{
	public static final String EXTENSIONNAME = "amwayevents";
	/**
	 * Event target - web methods.
	 */
	public static final String TARGET_WM = "wm";
	/**
	 * Event target - internal cron jobs.
	 */
	public static final String TARGET_INTERNAL_CRON = "internalCron";


	private AmwayeventsConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension
}
