//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.25 at 07:43:28 PM IST 
//


package com.amway.integration.cis.los.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Created by aiu0256 on 5/21/15.
 * <p/>
 * <p/>
 * <p/>
 * Java class for lineOfSponsorship complex type.
 * <p/>
 * <p/>
 * The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="lineOfSponsorship">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Aff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ABONo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EntryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrentAwardRank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HighestAwardRank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GroupABOCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="HasDownlines" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Downlines" type="{}lineOfSponsorship" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lineOfSponsorship", propOrder = { "aff", "aboNo", "name", "country", "entryDate", "currentAwardRank",
		"highestAwardRank", "status", "groupABOCount", "hasDownlines", "privacyFlag", "downlines" })
public class LineOfSponsorship
{

	@XmlElement(name = "Aff")
	protected String aff;
	@XmlElement(name = "ABONo")
	protected Long aboNo;
	@XmlElement(name = "Name")
	protected String name;
	@XmlElement(name = "Country")
	protected String country;
	@XmlElement(name = "EntryDate")
	protected String entryDate;
	@XmlElement(name = "CurrentAwardRank")
	protected String currentAwardRank;
	@XmlElement(name = "HighestAwardRank")
	protected String highestAwardRank;
	@XmlElement(name = "Status")
	protected String status;
	@XmlElement(name = "GroupABOCount")
	protected Integer groupABOCount;
	@XmlElement(name = "HasDownlines")
	protected Boolean hasDownlines;
	@XmlElement(name = "PrivacyFlag")
	protected Boolean privacyFlag;
	@XmlElement(name = "Downlines")
	protected List<LineOfSponsorship> downlines;

	/**
	 * Gets the value of the aff property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getAff()
	{
		return aff;
	}

	/**
	 * Sets the value of the aff property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setAff(final String value)
	{
		this.aff = value;
	}

	/**
	 * Gets the value of the aboNo property.
	 *
	 * @return possible object is {@link Long }
	 */
	public Long getABONo()
	{
		return aboNo;
	}

	/**
	 * Sets the value of the aboNo property.
	 *
	 * @param value allowed object is {@link Long }
	 */
	public void setABONo(final Long value)
	{
		this.aboNo = value;
	}

	/**
	 * Gets the value of the name property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Sets the value of the name property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setName(final String value)
	{
		this.name = value;
	}

	/**
	 * Gets the value of the country property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getCountry()
	{
		return country;
	}

	/**
	 * Sets the value of the country property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setCountry(final String value)
	{
		this.country = value;
	}

	/**
	 * Gets the value of the entryDate property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets the value of the entryDate property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setEntryDate(final String value)
	{
		this.entryDate = value;
	}

	/**
	 * Gets the value of the currentAwardRank property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getCurrentAwardRank()
	{
		return currentAwardRank;
	}

	/**
	 * Sets the value of the currentAwardRank property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setCurrentAwardRank(final String value)
	{
		this.currentAwardRank = value;
	}

	/**
	 * Gets the value of the highestAwardRank property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getHighestAwardRank()
	{
		return highestAwardRank;
	}

	/**
	 * Sets the value of the highestAwardRank property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setHighestAwardRank(final String value)
	{
		this.highestAwardRank = value;
	}

	/**
	 * Gets the value of the status property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * Sets the value of the status property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setStatus(final String value)
	{
		this.status = value;
	}

	/**
	 * Gets the value of the groupABOCount property.
	 *
	 * @return possible object is {@link Integer }
	 */
	public Integer getGroupABOCount()
	{
		return groupABOCount;
	}

	/**
	 * Sets the value of the groupABOCount property.
	 *
	 * @param value allowed object is {@link Integer }
	 */
	public void setGroupABOCount(final Integer value)
	{
		this.groupABOCount = value;
	}

	/**
	 * Gets the value of the hasDownlines property.
	 *
	 * @return possible object is {@link Boolean }
	 */
	public Boolean isHasDownlines()
	{
		return hasDownlines;
	}

	/**
	 * Sets the value of the hasDownlines property.
	 *
	 * @param value allowed object is {@link Boolean }
	 */
	public void setHasDownlines(final Boolean value)
	{
		this.hasDownlines = value;
	}


	public Boolean getPrivacyFlag()
	{
		return privacyFlag;
	}

	public void setPrivacyFlag(Boolean privacyFlag)
	{
		this.privacyFlag = privacyFlag;
	}

	/**
	 * Gets the value of the downlines property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
	 * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
	 * the downlines property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <p/>
	 * <pre>
	 * getDownlines().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list {@link LineOfSponsorship }
	 */
	public List<LineOfSponsorship> getDownlines()
	{
		if (downlines == null)
		{
			downlines = new ArrayList<LineOfSponsorship>();
		}
		return this.downlines;
	}


}