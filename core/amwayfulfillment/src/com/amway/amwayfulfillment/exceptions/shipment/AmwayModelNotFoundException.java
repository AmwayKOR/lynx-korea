package com.amway.amwayfulfillment.exceptions.shipment;

public class AmwayModelNotFoundException extends AmwayShipmentConfirmationException
{
	public AmwayModelNotFoundException(String code, String message, Object... params)
	{
		super(code, message, params);
	}
}
