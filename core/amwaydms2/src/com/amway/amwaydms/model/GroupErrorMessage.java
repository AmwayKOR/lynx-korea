package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.FieldErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class GroupErrorMessage   {
  
  private List<FieldErrorMessage> fieldErrorList = new ArrayList<FieldErrorMessage>();
  private String id = null;

  
  /**
   * The field level error with its field name
   **/
  
  @ApiModelProperty(value = "The field level error with its field name")
  @JsonProperty("fieldErrorList")
  public List<FieldErrorMessage> getFieldErrorList() {
    return fieldErrorList;
  }
  public void setFieldErrorList(List<FieldErrorMessage> fieldErrorList) {
    this.fieldErrorList = fieldErrorList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupErrorMessage groupErrorMessage = (GroupErrorMessage) o;
    return Objects.equals(fieldErrorList, groupErrorMessage.fieldErrorList) &&
        Objects.equals(id, groupErrorMessage.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fieldErrorList, id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupErrorMessage {\n");
    
    sb.append("    fieldErrorList: ").append(toIndentedString(fieldErrorList)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

