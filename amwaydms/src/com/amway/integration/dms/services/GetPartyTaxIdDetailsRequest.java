package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTaxIdRequest", propOrder =
{ "salesPlanAff", "aboNum", "partyId", "taxTypeCd" })
public class GetPartyTaxIdDetailsRequest extends BaseWebServiceInput
{
	protected String aboNum;
	protected String taxTypeCd;
	protected String partyId;
	protected String salesPlanAff;

	public String getSalesPlanAff()
	{
		return salesPlanAff;
	}

	public void setSalesPlanAff(final String salesPlanAff)
	{
		this.salesPlanAff = salesPlanAff;
	}

	public String getAboNum()
	{
		return aboNum;
	}

	public void setAboNum(final String aboNum)
	{
		this.aboNum = aboNum;
	}

	public String getTaxTypeCd()
	{
		return taxTypeCd;
	}

	public void setTaxTypeCd(final String taxTypeCd)
	{
		this.taxTypeCd = taxTypeCd;
	}

	public String getPartyId()
	{
		return partyId;
	}

	public void setPartyId(final String partyId)
	{
		this.partyId = partyId;
	}
}
