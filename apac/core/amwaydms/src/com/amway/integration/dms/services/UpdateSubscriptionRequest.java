
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for updateSubscriptionRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateSubscriptionRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriptionList" type="{}subsrciption" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateSubscriptionRequest", propOrder = {
    "aboNum",
    "salesPlanAff",
    "subscriptionList"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSubscriptionRequest
    extends BaseWebServiceInput
{

    protected String aboNum;
    protected String salesPlanAff;
    @XmlElement(nillable = true)
    protected List<Subsrciption> subscriptionList;

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
     * Gets the value of the subscriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subsrciption }
     * 
     * 
     */
    public List<Subsrciption> getSubscriptionList() {
        if (subscriptionList == null) {
            subscriptionList = new ArrayList<Subsrciption>();
        }
        return this.subscriptionList;
    }

}
