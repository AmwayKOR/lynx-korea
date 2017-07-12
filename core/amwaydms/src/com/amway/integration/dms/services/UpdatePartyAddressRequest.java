
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePartyAddressRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePartyAddressRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="updatePartyAddress" type="{}updatePartyAddress" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePartyAddressRequest", propOrder = {
    "updatePartyAddress"
})
public class UpdatePartyAddressRequest
    extends BaseWebServiceInput
{

    protected UpdatePartyAddress updatePartyAddress;

    /**
     * Gets the value of the updatePartyAddress property.
     * 
     * @return
     *     possible object is
     *     {@link UpdatePartyAddress }
     *     
     */
    public UpdatePartyAddress getUpdatePartyAddress() {
        return updatePartyAddress;
    }

    /**
     * Sets the value of the updatePartyAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdatePartyAddress }
     *     
     */
    public void setUpdatePartyAddress(UpdatePartyAddress value) {
        this.updatePartyAddress = value;
    }

}
