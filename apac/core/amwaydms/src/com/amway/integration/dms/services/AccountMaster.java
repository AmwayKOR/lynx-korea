package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for AccountMaster complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountMaster">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lglEntityType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regdlntlPrmyBusflg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountCreditStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeAboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segmentCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSubTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryPersonPartyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="busEntityNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blackListFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regdSpnIboNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lateRenewalEligibleFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loaCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeExcludeFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regdlntlSpnIboNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aboExpireDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountCreditLimit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renewalFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountMissingInfoFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regdlntlSpnAffNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderCreditLimit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aboEntryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountMaster", propOrder = {
    "currencyCd",
    "accountName",
    "lglEntityType",
    "regdlntlPrmyBusflg",
    "accountCreditStatusCd",
    "homeAboNum",
    "segmentCd",
    "accountSubTypeCd",
    "primaryPersonPartyId",
    "busEntityNum",
    "blackListFlg",
    "salesPlanAff",
    "regdSpnIboNo",
    "lateRenewalEligibleFlg",
    "loaCd",
    "company",
    "homeCntryCd",
    "includeExcludeFlg",
    "regdlntlSpnIboNo",
    "languageCd",
    "aboExpireDate",
    "accountCreditLimit",
    "aboNum",
    "cntryCd",
    "statusCd",
    "renewalFlag",
    "userId",
    "accountMissingInfoFlg",
    "userPin",
    "accountTypeCd",
    "regdlntlSpnAffNo",
    "orderCreditLimit",
    "aboEntryDate"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountMaster {

    protected String currencyCd;
    protected String accountName;
    protected String lglEntityType;
    protected String regdlntlPrmyBusflg;
    protected String accountCreditStatusCd;
    protected String homeAboNum;
    protected String segmentCd;
    protected String accountSubTypeCd;
    protected String primaryPersonPartyId;
    protected String busEntityNum;
    protected String blackListFlg;
    protected String salesPlanAff;
    protected String regdSpnIboNo;
    protected String lateRenewalEligibleFlg;
    protected String loaCd;
    protected String company;
    protected String homeCntryCd;
    protected String includeExcludeFlg;
    protected String regdlntlSpnIboNo;
    protected String languageCd;
    protected String aboExpireDate;
    protected String accountCreditLimit;
    protected String aboNum;
    protected String cntryCd;
    protected String statusCd;
    protected String renewalFlag;
    protected String userId;
    protected String accountMissingInfoFlg;
    protected String userPin;
    protected String accountTypeCd;
    protected String regdlntlSpnAffNo;
    protected String orderCreditLimit;
    protected String aboEntryDate;

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
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the lglEntityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLglEntityType() {
        return lglEntityType;
    }

    /**
     * Sets the value of the lglEntityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLglEntityType(String value) {
        this.lglEntityType = value;
    }

    /**
     * Gets the value of the regdlntlPrmyBusflg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegdlntlPrmyBusflg() {
        return regdlntlPrmyBusflg;
    }

    /**
     * Sets the value of the regdlntlPrmyBusflg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegdlntlPrmyBusflg(String value) {
        this.regdlntlPrmyBusflg = value;
    }

    /**
     * Gets the value of the accountCreditStatusCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCreditStatusCd() {
        return accountCreditStatusCd;
    }

    /**
     * Sets the value of the accountCreditStatusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCreditStatusCd(String value) {
        this.accountCreditStatusCd = value;
    }

    /**
     * Gets the value of the homeAboNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeAboNum() {
        return homeAboNum;
    }

    /**
     * Sets the value of the homeAboNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeAboNum(String value) {
        this.homeAboNum = value;
    }

    /**
     * Gets the value of the segmentCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentCd() {
        return segmentCd;
    }

    /**
     * Sets the value of the segmentCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentCd(String value) {
        this.segmentCd = value;
    }

    /**
     * Gets the value of the accountSubTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountSubTypeCd() {
        return accountSubTypeCd;
    }

    /**
     * Sets the value of the accountSubTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountSubTypeCd(String value) {
        this.accountSubTypeCd = value;
    }

    /**
     * Gets the value of the primaryPersonPartyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryPersonPartyId() {
        return primaryPersonPartyId;
    }

    /**
     * Sets the value of the primaryPersonPartyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryPersonPartyId(String value) {
        this.primaryPersonPartyId = value;
    }

    /**
     * Gets the value of the busEntityNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusEntityNum() {
        return busEntityNum;
    }

    /**
     * Sets the value of the busEntityNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusEntityNum(String value) {
        this.busEntityNum = value;
    }

    /**
     * Gets the value of the blackListFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlackListFlg() {
        return blackListFlg;
    }

    /**
     * Sets the value of the blackListFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlackListFlg(String value) {
        this.blackListFlg = value;
    }

    /**
     * Gets the value of the salesPlanAff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesPlanAff() {
        return salesPlanAff;
    }

    /**
     * Sets the value of the salesPlanAff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesPlanAff(String value) {
        this.salesPlanAff = value;
    }

    /**
     * Gets the value of the regdSpnIboNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegdSpnIboNo() {
        return regdSpnIboNo;
    }

    /**
     * Sets the value of the regdSpnIboNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegdSpnIboNo(String value) {
        this.regdSpnIboNo = value;
    }

    /**
     * Gets the value of the lateRenewalEligibleFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLateRenewalEligibleFlg() {
        return lateRenewalEligibleFlg;
    }

    /**
     * Sets the value of the lateRenewalEligibleFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLateRenewalEligibleFlg(String value) {
        this.lateRenewalEligibleFlg = value;
    }

    /**
     * Gets the value of the loaCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoaCd() {
        return loaCd;
    }

    /**
     * Sets the value of the loaCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoaCd(String value) {
        this.loaCd = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the homeCntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCntryCd() {
        return homeCntryCd;
    }

    /**
     * Sets the value of the homeCntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCntryCd(String value) {
        this.homeCntryCd = value;
    }

    /**
     * Gets the value of the includeExcludeFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeExcludeFlg() {
        return includeExcludeFlg;
    }

    /**
     * Sets the value of the includeExcludeFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeExcludeFlg(String value) {
        this.includeExcludeFlg = value;
    }

    /**
     * Gets the value of the regdlntlSpnIboNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegdlntlSpnIboNo() {
        return regdlntlSpnIboNo;
    }

    /**
     * Sets the value of the regdlntlSpnIboNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegdlntlSpnIboNo(String value) {
        this.regdlntlSpnIboNo = value;
    }

    /**
     * Gets the value of the languageCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCd() {
        return languageCd;
    }

    /**
     * Sets the value of the languageCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCd(String value) {
        this.languageCd = value;
    }

    /**
     * Gets the value of the aboExpireDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAboExpireDate() {
        return aboExpireDate;
    }

    /**
     * Sets the value of the aboExpireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAboExpireDate(String value) {
        this.aboExpireDate = value;
    }

    /**
     * Gets the value of the accountCreditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCreditLimit() {
        return accountCreditLimit;
    }

    /**
     * Sets the value of the accountCreditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCreditLimit(String value) {
        this.accountCreditLimit = value;
    }

    /**
     * Gets the value of the aboNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAboNum() {
        return aboNum;
    }

    /**
     * Sets the value of the aboNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAboNum(String value) {
        this.aboNum = value;
    }

    /**
     * Gets the value of the cntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCntryCd() {
        return cntryCd;
    }

    /**
     * Sets the value of the cntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCntryCd(String value) {
        this.cntryCd = value;
    }

    /**
     * Gets the value of the statusCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCd() {
        return statusCd;
    }

    /**
     * Sets the value of the statusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCd(String value) {
        this.statusCd = value;
    }

    /**
     * Gets the value of the renewalFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewalFlag() {
        return renewalFlag;
    }

    /**
     * Sets the value of the renewalFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewalFlag(String value) {
        this.renewalFlag = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the accountMissingInfoFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountMissingInfoFlg() {
        return accountMissingInfoFlg;
    }

    /**
     * Sets the value of the accountMissingInfoFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountMissingInfoFlg(String value) {
        this.accountMissingInfoFlg = value;
    }

    /**
     * Gets the value of the userPin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserPin() {
        return userPin;
    }

    /**
     * Sets the value of the userPin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserPin(String value) {
        this.userPin = value;
    }

    /**
     * Gets the value of the accountTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountTypeCd() {
        return accountTypeCd;
    }

    /**
     * Sets the value of the accountTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountTypeCd(String value) {
        this.accountTypeCd = value;
    }

    /**
     * Gets the value of the regdlntlSpnAffNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegdlntlSpnAffNo() {
        return regdlntlSpnAffNo;
    }

    /**
     * Sets the value of the regdlntlSpnAffNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegdlntlSpnAffNo(String value) {
        this.regdlntlSpnAffNo = value;
    }

    /**
     * Gets the value of the orderCreditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderCreditLimit() {
        return orderCreditLimit;
    }

    /**
     * Sets the value of the orderCreditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderCreditLimit(String value) {
        this.orderCreditLimit = value;
    }

    /**
     * Gets the value of the aboEntryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAboEntryDate() {
        return aboEntryDate;
    }

    /**
     * Sets the value of the aboEntryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAboEntryDate(String value) {
        this.aboEntryDate = value;
    }

}
