
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for accountBalanceResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountBalanceResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{}returnInfoService">
 *       &lt;sequence>
 *         &lt;element name="accountBalanceObjList" type="{}accountBalanceSvcObject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountBalanceResponse", propOrder = {
    "accountBalanceObjList"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBalanceResponse
    extends ReturnInfoService
{

    @XmlElement(nillable = true)
    protected List<AccountBalanceSvcObject> accountBalanceObjList;

    /**
     * Gets the value of the accountBalanceObjList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountBalanceObjList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountBalanceObjList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountBalanceSvcObject }
     * 
     * 
     */
    public List<AccountBalanceSvcObject> getAccountBalanceObjList() {
        if (accountBalanceObjList == null) {
            accountBalanceObjList = new ArrayList<AccountBalanceSvcObject>();
        }
        return this.accountBalanceObjList;
    }

}
