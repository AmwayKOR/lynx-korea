package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.CountryStateCity;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class StateCityPostCodeResponse   {
  
  private String serverName = null;
  private ErrorMessage errorMessage = null;
  private List<CountryStateCity> stateCtyPostalcdList = new ArrayList<CountryStateCity>();

  
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
  @JsonProperty("stateCtyPostalcdList")
  public List<CountryStateCity> getStateCtyPostalcdList() {
    return stateCtyPostalcdList;
  }
  public void setStateCtyPostalcdList(List<CountryStateCity> stateCtyPostalcdList) {
    this.stateCtyPostalcdList = stateCtyPostalcdList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StateCityPostCodeResponse stateCityPostCodeResponse = (StateCityPostCodeResponse) o;
    return Objects.equals(serverName, stateCityPostCodeResponse.serverName) &&
        Objects.equals(errorMessage, stateCityPostCodeResponse.errorMessage) &&
        Objects.equals(stateCtyPostalcdList, stateCityPostCodeResponse.stateCtyPostalcdList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, stateCtyPostalcdList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StateCityPostCodeResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    stateCtyPostalcdList: ").append(toIndentedString(stateCtyPostalcdList)).append("\n");
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

