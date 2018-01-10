package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.PartyContact;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class ExpireAccount   {
  
  private String salesPlanAff = null;
  private String aboNum = null;
  private String accountName = null;
  private String accountSubTypeCd = null;
  private String aboExpireDate = null;
  private String reason = null;
  private List<PartyContact> partyList = new ArrayList<PartyContact>();

  
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
  @JsonProperty("reason")
  public String getReason() {
    return reason;
  }
  public void setReason(String reason) {
    this.reason = reason;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyList")
  public List<PartyContact> getPartyList() {
    return partyList;
  }
  public void setPartyList(List<PartyContact> partyList) {
    this.partyList = partyList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExpireAccount expireAccount = (ExpireAccount) o;
    return Objects.equals(salesPlanAff, expireAccount.salesPlanAff) &&
        Objects.equals(aboNum, expireAccount.aboNum) &&
        Objects.equals(accountName, expireAccount.accountName) &&
        Objects.equals(accountSubTypeCd, expireAccount.accountSubTypeCd) &&
        Objects.equals(aboExpireDate, expireAccount.aboExpireDate) &&
        Objects.equals(reason, expireAccount.reason) &&
        Objects.equals(partyList, expireAccount.partyList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(salesPlanAff, aboNum, accountName, accountSubTypeCd, aboExpireDate, reason, partyList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExpireAccount {\n");
    
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
    sb.append("    aboExpireDate: ").append(toIndentedString(aboExpireDate)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    partyList: ").append(toIndentedString(partyList)).append("\n");
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

