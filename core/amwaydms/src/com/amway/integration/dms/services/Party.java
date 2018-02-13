package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for Party complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Party">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="taxList" type="{http://tempuri.org}Tax" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="missingInfoList" type="{http://tempuri.org}MissingInfoDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="personalIdList" type="{http://tempuri.org}PersonalId" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ecommList" type="{http://tempuri.org}Ecomm" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="addressList" type="{http://tempuri.org}Address" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nameList" type="{http://tempuri.org}PartyName" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="preferenceList" type="{http://tempuri.org}Preference" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="phoneList" type="{http://tempuri.org}Phone" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyMst" type="{http://tempuri.org}PartyMaster" minOccurs="0"/>
 *         &lt;element name="bankAccountDetailList" type="{http://tempuri.org}BankAccount" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party", propOrder = {
    "taxList",
    "missingInfoList",
    "personalIdList",
    "ecommList",
    "addressList",
    "nameList",
    "preferenceList",
    "phoneList",
    "partyMst",
    "bankAccountDetailList"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Party {

    protected List<Tax> taxList;
    protected List<MissingInfoDetail> missingInfoList;
    protected List<PersonalId> personalIdList;
    protected List<Ecomm> ecommList;
    protected List<Address> addressList;
    protected List<PartyName> nameList;
    protected List<Preference> preferenceList;
    protected List<Phone> phoneList;
    protected PartyMaster partyMst;
    protected List<BankAccount> bankAccountDetailList;
    protected List<Subscription> subscriptionList;

    /**
     * Gets the value of the taxList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tax }
     * 
     * 
     */
    public List<Tax> getTaxList() {
        if (taxList == null) {
            taxList = new ArrayList<Tax>();
        }
        return this.taxList;
    }

    /**
     * Gets the value of the missingInfoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the missingInfoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMissingInfoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MissingInfoDetail }
     * 
     * 
     */
    public List<MissingInfoDetail> getMissingInfoList() {
        if (missingInfoList == null) {
            missingInfoList = new ArrayList<MissingInfoDetail>();
        }
        return this.missingInfoList;
    }

    /**
     * Gets the value of the personalIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personalIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonalIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PersonalId }
     * 
     * 
     */
    public List<PersonalId> getPersonalIdList() {
        if (personalIdList == null) {
            personalIdList = new ArrayList<PersonalId>();
        }
        return this.personalIdList;
    }

    /**
     * Gets the value of the ecommList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ecommList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEcommList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ecomm }
     * 
     * 
     */
    public List<Ecomm> getEcommList() {
        if (ecommList == null) {
            ecommList = new ArrayList<Ecomm>();
        }
        return this.ecommList;
    }

    /**
     * Gets the value of the addressList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Address }
     * 
     * 
     */
    public List<Address> getAddressList() {
        if (addressList == null) {
            addressList = new ArrayList<Address>();
        }
        return this.addressList;
    }

    /**
     * Gets the value of the nameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyName }
     * 
     * 
     */
    public List<PartyName> getNameList() {
        if (nameList == null) {
            nameList = new ArrayList<PartyName>();
        }
        return this.nameList;
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
     * {@link Preference }
     * 
     * 
     */
    public List<Preference> getPreferenceList() {
        if (preferenceList == null) {
            preferenceList = new ArrayList<Preference>();
        }
        return this.preferenceList;
    }

    /**
     * Gets the value of the phoneList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Phone }
     * 
     * 
     */
    public List<Phone> getPhoneList() {
        if (phoneList == null) {
            phoneList = new ArrayList<Phone>();
        }
        return this.phoneList;
    }

    /**
     * Gets the value of the partyMst property.
     * 
     * @return
     *     possible object is
     *     {@link PartyMaster }
     *     
     */
    public PartyMaster getPartyMst() {
        return partyMst;
    }

    /**
     * Sets the value of the partyMst property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyMaster }
     *     
     */
    public void setPartyMst(PartyMaster value) {
        this.partyMst = value;
    }

    /**
     * Gets the value of the bankAccountDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bankAccountDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBankAccountDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BankAccount }
     * 
     * 
     */
    public List<BankAccount> getBankAccountDetailList() {
        if (bankAccountDetailList == null) {
            bankAccountDetailList = new ArrayList<BankAccount>();
        }
        return this.bankAccountDetailList;
    }

    public List<Subscription> getSubscriptionList()
    {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList)
    {
        this.subscriptionList = subscriptionList;
    }
}
