package com.amway.core.exceptions;

/**
 * Specific exception that is thrown when the payment amount doesn't match with order total
 *
 */
public class InsufficientPaymentAmountException extends Exception
{
	public InsufficientPaymentAmountException()
	{
		super("Payment Amount doesn't match with Order total");
	}

	public InsufficientPaymentAmountException(final String message)
	{
		super(message);
	}
}