package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Affiliate;
import com.amway.amwaydms.model.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AffiliateResponse   {
  
  private String serverName = null;
  private ErrorMessage errorMessage = null;
  private List<Affiliate> affiliateList = new ArrayList<Affiliate>();

  
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
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp'>Reference to DMS error codes and meanings</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp'>Reference to DMS error codes and meanings</a>")
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
  @JsonProperty("affiliateList")
  public List<Affiliate> getAffiliateList() {
    return affiliateList;
  }
  public void setAffiliateList(List<Affiliate> affiliateList) {
    this.affiliateList = affiliateList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffiliateResponse affiliateResponse = (AffiliateResponse) o;
    return Objects.equals(serverName, affiliateResponse.serverName) &&
        Objects.equals(errorMessage, affiliateResponse.errorMessage) &&
        Objects.equals(affiliateList, affiliateResponse.affiliateList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, affiliateList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffiliateResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    affiliateList: ").append(toIndentedString(affiliateList)).append("\n");
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

