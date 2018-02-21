package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BusinessNatureChange   {
  
  private String accountSubTypeCd = null;
  private String invoiceNum = null;
  private String reasonCd = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ACCST'>Reference to Account Sub Type Codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ACCST'>Reference to Account Sub Type Codes</a>")
  @JsonProperty("accountSubTypeCd")
  public String getAccountSubTypeCd() {
    return accountSubTypeCd;
  }
  public void setAccountSubTypeCd(String accountSubTypeCd) {
    this.accountSubTypeCd = accountSubTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("invoiceNum")
  public String getInvoiceNum() {
    return invoiceNum;
  }
  public void setInvoiceNum(String invoiceNum) {
    this.invoiceNum = invoiceNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("reasonCd")
  public String getReasonCd() {
    return reasonCd;
  }
  public void setReasonCd(String reasonCd) {
    this.reasonCd = reasonCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BusinessNatureChange businessNatureChange = (BusinessNatureChange) o;
    return Objects.equals(accountSubTypeCd, businessNatureChange.accountSubTypeCd) &&
        Objects.equals(invoiceNum, businessNatureChange.invoiceNum) &&
        Objects.equals(reasonCd, businessNatureChange.reasonCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountSubTypeCd, invoiceNum, reasonCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BusinessNatureChange {\n");
    
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
    sb.append("    invoiceNum: ").append(toIndentedString(invoiceNum)).append("\n");
    sb.append("    reasonCd: ").append(toIndentedString(reasonCd)).append("\n");
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

