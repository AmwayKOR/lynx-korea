package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.StateCityPostalcode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class CountryStateCity   {
  
  private String cntryCd = null;
  private List<StateCityPostalcode> stateCityPostalList = new ArrayList<StateCityPostalcode>();

  
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
  @JsonProperty("stateCityPostalList")
  public List<StateCityPostalcode> getStateCityPostalList() {
    return stateCityPostalList;
  }
  public void setStateCityPostalList(List<StateCityPostalcode> stateCityPostalList) {
    this.stateCityPostalList = stateCityPostalList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CountryStateCity countryStateCity = (CountryStateCity) o;
    return Objects.equals(cntryCd, countryStateCity.cntryCd) &&
        Objects.equals(stateCityPostalList, countryStateCity.stateCityPostalList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cntryCd, stateCityPostalList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CountryStateCity {\n");
    
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    stateCityPostalList: ").append(toIndentedString(stateCityPostalList)).append("\n");
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

