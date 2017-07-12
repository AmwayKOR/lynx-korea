/**
 *
 */
package com.amway.glos.dataobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * POJO class
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LosIntlBusSponsorValidation", propOrder = { "affiliate", "iboNumber" })
public class LosIntlBusSponsorValidation
{
	protected int affiliate;
	protected long iboNumber;

	/**
	 * @return affiliate
	 */
	public int getAffiliate()
	{
		return affiliate;
	}

	/**
	 * @param affiliate the affiliate to set
	 */
	public void setAffiliate(final int affiliate)
	{
		this.affiliate = affiliate;
	}

	/**
	 * @return iboNumber
	 */
	public long getIboNumber()
	{
		return iboNumber;
	}

	/**
	 * @param iboNumber the iboNumber to set
	 */
	public void setIboNumber(final long iboNumber)
	{
		this.iboNumber = iboNumber;
	}
}
