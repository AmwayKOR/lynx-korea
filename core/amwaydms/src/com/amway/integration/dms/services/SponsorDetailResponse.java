
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sponsorDetailResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sponsorDetailResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sponsorABONo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sponsorEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sponsorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sponsorPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sponsorDetailResponse", propOrder = {
    "sponsorABONo",
    "sponsorEmail",
    "sponsorName",
    "sponsorPhone"
})
public class SponsorDetailResponse {

    public SponsorDetailResponse(){

    }

    @XmlElementRef(name = "sponsorABONo", type = JAXBElement.class, required = false)
    protected String sponsorABONo;
    @XmlElementRef(name = "sponsorEmail", type = JAXBElement.class, required = false)
    protected String sponsorEmail;
    @XmlElementRef(name = "sponsorName", type = JAXBElement.class, required = false)
    protected String sponsorName;
    @XmlElementRef(name = "sponsorPhone", type = JAXBElement.class, required = false)
    protected String sponsorPhone;

    /**
     * Gets the value of the sponsorABONo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSponsorABONo() {
        return sponsorABONo;
    }

    /**
     * Sets the value of the sponsorABONo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSponsorABONo(String value) {
        this.sponsorABONo = value;
    }

    /**
     * Gets the value of the sponsorEmail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSponsorEmail() {
        return sponsorEmail;
    }

    /**
     * Sets the value of the sponsorEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSponsorEmail(String value) {
        this.sponsorEmail = value;
    }

    /**
     * Gets the value of the sponsorName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSponsorName() {
        return sponsorName;
    }

    /**
     * Sets the value of the sponsorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSponsorName(String value) {
        this.sponsorName = value;
    }

    /**
     * Gets the value of the sponsorPhone property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSponsorPhone() {
        return sponsorPhone;
    }

    /**
     * Sets the value of the sponsorPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSponsorPhone(String value) {
        this.sponsorPhone = value;
    }

}
