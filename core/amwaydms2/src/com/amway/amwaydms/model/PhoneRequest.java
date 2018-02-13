package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.PhoneInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PhoneRequest   {
  
  private PhoneInput phone = null;
  private Boolean applyToAllLinkedAccountFlag = null;

  
  /**
   * Phone Information
   **/
  
  @ApiModelProperty(required = true, value = "Phone Information")
  @JsonProperty("phone")
  public PhoneInput getPhone() {
    return phone;
  }
  public void setPhone(PhoneInput phone) {
    this.phone = phone;
  }

  
  /**
   * Apply changes to all linked accounts flag
   **/
  
  @ApiModelProperty(required = true, value = "Apply changes to all linked accounts flag")
  @JsonProperty("applyToAllLinkedAccountFlag")
  public Boolean getApplyToAllLinkedAccountFlag() {
    return applyToAllLinkedAccountFlag;
  }
  public void setApplyToAllLinkedAccountFlag(Boolean applyToAllLinkedAccountFlag) {
    this.applyToAllLinkedAccountFlag = applyToAllLinkedAccountFlag;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneRequest phoneRequest = (PhoneRequest) o;
    return Objects.equals(phone, phoneRequest.phone) &&
        Objects.equals(applyToAllLinkedAccountFlag, phoneRequest.applyToAllLinkedAccountFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phone, applyToAllLinkedAccountFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneRequest {\n");
    
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    applyToAllLinkedAccountFlag: ").append(toIndentedString(applyToAllLinkedAccountFlag)).append("\n");
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

