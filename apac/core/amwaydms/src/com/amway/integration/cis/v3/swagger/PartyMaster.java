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
 * PartyMaster
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PartyMaster   {
  @SerializedName("partyId")
  private String partyId = null;

  @SerializedName("partyTypeCd")
  private String partyTypeCd = null;

  @SerializedName("genderCd")
  private String genderCd = null;

  @SerializedName("ethnicCd")
  private String ethnicCd = null;

  @SerializedName("birthdate")
  private String birthdate = null;

  @SerializedName("languageCd")
  private String languageCd = null;

  @SerializedName("birthCountryCd")
  private String birthCountryCd = null;

  @SerializedName("tzOffset")
  private String tzOffset = null;

  @SerializedName("partyIsMinorFlg")
  private String partyIsMinorFlg = null;

  @SerializedName("userId")
  private String userId = null;

  @SerializedName("userPasswd")
  private String userPasswd = null;

  @SerializedName("userPin")
  private String userPin = null;

  @SerializedName("statusCd")
  private String statusCd = null;

  @SerializedName("primaryOnAccount")
  private String primaryOnAccount = null;

  @SerializedName("roleCd")
  private String roleCd = null;

  @SerializedName("relationShipToPrimaryPartyCd")
  private String relationShipToPrimaryPartyCd = null;

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

  public PartyMaster partyId(String partyId) {
    this.partyId = partyId;
    return this;
  }

   /**
   * Get partyId
   * @return partyId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public PartyMaster partyTypeCd(String partyTypeCd) {
    this.partyTypeCd = partyTypeCd;
    return this;
  }

   /**
   * Get partyTypeCd
   * @return partyTypeCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPartyTypeCd() {
    return partyTypeCd;
  }

  public void setPartyTypeCd(String partyTypeCd) {
    this.partyTypeCd = partyTypeCd;
  }

  public PartyMaster genderCd(String genderCd) {
    this.genderCd = genderCd;
    return this;
  }

   /**
   * Get genderCd
   * @return genderCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getGenderCd() {
    return genderCd;
  }

  public void setGenderCd(String genderCd) {
    this.genderCd = genderCd;
  }

  public PartyMaster ethnicCd(String ethnicCd) {
    this.ethnicCd = ethnicCd;
    return this;
  }

   /**
   * Get ethnicCd
   * @return ethnicCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getEthnicCd() {
    return ethnicCd;
  }

  public void setEthnicCd(String ethnicCd) {
    this.ethnicCd = ethnicCd;
  }

  public PartyMaster birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

   /**
   * Get birthdate
   * @return birthdate
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public PartyMaster languageCd(String languageCd) {
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

  public PartyMaster birthCountryCd(String birthCountryCd) {
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

  public PartyMaster tzOffset(String tzOffset) {
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

  public PartyMaster partyIsMinorFlg(String partyIsMinorFlg) {
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

  public PartyMaster userId(String userId) {
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

  public PartyMaster userPasswd(String userPasswd) {
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

  public PartyMaster userPin(String userPin) {
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

  public PartyMaster statusCd(String statusCd) {
    this.statusCd = statusCd;
    return this;
  }

   /**
   * Get statusCd
   * @return statusCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }

  public PartyMaster primaryOnAccount(String primaryOnAccount) {
    this.primaryOnAccount = primaryOnAccount;
    return this;
  }

   /**
   * Get primaryOnAccount
   * @return primaryOnAccount
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPrimaryOnAccount() {
    return primaryOnAccount;
  }

  public void setPrimaryOnAccount(String primaryOnAccount) {
    this.primaryOnAccount = primaryOnAccount;
  }

  public PartyMaster roleCd(String roleCd) {
    this.roleCd = roleCd;
    return this;
  }

   /**
   * Get roleCd
   * @return roleCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getRoleCd() {
    return roleCd;
  }

  public void setRoleCd(String roleCd) {
    this.roleCd = roleCd;
  }

  public PartyMaster relationShipToPrimaryPartyCd(String relationShipToPrimaryPartyCd) {
    this.relationShipToPrimaryPartyCd = relationShipToPrimaryPartyCd;
    return this;
  }

   /**
   * Get relationShipToPrimaryPartyCd
   * @return relationShipToPrimaryPartyCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getRelationShipToPrimaryPartyCd() {
    return relationShipToPrimaryPartyCd;
  }

  public void setRelationShipToPrimaryPartyCd(String relationShipToPrimaryPartyCd) {
    this.relationShipToPrimaryPartyCd = relationShipToPrimaryPartyCd;
  }

  public PartyMaster educationTypeCd(String educationTypeCd) {
    this.educationTypeCd = educationTypeCd;
    return this;
  }

   /**
   * Get educationTypeCd
   * @return educationTypeCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getEducationTypeCd() {
    return educationTypeCd;
  }

  public void setEducationTypeCd(String educationTypeCd) {
    this.educationTypeCd = educationTypeCd;
  }

  public PartyMaster professionCd(String professionCd) {
    this.professionCd = professionCd;
    return this;
  }

   /**
   * Get professionCd
   * @return professionCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getProfessionCd() {
    return professionCd;
  }

  public void setProfessionCd(String professionCd) {
    this.professionCd = professionCd;
  }

  public PartyMaster maritalStatusCd(String maritalStatusCd) {
    this.maritalStatusCd = maritalStatusCd;
    return this;
  }

   /**
   * Get maritalStatusCd
   * @return maritalStatusCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getMaritalStatusCd() {
    return maritalStatusCd;
  }

  public void setMaritalStatusCd(String maritalStatusCd) {
    this.maritalStatusCd = maritalStatusCd;
  }

  public PartyMaster natlCntryCd(String natlCntryCd) {
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

  public PartyMaster czshpCntryCd(String czshpCntryCd) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyMaster partyMaster = (PartyMaster) o;
    return Objects.equals(this.partyId, partyMaster.partyId) &&
        Objects.equals(this.partyTypeCd, partyMaster.partyTypeCd) &&
        Objects.equals(this.genderCd, partyMaster.genderCd) &&
        Objects.equals(this.ethnicCd, partyMaster.ethnicCd) &&
        Objects.equals(this.birthdate, partyMaster.birthdate) &&
        Objects.equals(this.languageCd, partyMaster.languageCd) &&
        Objects.equals(this.birthCountryCd, partyMaster.birthCountryCd) &&
        Objects.equals(this.tzOffset, partyMaster.tzOffset) &&
        Objects.equals(this.partyIsMinorFlg, partyMaster.partyIsMinorFlg) &&
        Objects.equals(this.userId, partyMaster.userId) &&
        Objects.equals(this.userPasswd, partyMaster.userPasswd) &&
        Objects.equals(this.userPin, partyMaster.userPin) &&
        Objects.equals(this.statusCd, partyMaster.statusCd) &&
        Objects.equals(this.primaryOnAccount, partyMaster.primaryOnAccount) &&
        Objects.equals(this.roleCd, partyMaster.roleCd) &&
        Objects.equals(this.relationShipToPrimaryPartyCd, partyMaster.relationShipToPrimaryPartyCd) &&
        Objects.equals(this.educationTypeCd, partyMaster.educationTypeCd) &&
        Objects.equals(this.professionCd, partyMaster.professionCd) &&
        Objects.equals(this.maritalStatusCd, partyMaster.maritalStatusCd) &&
        Objects.equals(this.natlCntryCd, partyMaster.natlCntryCd) &&
        Objects.equals(this.czshpCntryCd, partyMaster.czshpCntryCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, partyTypeCd, genderCd, ethnicCd, birthdate, languageCd, birthCountryCd, tzOffset, partyIsMinorFlg, userId, userPasswd, userPin, statusCd, primaryOnAccount, roleCd, relationShipToPrimaryPartyCd, educationTypeCd, professionCd, maritalStatusCd, natlCntryCd, czshpCntryCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyMaster {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    partyTypeCd: ").append(toIndentedString(partyTypeCd)).append("\n");
    sb.append("    genderCd: ").append(toIndentedString(genderCd)).append("\n");
    sb.append("    ethnicCd: ").append(toIndentedString(ethnicCd)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    birthCountryCd: ").append(toIndentedString(birthCountryCd)).append("\n");
    sb.append("    tzOffset: ").append(toIndentedString(tzOffset)).append("\n");
    sb.append("    partyIsMinorFlg: ").append(toIndentedString(partyIsMinorFlg)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userPasswd: ").append(toIndentedString(userPasswd)).append("\n");
    sb.append("    userPin: ").append(toIndentedString(userPin)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    primaryOnAccount: ").append(toIndentedString(primaryOnAccount)).append("\n");
    sb.append("    roleCd: ").append(toIndentedString(roleCd)).append("\n");
    sb.append("    relationShipToPrimaryPartyCd: ").append(toIndentedString(relationShipToPrimaryPartyCd)).append("\n");
    sb.append("    educationTypeCd: ").append(toIndentedString(educationTypeCd)).append("\n");
    sb.append("    professionCd: ").append(toIndentedString(professionCd)).append("\n");
    sb.append("    maritalStatusCd: ").append(toIndentedString(maritalStatusCd)).append("\n");
    sb.append("    natlCntryCd: ").append(toIndentedString(natlCntryCd)).append("\n");
    sb.append("    czshpCntryCd: ").append(toIndentedString(czshpCntryCd)).append("\n");
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

