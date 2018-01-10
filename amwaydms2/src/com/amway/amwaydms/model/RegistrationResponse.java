package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Account;
import com.amway.amwaydms.model.ErrorMessage;
import com.amway.amwaydms.model.PartyGroupErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class RegistrationResponse   {
  
  private String serverName = null;
  private Account account = null;
  private PartyGroupErrorMessage partyGroupErrorDetail = null;
  private ErrorMessage errorMessage = null;

  
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
  @JsonProperty("account")
  public Account getAccount() {
    return account;
  }
  public void setAccount(Account account) {
    this.account = account;
  }

  
  /**
   * Errors group by the original Party Index. PartyIndex of Zero is used for the Account Level error
   **/
  
  @ApiModelProperty(value = "Errors group by the original Party Index. PartyIndex of Zero is used for the Account Level error")
  @JsonProperty("partyGroupErrorDetail")
  public PartyGroupErrorMessage getPartyGroupErrorDetail() {
    return partyGroupErrorDetail;
  }
  public void setPartyGroupErrorDetail(PartyGroupErrorMessage partyGroupErrorDetail) {
    this.partyGroupErrorDetail = partyGroupErrorDetail;
  }

  
  /**
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=REGISTRATION'>Reference to DMS error codes and meanings</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=REGISTRATION'>Reference to DMS error codes and meanings</a>")
  @JsonProperty("errorMessage")
  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }
  public void setErrorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationResponse registrationResponse = (RegistrationResponse) o;
    return Objects.equals(serverName, registrationResponse.serverName) &&
        Objects.equals(account, registrationResponse.account) &&
        Objects.equals(partyGroupErrorDetail, registrationResponse.partyGroupErrorDetail) &&
        Objects.equals(errorMessage, registrationResponse.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, account, partyGroupErrorDetail, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrationResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    partyGroupErrorDetail: ").append(toIndentedString(partyGroupErrorDetail)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
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

