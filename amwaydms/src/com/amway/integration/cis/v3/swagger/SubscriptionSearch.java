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
 * SubscriptionSearch
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class SubscriptionSearch   {
  @SerializedName("subscriptionId")
  private String subscriptionId = null;

  @SerializedName("subscriptionName")
  private String subscriptionName = null;

  @SerializedName("subscriptionDeliveryList")
  private List<SubscriptionDelivery> subscriptionDeliveryList = new ArrayList<SubscriptionDelivery>();

  @SerializedName("salesPlanAff")
  private Integer salesPlanAff = null;

  @SerializedName("aboNum")
  private Long aboNum = null;

  @SerializedName("cntryCd")
  private String cntryCd = null;

  public SubscriptionSearch subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

   /**
   * A predefined Subscription ID
   * @return subscriptionId
  **/
  @ApiModelProperty(example = "null", required = true, value = "A predefined Subscription ID")
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public SubscriptionSearch subscriptionName(String subscriptionName) {
    this.subscriptionName = subscriptionName;
    return this;
  }

   /**
   * Get subscriptionName
   * @return subscriptionName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getSubscriptionName() {
    return subscriptionName;
  }

  public void setSubscriptionName(String subscriptionName) {
    this.subscriptionName = subscriptionName;
  }

  public SubscriptionSearch subscriptionDeliveryList(List<SubscriptionDelivery> subscriptionDeliveryList) {
    this.subscriptionDeliveryList = subscriptionDeliveryList;
    return this;
  }

  public SubscriptionSearch addSubscriptionDeliveryListItem(SubscriptionDelivery subscriptionDeliveryListItem) {
    this.subscriptionDeliveryList.add(subscriptionDeliveryListItem);
    return this;
  }

   /**
   * Get subscriptionDeliveryList
   * @return subscriptionDeliveryList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<SubscriptionDelivery> getSubscriptionDeliveryList() {
    return subscriptionDeliveryList;
  }

  public void setSubscriptionDeliveryList(List<SubscriptionDelivery> subscriptionDeliveryList) {
    this.subscriptionDeliveryList = subscriptionDeliveryList;
  }

  public SubscriptionSearch salesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
    return this;
  }

   /**
   * Get salesPlanAff
   * @return salesPlanAff
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getSalesPlanAff() {
    return salesPlanAff;
  }

  public void setSalesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  public SubscriptionSearch aboNum(Long aboNum) {
    this.aboNum = aboNum;
    return this;
  }

   /**
   * Get aboNum
   * @return aboNum
  **/
  @ApiModelProperty(example = "null", value = "")
  public Long getAboNum() {
    return aboNum;
  }

  public void setAboNum(Long aboNum) {
    this.aboNum = aboNum;
  }

  public SubscriptionSearch cntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
    return this;
  }

   /**
   * Get cntryCd
   * @return cntryCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCntryCd() {
    return cntryCd;
  }

  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionSearch subscriptionSearch = (SubscriptionSearch) o;
    return Objects.equals(this.subscriptionId, subscriptionSearch.subscriptionId) &&
        Objects.equals(this.subscriptionName, subscriptionSearch.subscriptionName) &&
        Objects.equals(this.subscriptionDeliveryList, subscriptionSearch.subscriptionDeliveryList) &&
        Objects.equals(this.salesPlanAff, subscriptionSearch.salesPlanAff) &&
        Objects.equals(this.aboNum, subscriptionSearch.aboNum) &&
        Objects.equals(this.cntryCd, subscriptionSearch.cntryCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionId, subscriptionName, subscriptionDeliveryList, salesPlanAff, aboNum, cntryCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionSearch {\n");
    
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    subscriptionName: ").append(toIndentedString(subscriptionName)).append("\n");
    sb.append("    subscriptionDeliveryList: ").append(toIndentedString(subscriptionDeliveryList)).append("\n");
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
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

