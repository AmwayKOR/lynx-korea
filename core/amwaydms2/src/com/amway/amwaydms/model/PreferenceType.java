package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PreferenceType   {
  
  private String preferenceValueCd = null;
  private String preferenceValueName = null;
  private String preferenceListId = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("preferenceValueCd")
  public String getPreferenceValueCd() {
    return preferenceValueCd;
  }
  public void setPreferenceValueCd(String preferenceValueCd) {
    this.preferenceValueCd = preferenceValueCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("preferenceValueName")
  public String getPreferenceValueName() {
    return preferenceValueName;
  }
  public void setPreferenceValueName(String preferenceValueName) {
    this.preferenceValueName = preferenceValueName;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PFLCD'>Reference to Preference List codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PFLCD'>Reference to Preference List codes</a>")
  @JsonProperty("preferenceListId")
  public String getPreferenceListId() {
    return preferenceListId;
  }
  public void setPreferenceListId(String preferenceListId) {
    this.preferenceListId = preferenceListId;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PreferenceType preferenceType = (PreferenceType) o;
    return Objects.equals(preferenceValueCd, preferenceType.preferenceValueCd) &&
        Objects.equals(preferenceValueName, preferenceType.preferenceValueName) &&
        Objects.equals(preferenceListId, preferenceType.preferenceListId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(preferenceValueCd, preferenceValueName, preferenceListId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PreferenceType {\n");
    
    sb.append("    preferenceValueCd: ").append(toIndentedString(preferenceValueCd)).append("\n");
    sb.append("    preferenceValueName: ").append(toIndentedString(preferenceValueName)).append("\n");
    sb.append("    preferenceListId: ").append(toIndentedString(preferenceListId)).append("\n");
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

