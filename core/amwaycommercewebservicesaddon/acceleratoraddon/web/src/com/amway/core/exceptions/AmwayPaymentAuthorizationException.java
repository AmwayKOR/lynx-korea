package com.amway.core.exceptions;

/**
 * Specific exception that is thrown when the payment authorization was not accepted
 *
 *
 *
 */
public class AmwayPaymentAuthorizationException extends Exception
{

	//Amway Core methods
	public AmwayPaymentAuthorizationException(final String message)
	{
		super(message);
	}

}
