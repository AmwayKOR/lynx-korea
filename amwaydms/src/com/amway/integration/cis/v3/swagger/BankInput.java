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
import java.util.ArrayList;
import java.util.List;


/**
 * BankInput
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class BankInput   {
  @SerializedName("bankAcctTypeCd")
  private String bankAcctTypeCd = null;

  @SerializedName("bankAcctNum")
  private String bankAcctNum = null;

  @SerializedName("effectiveStartDate")
  private String effectiveStartDate = null;

  @SerializedName("effectiveEndDate")
  private String effectiveEndDate = null;

  @SerializedName("primaryRoutingNum")
  private String primaryRoutingNum = null;

  @SerializedName("issuingBankId")
  private String issuingBankId = null;

  @SerializedName("issuingBankName")
  private String issuingBankName = null;

  @SerializedName("bankBranchCode")
  private String bankBranchCode = null;

  @SerializedName("bankBranchName")
  private String bankBranchName = null;

  @SerializedName("secondaryRoutingNum")
  private String secondaryRoutingNum = null;

  @SerializedName("bankAcctHolderName")
  private String bankAcctHolderName = null;

  @SerializedName("bankAcctName")
  private String bankAcctName = null;

  @SerializedName("currencyCd")
  private String currencyCd = null;

  @SerializedName("statusFlg")
  private String statusFlg = null;

  @SerializedName("accountUseCodes")
  private List<String> accountUseCodes = new ArrayList<String>();

  public BankInput bankAcctTypeCd(String bankAcctTypeCd) {
    this.bankAcctTypeCd = bankAcctTypeCd;
    return this;
  }

   /**
   * Bank Account Type
   * @return bankAcctTypeCd
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account Type")
  public String getBankAcctTypeCd() {
    return bankAcctTypeCd;
  }

  public void setBankAcctTypeCd(String bankAcctTypeCd) {
    this.bankAcctTypeCd = bankAcctTypeCd;
  }

  public BankInput bankAcctNum(String bankAcctNum) {
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

  public BankInput effectiveStartDate(String effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
    return this;
  }

   /**
   * Bank Account Effective date time in UTC such '2016-12-29T14:53:00-02:00'
   * @return effectiveStartDate
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account Effective date time in UTC such '2016-12-29T14:53:00-02:00'")
  public String getEffectiveStartDate() {
    return effectiveStartDate;
  }

  public void setEffectiveStartDate(String effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
  }

  public BankInput effectiveEndDate(String effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
    return this;
  }

   /**
   * Date Time in UTC such '2016-12-29T14:53:00-02:00'
   * @return effectiveEndDate
  **/
  @ApiModelProperty(example = "null", value = "Date Time in UTC such '2016-12-29T14:53:00-02:00'")
  public String getEffectiveEndDate() {
    return effectiveEndDate;
  }

  public void setEffectiveEndDate(String effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
  }

  public BankInput primaryRoutingNum(String primaryRoutingNum) {
    this.primaryRoutingNum = primaryRoutingNum;
    return this;
  }

   /**
   * Bank Account Primary Routing
   * @return primaryRoutingNum
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account Primary Routing")
  public String getPrimaryRoutingNum() {
    return primaryRoutingNum;
  }

  public void setPrimaryRoutingNum(String primaryRoutingNum) {
    this.primaryRoutingNum = primaryRoutingNum;
  }

  public BankInput issuingBankId(String issuingBankId) {
    this.issuingBankId = issuingBankId;
    return this;
  }

   /**
   * Bank Account Issueing Bank Id
   * @return issuingBankId
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account Issueing Bank Id")
  public String getIssuingBankId() {
    return issuingBankId;
  }

  public void setIssuingBankId(String issuingBankId) {
    this.issuingBankId = issuingBankId;
  }

  public BankInput issuingBankName(String issuingBankName) {
    this.issuingBankName = issuingBankName;
    return this;
  }

   /**
   * Bank Account Issueing Bank name
   * @return issuingBankName
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account Issueing Bank name")
  public String getIssuingBankName() {
    return issuingBankName;
  }

  public void setIssuingBankName(String issuingBankName) {
    this.issuingBankName = issuingBankName;
  }

  public BankInput bankBranchCode(String bankBranchCode) {
    this.bankBranchCode = bankBranchCode;
    return this;
  }

   /**
   * Bank Account Branch code
   * @return bankBranchCode
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account Branch code")
  public String getBankBranchCode() {
    return bankBranchCode;
  }

  public void setBankBranchCode(String bankBranchCode) {
    this.bankBranchCode = bankBranchCode;
  }

  public BankInput bankBranchName(String bankBranchName) {
    this.bankBranchName = bankBranchName;
    return this;
  }

   /**
   * Bank Account Branch name
   * @return bankBranchName
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account Branch name")
  public String getBankBranchName() {
    return bankBranchName;
  }

  public void setBankBranchName(String bankBranchName) {
    this.bankBranchName = bankBranchName;
  }

  public BankInput secondaryRoutingNum(String secondaryRoutingNum) {
    this.secondaryRoutingNum = secondaryRoutingNum;
    return this;
  }

   /**
   * Get secondaryRoutingNum
   * @return secondaryRoutingNum
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getSecondaryRoutingNum() {
    return secondaryRoutingNum;
  }

  public void setSecondaryRoutingNum(String secondaryRoutingNum) {
    this.secondaryRoutingNum = secondaryRoutingNum;
  }

  public BankInput bankAcctHolderName(String bankAcctHolderName) {
    this.bankAcctHolderName = bankAcctHolderName;
    return this;
  }

   /**
   * Bank Account holder name
   * @return bankAcctHolderName
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account holder name")
  public String getBankAcctHolderName() {
    return bankAcctHolderName;
  }

  public void setBankAcctHolderName(String bankAcctHolderName) {
    this.bankAcctHolderName = bankAcctHolderName;
  }

  public BankInput bankAcctName(String bankAcctName) {
    this.bankAcctName = bankAcctName;
    return this;
  }

   /**
   * Bank Account name
   * @return bankAcctName
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account name")
  public String getBankAcctName() {
    return bankAcctName;
  }

  public void setBankAcctName(String bankAcctName) {
    this.bankAcctName = bankAcctName;
  }

  public BankInput currencyCd(String currencyCd) {
    this.currencyCd = currencyCd;
    return this;
  }

   /**
   * Bank Account ISO currency code
   * @return currencyCd
  **/
  @ApiModelProperty(example = "null", required = true, value = "Bank Account ISO currency code")
  public String getCurrencyCd() {
    return currencyCd;
  }

  public void setCurrencyCd(String currencyCd) {
    this.currencyCd = currencyCd;
  }

  public BankInput statusFlg(String statusFlg) {
    this.statusFlg = statusFlg;
    return this;
  }

   /**
   * Get statusFlg
   * @return statusFlg
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStatusFlg() {
    return statusFlg;
  }

  public void setStatusFlg(String statusFlg) {
    this.statusFlg = statusFlg;
  }

  public BankInput accountUseCodes(List<String> accountUseCodes) {
    this.accountUseCodes = accountUseCodes;
    return this;
  }

  public BankInput addAccountUseCodesItem(String accountUseCodesItem) {
    this.accountUseCodes.add(accountUseCodesItem);
    return this;
  }

   /**
   * String delimiter of BankUsesageCode.  Please see <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BAACU'>Bank Account Usage codes</a>
   * @return accountUseCodes
  **/
  @ApiModelProperty(example = "null", required = true, value = "String delimiter of BankUsesageCode.  Please see <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BAACU'>Bank Account Usage codes</a>")
  public List<String> getAccountUseCodes() {
    return accountUseCodes;
  }

  public void setAccountUseCodes(List<String> accountUseCodes) {
    this.accountUseCodes = accountUseCodes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankInput bankInput = (BankInput) o;
    return Objects.equals(this.bankAcctTypeCd, bankInput.bankAcctTypeCd) &&
        Objects.equals(this.bankAcctNum, bankInput.bankAcctNum) &&
        Objects.equals(this.effectiveStartDate, bankInput.effectiveStartDate) &&
        Objects.equals(this.effectiveEndDate, bankInput.effectiveEndDate) &&
        Objects.equals(this.primaryRoutingNum, bankInput.primaryRoutingNum) &&
        Objects.equals(this.issuingBankId, bankInput.issuingBankId) &&
        Objects.equals(this.issuingBankName, bankInput.issuingBankName) &&
        Objects.equals(this.bankBranchCode, bankInput.bankBranchCode) &&
        Objects.equals(this.bankBranchName, bankInput.bankBranchName) &&
        Objects.equals(this.secondaryRoutingNum, bankInput.secondaryRoutingNum) &&
        Objects.equals(this.bankAcctHolderName, bankInput.bankAcctHolderName) &&
        Objects.equals(this.bankAcctName, bankInput.bankAcctName) &&
        Objects.equals(this.currencyCd, bankInput.currencyCd) &&
        Objects.equals(this.statusFlg, bankInput.statusFlg) &&
        Objects.equals(this.accountUseCodes, bankInput.accountUseCodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankAcctTypeCd, bankAcctNum, effectiveStartDate, effectiveEndDate, primaryRoutingNum, issuingBankId, issuingBankName, bankBranchCode, bankBranchName, secondaryRoutingNum, bankAcctHolderName, bankAcctName, currencyCd, statusFlg, accountUseCodes);
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
    sb.append("    accountUseCodes: ").append(toIndentedString(accountUseCodes)).append("\n");
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

