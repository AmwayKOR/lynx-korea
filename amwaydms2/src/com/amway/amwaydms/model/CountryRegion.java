package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Region;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class CountryRegion   {
  
  private String cntryCd = null;
  private List<Region> regionList = new ArrayList<Region>();

  
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
  @JsonProperty("regionList")
  public List<Region> getRegionList() {
    return regionList;
  }
  public void setRegionList(List<Region> regionList) {
    this.regionList = regionList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CountryRegion countryRegion = (CountryRegion) o;
    return Objects.equals(cntryCd, countryRegion.cntryCd) &&
        Objects.equals(regionList, countryRegion.regionList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cntryCd, regionList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CountryRegion {\n");
    
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    regionList: ").append(toIndentedString(regionList)).append("\n");
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

