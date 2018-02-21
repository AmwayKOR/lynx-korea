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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;


/**
 * Subscription
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Subscription   {
  @SerializedName("subscriptionId")
  private String subscriptionId = null;

  @SerializedName("subscriptionName")
  private String subscriptionName = null;

  @SerializedName("subscriptionDeliveryList")
  private List<SubscriptionDelivery> subscriptionDeliveryList = new ArrayList<SubscriptionDelivery>();

  @SerializedName("isPublicationCd")
  private String isPublicationCd = null;

  @SerializedName("pauseCd")
  private String pauseCd = null;

  @SerializedName("restoreCd")
  private String restoreCd = null;

  @SerializedName("cancelCd")
  private String cancelCd = null;

  @SerializedName("subscribeFlag")
  private String subscribeFlag = null;

  @SerializedName("languageCd")
  private String languageCd = null;

  public Subscription subscriptionId(String subscriptionId) {
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

  public Subscription subscriptionName(String subscriptionName) {
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

  public Subscription subscriptionDeliveryList(List<SubscriptionDelivery> subscriptionDeliveryList) {
    this.subscriptionDeliveryList = subscriptionDeliveryList;
    return this;
  }

  public Subscription addSubscriptionDeliveryListItem(SubscriptionDelivery subscriptionDeliveryListItem) {
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

  public Subscription isPublicationCd(String isPublicationCd) {
    this.isPublicationCd = isPublicationCd;
    return this;
  }

   /**
   * Get isPublicationCd
   * @return isPublicationCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getIsPublicationCd() {
    return isPublicationCd;
  }

  public void setIsPublicationCd(String isPublicationCd) {
    this.isPublicationCd = isPublicationCd;
  }

  public Subscription pauseCd(String pauseCd) {
    this.pauseCd = pauseCd;
    return this;
  }

   /**
   * Get pauseCd
   * @return pauseCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPauseCd() {
    return pauseCd;
  }

  public void setPauseCd(String pauseCd) {
    this.pauseCd = pauseCd;
  }

  public Subscription restoreCd(String restoreCd) {
    this.restoreCd = restoreCd;
    return this;
  }

   /**
   * Get restoreCd
   * @return restoreCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getRestoreCd() {
    return restoreCd;
  }

  public void setRestoreCd(String restoreCd) {
    this.restoreCd = restoreCd;
  }

  public Subscription cancelCd(String cancelCd) {
    this.cancelCd = cancelCd;
    return this;
  }

   /**
   * Get cancelCd
   * @return cancelCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCancelCd() {
    return cancelCd;
  }

  public void setCancelCd(String cancelCd) {
    this.cancelCd = cancelCd;
  }

  public Subscription subscribeFlag(String subscribeFlag) {
    this.subscribeFlag = subscribeFlag;
    return this;
  }

   /**
   * Get subscribeFlag
   * @return subscribeFlag
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getSubscribeFlag() {
    return subscribeFlag;
  }

  public void setSubscribeFlag(String subscribeFlag) {
    this.subscribeFlag = subscribeFlag;
  }

  public Subscription languageCd(String languageCd) {
    this.languageCd = languageCd;
    return this;
  }

   /**
   * Get languageCd
   * @return languageCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getLanguageCd() {
    return languageCd;
  }

  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscription subscription = (Subscription) o;
    return Objects.equals(this.subscriptionId, subscription.subscriptionId) &&
        Objects.equals(this.subscriptionName, subscription.subscriptionName) &&
        Objects.equals(this.subscriptionDeliveryList, subscription.subscriptionDeliveryList) &&
        Objects.equals(this.isPublicationCd, subscription.isPublicationCd) &&
        Objects.equals(this.pauseCd, subscription.pauseCd) &&
        Objects.equals(this.restoreCd, subscription.restoreCd) &&
        Objects.equals(this.cancelCd, subscription.cancelCd) &&
        Objects.equals(this.subscribeFlag, subscription.subscribeFlag) &&
        Objects.equals(this.languageCd, subscription.languageCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionId, subscriptionName, subscriptionDeliveryList, isPublicationCd, pauseCd, restoreCd, cancelCd, subscribeFlag, languageCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Subscription {\n");
    
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    subscriptionName: ").append(toIndentedString(subscriptionName)).append("\n");
    sb.append("    subscriptionDeliveryList: ").append(toIndentedString(subscriptionDeliveryList)).append("\n");
    sb.append("    isPublicationCd: ").append(toIndentedString(isPublicationCd)).append("\n");
    sb.append("    pauseCd: ").append(toIndentedString(pauseCd)).append("\n");
    sb.append("    restoreCd: ").append(toIndentedString(restoreCd)).append("\n");
    sb.append("    cancelCd: ").append(toIndentedString(cancelCd)).append("\n");
    sb.append("    subscribeFlag: ").append(toIndentedString(subscribeFlag)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
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

