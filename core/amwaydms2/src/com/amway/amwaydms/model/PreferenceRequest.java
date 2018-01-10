package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.PreferenceInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PreferenceRequest   {
  
  private PreferenceInput preference = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("preference")
  public PreferenceInput getPreference() {
    return preference;
  }
  public void setPreference(PreferenceInput preference) {
    this.preference = preference;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PreferenceRequest preferenceRequest = (PreferenceRequest) o;
    return Objects.equals(preference, preferenceRequest.preference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(preference);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PreferenceRequest {\n");
    
    sb.append("    preference: ").append(toIndentedString(preference)).append("\n");
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

