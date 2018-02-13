package com.amway.integration.dam.exception;

/**
 * Exceptions appearing during handling DAM events
 */
public class AmwayDamException extends RuntimeException
{
	private String code;

	private String[] params;

	public AmwayDamException(String code, String[] params)
	{
		this.code = code;
		this.params = params.clone();
	}

	public String getCode()
	{
		return code;
	}

	public String[] getParams()
	{
		return params;
	}
}
