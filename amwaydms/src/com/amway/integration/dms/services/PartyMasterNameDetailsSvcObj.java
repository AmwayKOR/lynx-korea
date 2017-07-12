
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for partyMasterNameDetailsSvcObj complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyMasterNameDetailsSvcObj">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partyMst" type="{}partyMasterServiceObject" minOccurs="0"/>
 *         &lt;element name="partyNameList" type="{}partyPersonName" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partyMasterNameDetailsSvcObj", propOrder = {
    "partyMst",
    "partyNameList"
})
public class PartyMasterNameDetailsSvcObj {

    protected PartyMasterServiceObject partyMst;
    @XmlElement(nillable = true)
    protected List<PartyPersonName> partyNameList;

    /**
     * Gets the value of the partyMst property.
     * 
     * @return
     *     possible object is
     *     {@link PartyMasterServiceObject }
     *     
     */
    public PartyMasterServiceObject getPartyMst() {
        return partyMst;
    }

    /**
     * Sets the value of the partyMst property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyMasterServiceObject }
     *     
     */
    public void setPartyMst(PartyMasterServiceObject value) {
        this.partyMst = value;
    }

    /**
     * Gets the value of the partyNameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyNameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyPersonName }
     * 
     * 
     */
    public List<PartyPersonName> getPartyNameList() {
        if (partyNameList == null) {
            partyNameList = new ArrayList<PartyPersonName>();
        }
        return this.partyNameList;
    }

}
