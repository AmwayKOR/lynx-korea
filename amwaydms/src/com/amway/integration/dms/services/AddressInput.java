
package com.amway.integration.dms.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addrStreet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addrLineTwo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contactPointTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="postalCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addrDeliveryTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usageList" type="{http://tempuri.org}AddressContactUsage" maxOccurs="unbounded"/>
 *         &lt;element name="addrLineThree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressValidatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validationResultOverRideFlg" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="tzOffSet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="geoCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warehouseCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactPointName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charSetCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deliveryInstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalBoxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addrLineFour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stateCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validationResultCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxJursidictionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="territory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressInput", propOrder = {
    "addrStreet",
    "cityName",
    "addrLineTwo",
    "contactPointTypeCd",
    "languageCd",
    "cntryCd",
    "postalCd",
    "addrDeliveryTypeCd",
    "usageList",
    "addrLineThree",
    "addressValidatedDate",
    "validationResultOverRideFlg",
    "tzOffSet",
    "latitude",
    "geoCd",
    "warehouseCd",
    "contactPointName",
    "charSetCd",
    "countyName",
    "longitude",
    "deliveryInstr",
    "contactId",
    "statusCd",
    "postalBoxNum",
    "addrLineFour",
    "stateCd",
    "validationResultCd",
    "taxJursidictionCd",
    "territory"
})
public class AddressInput {

    @XmlElement(required = true)
    protected String addrStreet;
    @XmlElement(required = true)
    protected String cityName;
    @XmlElement(required = true)
    protected String addrLineTwo;
    @XmlElement(required = true)
    protected String contactPointTypeCd;
    @XmlElement(required = true)
    protected String languageCd;
    @XmlElement(required = true)
    protected String cntryCd;
    @XmlElement(required = true)
    protected String postalCd;
    @XmlElement(required = true)
    protected String addrDeliveryTypeCd;
    @XmlElement(required = true)
    protected List<AddressContactUsage> usageList;
    protected String addrLineThree;
    protected String addressValidatedDate;
    protected Boolean validationResultOverRideFlg;
    protected String tzOffSet;
    protected String latitude;
    protected String geoCd;
    protected String warehouseCd;
    protected String contactPointName;
    protected String charSetCd;
    protected String countyName;
    protected String longitude;
    protected String deliveryInstr;
    protected String contactId;
    protected String statusCd;
    protected String postalBoxNum;
    protected String addrLineFour;
    protected String stateCd;
    protected String validationResultCd;
    protected String taxJursidictionCd;
    protected String territory;

    /**
     * Gets the value of the addrStreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrStreet() {
        return addrStreet;
    }

    /**
     * Sets the value of the addrStreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrStreet(String value) {
        this.addrStreet = value;
    }

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the addrLineTwo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrLineTwo() {
        return addrLineTwo;
    }

    /**
     * Sets the value of the addrLineTwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrLineTwo(String value) {
        this.addrLineTwo = value;
    }

    /**
     * Gets the value of the contactPointTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPointTypeCd() {
        return contactPointTypeCd;
    }

    /**
     * Sets the value of the contactPointTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPointTypeCd(String value) {
        this.contactPointTypeCd = value;
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
     * Gets the value of the postalCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCd() {
        return postalCd;
    }

    /**
     * Sets the value of the postalCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCd(String value) {
        this.postalCd = value;
    }

    /**
     * Gets the value of the addrDeliveryTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrDeliveryTypeCd() {
        return addrDeliveryTypeCd;
    }

    /**
     * Sets the value of the addrDeliveryTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrDeliveryTypeCd(String value) {
        this.addrDeliveryTypeCd = value;
    }

    /**
     * Gets the value of the usageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressContactUsage }
     * 
     * 
     */
    public List<AddressContactUsage> getUsageList() {
        if (usageList == null) {
            usageList = new ArrayList<AddressContactUsage>();
        }
        return this.usageList;
    }

    /**
     * Gets the value of the addrLineThree property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrLineThree() {
        return addrLineThree;
    }

    /**
     * Sets the value of the addrLineThree property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrLineThree(String value) {
        this.addrLineThree = value;
    }

    /**
     * Gets the value of the addressValidatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressValidatedDate() {
        return addressValidatedDate;
    }

    /**
     * Sets the value of the addressValidatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressValidatedDate(String value) {
        this.addressValidatedDate = value;
    }

    /**
     * Gets the value of the validationResultOverRideFlg property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isValidationResultOverRideFlg() {
        return validationResultOverRideFlg;
    }

    /**
     * Sets the value of the validationResultOverRideFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setValidationResultOverRideFlg(Boolean value) {
        this.validationResultOverRideFlg = value;
    }

    /**
     * Gets the value of the tzOffSet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTzOffSet() {
        return tzOffSet;
    }

    /**
     * Sets the value of the tzOffSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTzOffSet(String value) {
        this.tzOffSet = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitude(String value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the geoCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeoCd() {
        return geoCd;
    }

    /**
     * Sets the value of the geoCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeoCd(String value) {
        this.geoCd = value;
    }

    /**
     * Gets the value of the warehouseCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarehouseCd() {
        return warehouseCd;
    }

    /**
     * Sets the value of the warehouseCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarehouseCd(String value) {
        this.warehouseCd = value;
    }

    /**
     * Gets the value of the contactPointName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPointName() {
        return contactPointName;
    }

    /**
     * Sets the value of the contactPointName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPointName(String value) {
        this.contactPointName = value;
    }

    /**
     * Gets the value of the charSetCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharSetCd() {
        return charSetCd;
    }

    /**
     * Sets the value of the charSetCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharSetCd(String value) {
        this.charSetCd = value;
    }

    /**
     * Gets the value of the countyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * Sets the value of the countyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyName(String value) {
        this.countyName = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitude(String value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the deliveryInstr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryInstr() {
        return deliveryInstr;
    }

    /**
     * Sets the value of the deliveryInstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryInstr(String value) {
        this.deliveryInstr = value;
    }

    /**
     * Gets the value of the contactId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * Sets the value of the contactId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactId(String value) {
        this.contactId = value;
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
     * Gets the value of the postalBoxNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalBoxNum() {
        return postalBoxNum;
    }

    /**
     * Sets the value of the postalBoxNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalBoxNum(String value) {
        this.postalBoxNum = value;
    }

    /**
     * Gets the value of the addrLineFour property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrLineFour() {
        return addrLineFour;
    }

    /**
     * Sets the value of the addrLineFour property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrLineFour(String value) {
        this.addrLineFour = value;
    }

    /**
     * Gets the value of the stateCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateCd() {
        return stateCd;
    }

    /**
     * Sets the value of the stateCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateCd(String value) {
        this.stateCd = value;
    }

    /**
     * Gets the value of the validationResultCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationResultCd() {
        return validationResultCd;
    }

    /**
     * Sets the value of the validationResultCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationResultCd(String value) {
        this.validationResultCd = value;
    }

    /**
     * Gets the value of the taxJursidictionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxJursidictionCd() {
        return taxJursidictionCd;
    }

    /**
     * Sets the value of the taxJursidictionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxJursidictionCd(String value) {
        this.taxJursidictionCd = value;
    }

    /**
     * Gets the value of the territory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerritory() {
        return territory;
    }

    /**
     * Sets the value of the territory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerritory(String value) {
        this.territory = value;
    }

}
