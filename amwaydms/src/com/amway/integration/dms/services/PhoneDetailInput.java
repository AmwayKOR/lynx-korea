
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for phoneDetailInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="phoneDetailInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneAreaCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneExtNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneLocalNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneTypeList" type="{}xrefTypeInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="smsCapableFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "phoneDetailInput", propOrder = {
    "cntryCd",
    "phoneAreaCd",
    "phoneCntryCd",
    "phoneExtNum",
    "phoneLocalNum",
    "phoneTypeList",
    "smsCapableFlag",
    "usePrimaryFlag"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneDetailInput {

    protected String cntryCd;
    protected String phoneAreaCd;
    protected String phoneCntryCd;
    protected String phoneExtNum;
    protected String phoneLocalNum;
    @XmlElement(nillable = true)
    protected List<XrefTypeInput> phoneTypeList;
    protected String smsCapableFlag;
    protected String usePrimaryFlag;

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
     * Gets the value of the phoneTypeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneTypeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XrefTypeInput }
     * 
     * 
     */
    public List<XrefTypeInput> getPhoneTypeList() {
        if (phoneTypeList == null) {
            phoneTypeList = new ArrayList<XrefTypeInput>();
        }
        return this.phoneTypeList;
    }

    /**
     * Gets the value of the smsCapableFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsCapableFlag() {
        return smsCapableFlag;
    }

    /**
     * Sets the value of the smsCapableFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsCapableFlag(String value) {
        this.smsCapableFlag = value;
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
