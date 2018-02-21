/**
 *
 */
package com.amway.glos.dataobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * pojo to extract response of service
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LosIntlBusSponsorValidationResults", propOrder = { "affiliateName", "lmscertified", "qualifiedPlatinum",
		"resultStatus", "returnCode", "returnMessage" })
public class LosIntlBusSponsorValidationResult
{
	protected String affiliateName;
	protected String lmscertified;
	protected String qualifiedPlatinum;
	protected int returnCode;
	protected String returnMessage;
	protected ResultStatus resultStatus;


	/**
	 * @return the resultStatus
	 */
	public ResultStatus getResultStatus()
	{
		return resultStatus;
	}

	/**
	 * @param resultStatus the resultStatus to set
	 */
	public void setResultStatus(final ResultStatus resultStatus)
	{
		this.resultStatus = resultStatus;
	}

	/**
	 * @return affiliateName
	 */
	public String getAffiliateName()
	{
		return affiliateName;
	}

	/**
	 * @param affiliateName the affiliateName to set
	 */
	public void setAffiliateName(final String affiliateName)
	{
		this.affiliateName = affiliateName;
	}

	/**
	 * @return lmscertified
	 */
	public String getLmscertified()
	{
		return lmscertified;
	}

	/**
	 * @param lmscertified the lmscertified to set
	 */
	public void setLmscertified(final String lmscertified)
	{
		this.lmscertified = lmscertified;
	}

	/**
	 * @return qualifiedPlatinum
	 */
	public String getQualifiedPlatinum()
	{
		return qualifiedPlatinum;
	}

	/**
	 * @param qualifiedPlatinum the qualifiedPlatinum to set
	 */
	public void setQualifiedPlatinum(final String qualifiedPlatinum)
	{
		this.qualifiedPlatinum = qualifiedPlatinum;
	}

	/**
	 * @return returnCode
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
	 * @return returnMessage
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
}
