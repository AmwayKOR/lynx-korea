package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.NameInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyName   {
  
  private Long partyId = null;
  private String personNameTypeCd = null;
  private NameInfo localeName = null;
  private NameInfo latinName = null;
  private String languageCd = null;
  private String charSetCd = null;
  private String preferredName = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyId")
  public Long getPartyId() {
    return partyId;
  }
  public void setPartyId(Long partyId) {
    this.partyId = partyId;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PRNTY'>Reference to Person Name Type Code</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PRNTY'>Reference to Person Name Type Code</a>")
  @JsonProperty("personNameTypeCd")
  public String getPersonNameTypeCd() {
    return personNameTypeCd;
  }
  public void setPersonNameTypeCd(String personNameTypeCd) {
    this.personNameTypeCd = personNameTypeCd;
  }

  
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
    PartyName partyName = (PartyName) o;
    return Objects.equals(partyId, partyName.partyId) &&
        Objects.equals(personNameTypeCd, partyName.personNameTypeCd) &&
        Objects.equals(localeName, partyName.localeName) &&
        Objects.equals(latinName, partyName.latinName) &&
        Objects.equals(languageCd, partyName.languageCd) &&
        Objects.equals(charSetCd, partyName.charSetCd) &&
        Objects.equals(preferredName, partyName.preferredName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, personNameTypeCd, localeName, latinName, languageCd, charSetCd, preferredName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyName {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    personNameTypeCd: ").append(toIndentedString(personNameTypeCd)).append("\n");
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

