package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.UsageData;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountPhoneData   {
  
  private String partyId = null;
  private String contactId = null;
  private String contactPointName = null;
  private String contactPointTypeCd = null;
  private List<UsageData> usageDataList = new ArrayList<UsageData>();
  private String primaryPartyFlag = null;
  private String salesPlanAff = null;
  private String aboNum = null;
  private String phoneLocalNum = null;
  private String phoneCntryCd = null;
  private String phoneAreaCd = null;
  private String phoneExtNum = null;
  private String smsCapableFlag = null;
  private String cntryCd = null;
  private String dayFlag = null;
  private String eveningFlag = null;
  private String statusCd = null;

  
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
  @JsonProperty("contactId")
  public String getContactId() {
    return contactId;
  }
  public void setContactId(String contactId) {
    this.contactId = contactId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("contactPointName")
  public String getContactPointName() {
    return contactPointName;
  }
  public void setContactPointName(String contactPointName) {
    this.contactPointName = contactPointName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("contactPointTypeCd")
  public String getContactPointTypeCd() {
    return contactPointTypeCd;
  }
  public void setContactPointTypeCd(String contactPointTypeCd) {
    this.contactPointTypeCd = contactPointTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("usageDataList")
  public List<UsageData> getUsageDataList() {
    return usageDataList;
  }
  public void setUsageDataList(List<UsageData> usageDataList) {
    this.usageDataList = usageDataList;
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
  @JsonProperty("phoneLocalNum")
  public String getPhoneLocalNum() {
    return phoneLocalNum;
  }
  public void setPhoneLocalNum(String phoneLocalNum) {
    this.phoneLocalNum = phoneLocalNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneCntryCd")
  public String getPhoneCntryCd() {
    return phoneCntryCd;
  }
  public void setPhoneCntryCd(String phoneCntryCd) {
    this.phoneCntryCd = phoneCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneAreaCd")
  public String getPhoneAreaCd() {
    return phoneAreaCd;
  }
  public void setPhoneAreaCd(String phoneAreaCd) {
    this.phoneAreaCd = phoneAreaCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneExtNum")
  public String getPhoneExtNum() {
    return phoneExtNum;
  }
  public void setPhoneExtNum(String phoneExtNum) {
    this.phoneExtNum = phoneExtNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("smsCapableFlag")
  public String getSmsCapableFlag() {
    return smsCapableFlag;
  }
  public void setSmsCapableFlag(String smsCapableFlag) {
    this.smsCapableFlag = smsCapableFlag;
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
  @JsonProperty("dayFlag")
  public String getDayFlag() {
    return dayFlag;
  }
  public void setDayFlag(String dayFlag) {
    this.dayFlag = dayFlag;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("eveningFlag")
  public String getEveningFlag() {
    return eveningFlag;
  }
  public void setEveningFlag(String eveningFlag) {
    this.eveningFlag = eveningFlag;
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
    AccountPhoneData accountPhoneData = (AccountPhoneData) o;
    return Objects.equals(partyId, accountPhoneData.partyId) &&
        Objects.equals(contactId, accountPhoneData.contactId) &&
        Objects.equals(contactPointName, accountPhoneData.contactPointName) &&
        Objects.equals(contactPointTypeCd, accountPhoneData.contactPointTypeCd) &&
        Objects.equals(usageDataList, accountPhoneData.usageDataList) &&
        Objects.equals(primaryPartyFlag, accountPhoneData.primaryPartyFlag) &&
        Objects.equals(salesPlanAff, accountPhoneData.salesPlanAff) &&
        Objects.equals(aboNum, accountPhoneData.aboNum) &&
        Objects.equals(phoneLocalNum, accountPhoneData.phoneLocalNum) &&
        Objects.equals(phoneCntryCd, accountPhoneData.phoneCntryCd) &&
        Objects.equals(phoneAreaCd, accountPhoneData.phoneAreaCd) &&
        Objects.equals(phoneExtNum, accountPhoneData.phoneExtNum) &&
        Objects.equals(smsCapableFlag, accountPhoneData.smsCapableFlag) &&
        Objects.equals(cntryCd, accountPhoneData.cntryCd) &&
        Objects.equals(dayFlag, accountPhoneData.dayFlag) &&
        Objects.equals(eveningFlag, accountPhoneData.eveningFlag) &&
        Objects.equals(statusCd, accountPhoneData.statusCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, contactId, contactPointName, contactPointTypeCd, usageDataList, primaryPartyFlag, salesPlanAff, aboNum, phoneLocalNum, phoneCntryCd, phoneAreaCd, phoneExtNum, smsCapableFlag, cntryCd, dayFlag, eveningFlag, statusCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountPhoneData {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
    sb.append("    contactPointName: ").append(toIndentedString(contactPointName)).append("\n");
    sb.append("    contactPointTypeCd: ").append(toIndentedString(contactPointTypeCd)).append("\n");
    sb.append("    usageDataList: ").append(toIndentedString(usageDataList)).append("\n");
    sb.append("    primaryPartyFlag: ").append(toIndentedString(primaryPartyFlag)).append("\n");
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
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

