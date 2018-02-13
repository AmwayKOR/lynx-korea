package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.BankUsage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BankAccount   {
  
  private String bankAcctId = null;
  private String partyId = null;
  private String bankAcctTypeCd = null;
  private String bankAcctNum = null;
  private String effectiveStartDate = null;
  private String effectiveEndDate = null;
  private String primaryRoutingNum = null;
  private String issuingBankId = null;
  private String issuingBankName = null;
  private String bankBranchCode = null;
  private String bankBranchName = null;
  private String secondaryRoutingNum = null;
  private String bankAcctHolderName = null;
  private String bankAcctName = null;
  private String currencyCd = null;
  private String bankCntryCd = null;
  private List<BankUsage> acctUsageList = new ArrayList<BankUsage>();

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankAcctId")
  public String getBankAcctId() {
    return bankAcctId;
  }
  public void setBankAcctId(String bankAcctId) {
    this.bankAcctId = bankAcctId;
  }

  
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
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BATCD'>Reference to Bank Account Type Codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BATCD'>Reference to Bank Account Type Codes</a>")
  @JsonProperty("bankAcctTypeCd")
  public String getBankAcctTypeCd() {
    return bankAcctTypeCd;
  }
  public void setBankAcctTypeCd(String bankAcctTypeCd) {
    this.bankAcctTypeCd = bankAcctTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankAcctNum")
  public String getBankAcctNum() {
    return bankAcctNum;
  }
  public void setBankAcctNum(String bankAcctNum) {
    this.bankAcctNum = bankAcctNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("effectiveStartDate")
  public String getEffectiveStartDate() {
    return effectiveStartDate;
  }
  public void setEffectiveStartDate(String effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("effectiveEndDate")
  public String getEffectiveEndDate() {
    return effectiveEndDate;
  }
  public void setEffectiveEndDate(String effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("primaryRoutingNum")
  public String getPrimaryRoutingNum() {
    return primaryRoutingNum;
  }
  public void setPrimaryRoutingNum(String primaryRoutingNum) {
    this.primaryRoutingNum = primaryRoutingNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("issuingBankId")
  public String getIssuingBankId() {
    return issuingBankId;
  }
  public void setIssuingBankId(String issuingBankId) {
    this.issuingBankId = issuingBankId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("issuingBankName")
  public String getIssuingBankName() {
    return issuingBankName;
  }
  public void setIssuingBankName(String issuingBankName) {
    this.issuingBankName = issuingBankName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankBranchCode")
  public String getBankBranchCode() {
    return bankBranchCode;
  }
  public void setBankBranchCode(String bankBranchCode) {
    this.bankBranchCode = bankBranchCode;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankBranchName")
  public String getBankBranchName() {
    return bankBranchName;
  }
  public void setBankBranchName(String bankBranchName) {
    this.bankBranchName = bankBranchName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("secondaryRoutingNum")
  public String getSecondaryRoutingNum() {
    return secondaryRoutingNum;
  }
  public void setSecondaryRoutingNum(String secondaryRoutingNum) {
    this.secondaryRoutingNum = secondaryRoutingNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankAcctHolderName")
  public String getBankAcctHolderName() {
    return bankAcctHolderName;
  }
  public void setBankAcctHolderName(String bankAcctHolderName) {
    this.bankAcctHolderName = bankAcctHolderName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankAcctName")
  public String getBankAcctName() {
    return bankAcctName;
  }
  public void setBankAcctName(String bankAcctName) {
    this.bankAcctName = bankAcctName;
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
  @JsonProperty("bankCntryCd")
  public String getBankCntryCd() {
    return bankCntryCd;
  }
  public void setBankCntryCd(String bankCntryCd) {
    this.bankCntryCd = bankCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("acctUsageList")
  public List<BankUsage> getAcctUsageList() {
    return acctUsageList;
  }
  public void setAcctUsageList(List<BankUsage> acctUsageList) {
    this.acctUsageList = acctUsageList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccount bankAccount = (BankAccount) o;
    return Objects.equals(bankAcctId, bankAccount.bankAcctId) &&
        Objects.equals(partyId, bankAccount.partyId) &&
        Objects.equals(bankAcctTypeCd, bankAccount.bankAcctTypeCd) &&
        Objects.equals(bankAcctNum, bankAccount.bankAcctNum) &&
        Objects.equals(effectiveStartDate, bankAccount.effectiveStartDate) &&
        Objects.equals(effectiveEndDate, bankAccount.effectiveEndDate) &&
        Objects.equals(primaryRoutingNum, bankAccount.primaryRoutingNum) &&
        Objects.equals(issuingBankId, bankAccount.issuingBankId) &&
        Objects.equals(issuingBankName, bankAccount.issuingBankName) &&
        Objects.equals(bankBranchCode, bankAccount.bankBranchCode) &&
        Objects.equals(bankBranchName, bankAccount.bankBranchName) &&
        Objects.equals(secondaryRoutingNum, bankAccount.secondaryRoutingNum) &&
        Objects.equals(bankAcctHolderName, bankAccount.bankAcctHolderName) &&
        Objects.equals(bankAcctName, bankAccount.bankAcctName) &&
        Objects.equals(currencyCd, bankAccount.currencyCd) &&
        Objects.equals(bankCntryCd, bankAccount.bankCntryCd) &&
        Objects.equals(acctUsageList, bankAccount.acctUsageList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankAcctId, partyId, bankAcctTypeCd, bankAcctNum, effectiveStartDate, effectiveEndDate, primaryRoutingNum, issuingBankId, issuingBankName, bankBranchCode, bankBranchName, secondaryRoutingNum, bankAcctHolderName, bankAcctName, currencyCd, bankCntryCd, acctUsageList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankAccount {\n");
    
    sb.append("    bankAcctId: ").append(toIndentedString(bankAcctId)).append("\n");
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    bankAcctTypeCd: ").append(toIndentedString(bankAcctTypeCd)).append("\n");
    sb.append("    bankAcctNum: ").append(toIndentedString(bankAcctNum)).append("\n");
    sb.append("    effectiveStartDate: ").append(toIndentedString(effectiveStartDate)).append("\n");
    sb.append("    effectiveEndDate: ").append(toIndentedString(effectiveEndDate)).append("\n");
    sb.append("    primaryRoutingNum: ").append(toIndentedString(primaryRoutingNum)).append("\n");
    sb.append("    issuingBankId: ").append(toIndentedString(issuingBankId)).append("\n");
    sb.append("    issuingBankName: ").append(toIndentedString(issuingBankName)).append("\n");
    sb.append("    bankBranchCode: ").append(toIndentedString(bankBranchCode)).append("\n");
    sb.append("    bankBranchName: ").append(toIndentedString(bankBranchName)).append("\n");
    sb.append("    secondaryRoutingNum: ").append(toIndentedString(secondaryRoutingNum)).append("\n");
    sb.append("    bankAcctHolderName: ").append(toIndentedString(bankAcctHolderName)).append("\n");
    sb.append("    bankAcctName: ").append(toIndentedString(bankAcctName)).append("\n");
    sb.append("    currencyCd: ").append(toIndentedString(currencyCd)).append("\n");
    sb.append("    bankCntryCd: ").append(toIndentedString(bankCntryCd)).append("\n");
    sb.append("    acctUsageList: ").append(toIndentedString(acctUsageList)).append("\n");
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

