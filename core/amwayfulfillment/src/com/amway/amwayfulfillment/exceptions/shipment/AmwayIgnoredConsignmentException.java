package com.amway.amwayfulfillment.exceptions.shipment;

public class AmwayIgnoredConsignmentException extends AmwayShipmentConfirmationException
{
	public AmwayIgnoredConsignmentException(String code, String message, Object... params)
	{
		super(code, message, params);
	}
}
