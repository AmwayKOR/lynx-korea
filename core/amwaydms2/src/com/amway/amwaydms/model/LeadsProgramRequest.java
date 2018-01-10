package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class LeadsProgramRequest   {
  
  private Boolean contractAcceptFlg = null;
  private List<String> accountSubTypeCdList = new ArrayList<String>();
  private List<String> languageCdList = new ArrayList<String>();

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("contractAcceptFlg")
  public Boolean getContractAcceptFlg() {
    return contractAcceptFlg;
  }
  public void setContractAcceptFlg(Boolean contractAcceptFlg) {
    this.contractAcceptFlg = contractAcceptFlg;
  }

  
  /**
   * Account Sub-type codes. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ACCST'>link</a> for valid codes
   **/
  
  @ApiModelProperty(required = true, value = "Account Sub-type codes. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ACCST'>link</a> for valid codes")
  @JsonProperty("accountSubTypeCdList")
  public List<String> getAccountSubTypeCdList() {
    return accountSubTypeCdList;
  }
  public void setAccountSubTypeCdList(List<String> accountSubTypeCdList) {
    this.accountSubTypeCdList = accountSubTypeCdList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("languageCdList")
  public List<String> getLanguageCdList() {
    return languageCdList;
  }
  public void setLanguageCdList(List<String> languageCdList) {
    this.languageCdList = languageCdList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeadsProgramRequest leadsProgramRequest = (LeadsProgramRequest) o;
    return Objects.equals(contractAcceptFlg, leadsProgramRequest.contractAcceptFlg) &&
        Objects.equals(accountSubTypeCdList, leadsProgramRequest.accountSubTypeCdList) &&
        Objects.equals(languageCdList, leadsProgramRequest.languageCdList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contractAcceptFlg, accountSubTypeCdList, languageCdList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeadsProgramRequest {\n");
    
    sb.append("    contractAcceptFlg: ").append(toIndentedString(contractAcceptFlg)).append("\n");
    sb.append("    accountSubTypeCdList: ").append(toIndentedString(accountSubTypeCdList)).append("\n");
    sb.append("    languageCdList: ").append(toIndentedString(languageCdList)).append("\n");
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

