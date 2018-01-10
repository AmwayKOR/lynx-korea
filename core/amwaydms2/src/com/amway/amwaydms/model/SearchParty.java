package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.SearchAddress;
import com.amway.amwaydms.model.SearchEcomm;
import com.amway.amwaydms.model.SearchPhone;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SearchParty   {
  
  private String partyId = null;
  private String givenName = null;
  private String familyName = null;
  private String middleName = null;
  private String statusCd = null;
  private List<SearchAddress> addressList = new ArrayList<SearchAddress>();
  private List<SearchEcomm> ecommList = new ArrayList<SearchEcomm>();
  private List<SearchPhone> phoneList = new ArrayList<SearchPhone>();

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyId")
  public String getPartyId() {
    return partyId;
  }
  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("givenName")
  public String getGivenName() {
    return givenName;
  }
  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("familyName")
  public String getFamilyName() {
    return familyName;
  }
  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("middleName")
  public String getMiddleName() {
    return middleName;
  }
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("statusCd")
  public String getStatusCd() {
    return statusCd;
  }
  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addressList")
  public List<SearchAddress> getAddressList() {
    return addressList;
  }
  public void setAddressList(List<SearchAddress> addressList) {
    this.addressList = addressList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ecommList")
  public List<SearchEcomm> getEcommList() {
    return ecommList;
  }
  public void setEcommList(List<SearchEcomm> ecommList) {
    this.ecommList = ecommList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneList")
  public List<SearchPhone> getPhoneList() {
    return phoneList;
  }
  public void setPhoneList(List<SearchPhone> phoneList) {
    this.phoneList = phoneList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchParty searchParty = (SearchParty) o;
    return Objects.equals(partyId, searchParty.partyId) &&
        Objects.equals(givenName, searchParty.givenName) &&
        Objects.equals(familyName, searchParty.familyName) &&
        Objects.equals(middleName, searchParty.middleName) &&
        Objects.equals(statusCd, searchParty.statusCd) &&
        Objects.equals(addressList, searchParty.addressList) &&
        Objects.equals(ecommList, searchParty.ecommList) &&
        Objects.equals(phoneList, searchParty.phoneList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, givenName, familyName, middleName, statusCd, addressList, ecommList, phoneList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchParty {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    givenName: ").append(toIndentedString(givenName)).append("\n");
    sb.append("    familyName: ").append(toIndentedString(familyName)).append("\n");
    sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
    sb.append("    addressList: ").append(toIndentedString(addressList)).append("\n");
    sb.append("    ecommList: ").append(toIndentedString(ecommList)).append("\n");
    sb.append("    phoneList: ").append(toIndentedString(phoneList)).append("\n");
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

