package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.SearchParty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SearchAccount   {
  
  private String salesPlanAff = null;
  private String aboNum = null;
  private String accountName = null;
  private String statusCd = null;
  private String accountSubTypeCd = null;
  private List<SearchParty> partyList = new ArrayList<SearchParty>();

  
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
  @JsonProperty("partyList")
  public List<SearchParty> getPartyList() {
    return partyList;
  }
  public void setPartyList(List<SearchParty> partyList) {
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
    SearchAccount searchAccount = (SearchAccount) o;
    return Objects.equals(salesPlanAff, searchAccount.salesPlanAff) &&
        Objects.equals(aboNum, searchAccount.aboNum) &&
        Objects.equals(accountName, searchAccount.accountName) &&
        Objects.equals(statusCd, searchAccount.statusCd) &&
        Objects.equals(accountSubTypeCd, searchAccount.accountSubTypeCd) &&
        Objects.equals(partyList, searchAccount.partyList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(salesPlanAff, aboNum, accountName, statusCd, accountSubTypeCd, partyList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchAccount {\n");
    
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
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

