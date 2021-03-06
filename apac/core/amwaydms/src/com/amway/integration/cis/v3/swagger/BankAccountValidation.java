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
 * BankAccountValidation
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class BankAccountValidation   {
  @SerializedName("bankAcctTypeCd")
  private String bankAcctTypeCd = null;

  @SerializedName("bankAcctNum")
  private String bankAcctNum = null;

  @SerializedName("bankAcctHolderName")
  private String bankAcctHolderName = null;

  @SerializedName("bankAcctName")
  private String bankAcctName = null;

  @SerializedName("currencyCd")
  private String currencyCd = null;

  @SerializedName("primaryRoutingNum")
  private String primaryRoutingNum = null;

  @SerializedName("issuingBankId")
  private String issuingBankId = null;

  @SerializedName("bankBranchCode")
  private String bankBranchCode = null;

  public BankAccountValidation bankAcctTypeCd(String bankAcctTypeCd) {
    this.bankAcctTypeCd = bankAcctTypeCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BATCD'>Reference to Bank Account Type Codes</a>
   * @return bankAcctTypeCd
  **/
  @ApiModelProperty(example = "null", required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BATCD'>Reference to Bank Account Type Codes</a>")
  public String getBankAcctTypeCd() {
    return bankAcctTypeCd;
  }

  public void setBankAcctTypeCd(String bankAcctTypeCd) {
    this.bankAcctTypeCd = bankAcctTypeCd;
  }

  public BankAccountValidation bankAcctNum(String bankAcctNum) {
    this.bankAcctNum = bankAcctNum;
    return this;
  }

   /**
   * Bank Account Number
   * @return bankAcctNum
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account Number")
  public String getBankAcctNum() {
    return bankAcctNum;
  }

  public void setBankAcctNum(String bankAcctNum) {
    this.bankAcctNum = bankAcctNum;
  }

  public BankAccountValidation bankAcctHolderName(String bankAcctHolderName) {
    this.bankAcctHolderName = bankAcctHolderName;
    return this;
  }

   /**
   * Get bankAcctHolderName
   * @return bankAcctHolderName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getBankAcctHolderName() {
    return bankAcctHolderName;
  }

  public void setBankAcctHolderName(String bankAcctHolderName) {
    this.bankAcctHolderName = bankAcctHolderName;
  }

  public BankAccountValidation bankAcctName(String bankAcctName) {
    this.bankAcctName = bankAcctName;
    return this;
  }

   /**
   * Get bankAcctName
   * @return bankAcctName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getBankAcctName() {
    return bankAcctName;
  }

  public void setBankAcctName(String bankAcctName) {
    this.bankAcctName = bankAcctName;
  }

  public BankAccountValidation currencyCd(String currencyCd) {
    this.currencyCd = currencyCd;
    return this;
  }

   /**
   * Get currencyCd
   * @return currencyCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCurrencyCd() {
    return currencyCd;
  }

  public void setCurrencyCd(String currencyCd) {
    this.currencyCd = currencyCd;
  }

  public BankAccountValidation primaryRoutingNum(String primaryRoutingNum) {
    this.primaryRoutingNum = primaryRoutingNum;
    return this;
  }

   /**
   * Get primaryRoutingNum
   * @return primaryRoutingNum
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPrimaryRoutingNum() {
    return primaryRoutingNum;
  }

  public void setPrimaryRoutingNum(String primaryRoutingNum) {
    this.primaryRoutingNum = primaryRoutingNum;
  }

  public BankAccountValidation issuingBankId(String issuingBankId) {
    this.issuingBankId = issuingBankId;
    return this;
  }

   /**
   * Get issuingBankId
   * @return issuingBankId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getIssuingBankId() {
    return issuingBankId;
  }

  public void setIssuingBankId(String issuingBankId) {
    this.issuingBankId = issuingBankId;
  }

  public BankAccountValidation bankBranchCode(String bankBranchCode) {
    this.bankBranchCode = bankBranchCode;
    return this;
  }

   /**
   * Get bankBranchCode
   * @return bankBranchCode
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getBankBranchCode() {
    return bankBranchCode;
  }

  public void setBankBranchCode(String bankBranchCode) {
    this.bankBranchCode = bankBranchCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccountValidation bankAccountValidation = (BankAccountValidation) o;
    return Objects.equals(this.bankAcctTypeCd, bankAccountValidation.bankAcctTypeCd) &&
        Objects.equals(this.bankAcctNum, bankAccountValidation.bankAcctNum) &&
        Objects.equals(this.bankAcctHolderName, bankAccountValidation.bankAcctHolderName) &&
        Objects.equals(this.bankAcctName, bankAccountValidation.bankAcctName) &&
        Objects.equals(this.currencyCd, bankAccountValidation.currencyCd) &&
        Objects.equals(this.primaryRoutingNum, bankAccountValidation.primaryRoutingNum) &&
        Objects.equals(this.issuingBankId, bankAccountValidation.issuingBankId) &&
        Objects.equals(this.bankBranchCode, bankAccountValidation.bankBranchCode);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

