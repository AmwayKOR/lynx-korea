package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyUpdateRequest   {
  
  private String partyTypeCd = null;
  private String genderCd = null;
  private String birthDate = null;
  private String birthCountryCd = null;
  private String ethnicCd = null;
  private String roleCd = null;
  private String tzOffset = null;
  private String userId = null;
  private String userPasswd = null;
  private String languageCd = null;
  private String userPin = null;
  private String partyIsMinorFlg = null;
  private String educationTypeCd = null;
  private String professionCd = null;
  private String maritalStatusCd = null;
  private String natlCntryCd = null;
  private String czshpCntryCd = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PARTY'>Reference to Party Type Codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PARTY'>Reference to Party Type Codes</a>")
  @JsonProperty("partyTypeCd")
  public String getPartyTypeCd() {
    return partyTypeCd;
  }
  public void setPartyTypeCd(String partyTypeCd) {
    this.partyTypeCd = partyTypeCd;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=GNDER'>Reference to Gender</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=GNDER'>Reference to Gender</a>")
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
  @JsonProperty("birthDate")
  public String getBirthDate() {
    return birthDate;
  }
  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
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
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ETCTY'>Reference to Ethnicity</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ETCTY'>Reference to Ethnicity</a>")
  @JsonProperty("ethnicCd")
  public String getEthnicCd() {
    return ethnicCd;
  }
  public void setEthnicCd(String ethnicCd) {
    this.ethnicCd = ethnicCd;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ROLCD'>Reference to Role Code</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ROLCD'>Reference to Role Code</a>")
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
  @JsonProperty("partyIsMinorFlg")
  public String getPartyIsMinorFlg() {
    return partyIsMinorFlg;
  }
  public void setPartyIsMinorFlg(String partyIsMinorFlg) {
    this.partyIsMinorFlg = partyIsMinorFlg;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=EDUTY'>Reference to Education Type Code</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=EDUTY'>Reference to Education Type Code</a>")
  @JsonProperty("educationTypeCd")
  public String getEducationTypeCd() {
    return educationTypeCd;
  }
  public void setEducationTypeCd(String educationTypeCd) {
    this.educationTypeCd = educationTypeCd;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PROFS'>Reference to Professional Code</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PROFS'>Reference to Professional Code</a>")
  @JsonProperty("professionCd")
  public String getProfessionCd() {
    return professionCd;
  }
  public void setProfessionCd(String professionCd) {
    this.professionCd = professionCd;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=MARST'>Reference to Marital Status Code</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=MARST'>Reference to Marital Status Code</a>")
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyUpdateRequest partyUpdateRequest = (PartyUpdateRequest) o;
    return Objects.equals(partyTypeCd, partyUpdateRequest.partyTypeCd) &&
        Objects.equals(genderCd, partyUpdateRequest.genderCd) &&
        Objects.equals(birthDate, partyUpdateRequest.birthDate) &&
        Objects.equals(birthCountryCd, partyUpdateRequest.birthCountryCd) &&
        Objects.equals(ethnicCd, partyUpdateRequest.ethnicCd) &&
        Objects.equals(roleCd, partyUpdateRequest.roleCd) &&
        Objects.equals(tzOffset, partyUpdateRequest.tzOffset) &&
        Objects.equals(userId, partyUpdateRequest.userId) &&
        Objects.equals(userPasswd, partyUpdateRequest.userPasswd) &&
        Objects.equals(languageCd, partyUpdateRequest.languageCd) &&
        Objects.equals(userPin, partyUpdateRequest.userPin) &&
        Objects.equals(partyIsMinorFlg, partyUpdateRequest.partyIsMinorFlg) &&
        Objects.equals(educationTypeCd, partyUpdateRequest.educationTypeCd) &&
        Objects.equals(professionCd, partyUpdateRequest.professionCd) &&
        Objects.equals(maritalStatusCd, partyUpdateRequest.maritalStatusCd) &&
        Objects.equals(natlCntryCd, partyUpdateRequest.natlCntryCd) &&
        Objects.equals(czshpCntryCd, partyUpdateRequest.czshpCntryCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyTypeCd, genderCd, birthDate, birthCountryCd, ethnicCd, roleCd, tzOffset, userId, userPasswd, languageCd, userPin, partyIsMinorFlg, educationTypeCd, professionCd, maritalStatusCd, natlCntryCd, czshpCntryCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyUpdateRequest {\n");
    
    sb.append("    partyTypeCd: ").append(toIndentedString(partyTypeCd)).append("\n");
    sb.append("    genderCd: ").append(toIndentedString(genderCd)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    birthCountryCd: ").append(toIndentedString(birthCountryCd)).append("\n");
    sb.append("    ethnicCd: ").append(toIndentedString(ethnicCd)).append("\n");
    sb.append("    roleCd: ").append(toIndentedString(roleCd)).append("\n");
    sb.append("    tzOffset: ").append(toIndentedString(tzOffset)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userPasswd: ").append(toIndentedString(userPasswd)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    userPin: ").append(toIndentedString(userPin)).append("\n");
    sb.append("    partyIsMinorFlg: ").append(toIndentedString(partyIsMinorFlg)).append("\n");
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

