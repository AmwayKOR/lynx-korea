package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>
 * Java class for updatePartyPreferenceRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePartyPreferenceRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="isTrueFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceListId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceListValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "updatePartyPreferenceRequest", propOrder =
{ "isTrueFlg", "partyId", "preferenceId", "preferenceListId", "preferenceListValue", "preferenceNote", "salesPlanAff", "aboNum" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePartyPreferenceRequest extends BaseWebServiceInput
{
	protected String isTrueFlg;
	protected String partyId;
	protected String preferenceId;
	protected String preferenceListId;
	protected String preferenceListValue;
	protected String preferenceNote;
	protected String salesPlanAff;
	protected String aboNum;

	public String getAboNum()
	{
		return aboNum;
	}

	public void setAboNum(final String aboNum)
	{
		this.aboNum = aboNum;
	}

	/**
	 * Gets the value of the isTrueFlg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsTrueFlg()
	{
		return isTrueFlg;
	}

	/**
	 * Sets the value of the isTrueFlg property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setIsTrueFlg(final String value)
	{
		this.isTrueFlg = value;
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
	 * Gets the value of the preferenceListId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPreferenceListId()
	{
		return preferenceListId;
	}

	/**
	 * Sets the value of the preferenceListId property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setPreferenceListId(final String value)
	{
		this.preferenceListId = value;
	}

	/**
	 * Gets the value of the preferenceListValue property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPreferenceListValue()
	{
		return preferenceListValue;
	}

	/**
	 * Sets the value of the preferenceListValue property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setPreferenceListValue(final String value)
	{
		this.preferenceListValue = value;
	}

	/**
	 * Gets the value of the preferenceNote property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPreferenceNote()
	{
		return preferenceNote;
	}

	/**
	 * Sets the value of the preferenceNote property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setPreferenceNote(final String value)
	{
		this.preferenceNote = value;
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
