package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class ContractDetail   {
  
  private String contractType = null;
  private String languageCd = null;
  private String pdf = null;
  private Long seqNo = null;
  private String accountSubType = null;
  private Integer revNo = null;
  private String cntryCd = null;
  private Boolean dfltContractFlg = null;
  private Integer contractId = null;
  private String verbiage = null;
  private String sourceChannelId = null;

  
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
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("languageCd")
  public String getLanguageCd() {
    return languageCd;
  }
  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
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
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountSubType")
  public String getAccountSubType() {
    return accountSubType;
  }
  public void setAccountSubType(String accountSubType) {
    this.accountSubType = accountSubType;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("revNo")
  public Integer getRevNo() {
    return revNo;
  }
  public void setRevNo(Integer revNo) {
    this.revNo = revNo;
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
  @JsonProperty("dfltContractFlg")
  public Boolean getDfltContractFlg() {
    return dfltContractFlg;
  }
  public void setDfltContractFlg(Boolean dfltContractFlg) {
    this.dfltContractFlg = dfltContractFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("contractId")
  public Integer getContractId() {
    return contractId;
  }
  public void setContractId(Integer contractId) {
    this.contractId = contractId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("verbiage")
  public String getVerbiage() {
    return verbiage;
  }
  public void setVerbiage(String verbiage) {
    this.verbiage = verbiage;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContractDetail contractDetail = (ContractDetail) o;
    return Objects.equals(contractType, contractDetail.contractType) &&
        Objects.equals(languageCd, contractDetail.languageCd) &&
        Objects.equals(pdf, contractDetail.pdf) &&
        Objects.equals(seqNo, contractDetail.seqNo) &&
        Objects.equals(accountSubType, contractDetail.accountSubType) &&
        Objects.equals(revNo, contractDetail.revNo) &&
        Objects.equals(cntryCd, contractDetail.cntryCd) &&
        Objects.equals(dfltContractFlg, contractDetail.dfltContractFlg) &&
        Objects.equals(contractId, contractDetail.contractId) &&
        Objects.equals(verbiage, contractDetail.verbiage) &&
        Objects.equals(sourceChannelId, contractDetail.sourceChannelId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contractType, languageCd, pdf, seqNo, accountSubType, revNo, cntryCd, dfltContractFlg, contractId, verbiage, sourceChannelId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContractDetail {\n");
    
    sb.append("    contractType: ").append(toIndentedString(contractType)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    pdf: ").append(toIndentedString(pdf)).append("\n");
    sb.append("    seqNo: ").append(toIndentedString(seqNo)).append("\n");
    sb.append("    accountSubType: ").append(toIndentedString(accountSubType)).append("\n");
    sb.append("    revNo: ").append(toIndentedString(revNo)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    dfltContractFlg: ").append(toIndentedString(dfltContractFlg)).append("\n");
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    verbiage: ").append(toIndentedString(verbiage)).append("\n");
    sb.append("    sourceChannelId: ").append(toIndentedString(sourceChannelId)).append("\n");
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

