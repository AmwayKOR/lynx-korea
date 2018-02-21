package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AccountSearchPhoneInfoRequest;
import com.amway.amwaydms.model.AffAbo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class LosAccountSearchRequest   {
  
  private List<AffAbo> affAboList = new ArrayList<AffAbo>();
  private String lastName = null;
  private String emailAddress = null;
  private String postalCode = null;
  private String stateCd = null;
  private String city = null;
  private String firstName = null;
  private AccountSearchPhoneInfoRequest phoneInfo = null;

  
  /**
   * affAboList
   **/
  
  @ApiModelProperty(required = true, value = "affAboList")
  @JsonProperty("affAboList")
  public List<AffAbo> getAffAboList() {
    return affAboList;
  }
  public void setAffAboList(List<AffAbo> affAboList) {
    this.affAboList = affAboList;
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
   * State Code
   **/
  
  @ApiModelProperty(required = true, value = "State Code")
  @JsonProperty("stateCd")
  public String getStateCd() {
    return stateCd;
  }
  public void setStateCd(String stateCd) {
    this.stateCd = stateCd;
  }

  
  /**
   * City
   **/
  
  @ApiModelProperty(required = true, value = "City")
  @JsonProperty("city")
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  
  /**
   * First Name
   **/
  
  @ApiModelProperty(required = true, value = "First Name")
  @JsonProperty("firstName")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LosAccountSearchRequest losAccountSearchRequest = (LosAccountSearchRequest) o;
    return Objects.equals(affAboList, losAccountSearchRequest.affAboList) &&
        Objects.equals(lastName, losAccountSearchRequest.lastName) &&
        Objects.equals(emailAddress, losAccountSearchRequest.emailAddress) &&
        Objects.equals(postalCode, losAccountSearchRequest.postalCode) &&
        Objects.equals(stateCd, losAccountSearchRequest.stateCd) &&
        Objects.equals(city, losAccountSearchRequest.city) &&
        Objects.equals(firstName, losAccountSearchRequest.firstName) &&
        Objects.equals(phoneInfo, losAccountSearchRequest.phoneInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(affAboList, lastName, emailAddress, postalCode, stateCd, city, firstName, phoneInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LosAccountSearchRequest {\n");
    
    sb.append("    affAboList: ").append(toIndentedString(affAboList)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    phoneInfo: ").append(toIndentedString(phoneInfo)).append("\n");
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

