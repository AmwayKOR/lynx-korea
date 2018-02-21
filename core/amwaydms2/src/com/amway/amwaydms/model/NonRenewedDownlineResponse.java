package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class NonRenewedDownlineResponse   {
  
  private String serverName = null;
  private Boolean hasNotRenewDownline = null;
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
  @JsonProperty("hasNotRenewDownline")
  public Boolean getHasNotRenewDownline() {
    return hasNotRenewDownline;
  }
  public void setHasNotRenewDownline(Boolean hasNotRenewDownline) {
    this.hasNotRenewDownline = hasNotRenewDownline;
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
    NonRenewedDownlineResponse nonRenewedDownlineResponse = (NonRenewedDownlineResponse) o;
    return Objects.equals(serverName, nonRenewedDownlineResponse.serverName) &&
        Objects.equals(hasNotRenewDownline, nonRenewedDownlineResponse.hasNotRenewDownline) &&
        Objects.equals(errorMessage, nonRenewedDownlineResponse.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, hasNotRenewDownline, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NonRenewedDownlineResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    hasNotRenewDownline: ").append(toIndentedString(hasNotRenewDownline)).append("\n");
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

