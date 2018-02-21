
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for partyEcommData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyEcommData">
 *   &lt;complexContent>
 *     &lt;extension base="{}contact">
 *       &lt;sequence>
 *         &lt;element name="allowForLogIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyEcommData", propOrder = {
    "allowForLogIn",
    "ecommAddr",
    "ecommName",
    "ecommTypeCd",
    "ecommTypeName",
    "statusCd"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyEcommData
    extends Contact
{

    @XmlElementRef(name = "allowForLogIn", type = JAXBElement.class, required = false)
    protected String allowForLogIn;
    @XmlElementRef(name = "ecommAddr", type = JAXBElement.class, required = false)
    protected String ecommAddr;
    @XmlElementRef(name = "ecommName", type = JAXBElement.class, required = false)
    protected String ecommName;
    @XmlElementRef(name = "ecommTypeCd", type = JAXBElement.class, required = false)
    protected String ecommTypeCd;
    @XmlElementRef(name = "ecommTypeName", type = JAXBElement.class, required = false)
    protected String ecommTypeName;
    @XmlElementRef(name = "statusCd", type = JAXBElement.class, required = false)
    protected String statusCd;

    /**
     * Gets the value of the allowForLogIn property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getAllowForLogIn() {
        return allowForLogIn;
    }

    /**
     * Sets the value of the allowForLogIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAllowForLogIn(String value) {
        this.allowForLogIn = value;
    }

    /**
     * Gets the value of the ecommAddr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getEcommAddr() {
        return ecommAddr;
    }

    /**
     * Sets the value of the ecommAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEcommAddr(String value) {
        this.ecommAddr = value;
    }

    /**
     * Gets the value of the ecommName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getEcommName() {
        return ecommName;
    }

    /**
     * Sets the value of the ecommName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEcommName(String value) {
        this.ecommName = value;
    }

    /**
     * Gets the value of the ecommTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getEcommTypeCd() {
        return ecommTypeCd;
    }

    /**
     * Sets the value of the ecommTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEcommTypeCd(String value) {
        this.ecommTypeCd = value;
    }

    /**
     * Gets the value of the ecommTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getEcommTypeName() {
        return ecommTypeName;
    }

    /**
     * Sets the value of the ecommTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEcommTypeName(String value) {
        this.ecommTypeName = value;
    }

    /**
     * Gets the value of the statusCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getStatusCd() {
        return statusCd;
    }

    /**
     * Sets the value of the statusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatusCd(String value) {
        this.statusCd = value;
    }

}
