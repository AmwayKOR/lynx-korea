package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for SubscriptionDelivery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriptionDelivery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deliveryTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliveryChoiceCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriptionDelivery", propOrder = {
    "deliveryTypeCd",
    "deliveryChoiceCd"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionDelivery {

    @XmlElement(required = true)
    protected String deliveryTypeCd;
    protected String deliveryChoiceCd;

    /**
     * Gets the value of the deliveryTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryTypeCd() {
        return deliveryTypeCd;
    }

    /**
     * Sets the value of the deliveryTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryTypeCd(String value) {
        this.deliveryTypeCd = value;
    }

    /**
     * Gets the value of the deliveryChoiceCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryChoiceCd() {
        return deliveryChoiceCd;
    }

    /**
     * Sets the value of the deliveryChoiceCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryChoiceCd(String value) {
        this.deliveryChoiceCd = value;
    }

}
