
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for ecommData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ecommData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contactPointName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPointTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ecommData", propOrder = {
    "contactPointName",
    "contactPointTypeCd",
    "ecommAddr",
    "primaryFlag"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcommData {

    protected String contactPointName;
    protected String contactPointTypeCd;
    protected String ecommAddr;
    protected String primaryFlag;

    /**
     * Gets the value of the contactPointName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPointName() {
        return contactPointName;
    }

    /**
     * Sets the value of the contactPointName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPointName(String value) {
        this.contactPointName = value;
    }

    /**
     * Gets the value of the contactPointTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPointTypeCd() {
        return contactPointTypeCd;
    }

    /**
     * Sets the value of the contactPointTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPointTypeCd(String value) {
        this.contactPointTypeCd = value;
    }

    /**
     * Gets the value of the ecommAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setEcommAddr(String value) {
        this.ecommAddr = value;
    }

    /**
     * Gets the value of the primaryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryFlag() {
        return primaryFlag;
    }

    /**
     * Sets the value of the primaryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryFlag(String value) {
        this.primaryFlag = value;
    }

}
