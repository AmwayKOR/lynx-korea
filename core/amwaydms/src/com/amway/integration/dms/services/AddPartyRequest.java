
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addPartyRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addPartyRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{}baseWebServiceInput">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addTaxIdList" type="{}taxIdDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="birthCountryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="czshpCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="educationTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ethnicCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genderCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maritalStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{}addPartyName" minOccurs="0"/>
 *         &lt;element name="natlCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyIsMinorFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="professionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationShipToPrimaryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tzOffset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPasswd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addPartyRequest", propOrder = {
    "aboNum",
    "addTaxIdList",
    "birthCountryCd",
    "birthDate",
    "czshpCntryCd",
    "educationTypeCd",
    "ethnicCd",
    "genderCd",
    "maritalStatusCd",
    "name",
    "natlCntryCd",
    "partyIsMinorFlg",
    "partyTypeCd",
    "professionCd",
    "relationShipToPrimaryCd",
    "roleCd",
    "salesPlanAff",
    "tzOffset",
    "userId",
    "userPasswd",
    "userPin"
})
public class AddPartyRequest
    extends BaseWebServiceInput
{

    protected String aboNum;
    @XmlElement(nillable = true)
    protected List<TaxIdDetail> addTaxIdList;
    protected String birthCountryCd;
    protected String birthDate;
    protected String czshpCntryCd;
    protected String educationTypeCd;
    protected String ethnicCd;
    protected String genderCd;
    protected String maritalStatusCd;
    protected AddPartyName name;
    protected String natlCntryCd;
    protected String partyIsMinorFlg;
    protected String partyTypeCd;
    protected String professionCd;
    protected String relationShipToPrimaryCd;
    protected String roleCd;
    protected String salesPlanAff;
    protected String tzOffset;
    protected String userId;
    protected String userPasswd;
    protected String userPin;

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
     * Gets the value of the addTaxIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addTaxIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddTaxIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaxIdDetail }
     * 
     * 
     */
    public List<TaxIdDetail> getAddTaxIdList() {
        if (addTaxIdList == null) {
            addTaxIdList = new ArrayList<TaxIdDetail>();
        }
        return this.addTaxIdList;
    }

    /**
     * Gets the value of the birthCountryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthCountryCd() {
        return birthCountryCd;
    }

    /**
     * Sets the value of the birthCountryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthCountryCd(String value) {
        this.birthCountryCd = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the czshpCntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCzshpCntryCd() {
        return czshpCntryCd;
    }

    /**
     * Sets the value of the czshpCntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCzshpCntryCd(String value) {
        this.czshpCntryCd = value;
    }

    /**
     * Gets the value of the educationTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEducationTypeCd() {
        return educationTypeCd;
    }

    /**
     * Sets the value of the educationTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEducationTypeCd(String value) {
        this.educationTypeCd = value;
    }

    /**
     * Gets the value of the ethnicCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEthnicCd() {
        return ethnicCd;
    }

    /**
     * Sets the value of the ethnicCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEthnicCd(String value) {
        this.ethnicCd = value;
    }

    /**
     * Gets the value of the genderCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenderCd() {
        return genderCd;
    }

    /**
     * Sets the value of the genderCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenderCd(String value) {
        this.genderCd = value;
    }

    /**
     * Gets the value of the maritalStatusCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritalStatusCd() {
        return maritalStatusCd;
    }

    /**
     * Sets the value of the maritalStatusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritalStatusCd(String value) {
        this.maritalStatusCd = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link AddPartyName }
     *     
     */
    public AddPartyName getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddPartyName }
     *     
     */
    public void setName(AddPartyName value) {
        this.name = value;
    }

    /**
     * Gets the value of the natlCntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNatlCntryCd() {
        return natlCntryCd;
    }

    /**
     * Sets the value of the natlCntryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNatlCntryCd(String value) {
        this.natlCntryCd = value;
    }

    /**
     * Gets the value of the partyIsMinorFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartyIsMinorFlg() {
        return partyIsMinorFlg;
    }

    /**
     * Sets the value of the partyIsMinorFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyIsMinorFlg(String value) {
        this.partyIsMinorFlg = value;
    }

    /**
     * Gets the value of the partyTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartyTypeCd() {
        return partyTypeCd;
    }

    /**
     * Sets the value of the partyTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyTypeCd(String value) {
        this.partyTypeCd = value;
    }

    /**
     * Gets the value of the professionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfessionCd() {
        return professionCd;
    }

    /**
     * Sets the value of the professionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfessionCd(String value) {
        this.professionCd = value;
    }

    /**
     * Gets the value of the relationShipToPrimaryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationShipToPrimaryCd() {
        return relationShipToPrimaryCd;
    }

    /**
     * Sets the value of the relationShipToPrimaryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationShipToPrimaryCd(String value) {
        this.relationShipToPrimaryCd = value;
    }

    /**
     * Gets the value of the roleCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleCd() {
        return roleCd;
    }

    /**
     * Sets the value of the roleCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleCd(String value) {
        this.roleCd = value;
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
     * Gets the value of the tzOffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTzOffset() {
        return tzOffset;
    }

    /**
     * Sets the value of the tzOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTzOffset(String value) {
        this.tzOffset = value;
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
     * Gets the value of the userPasswd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserPasswd() {
        return userPasswd;
    }

    /**
     * Sets the value of the userPasswd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserPasswd(String value) {
        this.userPasswd = value;
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

}
