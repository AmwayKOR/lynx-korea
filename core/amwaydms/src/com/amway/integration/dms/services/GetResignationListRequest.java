
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for getResignationListRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getResignationListRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="resignationFromDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resignationToDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "getResignationListRequest", propOrder = {
    "resignationFromDate",
    "resignationToDate",
    "salesPlanAff"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResignationListRequest
    extends BaseWebServiceInput
{

    protected String resignationFromDate;
    protected String resignationToDate;
    protected String salesPlanAff;

    /**
     * Gets the value of the resignationFromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResignationFromDate() {
        return resignationFromDate;
    }

    /**
     * Sets the value of the resignationFromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResignationFromDate(String value) {
        this.resignationFromDate = value;
    }

    /**
     * Gets the value of the resignationToDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResignationToDate() {
        return resignationToDate;
    }

    /**
     * Sets the value of the resignationToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResignationToDate(String value) {
        this.resignationToDate = value;
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
