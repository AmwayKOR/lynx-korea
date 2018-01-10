package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SearchEcomm   {
  
  private String ecommAddr = null;
  private String statusCd = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ecommAddr")
  public String getEcommAddr() {
    return ecommAddr;
  }
  public void setEcommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchEcomm searchEcomm = (SearchEcomm) o;
    return Objects.equals(ecommAddr, searchEcomm.ecommAddr) &&
        Objects.equals(statusCd, searchEcomm.statusCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ecommAddr, statusCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchEcomm {\n");
    
    sb.append("    ecommAddr: ").append(toIndentedString(ecommAddr)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
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

