package com.amway.amwayfulfillment.exceptions.shipment;


/**
 * Exception that may arise during shipment confirmation
 */
public class AmwayShipmentConfirmationException extends Exception
{

	private final String message;
	private final String code;
	private transient Object[] params;  //since params are optional in the method signature, which implies the value
													//is sometimes null in practice, make this class storage transient for now
	
	public AmwayShipmentConfirmationException(String code, String message)
	{
		super(message);
		this.code = code;
		this.message = message;
	}

	public AmwayShipmentConfirmationException(String code, String message, Object ... params)
	{
		super(message);
		this.code = code;
		this.message = params == null ? message : String.format(message, params);
		this.params = params;
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	public String getCode()
	{
		return code;
	}

	public Object[] getParams()
	{
		return params;
	}

}
