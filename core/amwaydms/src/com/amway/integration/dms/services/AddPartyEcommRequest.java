
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addPartyEcommRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPartyEcommRequest">
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
@XmlType(name = "addPartyEcommRequest", propOrder = {
    "partyEcommData"
})
public class AddPartyEcommRequest
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
