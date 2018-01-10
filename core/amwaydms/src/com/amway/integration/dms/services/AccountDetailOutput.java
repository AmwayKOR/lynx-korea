
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for accountDetailOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountDetailOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acctUsageList" type="{}accountUseDetailXrefInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="bankAcctHolderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAcctId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAcctName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAcctNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAcctTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBranchName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "accountDetailOutput", propOrder = {
    "acctUsageList",
    "bankAcctHolderName",
    "bankAcctId",
    "bankAcctName",
    "bankAcctNum",
    "bankAcctTypeCd",
    "bankBranchCode",
    "bankBranchName",
    "currencyCd",
    "effectiveEndDate",
    "effectiveStartDate",
    "issuingBankId",
    "issuingBankName",
    "primaryRoutingNum",
    "secondaryRoutingNum"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetailOutput {

    @XmlElement(nillable = true)
    protected List<AccountUseDetailXrefInput> acctUsageList;
    @XmlElementRef(name = "bankAcctHolderName", type = JAXBElement.class, required = false)
    protected String bankAcctHolderName;
    @XmlElementRef(name = "bankAcctId", type = JAXBElement.class, required = false)
    protected String bankAcctId;
    @XmlElementRef(name = "bankAcctName", type = JAXBElement.class, required = false)
    protected String bankAcctName;
    @XmlElementRef(name = "bankAcctNum", type = JAXBElement.class, required = false)
    protected String bankAcctNum;
    @XmlElementRef(name = "bankAcctTypeCd", type = JAXBElement.class, required = false)
    protected String bankAcctTypeCd;
    @XmlElementRef(name = "bankBranchCode", type = JAXBElement.class, required = false)
    protected String bankBranchCode;
    @XmlElementRef(name = "bankBranchName", type = JAXBElement.class, required = false)
    protected String bankBranchName;
    @XmlElementRef(name = "currencyCd", type = JAXBElement.class, required = false)
    protected String currencyCd;
    @XmlElementRef(name = "effectiveEndDate", type = JAXBElement.class, required = false)
    protected String effectiveEndDate;
    @XmlElementRef(name = "effectiveStartDate", type = JAXBElement.class, required = false)
    protected String effectiveStartDate;
    @XmlElementRef(name = "issuingBankId", type = JAXBElement.class, required = false)
    protected String issuingBankId;
    @XmlElementRef(name = "issuingBankName", type = JAXBElement.class, required = false)
    protected String issuingBankName;
    @XmlElementRef(name = "primaryRoutingNum", type = JAXBElement.class, required = false)
    protected String primaryRoutingNum;
    @XmlElementRef(name = "secondaryRoutingNum", type = JAXBElement.class, required = false)
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBankAcctHolderName(String value) {
        this.bankAcctHolderName = value;
    }

    /**
     * Gets the value of the bankAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getBankAcctId() {
        return bankAcctId;
    }

    /**
     * Sets the value of the bankAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBankAcctId(String value) {
        this.bankAcctId = value;
    }

    /**
     * Gets the value of the bankAcctName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEffectiveStartDate(String value) {
        this.effectiveStartDate = value;
    }

    /**
     * Gets the value of the issuingBankId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecondaryRoutingNum(String value) {
        this.secondaryRoutingNum = value;
    }

}
