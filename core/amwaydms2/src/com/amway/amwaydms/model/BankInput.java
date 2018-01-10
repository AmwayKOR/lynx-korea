package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BankInput   {
  
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
  private String statusFlg = null;
  private String bankAcctId = null;
  private List<String> accountUseCodes = new ArrayList<String>();

  
  /**
   * Bank Account Type
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account Type")
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
   * Bank Account Effective date time in UTC such '2016-12-29T14:53:00-02:00'
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account Effective date time in UTC such '2016-12-29T14:53:00-02:00'")
  @JsonProperty("effectiveStartDate")
  public String getEffectiveStartDate() {
    return effectiveStartDate;
  }
  public void setEffectiveStartDate(String effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
  }

  
  /**
   * Date Time in UTC such '2016-12-29T14:53:00-02:00'
   **/
  
  @ApiModelProperty(value = "Date Time in UTC such '2016-12-29T14:53:00-02:00'")
  @JsonProperty("effectiveEndDate")
  public String getEffectiveEndDate() {
    return effectiveEndDate;
  }
  public void setEffectiveEndDate(String effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
  }

  
  /**
   * Bank Account Primary Routing
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account Primary Routing")
  @JsonProperty("primaryRoutingNum")
  public String getPrimaryRoutingNum() {
    return primaryRoutingNum;
  }
  public void setPrimaryRoutingNum(String primaryRoutingNum) {
    this.primaryRoutingNum = primaryRoutingNum;
  }

  
  /**
   * Bank Account Issueing Bank Id
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account Issueing Bank Id")
  @JsonProperty("issuingBankId")
  public String getIssuingBankId() {
    return issuingBankId;
  }
  public void setIssuingBankId(String issuingBankId) {
    this.issuingBankId = issuingBankId;
  }

  
  /**
   * Bank Account Issueing Bank name
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account Issueing Bank name")
  @JsonProperty("issuingBankName")
  public String getIssuingBankName() {
    return issuingBankName;
  }
  public void setIssuingBankName(String issuingBankName) {
    this.issuingBankName = issuingBankName;
  }

  
  /**
   * Bank Account Branch code
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account Branch code")
  @JsonProperty("bankBranchCode")
  public String getBankBranchCode() {
    return bankBranchCode;
  }
  public void setBankBranchCode(String bankBranchCode) {
    this.bankBranchCode = bankBranchCode;
  }

  
  /**
   * Bank Account Branch name
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account Branch name")
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
   * Bank Account holder name
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account holder name")
  @JsonProperty("bankAcctHolderName")
  public String getBankAcctHolderName() {
    return bankAcctHolderName;
  }
  public void setBankAcctHolderName(String bankAcctHolderName) {
    this.bankAcctHolderName = bankAcctHolderName;
  }

  
  /**
   * Bank Account name
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account name")
  @JsonProperty("bankAcctName")
  public String getBankAcctName() {
    return bankAcctName;
  }
  public void setBankAcctName(String bankAcctName) {
    this.bankAcctName = bankAcctName;
  }

  
  /**
   * Bank Account ISO currency code
   **/
  
  @ApiModelProperty(required = true, value = "Bank Account ISO currency code")
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
  @JsonProperty("statusFlg")
  public String getStatusFlg() {
    return statusFlg;
  }
  public void setStatusFlg(String statusFlg) {
    this.statusFlg = statusFlg;
  }

  
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
   * String delimiter of BankUsesageCode.  Please see <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BAACU'>Bank Account Usage codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "String delimiter of BankUsesageCode.  Please see <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BAACU'>Bank Account Usage codes</a>")
  @JsonProperty("accountUseCodes")
  public List<String> getAccountUseCodes() {
    return accountUseCodes;
  }
  public void setAccountUseCodes(List<String> accountUseCodes) {
    this.accountUseCodes = accountUseCodes;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankInput bankInput = (BankInput) o;
    return Objects.equals(bankAcctTypeCd, bankInput.bankAcctTypeCd) &&
        Objects.equals(bankAcctNum, bankInput.bankAcctNum) &&
        Objects.equals(effectiveStartDate, bankInput.effectiveStartDate) &&
        Objects.equals(effectiveEndDate, bankInput.effectiveEndDate) &&
        Objects.equals(primaryRoutingNum, bankInput.primaryRoutingNum) &&
        Objects.equals(issuingBankId, bankInput.issuingBankId) &&
        Objects.equals(issuingBankName, bankInput.issuingBankName) &&
        Objects.equals(bankBranchCode, bankInput.bankBranchCode) &&
        Objects.equals(bankBranchName, bankInput.bankBranchName) &&
        Objects.equals(secondaryRoutingNum, bankInput.secondaryRoutingNum) &&
        Objects.equals(bankAcctHolderName, bankInput.bankAcctHolderName) &&
        Objects.equals(bankAcctName, bankInput.bankAcctName) &&
        Objects.equals(currencyCd, bankInput.currencyCd) &&
        Objects.equals(statusFlg, bankInput.statusFlg) &&
        Objects.equals(bankAcctId, bankInput.bankAcctId) &&
        Objects.equals(accountUseCodes, bankInput.accountUseCodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankAcctTypeCd, bankAcctNum, effectiveStartDate, effectiveEndDate, primaryRoutingNum, issuingBankId, issuingBankName, bankBranchCode, bankBranchName, secondaryRoutingNum, bankAcctHolderName, bankAcctName, currencyCd, statusFlg, bankAcctId, accountUseCodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankInput {\n");
    
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
    sb.append("    statusFlg: ").append(toIndentedString(statusFlg)).append("\n");
    sb.append("    bankAcctId: ").append(toIndentedString(bankAcctId)).append("\n");
    sb.append("    accountUseCodes: ").append(toIndentedString(accountUseCodes)).append("\n");
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

