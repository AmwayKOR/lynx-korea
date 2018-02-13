package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.TaxUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class TaxUpdateRequest   {
  
  private TaxUpdate tax = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("tax")
  public TaxUpdate getTax() {
    return tax;
  }
  public void setTax(TaxUpdate tax) {
    this.tax = tax;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaxUpdateRequest taxUpdateRequest = (TaxUpdateRequest) o;
    return Objects.equals(tax, taxUpdateRequest.tax);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tax);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaxUpdateRequest {\n");
    
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
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

