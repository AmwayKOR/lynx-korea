package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Address;
import com.amway.amwaydms.model.Ecomm;
import com.amway.amwaydms.model.PartyMaster;
import com.amway.amwaydms.model.PartyName;
import com.amway.amwaydms.model.Phone;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyContact   {
  
  private PartyMaster partyMst = null;
  private List<PartyName> nameList = new ArrayList<PartyName>();
  private List<Address> addressList = new ArrayList<Address>();
  private List<Phone> phoneList = new ArrayList<Phone>();
  private List<Ecomm> ecommList = new ArrayList<Ecomm>();

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyMst")
  public PartyMaster getPartyMst() {
    return partyMst;
  }
  public void setPartyMst(PartyMaster partyMst) {
    this.partyMst = partyMst;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("nameList")
  public List<PartyName> getNameList() {
    return nameList;
  }
  public void setNameList(List<PartyName> nameList) {
    this.nameList = nameList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addressList")
  public List<Address> getAddressList() {
    return addressList;
  }
  public void setAddressList(List<Address> addressList) {
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyContact partyContact = (PartyContact) o;
    return Objects.equals(partyMst, partyContact.partyMst) &&
        Objects.equals(nameList, partyContact.nameList) &&
        Objects.equals(addressList, partyContact.addressList) &&
        Objects.equals(phoneList, partyContact.phoneList) &&
        Objects.equals(ecommList, partyContact.ecommList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyMst, nameList, addressList, phoneList, ecommList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyContact {\n");
    
    sb.append("    partyMst: ").append(toIndentedString(partyMst)).append("\n");
    sb.append("    nameList: ").append(toIndentedString(nameList)).append("\n");
    sb.append("    addressList: ").append(toIndentedString(addressList)).append("\n");
    sb.append("    phoneList: ").append(toIndentedString(phoneList)).append("\n");
    sb.append("    ecommList: ").append(toIndentedString(ecommList)).append("\n");
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

