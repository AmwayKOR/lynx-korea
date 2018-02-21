/**
 *
 */
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyCreditPofileObject", propOrder =
{ "cntryCd", "creditScore", "creditStatusCd", "ficoScore", "partyId" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyCreditPofileObject
{
	protected String cntryCd;
	protected String creditScore;
	protected String creditStatusCd;
	protected String ficoScore;
	protected String partyId;

	/**
	 * @return the cntryCd
	 */
	public String getCntryCd()
	{
		return cntryCd;
	}

	/**
	 * @param cntryCd
	 *           the cntryCd to set
	 */
	public void setCntryCd(final String cntryCd)
	{
		this.cntryCd = cntryCd;
	}

	/**
	 * @return the creditScore
	 */
	public String getCreditScore()
	{
		return creditScore;
	}

	/**
	 * @param creditScore
	 *           the creditScore to set
	 */
	public void setCreditScore(final String creditScore)
	{
		this.creditScore = creditScore;
	}

	/**
	 * @return the creditStatusCd
	 */
	public String getCreditStatusCd()
	{
		return creditStatusCd;
	}

	/**
	 * @param creditStatusCd
	 *           the creditStatusCd to set
	 */
	public void setCreditStatusCd(final String creditStatusCd)
	{
		this.creditStatusCd = creditStatusCd;
	}

	/**
	 * @return the ficoScore
	 */
	public String getFicoScore()
	{
		return ficoScore;
	}

	/**
	 * @param ficoScore
	 *           the ficoScore to set
	 */
	public void setFicoScore(final String ficoScore)
	{
		this.ficoScore = ficoScore;
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

}
