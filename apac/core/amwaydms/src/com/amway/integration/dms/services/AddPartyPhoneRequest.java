
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for addPartyPhoneRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPartyPhoneRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="partyPhoneDetail" type="{}partyPhoneData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPartyPhoneRequest", propOrder = {
    "partyPhoneDetail"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPartyPhoneRequest
    extends BaseWebServiceInput
{

    protected PartyPhoneData partyPhoneDetail;

    /**
     * Gets the value of the partyPhoneDetail property.
     * 
     * @return
     *     possible object is
     *     {@link PartyPhoneData }
     *     
     */
    public PartyPhoneData getPartyPhoneDetail() {
        return partyPhoneDetail;
    }

    /**
     * Sets the value of the partyPhoneDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyPhoneData }
     *     
     */
    public void setPartyPhoneDetail(PartyPhoneData value) {
        this.partyPhoneDetail = value;
    }

}
