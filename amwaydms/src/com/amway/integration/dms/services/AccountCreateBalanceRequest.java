package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountCreateBalanceRequest", propOrder =
{ "salesPlanAff", "aboNum", "balanceTypeCd", "currencyCd", "balanceAmount", "txSourceCd", "sourcRefNum", "txTypeCd" })
/**
 * 
 * Dms Request for Account create balance
 *
 */
public class AccountCreateBalanceRequest extends BaseWebServiceInput
{

	protected String salesPlanAff;
	protected String aboNum;
	protected String balanceTypeCd;
	protected String currencyCd;
	protected String balanceAmount;
	protected String txSourceCd;
	protected String sourcRefNum;
	protected String txTypeCd;

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
	 * @return the balanceTypeCd
	 */
	public String getBalanceTypeCd()
	{
		return balanceTypeCd;
	}

	/**
	 * @param balanceTypeCd
	 *           the balanceTypeCd to set
	 */
	public void setBalanceTypeCd(final String balanceTypeCd)
	{
		this.balanceTypeCd = balanceTypeCd;
	}

	/**
	 * @return the currencyCd
	 */
	public String getCurrencyCd()
	{
		return currencyCd;
	}

	/**
	 * @param currencyCd
	 *           the currencyCd to set
	 */
	public void setCurrencyCd(final String currencyCd)
	{
		this.currencyCd = currencyCd;
	}

	/**
	 * @return the balanceAmount
	 */
	public String getBalanceAmount()
	{
		return balanceAmount;
	}

	/**
	 * @param balanceAmount
	 *           the balanceAmount to set
	 */
	public void setBalanceAmount(final String balanceAmount)
	{
		this.balanceAmount = balanceAmount;
	}

	/**
	 * @return the txSourceCd
	 */
	public String getTxSourceCd()
	{
		return txSourceCd;
	}

	/**
	 * @param txSourceCd
	 *           the txSourceCd to set
	 */
	public void setTxSourceCd(final String txSourceCd)
	{
		this.txSourceCd = txSourceCd;
	}

	/**
	 * @return the sourcRefNum
	 */
	public String getSourcRefNum()
	{
		return sourcRefNum;
	}

	/**
	 * @param sourcRefNum
	 *           the sourcRefNum to set
	 */
	public void setSourcRefNum(final String sourcRefNum)
	{
		this.sourcRefNum = sourcRefNum;
	}

	/**
	 * @return the txTypeCd
	 */
	public String getTxTypeCd()
	{
		return txTypeCd;
	}

	/**
	 * @param txTypeCd
	 *           the txTypeCd to set
	 */
	public void setTxTypeCd(final String txTypeCd)
	{
		this.txTypeCd = txTypeCd;
	}


}
