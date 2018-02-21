package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.SubscriptionDelivery;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SubscriptionInput   {
  
  private String subscriptionId = null;
  private String subscriptionName = null;
  private List<SubscriptionDelivery> subscriptionDeliveryList = new ArrayList<SubscriptionDelivery>();

  
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionInput subscriptionInput = (SubscriptionInput) o;
    return Objects.equals(subscriptionId, subscriptionInput.subscriptionId) &&
        Objects.equals(subscriptionName, subscriptionInput.subscriptionName) &&
        Objects.equals(subscriptionDeliveryList, subscriptionInput.subscriptionDeliveryList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionId, subscriptionName, subscriptionDeliveryList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionInput {\n");
    
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    subscriptionName: ").append(toIndentedString(subscriptionName)).append("\n");
    sb.append("    subscriptionDeliveryList: ").append(toIndentedString(subscriptionDeliveryList)).append("\n");
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

