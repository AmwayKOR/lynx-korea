package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.CountryRegion;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class RegionResponse   {
  
  private String serverName = null;
  private ErrorMessage errorMessage = null;
  private List<CountryRegion> cntryRegionList = new ArrayList<CountryRegion>();

  
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
  @JsonProperty("cntryRegionList")
  public List<CountryRegion> getCntryRegionList() {
    return cntryRegionList;
  }
  public void setCntryRegionList(List<CountryRegion> cntryRegionList) {
    this.cntryRegionList = cntryRegionList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegionResponse regionResponse = (RegionResponse) o;
    return Objects.equals(serverName, regionResponse.serverName) &&
        Objects.equals(errorMessage, regionResponse.errorMessage) &&
        Objects.equals(cntryRegionList, regionResponse.cntryRegionList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, cntryRegionList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegionResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    cntryRegionList: ").append(toIndentedString(cntryRegionList)).append("\n");
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

