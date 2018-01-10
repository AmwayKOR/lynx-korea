package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AddressInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AddressRequest   {
  
  private AddressInput address = null;
  private String applyToAllLinkedAccountFlag = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("address")
  public AddressInput getAddress() {
    return address;
  }
  public void setAddress(AddressInput address) {
    this.address = address;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("applyToAllLinkedAccountFlag")
  public String getApplyToAllLinkedAccountFlag() {
    return applyToAllLinkedAccountFlag;
  }
  public void setApplyToAllLinkedAccountFlag(String applyToAllLinkedAccountFlag) {
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
    AddressRequest addressRequest = (AddressRequest) o;
    return Objects.equals(address, addressRequest.address) &&
        Objects.equals(applyToAllLinkedAccountFlag, addressRequest.applyToAllLinkedAccountFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, applyToAllLinkedAccountFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressRequest {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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

