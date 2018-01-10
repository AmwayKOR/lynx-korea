package com.amway.integration.dms.services;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * Dms request for add party credit profile.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPartyCreditProfileRequest", propOrder =
{ "partyCreditProfileList" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPartyCreditProfileRequest extends BaseWebServiceInput
{
	@XmlElement(nillable = true)
	protected List<PartyCreditPofileObject> partyCreditProfileList;

	/**
	 * 
	 * @return partyCreditProfileList
	 */
	public List<PartyCreditPofileObject> getPartyCreditProfileList()
	{
		return partyCreditProfileList;
	}

	/**
	 * 
	 * @param partyCreditProfileList
	 *           the partyCreditProfileList to set
	 */
	public void setPartyCreditProfileList(final List<PartyCreditPofileObject> partyCreditProfileList)
	{
		this.partyCreditProfileList = partyCreditProfileList;
	}
}
