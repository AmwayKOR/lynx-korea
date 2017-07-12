package com.alticor.ebsui.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.amway.glos.dataobject.LosSponsorValidationResults;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="validateSponsorReturn" type="{http://dataobject.glos.amway.com}LosSponsorValidationResults"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "validateSponsorReturn" })
@XmlRootElement(name = "validateSponsorResponse")
public class ValidateSponsorResponse
{

	@XmlElement(required = true, nillable = true)
	protected LosSponsorValidationResults validateSponsorReturn;

	/**
	 * Gets the value of the validateSponsorReturn property.
	 *
	 * @return possible object is
	 * {@link LosSponsorValidationResults }
	 */
	public LosSponsorValidationResults getValidateSponsorReturn()
	{
		return validateSponsorReturn;
	}

	/**
	 * Sets the value of the validateSponsorReturn property.
	 *
	 * @param value allowed object is
	 *              {@link LosSponsorValidationResults }
	 */
	public void setValidateSponsorReturn(LosSponsorValidationResults value)
	{
		this.validateSponsorReturn = value;
	}

}
