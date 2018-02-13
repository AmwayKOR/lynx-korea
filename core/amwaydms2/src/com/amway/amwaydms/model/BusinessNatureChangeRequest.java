package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.BusinessNatureChange;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BusinessNatureChangeRequest   {
  
  private BusinessNatureChange businessNatureChange = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("businessNatureChange")
  public BusinessNatureChange getBusinessNatureChange() {
    return businessNatureChange;
  }
  public void setBusinessNatureChange(BusinessNatureChange businessNatureChange) {
    this.businessNatureChange = businessNatureChange;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessNatureChangeRequest businessNatureChangeRequest = (BusinessNatureChangeRequest) o;
    return Objects.equals(businessNatureChange, businessNatureChangeRequest.businessNatureChange);
  }

  @Override
  public int hashCode() {
    return Objects.hash(businessNatureChange);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessNatureChangeRequest {\n");
    
    sb.append("    businessNatureChange: ").append(toIndentedString(businessNatureChange)).append("\n");
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

