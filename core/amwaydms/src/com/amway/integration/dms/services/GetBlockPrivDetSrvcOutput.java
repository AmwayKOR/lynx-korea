
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for getBlockPrivDetSrvcOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBlockPrivDetSrvcOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blockPrivTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isBlockFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBlockPrivDetSrvcOutput", propOrder = {
    "aboNum",
    "blockPrivTypeId",
    "effectiveDate",
    "expirationDate",
    "isBlockFlag",
    "salesPlanAff"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBlockPrivDetSrvcOutput {

    @XmlElementRef(name = "aboNum", type = JAXBElement.class, required = false)
    protected String aboNum;
    @XmlElementRef(name = "blockPrivTypeId", type = JAXBElement.class, required = false)
    protected String blockPrivTypeId;
    @XmlElementRef(name = "effectiveDate", type = JAXBElement.class, required = false)
    protected String effectiveDate;
    @XmlElementRef(name = "expirationDate", type = JAXBElement.class, required = false)
    protected String expirationDate;
    @XmlElementRef(name = "isBlockFlag", type = JAXBElement.class, required = false)
    protected String isBlockFlag;
    @XmlElementRef(name = "salesPlanAff", type = JAXBElement.class, required = false)
    protected String salesPlanAff;

    /**
     * Gets the value of the aboNum property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getAboNum() {
        return aboNum;
    }

    /**
     * Sets the value of the aboNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAboNum(String value) {
        this.aboNum = value;
    }

    /**
     * Gets the value of the blockPrivTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getBlockPrivTypeId() {
        return blockPrivTypeId;
    }

    /**
     * Sets the value of the blockPrivTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBlockPrivTypeId(String value) {
        this.blockPrivTypeId = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEffectiveDate(String value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the isBlockFlag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getIsBlockFlag() {
        return isBlockFlag;
    }

    /**
     * Sets the value of the isBlockFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIsBlockFlag(String value) {
        this.isBlockFlag = value;
    }

    /**
     * Gets the value of the salesPlanAff property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getSalesPlanAff() {
        return salesPlanAff;
    }

    /**
     * Sets the value of the salesPlanAff property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSalesPlanAff(String value) {
        this.salesPlanAff = value;
    }

}
