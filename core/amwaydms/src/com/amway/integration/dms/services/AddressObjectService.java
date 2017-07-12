
package com.amway.integration.dms.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addressObjectService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addressObjectService">
 *   &lt;complexContent>
 *     &lt;extension base="{}contact">
 *       &lt;sequence>
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
 *         &lt;element name="deliveryInstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="geoCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalBoxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stateCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxJursidictionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tzOffSet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "addressObjectService", propOrder = {
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
    "deliveryInstr",
    "geoCd",
    "languageCd",
    "latitude",
    "longitude",
    "postalBoxNum",
    "postalCd",
    "stateCd",
    "statusCd",
    "taxJursidictionCd",
    "tzOffSet",
    "warehouseCd"
})
public class AddressObjectService
    extends Contact
{

    @XmlElementRef(name = "addrDeliveryTypeCd", type = JAXBElement.class, required = false)
    protected String addrDeliveryTypeCd;
    @XmlElementRef(name = "addrLineFour", type = JAXBElement.class, required = false)
    protected String addrLineFour;
    @XmlElementRef(name = "addrLineThree", type = JAXBElement.class, required = false)
    protected String addrLineThree;
    @XmlElementRef(name = "addrLineTwo", type = JAXBElement.class, required = false)
    protected String addrLineTwo;
    @XmlElementRef(name = "addrStreet", type = JAXBElement.class, required = false)
    protected String addrStreet;
    @XmlElementRef(name = "addressValidatedDate", type = JAXBElement.class, required = false)
    protected String addressValidatedDate;
    @XmlElementRef(name = "charSetCd", type = JAXBElement.class, required = false)
    protected String charSetCd;
    @XmlElementRef(name = "cityName", type = JAXBElement.class, required = false)
    protected String cityName;
    @XmlElementRef(name = "cntryCd", type = JAXBElement.class, required = false)
    protected String cntryCd;
    @XmlElementRef(name = "countyName", type = JAXBElement.class, required = false)
    protected String countyName;
    @XmlElementRef(name = "deliveryInstr", type = JAXBElement.class, required = false)
    protected String deliveryInstr;
    @XmlElementRef(name = "geoCd", type = JAXBElement.class, required = false)
    protected String geoCd;
    @XmlElementRef(name = "languageCd", type = JAXBElement.class, required = false)
    protected String languageCd;
    @XmlElementRef(name = "latitude", type = JAXBElement.class, required = false)
    protected String latitude;
    @XmlElementRef(name = "longitude", type = JAXBElement.class, required = false)
    protected String longitude;
    @XmlElementRef(name = "postalBoxNum", type = JAXBElement.class, required = false)
    protected String postalBoxNum;
    @XmlElementRef(name = "postalCd", type = JAXBElement.class, required = false)
    protected String postalCd;
    @XmlElementRef(name = "stateCd", type = JAXBElement.class, required = false)
    protected String stateCd;
    @XmlElementRef(name = "statusCd", type = JAXBElement.class, required = false)
    protected String statusCd;
    @XmlElementRef(name = "taxJursidictionCd", type = JAXBElement.class, required = false)
    protected String taxJursidictionCd;
    @XmlElementRef(name = "tzOffSet", type = JAXBElement.class, required = false)
    protected String tzOffSet;
    @XmlElementRef(name = "warehouseCd", type = JAXBElement.class, required = false)
    protected String warehouseCd;

    /**
     * Gets the value of the addrDeliveryTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     * Gets the value of the countyName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCountyName(String value) {
        this.countyName = value;
    }

    /**
     * Gets the value of the deliveryInstr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeliveryInstr(String value) {
        this.deliveryInstr = value;
    }

    /**
     * Gets the value of the geoCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPostalCd(String value) {
        this.postalCd = value;
    }

    /**
     * Gets the value of the stateCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStateCd(String value) {
        this.stateCd = value;
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
     * Gets the value of the taxJursidictionCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTzOffSet(String value) {
        this.tzOffSet = value;
    }

    /**
     * Gets the value of the warehouseCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
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
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWarehouseCd(String value) {
        this.warehouseCd = value;
    }

}
