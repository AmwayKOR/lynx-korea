package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPartyTaxIdDetailsResponse", propOrder =
{ "taxIdList" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPartyTaxIdDetailsResponse extends ReturnInfoService
{
	@XmlElement(nillable = true)
	protected List<TaxDetailsOutput> taxIdList;

	public List<TaxDetailsOutput> getPartyTaxDetailsList()
	{
		if (taxIdList == null)
		{
			taxIdList = new ArrayList<TaxDetailsOutput>();
		}
		return this.taxIdList;
	}

	public void setPartyTaxDetailsList(final List<TaxDetailsOutput> taxIdList)
	{
		this.taxIdList = taxIdList;
	}
}
