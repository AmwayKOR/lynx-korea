package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SearchPhone   {
  
  private String phoneLocalNum = null;
  private String phoneCntryCd = null;
  private String phoneAreaCd = null;
  private String phoneExtNum = null;
  private String statusCd = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneLocalNum")
  public String getPhoneLocalNum() {
    return phoneLocalNum;
  }
  public void setPhoneLocalNum(String phoneLocalNum) {
    this.phoneLocalNum = phoneLocalNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneCntryCd")
  public String getPhoneCntryCd() {
    return phoneCntryCd;
  }
  public void setPhoneCntryCd(String phoneCntryCd) {
    this.phoneCntryCd = phoneCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneAreaCd")
  public String getPhoneAreaCd() {
    return phoneAreaCd;
  }
  public void setPhoneAreaCd(String phoneAreaCd) {
    this.phoneAreaCd = phoneAreaCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneExtNum")
  public String getPhoneExtNum() {
    return phoneExtNum;
  }
  public void setPhoneExtNum(String phoneExtNum) {
    this.phoneExtNum = phoneExtNum;
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
    SearchPhone searchPhone = (SearchPhone) o;
    return Objects.equals(phoneLocalNum, searchPhone.phoneLocalNum) &&
        Objects.equals(phoneCntryCd, searchPhone.phoneCntryCd) &&
        Objects.equals(phoneAreaCd, searchPhone.phoneAreaCd) &&
        Objects.equals(phoneExtNum, searchPhone.phoneExtNum) &&
        Objects.equals(statusCd, searchPhone.statusCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneLocalNum, phoneCntryCd, phoneAreaCd, phoneExtNum, statusCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchPhone {\n");
    
    sb.append("    phoneLocalNum: ").append(toIndentedString(phoneLocalNum)).append("\n");
    sb.append("    phoneCntryCd: ").append(toIndentedString(phoneCntryCd)).append("\n");
    sb.append("    phoneAreaCd: ").append(toIndentedString(phoneAreaCd)).append("\n");
    sb.append("    phoneExtNum: ").append(toIndentedString(phoneExtNum)).append("\n");
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

