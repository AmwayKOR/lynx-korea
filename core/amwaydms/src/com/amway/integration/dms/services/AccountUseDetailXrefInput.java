
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for accountUseDetailXrefInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountUseDetailXrefInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acctUseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="useFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountUseDetailXrefInput", propOrder = {
    "acctUseCode",
    "useFlag"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUseDetailXrefInput {

    @XmlElementRef(name = "acctUseCode", type = JAXBElement.class, required = false)
    protected String acctUseCode;
    @XmlElementRef(name = "useFlag", type = JAXBElement.class, required = false)
    protected String useFlag;

    /**
     * Gets the value of the acctUseCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getAcctUseCode() {
        return acctUseCode;
    }

    /**
     * Sets the value of the acctUseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAcctUseCode(String value) {
        this.acctUseCode = value;
    }

    /**
     * Gets the value of the useFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getUseFlag() {
        return useFlag;
    }

    /**
     * Sets the value of the useFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUseFlag(String value) {
        this.useFlag = value;
    }

}
