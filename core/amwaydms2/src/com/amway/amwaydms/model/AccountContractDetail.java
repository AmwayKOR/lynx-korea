package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountContractDetail   {
  
  private String contractId = null;
  private String contractName = null;
  private String contractType = null;
  private String effectiveStartDate = null;
  private String effectiveEndDate = null;
  private String acceptanceDate = null;
  private String contractSignDate = null;
  private String sourceChannelId = null;
  private String ipAddress = null;
  private String esign = null;
  private Long seqNo = null;
  private Long partyId = null;
  private String pdf = null;
  private String esignSourceChannelId = null;
  private String esignIpAddress = null;

  
  /**
   * Contract Id
   **/
  
  @ApiModelProperty(required = true, value = "Contract Id")
  @JsonProperty("contractId")
  public String getContractId() {
    return contractId;
  }
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }

  
  /**
   * Contract Name
   **/
  
  @ApiModelProperty(value = "Contract Name")
  @JsonProperty("contractName")
  public String getContractName() {
    return contractName;
  }
  public void setContractName(String contractName) {
    this.contractName = contractName;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=CNTID'>Reference to Contract Type Id</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=CNTID'>Reference to Contract Type Id</a>")
  @JsonProperty("contractType")
  public String getContractType() {
    return contractType;
  }
  public void setContractType(String contractType) {
    this.contractType = contractType;
  }

  
  /**
   * Contract effective start date
   **/
  
  @ApiModelProperty(value = "Contract effective start date")
  @JsonProperty("effectiveStartDate")
  public String getEffectiveStartDate() {
    return effectiveStartDate;
  }
  public void setEffectiveStartDate(String effectiveStartDate) {
    this.effectiveStartDate = effectiveStartDate;
  }

  
  /**
   * Contract effective end date
   **/
  
  @ApiModelProperty(value = "Contract effective end date")
  @JsonProperty("effectiveEndDate")
  public String getEffectiveEndDate() {
    return effectiveEndDate;
  }
  public void setEffectiveEndDate(String effectiveEndDate) {
    this.effectiveEndDate = effectiveEndDate;
  }

  
  /**
   * Contract accept date
   **/
  
  @ApiModelProperty(value = "Contract accept date")
  @JsonProperty("acceptanceDate")
  public String getAcceptanceDate() {
    return acceptanceDate;
  }
  public void setAcceptanceDate(String acceptanceDate) {
    this.acceptanceDate = acceptanceDate;
  }

  
  /**
   * Contract sign date
   **/
  
  @ApiModelProperty(required = true, value = "Contract sign date")
  @JsonProperty("contractSignDate")
  public String getContractSignDate() {
    return contractSignDate;
  }
  public void setContractSignDate(String contractSignDate) {
    this.contractSignDate = contractSignDate;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SRCHN'>Reference to Source channel Id</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SRCHN'>Reference to Source channel Id</a>")
  @JsonProperty("sourceChannelId")
  public String getSourceChannelId() {
    return sourceChannelId;
  }
  public void setSourceChannelId(String sourceChannelId) {
    this.sourceChannelId = sourceChannelId;
  }

  
  /**
   * Source IP address
   **/
  
  @ApiModelProperty(value = "Source IP address")
  @JsonProperty("ipAddress")
  public String getIpAddress() {
    return ipAddress;
  }
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
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
   * Party ID
   **/
  
  @ApiModelProperty(value = "Party ID")
  @JsonProperty("partyId")
  public Long getPartyId() {
    return partyId;
  }
  public void setPartyId(Long partyId) {
    this.partyId = partyId;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountContractDetail accountContractDetail = (AccountContractDetail) o;
    return Objects.equals(contractId, accountContractDetail.contractId) &&
        Objects.equals(contractName, accountContractDetail.contractName) &&
        Objects.equals(contractType, accountContractDetail.contractType) &&
        Objects.equals(effectiveStartDate, accountContractDetail.effectiveStartDate) &&
        Objects.equals(effectiveEndDate, accountContractDetail.effectiveEndDate) &&
        Objects.equals(acceptanceDate, accountContractDetail.acceptanceDate) &&
        Objects.equals(contractSignDate, accountContractDetail.contractSignDate) &&
        Objects.equals(sourceChannelId, accountContractDetail.sourceChannelId) &&
        Objects.equals(ipAddress, accountContractDetail.ipAddress) &&
        Objects.equals(esign, accountContractDetail.esign) &&
        Objects.equals(seqNo, accountContractDetail.seqNo) &&
        Objects.equals(partyId, accountContractDetail.partyId) &&
        Objects.equals(pdf, accountContractDetail.pdf) &&
        Objects.equals(esignSourceChannelId, accountContractDetail.esignSourceChannelId) &&
        Objects.equals(esignIpAddress, accountContractDetail.esignIpAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contractId, contractName, contractType, effectiveStartDate, effectiveEndDate, acceptanceDate, contractSignDate, sourceChannelId, ipAddress, esign, seqNo, partyId, pdf, esignSourceChannelId, esignIpAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountContractDetail {\n");
    
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    contractName: ").append(toIndentedString(contractName)).append("\n");
    sb.append("    contractType: ").append(toIndentedString(contractType)).append("\n");
    sb.append("    effectiveStartDate: ").append(toIndentedString(effectiveStartDate)).append("\n");
    sb.append("    effectiveEndDate: ").append(toIndentedString(effectiveEndDate)).append("\n");
    sb.append("    acceptanceDate: ").append(toIndentedString(acceptanceDate)).append("\n");
    sb.append("    contractSignDate: ").append(toIndentedString(contractSignDate)).append("\n");
    sb.append("    sourceChannelId: ").append(toIndentedString(sourceChannelId)).append("\n");
    sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
    sb.append("    esign: ").append(toIndentedString(esign)).append("\n");
    sb.append("    seqNo: ").append(toIndentedString(seqNo)).append("\n");
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    pdf: ").append(toIndentedString(pdf)).append("\n");
    sb.append("    esignSourceChannelId: ").append(toIndentedString(esignSourceChannelId)).append("\n");
    sb.append("    esignIpAddress: ").append(toIndentedString(esignIpAddress)).append("\n");
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

