package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountHistory   {
  
  private String processDate = null;
  private String transactionDate = null;
  private String processCd = null;
  private String reasonCd = null;
  private String tranSrcCd = null;
  private String countryCd = null;
  private String processTypeCd = null;
  private String detail = null;
  private String actionCd = null;
  private String userId = null;
  private String moduleName = null;
  private Integer salesPlanaff = null;
  private Long aboNum = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("processDate")
  public String getProcessDate() {
    return processDate;
  }
  public void setProcessDate(String processDate) {
    this.processDate = processDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("transactionDate")
  public String getTransactionDate() {
    return transactionDate;
  }
  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("processCd")
  public String getProcessCd() {
    return processCd;
  }
  public void setProcessCd(String processCd) {
    this.processCd = processCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("reasonCd")
  public String getReasonCd() {
    return reasonCd;
  }
  public void setReasonCd(String reasonCd) {
    this.reasonCd = reasonCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("tranSrcCd")
  public String getTranSrcCd() {
    return tranSrcCd;
  }
  public void setTranSrcCd(String tranSrcCd) {
    this.tranSrcCd = tranSrcCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("countryCd")
  public String getCountryCd() {
    return countryCd;
  }
  public void setCountryCd(String countryCd) {
    this.countryCd = countryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("processTypeCd")
  public String getProcessTypeCd() {
    return processTypeCd;
  }
  public void setProcessTypeCd(String processTypeCd) {
    this.processTypeCd = processTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }
  public void setDetail(String detail) {
    this.detail = detail;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("actionCd")
  public String getActionCd() {
    return actionCd;
  }
  public void setActionCd(String actionCd) {
    this.actionCd = actionCd;
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
  @JsonProperty("moduleName")
  public String getModuleName() {
    return moduleName;
  }
  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanaff")
  public Integer getSalesPlanaff() {
    return salesPlanaff;
  }
  public void setSalesPlanaff(Integer salesPlanaff) {
    this.salesPlanaff = salesPlanaff;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("aboNum")
  public Long getAboNum() {
    return aboNum;
  }
  public void setAboNum(Long aboNum) {
    this.aboNum = aboNum;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountHistory accountHistory = (AccountHistory) o;
    return Objects.equals(processDate, accountHistory.processDate) &&
        Objects.equals(transactionDate, accountHistory.transactionDate) &&
        Objects.equals(processCd, accountHistory.processCd) &&
        Objects.equals(reasonCd, accountHistory.reasonCd) &&
        Objects.equals(tranSrcCd, accountHistory.tranSrcCd) &&
        Objects.equals(countryCd, accountHistory.countryCd) &&
        Objects.equals(processTypeCd, accountHistory.processTypeCd) &&
        Objects.equals(detail, accountHistory.detail) &&
        Objects.equals(actionCd, accountHistory.actionCd) &&
        Objects.equals(userId, accountHistory.userId) &&
        Objects.equals(moduleName, accountHistory.moduleName) &&
        Objects.equals(salesPlanaff, accountHistory.salesPlanaff) &&
        Objects.equals(aboNum, accountHistory.aboNum);
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

