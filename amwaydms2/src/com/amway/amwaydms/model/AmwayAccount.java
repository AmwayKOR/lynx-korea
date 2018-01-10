package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AmwayAccount   {
  
  private Long accountId = null;
  private String accountName = null;
  private String accountSubTypeCd = null;
  private String cntryCd = null;
  private String statusCd = null;
  private String partyId = null;
  private String salesPlanAff = null;
  private String globalPartyId = null;
  private String roleCd = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountId")
  public Long getAccountId() {
    return accountId;
  }
  public void setAccountId(Long accountId) {
    this.accountId = accountId;
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
  @JsonProperty("partyId")
  public String getPartyId() {
    return partyId;
  }
  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  
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
  @JsonProperty("globalPartyId")
  public String getGlobalPartyId() {
    return globalPartyId;
  }
  public void setGlobalPartyId(String globalPartyId) {
    this.globalPartyId = globalPartyId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("roleCd")
  public String getRoleCd() {
    return roleCd;
  }
  public void setRoleCd(String roleCd) {
    this.roleCd = roleCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AmwayAccount amwayAccount = (AmwayAccount) o;
    return Objects.equals(accountId, amwayAccount.accountId) &&
        Objects.equals(accountName, amwayAccount.accountName) &&
        Objects.equals(accountSubTypeCd, amwayAccount.accountSubTypeCd) &&
        Objects.equals(cntryCd, amwayAccount.cntryCd) &&
        Objects.equals(statusCd, amwayAccount.statusCd) &&
        Objects.equals(partyId, amwayAccount.partyId) &&
        Objects.equals(salesPlanAff, amwayAccount.salesPlanAff) &&
        Objects.equals(globalPartyId, amwayAccount.globalPartyId) &&
        Objects.equals(roleCd, amwayAccount.roleCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, accountName, accountSubTypeCd, cntryCd, statusCd, partyId, salesPlanAff, globalPartyId, roleCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AmwayAccount {\n");
    
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    globalPartyId: ").append(toIndentedString(globalPartyId)).append("\n");
    sb.append("    roleCd: ").append(toIndentedString(roleCd)).append("\n");
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

