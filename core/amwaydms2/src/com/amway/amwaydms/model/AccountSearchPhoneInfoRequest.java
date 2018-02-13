package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountSearchPhoneInfoRequest   {
  
  private String phoneLocalNum = null;
  private String phoneCntryCd = null;
  private String phoneAreaCd = null;

  
  /**
   * phone local number
   **/
  
  @ApiModelProperty(required = true, value = "phone local number")
  @JsonProperty("phoneLocalNum")
  public String getPhoneLocalNum() {
    return phoneLocalNum;
  }
  public void setPhoneLocalNum(String phoneLocalNum) {
    this.phoneLocalNum = phoneLocalNum;
  }

  
  /**
   * phone country code
   **/
  
  @ApiModelProperty(value = "phone country code")
  @JsonProperty("phoneCntryCd")
  public String getPhoneCntryCd() {
    return phoneCntryCd;
  }
  public void setPhoneCntryCd(String phoneCntryCd) {
    this.phoneCntryCd = phoneCntryCd;
  }

  
  /**
   * phone area code
   **/
  
  @ApiModelProperty(value = "phone area code")
  @JsonProperty("phoneAreaCd")
  public String getPhoneAreaCd() {
    return phoneAreaCd;
  }
  public void setPhoneAreaCd(String phoneAreaCd) {
    this.phoneAreaCd = phoneAreaCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountSearchPhoneInfoRequest accountSearchPhoneInfoRequest = (AccountSearchPhoneInfoRequest) o;
    return Objects.equals(phoneLocalNum, accountSearchPhoneInfoRequest.phoneLocalNum) &&
        Objects.equals(phoneCntryCd, accountSearchPhoneInfoRequest.phoneCntryCd) &&
        Objects.equals(phoneAreaCd, accountSearchPhoneInfoRequest.phoneAreaCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneLocalNum, phoneCntryCd, phoneAreaCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountSearchPhoneInfoRequest {\n");
    
    sb.append("    phoneLocalNum: ").append(toIndentedString(phoneLocalNum)).append("\n");
    sb.append("    phoneCntryCd: ").append(toIndentedString(phoneCntryCd)).append("\n");
    sb.append("    phoneAreaCd: ").append(toIndentedString(phoneAreaCd)).append("\n");
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

