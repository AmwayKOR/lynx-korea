/**
 * Magic DMS RESTful Services
 * No descripton provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v3
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.amway.integration.cis.v3.swagger;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;


/**
 * AccountEcommInfo
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class AccountEcommInfo   {
  @SerializedName("partyId")
  private String partyId = null;

  @SerializedName("contactId")
  private String contactId = null;

  @SerializedName("contactPointTypeCd")
  private String contactPointTypeCd = null;

  @SerializedName("usageList")
  private List<ContactUsage> usageList = new ArrayList<ContactUsage>();

  @SerializedName("primaryPartyFlag")
  private String primaryPartyFlag = null;

  @SerializedName("salesPlanAff")
  private String salesPlanAff = null;

  @SerializedName("aboNum")
  private String aboNum = null;

  @SerializedName("ecommAddr")
  private String ecommAddr = null;

  @SerializedName("allowForLogIn")
  private String allowForLogIn = null;

  @SerializedName("ecommName")
  private String ecommName = null;

  @SerializedName("ecommTypeName")
  private String ecommTypeName = null;

  @SerializedName("statusCd")
  private String statusCd = null;

  public AccountEcommInfo partyId(String partyId) {
    this.partyId = partyId;
    return this;
  }

   /**
   * party Id
   * @return partyId
  **/
  @ApiModelProperty(example = "null", required = true, value = "party Id")
  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public AccountEcommInfo contactId(String contactId) {
    this.contactId = contactId;
    return this;
  }

   /**
   * external contact Id
   * @return contactId
  **/
  @ApiModelProperty(example = "null", value = "external contact Id")
  public String getContactId() {
    return contactId;
  }

  public void setContactId(String contactId) {
    this.contactId = contactId;
  }

  public AccountEcommInfo contactPointTypeCd(String contactPointTypeCd) {
    this.contactPointTypeCd = contactPointTypeCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=COPTY'>Reference to contact point type codes</a>
   * @return contactPointTypeCd
  **/
  @ApiModelProperty(example = "null", required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=COPTY'>Reference to contact point type codes</a>")
  public String getContactPointTypeCd() {
    return contactPointTypeCd;
  }

  public void setContactPointTypeCd(String contactPointTypeCd) {
    this.contactPointTypeCd = contactPointTypeCd;
  }

  public AccountEcommInfo usageList(List<ContactUsage> usageList) {
    this.usageList = usageList;
    return this;
  }

  public AccountEcommInfo addUsageListItem(ContactUsage usageListItem) {
    this.usageList.add(usageListItem);
    return this;
  }

   /**
   * usageList
   * @return usageList
  **/
  @ApiModelProperty(example = "null", required = true, value = "usageList")
  public List<ContactUsage> getUsageList() {
    return usageList;
  }

  public void setUsageList(List<ContactUsage> usageList) {
    this.usageList = usageList;
  }

  public AccountEcommInfo primaryPartyFlag(String primaryPartyFlag) {
    this.primaryPartyFlag = primaryPartyFlag;
    return this;
  }

   /**
   * Get primaryPartyFlag
   * @return primaryPartyFlag
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPrimaryPartyFlag() {
    return primaryPartyFlag;
  }

  public void setPrimaryPartyFlag(String primaryPartyFlag) {
    this.primaryPartyFlag = primaryPartyFlag;
  }

  public AccountEcommInfo salesPlanAff(String salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
    return this;
  }

   /**
   * Get salesPlanAff
   * @return salesPlanAff
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getSalesPlanAff() {
    return salesPlanAff;
  }

  public void setSalesPlanAff(String salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  public AccountEcommInfo aboNum(String aboNum) {
    this.aboNum = aboNum;
    return this;
  }

   /**
   * Get aboNum
   * @return aboNum
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAboNum() {
    return aboNum;
  }

  public void setAboNum(String aboNum) {
    this.aboNum = aboNum;
  }

  public AccountEcommInfo ecommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
    return this;
  }

   /**
   * Get ecommAddr
   * @return ecommAddr
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getEcommAddr() {
    return ecommAddr;
  }

  public void setEcommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
  }

  public AccountEcommInfo allowForLogIn(String allowForLogIn) {
    this.allowForLogIn = allowForLogIn;
    return this;
  }

   /**
   * Get allowForLogIn
   * @return allowForLogIn
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAllowForLogIn() {
    return allowForLogIn;
  }

  public void setAllowForLogIn(String allowForLogIn) {
    this.allowForLogIn = allowForLogIn;
  }

  public AccountEcommInfo ecommName(String ecommName) {
    this.ecommName = ecommName;
    return this;
  }

   /**
   * Get ecommName
   * @return ecommName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getEcommName() {
    return ecommName;
  }

  public void setEcommName(String ecommName) {
    this.ecommName = ecommName;
  }

  public AccountEcommInfo ecommTypeName(String ecommTypeName) {
    this.ecommTypeName = ecommTypeName;
    return this;
  }

   /**
   * Get ecommTypeName
   * @return ecommTypeName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getEcommTypeName() {
    return ecommTypeName;
  }

  public void setEcommTypeName(String ecommTypeName) {
    this.ecommTypeName = ecommTypeName;
  }

  public AccountEcommInfo statusCd(String statusCd) {
    this.statusCd = statusCd;
    return this;
  }

   /**
   * Get statusCd
   * @return statusCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountEcommInfo accountEcommInfo = (AccountEcommInfo) o;
    return Objects.equals(this.partyId, accountEcommInfo.partyId) &&
        Objects.equals(this.contactId, accountEcommInfo.contactId) &&
        Objects.equals(this.contactPointTypeCd, accountEcommInfo.contactPointTypeCd) &&
        Objects.equals(this.usageList, accountEcommInfo.usageList) &&
        Objects.equals(this.primaryPartyFlag, accountEcommInfo.primaryPartyFlag) &&
        Objects.equals(this.salesPlanAff, accountEcommInfo.salesPlanAff) &&
        Objects.equals(this.aboNum, accountEcommInfo.aboNum) &&
        Objects.equals(this.ecommAddr, accountEcommInfo.ecommAddr) &&
        Objects.equals(this.allowForLogIn, accountEcommInfo.allowForLogIn) &&
        Objects.equals(this.ecommName, accountEcommInfo.ecommName) &&
        Objects.equals(this.ecommTypeName, accountEcommInfo.ecommTypeName) &&
        Objects.equals(this.statusCd, accountEcommInfo.statusCd);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

