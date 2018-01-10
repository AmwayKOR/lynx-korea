package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AccountMaster;
import com.amway.amwaydms.model.BlockPrivilege;
import com.amway.amwaydms.model.LosParty;
import com.amway.amwaydms.model.MissingInfoDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class LosAccount   {
  
  private AccountMaster accountMst = null;
  private List<LosParty> partyList = new ArrayList<LosParty>();
  private List<BlockPrivilege> blockPrivilegeList = new ArrayList<BlockPrivilege>();
  private List<MissingInfoDetail> missingInfoList = new ArrayList<MissingInfoDetail>();

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountMst")
  public AccountMaster getAccountMst() {
    return accountMst;
  }
  public void setAccountMst(AccountMaster accountMst) {
    this.accountMst = accountMst;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyList")
  public List<LosParty> getPartyList() {
    return partyList;
  }
  public void setPartyList(List<LosParty> partyList) {
    this.partyList = partyList;
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
    LosAccount losAccount = (LosAccount) o;
    return Objects.equals(accountMst, losAccount.accountMst) &&
        Objects.equals(partyList, losAccount.partyList) &&
        Objects.equals(blockPrivilegeList, losAccount.blockPrivilegeList) &&
        Objects.equals(missingInfoList, losAccount.missingInfoList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountMst, partyList, blockPrivilegeList, missingInfoList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LosAccount {\n");
    
    sb.append("    accountMst: ").append(toIndentedString(accountMst)).append("\n");
    sb.append("    partyList: ").append(toIndentedString(partyList)).append("\n");
    sb.append("    blockPrivilegeList: ").append(toIndentedString(blockPrivilegeList)).append("\n");
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

