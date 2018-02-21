package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AddressInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AnonymousAddressRequest   {
  
  private AddressInput address = null;

  
  /**
   * Address information
   **/
  
  @ApiModelProperty(required = true, value = "Address information")
  @JsonProperty("address")
  public AddressInput getAddress() {
    return address;
  }
  public void setAddress(AddressInput address) {
    this.address = address;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnonymousAddressRequest anonymousAddressRequest = (AnonymousAddressRequest) o;
    return Objects.equals(address, anonymousAddressRequest.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AnonymousAddressRequest {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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

