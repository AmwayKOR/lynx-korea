/**
 *
 */
package com.amway.integration.cis.los.pojo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonAnySetter;


/**
 * @Transaction Pojo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transaction", propOrder = { "referenceId", "aboId", "bonusPeriod", "pointValue", "businessVolume",
		"isoCurrencyCode", "amount", "profit", "channel", "warehouseCode", "referenceInvoice" })
public class Transaction
{
	protected String referenceId;
	protected String aboId;
	protected String bonusPeriod;
	protected double pointValue;
	protected double businessVolume;
	protected String isoCurrencyCode;
	protected double amount;
	protected double profit;
	protected String channel;
	protected String warehouseCode;
	protected String referenceInvoice;

	/**
	 * @return referenceId
	 */
	public String getReferenceId()
	{
		return this.referenceId;
	}

	/**
	 * @param value the value to set
	 */
	public void setReferenceId(final String value)
	{
		this.referenceId = value;
	}

	/**
	 * @return aboId
	 */
	public String getAboId()
	{
		return this.aboId;
	}

	/**
	 * @param value the value to set
	 */
	public void setAboId(final String value)
	{
		this.aboId = value;
	}

	/**
	 * @return bonusPeriod
	 */
	public String getBonusPeriod()
	{
		return this.bonusPeriod;
	}

	/**
	 * @param value the value to set
	 */
	public void setBonusPeriod(final String value)
	{
		this.bonusPeriod = value;
	}

	/**
	 * @return pointValue
	 */
	public double getPointValue()
	{
		return this.pointValue;
	}

	/**
	 * @param value the value to set
	 */
	public void setPointValue(final double value)
	{
		this.pointValue = value;
	}

	/**
	 * @return businessVolume
	 */
	public double getBusinessVolume()
	{
		return this.businessVolume;
	}

	/**
	 * @param value the value to set
	 */
	@JsonAnySetter
	public void setBusinessVolume(final double value)
	{
		this.businessVolume = value;
	}

	/**
	 * @return isoCurrencyCode
	 */
	public String getIsoCurrencyCode()
	{
		return this.isoCurrencyCode;
	}

	/**
	 * @param value
	 */
	public void setIsoCurrencyCode(final String value)
	{
		this.isoCurrencyCode = value;
	}

	/**
	 * @return amount
	 */
	public double getAmount()
	{
		return this.amount;
	}

	/**
	 * @param value the value to set
	 */
	public void setAmount(final double value)
	{
		this.amount = value;
	}

	/**
	 * @return profit
	 */
	public double getProfit()
	{
		return this.profit;
	}

	/**
	 * @param value the value to set
	 */
	public void setProfit(final double value)
	{
		this.profit = value;
	}

	/**
	 * @return channel
	 */
	public String getChannel()
	{
		return this.channel;
	}

	/**
	 * @param value the value to set
	 */
	public void setChannel(final String value)
	{
		this.channel = value;
	}

	/**
	 * @return warehouseCode
	 */
	public String getWarehouseCode()
	{
		return this.warehouseCode;
	}

	/**
	 * @param value
	 */
	public void setWarehouseCode(final String value)
	{
		this.warehouseCode = value;
	}

	/**
	 * @return referenceInvoice
	 */
	public String getReferenceInvoice()
	{
		return this.referenceInvoice;
	}

	/**
	 * @param value
	 */
	public void setReferenceInvoice(final String value)
	{
		this.referenceInvoice = value;
	}
}
