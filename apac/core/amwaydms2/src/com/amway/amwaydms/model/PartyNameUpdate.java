package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.NameInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyNameUpdate   {
  
  private NameInfo localeName = null;
  private NameInfo latinName = null;
  private String languageCd = null;
  private String charSetCd = null;
  private String preferredName = null;

  
  /**
   * Local Language Name
   **/
  
  @ApiModelProperty(required = true, value = "Local Language Name")
  @JsonProperty("localeName")
  public NameInfo getLocaleName() {
    return localeName;
  }
  public void setLocaleName(NameInfo localeName) {
    this.localeName = localeName;
  }

  
  /**
   * Lantin Language Name if exists
   **/
  
  @ApiModelProperty(value = "Lantin Language Name if exists")
  @JsonProperty("latinName")
  public NameInfo getLatinName() {
    return latinName;
  }
  public void setLatinName(NameInfo latinName) {
    this.latinName = latinName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("languageCd")
  public String getLanguageCd() {
    return languageCd;
  }
  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("charSetCd")
  public String getCharSetCd() {
    return charSetCd;
  }
  public void setCharSetCd(String charSetCd) {
    this.charSetCd = charSetCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("preferredName")
  public String getPreferredName() {
    return preferredName;
  }
  public void setPreferredName(String preferredName) {
    this.preferredName = preferredName;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyNameUpdate partyNameUpdate = (PartyNameUpdate) o;
    return Objects.equals(localeName, partyNameUpdate.localeName) &&
        Objects.equals(latinName, partyNameUpdate.latinName) &&
        Objects.equals(languageCd, partyNameUpdate.languageCd) &&
        Objects.equals(charSetCd, partyNameUpdate.charSetCd) &&
        Objects.equals(preferredName, partyNameUpdate.preferredName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localeName, latinName, languageCd, charSetCd, preferredName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyNameUpdate {\n");
    
    sb.append("    localeName: ").append(toIndentedString(localeName)).append("\n");
    sb.append("    latinName: ").append(toIndentedString(latinName)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    charSetCd: ").append(toIndentedString(charSetCd)).append("\n");
    sb.append("    preferredName: ").append(toIndentedString(preferredName)).append("\n");
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

