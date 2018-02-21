package com.amway.glos.dataobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Result status
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultStatus", propOrder = { "returnCode", "returnMessage" })
public class ResultStatus
{
	protected int returnCode;

	/**
	 * @return the returnCode
	 */
	public int getReturnCode()
	{
		return returnCode;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(final int returnCode)
	{
		this.returnCode = returnCode;
	}

	/**
	 * @return the returnMessage
	 */
	public String getReturnMessage()
	{
		return returnMessage;
	}

	/**
	 * @param returnMessage the returnMessage to set
	 */
	public void setReturnMessage(final String returnMessage)
	{
		this.returnMessage = returnMessage;
	}

	protected String returnMessage;
}
