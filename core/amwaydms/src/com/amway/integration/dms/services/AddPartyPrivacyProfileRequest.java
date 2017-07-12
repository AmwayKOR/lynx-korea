
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addPartyPrivacyProfileRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPartyPrivacyProfileRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="partyPrivaryProfileList" type="{}partyPrivacySvcData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPartyPrivacyProfileRequest", propOrder = {
    "partyPrivaryProfileList"
})
public class AddPartyPrivacyProfileRequest
    extends BaseWebServiceInput
{

    @XmlElement(nillable = true)
    protected List<PartyPrivacySvcData> partyPrivaryProfileList;

    /**
     * Gets the value of the partyPrivaryProfileList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyPrivaryProfileList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyPrivaryProfileList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyPrivacySvcData }
     * 
     * 
     */
    public List<PartyPrivacySvcData> getPartyPrivaryProfileList() {
        if (partyPrivaryProfileList == null) {
            partyPrivaryProfileList = new ArrayList<PartyPrivacySvcData>();
        }
        return this.partyPrivaryProfileList;
    }

}
