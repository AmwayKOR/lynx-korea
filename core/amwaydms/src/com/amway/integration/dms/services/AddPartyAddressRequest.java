
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for addPartyAddressRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPartyAddressRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="addPartyAddress" type="{}addPartyAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPartyAddressRequest", propOrder = {
    "addPartyAddress"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPartyAddressRequest
    extends BaseWebServiceInput
{

    protected AddPartyAddress addPartyAddress;

    protected AddressInput address;
    protected String applyToAllLinkedAccountFlag;

    /**
     * Gets the value of the addPartyAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddPartyAddress }
     *     
     */
    public AddPartyAddress getAddPartyAddress() {
        return addPartyAddress;
    }

    /**
     * Sets the value of the addPartyAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddPartyAddress }
     *     
     */
    public void setAddPartyAddress(AddPartyAddress value) {
        this.addPartyAddress = value;
    }

    public AddressInput getAddress()
    {
        return address;
    }

    public void setAddress(AddressInput address)
    {
        this.address = address;
    }

    public String getApplyToAllLinkedAccountFlag()
    {
        return applyToAllLinkedAccountFlag;
    }

    public void setApplyToAllLinkedAccountFlag(String applyToAllLinkedAccountFlag)
    {
        this.applyToAllLinkedAccountFlag = applyToAllLinkedAccountFlag;
    }
}
