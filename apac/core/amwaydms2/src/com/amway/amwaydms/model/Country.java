package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Country   {
  
  private String cntryCd = null;
  private String cntryName = null;
  private String cntryDesc = null;
  private String amwayCntryCd = null;
  private String defaultCurrencyCd = null;
  private String defaultLanguageCd = null;
  private String cntryGroupCd = null;
  private String defaultGeoCd = null;
  private Integer salesPlanAff = null;
  private String dateFormat = null;
  private String addrStreet = null;
  private String addrLineTwo = null;
  private String addrLineThree = null;
  private String cityName = null;
  private String postalCd = null;
  private String stateCd = null;
  private Long cntryAliasAboNum = null;
  private Integer phoneCntryCd = null;
  private String cntryTimeZone = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryName")
  public String getCntryName() {
    return cntryName;
  }
  public void setCntryName(String cntryName) {
    this.cntryName = cntryName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryDesc")
  public String getCntryDesc() {
    return cntryDesc;
  }
  public void setCntryDesc(String cntryDesc) {
    this.cntryDesc = cntryDesc;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("amwayCntryCd")
  public String getAmwayCntryCd() {
    return amwayCntryCd;
  }
  public void setAmwayCntryCd(String amwayCntryCd) {
    this.amwayCntryCd = amwayCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("defaultCurrencyCd")
  public String getDefaultCurrencyCd() {
    return defaultCurrencyCd;
  }
  public void setDefaultCurrencyCd(String defaultCurrencyCd) {
    this.defaultCurrencyCd = defaultCurrencyCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("defaultLanguageCd")
  public String getDefaultLanguageCd() {
    return defaultLanguageCd;
  }
  public void setDefaultLanguageCd(String defaultLanguageCd) {
    this.defaultLanguageCd = defaultLanguageCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryGroupCd")
  public String getCntryGroupCd() {
    return cntryGroupCd;
  }
  public void setCntryGroupCd(String cntryGroupCd) {
    this.cntryGroupCd = cntryGroupCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("defaultGeoCd")
  public String getDefaultGeoCd() {
    return defaultGeoCd;
  }
  public void setDefaultGeoCd(String defaultGeoCd) {
    this.defaultGeoCd = defaultGeoCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanAff")
  public Integer getSalesPlanAff() {
    return salesPlanAff;
  }
  public void setSalesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("dateFormat")
  public String getDateFormat() {
    return dateFormat;
  }
  public void setDateFormat(String dateFormat) {
    this.dateFormat = dateFormat;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addrStreet")
  public String getAddrStreet() {
    return addrStreet;
  }
  public void setAddrStreet(String addrStreet) {
    this.addrStreet = addrStreet;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addrLineTwo")
  public String getAddrLineTwo() {
    return addrLineTwo;
  }
  public void setAddrLineTwo(String addrLineTwo) {
    this.addrLineTwo = addrLineTwo;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addrLineThree")
  public String getAddrLineThree() {
    return addrLineThree;
  }
  public void setAddrLineThree(String addrLineThree) {
    this.addrLineThree = addrLineThree;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cityName")
  public String getCityName() {
    return cityName;
  }
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("postalCd")
  public String getPostalCd() {
    return postalCd;
  }
  public void setPostalCd(String postalCd) {
    this.postalCd = postalCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("stateCd")
  public String getStateCd() {
    return stateCd;
  }
  public void setStateCd(String stateCd) {
    this.stateCd = stateCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryAliasAboNum")
  public Long getCntryAliasAboNum() {
    return cntryAliasAboNum;
  }
  public void setCntryAliasAboNum(Long cntryAliasAboNum) {
    this.cntryAliasAboNum = cntryAliasAboNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneCntryCd")
  public Integer getPhoneCntryCd() {
    return phoneCntryCd;
  }
  public void setPhoneCntryCd(Integer phoneCntryCd) {
    this.phoneCntryCd = phoneCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryTimeZone")
  public String getCntryTimeZone() {
    return cntryTimeZone;
  }
  public void setCntryTimeZone(String cntryTimeZone) {
    this.cntryTimeZone = cntryTimeZone;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Country country = (Country) o;
    return Objects.equals(cntryCd, country.cntryCd) &&
        Objects.equals(cntryName, country.cntryName) &&
        Objects.equals(cntryDesc, country.cntryDesc) &&
        Objects.equals(amwayCntryCd, country.amwayCntryCd) &&
        Objects.equals(defaultCurrencyCd, country.defaultCurrencyCd) &&
        Objects.equals(defaultLanguageCd, country.defaultLanguageCd) &&
        Objects.equals(cntryGroupCd, country.cntryGroupCd) &&
        Objects.equals(defaultGeoCd, country.defaultGeoCd) &&
        Objects.equals(salesPlanAff, country.salesPlanAff) &&
        Objects.equals(dateFormat, country.dateFormat) &&
        Objects.equals(addrStreet, country.addrStreet) &&
        Objects.equals(addrLineTwo, country.addrLineTwo) &&
        Objects.equals(addrLineThree, country.addrLineThree) &&
        Objects.equals(cityName, country.cityName) &&
        Objects.equals(postalCd, country.postalCd) &&
        Objects.equals(stateCd, country.stateCd) &&
        Objects.equals(cntryAliasAboNum, country.cntryAliasAboNum) &&
        Objects.equals(phoneCntryCd, country.phoneCntryCd) &&
        Objects.equals(cntryTimeZone, country.cntryTimeZone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cntryCd, cntryName, cntryDesc, amwayCntryCd, defaultCurrencyCd, defaultLanguageCd, cntryGroupCd, defaultGeoCd, salesPlanAff, dateFormat, addrStreet, addrLineTwo, addrLineThree, cityName, postalCd, stateCd, cntryAliasAboNum, phoneCntryCd, cntryTimeZone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Country {\n");
    
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    cntryName: ").append(toIndentedString(cntryName)).append("\n");
    sb.append("    cntryDesc: ").append(toIndentedString(cntryDesc)).append("\n");
    sb.append("    amwayCntryCd: ").append(toIndentedString(amwayCntryCd)).append("\n");
    sb.append("    defaultCurrencyCd: ").append(toIndentedString(defaultCurrencyCd)).append("\n");
    sb.append("    defaultLanguageCd: ").append(toIndentedString(defaultLanguageCd)).append("\n");
    sb.append("    cntryGroupCd: ").append(toIndentedString(cntryGroupCd)).append("\n");
    sb.append("    defaultGeoCd: ").append(toIndentedString(defaultGeoCd)).append("\n");
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    dateFormat: ").append(toIndentedString(dateFormat)).append("\n");
    sb.append("    addrStreet: ").append(toIndentedString(addrStreet)).append("\n");
    sb.append("    addrLineTwo: ").append(toIndentedString(addrLineTwo)).append("\n");
    sb.append("    addrLineThree: ").append(toIndentedString(addrLineThree)).append("\n");
    sb.append("    cityName: ").append(toIndentedString(cityName)).append("\n");
    sb.append("    postalCd: ").append(toIndentedString(postalCd)).append("\n");
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    cntryAliasAboNum: ").append(toIndentedString(cntryAliasAboNum)).append("\n");
    sb.append("    phoneCntryCd: ").append(toIndentedString(phoneCntryCd)).append("\n");
    sb.append("    cntryTimeZone: ").append(toIndentedString(cntryTimeZone)).append("\n");
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

