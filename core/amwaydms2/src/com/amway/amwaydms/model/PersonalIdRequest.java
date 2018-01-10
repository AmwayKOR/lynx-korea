package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.PersonalId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PersonalIdRequest   {
  
  private List<PersonalId> personalIdList = new ArrayList<PersonalId>();

  
  /**
   * Personal Id Information
   **/
  
  @ApiModelProperty(required = true, value = "Personal Id Information")
  @JsonProperty("personalIdList")
  public List<PersonalId> getPersonalIdList() {
    return personalIdList;
  }
  public void setPersonalIdList(List<PersonalId> personalIdList) {
    this.personalIdList = personalIdList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalIdRequest personalIdRequest = (PersonalIdRequest) o;
    return Objects.equals(personalIdList, personalIdRequest.personalIdList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personalIdList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalIdRequest {\n");
    
    sb.append("    personalIdList: ").append(toIndentedString(personalIdList)).append("\n");
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

