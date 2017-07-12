
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subsrciptionSvcData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subsrciptionSvcData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancelCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryTypeData" type="{}contactPointTypeData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="isPublicationCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pauseCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="restoreCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscribeFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriptionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriptionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subsrciptionSvcData", propOrder = {
    "cancelCd",
    "deliveryTypeData",
    "isPublicationCd",
    "languageCd",
    "pauseCd",
    "restoreCd",
    "subscribeFlag",
    "subscriptionId",
    "subscriptionName"
})
public class SubsrciptionSvcData {

    @XmlElementRef(name = "cancelCd", type = JAXBElement.class, required = false)
    protected String cancelCd;
    @XmlElement(nillable = true)
    protected List<ContactPointTypeData> deliveryTypeData;
    @XmlElementRef(name = "isPublicationCd", type = JAXBElement.class, required = false)
    protected String isPublicationCd;
    @XmlElementRef(name = "languageCd", type = JAXBElement.class, required = false)
    protected String languageCd;
    @XmlElementRef(name = "pauseCd", type = JAXBElement.class, required = false)
    protected String pauseCd;
    @XmlElementRef(name = "restoreCd", type = JAXBElement.class, required = false)
    protected String restoreCd;
    @XmlElementRef(name = "subscribeFlag", type = JAXBElement.class, required = false)
    protected String subscribeFlag;
    @XmlElementRef(name = "subscriptionId", type = JAXBElement.class, required = false)
    protected String subscriptionId;
    @XmlElementRef(name = "subscriptionName", type = JAXBElement.class, required = false)
    protected String subscriptionName;

    /**
     * Gets the value of the cancelCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getCancelCd() {
        return cancelCd;
    }

    /**
     * Sets the value of the cancelCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCancelCd(String value) {
        this.cancelCd = value;
    }

    /**
     * Gets the value of the deliveryTypeData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryTypeData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryTypeData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactPointTypeData }
     * 
     * 
     */
    public List<ContactPointTypeData> getDeliveryTypeData() {
        if (deliveryTypeData == null) {
            deliveryTypeData = new ArrayList<ContactPointTypeData>();
        }
        return this.deliveryTypeData;
    }

    /**
     * Gets the value of the isPublicationCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getIsPublicationCd() {
        return isPublicationCd;
    }

    /**
     * Sets the value of the isPublicationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsPublicationCd(String value) {
        this.isPublicationCd = value;
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
     * Gets the value of the pauseCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getPauseCd() {
        return pauseCd;
    }

    /**
     * Sets the value of the pauseCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPauseCd(String value) {
        this.pauseCd = value;
    }

    /**
     * Gets the value of the restoreCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getRestoreCd() {
        return restoreCd;
    }

    /**
     * Sets the value of the restoreCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRestoreCd(String value) {
        this.restoreCd = value;
    }

    /**
     * Gets the value of the subscribeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSubscribeFlag() {
        return subscribeFlag;
    }

    /**
     * Sets the value of the subscribeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubscribeFlag(String value) {
        this.subscribeFlag = value;
    }

    /**
     * Gets the value of the subscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Sets the value of the subscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubscriptionId(String value) {
        this.subscriptionId = value;
    }

    /**
     * Gets the value of the subscriptionName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSubscriptionName() {
        return subscriptionName;
    }

    /**
     * Sets the value of the subscriptionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSubscriptionName(String value) {
        this.subscriptionName = value;
    }

}
