/**
 * Magic DMS RESTful Services
 * No descripton provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v3
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.amway.integration.cis.v3.swagger;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;


/**
 * SearchParty
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class SearchParty   {
  @SerializedName("partyId")
  private String partyId = null;

  @SerializedName("givenName")
  private String givenName = null;

  @SerializedName("familyName")
  private String familyName = null;

  @SerializedName("middleName")
  private String middleName = null;

  @SerializedName("statusCd")
  private String statusCd = null;

  @SerializedName("addressList")
  private List<SearchAddress> addressList = new ArrayList<SearchAddress>();

  @SerializedName("ecommList")
  private List<SearchEcomm> ecommList = new ArrayList<SearchEcomm>();

  @SerializedName("phoneList")
  private List<SearchPhone> phoneList = new ArrayList<SearchPhone>();

  public SearchParty partyId(String partyId) {
    this.partyId = partyId;
    return this;
  }

   /**
   * Get partyId
   * @return partyId
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPartyId() {
    return partyId;
  }

  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  public SearchParty givenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

   /**
   * Get givenName
   * @return givenName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public SearchParty familyName(String familyName) {
    this.familyName = familyName;
    return this;
  }

   /**
   * Get familyName
   * @return familyName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public SearchParty middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

   /**
   * Get middleName
   * @return middleName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public SearchParty statusCd(String statusCd) {
    this.statusCd = statusCd;
    return this;
  }

   /**
   * Get statusCd
   * @return statusCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }

  public SearchParty addressList(List<SearchAddress> addressList) {
    this.addressList = addressList;
    return this;
  }

  public SearchParty addAddressListItem(SearchAddress addressListItem) {
    this.addressList.add(addressListItem);
    return this;
  }

   /**
   * Get addressList
   * @return addressList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<SearchAddress> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<SearchAddress> addressList) {
    this.addressList = addressList;
  }

  public SearchParty ecommList(List<SearchEcomm> ecommList) {
    this.ecommList = ecommList;
    return this;
  }

  public SearchParty addEcommListItem(SearchEcomm ecommListItem) {
    this.ecommList.add(ecommListItem);
    return this;
  }

   /**
   * Get ecommList
   * @return ecommList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<SearchEcomm> getEcommList() {
    return ecommList;
  }

  public void setEcommList(List<SearchEcomm> ecommList) {
    this.ecommList = ecommList;
  }

  public SearchParty phoneList(List<SearchPhone> phoneList) {
    this.phoneList = phoneList;
    return this;
  }

  public SearchParty addPhoneListItem(SearchPhone phoneListItem) {
    this.phoneList.add(phoneListItem);
    return this;
  }

   /**
   * Get phoneList
   * @return phoneList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<SearchPhone> getPhoneList() {
    return phoneList;
  }

  public void setPhoneList(List<SearchPhone> phoneList) {
    this.phoneList = phoneList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchParty searchParty = (SearchParty) o;
    return Objects.equals(this.partyId, searchParty.partyId) &&
        Objects.equals(this.givenName, searchParty.givenName) &&
        Objects.equals(this.familyName, searchParty.familyName) &&
        Objects.equals(this.middleName, searchParty.middleName) &&
        Objects.equals(this.statusCd, searchParty.statusCd) &&
        Objects.equals(this.addressList, searchParty.addressList) &&
        Objects.equals(this.ecommList, searchParty.ecommList) &&
        Objects.equals(this.phoneList, searchParty.phoneList);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

