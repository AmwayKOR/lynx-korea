package com.amway.integration.dam.util;

import com.amway.integration.dam.exception.AmwayDamException;


public class AmwayDamValidationUtil
{
	private AmwayDamValidationUtil()
	{
	}

	public static void validate(Boolean expression, String errorCode, String... params)
	{
		if (!expression)
		{
			throw new AmwayDamException(errorCode, params);
		}
	}

	public static void validateNotNull(Object target, String errorCode, String... params)
	{
		validate(target != null, errorCode, params);
	}
}
