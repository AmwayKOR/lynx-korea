package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Language   {
  
  private String cntryCd = null;
  private String languageCd = null;
  private String languageDesc = null;
  private Boolean officialLanguage = null;
  private Boolean preferredLanguage = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOCN'>Reference to ISO Country codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOCN'>Reference to ISO Country codes</a>")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOLN'>Reference to Language codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOLN'>Reference to Language codes</a>")
  @JsonProperty("languageCd")
  public String getLanguageCd() {
    return languageCd;
  }
  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
  }

  
  /**
   * Language Description
   **/
  
  @ApiModelProperty(value = "Language Description")
  @JsonProperty("languageDesc")
  public String getLanguageDesc() {
    return languageDesc;
  }
  public void setLanguageDesc(String languageDesc) {
    this.languageDesc = languageDesc;
  }

  
  /**
   * Is Official Language for specified Country?
   **/
  
  @ApiModelProperty(value = "Is Official Language for specified Country?")
  @JsonProperty("officialLanguage")
  public Boolean getOfficialLanguage() {
    return officialLanguage;
  }
  public void setOfficialLanguage(Boolean officialLanguage) {
    this.officialLanguage = officialLanguage;
  }

  
  /**
   * Is Preferred Language for specified Country?
   **/
  
  @ApiModelProperty(value = "Is Preferred Language for specified Country?")
  @JsonProperty("preferredLanguage")
  public Boolean getPreferredLanguage() {
    return preferredLanguage;
  }
  public void setPreferredLanguage(Boolean preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Language language = (Language) o;
    return Objects.equals(cntryCd, language.cntryCd) &&
        Objects.equals(languageCd, language.languageCd) &&
        Objects.equals(languageDesc, language.languageDesc) &&
        Objects.equals(officialLanguage, language.officialLanguage) &&
        Objects.equals(preferredLanguage, language.preferredLanguage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cntryCd, languageCd, languageDesc, officialLanguage, preferredLanguage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Language {\n");
    
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    languageDesc: ").append(toIndentedString(languageDesc)).append("\n");
    sb.append("    officialLanguage: ").append(toIndentedString(officialLanguage)).append("\n");
    sb.append("    preferredLanguage: ").append(toIndentedString(preferredLanguage)).append("\n");
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

