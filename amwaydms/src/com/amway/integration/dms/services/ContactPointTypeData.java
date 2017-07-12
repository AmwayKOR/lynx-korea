
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for contactPointTypeData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contactPointTypeData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contactPointTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "contactPointTypeData", propOrder = {
    "contactPointTypeCd",
    "deliveryChoiceCd"
})
public class ContactPointTypeData {

    @XmlElementRef(name = "contactPointTypeCd", type = JAXBElement.class, required = false)
    protected String contactPointTypeCd;
    @XmlElementRef(name = "deliveryChoiceCd", type = JAXBElement.class, required = false)
    protected String deliveryChoiceCd;

    /**
     * Gets the value of the contactPointTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setContactPointTypeCd(String value) {
        this.contactPointTypeCd = value;
    }

    /**
     * Gets the value of the deliveryChoiceCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeliveryChoiceCd(String value) {
        this.deliveryChoiceCd = value;
    }

}
