package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for updatePersonalIdRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePersonalIdRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personalIdDetailsInput" type="{}personalIdDetailsInput" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePersonalIdRequest", propOrder = {
    "aboNum",
    "personalIdDetailsInput",
    "salesPlanAff"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePersonalIdRequest
    extends BaseWebServiceInput
{

    protected String aboNum;
    protected PersonalIdDetailsInput personalIdDetailsInput;
    protected String salesPlanAff;

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
     * Gets the value of the personalIdDetailsInput property.
     * 
     * @return
     *     possible object is
     *     {@link PersonalIdDetailsInput }
     *     
     */
    public PersonalIdDetailsInput getPersonalIdDetailsInput() {
        return personalIdDetailsInput;
    }

    /**
     * Sets the value of the personalIdDetailsInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalIdDetailsInput }
     *     
     */
    public void setPersonalIdDetailsInput(PersonalIdDetailsInput value) {
        this.personalIdDetailsInput = value;
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

}
