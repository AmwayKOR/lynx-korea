
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for aboMassSyncRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aboMassSyncRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aboMassSyncRequest", propOrder = {
    "aboNum",
    "salesPlanAff",
    "sourceDetail"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AboMassSyncRequest
    extends BaseWebServiceInput
{

    protected String aboNum;
    protected String salesPlanAff;
    protected String sourceDetail;

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
     * Gets the value of the sourceDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceDetail() {
        return sourceDetail;
    }

    /**
     * Sets the value of the sourceDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceDetail(String value) {
        this.sourceDetail = value;
    }

}
