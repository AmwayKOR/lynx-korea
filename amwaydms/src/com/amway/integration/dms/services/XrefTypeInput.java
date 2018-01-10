
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for xrefTypeInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="xrefTypeInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="careOf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPointName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPointPurposeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPointTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seqNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xrefTypeInput", propOrder = {
    "careOf",
    "cntryCd",
    "contactPointName",
    "contactPointPurposeCd",
    "contactPointTypeCd",
    "primaryFlag",
    "seqNum",
    "statusFlag"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class XrefTypeInput {

    protected String careOf;
    protected String cntryCd;
    protected String contactPointName;
    protected String contactPointPurposeCd;
    protected String contactPointTypeCd;
    protected String primaryFlag;
    protected String seqNum;
    protected String statusFlag;

    /**
     * Gets the value of the careOf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCareOf() {
        return careOf;
    }

    /**
     * Sets the value of the careOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCareOf(String value) {
        this.careOf = value;
    }

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
     * Gets the value of the contactPointName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPointName() {
        return contactPointName;
    }

    /**
     * Sets the value of the contactPointName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPointName(String value) {
        this.contactPointName = value;
    }

    /**
     * Gets the value of the contactPointPurposeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPointPurposeCd() {
        return contactPointPurposeCd;
    }

    /**
     * Sets the value of the contactPointPurposeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPointPurposeCd(String value) {
        this.contactPointPurposeCd = value;
    }

    /**
     * Gets the value of the contactPointTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setContactPointTypeCd(String value) {
        this.contactPointTypeCd = value;
    }

    /**
     * Gets the value of the primaryFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryFlag() {
        return primaryFlag;
    }

    /**
     * Sets the value of the primaryFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryFlag(String value) {
        this.primaryFlag = value;
    }

    /**
     * Gets the value of the seqNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeqNum() {
        return seqNum;
    }

    /**
     * Sets the value of the seqNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeqNum(String value) {
        this.seqNum = value;
    }

    /**
     * Gets the value of the statusFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusFlag() {
        return statusFlag;
    }

    /**
     * Sets the value of the statusFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusFlag(String value) {
        this.statusFlag = value;
    }

}
