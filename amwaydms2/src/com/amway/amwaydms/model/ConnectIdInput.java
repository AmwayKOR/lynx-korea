package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class ConnectIdInput   {
  
  private String websiteLanguageCd = null;
  private String clientApp = null;
  private String redirectUri = null;

  
  /**
   * Website display language code in language tag format ex. en-US, en-CA, pt-BR (ISO Language code-ISO country code)
   **/
  
  @ApiModelProperty(required = true, value = "Website display language code in language tag format ex. en-US, en-CA, pt-BR (ISO Language code-ISO country code)")
  @JsonProperty("websiteLanguageCd")
  public String getWebsiteLanguageCd() {
    return websiteLanguageCd;
  }
  public void setWebsiteLanguageCd(String websiteLanguageCd) {
    this.websiteLanguageCd = websiteLanguageCd;
  }

  
  /**
   * Requesting client app name for Amway login system to create login user
   **/
  
  @ApiModelProperty(required = true, value = "Requesting client app name for Amway login system to create login user")
  @JsonProperty("clientApp")
  public String getClientApp() {
    return clientApp;
  }
  public void setClientApp(String clientApp) {
    this.clientApp = clientApp;
  }

  
  /**
   * Target website URL for Amway login system to give access for the login user
   **/
  
  @ApiModelProperty(required = true, value = "Target website URL for Amway login system to give access for the login user")
  @JsonProperty("redirectUri")
  public String getRedirectUri() {
    return redirectUri;
  }
  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectIdInput connectIdInput = (ConnectIdInput) o;
    return Objects.equals(websiteLanguageCd, connectIdInput.websiteLanguageCd) &&
        Objects.equals(clientApp, connectIdInput.clientApp) &&
        Objects.equals(redirectUri, connectIdInput.redirectUri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(websiteLanguageCd, clientApp, redirectUri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectIdInput {\n");
    
    sb.append("    websiteLanguageCd: ").append(toIndentedString(websiteLanguageCd)).append("\n");
    sb.append("    clientApp: ").append(toIndentedString(clientApp)).append("\n");
    sb.append("    redirectUri: ").append(toIndentedString(redirectUri)).append("\n");
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

