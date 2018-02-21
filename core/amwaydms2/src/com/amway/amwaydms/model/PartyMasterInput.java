package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.ConnectIdInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyMasterInput   {
  
  private Long partyId = null;
  private String partyTypeCd = null;
  private String genderCd = null;
  private String birthDate = null;
  private String roleCd = null;
  private String birthCountryCd = null;
  private String ethnicCd = null;
  private String relationShipToPrimaryCd = null;
  private String userId = null;
  private String userPasswd = null;
  private String userPin = null;
  private String tzOffset = null;
  private String languageCd = null;
  private String partyIsMinorFlg = null;
  private ConnectIdInput connectIdInput = null;
  private String educationTypeCd = null;
  private String professionCd = null;
  private String maritalStatusCd = null;
  private String natlCntryCd = null;
  private String czshpCntryCd = null;
  private String electronicSignature = null;

  
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
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PARTY'>Reference to Party Type</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PARTY'>Reference to Party Type</a>")
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
   * Birth date in UTC such as '1957-05-28T00:00:00-02:00'
   **/
  
  @ApiModelProperty(value = "Birth date in UTC such as '1957-05-28T00:00:00-02:00'")
  @JsonProperty("birthDate")
  public String getBirthDate() {
    return birthDate;
  }
  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
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
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("relationShipToPrimaryCd")
  public String getRelationShipToPrimaryCd() {
    return relationShipToPrimaryCd;
  }
  public void setRelationShipToPrimaryCd(String relationShipToPrimaryCd) {
    this.relationShipToPrimaryCd = relationShipToPrimaryCd;
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
  @JsonProperty("connectIdInput")
  public ConnectIdInput getConnectIdInput() {
    return connectIdInput;
  }
  public void setConnectIdInput(ConnectIdInput connectIdInput) {
    this.connectIdInput = connectIdInput;
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

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("electronicSignature")
  public String getElectronicSignature() {
    return electronicSignature;
  }
  public void setElectronicSignature(String electronicSignature) {
    this.electronicSignature = electronicSignature;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyMasterInput partyMasterInput = (PartyMasterInput) o;
    return Objects.equals(partyId, partyMasterInput.partyId) &&
        Objects.equals(partyTypeCd, partyMasterInput.partyTypeCd) &&
        Objects.equals(genderCd, partyMasterInput.genderCd) &&
        Objects.equals(birthDate, partyMasterInput.birthDate) &&
        Objects.equals(roleCd, partyMasterInput.roleCd) &&
        Objects.equals(birthCountryCd, partyMasterInput.birthCountryCd) &&
        Objects.equals(ethnicCd, partyMasterInput.ethnicCd) &&
        Objects.equals(relationShipToPrimaryCd, partyMasterInput.relationShipToPrimaryCd) &&
        Objects.equals(userId, partyMasterInput.userId) &&
        Objects.equals(userPasswd, partyMasterInput.userPasswd) &&
        Objects.equals(userPin, partyMasterInput.userPin) &&
        Objects.equals(tzOffset, partyMasterInput.tzOffset) &&
        Objects.equals(languageCd, partyMasterInput.languageCd) &&
        Objects.equals(partyIsMinorFlg, partyMasterInput.partyIsMinorFlg) &&
        Objects.equals(connectIdInput, partyMasterInput.connectIdInput) &&
        Objects.equals(educationTypeCd, partyMasterInput.educationTypeCd) &&
        Objects.equals(professionCd, partyMasterInput.professionCd) &&
        Objects.equals(maritalStatusCd, partyMasterInput.maritalStatusCd) &&
        Objects.equals(natlCntryCd, partyMasterInput.natlCntryCd) &&
        Objects.equals(czshpCntryCd, partyMasterInput.czshpCntryCd) &&
        Objects.equals(electronicSignature, partyMasterInput.electronicSignature);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, partyTypeCd, genderCd, birthDate, roleCd, birthCountryCd, ethnicCd, relationShipToPrimaryCd, userId, userPasswd, userPin, tzOffset, languageCd, partyIsMinorFlg, connectIdInput, educationTypeCd, professionCd, maritalStatusCd, natlCntryCd, czshpCntryCd, electronicSignature);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyMasterInput {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
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
    sb.append("    connectIdInput: ").append(toIndentedString(connectIdInput)).append("\n");
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

