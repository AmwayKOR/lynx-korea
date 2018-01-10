
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for subsrciption complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subsrciption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cancelCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryTypeDataList" type="{}contactPointTypeData" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "subsrciption", propOrder = {
    "cancelCd",
    "deliveryTypeDataList",
    "pauseCd",
    "restoreCd",
    "subscribeFlag",
    "subscriptionId",
    "subscriptionName"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subsrciption {

    protected String cancelCd;
    @XmlElement(nillable = true)
    protected List<ContactPointTypeData> deliveryTypeDataList;
    protected String pauseCd;
    protected String restoreCd;
    protected String subscribeFlag;
    protected String subscriptionId;
    protected String subscriptionName;

    /**
     * Gets the value of the cancelCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setCancelCd(String value) {
        this.cancelCd = value;
    }

    /**
     * Gets the value of the deliveryTypeDataList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryTypeDataList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryTypeDataList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactPointTypeData }
     * 
     * 
     */
    public List<ContactPointTypeData> getDeliveryTypeDataList() {
        if (deliveryTypeDataList == null) {
            deliveryTypeDataList = new ArrayList<ContactPointTypeData>();
        }
        return this.deliveryTypeDataList;
    }

    /**
     * Gets the value of the pauseCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setSubscriptionName(String value) {
        this.subscriptionName = value;
    }

}
