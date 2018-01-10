package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Subscription;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SubscriptionRequest   {
  
  private List<Subscription> subscriptionList = new ArrayList<Subscription>();

  
  /**
   * A list of Subscription information
   **/
  
  @ApiModelProperty(required = true, value = "A list of Subscription information")
  @JsonProperty("subscriptionList")
  public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }
  public void setSubscriptionList(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionRequest subscriptionRequest = (SubscriptionRequest) o;
    return Objects.equals(subscriptionList, subscriptionRequest.subscriptionList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionRequest {\n");
    
    sb.append("    subscriptionList: ").append(toIndentedString(subscriptionList)).append("\n");
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

