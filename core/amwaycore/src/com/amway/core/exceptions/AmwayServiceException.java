/**
 *
 */
package com.amway.core.exceptions;

/**
 * custom amway exception to handle message code
 */
public class AmwayServiceException extends RuntimeException
{
	private int messageCode;

	private String type;

	/**
	 * @param messageString
	 */
	public AmwayServiceException(final String messageString)
	{
		super(messageString);
	}

	/**
	 * @param messageString
	 * @param messageCode
	 */
	public AmwayServiceException(final String messageString, final int messageCode)
	{
		super(messageString);
		this.setMessageCode(messageCode);
	}

	/**
	 *
	 * @param messageString
	 * @param messageCode
	 */
	public AmwayServiceException(final String messageString, final int messageCode, final String type)
	{
		super(messageString);
		this.setMessageCode(messageCode);
		this.setType(type);
	}

	/**
	 *
	 * @return messageCode
	 */
	public int getMessageCode()
	{
		return messageCode;
	}

	/**
	 * @param messageCode the messageCode to set
	 */
	public void setMessageCode(final int messageCode)
	{
		this.messageCode = messageCode;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *           the type to set
	 */
	public void setType(final String type)
	{
		this.type = type;
	}

}
