
package com.amway.integration.dms.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updatePartyAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updatePartyAddress">
 *   &lt;complexContent>
 *     &lt;extension base="{}contact">
 *       &lt;sequence>
 *         &lt;element name="aboNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addrDeliveryTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addrLineFour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addrLineThree" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addrLineTwo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addrStreet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressValidatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="charSetCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cntryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="geoCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalBoxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesPlanAff" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stateCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxJursidictionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tzOffSet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validationResultCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validationResultOverRideFlg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warehouseCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatePartyAddress", propOrder = {
    "aboNum",
    "addrDeliveryTypeCd",
    "addrLineFour",
    "addrLineThree",
    "addrLineTwo",
    "addrStreet",
    "addressValidatedDate",
    "charSetCd",
    "cityName",
    "cntryCd",
    "countyName",
    "geoCd",
    "languageCd",
    "latitude",
    "longitude",
    "postalBoxNum",
    "postalCd",
    "salesPlanAff",
    "stateCd",
    "taxJursidictionCd",
    "tzOffSet",
    "validationResultCd",
    "validationResultOverRideFlg",
    "warehouseCd"
})
public class UpdatePartyAddress
    extends Contact
{

    protected String aboNum;
    protected String addrDeliveryTypeCd;
    protected String addrLineFour;
    protected String addrLineThree;
    protected String addrLineTwo;
    protected String addrStreet;
    protected String addressValidatedDate;
    protected String charSetCd;
    protected String cityName;
    protected String cntryCd;
    protected String countyName;
    protected String geoCd;
    protected String languageCd;
    protected String latitude;
    protected String longitude;
    protected String postalBoxNum;
    protected String postalCd;
    protected String salesPlanAff;
    protected String stateCd;
    protected String taxJursidictionCd;
    protected String tzOffSet;
    protected String validationResultCd;
    protected String validationResultOverRideFlg;
    protected String warehouseCd;

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
     * Gets the value of the validationResultOverRideFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationResultOverRideFlg() {
        return validationResultOverRideFlg;
    }

    /**
     * Sets the value of the validationResultOverRideFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationResultOverRideFlg(String value) {
        this.validationResultOverRideFlg = value;
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

}
