package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.ContactUsage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PhoneInput   {
  
  private String contactId = null;
  private String contactPointTypeCd = null;
  private List<ContactUsage> usageList = new ArrayList<ContactUsage>();
  private String phoneLocalNum = null;
  private String phoneCntryCd = null;
  private String phoneAreaCd = null;
  private String phoneExtNum = null;
  private Boolean smsCapableFlag = null;
  private String cntryCd = null;
  private Boolean dayFlag = null;
  private Boolean eveningFlag = null;
  private String statusCd = null;

  
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
   * phone local number
   **/
  
  @ApiModelProperty(required = true, value = "phone local number")
  @JsonProperty("phoneLocalNum")
  public String getPhoneLocalNum() {
    return phoneLocalNum;
  }
  public void setPhoneLocalNum(String phoneLocalNum) {
    this.phoneLocalNum = phoneLocalNum;
  }

  
  /**
   * phone country code
   **/
  
  @ApiModelProperty(required = true, value = "phone country code")
  @JsonProperty("phoneCntryCd")
  public String getPhoneCntryCd() {
    return phoneCntryCd;
  }
  public void setPhoneCntryCd(String phoneCntryCd) {
    this.phoneCntryCd = phoneCntryCd;
  }

  
  /**
   * phone area code
   **/
  
  @ApiModelProperty(value = "phone area code")
  @JsonProperty("phoneAreaCd")
  public String getPhoneAreaCd() {
    return phoneAreaCd;
  }
  public void setPhoneAreaCd(String phoneAreaCd) {
    this.phoneAreaCd = phoneAreaCd;
  }

  
  /**
   * phone extension number
   **/
  
  @ApiModelProperty(value = "phone extension number")
  @JsonProperty("phoneExtNum")
  public String getPhoneExtNum() {
    return phoneExtNum;
  }
  public void setPhoneExtNum(String phoneExtNum) {
    this.phoneExtNum = phoneExtNum;
  }

  
  /**
   * phone sms capable flag
   **/
  
  @ApiModelProperty(value = "phone sms capable flag")
  @JsonProperty("smsCapableFlag")
  public Boolean getSmsCapableFlag() {
    return smsCapableFlag;
  }
  public void setSmsCapableFlag(Boolean smsCapableFlag) {
    this.smsCapableFlag = smsCapableFlag;
  }

  
  /**
   * <a href='http://ed/Domains/Details/16'>Reference to phone ISO Country Codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='http://ed/Domains/Details/16'>Reference to phone ISO Country Codes</a>")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  
  /**
   * phone communication allowed during day time flag
   **/
  
  @ApiModelProperty(value = "phone communication allowed during day time flag")
  @JsonProperty("dayFlag")
  public Boolean getDayFlag() {
    return dayFlag;
  }
  public void setDayFlag(Boolean dayFlag) {
    this.dayFlag = dayFlag;
  }

  
  /**
   * phone communication allowed during evening time flag
   **/
  
  @ApiModelProperty(value = "phone communication allowed during evening time flag")
  @JsonProperty("eveningFlag")
  public Boolean getEveningFlag() {
    return eveningFlag;
  }
  public void setEveningFlag(Boolean eveningFlag) {
    this.eveningFlag = eveningFlag;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PHSCD'>Reference to phone status codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PHSCD'>Reference to phone status codes</a>")
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
    PhoneInput phoneInput = (PhoneInput) o;
    return Objects.equals(contactId, phoneInput.contactId) &&
        Objects.equals(contactPointTypeCd, phoneInput.contactPointTypeCd) &&
        Objects.equals(usageList, phoneInput.usageList) &&
        Objects.equals(phoneLocalNum, phoneInput.phoneLocalNum) &&
        Objects.equals(phoneCntryCd, phoneInput.phoneCntryCd) &&
        Objects.equals(phoneAreaCd, phoneInput.phoneAreaCd) &&
        Objects.equals(phoneExtNum, phoneInput.phoneExtNum) &&
        Objects.equals(smsCapableFlag, phoneInput.smsCapableFlag) &&
        Objects.equals(cntryCd, phoneInput.cntryCd) &&
        Objects.equals(dayFlag, phoneInput.dayFlag) &&
        Objects.equals(eveningFlag, phoneInput.eveningFlag) &&
        Objects.equals(statusCd, phoneInput.statusCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactId, contactPointTypeCd, usageList, phoneLocalNum, phoneCntryCd, phoneAreaCd, phoneExtNum, smsCapableFlag, cntryCd, dayFlag, eveningFlag, statusCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneInput {\n");
    
    sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
    sb.append("    contactPointTypeCd: ").append(toIndentedString(contactPointTypeCd)).append("\n");
    sb.append("    usageList: ").append(toIndentedString(usageList)).append("\n");
    sb.append("    phoneLocalNum: ").append(toIndentedString(phoneLocalNum)).append("\n");
    sb.append("    phoneCntryCd: ").append(toIndentedString(phoneCntryCd)).append("\n");
    sb.append("    phoneAreaCd: ").append(toIndentedString(phoneAreaCd)).append("\n");
    sb.append("    phoneExtNum: ").append(toIndentedString(phoneExtNum)).append("\n");
    sb.append("    smsCapableFlag: ").append(toIndentedString(smsCapableFlag)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    dayFlag: ").append(toIndentedString(dayFlag)).append("\n");
    sb.append("    eveningFlag: ").append(toIndentedString(eveningFlag)).append("\n");
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

