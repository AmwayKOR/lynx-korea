
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for AddressContactUsage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressContactUsage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="primaryFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="contactPointPurposeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="careOf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressContactUsage", propOrder = {
    "primaryFlag",
    "contactPointPurposeCd",
    "careOf"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressContactUsage {

    protected boolean primaryFlag;
    @XmlElement(required = true)
    protected String contactPointPurposeCd;
    protected String careOf;

    /**
     * Gets the value of the primaryFlag property.
     * 
     */
    public boolean isPrimaryFlag() {
        return primaryFlag;
    }

    /**
     * Sets the value of the primaryFlag property.
     * 
     */
    public void setPrimaryFlag(boolean value) {
        this.primaryFlag = value;
    }

    /**
     * Gets the value of the contactPointPurposeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPointPurposeCd() {
        return contactPointPurposeCd;
    }

    /**
     * Sets the value of the contactPointPurposeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPointPurposeCd(String value) {
        this.contactPointPurposeCd = value;
    }

    /**
     * Gets the value of the careOf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCareOf() {
        return careOf;
    }

    /**
     * Sets the value of the careOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCareOf(String value) {
        this.careOf = value;
    }

}
