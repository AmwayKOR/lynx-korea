package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * Response for credit profile.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditProfileResponse extends ReturnInfoService
{
	@XmlElement(nillable = true)
	protected List<PartyCreditPofileObject> creditProfileList;


	/**
	 * To get list of party credit profile
	 * 
	 * @return List<PartyCreditPofileObject>
	 */
	public List<PartyCreditPofileObject> getPartyCreditProfileObjList()
	{
		if (creditProfileList == null)
		{
			creditProfileList = new ArrayList<PartyCreditPofileObject>();
		}
		return this.creditProfileList;
	}


}
