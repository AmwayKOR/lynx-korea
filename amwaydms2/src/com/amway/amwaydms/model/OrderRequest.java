package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class OrderRequest   {
  


  public enum ActionTypeEnum {
    ORDERCREATED("OrderCreated"),
    ORDERCANCELLED("OrderCancelled"),
    ORDERRETURNED("OrderReturned");

    private String value;

    ActionTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private ActionTypeEnum actionType = null;


  public enum OrderTypeEnum {
    FIRSTORDER("FirstOrder"),
    RENEWAL("Renewal");

    private String value;

    OrderTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private OrderTypeEnum orderType = null;
  private String invoiceNumber = null;
  private String processDate = null;

  
  /**
   * Action Type
   **/
  
  @ApiModelProperty(required = true, value = "Action Type")
  @JsonProperty("actionType")
  public ActionTypeEnum getActionType() {
    return actionType;
  }
  public void setActionType(ActionTypeEnum actionType) {
    this.actionType = actionType;
  }

  
  /**
   * Order Type
   **/
  
  @ApiModelProperty(required = true, value = "Order Type")
  @JsonProperty("orderType")
  public OrderTypeEnum getOrderType() {
    return orderType;
  }
  public void setOrderType(OrderTypeEnum orderType) {
    this.orderType = orderType;
  }

  
  /**
   * Invoice Number
   **/
  
  @ApiModelProperty(required = true, value = "Invoice Number")
  @JsonProperty("invoiceNumber")
  public String getInvoiceNumber() {
    return invoiceNumber;
  }
  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  
  /**
   * Process Date
   **/
  
  @ApiModelProperty(required = true, value = "Process Date")
  @JsonProperty("processDate")
  public String getProcessDate() {
    return processDate;
  }
  public void setProcessDate(String processDate) {
    this.processDate = processDate;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderRequest orderRequest = (OrderRequest) o;
    return Objects.equals(actionType, orderRequest.actionType) &&
        Objects.equals(orderType, orderRequest.orderType) &&
        Objects.equals(invoiceNumber, orderRequest.invoiceNumber) &&
        Objects.equals(processDate, orderRequest.processDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(actionType, orderType, invoiceNumber, processDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderRequest {\n");
    
    sb.append("    actionType: ").append(toIndentedString(actionType)).append("\n");
    sb.append("    orderType: ").append(toIndentedString(orderType)).append("\n");
    sb.append("    invoiceNumber: ").append(toIndentedString(invoiceNumber)).append("\n");
    sb.append("    processDate: ").append(toIndentedString(processDate)).append("\n");
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

