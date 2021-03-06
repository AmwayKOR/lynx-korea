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
 * Created by aiu0256 on 5/22/15.
 * <p/>
 * <p/>
 * <p/>
 * Java class for lineOfSponsorshipBusinessDetail complex type.
 * <p/>
 * <p/>
 * The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="lineOfSponsorshipBusinessDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Aff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ABONo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="HighestAward" type="{}aboLosAward" minOccurs="0"/>
 *         &lt;element name="BonusPeriodStats" type="{}aboLosBonusPeriodStatistic" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SponsoringStats" type="{}aboLosBonusPeriodSponsoringStatistic" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RetrieveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lineOfSponsorshipBusinessDetail", propOrder = { "aff", "aboNo", "currentAward", "highestAward",
		"bonusPeriodStats", "sponsoringStats", "extended", "retrieveDate", "qualifications" })
public class LineOfSponsorshipBusinessDetail
{

	@XmlElement(name = "Aff")
	protected String aff;
	@XmlElement(name = "ABONo")
	protected Long aboNo;
	@XmlElement(name = "HighestAward")
	protected AboLosAward highestAward;
	@XmlElement(name = "CurrentAward")
	protected AboLosAward currentAward;
	@XmlElement(name = "BonusPeriodStats")
	protected List<AboLosBonusPeriodStatistic> bonusPeriodStats;
	@XmlElement(name = "SponsoringStats")
	protected List<AboLosBonusPeriodSponsoringStatistic> sponsoringStats;
	@XmlElement(name = "Extended")
	protected AboLosExtended extended;
	@XmlElement(name = "RetrieveDate")
	protected String retrieveDate;
	@XmlElement(name = "Qualifications")
	protected List<AboLosQualifications> qualifications;

	/**
	 * @return the qualifications
	 */
	public List<AboLosQualifications> getQualifications()
	{
		return qualifications;
	}

	/**
	 * @param qualifications the qualifications to set
	 */
	public void setQualifications(final List<AboLosQualifications> qualifications)
	{
		this.qualifications = qualifications;
	}

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
	 * Gets the value of the highestAward property.
	 *
	 * @return possible object is {@link AboLosAward }
	 */
	public AboLosAward getHighestAward()
	{
		return highestAward;
	}

	/**
	 * Sets the value of the highestAward property.
	 *
	 * @param value allowed object is {@link AboLosAward }
	 */
	public void setHighestAward(final AboLosAward value)
	{
		this.highestAward = value;
	}

	/**
	 * Gets the value of the bonusPeriodStats property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
	 * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
	 * the bonusPeriodStats property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <p/>
	 * <pre>
	 * getBonusPeriodStats().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list {@link AboLosBonusPeriodStatistic }
	 */
	public List<AboLosBonusPeriodStatistic> getBonusPeriodStats()
	{
		if (bonusPeriodStats == null)
		{
			bonusPeriodStats = new ArrayList<AboLosBonusPeriodStatistic>();
		}
		return this.bonusPeriodStats;
	}

	/**
	 * Gets the value of the sponsoringStats property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
	 * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
	 * the sponsoringStats property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <p/>
	 * <pre>
	 * getSponsoringStats().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list {@link AboLosBonusPeriodSponsoringStatistic }
	 */
	public List<AboLosBonusPeriodSponsoringStatistic> getSponsoringStats()
	{
		if (sponsoringStats == null)
		{
			sponsoringStats = new ArrayList<AboLosBonusPeriodSponsoringStatistic>();
		}
		return this.sponsoringStats;
	}

	/**
	 * Gets the value of the retrieveDate property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getRetrieveDate()
	{
		return retrieveDate;
	}

	/**
	 * Sets the value of the retrieveDate property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setRetrieveDate(final String value)
	{
		this.retrieveDate = value;
	}

	/**
	 * @return extended
	 */
	public AboLosExtended getExtended()
	{
		return extended;
	}

	/**
	 * @param extended the extended to set
	 */
	public void setExtended(final AboLosExtended extended)
	{
		this.extended = extended;
	}

	/**
	 * @return the currentAward
	 */
	public AboLosAward getCurrentAward()
	{
		return currentAward;
	}

	/**
	 * @param currentAward the currentAward to set
	 */
	public void setCurrentAward(final AboLosAward currentAward)
	{
		this.currentAward = currentAward;
	}


}
