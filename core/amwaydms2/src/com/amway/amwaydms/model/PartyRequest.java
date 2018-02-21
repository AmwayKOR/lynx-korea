package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AddressInput;
import com.amway.amwaydms.model.Ecomm;
import com.amway.amwaydms.model.PartyMasterInput;
import com.amway.amwaydms.model.PartyName;
import com.amway.amwaydms.model.PersonalId;
import com.amway.amwaydms.model.Phone;
import com.amway.amwaydms.model.Tax;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyRequest   {
  
  private PartyMasterInput partyMst = null;
  private PartyName name = null;
  private List<Tax> taxList = new ArrayList<Tax>();
  private List<AddressInput> addressList = new ArrayList<AddressInput>();
  private List<Phone> phoneList = new ArrayList<Phone>();
  private List<Ecomm> ecommList = new ArrayList<Ecomm>();
  private PersonalId personalId = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyMst")
  public PartyMasterInput getPartyMst() {
    return partyMst;
  }
  public void setPartyMst(PartyMasterInput partyMst) {
    this.partyMst = partyMst;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("name")
  public PartyName getName() {
    return name;
  }
  public void setName(PartyName name) {
    this.name = name;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("taxList")
  public List<Tax> getTaxList() {
    return taxList;
  }
  public void setTaxList(List<Tax> taxList) {
    this.taxList = taxList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addressList")
  public List<AddressInput> getAddressList() {
    return addressList;
  }
  public void setAddressList(List<AddressInput> addressList) {
    this.addressList = addressList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneList")
  public List<Phone> getPhoneList() {
    return phoneList;
  }
  public void setPhoneList(List<Phone> phoneList) {
    this.phoneList = phoneList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ecommList")
  public List<Ecomm> getEcommList() {
    return ecommList;
  }
  public void setEcommList(List<Ecomm> ecommList) {
    this.ecommList = ecommList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("personalId")
  public PersonalId getPersonalId() {
    return personalId;
  }
  public void setPersonalId(PersonalId personalId) {
    this.personalId = personalId;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyRequest partyRequest = (PartyRequest) o;
    return Objects.equals(partyMst, partyRequest.partyMst) &&
        Objects.equals(name, partyRequest.name) &&
        Objects.equals(taxList, partyRequest.taxList) &&
        Objects.equals(addressList, partyRequest.addressList) &&
        Objects.equals(phoneList, partyRequest.phoneList) &&
        Objects.equals(ecommList, partyRequest.ecommList) &&
        Objects.equals(personalId, partyRequest.personalId);
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

