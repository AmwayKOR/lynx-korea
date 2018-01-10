package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for BlockPrivilege complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BlockPrivilege">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="blockPrivTypeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="blockFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BlockPrivilege", propOrder = {
    "blockPrivTypeId",
    "effectiveDate",
    "expirationDate",
    "blockFlag"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockPrivilege {

    @XmlElement(required = true)
    protected String blockPrivTypeId;
    @XmlElement(required = true)
    protected String effectiveDate;
    @XmlElement(required = true)
    protected String expirationDate;
    protected Boolean blockFlag;

    /**
     * Gets the value of the blockPrivTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setExpirationDate(String value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the blockFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBlockFlag() {
        return blockFlag;
    }

    /**
     * Sets the value of the blockFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBlockFlag(Boolean value) {
        this.blockFlag = value;
    }

}
