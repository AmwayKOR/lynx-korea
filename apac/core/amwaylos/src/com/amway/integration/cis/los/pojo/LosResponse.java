//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.25 at 07:43:28 PM IST 
//


package com.amway.integration.cis.los.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Created by aiu0256 on 5/21/15.
 * <p/>
 * <p/>
 * <p/>
 * Java class for losResponse complex type.
 * <p/>
 * <p/>
 * The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="losResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LOS" type="{}lineOfSponsorship" minOccurs="0"/>
 *         &lt;element name="returnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RetrieveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "losResponse", propOrder = { "los", "returnCode", "returnMessage", "retrieveDate" })
public class LosResponse
{

	@XmlElement(name = "LOS")
	protected LineOfSponsorship los;
	protected String returnCode;
	protected String returnMessage;
	@XmlElement(name = "RetrieveDate")
	protected String retrieveDate;

	/**
	 * Gets the value of the los property.
	 *
	 * @return possible object is {@link LineOfSponsorship }
	 */
	public LineOfSponsorship getLOS()
	{
		return los;
	}

	/**
	 * Sets the value of the los property.
	 *
	 * @param value allowed object is {@link LineOfSponsorship }
	 */
	public void setLOS(final LineOfSponsorship value)
	{
		this.los = value;
	}

	/**
	 * Gets the value of the returnCode property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getReturnCode()
	{
		return returnCode;
	}

	/**
	 * Sets the value of the returnCode property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setReturnCode(final String value)
	{
		this.returnCode = value;
	}

	/**
	 * Gets the value of the returnMessage property.
	 *
	 * @return possible object is {@link String }
	 */
	public String getReturnMessage()
	{
		return returnMessage;
	}

	/**
	 * Sets the value of the returnMessage property.
	 *
	 * @param value allowed object is {@link String }
	 */
	public void setReturnMessage(final String value)
	{
		this.returnMessage = value;
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

}