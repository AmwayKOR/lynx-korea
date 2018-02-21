package com.amway.core.exceptions;

/**
 * Specific validation exception that is added to the response data.
 * The existing WebserviceValidationException does not put the error in the ErrorList of the response data.
 *
 */
public class AmwayEndpointValidationException extends Exception
{
	public AmwayEndpointValidationException()
	{
		super("Data provided to the endpoint is invalid.");
	}

	public AmwayEndpointValidationException(final String message)
	{
		super(message);
	}

}
