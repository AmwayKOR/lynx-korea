
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for ecommDetailInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ecommDetailInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allowForLogin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommTypeList" type="{}xrefTypeInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxMesgLength" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usePrimaryFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ecommDetailInput", propOrder = {
    "allowForLogin",
    "ecommAddr",
    "ecommName",
    "ecommTypeCd",
    "ecommTypeList",
    "languageCd",
    "maxMesgLength",
    "usePrimaryFlag"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class EcommDetailInput {

    protected String allowForLogin;
    protected String ecommAddr;
    protected String ecommName;
    protected String ecommTypeCd;
    @XmlElement(nillable = true)
    protected List<XrefTypeInput> ecommTypeList;
    protected String languageCd;
    protected String maxMesgLength;
    protected String usePrimaryFlag;

    /**
     * Gets the value of the allowForLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowForLogin() {
        return allowForLogin;
    }

    /**
     * Sets the value of the allowForLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowForLogin(String value) {
        this.allowForLogin = value;
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
     * Gets the value of the ecommTypeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ecommTypeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEcommTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XrefTypeInput }
     * 
     * 
     */
    public List<XrefTypeInput> getEcommTypeList() {
        if (ecommTypeList == null) {
            ecommTypeList = new ArrayList<XrefTypeInput>();
        }
        return this.ecommTypeList;
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
     * Gets the value of the maxMesgLength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxMesgLength() {
        return maxMesgLength;
    }

    /**
     * Sets the value of the maxMesgLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxMesgLength(String value) {
        this.maxMesgLength = value;
    }

    /**
     * Gets the value of the usePrimaryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsePrimaryFlag() {
        return usePrimaryFlag;
    }

    /**
     * Sets the value of the usePrimaryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsePrimaryFlag(String value) {
        this.usePrimaryFlag = value;
    }

}
