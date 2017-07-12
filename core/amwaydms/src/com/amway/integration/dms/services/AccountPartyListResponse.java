
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accountPartyListResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountPartyListResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="accountParty" type="{}accountParty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountPartyListResponse", propOrder = {
    "accountParty"
})
public class AccountPartyListResponse
    extends ReturnInfoService
{

    @XmlElement(nillable = true)
    protected List<AccountParty> accountParty;

    /**
     * Gets the value of the accountParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountParty }
     * 
     * 
     */
    public List<AccountParty> getAccountParty() {
        if (accountParty == null) {
            accountParty = new ArrayList<AccountParty>();
        }
        return this.accountParty;
    }

}
