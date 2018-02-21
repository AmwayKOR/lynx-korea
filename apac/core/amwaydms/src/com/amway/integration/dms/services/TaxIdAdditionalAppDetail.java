package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>
 * Java class for taxIdDetail complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taxIdDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="countryTaxCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxTypeExpireDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taxIdAdditionalAppDetail", propOrder =
{ "countryTaxCd", "taxId", "taxTypeCd", "taxTypeExpireDate" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxIdAdditionalAppDetail
{

	protected String countryTaxCd;
	protected String taxId;
	protected String taxTypeCd;
	protected String taxTypeExpireDate;

	/**
	 * Gets the value of the countryTaxCd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCountryTaxCd()
	{
		return countryTaxCd;
	}

	/**
	 * Sets the value of the countryTaxCd property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setCountryTaxCd(final String value)
	{
		this.countryTaxCd = value;
	}

	/**
	 * Gets the value of the taxId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaxId()
	{
		return taxId;
	}

	/**
	 * Sets the value of the taxId property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setTaxId(final String value)
	{
		this.taxId = value;
	}

	/**
	 * Gets the value of the taxTypeCd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaxTypeCd()
	{
		return taxTypeCd;
	}

	/**
	 * Sets the value of the taxTypeCd property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setTaxTypeCd(final String value)
	{
		this.taxTypeCd = value;
	}

	/**
	 * Gets the value of the taxTypeExpireDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTaxTypeExpireDate()
	{
		return taxTypeExpireDate;
	}

	/**
	 * Sets the value of the taxTypeExpireDate property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setTaxTypeExpireDate(final String value)
	{
		this.taxTypeExpireDate = value;
	}

}
