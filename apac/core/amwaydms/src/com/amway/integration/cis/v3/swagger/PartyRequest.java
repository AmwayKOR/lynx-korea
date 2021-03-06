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
 * PartyRequest
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class PartyRequest   {
  @SerializedName("partyMst")
  private PartyMasterInput partyMst = null;

  @SerializedName("name")
  private PartyName name = null;

  @SerializedName("taxList")
  private List<Tax> taxList = new ArrayList<Tax>();

  @SerializedName("addressList")
  private List<AddressInput> addressList = new ArrayList<AddressInput>();

  @SerializedName("phoneList")
  private List<Phone> phoneList = new ArrayList<Phone>();

  @SerializedName("ecommList")
  private List<Ecomm> ecommList = new ArrayList<Ecomm>();

  @SerializedName("personalId")
  private PersonalId personalId = null;

  public PartyRequest partyMst(PartyMasterInput partyMst) {
    this.partyMst = partyMst;
    return this;
  }

   /**
   * Get partyMst
   * @return partyMst
  **/
  @ApiModelProperty(example = "null", value = "")
  public PartyMasterInput getPartyMst() {
    return partyMst;
  }

  public void setPartyMst(PartyMasterInput partyMst) {
    this.partyMst = partyMst;
  }

  public PartyRequest name(PartyName name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "")
  public PartyName getName() {
    return name;
  }

  public void setName(PartyName name) {
    this.name = name;
  }

  public PartyRequest taxList(List<Tax> taxList) {
    this.taxList = taxList;
    return this;
  }

  public PartyRequest addTaxListItem(Tax taxListItem) {
    this.taxList.add(taxListItem);
    return this;
  }

   /**
   * Get taxList
   * @return taxList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<Tax> getTaxList() {
    return taxList;
  }

  public void setTaxList(List<Tax> taxList) {
    this.taxList = taxList;
  }

  public PartyRequest addressList(List<AddressInput> addressList) {
    this.addressList = addressList;
    return this;
  }

  public PartyRequest addAddressListItem(AddressInput addressListItem) {
    this.addressList.add(addressListItem);
    return this;
  }

   /**
   * Get addressList
   * @return addressList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<AddressInput> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<AddressInput> addressList) {
    this.addressList = addressList;
  }

  public PartyRequest phoneList(List<Phone> phoneList) {
    this.phoneList = phoneList;
    return this;
  }

  public PartyRequest addPhoneListItem(Phone phoneListItem) {
    this.phoneList.add(phoneListItem);
    return this;
  }

   /**
   * Get phoneList
   * @return phoneList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<Phone> getPhoneList() {
    return phoneList;
  }

  public void setPhoneList(List<Phone> phoneList) {
    this.phoneList = phoneList;
  }

  public PartyRequest ecommList(List<Ecomm> ecommList) {
    this.ecommList = ecommList;
    return this;
  }

  public PartyRequest addEcommListItem(Ecomm ecommListItem) {
    this.ecommList.add(ecommListItem);
    return this;
  }

   /**
   * Get ecommList
   * @return ecommList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<Ecomm> getEcommList() {
    return ecommList;
  }

  public void setEcommList(List<Ecomm> ecommList) {
    this.ecommList = ecommList;
  }

  public PartyRequest personalId(PersonalId personalId) {
    this.personalId = personalId;
    return this;
  }

   /**
   * Get personalId
   * @return personalId
  **/
  @ApiModelProperty(example = "null", value = "")
  public PersonalId getPersonalId() {
    return personalId;
  }

  public void setPersonalId(PersonalId personalId) {
    this.personalId = personalId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyRequest partyRequest = (PartyRequest) o;
    return Objects.equals(this.partyMst, partyRequest.partyMst) &&
        Objects.equals(this.name, partyRequest.name) &&
        Objects.equals(this.taxList, partyRequest.taxList) &&
        Objects.equals(this.addressList, partyRequest.addressList) &&
        Objects.equals(this.phoneList, partyRequest.phoneList) &&
        Objects.equals(this.ecommList, partyRequest.ecommList) &&
        Objects.equals(this.personalId, partyRequest.personalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyMst, name, taxList, addressList, phoneList, ecommList, personalId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyRequest {\n");
    
    sb.append("    partyMst: ").append(toIndentedString(partyMst)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    taxList: ").append(toIndentedString(taxList)).append("\n");
    sb.append("    addressList: ").append(toIndentedString(addressList)).append("\n");
    sb.append("    phoneList: ").append(toIndentedString(phoneList)).append("\n");
    sb.append("    ecommList: ").append(toIndentedString(ecommList)).append("\n");
    sb.append("    personalId: ").append(toIndentedString(personalId)).append("\n");
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

