package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.SubscriptionDelivery;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SubscriptionSearch   {
  
  private String subscriptionId = null;
  private String subscriptionName = null;
  private List<SubscriptionDelivery> subscriptionDeliveryList = new ArrayList<SubscriptionDelivery>();
  private Integer salesPlanAff = null;
  private Long aboNum = null;
  private String cntryCd = null;
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
  @JsonProperty("salesPlanAff")
  public Integer getSalesPlanAff() {
    return salesPlanAff;
  }
  public void setSalesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("aboNum")
  public Long getAboNum() {
    return aboNum;
  }
  public void setAboNum(Long aboNum) {
    this.aboNum = aboNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
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
    SubscriptionSearch subscriptionSearch = (SubscriptionSearch) o;
    return Objects.equals(subscriptionId, subscriptionSearch.subscriptionId) &&
        Objects.equals(subscriptionName, subscriptionSearch.subscriptionName) &&
        Objects.equals(subscriptionDeliveryList, subscriptionSearch.subscriptionDeliveryList) &&
        Objects.equals(salesPlanAff, subscriptionSearch.salesPlanAff) &&
        Objects.equals(aboNum, subscriptionSearch.aboNum) &&
        Objects.equals(cntryCd, subscriptionSearch.cntryCd) &&
        Objects.equals(pubCategoryDesc, subscriptionSearch.pubCategoryDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionId, subscriptionName, subscriptionDeliveryList, salesPlanAff, aboNum, cntryCd, pubCategoryDesc);
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

