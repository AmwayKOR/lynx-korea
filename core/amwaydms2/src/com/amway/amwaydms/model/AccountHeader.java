package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountHeader   {
  
  private String salesPlanAff = null;
  private String accountTypeCd = null;
  private String cntryCd = null;
  private String languageCd = null;
  private String currencyCd = null;
  private String lglEntityType = null;
  private String loaCd = null;
  private String statusCd = null;
  private String aboEntryDate = null;
  private String company = null;
  private String includeExcludeFlg = null;
  private String userPin = null;
  private String blackListFlg = null;
  private String accountSubTypeCd = null;
  private String busEntityNum = null;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountHeader accountHeader = (AccountHeader) o;
    return Objects.equals(salesPlanAff, accountHeader.salesPlanAff) &&
        Objects.equals(accountTypeCd, accountHeader.accountTypeCd) &&
        Objects.equals(cntryCd, accountHeader.cntryCd) &&
        Objects.equals(languageCd, accountHeader.languageCd) &&
        Objects.equals(currencyCd, accountHeader.currencyCd) &&
        Objects.equals(lglEntityType, accountHeader.lglEntityType) &&
        Objects.equals(loaCd, accountHeader.loaCd) &&
        Objects.equals(statusCd, accountHeader.statusCd) &&
        Objects.equals(aboEntryDate, accountHeader.aboEntryDate) &&
        Objects.equals(company, accountHeader.company) &&
        Objects.equals(includeExcludeFlg, accountHeader.includeExcludeFlg) &&
        Objects.equals(userPin, accountHeader.userPin) &&
        Objects.equals(blackListFlg, accountHeader.blackListFlg) &&
        Objects.equals(accountSubTypeCd, accountHeader.accountSubTypeCd) &&
        Objects.equals(busEntityNum, accountHeader.busEntityNum) &&
        Objects.equals(userId, accountHeader.userId) &&
        Objects.equals(aboExpireDate, accountHeader.aboExpireDate) &&
        Objects.equals(accountCreditLimit, accountHeader.accountCreditLimit) &&
        Objects.equals(accountCreditStatusCd, accountHeader.accountCreditStatusCd) &&
        Objects.equals(accountMissingInfoFlg, accountHeader.accountMissingInfoFlg) &&
        Objects.equals(orderCreditLimit, accountHeader.orderCreditLimit) &&
        Objects.equals(aboNum, accountHeader.aboNum) &&
        Objects.equals(primaryPersonPartyId, accountHeader.primaryPersonPartyId) &&
        Objects.equals(lateRenewalEligibleFlg, accountHeader.lateRenewalEligibleFlg) &&
        Objects.equals(renewalFlag, accountHeader.renewalFlag) &&
        Objects.equals(homeCntryCd, accountHeader.homeCntryCd) &&
        Objects.equals(homeAboNum, accountHeader.homeAboNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(salesPlanAff, accountTypeCd, cntryCd, languageCd, currencyCd, lglEntityType, loaCd, statusCd, aboEntryDate, company, includeExcludeFlg, userPin, blackListFlg, accountSubTypeCd, busEntityNum, userId, aboExpireDate, accountCreditLimit, accountCreditStatusCd, accountMissingInfoFlg, orderCreditLimit, aboNum, primaryPersonPartyId, lateRenewalEligibleFlg, renewalFlag, homeCntryCd, homeAboNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountHeader {\n");
    
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    accountTypeCd: ").append(toIndentedString(accountTypeCd)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    currencyCd: ").append(toIndentedString(currencyCd)).append("\n");
    sb.append("    lglEntityType: ").append(toIndentedString(lglEntityType)).append("\n");
    sb.append("    loaCd: ").append(toIndentedString(loaCd)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    aboEntryDate: ").append(toIndentedString(aboEntryDate)).append("\n");
    sb.append("    company: ").append(toIndentedString(company)).append("\n");
    sb.append("    includeExcludeFlg: ").append(toIndentedString(includeExcludeFlg)).append("\n");
    sb.append("    userPin: ").append(toIndentedString(userPin)).append("\n");
    sb.append("    blackListFlg: ").append(toIndentedString(blackListFlg)).append("\n");
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
    sb.append("    busEntityNum: ").append(toIndentedString(busEntityNum)).append("\n");
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

