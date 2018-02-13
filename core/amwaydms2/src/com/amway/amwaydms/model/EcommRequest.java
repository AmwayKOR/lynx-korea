package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.EcommInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class EcommRequest   {
  
  private EcommInput ecomm = null;
  private Boolean applyToAllLinkedAccountFlag = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ecomm")
  public EcommInput getEcomm() {
    return ecomm;
  }
  public void setEcomm(EcommInput ecomm) {
    this.ecomm = ecomm;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
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
    EcommRequest ecommRequest = (EcommRequest) o;
    return Objects.equals(ecomm, ecommRequest.ecomm) &&
        Objects.equals(applyToAllLinkedAccountFlag, ecommRequest.applyToAllLinkedAccountFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ecomm, applyToAllLinkedAccountFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EcommRequest {\n");
    
    sb.append("    ecomm: ").append(toIndentedString(ecomm)).append("\n");
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

