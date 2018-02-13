
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for deletePartyAddressRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deletePartyAddressRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="deleteAddress" type="{}deleteAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deletePartyAddressRequest", propOrder = {
    "deleteAddress"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeletePartyAddressRequest
    extends BaseWebServiceInput
{

    protected DeleteAddress deleteAddress;

    /**
     * Gets the value of the deleteAddress property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteAddress }
     *     
     */
    public DeleteAddress getDeleteAddress() {
        return deleteAddress;
    }

    /**
     * Sets the value of the deleteAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteAddress }
     *     
     */
    public void setDeleteAddress(DeleteAddress value) {
        this.deleteAddress = value;
    }

}
