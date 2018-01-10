
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for addPartyName complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPartyName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charSetCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latinName" type="{}nameDetailsService" minOccurs="0"/>
 *         &lt;element name="localeName" type="{}nameDetailsService" minOccurs="0"/>
 *         &lt;element name="personNameTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPartyName", propOrder = {
    "charSetCd",
    "languageCd",
    "latinName",
    "localeName",
    "personNameTypeCd",
    "preferredName"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPartyName {

    protected String charSetCd;
    protected String languageCd;
    protected NameDetailsService latinName;
    protected NameDetailsService localeName;
    protected String personNameTypeCd;
    protected String preferredName;

    /**
     * Gets the value of the charSetCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharSetCd() {
        return charSetCd;
    }

    /**
     * Sets the value of the charSetCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharSetCd(String value) {
        this.charSetCd = value;
    }

    /**
     * Gets the value of the languageCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCd() {
        return languageCd;
    }

    /**
     * Sets the value of the languageCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCd(String value) {
        this.languageCd = value;
    }

    /**
     * Gets the value of the latinName property.
     * 
     * @return
     *     possible object is
     *     {@link NameDetailsService }
     *     
     */
    public NameDetailsService getLatinName() {
        return latinName;
    }

    /**
     * Sets the value of the latinName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameDetailsService }
     *     
     */
    public void setLatinName(NameDetailsService value) {
        this.latinName = value;
    }

    /**
     * Gets the value of the localeName property.
     * 
     * @return
     *     possible object is
     *     {@link NameDetailsService }
     *     
     */
    public NameDetailsService getLocaleName() {
        return localeName;
    }

    /**
     * Sets the value of the localeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameDetailsService }
     *     
     */
    public void setLocaleName(NameDetailsService value) {
        this.localeName = value;
    }

    /**
     * Gets the value of the personNameTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonNameTypeCd() {
        return personNameTypeCd;
    }

    /**
     * Sets the value of the personNameTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonNameTypeCd(String value) {
        this.personNameTypeCd = value;
    }

    /**
     * Gets the value of the preferredName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredName() {
        return preferredName;
    }

    /**
     * Sets the value of the preferredName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredName(String value) {
        this.preferredName = value;
    }

}
