
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accountFullResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accountFullResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aboEntryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aboExpireDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountCreditLimit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountCreditStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountMissingInfoFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSubTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blackListFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="busEntityNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeExcludeFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lateRenewalEligibleFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lglEntityType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loaCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderCreditLimit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regdSpnIboNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regdlntlPrmyBusflg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regdlntlSpnAffNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regdlntlSpnIboNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="segmentCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accountFullResponse", propOrder = {
    "aboEntryDate",
    "aboExpireDate",
    "aboNum",
    "accountCreditLimit",
    "accountCreditStatusCd",
    "accountId",
    "accountMissingInfoFlg",
    "accountName",
    "accountSubTypeCd",
    "accountTypeCd",
    "blackListFlg",
    "busEntityNum",
    "cntryCd",
    "company",
    "currencyCd",
    "includeExcludeFlg",
    "languageCd",
    "lateRenewalEligibleFlg",
    "lglEntityType",
    "loaCd",
    "orderCreditLimit",
    "regdSpnIboNo",
    "regdlntlPrmyBusflg",
    "regdlntlSpnAffNo",
    "regdlntlSpnIboNo",
    "salesPlanAff",
    "segmentCd",
    "statusCd",
    "userId",
    "userPin"
})
public class AccountFullResponse {

    @XmlElementRef(name = "aboEntryDate", type = JAXBElement.class, required = false)
    protected String aboEntryDate;
    @XmlElementRef(name = "aboExpireDate", type = JAXBElement.class, required = false)
    protected String aboExpireDate;
    @XmlElementRef(name = "aboNum", type = JAXBElement.class, required = false)
    protected String aboNum;
    @XmlElementRef(name = "accountCreditLimit", type = JAXBElement.class, required = false)
    protected String accountCreditLimit;
    @XmlElementRef(name = "accountCreditStatusCd", type = JAXBElement.class, required = false)
    protected String accountCreditStatusCd;
    @XmlElementRef(name = "accountId", type = JAXBElement.class, required = false)
    protected String accountId;
    @XmlElementRef(name = "accountMissingInfoFlg", type = JAXBElement.class, required = false)
    protected String accountMissingInfoFlg;
    @XmlElementRef(name = "accountName", type = JAXBElement.class, required = false)
    protected String accountName;
    @XmlElementRef(name = "accountSubTypeCd", type = JAXBElement.class, required = false)
    protected String accountSubTypeCd;
    @XmlElementRef(name = "accountTypeCd", type = JAXBElement.class, required = false)
    protected String accountTypeCd;
    @XmlElementRef(name = "blackListFlg", type = JAXBElement.class, required = false)
    protected String blackListFlg;
    @XmlElementRef(name = "busEntityNum", type = JAXBElement.class, required = false)
    protected String busEntityNum;
    @XmlElementRef(name = "cntryCd", type = JAXBElement.class, required = false)
    protected String cntryCd;
    @XmlElementRef(name = "company", type = JAXBElement.class, required = false)
    protected String company;
    @XmlElementRef(name = "currencyCd", type = JAXBElement.class, required = false)
    protected String currencyCd;
    @XmlElementRef(name = "includeExcludeFlg", type = JAXBElement.class, required = false)
    protected String includeExcludeFlg;
    @XmlElementRef(name = "languageCd", type = JAXBElement.class, required = false)
    protected String languageCd;
    @XmlElementRef(name = "lateRenewalEligibleFlg", type = JAXBElement.class, required = false)
    protected String lateRenewalEligibleFlg;
    @XmlElementRef(name = "lglEntityType", type = JAXBElement.class, required = false)
    protected String lglEntityType;
    @XmlElementRef(name = "loaCd", type = JAXBElement.class, required = false)
    protected String loaCd;
    @XmlElementRef(name = "orderCreditLimit", type = JAXBElement.class, required = false)
    protected String orderCreditLimit;
    @XmlElementRef(name = "regdSpnIboNo", type = JAXBElement.class, required = false)
    protected String regdSpnIboNo;
    @XmlElementRef(name = "regdlntlPrmyBusflg", type = JAXBElement.class, required = false)
    protected String regdlntlPrmyBusflg;
    @XmlElementRef(name = "regdlntlSpnAffNo", type = JAXBElement.class, required = false)
    protected String regdlntlSpnAffNo;
    @XmlElementRef(name = "regdlntlSpnIboNo", type = JAXBElement.class, required = false)
    protected String regdlntlSpnIboNo;
    @XmlElementRef(name = "salesPlanAff", type = JAXBElement.class, required = false)
    protected String salesPlanAff;
    @XmlElementRef(name = "segmentCd", type = JAXBElement.class, required = false)
    protected String segmentCd;
    @XmlElementRef(name = "statusCd", type = JAXBElement.class, required = false)
    protected String statusCd;
    @XmlElementRef(name = "userId", type = JAXBElement.class, required = false)
    protected String userId;
    @XmlElementRef(name = "userPin", type = JAXBElement.class, required = false)
    protected String userPin;

