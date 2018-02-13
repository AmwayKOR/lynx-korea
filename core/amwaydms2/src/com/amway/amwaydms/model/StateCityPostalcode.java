package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class StateCityPostalcode   {
  
  private String stateCd = null;
  private String cityName = null;
  private String fromPostalCd = null;
  private String toPostalCd = null;

  
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
  @JsonProperty("fromPostalCd")
  public String getFromPostalCd() {
    return fromPostalCd;
  }
  public void setFromPostalCd(String fromPostalCd) {
    this.fromPostalCd = fromPostalCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("toPostalCd")
  public String getToPostalCd() {
    return toPostalCd;
  }
  public void setToPostalCd(String toPostalCd) {
    this.toPostalCd = toPostalCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StateCityPostalcode stateCityPostalcode = (StateCityPostalcode) o;
    return Objects.equals(stateCd, stateCityPostalcode.stateCd) &&
        Objects.equals(cityName, stateCityPostalcode.cityName) &&
        Objects.equals(fromPostalCd, stateCityPostalcode.fromPostalCd) &&
        Objects.equals(toPostalCd, stateCityPostalcode.toPostalCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateCd, cityName, fromPostalCd, toPostalCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StateCityPostalcode {\n");
    
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    cityName: ").append(toIndentedString(cityName)).append("\n");
    sb.append("    fromPostalCd: ").append(toIndentedString(fromPostalCd)).append("\n");
    sb.append("    toPostalCd: ").append(toIndentedString(toPostalCd)).append("\n");
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

