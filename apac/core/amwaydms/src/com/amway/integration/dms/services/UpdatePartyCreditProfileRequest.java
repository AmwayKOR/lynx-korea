package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateCreditProfileRequest", propOrder =
{ "partyCreditProfile" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePartyCreditProfileRequest extends BaseWebServiceInput
{
	@XmlElement(nillable = true)
	protected PartyCreditPofileObject partyCreditProfile;

	/**
	 * 
	 * @return partyCreditProfile
	 */
	public PartyCreditPofileObject getPartyCreditProfile()
	{
		return partyCreditProfile;
	}

	/**
	 * 
	 * @param partyCreditProfile
	 *           the partyCreditProfile to set
	 */
	public void setPartyCreditProfile(final PartyCreditPofileObject partyCreditProfile)
	{
		this.partyCreditProfile = partyCreditProfile;
	}


}
