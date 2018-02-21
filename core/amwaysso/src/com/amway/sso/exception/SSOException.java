/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.sso.exception;

public class SSOException extends RuntimeException
{
	public SSOException()
	{
		super();
	}

	public SSOException(final String paramString, final Throwable paramThrowable, final boolean paramBoolean1,
			final boolean paramBoolean2)
	{
		super(paramString, paramThrowable, paramBoolean1, paramBoolean2);
	}

	public SSOException(final String paramString, final Throwable paramThrowable)
	{
		super(paramString, paramThrowable);
	}

	public SSOException(final String paramString)
	{
		super(paramString);
	}

	public SSOException(final Throwable paramThrowable)
	{
		super(paramThrowable);
	}
}
