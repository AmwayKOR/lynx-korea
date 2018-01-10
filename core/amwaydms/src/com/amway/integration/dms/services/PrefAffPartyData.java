
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for prefAffPartyData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prefAffPartyData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aboNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isTrueFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferenceList" type="{}preferenceList" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="preferenceNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prefAffPartyData", propOrder = {
    "aboNo",
    "isTrueFlg",
    "partyId",
    "preferenceId",
    "preferenceList",
    "preferenceNote",
    "salesPlanAff"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrefAffPartyData {

    @XmlElementRef(name = "aboNo", type = JAXBElement.class, required = false)
    protected String aboNo;
    @XmlElementRef(name = "isTrueFlg", type = JAXBElement.class, required = false)
    protected String isTrueFlg;
    @XmlElementRef(name = "partyId", type = JAXBElement.class, required = false)
    protected String partyId;
    @XmlElementRef(name = "preferenceId", type = JAXBElement.class, required = false)
    protected String preferenceId;
    @XmlElement(nillable = true)
    protected List<PreferenceList> preferenceList;
    @XmlElementRef(name = "preferenceNote", type = JAXBElement.class, required = false)
    protected String preferenceNote;
    @XmlElementRef(name = "salesPlanAff", type = JAXBElement.class, required = false)
    protected String salesPlanAff;

    /**
     * Gets the value of the aboNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getAboNo() {
        return aboNo;
    }

    /**
     * Sets the value of the aboNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAboNo(String value) {
        this.aboNo = value;
    }

    /**
     * Gets the value of the isTrueFlg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getIsTrueFlg() {
        return isTrueFlg;
    }

    /**
     * Sets the value of the isTrueFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsTrueFlg(String value) {
        this.isTrueFlg = value;
    }

    /**
     * Gets the value of the partyId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * Sets the value of the partyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPartyId(String value) {
        this.partyId = value;
    }

    /**
     * Gets the value of the preferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getPreferenceId() {
        return preferenceId;
    }

    /**
     * Sets the value of the preferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreferenceId(String value) {
        this.preferenceId = value;
    }

    /**
     * Gets the value of the preferenceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preferenceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreferenceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PreferenceList }
     * 
     * 
     */
    public List<PreferenceList> getPreferenceList() {
        if (preferenceList == null) {
            preferenceList = new ArrayList<PreferenceList>();
        }
        return this.preferenceList;
    }

    /**
     * Gets the value of the preferenceNote property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getPreferenceNote() {
        return preferenceNote;
    }

    /**
     * Sets the value of the preferenceNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPreferenceNote(String value) {
        this.preferenceNote = value;
    }

    /**
     * Gets the value of the salesPlanAff property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSalesPlanAff() {
        return salesPlanAff;
    }

    /**
     * Sets the value of the salesPlanAff property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSalesPlanAff(String value) {
        this.salesPlanAff = value;
    }

}
