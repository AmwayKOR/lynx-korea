package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BankAccountValidation   {
  
  private String bankAcctTypeCd = null;
  private String bankAcctNum = null;
  private String bankAcctHolderName = null;
  private String bankAcctName = null;
  private String currencyCd = null;
  private String primaryRoutingNum = null;
  private String issuingBankId = null;
  private String bankBranchCode = null;

  
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
   * Bank Account Number
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account Number")
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
  @JsonProperty("bankBranchCode")
  public String getBankBranchCode() {
    return bankBranchCode;
  }
  public void setBankBranchCode(String bankBranchCode) {
    this.bankBranchCode = bankBranchCode;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccountValidation bankAccountValidation = (BankAccountValidation) o;
    return Objects.equals(bankAcctTypeCd, bankAccountValidation.bankAcctTypeCd) &&
        Objects.equals(bankAcctNum, bankAccountValidation.bankAcctNum) &&
        Objects.equals(bankAcctHolderName, bankAccountValidation.bankAcctHolderName) &&
        Objects.equals(bankAcctName, bankAccountValidation.bankAcctName) &&
        Objects.equals(currencyCd, bankAccountValidation.currencyCd) &&
        Objects.equals(primaryRoutingNum, bankAccountValidation.primaryRoutingNum) &&
        Objects.equals(issuingBankId, bankAccountValidation.issuingBankId) &&
        Objects.equals(bankBranchCode, bankAccountValidation.bankBranchCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankAcctTypeCd, bankAcctNum, bankAcctHolderName, bankAcctName, currencyCd, primaryRoutingNum, issuingBankId, bankBranchCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankAccountValidation {\n");
    
    sb.append("    bankAcctTypeCd: ").append(toIndentedString(bankAcctTypeCd)).append("\n");
    sb.append("    bankAcctNum: ").append(toIndentedString(bankAcctNum)).append("\n");
    sb.append("    bankAcctHolderName: ").append(toIndentedString(bankAcctHolderName)).append("\n");
    sb.append("    bankAcctName: ").append(toIndentedString(bankAcctName)).append("\n");
    sb.append("    currencyCd: ").append(toIndentedString(currencyCd)).append("\n");
    sb.append("    primaryRoutingNum: ").append(toIndentedString(primaryRoutingNum)).append("\n");
    sb.append("    issuingBankId: ").append(toIndentedString(issuingBankId)).append("\n");
    sb.append("    bankBranchCode: ").append(toIndentedString(bankBranchCode)).append("\n");
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

