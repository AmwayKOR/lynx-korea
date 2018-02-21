package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountBalanceRequest   {
  
  private String balanceTypeCd = null;
  private String instrumentId = null;
  private String currencyCd = null;
  private String balanceAmount = null;
  private String txSourceCd = null;
  private String sourcRefNum = null;
  private String txTypeCd = null;
  private String txIsoCntryCd = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BALCD'>Reference to Account Balance Type codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BALCD'>Reference to Account Balance Type codes</a>")
  @JsonProperty("balanceTypeCd")
  public String getBalanceTypeCd() {
    return balanceTypeCd;
  }
  public void setBalanceTypeCd(String balanceTypeCd) {
    this.balanceTypeCd = balanceTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("instrumentId")
  public String getInstrumentId() {
    return instrumentId;
  }
  public void setInstrumentId(String instrumentId) {
    this.instrumentId = instrumentId;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOCU'>Reference to Currency Codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ISOCU'>Reference to Currency Codes</a>")
  @JsonProperty("currencyCd")
  public String getCurrencyCd() {
    return currencyCd;
  }
  public void setCurrencyCd(String currencyCd) {
    this.currencyCd = currencyCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("balanceAmount")
  public String getBalanceAmount() {
    return balanceAmount;
  }
  public void setBalanceAmount(String balanceAmount) {
    this.balanceAmount = balanceAmount;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=TXSRC'>Reference to Transaction Source codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=TXSRC'>Reference to Transaction Source codes</a>")
  @JsonProperty("txSourceCd")
  public String getTxSourceCd() {
    return txSourceCd;
  }
  public void setTxSourceCd(String txSourceCd) {
    this.txSourceCd = txSourceCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sourcRefNum")
  public String getSourcRefNum() {
    return sourcRefNum;
  }
  public void setSourcRefNum(String sourcRefNum) {
    this.sourcRefNum = sourcRefNum;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=TXTCD'>Reference to Transaction Type codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=TXTCD'>Reference to Transaction Type codes</a>")
  @JsonProperty("txTypeCd")
  public String getTxTypeCd() {
    return txTypeCd;
  }
  public void setTxTypeCd(String txTypeCd) {
    this.txTypeCd = txTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("txIsoCntryCd")
  public String getTxIsoCntryCd() {
    return txIsoCntryCd;
  }
  public void setTxIsoCntryCd(String txIsoCntryCd) {
    this.txIsoCntryCd = txIsoCntryCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountBalanceRequest accountBalanceRequest = (AccountBalanceRequest) o;
    return Objects.equals(balanceTypeCd, accountBalanceRequest.balanceTypeCd) &&
        Objects.equals(instrumentId, accountBalanceRequest.instrumentId) &&
        Objects.equals(currencyCd, accountBalanceRequest.currencyCd) &&
        Objects.equals(balanceAmount, accountBalanceRequest.balanceAmount) &&
        Objects.equals(txSourceCd, accountBalanceRequest.txSourceCd) &&
        Objects.equals(sourcRefNum, accountBalanceRequest.sourcRefNum) &&
        Objects.equals(txTypeCd, accountBalanceRequest.txTypeCd) &&
        Objects.equals(txIsoCntryCd, accountBalanceRequest.txIsoCntryCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(balanceTypeCd, instrumentId, currencyCd, balanceAmount, txSourceCd, sourcRefNum, txTypeCd, txIsoCntryCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountBalanceRequest {\n");
    
    sb.append("    balanceTypeCd: ").append(toIndentedString(balanceTypeCd)).append("\n");
    sb.append("    instrumentId: ").append(toIndentedString(instrumentId)).append("\n");
    sb.append("    currencyCd: ").append(toIndentedString(currencyCd)).append("\n");
    sb.append("    balanceAmount: ").append(toIndentedString(balanceAmount)).append("\n");
    sb.append("    txSourceCd: ").append(toIndentedString(txSourceCd)).append("\n");
    sb.append("    sourcRefNum: ").append(toIndentedString(sourcRefNum)).append("\n");
    sb.append("    txTypeCd: ").append(toIndentedString(txTypeCd)).append("\n");
    sb.append("    txIsoCntryCd: ").append(toIndentedString(txIsoCntryCd)).append("\n");
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

