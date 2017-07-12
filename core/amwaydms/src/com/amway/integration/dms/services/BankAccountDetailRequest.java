package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * Dms request for bank account details.
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bankAccountDetailRequest", propOrder =
{ "salesPlanAff", "aboNum", "partyId", "bankAcctUseCode" })
public class BankAccountDetailRequest extends BaseWebServiceInput
{

	protected String salesPlanAff;
	protected String aboNum;
	protected String partyId;
	protected String bankAcctUseCode;

	/**
	 * 
	 * @return salesPlanAff
	 */
	public String getSalesPlanAff()
	{
		return salesPlanAff;
	}

	/**
	 * 
	 * @param salesPlanAff
	 *           the salesPlanAff to set
	 */
	public void setSalesPlanAff(final String salesPlanAff)
	{
		this.salesPlanAff = salesPlanAff;
	}

	/**
	 * 
	 * @return aboNum
	 */
	public String getAboNum()
	{
		return aboNum;
	}

	/**
	 * 
	 * @param aboNum
	 *           the aboNum to set
	 */
	public void setAboNum(final String aboNum)
	{
		this.aboNum = aboNum;
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

	/**
	 * 
	 * @return bankAcctUseCode
	 */
	public String getBankAcctUseCode()
	{
		return bankAcctUseCode;
	}

	/**
	 * 
	 * @param bankAcctUseCode
	 *           the bankAcctUseCode to set
	 */
	public void setBankAcctUseCode(final String bankAcctUseCode)
	{
		this.bankAcctUseCode = bankAcctUseCode;
	}


}
