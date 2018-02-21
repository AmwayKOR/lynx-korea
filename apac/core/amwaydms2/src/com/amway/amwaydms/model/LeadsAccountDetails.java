package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class LeadsAccountDetails   {
  
  private Integer salesPlanAff = null;
  private Long aboNum = null;
  private Address address = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanAff")
  public Integer getSalesPlanAff() {
    return salesPlanAff;
  }
  public void setSalesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("aboNum")
  public Long getAboNum() {
    return aboNum;
  }
  public void setAboNum(Long aboNum) {
    this.aboNum = aboNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("address")
  public Address getAddress() {
    return address;
  }
  public void setAddress(Address address) {
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
    LeadsAccountDetails leadsAccountDetails = (LeadsAccountDetails) o;
    return Objects.equals(salesPlanAff, leadsAccountDetails.salesPlanAff) &&
        Objects.equals(aboNum, leadsAccountDetails.aboNum) &&
        Objects.equals(address, leadsAccountDetails.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(salesPlanAff, aboNum, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeadsAccountDetails {\n");
    
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
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

