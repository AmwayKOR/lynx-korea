package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.ContactUsage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountEcommInfo   {
  
  private String partyId = null;
  private String contactId = null;
  private String contactPointTypeCd = null;
  private List<ContactUsage> usageList = new ArrayList<ContactUsage>();
  private String primaryPartyFlag = null;
  private String salesPlanAff = null;
  private String aboNum = null;
  private String ecommAddr = null;
  private String allowForLogIn = null;
  private String ecommName = null;
  private String ecommTypeName = null;
  private String statusCd = null;

  
  /**
   * party Id
   **/
  
  @ApiModelProperty(required = true, value = "party Id")
  @JsonProperty("partyId")
  public String getPartyId() {
    return partyId;
  }
  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  
  /**
   * external contact Id
   **/
  
  @ApiModelProperty(value = "external contact Id")
  @JsonProperty("contactId")
  public String getContactId() {
    return contactId;
  }
  public void setContactId(String contactId) {
    this.contactId = contactId;
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
   * usageList
   **/
  
  @ApiModelProperty(required = true, value = "usageList")
  @JsonProperty("usageList")
  public List<ContactUsage> getUsageList() {
    return usageList;
  }
  public void setUsageList(List<ContactUsage> usageList) {
    this.usageList = usageList;
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
  @JsonProperty("ecommAddr")
  public String getEcommAddr() {
    return ecommAddr;
  }
  public void setEcommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("allowForLogIn")
  public String getAllowForLogIn() {
    return allowForLogIn;
  }
  public void setAllowForLogIn(String allowForLogIn) {
    this.allowForLogIn = allowForLogIn;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ecommName")
  public String getEcommName() {
    return ecommName;
  }
  public void setEcommName(String ecommName) {
    this.ecommName = ecommName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ecommTypeName")
  public String getEcommTypeName() {
    return ecommTypeName;
  }
  public void setEcommTypeName(String ecommTypeName) {
    this.ecommTypeName = ecommTypeName;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountEcommInfo accountEcommInfo = (AccountEcommInfo) o;
    return Objects.equals(partyId, accountEcommInfo.partyId) &&
        Objects.equals(contactId, accountEcommInfo.contactId) &&
        Objects.equals(contactPointTypeCd, accountEcommInfo.contactPointTypeCd) &&
        Objects.equals(usageList, accountEcommInfo.usageList) &&
        Objects.equals(primaryPartyFlag, accountEcommInfo.primaryPartyFlag) &&
        Objects.equals(salesPlanAff, accountEcommInfo.salesPlanAff) &&
        Objects.equals(aboNum, accountEcommInfo.aboNum) &&
        Objects.equals(ecommAddr, accountEcommInfo.ecommAddr) &&
        Objects.equals(allowForLogIn, accountEcommInfo.allowForLogIn) &&
        Objects.equals(ecommName, accountEcommInfo.ecommName) &&
        Objects.equals(ecommTypeName, accountEcommInfo.ecommTypeName) &&
        Objects.equals(statusCd, accountEcommInfo.statusCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, contactId, contactPointTypeCd, usageList, primaryPartyFlag, salesPlanAff, aboNum, ecommAddr, allowForLogIn, ecommName, ecommTypeName, statusCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountEcommInfo {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
    sb.append("    contactPointTypeCd: ").append(toIndentedString(contactPointTypeCd)).append("\n");
    sb.append("    usageList: ").append(toIndentedString(usageList)).append("\n");
    sb.append("    primaryPartyFlag: ").append(toIndentedString(primaryPartyFlag)).append("\n");
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    ecommAddr: ").append(toIndentedString(ecommAddr)).append("\n");
    sb.append("    allowForLogIn: ").append(toIndentedString(allowForLogIn)).append("\n");
    sb.append("    ecommName: ").append(toIndentedString(ecommName)).append("\n");
    sb.append("    ecommTypeName: ").append(toIndentedString(ecommTypeName)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
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

