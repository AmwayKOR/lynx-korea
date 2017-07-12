
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for preferenceList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="preferenceList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="preferenceListId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceValueCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceValueName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preferenceList", propOrder = {
    "preferenceListId",
    "preferenceValueCd",
    "preferenceValueName"
})
public class PreferenceList {

    @XmlElementRef(name = "preferenceListId", type = JAXBElement.class, required = false)
    protected String preferenceListId;
    @XmlElementRef(name = "preferenceValueCd", type = JAXBElement.class, required = false)
    protected String preferenceValueCd;
    @XmlElementRef(name = "preferenceValueName", type = JAXBElement.class, required = false)
    protected String preferenceValueName;

    /**
     * Gets the value of the preferenceListId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreferenceListId(String value) {
        this.preferenceListId = value;
    }

    /**
     * Gets the value of the preferenceValueCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreferenceValueCd(String value) {
        this.preferenceValueCd = value;
    }

    /**
     * Gets the value of the preferenceValueName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreferenceValueName(String value) {
        this.preferenceValueName = value;
    }

}
