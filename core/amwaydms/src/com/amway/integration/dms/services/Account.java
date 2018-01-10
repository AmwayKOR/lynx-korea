package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for Account complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Account">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sponsor" type="{http://tempuri.org}Sponsor" minOccurs="0"/>
 *         &lt;element name="blockPrivilegeList" type="{http://tempuri.org}BlockPrivilege" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subscriptionList" type="{http://tempuri.org}Subscription" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accountBalanceList" type="{http://tempuri.org}AccountBalance" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accountMst" type="{http://tempuri.org}AccountMaster" minOccurs="0"/>
 *         &lt;element name="partyList" type="{http://tempuri.org}Party" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", propOrder = {
    "sponsor",
    "blockPrivilegeList",
    "subscriptionList",
    "accountBalanceList",
    "accountMst",
    "partyList"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    protected Sponsor sponsor;
    protected List<BlockPrivilege> blockPrivilegeList;
    protected List<Subscription> subscriptionList;
    protected List<AccountBalance> accountBalanceList;
    protected AccountMaster accountMst;
    protected List<Party> partyList;
    protected List<MissingInfoDetail> missingInfoList;

    /**
     * Gets the value of the sponsor property.
     * 
     * @return
     *     possible object is
     *     {@link Sponsor }
     *     
     */
    public Sponsor getSponsor() {
        return sponsor;
    }

    /**
     * Sets the value of the sponsor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sponsor }
     *     
     */
    public void setSponsor(Sponsor value) {
        this.sponsor = value;
    }

    /**
     * Gets the value of the blockPrivilegeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blockPrivilegeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockPrivilegeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlockPrivilege }
     * 
     * 
     */
    public List<BlockPrivilege> getBlockPrivilegeList() {
        if (blockPrivilegeList == null) {
            blockPrivilegeList = new ArrayList<BlockPrivilege>();
        }
        return this.blockPrivilegeList;
    }

    /**
     * Gets the value of the subscriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subscription }
     * 
     * 
     */
    public List<Subscription> getSubscriptionList() {
        if (subscriptionList == null) {
            subscriptionList = new ArrayList<Subscription>();
        }
        return this.subscriptionList;
    }

    /**
     * Gets the value of the accountBalanceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountBalanceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountBalanceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountBalance }
     * 
     * 
     */
    public List<AccountBalance> getAccountBalanceList() {
        if (accountBalanceList == null) {
            accountBalanceList = new ArrayList<AccountBalance>();
        }
        return this.accountBalanceList;
    }

    /**
     * Gets the value of the accountMst property.
     * 
     * @return
     *     possible object is
     *     {@link AccountMaster }
     *     
     */
    public AccountMaster getAccountMst() {
        return accountMst;
    }

    /**
     * Sets the value of the accountMst property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountMaster }
     *     
     */
    public void setAccountMst(AccountMaster value) {
        this.accountMst = value;
    }

    /**
     * Gets the value of the partyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Party }
     * 
     * 
     */
    public List<Party> getPartyList() {
        if (partyList == null) {
            partyList = new ArrayList<Party>();
        }
        return this.partyList;
    }

    public List<MissingInfoDetail> getMissingInfoList()
    {
        return missingInfoList;
    }

    public void setMissingInfoList(List<MissingInfoDetail> missingInfoList)
    {
        this.missingInfoList = missingInfoList;
    }
}
