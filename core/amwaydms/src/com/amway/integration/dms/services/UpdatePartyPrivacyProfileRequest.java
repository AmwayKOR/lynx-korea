
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePartyPrivacyProfileRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePartyPrivacyProfileRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="partyPrivacyData" type="{}partyPrivacySvcData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePartyPrivacyProfileRequest", propOrder = {
    "partyPrivacyData"
})
public class UpdatePartyPrivacyProfileRequest
    extends BaseWebServiceInput
{

    protected PartyPrivacySvcData partyPrivacyData;

    /**
     * Gets the value of the partyPrivacyData property.
     * 
     * @return
     *     possible object is
     *     {@link PartyPrivacySvcData }
     *     
     */
    public PartyPrivacySvcData getPartyPrivacyData() {
        return partyPrivacyData;
    }

    /**
     * Sets the value of the partyPrivacyData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyPrivacySvcData }
     *     
     */
    public void setPartyPrivacyData(PartyPrivacySvcData value) {
        this.partyPrivacyData = value;
    }

}
