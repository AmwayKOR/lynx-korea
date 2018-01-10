
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for partyHistoryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyHistoryResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="partyHistoryData" type="{}partyHistoryData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyHistoryResponse", propOrder = {
    "partyHistoryData"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyHistoryResponse
    extends ReturnInfoService
{

    @XmlElement(nillable = true)
    protected List<PartyHistoryData> partyHistoryData;

    /**
     * Gets the value of the partyHistoryData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyHistoryData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyHistoryData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyHistoryData }
     * 
     * 
     */
    public List<PartyHistoryData> getPartyHistoryData() {
        if (partyHistoryData == null) {
            partyHistoryData = new ArrayList<PartyHistoryData>();
        }
        return this.partyHistoryData;
    }

}
