
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for partyNameDetailResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyNameDetailResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="partyMasterNameDtlList" type="{}partyMasterNameDetailsSvcObj" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyNameDetailResponse", propOrder = {
    "partyMasterNameDtlList"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyNameDetailResponse
    extends ReturnInfoService
{

    @XmlElement(nillable = true)
    protected List<PartyMasterNameDetailsSvcObj> partyMasterNameDtlList;

    /**
     * Gets the value of the partyMasterNameDtlList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyMasterNameDtlList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyMasterNameDtlList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyMasterNameDetailsSvcObj }
     * 
     * 
     */
    public List<PartyMasterNameDetailsSvcObj> getPartyMasterNameDtlList() {
        if (partyMasterNameDtlList == null) {
            partyMasterNameDtlList = new ArrayList<PartyMasterNameDetailsSvcObj>();
        }
        return this.partyMasterNameDtlList;
    }

}
