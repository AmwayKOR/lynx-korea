package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AffAbo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class LosAccountsRequest   {
  
  private List<AffAbo> affAboList = new ArrayList<AffAbo>();

  
  /**
   * affAboList
   **/
  
  @ApiModelProperty(required = true, value = "affAboList")
  @JsonProperty("affAboList")
  public List<AffAbo> getAffAboList() {
    return affAboList;
  }
  public void setAffAboList(List<AffAbo> affAboList) {
    this.affAboList = affAboList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LosAccountsRequest losAccountsRequest = (LosAccountsRequest) o;
    return Objects.equals(affAboList, losAccountsRequest.affAboList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(affAboList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LosAccountsRequest {\n");
    
    sb.append("    affAboList: ").append(toIndentedString(affAboList)).append("\n");
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

