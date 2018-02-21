package com.amway.glos.dataobject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LosSponsorValidationResults complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="LosSponsorValidationResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sponAffId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sponIboNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="shouldBeSponAff" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="shouldBeSponIBO" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="losValReturnCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sponsorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="losValReturnMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LosSponsorValidationResults", propOrder = { "sponAffId", "sponIboNo", "shouldBeSponAff", "shouldBeSponIBO",
		"losValReturnCode", "sponsorName", "losValReturnMessage" })
public class LosSponsorValidationResults
{

	protected int sponAffId;
	protected long sponIboNo;
	protected int shouldBeSponAff;
	protected long shouldBeSponIBO;
	protected int losValReturnCode;
	@XmlElement(required = true)
	protected String sponsorName;
	@XmlElement(required = true, nillable = true)
	protected String losValReturnMessage;

	/**
	 * Gets the value of the sponAffId property.
	 */
	public int getSponAffId()
	{
		return sponAffId;
	}

	/**
	 * Sets the value of the sponAffId property.
	 */
	public void setSponAffId(int value)
	{
		this.sponAffId = value;
	}

	/**
	 * Gets the value of the sponIboNo property.
	 */
	public long getSponIboNo()
	{
		return sponIboNo;
	}

	/**
	 * Sets the value of the sponIboNo property.
	 */
	public void setSponIboNo(long value)
	{
		this.sponIboNo = value;
	}

	/**
	 * Gets the value of the shouldBeSponAff property.
	 */
	public int getShouldBeSponAff()
	{
		return shouldBeSponAff;
	}

	/**
	 * Sets the value of the shouldBeSponAff property.
	 */
	public void setShouldBeSponAff(int value)
	{
		this.shouldBeSponAff = value;
	}

	/**
	 * Gets the value of the shouldBeSponIBO property.
	 */
	public long getShouldBeSponIBO()
	{
		return shouldBeSponIBO;
	}

	/**
	 * Sets the value of the shouldBeSponIBO property.
	 */
	public void setShouldBeSponIBO(long value)
	{
		this.shouldBeSponIBO = value;
	}

	/**
	 * Gets the value of the losValReturnCode property.
	 */
	public int getLosValReturnCode()
	{
		return losValReturnCode;
	}

	/**
	 * Sets the value of the losValReturnCode property.
	 */
	public void setLosValReturnCode(int value)
	{
		this.losValReturnCode = value;
	}

	/**
	 * Gets the value of the sponsorName property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getSponsorName()
	{
		return sponsorName;
	}

	/**
	 * Sets the value of the sponsorName property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setSponsorName(String value)
	{
		this.sponsorName = value;
	}

	/**
	 * Gets the value of the losValReturnMessage property.
	 *
	 * @return possible object is
	 * {@link String }
	 */
	public String getLosValReturnMessage()
	{
		return losValReturnMessage;
	}

	/**
	 * Sets the value of the losValReturnMessage property.
	 *
	 * @param value allowed object is
	 *              {@link String }
	 */
	public void setLosValReturnMessage(String value)
	{
		this.losValReturnMessage = value;
	}

}
