package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.PersonalIdUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PersonalIdUpdateRequest   {
  
  private PersonalIdUpdate personalId = null;

  
  /**
   * Personal Id Information
   **/
  
  @ApiModelProperty(required = true, value = "Personal Id Information")
  @JsonProperty("personalId")
  public PersonalIdUpdate getPersonalId() {
    return personalId;
  }
  public void setPersonalId(PersonalIdUpdate personalId) {
    this.personalId = personalId;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalIdUpdateRequest personalIdUpdateRequest = (PersonalIdUpdateRequest) o;
    return Objects.equals(personalId, personalIdUpdateRequest.personalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personalId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalIdUpdateRequest {\n");
    
    sb.append("    personalId: ").append(toIndentedString(personalId)).append("\n");
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

