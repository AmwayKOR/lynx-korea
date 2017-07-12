package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for getPrefrenceListRequest complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPrefrenceListRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="preferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPrefrenceListRequest", propOrder =
{ "salesPlanAff", "preferenceId" })
public class GetPrefrenceListRequest extends BaseWebServiceInput
{
	protected String salesPlanAff;
	protected String preferenceId;

	public String getSalesPlanAff()
	{
		return salesPlanAff;
	}

	public void setSalesPlanAff(final String salesPlanAff)
	{
		this.salesPlanAff = salesPlanAff;
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

}
