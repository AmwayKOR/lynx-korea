package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


/**
 * 
 * Response for credit profile.
 * 
 */
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
