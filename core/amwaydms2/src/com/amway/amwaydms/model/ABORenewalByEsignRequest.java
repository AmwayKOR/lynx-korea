package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.EsignRequestDetailInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class ABORenewalByEsignRequest   {
  
  private String renewalDate = null;
  private String renewalCd = null;
  private String invoiceNum = null;
  private Boolean renewalWithGroupFlg = null;
  private EsignRequestDetailInput esignRequestData = null;

  
  /**
   * Renewal Date
   **/
  
  @ApiModelProperty(required = true, value = "Renewal Date")
  @JsonProperty("renewalDate")
  public String getRenewalDate() {
    return renewalDate;
  }
  public void setRenewalDate(String renewalDate) {
    this.renewalDate = renewalDate;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=RENCD'>Reference to Renewal Codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=RENCD'>Reference to Renewal Codes</a>")
  @JsonProperty("renewalCd")
  public String getRenewalCd() {
    return renewalCd;
  }
  public void setRenewalCd(String renewalCd) {
    this.renewalCd = renewalCd;
  }

  
  /**
   * Invoice Number
   **/
  
  @ApiModelProperty(value = "Invoice Number")
  @JsonProperty("invoiceNum")
  public String getInvoiceNum() {
    return invoiceNum;
  }
  public void setInvoiceNum(String invoiceNum) {
    this.invoiceNum = invoiceNum;
  }

  
  /**
   * Renewal With GroupFlg
   **/
  
  @ApiModelProperty(value = "Renewal With GroupFlg")
  @JsonProperty("renewalWithGroupFlg")
  public Boolean getRenewalWithGroupFlg() {
    return renewalWithGroupFlg;
  }
  public void setRenewalWithGroupFlg(Boolean renewalWithGroupFlg) {
    this.renewalWithGroupFlg = renewalWithGroupFlg;
  }

  
  /**
   * esignData
   **/
  
  @ApiModelProperty(required = true, value = "esignData")
  @JsonProperty("esignRequestData")
  public EsignRequestDetailInput getEsignRequestData() {
    return esignRequestData;
  }
  public void setEsignRequestData(EsignRequestDetailInput esignRequestData) {
    this.esignRequestData = esignRequestData;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ABORenewalByEsignRequest aBORenewalByEsignRequest = (ABORenewalByEsignRequest) o;
    return Objects.equals(renewalDate, aBORenewalByEsignRequest.renewalDate) &&
        Objects.equals(renewalCd, aBORenewalByEsignRequest.renewalCd) &&
        Objects.equals(invoiceNum, aBORenewalByEsignRequest.invoiceNum) &&
        Objects.equals(renewalWithGroupFlg, aBORenewalByEsignRequest.renewalWithGroupFlg) &&
        Objects.equals(esignRequestData, aBORenewalByEsignRequest.esignRequestData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(renewalDate, renewalCd, invoiceNum, renewalWithGroupFlg, esignRequestData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ABORenewalByEsignRequest {\n");
    
    sb.append("    renewalDate: ").append(toIndentedString(renewalDate)).append("\n");
    sb.append("    renewalCd: ").append(toIndentedString(renewalCd)).append("\n");
    sb.append("    invoiceNum: ").append(toIndentedString(invoiceNum)).append("\n");
    sb.append("    renewalWithGroupFlg: ").append(toIndentedString(renewalWithGroupFlg)).append("\n");
    sb.append("    esignRequestData: ").append(toIndentedString(esignRequestData)).append("\n");
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

