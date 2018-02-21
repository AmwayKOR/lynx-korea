package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AddressContactUsage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AddressInput   {
  
  private String contactId = null;
  private String contactPointTypeCd = null;
  private String contactPointName = null;
  private List<AddressContactUsage> usageList = new ArrayList<AddressContactUsage>();
  private String addrStreet = null;
  private String addrLineTwo = null;
  private String addrLineThree = null;
  private String addrLineFour = null;
  private String statusCd = null;
  private String cityName = null;
  private String cntryCd = null;
  private String countyName = null;
  private String addrDeliveryTypeCd = null;
  private String taxJursidictionCd = null;
  private String geoCd = null;
  private String latitude = null;
  private String longitude = null;
  private String postalBoxNum = null;
  private String postalCd = null;
  private String stateCd = null;
  private String warehouseCd = null;
  private String tzOffSet = null;
  private String languageCd = null;
  private String charSetCd = null;
  private String deliveryInstr = null;
  private String territory = null;
  private String addressValidatedDate = null;
  private Boolean validationResultOverRideFlg = null;
  private String validationResultCd = null;

  
  /**
   * external address Id
   **/
  
  @ApiModelProperty(value = "external address Id")
  @JsonProperty("contactId")
  public String getContactId() {
    return contactId;
  }
  public void setContactId(String contactId) {
    this.contactId = contactId;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=COPTY'>Reference to contact point type codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=COPTY'>Reference to contact point type codes</a>")
  @JsonProperty("contactPointTypeCd")
  public String getContactPointTypeCd() {
    return contactPointTypeCd;
  }
  public void setContactPointTypeCd(String contactPointTypeCd) {
    this.contactPointTypeCd = contactPointTypeCd;
  }

  
  /**
   * Nick name
   **/
  
  @ApiModelProperty(value = "Nick name")
  @JsonProperty("contactPointName")
  public String getContactPointName() {
    return contactPointName;
  }
  public void setContactPointName(String contactPointName) {
    this.contactPointName = contactPointName;
  }

  
  /**
   * usageList
   **/
  
  @ApiModelProperty(required = true, value = "usageList")
  @JsonProperty("usageList")
  public List<AddressContactUsage> getUsageList() {
    return usageList;
  }
  public void setUsageList(List<AddressContactUsage> usageList) {
    this.usageList = usageList;
  }

  
  /**
   * address line1
   **/
  
  @ApiModelProperty(required = true, value = "address line1")
  @JsonProperty("addrStreet")
  public String getAddrStreet() {
    return addrStreet;
  }
  public void setAddrStreet(String addrStreet) {
    this.addrStreet = addrStreet;
  }

  
  /**
   * address line2
   **/
  
  @ApiModelProperty(required = true, value = "address line2")
  @JsonProperty("addrLineTwo")
  public String getAddrLineTwo() {
    return addrLineTwo;
  }
  public void setAddrLineTwo(String addrLineTwo) {
    this.addrLineTwo = addrLineTwo;
  }

  
  /**
   * address line3
   **/
  
  @ApiModelProperty(value = "address line3")
  @JsonProperty("addrLineThree")
  public String getAddrLineThree() {
    return addrLineThree;
  }
  public void setAddrLineThree(String addrLineThree) {
    this.addrLineThree = addrLineThree;
  }

  
  /**
   * address line4
   **/
  
  @ApiModelProperty(value = "address line4")
  @JsonProperty("addrLineFour")
  public String getAddrLineFour() {
    return addrLineFour;
  }
  public void setAddrLineFour(String addrLineFour) {
    this.addrLineFour = addrLineFour;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ADSCD'>Reference to address status codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ADSCD'>Reference to address status codes</a>")
  @JsonProperty("statusCd")
  public String getStatusCd() {
    return statusCd;
  }
  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }

  
  /**
   * City Name
   **/
  
  @ApiModelProperty(required = true, value = "City Name")
  @JsonProperty("cityName")
  public String getCityName() {
    return cityName;
  }
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  
  /**
   * Country Code
   **/
  
  @ApiModelProperty(required = true, value = "Country Code")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  
  /**
   * County Name
   **/
  
  @ApiModelProperty(value = "County Name")
  @JsonProperty("countyName")
  public String getCountyName() {
    return countyName;
  }
  public void setCountyName(String countyName) {
    this.countyName = countyName;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ADDTC'>Reference to address delivery type codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ADDTC'>Reference to address delivery type codes</a>")
  @JsonProperty("addrDeliveryTypeCd")
  public String getAddrDeliveryTypeCd() {
    return addrDeliveryTypeCd;
  }
  public void setAddrDeliveryTypeCd(String addrDeliveryTypeCd) {
    this.addrDeliveryTypeCd = addrDeliveryTypeCd;
  }

  
  /**
   * Tax Juridiction code
   **/
  
  @ApiModelProperty(value = "Tax Juridiction code")
  @JsonProperty("taxJursidictionCd")
  public String getTaxJursidictionCd() {
    return taxJursidictionCd;
  }
  public void setTaxJursidictionCd(String taxJursidictionCd) {
    this.taxJursidictionCd = taxJursidictionCd;
  }

  
  /**
   * Geo code
   **/
  
  @ApiModelProperty(value = "Geo code")
  @JsonProperty("geoCd")
  public String getGeoCd() {
    return geoCd;
  }
  public void setGeoCd(String geoCd) {
    this.geoCd = geoCd;
  }

  
  /**
   * Latitude
   **/
  
  @ApiModelProperty(value = "Latitude")
  @JsonProperty("latitude")
  public String getLatitude() {
    return latitude;
  }
  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  
  /**
   * Longitude
   **/
  
  @ApiModelProperty(value = "Longitude")
  @JsonProperty("longitude")
  public String getLongitude() {
    return longitude;
  }
  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  
  /**
   * Postal Box Number
   **/
  
  @ApiModelProperty(value = "Postal Box Number")
  @JsonProperty("postalBoxNum")
  public String getPostalBoxNum() {
    return postalBoxNum;
  }
  public void setPostalBoxNum(String postalBoxNum) {
    this.postalBoxNum = postalBoxNum;
  }

  
  /**
   * Postal Code
   **/
  
  @ApiModelProperty(required = true, value = "Postal Code")
  @JsonProperty("postalCd")
  public String getPostalCd() {
    return postalCd;
  }
  public void setPostalCd(String postalCd) {
    this.postalCd = postalCd;
  }

  
  /**
   * State code
   **/
  
  @ApiModelProperty(value = "State code")
  @JsonProperty("stateCd")
  public String getStateCd() {
    return stateCd;
  }
  public void setStateCd(String stateCd) {
    this.stateCd = stateCd;
  }

  
  /**
   * Warehouse code
   **/
  
  @ApiModelProperty(value = "Warehouse code")
  @JsonProperty("warehouseCd")
  public String getWarehouseCd() {
    return warehouseCd;
  }
  public void setWarehouseCd(String warehouseCd) {
    this.warehouseCd = warehouseCd;
  }

  
  /**
   * Timezone offset
   **/
  
  @ApiModelProperty(value = "Timezone offset")
  @JsonProperty("tzOffSet")
  public String getTzOffSet() {
    return tzOffSet;
  }
  public void setTzOffSet(String tzOffSet) {
    this.tzOffSet = tzOffSet;
  }

  
  /**
   * ISO Language code
   **/
  
  @ApiModelProperty(required = true, value = "ISO Language code")
  @JsonProperty("languageCd")
  public String getLanguageCd() {
    return languageCd;
  }
  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
  }

  
  /**
   * Character set code
   **/
  
  @ApiModelProperty(value = "Character set code")
  @JsonProperty("charSetCd")
  public String getCharSetCd() {
    return charSetCd;
  }
  public void setCharSetCd(String charSetCd) {
    this.charSetCd = charSetCd;
  }

  
  /**
   * Delivery instruction
   **/
  
  @ApiModelProperty(value = "Delivery instruction")
  @JsonProperty("deliveryInstr")
  public String getDeliveryInstr() {
    return deliveryInstr;
  }
  public void setDeliveryInstr(String deliveryInstr) {
    this.deliveryInstr = deliveryInstr;
  }

  
  /**
   * Territory
   **/
  
  @ApiModelProperty(value = "Territory")
  @JsonProperty("territory")
  public String getTerritory() {
    return territory;
  }
  public void setTerritory(String territory) {
    this.territory = territory;
  }

  
  /**
   * Address validation date in UTC format
   **/
  
  @ApiModelProperty(value = "Address validation date in UTC format")
  @JsonProperty("addressValidatedDate")
  public String getAddressValidatedDate() {
    return addressValidatedDate;
  }
  public void setAddressValidatedDate(String addressValidatedDate) {
    this.addressValidatedDate = addressValidatedDate;
  }

  
  /**
   * Address validation override flag
   **/
  
  @ApiModelProperty(value = "Address validation override flag")
  @JsonProperty("validationResultOverRideFlg")
  public Boolean getValidationResultOverRideFlg() {
    return validationResultOverRideFlg;
  }
  public void setValidationResultOverRideFlg(Boolean validationResultOverRideFlg) {
    this.validationResultOverRideFlg = validationResultOverRideFlg;
  }

  
  /**
   * Address validation result code
   **/
  
  @ApiModelProperty(value = "Address validation result code")
  @JsonProperty("validationResultCd")
  public String getValidationResultCd() {
    return validationResultCd;
  }
  public void setValidationResultCd(String validationResultCd) {
    this.validationResultCd = validationResultCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressInput addressInput = (AddressInput) o;
    return Objects.equals(contactId, addressInput.contactId) &&
        Objects.equals(contactPointTypeCd, addressInput.contactPointTypeCd) &&
        Objects.equals(contactPointName, addressInput.contactPointName) &&
        Objects.equals(usageList, addressInput.usageList) &&
        Objects.equals(addrStreet, addressInput.addrStreet) &&
        Objects.equals(addrLineTwo, addressInput.addrLineTwo) &&
        Objects.equals(addrLineThree, addressInput.addrLineThree) &&
        Objects.equals(addrLineFour, addressInput.addrLineFour) &&
        Objects.equals(statusCd, addressInput.statusCd) &&
        Objects.equals(cityName, addressInput.cityName) &&
        Objects.equals(cntryCd, addressInput.cntryCd) &&
        Objects.equals(countyName, addressInput.countyName) &&
        Objects.equals(addrDeliveryTypeCd, addressInput.addrDeliveryTypeCd) &&
        Objects.equals(taxJursidictionCd, addressInput.taxJursidictionCd) &&
        Objects.equals(geoCd, addressInput.geoCd) &&
        Objects.equals(latitude, addressInput.latitude) &&
        Objects.equals(longitude, addressInput.longitude) &&
        Objects.equals(postalBoxNum, addressInput.postalBoxNum) &&
        Objects.equals(postalCd, addressInput.postalCd) &&
        Objects.equals(stateCd, addressInput.stateCd) &&
        Objects.equals(warehouseCd, addressInput.warehouseCd) &&
        Objects.equals(tzOffSet, addressInput.tzOffSet) &&
        Objects.equals(languageCd, addressInput.languageCd) &&
        Objects.equals(charSetCd, addressInput.charSetCd) &&
        Objects.equals(deliveryInstr, addressInput.deliveryInstr) &&
        Objects.equals(territory, addressInput.territory) &&
        Objects.equals(addressValidatedDate, addressInput.addressValidatedDate) &&
        Objects.equals(validationResultOverRideFlg, addressInput.validationResultOverRideFlg) &&
        Objects.equals(validationResultCd, addressInput.validationResultCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, contactPointTypeCd, contactPointName, usageList, addrStreet, addrLineTwo, addrLineThree, addrLineFour, statusCd, cityName, cntryCd, countyName, addrDeliveryTypeCd, taxJursidictionCd, geoCd, latitude, longitude, postalBoxNum, postalCd, stateCd, warehouseCd, tzOffSet, languageCd, charSetCd, deliveryInstr, territory, addressValidatedDate, validationResultOverRideFlg, validationResultCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressInput {\n");
    
    sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
    sb.append("    contactPointTypeCd: ").append(toIndentedString(contactPointTypeCd)).append("\n");
    sb.append("    contactPointName: ").append(toIndentedString(contactPointName)).append("\n");
    sb.append("    usageList: ").append(toIndentedString(usageList)).append("\n");
    sb.append("    addrStreet: ").append(toIndentedString(addrStreet)).append("\n");
    sb.append("    addrLineTwo: ").append(toIndentedString(addrLineTwo)).append("\n");
    sb.append("    addrLineThree: ").append(toIndentedString(addrLineThree)).append("\n");
    sb.append("    addrLineFour: ").append(toIndentedString(addrLineFour)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    cityName: ").append(toIndentedString(cityName)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    countyName: ").append(toIndentedString(countyName)).append("\n");
    sb.append("    addrDeliveryTypeCd: ").append(toIndentedString(addrDeliveryTypeCd)).append("\n");
    sb.append("    taxJursidictionCd: ").append(toIndentedString(taxJursidictionCd)).append("\n");
    sb.append("    geoCd: ").append(toIndentedString(geoCd)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    postalBoxNum: ").append(toIndentedString(postalBoxNum)).append("\n");
    sb.append("    postalCd: ").append(toIndentedString(postalCd)).append("\n");
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    warehouseCd: ").append(toIndentedString(warehouseCd)).append("\n");
    sb.append("    tzOffSet: ").append(toIndentedString(tzOffSet)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    charSetCd: ").append(toIndentedString(charSetCd)).append("\n");
    sb.append("    deliveryInstr: ").append(toIndentedString(deliveryInstr)).append("\n");
    sb.append("    territory: ").append(toIndentedString(territory)).append("\n");
    sb.append("    addressValidatedDate: ").append(toIndentedString(addressValidatedDate)).append("\n");
    sb.append("    validationResultOverRideFlg: ").append(toIndentedString(validationResultOverRideFlg)).append("\n");
    sb.append("    validationResultCd: ").append(toIndentedString(validationResultCd)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

