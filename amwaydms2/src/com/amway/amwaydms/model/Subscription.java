package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.SubscriptionDelivery;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Subscription   {
  
  private String subscriptionId = null;
  private String subscriptionName = null;
  private List<SubscriptionDelivery> subscriptionDeliveryList = new ArrayList<SubscriptionDelivery>();
  private String isPublicationCd = null;
  private String pauseCd = null;
  private String restoreCd = null;
  private String cancelCd = null;
  private String subscribeFlag = null;
  private String languageCd = null;
  private Long partyId = null;
  private List<String> languageCdList = new ArrayList<String>();
  private String pubCategoryDesc = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SUBCD'>Reference to Subscription Id</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SUBCD'>Reference to Subscription Id</a>")
  @JsonProperty("subscriptionId")
  public String getSubscriptionId() {
    return subscriptionId;
  }
  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("subscriptionName")
  public String getSubscriptionName() {
    return subscriptionName;
  }
  public void setSubscriptionName(String subscriptionName) {
    this.subscriptionName = subscriptionName;
  }

  
  /**
   * subscriptionDeliveryList
   **/
  
  @ApiModelProperty(required = true, value = "subscriptionDeliveryList")
  @JsonProperty("subscriptionDeliveryList")
  public List<SubscriptionDelivery> getSubscriptionDeliveryList() {
    return subscriptionDeliveryList;
  }
  public void setSubscriptionDeliveryList(List<SubscriptionDelivery> subscriptionDeliveryList) {
    this.subscriptionDeliveryList = subscriptionDeliveryList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("isPublicationCd")
  public String getIsPublicationCd() {
    return isPublicationCd;
  }
  public void setIsPublicationCd(String isPublicationCd) {
    this.isPublicationCd = isPublicationCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("pauseCd")
  public String getPauseCd() {
    return pauseCd;
  }
  public void setPauseCd(String pauseCd) {
    this.pauseCd = pauseCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("restoreCd")
  public String getRestoreCd() {
    return restoreCd;
  }
  public void setRestoreCd(String restoreCd) {
    this.restoreCd = restoreCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cancelCd")
  public String getCancelCd() {
    return cancelCd;
  }
  public void setCancelCd(String cancelCd) {
    this.cancelCd = cancelCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("subscribeFlag")
  public String getSubscribeFlag() {
    return subscribeFlag;
  }
  public void setSubscribeFlag(String subscribeFlag) {
    this.subscribeFlag = subscribeFlag;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("languageCd")
  public String getLanguageCd() {
    return languageCd;
  }
  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyId")
  public Long getPartyId() {
    return partyId;
  }
  public void setPartyId(Long partyId) {
    this.partyId = partyId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("languageCdList")
  public List<String> getLanguageCdList() {
    return languageCdList;
  }
  public void setLanguageCdList(List<String> languageCdList) {
    this.languageCdList = languageCdList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("pubCategoryDesc")
  public String getPubCategoryDesc() {
    return pubCategoryDesc;
  }
  public void setPubCategoryDesc(String pubCategoryDesc) {
    this.pubCategoryDesc = pubCategoryDesc;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Subscription subscription = (Subscription) o;
    return Objects.equals(subscriptionId, subscription.subscriptionId) &&
        Objects.equals(subscriptionName, subscription.subscriptionName) &&
        Objects.equals(subscriptionDeliveryList, subscription.subscriptionDeliveryList) &&
        Objects.equals(isPublicationCd, subscription.isPublicationCd) &&
        Objects.equals(pauseCd, subscription.pauseCd) &&
        Objects.equals(restoreCd, subscription.restoreCd) &&
        Objects.equals(cancelCd, subscription.cancelCd) &&
        Objects.equals(subscribeFlag, subscription.subscribeFlag) &&
        Objects.equals(languageCd, subscription.languageCd) &&
        Objects.equals(partyId, subscription.partyId) &&
        Objects.equals(languageCdList, subscription.languageCdList) &&
        Objects.equals(pubCategoryDesc, subscription.pubCategoryDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionId, subscriptionName, subscriptionDeliveryList, isPublicationCd, pauseCd, restoreCd, cancelCd, subscribeFlag, languageCd, partyId, languageCdList, pubCategoryDesc);
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
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    languageCdList: ").append(toIndentedString(languageCdList)).append("\n");
    sb.append("    pubCategoryDesc: ").append(toIndentedString(pubCategoryDesc)).append("\n");
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

