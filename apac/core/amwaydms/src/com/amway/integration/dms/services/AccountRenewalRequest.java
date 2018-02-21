/**
 * 
 */
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>
 * Java class for accountBalanceRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountRenewalRequest", propOrder =
{ "aboNum", "salesPlanAff", "accountSubTypeCd", "renewalDate", "renewalCd", "legalConsentFlg", "invoiceNumber",
		"renewalWithGroupFlg" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRenewalRequest extends BaseWebServiceInput
{
	protected String aboNum;
	protected String salesPlanAff;
	protected String accountSubTypeCd;
	protected String renewalDate;
	protected String renewalCd;
	protected String legalConsentFlg;
	protected String invoiceNumber;
	protected String renewalWithGroupFlg;

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
	 * @return the accountSubTypeCd
	 */
	public String getAccountSubTypeCd()
	{
		return accountSubTypeCd;
	}

	/**
	 * @param accountSubTypeCd
	 *           the accountSubTypeCd to set
	 */
	public void setAccountSubTypeCd(final String accountSubTypeCd)
	{
		this.accountSubTypeCd = accountSubTypeCd;
	}

	/**
	 * @return the renewalDate
	 */
	public String getRenewalDate()
	{
		return renewalDate;
	}

	/**
	 * @param renewalDate
	 *           the renewalDate to set
	 */
	public void setRenewalDate(final String renewalDate)
	{
		this.renewalDate = renewalDate;
	}

	/**
	 * @return the renewalCd
	 */
	public String getRenewalCd()
	{
		return renewalCd;
	}

	/**
	 * @param renewalCd
	 *           the renewalCd to set
	 */
	public void setRenewalCd(final String renewalCd)
	{
		this.renewalCd = renewalCd;
	}

	/**
	 * @return the legalConsentFlg
	 */
	public String getLegalConsentFlg()
	{
		return legalConsentFlg;
	}

	/**
	 * @param legalConsentFlg
	 *           the legalConsentFlg to set
	 */
	public void setLegalConsentFlg(final String legalConsentFlg)
	{
		this.legalConsentFlg = legalConsentFlg;
	}

	/**
	 * @return the invoiceNumber
	 */
	public String getInvoiceNumber()
	{
		return invoiceNumber;
	}

	/**
	 * @param invoiceNumber
	 *           the invoiceNumber to set
	 */
	public void setInvoiceNumber(final String invoiceNumber)
	{
		this.invoiceNumber = invoiceNumber;
	}

	/**
	 * @return the renewalWithGroupFlg
	 */
	public String getRenewalWithGroupFlg()
	{
		return renewalWithGroupFlg;
	}

	/**
	 * @param renewalWithGroupFlg
	 *           the renewalWithGroupFlg to set
	 */
	public void setRenewalWithGroupFlg(final String renewalWithGroupFlg)
	{
		this.renewalWithGroupFlg = renewalWithGroupFlg;
	}

}
