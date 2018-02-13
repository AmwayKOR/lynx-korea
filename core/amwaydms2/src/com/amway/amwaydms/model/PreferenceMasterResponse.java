package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.ErrorMessage;
import com.amway.amwaydms.model.PreferenceMaster;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PreferenceMasterResponse   {
  
  private String serverName = null;
  private ErrorMessage errorMessage = null;
  private List<PreferenceMaster> preferenceMaster = new ArrayList<PreferenceMaster>();

  
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
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=PREFERENCE'>Reference to DMS error codes and meanings</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=PREFERENCE'>Reference to DMS error codes and meanings</a>")
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
  @JsonProperty("preferenceMaster")
  public List<PreferenceMaster> getPreferenceMaster() {
    return preferenceMaster;
  }
  public void setPreferenceMaster(List<PreferenceMaster> preferenceMaster) {
    this.preferenceMaster = preferenceMaster;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PreferenceMasterResponse preferenceMasterResponse = (PreferenceMasterResponse) o;
    return Objects.equals(serverName, preferenceMasterResponse.serverName) &&
        Objects.equals(errorMessage, preferenceMasterResponse.errorMessage) &&
        Objects.equals(preferenceMaster, preferenceMasterResponse.preferenceMaster);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, preferenceMaster);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PreferenceMasterResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    preferenceMaster: ").append(toIndentedString(preferenceMaster)).append("\n");
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

