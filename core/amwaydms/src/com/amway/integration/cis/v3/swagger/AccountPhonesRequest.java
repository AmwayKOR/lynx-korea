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
 * AccountPhonesRequest
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class AccountPhonesRequest   {
  @SerializedName("affAboList")
  private List<AffAbo> affAboList = new ArrayList<AffAbo>();

  @SerializedName("primaryPartyFlag")
  private String primaryPartyFlag = null;

  @SerializedName("contactPointTypeCd")
  private String contactPointTypeCd = null;

  @SerializedName("contactPointPurposeCd")
  private String contactPointPurposeCd = null;

  @SerializedName("primaryUsageFlag")
  private String primaryUsageFlag = null;

  public AccountPhonesRequest affAboList(List<AffAbo> affAboList) {
    this.affAboList = affAboList;
    return this;
  }

  public AccountPhonesRequest addAffAboListItem(AffAbo affAboListItem) {
    this.affAboList.add(affAboListItem);
    return this;
  }

   /**
   * affAboList
   * @return affAboList
  **/
  @ApiModelProperty(example = "null", required = true, value = "affAboList")
  public List<AffAbo> getAffAboList() {
    return affAboList;
  }

  public void setAffAboList(List<AffAbo> affAboList) {
    this.affAboList = affAboList;
  }

  public AccountPhonesRequest primaryPartyFlag(String primaryPartyFlag) {
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

  public AccountPhonesRequest contactPointTypeCd(String contactPointTypeCd) {
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

  public AccountPhonesRequest contactPointPurposeCd(String contactPointPurposeCd) {
    this.contactPointPurposeCd = contactPointPurposeCd;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=CNPTU'>Reference to contact point type codes</a>
   * @return contactPointPurposeCd
  **/
  @ApiModelProperty(example = "null", required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=CNPTU'>Reference to contact point type codes</a>")
  public String getContactPointPurposeCd() {
    return contactPointPurposeCd;
  }

  public void setContactPointPurposeCd(String contactPointPurposeCd) {
    this.contactPointPurposeCd = contactPointPurposeCd;
  }

  public AccountPhonesRequest primaryUsageFlag(String primaryUsageFlag) {
    this.primaryUsageFlag = primaryUsageFlag;
    return this;
  }

   /**
   * Get primaryUsageFlag
   * @return primaryUsageFlag
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPrimaryUsageFlag() {
    return primaryUsageFlag;
  }

  public void setPrimaryUsageFlag(String primaryUsageFlag) {
    this.primaryUsageFlag = primaryUsageFlag;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountPhonesRequest accountPhonesRequest = (AccountPhonesRequest) o;
    return Objects.equals(this.affAboList, accountPhonesRequest.affAboList) &&
        Objects.equals(this.primaryPartyFlag, accountPhonesRequest.primaryPartyFlag) &&
        Objects.equals(this.contactPointTypeCd, accountPhonesRequest.contactPointTypeCd) &&
        Objects.equals(this.contactPointPurposeCd, accountPhonesRequest.contactPointPurposeCd) &&
        Objects.equals(this.primaryUsageFlag, accountPhonesRequest.primaryUsageFlag);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

