
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for partyPhoneData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyPhoneData">
 *   &lt;complexContent>
 *     &lt;extension base="{}contact">
 *       &lt;sequence>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dayFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eveningFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneAreaCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneExtNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneLocalNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsCapableFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyPhoneData", propOrder = {
    "cntryCd",
    "dayFlag",
    "eveningFlag",
    "phoneAreaCd",
    "phoneCntryCd",
    "phoneExtNum",
    "phoneLocalNum",
    "smsCapableFlag",
    "statusCd"
})
public class PartyPhoneData
    extends Contact
{


    protected String cntryCd;
    protected String dayFlag;
    protected String eveningFlag;
    protected String phoneAreaCd;
    protected String phoneCntryCd;
    protected String phoneExtNum;
    protected String phoneLocalNum;
    protected String smsCapableFlag;
    protected String statusCd;

    /**
     * Gets the value of the cntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCntryCd(String value) {
        this.cntryCd = value;
    }

    /**
     * Gets the value of the dayFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getDayFlag() {
        return dayFlag;
    }

    /**
     * Sets the value of the dayFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDayFlag(String value) {
        this.dayFlag = value;
    }

    /**
     * Gets the value of the eveningFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getEveningFlag() {
        return eveningFlag;
    }

    /**
     * Sets the value of the eveningFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEveningFlag(String value) {
        this.eveningFlag = value;
    }

    /**
     * Gets the value of the phoneAreaCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPhoneLocalNum(String value) {
        this.phoneLocalNum = value;
    }

    /**
     * Gets the value of the smsCapableFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSmsCapableFlag(String value) {
        this.smsCapableFlag = value;
    }

    /**
     * Gets the value of the statusCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatusCd(String value) {
        this.statusCd = value;
    }

}
