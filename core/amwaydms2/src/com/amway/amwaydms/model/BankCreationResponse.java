package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.BankAccount;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BankCreationResponse   {
  
  private String serverName = null;
  private String bankAcctId = null;
  private ErrorMessage errorMessage = null;
  private BankAccount bank = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("serverName")
  public String getServerName() {
    return serverName;
  }
  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankAcctId")
  public String getBankAcctId() {
    return bankAcctId;
  }
  public void setBankAcctId(String bankAcctId) {
    this.bankAcctId = bankAcctId;
  }

  
  /**
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=BANK'>Reference to DMS error codes and meanings</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=BANK'>Reference to DMS error codes and meanings</a>")
  @JsonProperty("errorMessage")
  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }
  public void setErrorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bank")
  public BankAccount getBank() {
    return bank;
  }
  public void setBank(BankAccount bank) {
    this.bank = bank;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankCreationResponse bankCreationResponse = (BankCreationResponse) o;
    return Objects.equals(serverName, bankCreationResponse.serverName) &&
        Objects.equals(bankAcctId, bankCreationResponse.bankAcctId) &&
        Objects.equals(errorMessage, bankCreationResponse.errorMessage) &&
        Objects.equals(bank, bankCreationResponse.bank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, bankAcctId, errorMessage, bank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankCreationResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    bankAcctId: ").append(toIndentedString(bankAcctId)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
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

