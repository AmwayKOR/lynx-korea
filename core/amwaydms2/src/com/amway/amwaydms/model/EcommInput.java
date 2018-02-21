package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.ContactUsage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class EcommInput   {
  
  private String contactId = null;
  private String contactPointTypeCd = null;
  private List<ContactUsage> usageList = new ArrayList<ContactUsage>();
  private String ecommAddr = null;
  private Boolean allowForLogIn = null;
  private String ecommName = null;
  private String ecommTypeCd = null;
  private String ecommTypeName = null;
  private String statusCd = null;
  private Boolean chkLoginSystemFlg = null;

  
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
   * Email Address
   **/
  
  @ApiModelProperty(required = true, value = "Email Address")
  @JsonProperty("ecommAddr")
  public String getEcommAddr() {
    return ecommAddr;
  }
  public void setEcommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
  }

  
  /**
   * Allow for Login
   **/
  
  @ApiModelProperty(value = "Allow for Login")
  @JsonProperty("allowForLogIn")
  public Boolean getAllowForLogIn() {
    return allowForLogIn;
  }
  public void setAllowForLogIn(Boolean allowForLogIn) {
    this.allowForLogIn = allowForLogIn;
  }

  
  /**
   * E-communication name
   **/
  
  @ApiModelProperty(value = "E-communication name")
  @JsonProperty("ecommName")
  public String getEcommName() {
    return ecommName;
  }
  public void setEcommName(String ecommName) {
    this.ecommName = ecommName;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ECMTC'>Reference to E-Communication type codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ECMTC'>Reference to E-Communication type codes</a>")
  @JsonProperty("ecommTypeCd")
  public String getEcommTypeCd() {
    return ecommTypeCd;
  }
  public void setEcommTypeCd(String ecommTypeCd) {
    this.ecommTypeCd = ecommTypeCd;
  }

  
  /**
   * E-communication type name
   **/
  
  @ApiModelProperty(value = "E-communication type name")
  @JsonProperty("ecommTypeName")
  public String getEcommTypeName() {
    return ecommTypeName;
  }
  public void setEcommTypeName(String ecommTypeName) {
    this.ecommTypeName = ecommTypeName;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=EMSCD'>Reference to ecomm status codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=EMSCD'>Reference to ecomm status codes</a>")
  @JsonProperty("statusCd")
  public String getStatusCd() {
    return statusCd;
  }
  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }

  
  /**
   * Flag to check if email address is available in Amway login system to use
   **/
  
  @ApiModelProperty(value = "Flag to check if email address is available in Amway login system to use")
  @JsonProperty("chkLoginSystemFlg")
  public Boolean getChkLoginSystemFlg() {
    return chkLoginSystemFlg;
  }
  public void setChkLoginSystemFlg(Boolean chkLoginSystemFlg) {
    this.chkLoginSystemFlg = chkLoginSystemFlg;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EcommInput ecommInput = (EcommInput) o;
    return Objects.equals(contactId, ecommInput.contactId) &&
        Objects.equals(contactPointTypeCd, ecommInput.contactPointTypeCd) &&
        Objects.equals(usageList, ecommInput.usageList) &&
        Objects.equals(ecommAddr, ecommInput.ecommAddr) &&
        Objects.equals(allowForLogIn, ecommInput.allowForLogIn) &&
        Objects.equals(ecommName, ecommInput.ecommName) &&
        Objects.equals(ecommTypeCd, ecommInput.ecommTypeCd) &&
        Objects.equals(ecommTypeName, ecommInput.ecommTypeName) &&
        Objects.equals(statusCd, ecommInput.statusCd) &&
        Objects.equals(chkLoginSystemFlg, ecommInput.chkLoginSystemFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, contactPointTypeCd, usageList, ecommAddr, allowForLogIn, ecommName, ecommTypeCd, ecommTypeName, statusCd, chkLoginSystemFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EcommInput {\n");
    
    sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
    sb.append("    contactPointTypeCd: ").append(toIndentedString(contactPointTypeCd)).append("\n");
    sb.append("    usageList: ").append(toIndentedString(usageList)).append("\n");
    sb.append("    ecommAddr: ").append(toIndentedString(ecommAddr)).append("\n");
    sb.append("    allowForLogIn: ").append(toIndentedString(allowForLogIn)).append("\n");
    sb.append("    ecommName: ").append(toIndentedString(ecommName)).append("\n");
    sb.append("    ecommTypeCd: ").append(toIndentedString(ecommTypeCd)).append("\n");
    sb.append("    ecommTypeName: ").append(toIndentedString(ecommTypeName)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    chkLoginSystemFlg: ").append(toIndentedString(chkLoginSystemFlg)).append("\n");
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

