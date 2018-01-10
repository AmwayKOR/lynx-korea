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
 * AccountHistory
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class AccountHistory   {
  @SerializedName("processDate")
  private String processDate = null;

  @SerializedName("transactionDate")
  private String transactionDate = null;

  @SerializedName("processCd")
  private String processCd = null;

  @SerializedName("reasonCd")
  private String reasonCd = null;

  @SerializedName("tranSrcCd")
  private String tranSrcCd = null;

  @SerializedName("countryCd")
  private String countryCd = null;

  @SerializedName("processTypeCd")
  private String processTypeCd = null;

  @SerializedName("detail")
  private String detail = null;

  @SerializedName("actionCd")
  private String actionCd = null;

  @SerializedName("userId")
  private String userId = null;

  @SerializedName("moduleName")
  private String moduleName = null;

  @SerializedName("salesPlanaff")
  private Integer salesPlanaff = null;

  @SerializedName("aboNum")
  private Long aboNum = null;

  public AccountHistory processDate(String processDate) {
    this.processDate = processDate;
    return this;
  }

   /**
   * Get processDate
   * @return processDate
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getProcessDate() {
    return processDate;
  }

  public void setProcessDate(String processDate) {
    this.processDate = processDate;
  }

  public AccountHistory transactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
    return this;
  }

   /**
   * Get transactionDate
   * @return transactionDate
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  public AccountHistory processCd(String processCd) {
    this.processCd = processCd;
    return this;
  }

   /**
   * Get processCd
   * @return processCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getProcessCd() {
    return processCd;
  }

  public void setProcessCd(String processCd) {
    this.processCd = processCd;
  }

  public AccountHistory reasonCd(String reasonCd) {
    this.reasonCd = reasonCd;
    return this;
  }

   /**
   * Get reasonCd
   * @return reasonCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getReasonCd() {
    return reasonCd;
  }

  public void setReasonCd(String reasonCd) {
    this.reasonCd = reasonCd;
  }

  public AccountHistory tranSrcCd(String tranSrcCd) {
    this.tranSrcCd = tranSrcCd;
    return this;
  }

   /**
   * Get tranSrcCd
   * @return tranSrcCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getTranSrcCd() {
    return tranSrcCd;
  }

  public void setTranSrcCd(String tranSrcCd) {
    this.tranSrcCd = tranSrcCd;
  }

  public AccountHistory countryCd(String countryCd) {
    this.countryCd = countryCd;
    return this;
  }

   /**
   * Get countryCd
   * @return countryCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCountryCd() {
    return countryCd;
  }

  public void setCountryCd(String countryCd) {
    this.countryCd = countryCd;
  }

  public AccountHistory processTypeCd(String processTypeCd) {
    this.processTypeCd = processTypeCd;
    return this;
  }

   /**
   * Get processTypeCd
   * @return processTypeCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getProcessTypeCd() {
    return processTypeCd;
  }

  public void setProcessTypeCd(String processTypeCd) {
    this.processTypeCd = processTypeCd;
  }

  public AccountHistory detail(String detail) {
    this.detail = detail;
    return this;
  }

   /**
   * Get detail
   * @return detail
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public AccountHistory actionCd(String actionCd) {
    this.actionCd = actionCd;
    return this;
  }

   /**
   * Get actionCd
   * @return actionCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getActionCd() {
    return actionCd;
  }

  public void setActionCd(String actionCd) {
    this.actionCd = actionCd;
  }

  public AccountHistory userId(String userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public AccountHistory moduleName(String moduleName) {
    this.moduleName = moduleName;
    return this;
  }

   /**
   * Get moduleName
   * @return moduleName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public AccountHistory salesPlanaff(Integer salesPlanaff) {
    this.salesPlanaff = salesPlanaff;
    return this;
  }

   /**
   * Get salesPlanaff
   * @return salesPlanaff
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getSalesPlanaff() {
    return salesPlanaff;
  }

  public void setSalesPlanaff(Integer salesPlanaff) {
    this.salesPlanaff = salesPlanaff;
  }

  public AccountHistory aboNum(Long aboNum) {
    this.aboNum = aboNum;
    return this;
  }

   /**
   * Get aboNum
   * @return aboNum
  **/
  @ApiModelProperty(example = "null", value = "")
  public Long getAboNum() {
    return aboNum;
  }

  public void setAboNum(Long aboNum) {
    this.aboNum = aboNum;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountHistory accountHistory = (AccountHistory) o;
    return Objects.equals(this.processDate, accountHistory.processDate) &&
        Objects.equals(this.transactionDate, accountHistory.transactionDate) &&
        Objects.equals(this.processCd, accountHistory.processCd) &&
        Objects.equals(this.reasonCd, accountHistory.reasonCd) &&
        Objects.equals(this.tranSrcCd, accountHistory.tranSrcCd) &&
        Objects.equals(this.countryCd, accountHistory.countryCd) &&
        Objects.equals(this.processTypeCd, accountHistory.processTypeCd) &&
        Objects.equals(this.detail, accountHistory.detail) &&
        Objects.equals(this.actionCd, accountHistory.actionCd) &&
        Objects.equals(this.userId, accountHistory.userId) &&
        Objects.equals(this.moduleName, accountHistory.moduleName) &&
        Objects.equals(this.salesPlanaff, accountHistory.salesPlanaff) &&
        Objects.equals(this.aboNum, accountHistory.aboNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processDate, transactionDate, processCd, reasonCd, tranSrcCd, countryCd, processTypeCd, detail, actionCd, userId, moduleName, salesPlanaff, aboNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountHistory {\n");
    
    sb.append("    processDate: ").append(toIndentedString(processDate)).append("\n");
    sb.append("    transactionDate: ").append(toIndentedString(transactionDate)).append("\n");
    sb.append("    processCd: ").append(toIndentedString(processCd)).append("\n");
    sb.append("    reasonCd: ").append(toIndentedString(reasonCd)).append("\n");
    sb.append("    tranSrcCd: ").append(toIndentedString(tranSrcCd)).append("\n");
    sb.append("    countryCd: ").append(toIndentedString(countryCd)).append("\n");
    sb.append("    processTypeCd: ").append(toIndentedString(processTypeCd)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    actionCd: ").append(toIndentedString(actionCd)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    moduleName: ").append(toIndentedString(moduleName)).append("\n");
    sb.append("    salesPlanaff: ").append(toIndentedString(salesPlanaff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
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

