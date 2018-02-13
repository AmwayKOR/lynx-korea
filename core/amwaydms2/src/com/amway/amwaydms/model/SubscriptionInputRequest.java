package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.SubscriptionInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SubscriptionInputRequest   {
  
  private List<SubscriptionInput> subscriptionInputList = new ArrayList<SubscriptionInput>();

  
  /**
   * A list of Subscription to be created
   **/
  
  @ApiModelProperty(required = true, value = "A list of Subscription to be created")
  @JsonProperty("subscriptionInputList")
  public List<SubscriptionInput> getSubscriptionInputList() {
    return subscriptionInputList;
  }
  public void setSubscriptionInputList(List<SubscriptionInput> subscriptionInputList) {
    this.subscriptionInputList = subscriptionInputList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionInputRequest subscriptionInputRequest = (SubscriptionInputRequest) o;
    return Objects.equals(subscriptionInputList, subscriptionInputRequest.subscriptionInputList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionInputList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionInputRequest {\n");
    
    sb.append("    subscriptionInputList: ").append(toIndentedString(subscriptionInputList)).append("\n");
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

