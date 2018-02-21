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
 * PartyMasterInput
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class PartyMasterInput   {
  @SerializedName("partyTypeCd")
  private String partyTypeCd = null;

  @SerializedName("genderCd")
  private String genderCd = null;

  @SerializedName("birthDate")
  private String birthDate = null;

  @SerializedName("roleCd")
  private String roleCd = null;

  @SerializedName("birthCountryCd")
  private String birthCountryCd = null;

  @SerializedName("ethnicCd")
  private String ethnicCd = null;

  @SerializedName("relationShipToPrimaryCd")
  private String relationShipToPrimaryCd = null;

  @SerializedName("userId")
  private String userId = null;

  @SerializedName("userPasswd")
  private String userPasswd = null;

  @SerializedName("userPin")
  private String userPin = null;

  @SerializedName("tzOffset")
  private String tzOffset = null;

  @SerializedName("languageCd")
  private String languageCd = null;

  @SerializedName("partyIsMinorFlg")
  private String partyIsMinorFlg = null;

  @SerializedName("educationTypeCd")
  private String educationTypeCd = null;

  @SerializedName("professionCd")
  private String professionCd = null;

  @SerializedName("maritalStatusCd")
  private String maritalStatusCd = null;

  @SerializedName("natlCntryCd")
  private String natlCntryCd = null;

  @SerializedName("czshpCntryCd")
  private String czshpCntryCd = null;

  @SerializedName("electronicSignature")
  private String electronicSignature = null;

  public PartyMasterInput partyTypeCd(String partyTypeCd) {
    this.partyTypeCd = partyTypeCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PARTY'>Reference to Party Type</a>
   * @return partyTypeCd
  **/
  @ApiModelProperty(example = "null", required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PARTY'>Reference to Party Type</a>")
  public String getPartyTypeCd() {
    return partyTypeCd;
  }

  public void setPartyTypeCd(String partyTypeCd) {
    this.partyTypeCd = partyTypeCd;
  }

  public PartyMasterInput genderCd(String genderCd) {
    this.genderCd = genderCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=GNDER'>Reference to Gender</a>
   * @return genderCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=GNDER'>Reference to Gender</a>")
  public String getGenderCd() {
    return genderCd;
  }

  public void setGenderCd(String genderCd) {
    this.genderCd = genderCd;
  }

  public PartyMasterInput birthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

   /**
   * Birth date in UTC such as '1957-05-28T00:00:00-02:00'
   * @return birthDate
  **/
  @ApiModelProperty(example = "null", value = "Birth date in UTC such as '1957-05-28T00:00:00-02:00'")
  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public PartyMasterInput roleCd(String roleCd) {
    this.roleCd = roleCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ROLCD'>Reference to Role Code</a>
   * @return roleCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ROLCD'>Reference to Role Code</a>")
  public String getRoleCd() {
    return roleCd;
  }

  public void setRoleCd(String roleCd) {
    this.roleCd = roleCd;
  }

  public PartyMasterInput birthCountryCd(String birthCountryCd) {
    this.birthCountryCd = birthCountryCd;
    return this;
  }

   /**
   * Get birthCountryCd
   * @return birthCountryCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getBirthCountryCd() {
    return birthCountryCd;
  }

  public void setBirthCountryCd(String birthCountryCd) {
    this.birthCountryCd = birthCountryCd;
  }

  public PartyMasterInput ethnicCd(String ethnicCd) {
    this.ethnicCd = ethnicCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ETCTY'>Reference to Ethnicity</a>
   * @return ethnicCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ETCTY'>Reference to Ethnicity</a>")
  public String getEthnicCd() {
    return ethnicCd;
  }

  public void setEthnicCd(String ethnicCd) {
    this.ethnicCd = ethnicCd;
  }

  public PartyMasterInput relationShipToPrimaryCd(String relationShipToPrimaryCd) {
    this.relationShipToPrimaryCd = relationShipToPrimaryCd;
    return this;
  }

   /**
   * Get relationShipToPrimaryCd
   * @return relationShipToPrimaryCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getRelationShipToPrimaryCd() {
    return relationShipToPrimaryCd;
  }

  public void setRelationShipToPrimaryCd(String relationShipToPrimaryCd) {
    this.relationShipToPrimaryCd = relationShipToPrimaryCd;
  }

  public PartyMasterInput userId(String userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public PartyMasterInput userPasswd(String userPasswd) {
    this.userPasswd = userPasswd;
    return this;
  }

   /**
   * Get userPasswd
   * @return userPasswd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getUserPasswd() {
    return userPasswd;
  }

  public void setUserPasswd(String userPasswd) {
    this.userPasswd = userPasswd;
  }

  public PartyMasterInput userPin(String userPin) {
    this.userPin = userPin;
    return this;
  }

   /**
   * Get userPin
   * @return userPin
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getUserPin() {
    return userPin;
  }

  public void setUserPin(String userPin) {
    this.userPin = userPin;
  }

  public PartyMasterInput tzOffset(String tzOffset) {
    this.tzOffset = tzOffset;
    return this;
  }

   /**
   * Get tzOffset
   * @return tzOffset
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getTzOffset() {
    return tzOffset;
  }

  public void setTzOffset(String tzOffset) {
    this.tzOffset = tzOffset;
  }

  public PartyMasterInput languageCd(String languageCd) {
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

  public PartyMasterInput partyIsMinorFlg(String partyIsMinorFlg) {
    this.partyIsMinorFlg = partyIsMinorFlg;
    return this;
  }

   /**
   * Get partyIsMinorFlg
   * @return partyIsMinorFlg
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPartyIsMinorFlg() {
    return partyIsMinorFlg;
  }

  public void setPartyIsMinorFlg(String partyIsMinorFlg) {
    this.partyIsMinorFlg = partyIsMinorFlg;
  }

  public PartyMasterInput educationTypeCd(String educationTypeCd) {
    this.educationTypeCd = educationTypeCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=EDUTY'>Reference to Education Type Code</a>
   * @return educationTypeCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=EDUTY'>Reference to Education Type Code</a>")
  public String getEducationTypeCd() {
    return educationTypeCd;
  }

  public void setEducationTypeCd(String educationTypeCd) {
    this.educationTypeCd = educationTypeCd;
  }

  public PartyMasterInput professionCd(String professionCd) {
    this.professionCd = professionCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PROFS'>Reference to Professional Code</a>
   * @return professionCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PROFS'>Reference to Professional Code</a>")
  public String getProfessionCd() {
    return professionCd;
  }

  public void setProfessionCd(String professionCd) {
    this.professionCd = professionCd;
  }

  public PartyMasterInput maritalStatusCd(String maritalStatusCd) {
    this.maritalStatusCd = maritalStatusCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=MARST'>Reference to Marital Status Code</a>
   * @return maritalStatusCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=MARST'>Reference to Marital Status Code</a>")
  public String getMaritalStatusCd() {
    return maritalStatusCd;
  }

  public void setMaritalStatusCd(String maritalStatusCd) {
    this.maritalStatusCd = maritalStatusCd;
  }

  public PartyMasterInput natlCntryCd(String natlCntryCd) {
    this.natlCntryCd = natlCntryCd;
    return this;
  }

   /**
   * Get natlCntryCd
   * @return natlCntryCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getNatlCntryCd() {
    return natlCntryCd;
  }

  public void setNatlCntryCd(String natlCntryCd) {
    this.natlCntryCd = natlCntryCd;
  }

  public PartyMasterInput czshpCntryCd(String czshpCntryCd) {
    this.czshpCntryCd = czshpCntryCd;
    return this;
  }

   /**
   * Get czshpCntryCd
   * @return czshpCntryCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCzshpCntryCd() {
    return czshpCntryCd;
  }

  public void setCzshpCntryCd(String czshpCntryCd) {
    this.czshpCntryCd = czshpCntryCd;
  }

  public PartyMasterInput electronicSignature(String electronicSignature) {
    this.electronicSignature = electronicSignature;
    return this;
  }

   /**
   * Get electronicSignature
   * @return electronicSignature
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getElectronicSignature() {
    return electronicSignature;
  }

  public void setElectronicSignature(String electronicSignature) {
    this.electronicSignature = electronicSignature;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyMasterInput partyMasterInput = (PartyMasterInput) o;
    return Objects.equals(this.partyTypeCd, partyMasterInput.partyTypeCd) &&
        Objects.equals(this.genderCd, partyMasterInput.genderCd) &&
        Objects.equals(this.birthDate, partyMasterInput.birthDate) &&
        Objects.equals(this.roleCd, partyMasterInput.roleCd) &&
        Objects.equals(this.birthCountryCd, partyMasterInput.birthCountryCd) &&
        Objects.equals(this.ethnicCd, partyMasterInput.ethnicCd) &&
        Objects.equals(this.relationShipToPrimaryCd, partyMasterInput.relationShipToPrimaryCd) &&
        Objects.equals(this.userId, partyMasterInput.userId) &&
        Objects.equals(this.userPasswd, partyMasterInput.userPasswd) &&
        Objects.equals(this.userPin, partyMasterInput.userPin) &&
        Objects.equals(this.tzOffset, partyMasterInput.tzOffset) &&
        Objects.equals(this.languageCd, partyMasterInput.languageCd) &&
        Objects.equals(this.partyIsMinorFlg, partyMasterInput.partyIsMinorFlg) &&
        Objects.equals(this.educationTypeCd, partyMasterInput.educationTypeCd) &&
        Objects.equals(this.professionCd, partyMasterInput.professionCd) &&
        Objects.equals(this.maritalStatusCd, partyMasterInput.maritalStatusCd) &&
        Objects.equals(this.natlCntryCd, partyMasterInput.natlCntryCd) &&
        Objects.equals(this.czshpCntryCd, partyMasterInput.czshpCntryCd) &&
        Objects.equals(this.electronicSignature, partyMasterInput.electronicSignature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyTypeCd, genderCd, birthDate, roleCd, birthCountryCd, ethnicCd, relationShipToPrimaryCd, userId, userPasswd, userPin, tzOffset, languageCd, partyIsMinorFlg, educationTypeCd, professionCd, maritalStatusCd, natlCntryCd, czshpCntryCd, electronicSignature);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyMasterInput {\n");
    
    sb.append("    partyTypeCd: ").append(toIndentedString(partyTypeCd)).append("\n");
    sb.append("    genderCd: ").append(toIndentedString(genderCd)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    roleCd: ").append(toIndentedString(roleCd)).append("\n");
    sb.append("    birthCountryCd: ").append(toIndentedString(birthCountryCd)).append("\n");
    sb.append("    ethnicCd: ").append(toIndentedString(ethnicCd)).append("\n");
    sb.append("    relationShipToPrimaryCd: ").append(toIndentedString(relationShipToPrimaryCd)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userPasswd: ").append(toIndentedString(userPasswd)).append("\n");
    sb.append("    userPin: ").append(toIndentedString(userPin)).append("\n");
    sb.append("    tzOffset: ").append(toIndentedString(tzOffset)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    partyIsMinorFlg: ").append(toIndentedString(partyIsMinorFlg)).append("\n");
    sb.append("    educationTypeCd: ").append(toIndentedString(educationTypeCd)).append("\n");
    sb.append("    professionCd: ").append(toIndentedString(professionCd)).append("\n");
    sb.append("    maritalStatusCd: ").append(toIndentedString(maritalStatusCd)).append("\n");
    sb.append("    natlCntryCd: ").append(toIndentedString(natlCntryCd)).append("\n");
    sb.append("    czshpCntryCd: ").append(toIndentedString(czshpCntryCd)).append("\n");
    sb.append("    electronicSignature: ").append(toIndentedString(electronicSignature)).append("\n");
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

