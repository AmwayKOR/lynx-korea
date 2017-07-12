
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePartyPhoneRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePartyPhoneRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="partyPhoneData" type="{}partyPhoneData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePartyPhoneRequest", propOrder = {
    "partyPhoneData"
})
public class UpdatePartyPhoneRequest
    extends BaseWebServiceInput
{

    protected PartyPhoneData partyPhoneData;

    /**
     * Gets the value of the partyPhoneData property.
     * 
     * @return
     *     possible object is
     *     {@link PartyPhoneData }
     *     
     */
    public PartyPhoneData getPartyPhoneData() {
        return partyPhoneData;
    }

    /**
     * Sets the value of the partyPhoneData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyPhoneData }
     *     
     */
    public void setPartyPhoneData(PartyPhoneData value) {
        this.partyPhoneData = value;
    }

}
