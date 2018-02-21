package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountBalance   {
  
  private String balanceTypeCd = null;
  private String instrumentId = null;
  private String balanceAmount = null;
  private String currencyCd = null;
  private String txIsoCntryCd = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
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
   **/
  
  @ApiModelProperty(value = "")
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
    AccountBalance accountBalance = (AccountBalance) o;
    return Objects.equals(balanceTypeCd, accountBalance.balanceTypeCd) &&
        Objects.equals(instrumentId, accountBalance.instrumentId) &&
        Objects.equals(balanceAmount, accountBalance.balanceAmount) &&
        Objects.equals(currencyCd, accountBalance.currencyCd) &&
        Objects.equals(txIsoCntryCd, accountBalance.txIsoCntryCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(balanceTypeCd, instrumentId, balanceAmount, currencyCd, txIsoCntryCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountBalance {\n");
    
    sb.append("    balanceTypeCd: ").append(toIndentedString(balanceTypeCd)).append("\n");
    sb.append("    instrumentId: ").append(toIndentedString(instrumentId)).append("\n");
    sb.append("    balanceAmount: ").append(toIndentedString(balanceAmount)).append("\n");
    sb.append("    currencyCd: ").append(toIndentedString(currencyCd)).append("\n");
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

