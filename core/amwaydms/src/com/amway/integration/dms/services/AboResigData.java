
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for aboResigData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aboResigData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reasonCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resignationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aboResigData", propOrder = {
    "aboNum",
    "cntryCd",
    "reasonCd",
    "resignationDate"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AboResigData {

    protected String aboNum;
    protected String cntryCd;
    protected String reasonCd;
    protected String resignationDate;

    /**
     * Gets the value of the aboNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAboNum() {
        return aboNum;
    }

    /**
     * Sets the value of the aboNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAboNum(String value) {
        this.aboNum = value;
    }

    /**
     * Gets the value of the cntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCntryCd() {
        return cntryCd;
    }

    /**
     * Sets the value of the cntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCntryCd(String value) {
        this.cntryCd = value;
    }

    /**
     * Gets the value of the reasonCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonCd() {
        return reasonCd;
    }

    /**
     * Sets the value of the reasonCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonCd(String value) {
        this.reasonCd = value;
    }

    /**
     * Gets the value of the resignationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResignationDate() {
        return resignationDate;
    }

    /**
     * Sets the value of the resignationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResignationDate(String value) {
        this.resignationDate = value;
    }

}