    /**
     * Gets the value of the aboEntryDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAboEntryDate(String value) {
        this.aboEntryDate = value;
    }

    /**
     * Gets the value of the aboExpireDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAboExpireDate(String value) {
        this.aboExpireDate = value;
    }

    /**
     * Gets the value of the aboNum property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAboNum(String value) {
        this.aboNum = value;
    }

    /**
     * Gets the value of the accountCreditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountCreditLimit(String value) {
        this.accountCreditLimit = value;
    }

    /**
     * Gets the value of the accountCreditStatusCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountCreditStatusCd(String value) {
        this.accountCreditStatusCd = value;
    }

    /**
     * Gets the value of the accountId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the value of the accountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountId(String value) {
        this.accountId = value;
    }

    /**
     * Gets the value of the accountMissingInfoFlg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountMissingInfoFlg(String value) {
        this.accountMissingInfoFlg = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the accountSubTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountSubTypeCd(String value) {
        this.accountSubTypeCd = value;
    }

    /**
     * Gets the value of the accountTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAccountTypeCd(String value) {
        this.accountTypeCd = value;
    }

    /**
     * Gets the value of the blackListFlg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBlackListFlg(String value) {
        this.blackListFlg = value;
    }

    /**
     * Gets the value of the busEntityNum property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBusEntityNum(String value) {
        this.busEntityNum = value;
    }

    /**
     * Gets the value of the cntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCntryCd(String value) {
        this.cntryCd = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCompany(String value) {
        this.company = value;
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
     * Gets the value of the includeExcludeFlg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIncludeExcludeFlg(String value) {
        this.includeExcludeFlg = value;
    }

    /**
     * Gets the value of the languageCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLanguageCd(String value) {
        this.languageCd = value;
    }

    /**
     * Gets the value of the lateRenewalEligibleFlg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLateRenewalEligibleFlg(String value) {
        this.lateRenewalEligibleFlg = value;
    }

    /**
     * Gets the value of the lglEntityType property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLglEntityType(String value) {
        this.lglEntityType = value;
    }

    /**
     * Gets the value of the loaCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLoaCd(String value) {
        this.loaCd = value;
    }

    /**
     * Gets the value of the orderCreditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrderCreditLimit(String value) {
        this.orderCreditLimit = value;
    }

    /**
     * Gets the value of the regdSpnIboNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRegdSpnIboNo(String value) {
        this.regdSpnIboNo = value;
    }

    /**
     * Gets the value of the regdlntlPrmyBusflg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRegdlntlPrmyBusflg(String value) {
        this.regdlntlPrmyBusflg = value;
    }

    /**
     * Gets the value of the regdlntlSpnAffNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRegdlntlSpnAffNo(String value) {
        this.regdlntlSpnAffNo = value;
    }

    /**
     * Gets the value of the regdlntlSpnIboNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRegdlntlSpnIboNo(String value) {
        this.regdlntlSpnIboNo = value;
    }

    /**
     * Gets the value of the salesPlanAff property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSalesPlanAff(String value) {
        this.salesPlanAff = value;
    }

    /**
     * Gets the value of the segmentCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSegmentCd(String value) {
        this.segmentCd = value;
    }

    /**
     * Gets the value of the statusCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatusCd(String value) {
        this.statusCd = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the userPin property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUserPin(String value) {
        this.userPin = value;
    }

}
