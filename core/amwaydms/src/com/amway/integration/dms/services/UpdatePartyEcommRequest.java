
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePartyEcommRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePartyEcommRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="partyEcommData" type="{}partyEcommData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePartyEcommRequest", propOrder = {
    "partyEcommData"
})
public class UpdatePartyEcommRequest
    extends BaseWebServiceInput
{

    protected PartyEcommData partyEcommData;

    /**
     * Gets the value of the partyEcommData property.
     * 
     * @return
     *     possible object is
     *     {@link PartyEcommData }
     *     
     */
    public PartyEcommData getPartyEcommData() {
        return partyEcommData;
    }

    /**
     * Sets the value of the partyEcommData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyEcommData }
     *     
     */
    public void setPartyEcommData(PartyEcommData value) {
        this.partyEcommData = value;
    }

}
