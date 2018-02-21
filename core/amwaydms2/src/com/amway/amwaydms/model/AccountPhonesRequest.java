package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AffAbo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountPhonesRequest   {
  
  private List<AffAbo> affAboList = new ArrayList<AffAbo>();
  private String primaryPartyFlag = null;
  private String contactPointTypeCd = null;
  private String contactPointPurposeCd = null;
  private String primaryUsageFlag = null;

  
  /**
   * affAboList
   **/
  
  @ApiModelProperty(required = true, value = "affAboList")
  @JsonProperty("affAboList")
  public List<AffAbo> getAffAboList() {
    return affAboList;
  }
  public void setAffAboList(List<AffAbo> affAboList) {
    this.affAboList = affAboList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("primaryPartyFlag")
  public String getPrimaryPartyFlag() {
    return primaryPartyFlag;
  }
  public void setPrimaryPartyFlag(String primaryPartyFlag) {
    this.primaryPartyFlag = primaryPartyFlag;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=COPTY'>Reference to contact point type codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=COPTY'>Reference to contact point type codes</a>")
  @JsonProperty("contactPointTypeCd")
  public String getContactPointTypeCd() {
    return contactPointTypeCd;
  }
  public void setContactPointTypeCd(String contactPointTypeCd) {
    this.contactPointTypeCd = contactPointTypeCd;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=CNPTU'>Reference to contact point type codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=CNPTU'>Reference to contact point type codes</a>")
  @JsonProperty("contactPointPurposeCd")
  public String getContactPointPurposeCd() {
    return contactPointPurposeCd;
  }
  public void setContactPointPurposeCd(String contactPointPurposeCd) {
    this.contactPointPurposeCd = contactPointPurposeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("primaryUsageFlag")
  public String getPrimaryUsageFlag() {
    return primaryUsageFlag;
  }
  public void setPrimaryUsageFlag(String primaryUsageFlag) {
    this.primaryUsageFlag = primaryUsageFlag;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountPhonesRequest accountPhonesRequest = (AccountPhonesRequest) o;
    return Objects.equals(affAboList, accountPhonesRequest.affAboList) &&
        Objects.equals(primaryPartyFlag, accountPhonesRequest.primaryPartyFlag) &&
        Objects.equals(contactPointTypeCd, accountPhonesRequest.contactPointTypeCd) &&
        Objects.equals(contactPointPurposeCd, accountPhonesRequest.contactPointPurposeCd) &&
        Objects.equals(primaryUsageFlag, accountPhonesRequest.primaryUsageFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(affAboList, primaryPartyFlag, contactPointTypeCd, contactPointPurposeCd, primaryUsageFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountPhonesRequest {\n");
    
    sb.append("    affAboList: ").append(toIndentedString(affAboList)).append("\n");
    sb.append("    primaryPartyFlag: ").append(toIndentedString(primaryPartyFlag)).append("\n");
    sb.append("    contactPointTypeCd: ").append(toIndentedString(contactPointTypeCd)).append("\n");
    sb.append("    contactPointPurposeCd: ").append(toIndentedString(contactPointPurposeCd)).append("\n");
    sb.append("    primaryUsageFlag: ").append(toIndentedString(primaryUsageFlag)).append("\n");
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

