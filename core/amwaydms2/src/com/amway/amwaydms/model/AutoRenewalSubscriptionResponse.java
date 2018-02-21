package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.BankAccount;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AutoRenewalSubscriptionResponse   {
  
  private String serverName = null;
  private ErrorMessage errorMessage = null;
  private Boolean signedUpFlg = null;
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
  @JsonProperty("signedUpFlg")
  public Boolean getSignedUpFlg() {
    return signedUpFlg;
  }
  public void setSignedUpFlg(Boolean signedUpFlg) {
    this.signedUpFlg = signedUpFlg;
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
    AutoRenewalSubscriptionResponse autoRenewalSubscriptionResponse = (AutoRenewalSubscriptionResponse) o;
    return Objects.equals(serverName, autoRenewalSubscriptionResponse.serverName) &&
        Objects.equals(errorMessage, autoRenewalSubscriptionResponse.errorMessage) &&
        Objects.equals(signedUpFlg, autoRenewalSubscriptionResponse.signedUpFlg) &&
        Objects.equals(bank, autoRenewalSubscriptionResponse.bank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, signedUpFlg, bank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AutoRenewalSubscriptionResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    signedUpFlg: ").append(toIndentedString(signedUpFlg)).append("\n");
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

