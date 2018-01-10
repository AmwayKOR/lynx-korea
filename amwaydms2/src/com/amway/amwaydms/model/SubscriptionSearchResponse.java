package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.ErrorMessage;
import com.amway.amwaydms.model.SubscriptionSearch;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SubscriptionSearchResponse   {
  
  private List<SubscriptionSearch> subscriptionAccountList = new ArrayList<SubscriptionSearch>();
  private ErrorMessage errorMessage = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("subscriptionAccountList")
  public List<SubscriptionSearch> getSubscriptionAccountList() {
    return subscriptionAccountList;
  }
  public void setSubscriptionAccountList(List<SubscriptionSearch> subscriptionAccountList) {
    this.subscriptionAccountList = subscriptionAccountList;
  }

  
  /**
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=subscription'>Reference to DMS error codes and meanings</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=subscription'>Reference to DMS error codes and meanings</a>")
  @JsonProperty("errorMessage")
  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }
  public void setErrorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionSearchResponse subscriptionSearchResponse = (SubscriptionSearchResponse) o;
    return Objects.equals(subscriptionAccountList, subscriptionSearchResponse.subscriptionAccountList) &&
        Objects.equals(errorMessage, subscriptionSearchResponse.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionAccountList, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionSearchResponse {\n");
    
    sb.append("    subscriptionAccountList: ").append(toIndentedString(subscriptionAccountList)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
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

