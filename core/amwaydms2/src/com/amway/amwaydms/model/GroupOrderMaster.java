package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class GroupOrderMaster   {
  
  private String salesPlanAff = null;
  private String accountTypeCd = null;
  private String cntryCd = null;
  private String languageCd = null;
  private String currencyCd = null;
  private String lglEntityType = null;
  private String loaCd = null;
  private String statusCd = null;
  private String aboEntryDate = null;
  private String accountName = null;
  private String company = null;
  private String includeExcludeFlg = null;
  private String userPin = null;
  private String blackListFlg = null;
  private String accountSubTypeCd = null;
  private String busEntityNum = null;
  private String segmentCd = null;
  private String regdSpnIboNo = null;
  private String regdlntlSpnAffNo = null;
  private String regdlntlSpnIboNo = null;
  private String regdlntlPrmyBusflg = null;
  private String userId = null;
  private String aboExpireDate = null;
  private String accountCreditLimit = null;
  private String accountCreditStatusCd = null;
  private String accountMissingInfoFlg = null;
  private String orderCreditLimit = null;
  private String aboNum = null;
  private String primaryPersonPartyId = null;
  private String lateRenewalEligibleFlg = null;
  private String renewalFlag = null;
  private String homeCntryCd = null;
  private String homeAboNum = null;
  private String prevAccountSubTypeCd = null;
  private String accountSubTypeConversionDate = null;
  private String aboRenewalDate = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanAff")
  public String getSalesPlanAff() {
    return salesPlanAff;
  }
  public void setSalesPlanAff(String salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountTypeCd")
  public String getAccountTypeCd() {
    return accountTypeCd;
  }
  public void setAccountTypeCd(String accountTypeCd) {
    this.accountTypeCd = accountTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
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
  @JsonProperty("currencyCd")
  public String getCurrencyCd() {
    return currencyCd;
  }
  public void setCurrencyCd(String currencyCd) {
    this.currencyCd = currencyCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("lglEntityType")
  public String getLglEntityType() {
    return lglEntityType;
  }
  public void setLglEntityType(String lglEntityType) {
    this.lglEntityType = lglEntityType;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("loaCd")
  public String getLoaCd() {
    return loaCd;
  }
  public void setLoaCd(String loaCd) {
    this.loaCd = loaCd;
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
  @JsonProperty("aboEntryDate")
  public String getAboEntryDate() {
    return aboEntryDate;
  }
  public void setAboEntryDate(String aboEntryDate) {
    this.aboEntryDate = aboEntryDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountName")
  public String getAccountName() {
    return accountName;
  }
  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("company")
  public String getCompany() {
    return company;
  }
  public void setCompany(String company) {
    this.company = company;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("includeExcludeFlg")
  public String getIncludeExcludeFlg() {
    return includeExcludeFlg;
  }
  public void setIncludeExcludeFlg(String includeExcludeFlg) {
    this.includeExcludeFlg = includeExcludeFlg;
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
  @JsonProperty("blackListFlg")
  public String getBlackListFlg() {
    return blackListFlg;
  }
  public void setBlackListFlg(String blackListFlg) {
    this.blackListFlg = blackListFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountSubTypeCd")
  public String getAccountSubTypeCd() {
    return accountSubTypeCd;
  }
  public void setAccountSubTypeCd(String accountSubTypeCd) {
    this.accountSubTypeCd = accountSubTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("busEntityNum")
  public String getBusEntityNum() {
    return busEntityNum;
  }
  public void setBusEntityNum(String busEntityNum) {
    this.busEntityNum = busEntityNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("segmentCd")
  public String getSegmentCd() {
    return segmentCd;
  }
  public void setSegmentCd(String segmentCd) {
    this.segmentCd = segmentCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("regdSpnIboNo")
  public String getRegdSpnIboNo() {
    return regdSpnIboNo;
  }
  public void setRegdSpnIboNo(String regdSpnIboNo) {
    this.regdSpnIboNo = regdSpnIboNo;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("regdlntlSpnAffNo")
  public String getRegdlntlSpnAffNo() {
    return regdlntlSpnAffNo;
  }
  public void setRegdlntlSpnAffNo(String regdlntlSpnAffNo) {
    this.regdlntlSpnAffNo = regdlntlSpnAffNo;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("regdlntlSpnIboNo")
  public String getRegdlntlSpnIboNo() {
    return regdlntlSpnIboNo;
  }
  public void setRegdlntlSpnIboNo(String regdlntlSpnIboNo) {
    this.regdlntlSpnIboNo = regdlntlSpnIboNo;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("regdlntlPrmyBusflg")
  public String getRegdlntlPrmyBusflg() {
    return regdlntlPrmyBusflg;
  }
  public void setRegdlntlPrmyBusflg(String regdlntlPrmyBusflg) {
    this.regdlntlPrmyBusflg = regdlntlPrmyBusflg;
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
  @JsonProperty("aboExpireDate")
  public String getAboExpireDate() {
    return aboExpireDate;
  }
  public void setAboExpireDate(String aboExpireDate) {
    this.aboExpireDate = aboExpireDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountCreditLimit")
  public String getAccountCreditLimit() {
    return accountCreditLimit;
  }
  public void setAccountCreditLimit(String accountCreditLimit) {
    this.accountCreditLimit = accountCreditLimit;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountCreditStatusCd")
  public String getAccountCreditStatusCd() {
    return accountCreditStatusCd;
  }
  public void setAccountCreditStatusCd(String accountCreditStatusCd) {
    this.accountCreditStatusCd = accountCreditStatusCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountMissingInfoFlg")
  public String getAccountMissingInfoFlg() {
    return accountMissingInfoFlg;
  }
  public void setAccountMissingInfoFlg(String accountMissingInfoFlg) {
    this.accountMissingInfoFlg = accountMissingInfoFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("orderCreditLimit")
  public String getOrderCreditLimit() {
    return orderCreditLimit;
  }
  public void setOrderCreditLimit(String orderCreditLimit) {
    this.orderCreditLimit = orderCreditLimit;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("aboNum")
  public String getAboNum() {
    return aboNum;
  }
  public void setAboNum(String aboNum) {
    this.aboNum = aboNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("primaryPersonPartyId")
  public String getPrimaryPersonPartyId() {
    return primaryPersonPartyId;
  }
  public void setPrimaryPersonPartyId(String primaryPersonPartyId) {
    this.primaryPersonPartyId = primaryPersonPartyId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("lateRenewalEligibleFlg")
  public String getLateRenewalEligibleFlg() {
    return lateRenewalEligibleFlg;
  }
  public void setLateRenewalEligibleFlg(String lateRenewalEligibleFlg) {
    this.lateRenewalEligibleFlg = lateRenewalEligibleFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("renewalFlag")
  public String getRenewalFlag() {
    return renewalFlag;
  }
  public void setRenewalFlag(String renewalFlag) {
    this.renewalFlag = renewalFlag;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("homeCntryCd")
  public String getHomeCntryCd() {
    return homeCntryCd;
  }
  public void setHomeCntryCd(String homeCntryCd) {
    this.homeCntryCd = homeCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("homeAboNum")
  public String getHomeAboNum() {
    return homeAboNum;
  }
  public void setHomeAboNum(String homeAboNum) {
    this.homeAboNum = homeAboNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("prevAccountSubTypeCd")
  public String getPrevAccountSubTypeCd() {
    return prevAccountSubTypeCd;
  }
  public void setPrevAccountSubTypeCd(String prevAccountSubTypeCd) {
    this.prevAccountSubTypeCd = prevAccountSubTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountSubTypeConversionDate")
  public String getAccountSubTypeConversionDate() {
    return accountSubTypeConversionDate;
  }
  public void setAccountSubTypeConversionDate(String accountSubTypeConversionDate) {
    this.accountSubTypeConversionDate = accountSubTypeConversionDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("aboRenewalDate")
  public String getAboRenewalDate() {
    return aboRenewalDate;
  }
  public void setAboRenewalDate(String aboRenewalDate) {
    this.aboRenewalDate = aboRenewalDate;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupOrderMaster groupOrderMaster = (GroupOrderMaster) o;
    return Objects.equals(salesPlanAff, groupOrderMaster.salesPlanAff) &&
        Objects.equals(accountTypeCd, groupOrderMaster.accountTypeCd) &&
        Objects.equals(cntryCd, groupOrderMaster.cntryCd) &&
        Objects.equals(languageCd, groupOrderMaster.languageCd) &&
        Objects.equals(currencyCd, groupOrderMaster.currencyCd) &&
        Objects.equals(lglEntityType, groupOrderMaster.lglEntityType) &&
        Objects.equals(loaCd, groupOrderMaster.loaCd) &&
        Objects.equals(statusCd, groupOrderMaster.statusCd) &&
        Objects.equals(aboEntryDate, groupOrderMaster.aboEntryDate) &&
        Objects.equals(accountName, groupOrderMaster.accountName) &&
        Objects.equals(company, groupOrderMaster.company) &&
        Objects.equals(includeExcludeFlg, groupOrderMaster.includeExcludeFlg) &&
        Objects.equals(userPin, groupOrderMaster.userPin) &&
        Objects.equals(blackListFlg, groupOrderMaster.blackListFlg) &&
        Objects.equals(accountSubTypeCd, groupOrderMaster.accountSubTypeCd) &&
        Objects.equals(busEntityNum, groupOrderMaster.busEntityNum) &&
        Objects.equals(segmentCd, groupOrderMaster.segmentCd) &&
        Objects.equals(regdSpnIboNo, groupOrderMaster.regdSpnIboNo) &&
        Objects.equals(regdlntlSpnAffNo, groupOrderMaster.regdlntlSpnAffNo) &&
        Objects.equals(regdlntlSpnIboNo, groupOrderMaster.regdlntlSpnIboNo) &&
        Objects.equals(regdlntlPrmyBusflg, groupOrderMaster.regdlntlPrmyBusflg) &&
        Objects.equals(userId, groupOrderMaster.userId) &&
        Objects.equals(aboExpireDate, groupOrderMaster.aboExpireDate) &&
        Objects.equals(accountCreditLimit, groupOrderMaster.accountCreditLimit) &&
        Objects.equals(accountCreditStatusCd, groupOrderMaster.accountCreditStatusCd) &&
        Objects.equals(accountMissingInfoFlg, groupOrderMaster.accountMissingInfoFlg) &&
        Objects.equals(orderCreditLimit, groupOrderMaster.orderCreditLimit) &&
        Objects.equals(aboNum, groupOrderMaster.aboNum) &&
        Objects.equals(primaryPersonPartyId, groupOrderMaster.primaryPersonPartyId) &&
        Objects.equals(lateRenewalEligibleFlg, groupOrderMaster.lateRenewalEligibleFlg) &&
        Objects.equals(renewalFlag, groupOrderMaster.renewalFlag) &&
        Objects.equals(homeCntryCd, groupOrderMaster.homeCntryCd) &&
        Objects.equals(homeAboNum, groupOrderMaster.homeAboNum) &&
        Objects.equals(prevAccountSubTypeCd, groupOrderMaster.prevAccountSubTypeCd) &&
        Objects.equals(accountSubTypeConversionDate, groupOrderMaster.accountSubTypeConversionDate) &&
        Objects.equals(aboRenewalDate, groupOrderMaster.aboRenewalDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(salesPlanAff, accountTypeCd, cntryCd, languageCd, currencyCd, lglEntityType, loaCd, statusCd, aboEntryDate, accountName, company, includeExcludeFlg, userPin, blackListFlg, accountSubTypeCd, busEntityNum, segmentCd, regdSpnIboNo, regdlntlSpnAffNo, regdlntlSpnIboNo, regdlntlPrmyBusflg, userId, aboExpireDate, accountCreditLimit, accountCreditStatusCd, accountMissingInfoFlg, orderCreditLimit, aboNum, primaryPersonPartyId, lateRenewalEligibleFlg, renewalFlag, homeCntryCd, homeAboNum, prevAccountSubTypeCd, accountSubTypeConversionDate, aboRenewalDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupOrderMaster {\n");
    
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    accountTypeCd: ").append(toIndentedString(accountTypeCd)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    currencyCd: ").append(toIndentedString(currencyCd)).append("\n");
    sb.append("    lglEntityType: ").append(toIndentedString(lglEntityType)).append("\n");
    sb.append("    loaCd: ").append(toIndentedString(loaCd)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    aboEntryDate: ").append(toIndentedString(aboEntryDate)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    includeExcludeFlg: ").append(toIndentedString(includeExcludeFlg)).append("\n");
    sb.append("    userPin: ").append(toIndentedString(userPin)).append("\n");
    sb.append("    blackListFlg: ").append(toIndentedString(blackListFlg)).append("\n");
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
    sb.append("    busEntityNum: ").append(toIndentedString(busEntityNum)).append("\n");
    sb.append("    segmentCd: ").append(toIndentedString(segmentCd)).append("\n");
    sb.append("    regdSpnIboNo: ").append(toIndentedString(regdSpnIboNo)).append("\n");
    sb.append("    regdlntlSpnAffNo: ").append(toIndentedString(regdlntlSpnAffNo)).append("\n");
    sb.append("    regdlntlSpnIboNo: ").append(toIndentedString(regdlntlSpnIboNo)).append("\n");
    sb.append("    regdlntlPrmyBusflg: ").append(toIndentedString(regdlntlPrmyBusflg)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    aboExpireDate: ").append(toIndentedString(aboExpireDate)).append("\n");
    sb.append("    accountCreditLimit: ").append(toIndentedString(accountCreditLimit)).append("\n");
    sb.append("    accountCreditStatusCd: ").append(toIndentedString(accountCreditStatusCd)).append("\n");
    sb.append("    accountMissingInfoFlg: ").append(toIndentedString(accountMissingInfoFlg)).append("\n");
    sb.append("    orderCreditLimit: ").append(toIndentedString(orderCreditLimit)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    primaryPersonPartyId: ").append(toIndentedString(primaryPersonPartyId)).append("\n");
    sb.append("    lateRenewalEligibleFlg: ").append(toIndentedString(lateRenewalEligibleFlg)).append("\n");
    sb.append("    renewalFlag: ").append(toIndentedString(renewalFlag)).append("\n");
    sb.append("    homeCntryCd: ").append(toIndentedString(homeCntryCd)).append("\n");
    sb.append("    homeAboNum: ").append(toIndentedString(homeAboNum)).append("\n");
    sb.append("    prevAccountSubTypeCd: ").append(toIndentedString(prevAccountSubTypeCd)).append("\n");
    sb.append("    accountSubTypeConversionDate: ").append(toIndentedString(accountSubTypeConversionDate)).append("\n");
    sb.append("    aboRenewalDate: ").append(toIndentedString(aboRenewalDate)).append("\n");
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

