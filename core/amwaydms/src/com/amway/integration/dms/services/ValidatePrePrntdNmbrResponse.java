package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for validatePrePrntdNmbrResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="validatePrePrntdNmbrResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="prePrintedNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceItemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validatePrePrntdNmbrResponse", propOrder =
{ "prePrintedNum", "serviceItemId" })
public class ValidatePrePrntdNmbrResponse extends ReturnInfoService
{

	protected String prePrintedNum;
	protected String serviceItemId;

	/**
	 * Gets the value of the prePrintedNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPrePrintedNum()
	{
		return prePrintedNum;
	}

	/**
	 * Sets the value of the prePrintedNum property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setPrePrintedNum(final String value)
	{
		this.prePrintedNum = value;
	}

	/**
	 * Gets the value of the serviceItemId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getServiceItemId()
	{
		return serviceItemId;
	}

	/**
	 * Sets the value of the serviceItemId property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setServiceItemId(final String value)
	{
		this.serviceItemId = value;
	}

}
