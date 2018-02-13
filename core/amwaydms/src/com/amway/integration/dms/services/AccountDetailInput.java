
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for accountDetailInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountDetailInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acctUsageList" type="{}accountUseDetailXrefInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="bankAcctHolderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAcctName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAcctNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAcctTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isAcctTypeChecking" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isCreditCardAcct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuingBankId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issuingBankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryRoutingNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondaryRoutingNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountDetailInput", propOrder = {
    "acctUsageList",
    "bankAcctHolderName",
    "bankAcctName",
    "bankAcctNum",
    "bankAcctTypeCd",
    "bankBranchCode",
    "bankBranchName",
    "currencyCd",
    "effectiveEndDate",
    "effectiveStartDate",
    "isAcctTypeChecking",
    "isCreditCardAcct",
    "issuingBankId",
    "issuingBankName",
    "primaryRoutingNum",
    "secondaryRoutingNum"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetailInput {

    @XmlElement(nillable = true)
    protected List<AccountUseDetailXrefInput> acctUsageList;
    protected String bankAcctHolderName;
    protected String bankAcctName;
    protected String bankAcctNum;
    protected String bankAcctTypeCd;
    protected String bankBranchCode;
    protected String bankBranchName;
    protected String currencyCd;
    protected String effectiveEndDate;
    protected String effectiveStartDate;
    protected String isAcctTypeChecking;
    protected String isCreditCardAcct;
    protected String issuingBankId;
    protected String issuingBankName;
    protected String primaryRoutingNum;
    protected String secondaryRoutingNum;

    /**
     * Gets the value of the acctUsageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acctUsageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAcctUsageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountUseDetailXrefInput }
     * 
     * 
     */
    public List<AccountUseDetailXrefInput> getAcctUsageList() {
        if (acctUsageList == null) {
            acctUsageList = new ArrayList<AccountUseDetailXrefInput>();
        }
        return this.acctUsageList;
    }

    /**
     * Gets the value of the bankAcctHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAcctHolderName() {
        return bankAcctHolderName;
    }

    /**
     * Sets the value of the bankAcctHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAcctHolderName(String value) {
        this.bankAcctHolderName = value;
    }

    /**
     * Gets the value of the bankAcctName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAcctName() {
        return bankAcctName;
    }

    /**
     * Sets the value of the bankAcctName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAcctName(String value) {
        this.bankAcctName = value;
    }

    /**
     * Gets the value of the bankAcctNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAcctNum() {
        return bankAcctNum;
    }

    /**
     * Sets the value of the bankAcctNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAcctNum(String value) {
        this.bankAcctNum = value;
    }

    /**
     * Gets the value of the bankAcctTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAcctTypeCd() {
        return bankAcctTypeCd;
    }

    /**
     * Sets the value of the bankAcctTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAcctTypeCd(String value) {
        this.bankAcctTypeCd = value;
    }

    /**
     * Gets the value of the bankBranchCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankBranchCode() {
        return bankBranchCode;
    }

    /**
     * Sets the value of the bankBranchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankBranchCode(String value) {
        this.bankBranchCode = value;
    }

    /**
     * Gets the value of the bankBranchName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankBranchName() {
        return bankBranchName;
    }

    /**
     * Sets the value of the bankBranchName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankBranchName(String value) {
        this.bankBranchName = value;
    }

    /**
     * Gets the value of the currencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCd() {
        return currencyCd;
    }

    /**
     * Sets the value of the currencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCd(String value) {
        this.currencyCd = value;
    }

    /**
     * Gets the value of the effectiveEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveEndDate() {
        return effectiveEndDate;
    }

    /**
     * Sets the value of the effectiveEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveEndDate(String value) {
        this.effectiveEndDate = value;
    }

    /**
     * Gets the value of the effectiveStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveStartDate() {
        return effectiveStartDate;
    }

    /**
     * Sets the value of the effectiveStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveStartDate(String value) {
        this.effectiveStartDate = value;
    }

    /**
     * Gets the value of the isAcctTypeChecking property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAcctTypeChecking() {
        return isAcctTypeChecking;
    }

    /**
     * Sets the value of the isAcctTypeChecking property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAcctTypeChecking(String value) {
        this.isAcctTypeChecking = value;
    }

    /**
     * Gets the value of the isCreditCardAcct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsCreditCardAcct() {
        return isCreditCardAcct;
    }

    /**
     * Sets the value of the isCreditCardAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsCreditCardAcct(String value) {
        this.isCreditCardAcct = value;
    }

    /**
     * Gets the value of the issuingBankId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuingBankId() {
        return issuingBankId;
    }

    /**
     * Sets the value of the issuingBankId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingBankId(String value) {
        this.issuingBankId = value;
    }

    /**
     * Gets the value of the issuingBankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssuingBankName() {
        return issuingBankName;
    }

    /**
     * Sets the value of the issuingBankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssuingBankName(String value) {
        this.issuingBankName = value;
    }

    /**
     * Gets the value of the primaryRoutingNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryRoutingNum() {
        return primaryRoutingNum;
    }

    /**
     * Sets the value of the primaryRoutingNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryRoutingNum(String value) {
        this.primaryRoutingNum = value;
    }

    /**
     * Gets the value of the secondaryRoutingNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryRoutingNum() {
        return secondaryRoutingNum;
    }

    /**
     * Sets the value of the secondaryRoutingNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryRoutingNum(String value) {
        this.secondaryRoutingNum = value;
    }

}
