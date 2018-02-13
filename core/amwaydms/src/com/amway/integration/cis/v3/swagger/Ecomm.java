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
 * Ecomm
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class Ecomm   {
  @SerializedName("partyId")
  private String partyId = null;

  @SerializedName("contactId")
  private String contactId = null;

  @SerializedName("contactPointTypeCd")
  private String contactPointTypeCd = null;

  @SerializedName("usageList")
  private List<ContactUsage> usageList = new ArrayList<ContactUsage>();

  @SerializedName("ecommAddr")
  private String ecommAddr = null;

  @SerializedName("allowForLogIn")
  private Boolean allowForLogIn = null;

  @SerializedName("ecommName")
  private String ecommName = null;

  @SerializedName("ecommTypeCd")
  private String ecommTypeCd = null;

  @SerializedName("ecommTypeName")
  private String ecommTypeName = null;

  @SerializedName("statusCd")
  private String statusCd = null;

  public Ecomm partyId(String partyId) {
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

  public Ecomm contactId(String contactId) {
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

  public Ecomm contactPointTypeCd(String contactPointTypeCd) {
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

  public Ecomm usageList(List<ContactUsage> usageList) {
    this.usageList = usageList;
    return this;
  }

  public Ecomm addUsageListItem(ContactUsage usageListItem) {
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

  public Ecomm ecommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
    return this;
  }

   /**
   * Email Address
   * @return ecommAddr
  **/
  @ApiModelProperty(example = "null", required = true, value = "Email Address")
  public String getEcommAddr() {
    return ecommAddr;
  }

  public void setEcommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
  }

  public Ecomm allowForLogIn(Boolean allowForLogIn) {
    this.allowForLogIn = allowForLogIn;
    return this;
  }

   /**
   * Allow for Login
   * @return allowForLogIn
  **/
  @ApiModelProperty(example = "null", value = "Allow for Login")
  public Boolean getAllowForLogIn() {
    return allowForLogIn;
  }

  public void setAllowForLogIn(Boolean allowForLogIn) {
    this.allowForLogIn = allowForLogIn;
  }

  public Ecomm ecommName(String ecommName) {
    this.ecommName = ecommName;
    return this;
  }

   /**
   * E-communication name
   * @return ecommName
  **/
  @ApiModelProperty(example = "null", value = "E-communication name")
  public String getEcommName() {
    return ecommName;
  }

  public void setEcommName(String ecommName) {
    this.ecommName = ecommName;
  }

  public Ecomm ecommTypeCd(String ecommTypeCd) {
    this.ecommTypeCd = ecommTypeCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ECMTC'>Reference to E-Communication type codes</a>
   * @return ecommTypeCd
  **/
  @ApiModelProperty(example = "null", required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ECMTC'>Reference to E-Communication type codes</a>")
  public String getEcommTypeCd() {
    return ecommTypeCd;
  }

  public void setEcommTypeCd(String ecommTypeCd) {
    this.ecommTypeCd = ecommTypeCd;
  }

  public Ecomm ecommTypeName(String ecommTypeName) {
    this.ecommTypeName = ecommTypeName;
    return this;
  }

   /**
   * E-communication type name
   * @return ecommTypeName
  **/
  @ApiModelProperty(example = "null", value = "E-communication type name")
  public String getEcommTypeName() {
    return ecommTypeName;
  }

  public void setEcommTypeName(String ecommTypeName) {
    this.ecommTypeName = ecommTypeName;
  }

  public Ecomm statusCd(String statusCd) {
    this.statusCd = statusCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=EMSCD'>Reference to ecomm status codes</a>
   * @return statusCd
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=EMSCD'>Reference to ecomm status codes</a>")
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
    Ecomm ecomm = (Ecomm) o;
    return Objects.equals(this.partyId, ecomm.partyId) &&
        Objects.equals(this.contactId, ecomm.contactId) &&
        Objects.equals(this.contactPointTypeCd, ecomm.contactPointTypeCd) &&
        Objects.equals(this.usageList, ecomm.usageList) &&
        Objects.equals(this.ecommAddr, ecomm.ecommAddr) &&
        Objects.equals(this.allowForLogIn, ecomm.allowForLogIn) &&
        Objects.equals(this.ecommName, ecomm.ecommName) &&
        Objects.equals(this.ecommTypeCd, ecomm.ecommTypeCd) &&
        Objects.equals(this.ecommTypeName, ecomm.ecommTypeName) &&
        Objects.equals(this.statusCd, ecomm.statusCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, contactId, contactPointTypeCd, usageList, ecommAddr, allowForLogIn, ecommName, ecommTypeCd, ecommTypeName, statusCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ecomm {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    contactId: ").append(toIndentedString(contactId)).append("\n");
    sb.append("    contactPointTypeCd: ").append(toIndentedString(contactPointTypeCd)).append("\n");
    sb.append("    usageList: ").append(toIndentedString(usageList)).append("\n");
    sb.append("    ecommAddr: ").append(toIndentedString(ecommAddr)).append("\n");
    sb.append("    allowForLogIn: ").append(toIndentedString(allowForLogIn)).append("\n");
    sb.append("    ecommName: ").append(toIndentedString(ecommName)).append("\n");
    sb.append("    ecommTypeCd: ").append(toIndentedString(ecommTypeCd)).append("\n");
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
