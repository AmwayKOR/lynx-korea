package com.amway.integration.dms.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for baseWebServiceInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="baseWebServiceInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientApplicationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIpAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientRoleList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientSalesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loggedInAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loggedInCustomerServiceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loggedInPartyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseWebServiceInput {

    protected String clientApplicationId;
    protected String clientCntryCd;
    protected String clientIpAddress;
    protected String clientRoleList;
    protected String clientSalesPlanAff;
    protected String loggedInAccountId;
    protected String loggedInCustomerServiceId;
    protected String loggedInPartyId;

    /**
     * Gets the value of the clientApplicationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientApplicationId() {
        return clientApplicationId;
    }

    /**
     * Sets the value of the clientApplicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientApplicationId(String value) {
        this.clientApplicationId = value;
    }

    /**
     * Gets the value of the clientCntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientCntryCd() {
        return clientCntryCd;
    }

    /**
     * Sets the value of the clientCntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientCntryCd(String value) {
        this.clientCntryCd = value;
    }

    /**
     * Gets the value of the clientIpAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientIpAddress() {
        return clientIpAddress;
    }

    /**
     * Sets the value of the clientIpAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientIpAddress(String value) {
        this.clientIpAddress = value;
    }

    /**
     * Gets the value of the clientRoleList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientRoleList() {
        return clientRoleList;
    }

    /**
     * Sets the value of the clientRoleList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientRoleList(String value) {
        this.clientRoleList = value;
    }

    /**
     * Gets the value of the clientSalesPlanAff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientSalesPlanAff() {
        return clientSalesPlanAff;
    }

    /**
     * Sets the value of the clientSalesPlanAff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientSalesPlanAff(String value) {
        this.clientSalesPlanAff = value;
    }

    /**
     * Gets the value of the loggedInAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedInAccountId() {
        return loggedInAccountId;
    }

    /**
     * Sets the value of the loggedInAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedInAccountId(String value) {
        this.loggedInAccountId = value;
    }

    /**
     * Gets the value of the loggedInCustomerServiceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedInCustomerServiceId() {
        return loggedInCustomerServiceId;
    }

    /**
     * Sets the value of the loggedInCustomerServiceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedInCustomerServiceId(String value) {
        this.loggedInCustomerServiceId = value;
    }

    /**
     * Gets the value of the loggedInPartyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedInPartyId() {
        return loggedInPartyId;
    }

    /**
     * Sets the value of the loggedInPartyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedInPartyId(String value) {
        this.loggedInPartyId = value;
    }

}
