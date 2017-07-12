
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for partyPersonName complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyPersonName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="charSetCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latinName" type="{}nameDetails" minOccurs="0"/>
 *         &lt;element name="localeName" type="{}nameDetails" minOccurs="0"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "partyPersonName", propOrder = {
    "charSetCd",
    "languageCd",
    "latinName",
    "localeName",
    "partyId",
    "personNameTypeCd",
    "preferredName"
})
public class PartyPersonName {

    @XmlElementRef(name = "charSetCd", type = JAXBElement.class, required = false)
    protected String charSetCd;
    @XmlElementRef(name = "languageCd", type = JAXBElement.class, required = false)
    protected String languageCd;
    @XmlElementRef(name = "latinName", type = JAXBElement.class, required = false)
    protected NameDetails latinName;
    @XmlElementRef(name = "localeName", type = JAXBElement.class, required = false)
    protected NameDetails localeName;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long partyId;
    @XmlElementRef(name = "personNameTypeCd", type = JAXBElement.class, required = false)
    protected String personNameTypeCd;
    @XmlElementRef(name = "preferredName", type = JAXBElement.class, required = false)
    protected String preferredName;

    /**
     * Gets the value of the charSetCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link NameDetails }{@code >}
     *     
     */
    public NameDetails getLatinName() {
        return latinName;
    }

    /**
     * Sets the value of the latinName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link NameDetails }{@code >}
     *     
     */
    public void setLatinName(NameDetails value) {
        this.latinName = value;
    }

    /**
     * Gets the value of the localeName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link NameDetails }{@code >}
     *     
     */
    public NameDetails getLocaleName() {
        return localeName;
    }

    /**
     * Sets the value of the localeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link NameDetails }{@code >}
     *     
     */
    public void setLocaleName(NameDetails value) {
        this.localeName = value;
    }

    /**
     * Gets the value of the partyId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPartyId() {
        return partyId;
    }

    /**
     * Sets the value of the partyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPartyId(Long value) {
        this.partyId = value;
    }

    /**
     * Gets the value of the personNameTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreferredName(String value) {
        this.preferredName = value;
    }

}
