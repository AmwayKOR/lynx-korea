package com.amway.glos.dataobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LosSponsorValidation complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="LosSponsorValidation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="affiliate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="busNatr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sponsorNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sponTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LosSponsorValidation", propOrder = { "affiliate", "country", "busNatr", "sponsorNo", "sponTypeCd" })
public class LosSponsorValidation
{

	protected int affiliate;
	@XmlElement(required = true, nillable = true)
	protected String country;
	@XmlElement(required = true, nillable = true)
	protected String busNatr;
	protected long sponsorNo;
	@XmlElement(required = true, nillable = true)
	protected String sponTypeCd;

	/**
	 * Gets the value of the affiliate property.
	 */
	public int getAffiliate()
	{
		return affiliate;
	}

	/**
	 * Sets the value of the affiliate property.
	 */
	public void setAffiliate(int value)
	{
		this.affiliate = value;
	}

	/**
	 * Gets the value of the country property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * Sets the value of the country property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setCountry(String value)
	{
		this.country = value;
	}

	/**
	 * Gets the value of the busNatr property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getBusNatr()
	{
		return busNatr;
	}

	/**
	 * Sets the value of the busNatr property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setBusNatr(String value)
	{
		this.busNatr = value;
	}

	/**
	 * Gets the value of the sponsorNo property.
	 */
	public long getSponsorNo()
	{
		return sponsorNo;
	}

	/**
	 * Sets the value of the sponsorNo property.
	 */
	public void setSponsorNo(long value)
	{
		this.sponsorNo = value;
	}

	/**
	 * Gets the value of the sponTypeCd property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getSponTypeCd()
	{
		return sponTypeCd;
	}

	/**
	 * Sets the value of the sponTypeCd property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setSponTypeCd(String value)
	{
		this.sponTypeCd = value;
	}

}
