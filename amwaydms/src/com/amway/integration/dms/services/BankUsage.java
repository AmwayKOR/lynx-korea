
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for BankUsage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BankUsage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="useFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acctUseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankUsage", propOrder = {
    "useFlag",
    "acctUseCode"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankUsage {

    protected String useFlag;
    protected String acctUseCode;

    /**
     * Gets the value of the useFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setUseFlag(String value) {
        this.useFlag = value;
    }

    /**
     * Gets the value of the acctUseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setAcctUseCode(String value) {
        this.acctUseCode = value;
    }

}
