package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AccountHistory;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountHistoryResponse   {
  
  private String serverName = null;
  private ErrorMessage errorMessage = null;
  private List<AccountHistory> accountHistory = new ArrayList<AccountHistory>();

  
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
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=HISTORY'>Reference to DMS error codes and meanings</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=HISTORY'>Reference to DMS error codes and meanings</a>")
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
  @JsonProperty("accountHistory")
  public List<AccountHistory> getAccountHistory() {
    return accountHistory;
  }
  public void setAccountHistory(List<AccountHistory> accountHistory) {
    this.accountHistory = accountHistory;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountHistoryResponse accountHistoryResponse = (AccountHistoryResponse) o;
    return Objects.equals(serverName, accountHistoryResponse.serverName) &&
        Objects.equals(errorMessage, accountHistoryResponse.errorMessage) &&
        Objects.equals(accountHistory, accountHistoryResponse.accountHistory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, accountHistory);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountHistoryResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    accountHistory: ").append(toIndentedString(accountHistory)).append("\n");
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

