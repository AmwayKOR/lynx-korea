
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addTaxIdInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addTaxIdInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxIdAdd" type="{}taxIdDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addTaxIdInput", propOrder = {
    "aboNum",
    "salesPlanAff",
    "taxIdAdd"
})
public class AddTaxIdInput {

    protected String aboNum;
    protected String salesPlanAff;
    protected TaxIdDetail taxIdAdd;

    /**
     * Gets the value of the aboNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setAboNum(String value) {
        this.aboNum = value;
    }

    /**
     * Gets the value of the salesPlanAff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
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
     *     {@link String }
     *     
     */
    public void setSalesPlanAff(String value) {
        this.salesPlanAff = value;
    }

    /**
     * Gets the value of the taxIdAdd property.
     * 
     * @return
     *     possible object is
     *     {@link TaxIdDetail }
     *     
     */
    public TaxIdDetail getTaxIdAdd() {
        return taxIdAdd;
    }

    /**
     * Sets the value of the taxIdAdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxIdDetail }
     *     
     */
    public void setTaxIdAdd(TaxIdDetail value) {
        this.taxIdAdd = value;
    }

}
