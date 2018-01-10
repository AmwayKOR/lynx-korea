
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * <p>Java class for partyMasterServiceObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partyMasterServiceObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="birthCountryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="czshpCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="educationTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ethnicCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genderCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maritalStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="natlCntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyIsMinorFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryOnAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="professionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationShipToPrimaryPartyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tzOffset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userPasswd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "partyMasterServiceObject", propOrder = {
    "birthCountryCd",
    "birthdate",
    "czshpCntryCd",
    "educationTypeCd",
    "ethnicCd",
    "genderCd",
    "languageCd",
    "maritalStatusCd",
    "natlCntryCd",
    "partyId",
    "partyIsMinorFlg",
    "partyTypeCd",
    "primaryOnAccount",
    "professionCd",
    "relationShipToPrimaryPartyCd",
    "roleCd",
    "statusCd",
    "tzOffset",
    "userId",
    "userPasswd",
    "userPin"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PartyMasterServiceObject {

    @XmlElementRef(name = "birthCountryCd", type = JAXBElement.class, required = false)
    protected String birthCountryCd;
    @XmlElementRef(name = "birthdate", type = JAXBElement.class, required = false)
    protected String birthdate;
    @XmlElementRef(name = "czshpCntryCd", type = JAXBElement.class, required = false)
    protected String czshpCntryCd;
    @XmlElementRef(name = "educationTypeCd", type = JAXBElement.class, required = false)
    protected String educationTypeCd;
    @XmlElementRef(name = "ethnicCd", type = JAXBElement.class, required = false)
    protected String ethnicCd;
    @XmlElementRef(name = "genderCd", type = JAXBElement.class, required = false)
    protected String genderCd;
    @XmlElementRef(name = "languageCd", type = JAXBElement.class, required = false)
    protected String languageCd;
    @XmlElementRef(name = "maritalStatusCd", type = JAXBElement.class, required = false)
    protected String maritalStatusCd;
    @XmlElementRef(name = "natlCntryCd", type = JAXBElement.class, required = false)
    protected String natlCntryCd;
    @XmlElementRef(name = "partyId", type = JAXBElement.class, required = false)
    protected String partyId;
    @XmlElementRef(name = "partyIsMinorFlg", type = JAXBElement.class, required = false)
    protected String partyIsMinorFlg;
    @XmlElementRef(name = "partyTypeCd", type = JAXBElement.class, required = false)
    protected String partyTypeCd;
    @XmlElementRef(name = "primaryOnAccount", type = JAXBElement.class, required = false)
    protected String primaryOnAccount;
    @XmlElementRef(name = "professionCd", type = JAXBElement.class, required = false)
    protected String professionCd;
    @XmlElementRef(name = "relationShipToPrimaryPartyCd", type = JAXBElement.class, required = false)
    protected String relationShipToPrimaryPartyCd;
    @XmlElementRef(name = "roleCd", type = JAXBElement.class, required = false)
    protected String roleCd;
    @XmlElementRef(name = "statusCd", type = JAXBElement.class, required = false)
    protected String statusCd;
    @XmlElementRef(name = "tzOffset", type = JAXBElement.class, required = false)
    protected String tzOffset;
    @XmlElementRef(name = "userId", type = JAXBElement.class, required = false)
    protected String userId;
    @XmlElementRef(name = "userPasswd", type = JAXBElement.class, required = false)
    protected String userPasswd;
    @XmlElementRef(name = "userPin", type = JAXBElement.class, required = false)
    protected String userPin;

    /**
     * Gets the value of the birthCountryCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBirthCountryCd(String value) {
        this.birthCountryCd = value;
    }

    /**
     * Gets the value of the birthdate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the value of the birthdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBirthdate(String value) {
        this.birthdate = value;
    }

    /**
     * Gets the value of the czshpCntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setGenderCd(String value) {
        this.genderCd = value;
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
     * Gets the value of the maritalStatusCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMaritalStatusCd(String value) {
        this.maritalStatusCd = value;
    }

    /**
     * Gets the value of the natlCntryCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNatlCntryCd(String value) {
        this.natlCntryCd = value;
    }

    /**
     * Gets the value of the partyId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * Sets the value of the partyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPartyId(String value) {
        this.partyId = value;
    }

    /**
     * Gets the value of the partyIsMinorFlg property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPartyTypeCd(String value) {
        this.partyTypeCd = value;
    }

    /**
     * Gets the value of the primaryOnAccount property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getPrimaryOnAccount() {
        return primaryOnAccount;
    }

    /**
     * Sets the value of the primaryOnAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPrimaryOnAccount(String value) {
        this.primaryOnAccount = value;
    }

    /**
     * Gets the value of the professionCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProfessionCd(String value) {
        this.professionCd = value;
    }

    /**
     * Gets the value of the relationShipToPrimaryPartyCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public String getRelationShipToPrimaryPartyCd() {
        return relationShipToPrimaryPartyCd;
    }

    /**
     * Sets the value of the relationShipToPrimaryPartyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRelationShipToPrimaryPartyCd(String value) {
        this.relationShipToPrimaryPartyCd = value;
    }

    /**
     * Gets the value of the roleCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoleCd(String value) {
        this.roleCd = value;
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
     * Gets the value of the tzOffset property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     * Gets the value of the userPasswd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
