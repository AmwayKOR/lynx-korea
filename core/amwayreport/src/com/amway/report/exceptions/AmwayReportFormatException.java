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
package com.amway.report.exceptions;

/**
 * The Report Format exception.
 */
public class AmwayReportFormatException extends Exception
{

	public AmwayReportFormatException()
	{
		super("Invalid Report format.");
	}

	public AmwayReportFormatException(final String message)
	{
		super(message);
	}
}
