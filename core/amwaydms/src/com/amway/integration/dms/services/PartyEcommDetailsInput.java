
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for partyEcommDetailsInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyEcommDetailsInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="affNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ecommDetailInput" type="{}ecommDetailInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="processType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyEcommDetailsInput", propOrder = {
    "affNo",
    "ecommDetailInput",
    "processType"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyEcommDetailsInput {

    protected String affNo;
    @XmlElement(nillable = true)
    protected List<EcommDetailInput> ecommDetailInput;
    protected String processType;

    /**
     * Gets the value of the affNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffNo() {
        return affNo;
    }

    /**
     * Sets the value of the affNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffNo(String value) {
        this.affNo = value;
    }

    /**
     * Gets the value of the ecommDetailInput property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ecommDetailInput property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEcommDetailInput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EcommDetailInput }
     * 
     * 
     */
    public List<EcommDetailInput> getEcommDetailInput() {
        if (ecommDetailInput == null) {
            ecommDetailInput = new ArrayList<EcommDetailInput>();
        }
        return this.ecommDetailInput;
    }

    /**
     * Gets the value of the processType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessType() {
        return processType;
    }

    /**
     * Sets the value of the processType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessType(String value) {
        this.processType = value;
    }

}
