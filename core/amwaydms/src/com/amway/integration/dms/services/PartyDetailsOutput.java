
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
 * <p>Java class for partyDetailsOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyDetailsOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressMasterList" type="{}addressObjectService" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyEcommDetailList" type="{}partyEcommData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyMst" type="{}partyMasterServiceObject" minOccurs="0"/>
 *         &lt;element name="partyNameList" type="{}partyPersonName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyPhoneList" type="{}partyPhoneData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyPreferences" type="{}prefAffPartyData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="personalIds" type="{}personalIdOutput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="taxDetail" type="{}taxDetailsOutput" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyDetailsOutput", propOrder = {
    "addressMasterList",
    "partyEcommDetailList",
    "partyMst",
    "partyNameList",
    "partyPhoneList",
    "partyPreferences",
    "personalIds",
    "taxDetail"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyDetailsOutput {

    @XmlElement(nillable = true)
    protected List<AddressObjectService> addressMasterList;
    @XmlElement(nillable = true)
    protected List<PartyEcommData> partyEcommDetailList;
    @XmlElementRef(name = "partyMst", type = JAXBElement.class, required = false)
    protected PartyMasterServiceObject partyMst;
    @XmlElement(nillable = true)
    protected List<PartyPersonName> partyNameList;
    @XmlElement(nillable = true)
    protected List<PartyPhoneData> partyPhoneList;
    @XmlElement(nillable = true)
    protected List<PrefAffPartyData> partyPreferences;
    @XmlElement(nillable = true)
    protected List<PersonalIdOutput> personalIds;
    @XmlElement(nillable = true)
    protected List<TaxDetailsOutput> taxDetail;

    /**
     * Gets the value of the addressMasterList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressMasterList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressMasterList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressObjectService }
     * 
     * 
     */
    public List<AddressObjectService> getAddressMasterList() {
        if (addressMasterList == null) {
            addressMasterList = new ArrayList<AddressObjectService>();
        }
        return this.addressMasterList;
    }

    /**
     * Gets the value of the partyEcommDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyEcommDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyEcommDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyEcommData }
     * 
     * 
     */
    public List<PartyEcommData> getPartyEcommDetailList() {
        if (partyEcommDetailList == null) {
            partyEcommDetailList = new ArrayList<PartyEcommData>();
        }
        return this.partyEcommDetailList;
    }

    /**
     * Gets the value of the partyMst property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PartyMasterServiceObject }{@code >}
     *     
     */
    public PartyMasterServiceObject getPartyMst() {
        return partyMst;
    }

    /**
     * Sets the value of the partyMst property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PartyMasterServiceObject }{@code >}
     *     
     */
    public void setPartyMst(PartyMasterServiceObject value) {
        this.partyMst = value;
    }

    /**
     * Gets the value of the partyNameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyNameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyPersonName }
     * 
     * 
     */
    public List<PartyPersonName> getPartyNameList() {
        if (partyNameList == null) {
            partyNameList = new ArrayList<PartyPersonName>();
        }
        return this.partyNameList;
    }

    /**
     * Gets the value of the partyPhoneList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyPhoneList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyPhoneList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyPhoneData }
     * 
     * 
     */
    public List<PartyPhoneData> getPartyPhoneList() {
        if (partyPhoneList == null) {
            partyPhoneList = new ArrayList<PartyPhoneData>();
        }
        return this.partyPhoneList;
    }

    /**
     * Gets the value of the partyPreferences property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyPreferences property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyPreferences().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrefAffPartyData }
     * 
     * 
     */
    public List<PrefAffPartyData> getPartyPreferences() {
        if (partyPreferences == null) {
            partyPreferences = new ArrayList<PrefAffPartyData>();
        }
        return this.partyPreferences;
    }

    /**
     * Gets the value of the personalIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personalIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonalIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonalIdOutput }
     * 
     * 
     */
    public List<PersonalIdOutput> getPersonalIds() {
        if (personalIds == null) {
            personalIds = new ArrayList<PersonalIdOutput>();
        }
        return this.personalIds;
    }

    /**
     * Gets the value of the taxDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaxDetailsOutput }
     * 
     * 
     */
    public List<TaxDetailsOutput> getTaxDetail() {
        if (taxDetail == null) {
            taxDetail = new ArrayList<TaxDetailsOutput>();
        }
        return this.taxDetail;
    }

}
