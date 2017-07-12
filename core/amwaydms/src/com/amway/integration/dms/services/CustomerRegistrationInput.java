
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for customerRegistrationInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerRegistrationInput">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSubTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addrAttributes" type="{}addressAttributesInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="authorizeEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAttributes" type="{}accountAttributesInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractAcceptFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eCommAttributes" type="{}ecommAttributesInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="invoiceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lglEnttyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameAttributes" type="{}nameAttributesInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyAttributes" type="{}partyAttributesInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partyTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneAttributes" type="{}phoneAttributesInput" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="processTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saveRegestrationFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sponAttributes" type="{}sponsorAttributesInput" minOccurs="0"/>
 *         &lt;element name="stepNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxAttributes" type="{}partyTaxXrefInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerRegistrationInput", propOrder = {
    "aboNum",
    "accountSubTypeCd",
    "accountTypeCd",
    "addrAttributes",
    "authorizeEmail",
    "bankAttributes",
    "cntryCd",
    "contractAcceptFlg",
    "eCommAttributes",
    "invoiceNumber",
    "lglEnttyType",
    "nameAttributes",
    "partyAttributes",
    "partyTypeId",
    "phoneAttributes",
    "processTypeCd",
    "salesPlanAff",
    "saveRegestrationFlg",
    "sponAttributes",
    "stepNum",
    "taxAttributes"
})
public class CustomerRegistrationInput
    extends BaseWebServiceInput
{

    protected String aboNum;
    protected String accountSubTypeCd;
    protected String accountTypeCd;
    @XmlElement(nillable = true)
    protected List<AddressAttributesInput> addrAttributes;
    protected String authorizeEmail;
    @XmlElement(nillable = true)
    protected List<AccountAttributesInput> bankAttributes;
    protected String cntryCd;
    protected String contractAcceptFlg;
    @XmlElement(nillable = true)
    protected List<EcommAttributesInput> eCommAttributes;
    protected String invoiceNumber;
    protected String lglEnttyType;
    @XmlElement(nillable = true)
    protected List<NameAttributesInput> nameAttributes;
    @XmlElement(nillable = true)
    protected List<PartyAttributesInput> partyAttributes;
    protected String partyTypeId;
    @XmlElement(nillable = true)
    protected List<PhoneAttributesInput> phoneAttributes;
    protected String processTypeCd;
    protected String salesPlanAff;
    protected String saveRegestrationFlg;
    protected SponsorAttributesInput sponAttributes;
    protected String stepNum;
    protected PartyTaxXrefInput taxAttributes;

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
     * Gets the value of the addrAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addrAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddrAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressAttributesInput }
     * 
     * 
     */
    public List<AddressAttributesInput> getAddrAttributes() {
        if (addrAttributes == null) {
            addrAttributes = new ArrayList<AddressAttributesInput>();
        }
        return this.addrAttributes;
    }

    /**
     * Gets the value of the authorizeEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorizeEmail() {
        return authorizeEmail;
    }

    /**
     * Sets the value of the authorizeEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorizeEmail(String value) {
        this.authorizeEmail = value;
    }

    /**
     * Gets the value of the bankAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bankAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBankAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountAttributesInput }
     * 
     * 
     */
    public List<AccountAttributesInput> getBankAttributes() {
        if (bankAttributes == null) {
            bankAttributes = new ArrayList<AccountAttributesInput>();
        }
        return this.bankAttributes;
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
     * Gets the value of the contractAcceptFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractAcceptFlg() {
        return contractAcceptFlg;
    }

    /**
     * Sets the value of the contractAcceptFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractAcceptFlg(String value) {
        this.contractAcceptFlg = value;
    }

    /**
     * Gets the value of the eCommAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eCommAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getECommAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EcommAttributesInput }
     * 
     * 
     */
    public List<EcommAttributesInput> getECommAttributes() {
        if (eCommAttributes == null) {
            eCommAttributes = new ArrayList<EcommAttributesInput>();
        }
        return this.eCommAttributes;
    }

    /**
     * Gets the value of the invoiceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Sets the value of the invoiceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvoiceNumber(String value) {
        this.invoiceNumber = value;
    }

    /**
     * Gets the value of the lglEnttyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLglEnttyType() {
        return lglEnttyType;
    }

    /**
     * Sets the value of the lglEnttyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLglEnttyType(String value) {
        this.lglEnttyType = value;
    }

    /**
     * Gets the value of the nameAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nameAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNameAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameAttributesInput }
     * 
     * 
     */
    public List<NameAttributesInput> getNameAttributes() {
        if (nameAttributes == null) {
            nameAttributes = new ArrayList<NameAttributesInput>();
        }
        return this.nameAttributes;
    }

    /**
     * Gets the value of the partyAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyAttributesInput }
     * 
     * 
     */
    public List<PartyAttributesInput> getPartyAttributes() {
        if (partyAttributes == null) {
            partyAttributes = new ArrayList<PartyAttributesInput>();
        }
        return this.partyAttributes;
    }

    /**
     * Gets the value of the partyTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartyTypeId() {
        return partyTypeId;
    }

    /**
     * Sets the value of the partyTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyTypeId(String value) {
        this.partyTypeId = value;
    }

    /**
     * Gets the value of the phoneAttributes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneAttributes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneAttributes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneAttributesInput }
     * 
     * 
     */
    public List<PhoneAttributesInput> getPhoneAttributes() {
        if (phoneAttributes == null) {
            phoneAttributes = new ArrayList<PhoneAttributesInput>();
        }
        return this.phoneAttributes;
    }

    /**
     * Gets the value of the processTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessTypeCd() {
        return processTypeCd;
    }

    /**
     * Sets the value of the processTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessTypeCd(String value) {
        this.processTypeCd = value;
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
     * Gets the value of the saveRegestrationFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSaveRegestrationFlg() {
        return saveRegestrationFlg;
    }

    /**
     * Sets the value of the saveRegestrationFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSaveRegestrationFlg(String value) {
        this.saveRegestrationFlg = value;
    }

    /**
     * Gets the value of the sponAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link SponsorAttributesInput }
     *     
     */
    public SponsorAttributesInput getSponAttributes() {
        return sponAttributes;
    }

    /**
     * Sets the value of the sponAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link SponsorAttributesInput }
     *     
     */
    public void setSponAttributes(SponsorAttributesInput value) {
        this.sponAttributes = value;
    }

    /**
     * Gets the value of the stepNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStepNum() {
        return stepNum;
    }

    /**
     * Sets the value of the stepNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStepNum(String value) {
        this.stepNum = value;
    }

    /**
     * Gets the value of the taxAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link PartyTaxXrefInput }
     *     
     */
    public PartyTaxXrefInput getTaxAttributes() {
        return taxAttributes;
    }

    /**
     * Sets the value of the taxAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyTaxXrefInput }
     *     
     */
    public void setTaxAttributes(PartyTaxXrefInput value) {
        this.taxAttributes = value;
    }

}
