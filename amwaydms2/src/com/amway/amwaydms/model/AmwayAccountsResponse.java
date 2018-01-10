package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AmwayAccount;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AmwayAccountsResponse   {
  
  private String serverName = null;
  private List<AmwayAccount> amwayAccountList = new ArrayList<AmwayAccount>();
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
  @JsonProperty("amwayAccountList")
  public List<AmwayAccount> getAmwayAccountList() {
    return amwayAccountList;
  }
  public void setAmwayAccountList(List<AmwayAccount> amwayAccountList) {
    this.amwayAccountList = amwayAccountList;
  }

  
  /**
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=account'>Reference to DMS error codes and meanings</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=account'>Reference to DMS error codes and meanings</a>")
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
    AmwayAccountsResponse amwayAccountsResponse = (AmwayAccountsResponse) o;
    return Objects.equals(serverName, amwayAccountsResponse.serverName) &&
        Objects.equals(amwayAccountList, amwayAccountsResponse.amwayAccountList) &&
        Objects.equals(errorMessage, amwayAccountsResponse.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, amwayAccountList, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AmwayAccountsResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    amwayAccountList: ").append(toIndentedString(amwayAccountList)).append("\n");
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

