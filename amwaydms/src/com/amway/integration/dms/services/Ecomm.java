
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for Ecomm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Ecomm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contactPointTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ecommAddr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ecommTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usageList" type="{http://tempuri.org}ContactUsage" maxOccurs="unbounded"/>
 *         &lt;element name="ecommName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowForLogIn" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="contactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Ecomm", propOrder = {
    "contactPointTypeCd",
    "ecommAddr",
    "ecommTypeCd",
    "partyId",
    "usageList",
    "ecommName",
    "allowForLogIn",
    "contactId",
    "ecommTypeName",
    "statusCd"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ecomm {

    @XmlElement(required = true)
    protected String contactPointTypeCd;
    @XmlElement(required = true)
    protected String ecommAddr;
    @XmlElement(required = true)
    protected String ecommTypeCd;
    @XmlElement(required = true)
    protected String partyId;
    @XmlElement(required = true)
    protected List<ContactUsage> usageList;
    protected String ecommName;
    protected Boolean allowForLogIn;
    protected String contactId;
    protected String ecommTypeName;
    protected String statusCd;

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
     * Gets the value of the ecommTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEcommTypeCd() {
        return ecommTypeCd;
    }

    /**
     * Sets the value of the ecommTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEcommTypeCd(String value) {
        this.ecommTypeCd = value;
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
     * Gets the value of the ecommName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEcommName() {
        return ecommName;
    }

    /**
     * Sets the value of the ecommName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEcommName(String value) {
        this.ecommName = value;
    }

    /**
     * Gets the value of the allowForLogIn property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowForLogIn() {
        return allowForLogIn;
    }

    /**
     * Sets the value of the allowForLogIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowForLogIn(Boolean value) {
        this.allowForLogIn = value;
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
     * Gets the value of the ecommTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEcommTypeName() {
        return ecommTypeName;
    }

    /**
     * Sets the value of the ecommTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEcommTypeName(String value) {
        this.ecommTypeName = value;
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

}
