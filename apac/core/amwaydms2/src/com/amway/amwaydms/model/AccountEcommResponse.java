package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AccountEcommInfo;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountEcommResponse   {
  
  private String serverName = null;
  private List<AccountEcommInfo> accountEcommList = new ArrayList<AccountEcommInfo>();
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
  @JsonProperty("accountEcommList")
  public List<AccountEcommInfo> getAccountEcommList() {
    return accountEcommList;
  }
  public void setAccountEcommList(List<AccountEcommInfo> accountEcommList) {
    this.accountEcommList = accountEcommList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
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
    AccountEcommResponse accountEcommResponse = (AccountEcommResponse) o;
    return Objects.equals(serverName, accountEcommResponse.serverName) &&
        Objects.equals(accountEcommList, accountEcommResponse.accountEcommList) &&
        Objects.equals(errorMessage, accountEcommResponse.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, accountEcommList, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountEcommResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    accountEcommList: ").append(toIndentedString(accountEcommList)).append("\n");
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

