
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for returnInfoService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="returnInfoService">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="returnCd" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="returnMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "returnInfoService", propOrder = {
    "type",
    "returnCd",
    "returnMessage",
    "serverName"
})
@XmlSeeAlso({
    PartyHistoryResponse.class,
    CustomerRegistrationOutput.class,
    GetPartyAddressResponse.class,
    PersonalIdDetailsResponse.class,
    PartyNameDetailResponse.class,
    BankAccountDetailResponse.class,
    BlockPrivilegeServiceOutput.class,
    TaxIdResponse.class,
    AmwayProfileOutput.class,
    AddPartyResponse.class,
    AccountResponse.class,
    AccountHistoryResponse.class,
    GetResignationListResponse.class,
    GetIdResponse.class,
    AccountPartyListResponse.class
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReturnInfoService {

    protected String type;
    protected int returnCd;
    protected String returnMessage;
    protected String serverName;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *           the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    // merge with v3 CommonResponse
    protected ErrorMessage errorMessage;
    protected String contactId;
    // merge with v3 CommonResponse

    /**
     * Gets the value of the returnCd property.
     * 
     */
    public int getReturnCd() {
        return returnCd;
    }

    /**
     * Sets the value of the returnCd property.
     * 
     */
    public void setReturnCd(int value) {
        this.returnCd = value;
    }

    /**
     * Gets the value of the returnMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnMessage() {
        return returnMessage;
    }

    /**
     * Sets the value of the returnMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnMessage(String value) {
        this.returnMessage = value;
    }

    /**
     * Gets the value of the serverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the value of the serverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerName(String value) {
        this.serverName = value;
    }


    // merge with v3 CommonResponse
    public ErrorMessage getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getContactId()
    {
        return contactId;
    }

    public void setContactId(String contactId)
    {
        this.contactId = contactId;
    }

    // merge with v3 CommonResponse
}
