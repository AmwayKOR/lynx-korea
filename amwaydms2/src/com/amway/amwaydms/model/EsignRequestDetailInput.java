package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class EsignRequestDetailInput   {
  
  private Long partyId = null;
  private String contractId = null;
  private Long seqNo = null;
  private String signDate = null;
  private String esignSourceChannelId = null;
  private String esignIpAddress = null;
  private String pdf = null;
  private String esign = null;

  
  /**
   * Party ID
   **/
  
  @ApiModelProperty(required = true, value = "Party ID")
  @JsonProperty("partyId")
  public Long getPartyId() {
    return partyId;
  }
  public void setPartyId(Long partyId) {
    this.partyId = partyId;
  }

  
  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("contractId")
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  
  /**
   * Sequence number
   **/
  
  @ApiModelProperty(required = true, value = "Sequence number")
  @JsonProperty("seqNo")
  public Long getSeqNo() {
    return seqNo;
  }
  public void setSeqNo(Long seqNo) {
    this.seqNo = seqNo;
  }

  
  /**
   * sign date
   **/
  
  @ApiModelProperty(required = true, value = "sign date")
  @JsonProperty("signDate")
  public String getSignDate() {
    return signDate;
  }
  public void setSignDate(String signDate) {
    this.signDate = signDate;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SRCHN'>Reference to E-sign Source channel Id</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SRCHN'>Reference to E-sign Source channel Id</a>")
  @JsonProperty("esignSourceChannelId")
  public String getEsignSourceChannelId() {
    return esignSourceChannelId;
  }
  public void setEsignSourceChannelId(String esignSourceChannelId) {
    this.esignSourceChannelId = esignSourceChannelId;
  }

  
  /**
   * E-sign Source IP address
   **/
  
  @ApiModelProperty(value = "E-sign Source IP address")
  @JsonProperty("esignIpAddress")
  public String getEsignIpAddress() {
    return esignIpAddress;
  }
  public void setEsignIpAddress(String esignIpAddress) {
    this.esignIpAddress = esignIpAddress;
  }

  
  /**
   * Contract PDF Link
   **/
  
  @ApiModelProperty(value = "Contract PDF Link")
  @JsonProperty("pdf")
  public String getPdf() {
    return pdf;
  }
  public void setPdf(String pdf) {
    this.pdf = pdf;
  }

  
  /**
   * E-Signature
   **/
  
  @ApiModelProperty(required = true, value = "E-Signature")
  @JsonProperty("esign")
  public String getEsign() {
    return esign;
  }
  public void setEsign(String esign) {
    this.esign = esign;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsignRequestDetailInput esignRequestDetailInput = (EsignRequestDetailInput) o;
    return Objects.equals(partyId, esignRequestDetailInput.partyId) &&
        Objects.equals(contractId, esignRequestDetailInput.contractId) &&
        Objects.equals(seqNo, esignRequestDetailInput.seqNo) &&
        Objects.equals(signDate, esignRequestDetailInput.signDate) &&
        Objects.equals(esignSourceChannelId, esignRequestDetailInput.esignSourceChannelId) &&
        Objects.equals(esignIpAddress, esignRequestDetailInput.esignIpAddress) &&
        Objects.equals(pdf, esignRequestDetailInput.pdf) &&
        Objects.equals(esign, esignRequestDetailInput.esign);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, contractId, seqNo, signDate, esignSourceChannelId, esignIpAddress, pdf, esign);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EsignRequestDetailInput {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    seqNo: ").append(toIndentedString(seqNo)).append("\n");
    sb.append("    signDate: ").append(toIndentedString(signDate)).append("\n");
    sb.append("    esignSourceChannelId: ").append(toIndentedString(esignSourceChannelId)).append("\n");
    sb.append("    esignIpAddress: ").append(toIndentedString(esignIpAddress)).append("\n");
    sb.append("    pdf: ").append(toIndentedString(pdf)).append("\n");
    sb.append("    esign: ").append(toIndentedString(esign)).append("\n");
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

