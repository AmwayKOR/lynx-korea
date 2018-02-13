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
 * SearchAccount
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class SearchAccount   {
  @SerializedName("salesPlanAff")
  private String salesPlanAff = null;

  @SerializedName("aboNum")
  private String aboNum = null;

  @SerializedName("accountName")
  private String accountName = null;

  @SerializedName("statusCd")
  private String statusCd = null;

  @SerializedName("accountSubTypeCd")
  private String accountSubTypeCd = null;

  @SerializedName("partyList")
  private List<SearchParty> partyList = new ArrayList<SearchParty>();

  public SearchAccount salesPlanAff(String salesPlanAff) {
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

  public SearchAccount aboNum(String aboNum) {
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

  public SearchAccount accountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

   /**
   * Get accountName
   * @return accountName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public SearchAccount statusCd(String statusCd) {
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

  public SearchAccount accountSubTypeCd(String accountSubTypeCd) {
    this.accountSubTypeCd = accountSubTypeCd;
    return this;
  }

   /**
   * Get accountSubTypeCd
   * @return accountSubTypeCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAccountSubTypeCd() {
    return accountSubTypeCd;
  }

  public void setAccountSubTypeCd(String accountSubTypeCd) {
    this.accountSubTypeCd = accountSubTypeCd;
  }

  public SearchAccount partyList(List<SearchParty> partyList) {
    this.partyList = partyList;
    return this;
  }

  public SearchAccount addPartyListItem(SearchParty partyListItem) {
    this.partyList.add(partyListItem);
    return this;
  }

   /**
   * Get partyList
   * @return partyList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<SearchParty> getPartyList() {
    return partyList;
  }

  public void setPartyList(List<SearchParty> partyList) {
    this.partyList = partyList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchAccount searchAccount = (SearchAccount) o;
    return Objects.equals(this.salesPlanAff, searchAccount.salesPlanAff) &&
        Objects.equals(this.aboNum, searchAccount.aboNum) &&
        Objects.equals(this.accountName, searchAccount.accountName) &&
        Objects.equals(this.statusCd, searchAccount.statusCd) &&
        Objects.equals(this.accountSubTypeCd, searchAccount.accountSubTypeCd) &&
        Objects.equals(this.partyList, searchAccount.partyList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(salesPlanAff, aboNum, accountName, statusCd, accountSubTypeCd, partyList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchAccount {\n");
    
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    accountName: ").append(toIndentedString(accountName)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
    sb.append("    partyList: ").append(toIndentedString(partyList)).append("\n");
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

