
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for Phone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Phone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contactPointTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phoneCntryCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="phoneLocalNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usageList" type="{http://tempuri.org}ContactUsage" maxOccurs="unbounded"/>
 *         &lt;element name="contactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneExtNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eveningFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="smsCapableFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dayFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="phoneAreaCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Phone", propOrder = {
    "contactPointTypeCd",
    "phoneCntryCd",
    "cntryCd",
    "phoneLocalNum",
    "partyId",
    "usageList",
    "contactId",
    "phoneExtNum",
    "statusCd",
    "eveningFlag",
    "smsCapableFlag",
    "dayFlag",
    "phoneAreaCd"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone {

    @XmlElement(required = true)
    protected String contactPointTypeCd;
    @XmlElement(required = true)
    protected String phoneCntryCd;
    @XmlElement(required = true)
    protected String cntryCd;
    @XmlElement(required = true)
    protected String phoneLocalNum;
    @XmlElement(required = true)
    protected String partyId;
    @XmlElement(required = true)
    protected List<ContactUsage> usageList;
    protected String contactId;
    protected String phoneExtNum;
    protected String statusCd;
    protected Boolean eveningFlag;
    protected Boolean smsCapableFlag;
    protected Boolean dayFlag;
    protected String phoneAreaCd;

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
     * Gets the value of the phoneCntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneCntryCd() {
        return phoneCntryCd;
    }

    /**
     * Sets the value of the phoneCntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneCntryCd(String value) {
        this.phoneCntryCd = value;
    }

    /**
     * Gets the value of the cntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCntryCd() {
        return cntryCd;
    }

    /**
     * Sets the value of the cntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCntryCd(String value) {
        this.cntryCd = value;
    }

    /**
     * Gets the value of the phoneLocalNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneLocalNum() {
        return phoneLocalNum;
    }

    /**
     * Sets the value of the phoneLocalNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneLocalNum(String value) {
        this.phoneLocalNum = value;
    }

    /**
     * Gets the value of the partyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setPartyId(String value) {
        this.partyId = value;
    }

    /**
     * Gets the value of the usageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactUsage }
     * 
     * 
     */
    public List<ContactUsage> getUsageList() {
        if (usageList == null) {
            usageList = new ArrayList<ContactUsage>();
        }
        return this.usageList;
    }

    /**
     * Gets the value of the contactId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * Sets the value of the contactId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactId(String value) {
        this.contactId = value;
    }

    /**
     * Gets the value of the phoneExtNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneExtNum() {
        return phoneExtNum;
    }

    /**
     * Sets the value of the phoneExtNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneExtNum(String value) {
        this.phoneExtNum = value;
    }

    /**
     * Gets the value of the statusCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCd() {
        return statusCd;
    }

    /**
     * Sets the value of the statusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCd(String value) {
        this.statusCd = value;
    }

    /**
     * Gets the value of the eveningFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEveningFlag() {
        return eveningFlag;
    }

    /**
     * Sets the value of the eveningFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEveningFlag(Boolean value) {
        this.eveningFlag = value;
    }

    /**
     * Gets the value of the smsCapableFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSmsCapableFlag() {
        return smsCapableFlag;
    }

    /**
     * Sets the value of the smsCapableFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSmsCapableFlag(Boolean value) {
        this.smsCapableFlag = value;
    }

    /**
     * Gets the value of the dayFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDayFlag() {
        return dayFlag;
    }

    /**
     * Sets the value of the dayFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDayFlag(Boolean value) {
        this.dayFlag = value;
    }

    /**
     * Gets the value of the phoneAreaCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneAreaCd() {
        return phoneAreaCd;
    }

    /**
     * Sets the value of the phoneAreaCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneAreaCd(String value) {
        this.phoneAreaCd = value;
    }

}
