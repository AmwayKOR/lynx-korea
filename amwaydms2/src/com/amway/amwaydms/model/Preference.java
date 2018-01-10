package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.PreferenceType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Preference   {
  
  private String preferenceId = null;
  private String isTrueFlg = null;
  private String preferenceNote = null;
  private List<PreferenceType> preferenceTypeList = new ArrayList<PreferenceType>();

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PRFID'>Reference to Preference Id types</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PRFID'>Reference to Preference Id types</a>")
  @JsonProperty("preferenceId")
  public String getPreferenceId() {
    return preferenceId;
  }
  public void setPreferenceId(String preferenceId) {
    this.preferenceId = preferenceId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("isTrueFlg")
  public String getIsTrueFlg() {
    return isTrueFlg;
  }
  public void setIsTrueFlg(String isTrueFlg) {
    this.isTrueFlg = isTrueFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("preferenceNote")
  public String getPreferenceNote() {
    return preferenceNote;
  }
  public void setPreferenceNote(String preferenceNote) {
    this.preferenceNote = preferenceNote;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("preferenceTypeList")
  public List<PreferenceType> getPreferenceTypeList() {
    return preferenceTypeList;
  }
  public void setPreferenceTypeList(List<PreferenceType> preferenceTypeList) {
    this.preferenceTypeList = preferenceTypeList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Preference preference = (Preference) o;
    return Objects.equals(preferenceId, preference.preferenceId) &&
        Objects.equals(isTrueFlg, preference.isTrueFlg) &&
        Objects.equals(preferenceNote, preference.preferenceNote) &&
        Objects.equals(preferenceTypeList, preference.preferenceTypeList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(preferenceId, isTrueFlg, preferenceNote, preferenceTypeList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Preference {\n");
    
    sb.append("    preferenceId: ").append(toIndentedString(preferenceId)).append("\n");
    sb.append("    isTrueFlg: ").append(toIndentedString(isTrueFlg)).append("\n");
    sb.append("    preferenceNote: ").append(toIndentedString(preferenceNote)).append("\n");
    sb.append("    preferenceTypeList: ").append(toIndentedString(preferenceTypeList)).append("\n");
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

