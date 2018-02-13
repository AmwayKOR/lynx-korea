package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Region   {
  
  private String stateCd = null;
  private String stateNm = null;
  private String geoRgnCd = null;
  private String salesRgnCd = null;

  
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
  @JsonProperty("stateNm")
  public String getStateNm() {
    return stateNm;
  }
  public void setStateNm(String stateNm) {
    this.stateNm = stateNm;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("geoRgnCd")
  public String getGeoRgnCd() {
    return geoRgnCd;
  }
  public void setGeoRgnCd(String geoRgnCd) {
    this.geoRgnCd = geoRgnCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesRgnCd")
  public String getSalesRgnCd() {
    return salesRgnCd;
  }
  public void setSalesRgnCd(String salesRgnCd) {
    this.salesRgnCd = salesRgnCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Region region = (Region) o;
    return Objects.equals(stateCd, region.stateCd) &&
        Objects.equals(stateNm, region.stateNm) &&
        Objects.equals(geoRgnCd, region.geoRgnCd) &&
        Objects.equals(salesRgnCd, region.salesRgnCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateCd, stateNm, geoRgnCd, salesRgnCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Region {\n");
    
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    stateNm: ").append(toIndentedString(stateNm)).append("\n");
    sb.append("    geoRgnCd: ").append(toIndentedString(geoRgnCd)).append("\n");
    sb.append("    salesRgnCd: ").append(toIndentedString(salesRgnCd)).append("\n");
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

