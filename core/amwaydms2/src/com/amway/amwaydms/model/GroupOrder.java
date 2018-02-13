package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AccountBalance;
import com.amway.amwaydms.model.BankAccount;
import com.amway.amwaydms.model.BlockPrivilege;
import com.amway.amwaydms.model.GroupOrderMaster;
import com.amway.amwaydms.model.MissingInfoDetail;
import com.amway.amwaydms.model.Party;
import com.amway.amwaydms.model.Sponsor;
import com.amway.amwaydms.model.Subscription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class GroupOrder   {
  
  private GroupOrderMaster accountMst = null;
  private List<Party> partyList = new ArrayList<Party>();
  private Sponsor sponsor = null;
  private List<BlockPrivilege> blockPrivilegeList = new ArrayList<BlockPrivilege>();
  private List<AccountBalance> accountBalanceList = new ArrayList<AccountBalance>();
  private List<Subscription> subscriptionList = new ArrayList<Subscription>();
  private List<BankAccount> bankAccountDetailList = new ArrayList<BankAccount>();
  private List<MissingInfoDetail> missingInfoList = new ArrayList<MissingInfoDetail>();

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountMst")
  public GroupOrderMaster getAccountMst() {
    return accountMst;
  }
  public void setAccountMst(GroupOrderMaster accountMst) {
    this.accountMst = accountMst;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyList")
  public List<Party> getPartyList() {
    return partyList;
  }
  public void setPartyList(List<Party> partyList) {
    this.partyList = partyList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sponsor")
  public Sponsor getSponsor() {
    return sponsor;
  }
  public void setSponsor(Sponsor sponsor) {
    this.sponsor = sponsor;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("blockPrivilegeList")
  public List<BlockPrivilege> getBlockPrivilegeList() {
    return blockPrivilegeList;
  }
  public void setBlockPrivilegeList(List<BlockPrivilege> blockPrivilegeList) {
    this.blockPrivilegeList = blockPrivilegeList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountBalanceList")
  public List<AccountBalance> getAccountBalanceList() {
    return accountBalanceList;
  }
  public void setAccountBalanceList(List<AccountBalance> accountBalanceList) {
    this.accountBalanceList = accountBalanceList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("subscriptionList")
  public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }
  public void setSubscriptionList(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankAccountDetailList")
  public List<BankAccount> getBankAccountDetailList() {
    return bankAccountDetailList;
  }
  public void setBankAccountDetailList(List<BankAccount> bankAccountDetailList) {
    this.bankAccountDetailList = bankAccountDetailList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("missingInfoList")
  public List<MissingInfoDetail> getMissingInfoList() {
    return missingInfoList;
  }
  public void setMissingInfoList(List<MissingInfoDetail> missingInfoList) {
    this.missingInfoList = missingInfoList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GroupOrder groupOrder = (GroupOrder) o;
    return Objects.equals(accountMst, groupOrder.accountMst) &&
        Objects.equals(partyList, groupOrder.partyList) &&
        Objects.equals(sponsor, groupOrder.sponsor) &&
        Objects.equals(blockPrivilegeList, groupOrder.blockPrivilegeList) &&
        Objects.equals(accountBalanceList, groupOrder.accountBalanceList) &&
        Objects.equals(subscriptionList, groupOrder.subscriptionList) &&
        Objects.equals(bankAccountDetailList, groupOrder.bankAccountDetailList) &&
        Objects.equals(missingInfoList, groupOrder.missingInfoList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountMst, partyList, sponsor, blockPrivilegeList, accountBalanceList, subscriptionList, bankAccountDetailList, missingInfoList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupOrder {\n");
    
    sb.append("    accountMst: ").append(toIndentedString(accountMst)).append("\n");
    sb.append("    partyList: ").append(toIndentedString(partyList)).append("\n");
    sb.append("    sponsor: ").append(toIndentedString(sponsor)).append("\n");
    sb.append("    blockPrivilegeList: ").append(toIndentedString(blockPrivilegeList)).append("\n");
    sb.append("    accountBalanceList: ").append(toIndentedString(accountBalanceList)).append("\n");
    sb.append("    subscriptionList: ").append(toIndentedString(subscriptionList)).append("\n");
    sb.append("    bankAccountDetailList: ").append(toIndentedString(bankAccountDetailList)).append("\n");
    sb.append("    missingInfoList: ").append(toIndentedString(missingInfoList)).append("\n");
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

