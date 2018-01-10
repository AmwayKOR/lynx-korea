package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SubscriptionDelivery   {
  
  private String deliveryTypeCd = null;


  public enum DeliveryChoiceCdEnum {
    Y("Y"),
    N("N");

    private String value;

    DeliveryChoiceCdEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private DeliveryChoiceCdEnum deliveryChoiceCd = null;

  
  /**
   * Valid values are: AD-Address,EM-Email,PH-Phone,SM-SMS,SO-Social Media
   **/
  
  @ApiModelProperty(required = true, value = "Valid values are: AD-Address,EM-Email,PH-Phone,SM-SMS,SO-Social Media")
  @JsonProperty("deliveryTypeCd")
  public String getDeliveryTypeCd() {
    return deliveryTypeCd;
  }
  public void setDeliveryTypeCd(String deliveryTypeCd) {
    this.deliveryTypeCd = deliveryTypeCd;
  }

  
  /**
   * Valid Values
   **/
  
  @ApiModelProperty(required = true, value = "Valid Values")
  @JsonProperty("deliveryChoiceCd")
  public DeliveryChoiceCdEnum getDeliveryChoiceCd() {
    return deliveryChoiceCd;
  }
  public void setDeliveryChoiceCd(DeliveryChoiceCdEnum deliveryChoiceCd) {
    this.deliveryChoiceCd = deliveryChoiceCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionDelivery subscriptionDelivery = (SubscriptionDelivery) o;
    return Objects.equals(deliveryTypeCd, subscriptionDelivery.deliveryTypeCd) &&
        Objects.equals(deliveryChoiceCd, subscriptionDelivery.deliveryChoiceCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deliveryTypeCd, deliveryChoiceCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionDelivery {\n");
    
    sb.append("    deliveryTypeCd: ").append(toIndentedString(deliveryTypeCd)).append("\n");
    sb.append("    deliveryChoiceCd: ").append(toIndentedString(deliveryChoiceCd)).append("\n");
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

