package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyMaster   {
  
  private String partyId = null;
  private String partyTypeCd = null;
  private String genderCd = null;
  private String ethnicCd = null;
  private String birthdate = null;
  private String languageCd = null;
  private String birthCountryCd = null;
  private String tzOffset = null;
  private String partyIsMinorFlg = null;
  private String userId = null;
  private String userPasswd = null;
  private String userPin = null;
  private String statusCd = null;
  private String primaryOnAccount = null;
  private String roleCd = null;
  private String relationShipToPrimaryPartyCd = null;
  private String educationTypeCd = null;
  private String professionCd = null;
  private String maritalStatusCd = null;
  private String natlCntryCd = null;
  private String czshpCntryCd = null;
  private String activationTokenURL = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyId")
  public String getPartyId() {
    return partyId;
  }
  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyTypeCd")
  public String getPartyTypeCd() {
    return partyTypeCd;
  }
  public void setPartyTypeCd(String partyTypeCd) {
    this.partyTypeCd = partyTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("genderCd")
  public String getGenderCd() {
    return genderCd;
  }
  public void setGenderCd(String genderCd) {
    this.genderCd = genderCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ethnicCd")
  public String getEthnicCd() {
    return ethnicCd;
  }
  public void setEthnicCd(String ethnicCd) {
    this.ethnicCd = ethnicCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("birthdate")
  public String getBirthdate() {
    return birthdate;
  }
  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
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
  @JsonProperty("birthCountryCd")
  public String getBirthCountryCd() {
    return birthCountryCd;
  }
  public void setBirthCountryCd(String birthCountryCd) {
    this.birthCountryCd = birthCountryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("tzOffset")
  public String getTzOffset() {
    return tzOffset;
  }
  public void setTzOffset(String tzOffset) {
    this.tzOffset = tzOffset;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyIsMinorFlg")
  public String getPartyIsMinorFlg() {
    return partyIsMinorFlg;
  }
  public void setPartyIsMinorFlg(String partyIsMinorFlg) {
    this.partyIsMinorFlg = partyIsMinorFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("userId")
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("userPasswd")
  public String getUserPasswd() {
    return userPasswd;
  }
  public void setUserPasswd(String userPasswd) {
    this.userPasswd = userPasswd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("userPin")
  public String getUserPin() {
    return userPin;
  }
  public void setUserPin(String userPin) {
    this.userPin = userPin;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("statusCd")
  public String getStatusCd() {
    return statusCd;
  }
  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("primaryOnAccount")
  public String getPrimaryOnAccount() {
    return primaryOnAccount;
  }
  public void setPrimaryOnAccount(String primaryOnAccount) {
    this.primaryOnAccount = primaryOnAccount;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("roleCd")
  public String getRoleCd() {
    return roleCd;
  }
  public void setRoleCd(String roleCd) {
    this.roleCd = roleCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("relationShipToPrimaryPartyCd")
  public String getRelationShipToPrimaryPartyCd() {
    return relationShipToPrimaryPartyCd;
  }
  public void setRelationShipToPrimaryPartyCd(String relationShipToPrimaryPartyCd) {
    this.relationShipToPrimaryPartyCd = relationShipToPrimaryPartyCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("educationTypeCd")
  public String getEducationTypeCd() {
    return educationTypeCd;
  }
  public void setEducationTypeCd(String educationTypeCd) {
    this.educationTypeCd = educationTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("professionCd")
  public String getProfessionCd() {
    return professionCd;
  }
  public void setProfessionCd(String professionCd) {
    this.professionCd = professionCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("maritalStatusCd")
  public String getMaritalStatusCd() {
    return maritalStatusCd;
  }
  public void setMaritalStatusCd(String maritalStatusCd) {
    this.maritalStatusCd = maritalStatusCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("natlCntryCd")
  public String getNatlCntryCd() {
    return natlCntryCd;
  }
  public void setNatlCntryCd(String natlCntryCd) {
    this.natlCntryCd = natlCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("czshpCntryCd")
  public String getCzshpCntryCd() {
    return czshpCntryCd;
  }
  public void setCzshpCntryCd(String czshpCntryCd) {
    this.czshpCntryCd = czshpCntryCd;
  }

  
  /**
   * Amway login system activation token URL
   **/
  
  @ApiModelProperty(value = "Amway login system activation token URL")
  @JsonProperty("activationTokenURL")
  public String getActivationTokenURL() {
    return activationTokenURL;
  }
  public void setActivationTokenURL(String activationTokenURL) {
    this.activationTokenURL = activationTokenURL;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyMaster partyMaster = (PartyMaster) o;
    return Objects.equals(partyId, partyMaster.partyId) &&
        Objects.equals(partyTypeCd, partyMaster.partyTypeCd) &&
        Objects.equals(genderCd, partyMaster.genderCd) &&
        Objects.equals(ethnicCd, partyMaster.ethnicCd) &&
        Objects.equals(birthdate, partyMaster.birthdate) &&
        Objects.equals(languageCd, partyMaster.languageCd) &&
        Objects.equals(birthCountryCd, partyMaster.birthCountryCd) &&
        Objects.equals(tzOffset, partyMaster.tzOffset) &&
        Objects.equals(partyIsMinorFlg, partyMaster.partyIsMinorFlg) &&
        Objects.equals(userId, partyMaster.userId) &&
        Objects.equals(userPasswd, partyMaster.userPasswd) &&
        Objects.equals(userPin, partyMaster.userPin) &&
        Objects.equals(statusCd, partyMaster.statusCd) &&
        Objects.equals(primaryOnAccount, partyMaster.primaryOnAccount) &&
        Objects.equals(roleCd, partyMaster.roleCd) &&
        Objects.equals(relationShipToPrimaryPartyCd, partyMaster.relationShipToPrimaryPartyCd) &&
        Objects.equals(educationTypeCd, partyMaster.educationTypeCd) &&
        Objects.equals(professionCd, partyMaster.professionCd) &&
        Objects.equals(maritalStatusCd, partyMaster.maritalStatusCd) &&
        Objects.equals(natlCntryCd, partyMaster.natlCntryCd) &&
        Objects.equals(czshpCntryCd, partyMaster.czshpCntryCd) &&
        Objects.equals(activationTokenURL, partyMaster.activationTokenURL);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, partyTypeCd, genderCd, ethnicCd, birthdate, languageCd, birthCountryCd, tzOffset, partyIsMinorFlg, userId, userPasswd, userPin, statusCd, primaryOnAccount, roleCd, relationShipToPrimaryPartyCd, educationTypeCd, professionCd, maritalStatusCd, natlCntryCd, czshpCntryCd, activationTokenURL);
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
    sb.append("    activationTokenURL: ").append(toIndentedString(activationTokenURL)).append("\n");
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

