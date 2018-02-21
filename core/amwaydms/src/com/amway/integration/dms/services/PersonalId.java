
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for PersonalId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonalId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="personalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="personalIdTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="countryPersonalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonalId", propOrder = {
    "personalId",
    "personalIdTypeCd",
    "countryPersonalId",
    "effectiveDate",
    "expirationDate"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalId {

    @XmlElement(required = true)
    protected String personalId;
    @XmlElement(required = true)
    protected String personalIdTypeCd;
    protected String countryPersonalId;
    protected String effectiveDate;
    protected String expirationDate;

    /**
     * Gets the value of the personalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonalId() {
        return personalId;
    }

    /**
     * Sets the value of the personalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonalId(String value) {
        this.personalId = value;
    }

    /**
     * Gets the value of the personalIdTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonalIdTypeCd() {
        return personalIdTypeCd;
    }

    /**
     * Sets the value of the personalIdTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonalIdTypeCd(String value) {
        this.personalIdTypeCd = value;
    }

    /**
     * Gets the value of the countryPersonalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryPersonalId() {
        return countryPersonalId;
    }

    /**
     * Sets the value of the countryPersonalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryPersonalId(String value) {
        this.countryPersonalId = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveDate(String value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

}
