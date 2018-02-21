package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.CountryTerritory;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class TerritoryResponse   {
  
  private String serverName = null;
  private ErrorMessage errorMessage = null;
  private List<CountryTerritory> cntryTerritryList = new ArrayList<CountryTerritory>();

  
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
  @JsonProperty("cntryTerritryList")
  public List<CountryTerritory> getCntryTerritryList() {
    return cntryTerritryList;
  }
  public void setCntryTerritryList(List<CountryTerritory> cntryTerritryList) {
    this.cntryTerritryList = cntryTerritryList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerritoryResponse territoryResponse = (TerritoryResponse) o;
    return Objects.equals(serverName, territoryResponse.serverName) &&
        Objects.equals(errorMessage, territoryResponse.errorMessage) &&
        Objects.equals(cntryTerritryList, territoryResponse.cntryTerritryList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, cntryTerritryList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerritoryResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    cntryTerritryList: ").append(toIndentedString(cntryTerritryList)).append("\n");
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

