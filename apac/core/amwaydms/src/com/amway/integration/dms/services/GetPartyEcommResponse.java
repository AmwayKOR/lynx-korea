
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for getPartyEcommResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPartyEcommResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="partyEcommDataList" type="{}partyEcommData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPartyEcommResponse", propOrder = {
    "partyEcommDataList"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPartyEcommResponse
    extends ReturnInfoService
{

    @XmlElement(nillable = true)
    protected List<PartyEcommData> partyEcommDataList;

    /**
     * Gets the value of the partyEcommDataList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyEcommDataList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyEcommDataList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyEcommData }
     * 
     * 
     */
    public List<PartyEcommData> getPartyEcommDataList() {
        if (partyEcommDataList == null) {
            partyEcommDataList = new ArrayList<PartyEcommData>();
        }
        return this.partyEcommDataList;
    }

}
