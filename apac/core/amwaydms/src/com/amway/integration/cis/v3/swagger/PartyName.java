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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * PartyName
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PartyName   {
  @SerializedName("personNameTypeCd")
  private String personNameTypeCd = null;

  @SerializedName("localeName")
  private NameInfo localeName = null;

  @SerializedName("latinName")
  private NameInfo latinName = null;

  @SerializedName("languageCd")
  private String languageCd = null;

  @SerializedName("charSetCd")
  private String charSetCd = null;

  @SerializedName("preferredName")
  private String preferredName = null;

  public PartyName personNameTypeCd(String personNameTypeCd) {
    this.personNameTypeCd = personNameTypeCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PRNTY'>Reference to Person Name Type Code</a>
   * @return personNameTypeCd
  **/
  @ApiModelProperty(example = "null", required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PRNTY'>Reference to Person Name Type Code</a>")
  public String getPersonNameTypeCd() {
    return personNameTypeCd;
  }

  public void setPersonNameTypeCd(String personNameTypeCd) {
    this.personNameTypeCd = personNameTypeCd;
  }

  public PartyName localeName(NameInfo localeName) {
    this.localeName = localeName;
    return this;
  }

   /**
   * Local Language Name
   * @return localeName
  **/
  @ApiModelProperty(example = "null", required = true, value = "Local Language Name")
  public NameInfo getLocaleName() {
    return localeName;
  }

  public void setLocaleName(NameInfo localeName) {
    this.localeName = localeName;
  }

  public PartyName latinName(NameInfo latinName) {
    this.latinName = latinName;
    return this;
  }

   /**
   * Lantin Language Name if exists
   * @return latinName
  **/
  @ApiModelProperty(example = "null", value = "Lantin Language Name if exists")
  public NameInfo getLatinName() {
    return latinName;
  }

  public void setLatinName(NameInfo latinName) {
    this.latinName = latinName;
  }

  public PartyName languageCd(String languageCd) {
    this.languageCd = languageCd;
    return this;
  }

   /**
   * Get languageCd
   * @return languageCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getLanguageCd() {
    return languageCd;
  }

  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
  }

  public PartyName charSetCd(String charSetCd) {
    this.charSetCd = charSetCd;
    return this;
  }

   /**
   * Get charSetCd
   * @return charSetCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCharSetCd() {
    return charSetCd;
  }

  public void setCharSetCd(String charSetCd) {
    this.charSetCd = charSetCd;
  }

  public PartyName preferredName(String preferredName) {
    this.preferredName = preferredName;
    return this;
  }

   /**
   * Get preferredName
   * @return preferredName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPreferredName() {
    return preferredName;
  }

  public void setPreferredName(String preferredName) {
    this.preferredName = preferredName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyName partyName = (PartyName) o;
    return Objects.equals(this.personNameTypeCd, partyName.personNameTypeCd) &&
        Objects.equals(this.localeName, partyName.localeName) &&
        Objects.equals(this.latinName, partyName.latinName) &&
        Objects.equals(this.languageCd, partyName.languageCd) &&
        Objects.equals(this.charSetCd, partyName.charSetCd) &&
        Objects.equals(this.preferredName, partyName.preferredName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personNameTypeCd, localeName, latinName, languageCd, charSetCd, preferredName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyName {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

