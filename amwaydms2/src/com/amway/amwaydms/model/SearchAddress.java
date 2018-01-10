package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SearchAddress   {
  
  private String addrStreet = null;
  private String addrLineTwo = null;
  private String cityName = null;
  private String stateCd = null;
  private String postalCd = null;
  private String statusCd = null;
  private String territory = null;

  
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
  @JsonProperty("statusCd")
  public String getStatusCd() {
    return statusCd;
  }
  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("territory")
  public String getTerritory() {
    return territory;
  }
  public void setTerritory(String territory) {
    this.territory = territory;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchAddress searchAddress = (SearchAddress) o;
    return Objects.equals(addrStreet, searchAddress.addrStreet) &&
        Objects.equals(addrLineTwo, searchAddress.addrLineTwo) &&
        Objects.equals(cityName, searchAddress.cityName) &&
        Objects.equals(stateCd, searchAddress.stateCd) &&
        Objects.equals(postalCd, searchAddress.postalCd) &&
        Objects.equals(statusCd, searchAddress.statusCd) &&
        Objects.equals(territory, searchAddress.territory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addrStreet, addrLineTwo, cityName, stateCd, postalCd, statusCd, territory);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchAddress {\n");
    
    sb.append("    addrStreet: ").append(toIndentedString(addrStreet)).append("\n");
    sb.append("    addrLineTwo: ").append(toIndentedString(addrLineTwo)).append("\n");
    sb.append("    cityName: ").append(toIndentedString(cityName)).append("\n");
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    postalCd: ").append(toIndentedString(postalCd)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    territory: ").append(toIndentedString(territory)).append("\n");
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

