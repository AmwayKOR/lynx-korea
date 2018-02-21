package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * Dms request for credit profile.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditProfileRequest", propOrder =
{ "aboNum", "salesPlanAff", "partyId" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditProfileRequest extends BaseWebServiceInput
{
	/**
	 * @return the aboNum
	 */
	public String getAboNum()
	{
		return aboNum;
	}

	/**
	 * @param aboNum
	 *           the aboNum to set
	 */
	public void setAboNum(final String aboNum)
	{
		this.aboNum = aboNum;
	}

	/**
	 * @return the salesPlanAff
	 */
	public String getSalesPlanAff()
	{
		return salesPlanAff;
	}

	/**
	 * @param salesPlanAff
	 *           the salesPlanAff to set
	 */
	public void setSalesPlanAff(final String salesPlanAff)
	{
		this.salesPlanAff = salesPlanAff;
	}

	/**
	 * @return the partyId
	 */
	public String getPartyId()
	{
		return partyId;
	}

	/**
	 * @param partyId
	 *           the partyId to set
	 */
	public void setPartyId(final String partyId)
	{
		this.partyId = partyId;
	}

	protected String aboNum;
	protected String salesPlanAff;
	protected String partyId;
}
