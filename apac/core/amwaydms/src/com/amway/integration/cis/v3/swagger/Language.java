/**
 * Magic DMS RESTful Services
 * No descripton provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v3
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.amway.integration.cis.v3.swagger;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Language
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class Language   {
  @SerializedName("cntryCd")
  private String cntryCd = null;

  @SerializedName("languageCd")
  private String languageCd = null;

  @SerializedName("languageDesc")
  private String languageDesc = null;

  @SerializedName("officialLanguage")
  private Boolean officialLanguage = null;

  @SerializedName("preferredLanguage")
  private Boolean preferredLanguage = null;

  public Language cntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOCN'>Reference to ISO Country codes</a>
   * @return cntryCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOCN'>Reference to ISO Country codes</a>")
  public String getCntryCd() {
    return cntryCd;
  }

  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  public Language languageCd(String languageCd) {
    this.languageCd = languageCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOLN'>Reference to Language codes</a>
   * @return languageCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOLN'>Reference to Language codes</a>")
  public String getLanguageCd() {
    return languageCd;
  }

  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
  }

  public Language languageDesc(String languageDesc) {
    this.languageDesc = languageDesc;
    return this;
  }

   /**
   * Language Description
   * @return languageDesc
  **/
  @ApiModelProperty(example = "null", value = "Language Description")
  public String getLanguageDesc() {
    return languageDesc;
  }

  public void setLanguageDesc(String languageDesc) {
    this.languageDesc = languageDesc;
  }

  public Language officialLanguage(Boolean officialLanguage) {
    this.officialLanguage = officialLanguage;
    return this;
  }

   /**
   * Is Official Language for specified Country?
   * @return officialLanguage
  **/
  @ApiModelProperty(example = "null", value = "Is Official Language for specified Country?")
  public Boolean getOfficialLanguage() {
    return officialLanguage;
  }

  public void setOfficialLanguage(Boolean officialLanguage) {
    this.officialLanguage = officialLanguage;
  }

  public Language preferredLanguage(Boolean preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
    return this;
  }

   /**
   * Is Preferred Language for specified Country?
   * @return preferredLanguage
  **/
  @ApiModelProperty(example = "null", value = "Is Preferred Language for specified Country?")
  public Boolean getPreferredLanguage() {
    return preferredLanguage;
  }

  public void setPreferredLanguage(Boolean preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Language language = (Language) o;
    return Objects.equals(this.cntryCd, language.cntryCd) &&
        Objects.equals(this.languageCd, language.languageCd) &&
        Objects.equals(this.languageDesc, language.languageDesc) &&
        Objects.equals(this.officialLanguage, language.officialLanguage) &&
        Objects.equals(this.preferredLanguage, language.preferredLanguage);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

