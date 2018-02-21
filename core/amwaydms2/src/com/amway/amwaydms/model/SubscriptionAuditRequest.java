package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SubscriptionAuditRequest   {
  
  private Long subscriptionId = null;
  private String cntryCd = null;
  private String deliveryTypeCd = null;
  private String date = null;
  private Long partyId = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SUBCD'>Reference to Subscription Id</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SUBCD'>Reference to Subscription Id</a>")
  @JsonProperty("subscriptionId")
  public Long getSubscriptionId() {
    return subscriptionId;
  }
  public void setSubscriptionId(Long subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  
  /**
   * ISO Country Code
   **/
  
  @ApiModelProperty(required = true, value = "ISO Country Code")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  
  /**
   * Delivery type Code
   **/
  
  @ApiModelProperty(required = true, value = "Delivery type Code")
  @JsonProperty("deliveryTypeCd")
  public String getDeliveryTypeCd() {
    return deliveryTypeCd;
  }
  public void setDeliveryTypeCd(String deliveryTypeCd) {
    this.deliveryTypeCd = deliveryTypeCd;
  }

  
  /**
   * date
   **/
  
  @ApiModelProperty(value = "date")
  @JsonProperty("date")
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }

  
  /**
   * Party Id
   **/
  
  @ApiModelProperty(value = "Party Id")
  @JsonProperty("partyId")
  public Long getPartyId() {
    return partyId;
  }
  public void setPartyId(Long partyId) {
    this.partyId = partyId;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionAuditRequest subscriptionAuditRequest = (SubscriptionAuditRequest) o;
    return Objects.equals(subscriptionId, subscriptionAuditRequest.subscriptionId) &&
        Objects.equals(cntryCd, subscriptionAuditRequest.cntryCd) &&
        Objects.equals(deliveryTypeCd, subscriptionAuditRequest.deliveryTypeCd) &&
        Objects.equals(date, subscriptionAuditRequest.date) &&
        Objects.equals(partyId, subscriptionAuditRequest.partyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionId, cntryCd, deliveryTypeCd, date, partyId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionAuditRequest {\n");
    
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    deliveryTypeCd: ").append(toIndentedString(deliveryTypeCd)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
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

