
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for PreferenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PreferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="preferenceListId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceValueName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceValueCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreferenceType", propOrder = {
    "preferenceListId",
    "preferenceValueName",
    "preferenceValueCd"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreferenceType {

    protected String preferenceListId;
    protected String preferenceValueName;
    protected String preferenceValueCd;

    /**
     * Gets the value of the preferenceListId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceListId() {
        return preferenceListId;
    }

    /**
     * Sets the value of the preferenceListId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceListId(String value) {
        this.preferenceListId = value;
    }

    /**
     * Gets the value of the preferenceValueName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceValueName() {
        return preferenceValueName;
    }

    /**
     * Sets the value of the preferenceValueName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceValueName(String value) {
        this.preferenceValueName = value;
    }

    /**
     * Gets the value of the preferenceValueCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferenceValueCd() {
        return preferenceValueCd;
    }

    /**
     * Sets the value of the preferenceValueCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferenceValueCd(String value) {
        this.preferenceValueCd = value;
    }

}
