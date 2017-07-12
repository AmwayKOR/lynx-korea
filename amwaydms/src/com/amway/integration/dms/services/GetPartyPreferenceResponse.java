
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPartyPreferenceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPartyPreferenceResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="prefAffPartyData" type="{}prefAffPartyData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPartyPreferenceResponse", propOrder = {
    "prefAffPartyData"
})
public class GetPartyPreferenceResponse
    extends ReturnInfoService
{

    @XmlElement(nillable = true)
    protected List<PrefAffPartyData> prefAffPartyData;

    /**
     * Gets the value of the prefAffPartyData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prefAffPartyData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrefAffPartyData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrefAffPartyData }
     * 
     * 
     */
    public List<PrefAffPartyData> getPrefAffPartyData() {
        if (prefAffPartyData == null) {
            prefAffPartyData = new ArrayList<PrefAffPartyData>();
        }
        return this.prefAffPartyData;
    }

}
