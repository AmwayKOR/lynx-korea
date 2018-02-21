package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * Dms Request for delete credit profile data.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteCreditProfileDataRequest", propOrder =
{ "cntryCd", "partyId" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteCreditProfileDataRequest extends BaseWebServiceInput
{
	protected String cntryCd;
	protected String partyId;

	/**
	 * 
	 * @return cntryCd
	 */
	public String getCntryCd()
	{
		return cntryCd;
	}

	/**
	 * 
	 * @param cntryCd
	 *           the cntryCd to set
	 */
	public void setCntryCd(final String cntryCd)
	{
		this.cntryCd = cntryCd;
	}

	/**
	 * 
	 * @return partyId
	 */
	public String getPartyId()
	{
		return partyId;
	}

	/**
	 * 
	 * @param partyId
	 *           the partyId to set
	 */
	public void setPartyId(final String partyId)
	{
		this.partyId = partyId;
	}
}
