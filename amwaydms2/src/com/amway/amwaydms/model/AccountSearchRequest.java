package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AccountSearchPhoneInfoRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AccountSearchRequest   {
  
  private List<String> isoCntryCd = new ArrayList<String>();
  private String lastName = null;
  private String emailAddress = null;
  private String postalCode = null;
  private AccountSearchPhoneInfoRequest phoneInfo = null;
  private String pageSize = null;
  private String requestingPage = null;

  
  /**
   * <a href='http://ed/Domains/Details/16'>Reference to ISO Country Codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='http://ed/Domains/Details/16'>Reference to ISO Country Codes</a>")
  @JsonProperty("isoCntryCd")
  public List<String> getIsoCntryCd() {
    return isoCntryCd;
  }
  public void setIsoCntryCd(List<String> isoCntryCd) {
    this.isoCntryCd = isoCntryCd;
  }

  
  /**
   * Family Name
   **/
  
  @ApiModelProperty(required = true, value = "Family Name")
  @JsonProperty("lastName")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  
  /**
   * Email Address
   **/
  
  @ApiModelProperty(required = true, value = "Email Address")
  @JsonProperty("emailAddress")
  public String getEmailAddress() {
    return emailAddress;
  }
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  
  /**
   * Postal Code
   **/
  
  @ApiModelProperty(required = true, value = "Postal Code")
  @JsonProperty("postalCode")
  public String getPostalCode() {
    return postalCode;
  }
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  
  /**
   * Phone Information
   **/
  
  @ApiModelProperty(required = true, value = "Phone Information")
  @JsonProperty("phoneInfo")
  public AccountSearchPhoneInfoRequest getPhoneInfo() {
    return phoneInfo;
  }
  public void setPhoneInfo(AccountSearchPhoneInfoRequest phoneInfo) {
    this.phoneInfo = phoneInfo;
  }

  
  /**
   * Page Size
   **/
  
  @ApiModelProperty(required = true, value = "Page Size")
  @JsonProperty("pageSize")
  public String getPageSize() {
    return pageSize;
  }
  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  
  /**
   * Requesting Page
   **/
  
  @ApiModelProperty(required = true, value = "Requesting Page")
  @JsonProperty("requestingPage")
  public String getRequestingPage() {
    return requestingPage;
  }
  public void setRequestingPage(String requestingPage) {
    this.requestingPage = requestingPage;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountSearchRequest accountSearchRequest = (AccountSearchRequest) o;
    return Objects.equals(isoCntryCd, accountSearchRequest.isoCntryCd) &&
        Objects.equals(lastName, accountSearchRequest.lastName) &&
        Objects.equals(emailAddress, accountSearchRequest.emailAddress) &&
        Objects.equals(postalCode, accountSearchRequest.postalCode) &&
        Objects.equals(phoneInfo, accountSearchRequest.phoneInfo) &&
        Objects.equals(pageSize, accountSearchRequest.pageSize) &&
        Objects.equals(requestingPage, accountSearchRequest.requestingPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isoCntryCd, lastName, emailAddress, postalCode, phoneInfo, pageSize, requestingPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountSearchRequest {\n");
    
    sb.append("    isoCntryCd: ").append(toIndentedString(isoCntryCd)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
    sb.append("    phoneInfo: ").append(toIndentedString(phoneInfo)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    requestingPage: ").append(toIndentedString(requestingPage)).append("\n");
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

