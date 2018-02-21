package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>
 * Java class for getPartyPrefrenceListRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPartyPrefrenceListRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPartyPrefrenceListRequest", propOrder =
{ "aboNum", "partyId", "preferenceId", "salesPlanAff" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPartyPrefrenceListRequest extends BaseWebServiceInput
{
	protected String aboNum;
	protected String partyId;
	protected String preferenceId;
	protected String salesPlanAff;

	public String getAboNum()
	{
		return aboNum;
	}

	public void setAboNum(final String aboNum)
	{
		this.aboNum = aboNum;
	}


	/**
	 * Gets the value of the partyId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPartyId()
	{
		return partyId;
	}

	/**
	 * Sets the value of the partyId property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setPartyId(final String value)
	{
		this.partyId = value;
	}

	/**
	 * Gets the value of the preferenceId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPreferenceId()
	{
		return preferenceId;
	}

	/**
	 * Sets the value of the preferenceId property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setPreferenceId(final String value)
	{
		this.preferenceId = value;
	}

	/**
	 * Gets the value of the salesPlanAff property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSalesPlanAff()
	{
		return salesPlanAff;
	}

	/**
	 * Sets the value of the salesPlanAff property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setSalesPlanAff(final String value)
	{
		this.salesPlanAff = value;
	}

}
