package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Tax;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class TaxRequest   {
  
  private List<Tax> taxList = new ArrayList<Tax>();

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("taxList")
  public List<Tax> getTaxList() {
    return taxList;
  }
  public void setTaxList(List<Tax> taxList) {
    this.taxList = taxList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaxRequest taxRequest = (TaxRequest) o;
    return Objects.equals(taxList, taxRequest.taxList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taxList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaxRequest {\n");
    
    sb.append("    taxList: ").append(toIndentedString(taxList)).append("\n");
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

