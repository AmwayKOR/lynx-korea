package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AddressInput;
import com.amway.amwaydms.model.BankInput;
import com.amway.amwaydms.model.EcommInput;
import com.amway.amwaydms.model.PartyMasterInput;
import com.amway.amwaydms.model.PartyName;
import com.amway.amwaydms.model.PersonalId;
import com.amway.amwaydms.model.PhoneInput;
import com.amway.amwaydms.model.Tax;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyInput   {
  
  private Integer partyIndex = null;
  private PartyMasterInput partyMst = null;
  private PartyName name = null;
  private AddressInput address = null;
  private List<PhoneInput> phoneList = new ArrayList<PhoneInput>();
  private List<EcommInput> ecommList = new ArrayList<EcommInput>();
  private Tax tax = null;
  private PersonalId personalId = null;
  private BankInput bank = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyIndex")
  public Integer getPartyIndex() {
    return partyIndex;
  }
  public void setPartyIndex(Integer partyIndex) {
    this.partyIndex = partyIndex;
  }

  
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
  @JsonProperty("address")
  public AddressInput getAddress() {
    return address;
  }
  public void setAddress(AddressInput address) {
    this.address = address;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneList")
  public List<PhoneInput> getPhoneList() {
    return phoneList;
  }
  public void setPhoneList(List<PhoneInput> phoneList) {
    this.phoneList = phoneList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ecommList")
  public List<EcommInput> getEcommList() {
    return ecommList;
  }
  public void setEcommList(List<EcommInput> ecommList) {
    this.ecommList = ecommList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("tax")
  public Tax getTax() {
    return tax;
  }
  public void setTax(Tax tax) {
    this.tax = tax;
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

  
  /**
   * BankAccount
   **/
  
  @ApiModelProperty(value = "BankAccount")
  @JsonProperty("bank")
  public BankInput getBank() {
    return bank;
  }
  public void setBank(BankInput bank) {
    this.bank = bank;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyInput partyInput = (PartyInput) o;
    return Objects.equals(partyIndex, partyInput.partyIndex) &&
        Objects.equals(partyMst, partyInput.partyMst) &&
        Objects.equals(name, partyInput.name) &&
        Objects.equals(address, partyInput.address) &&
        Objects.equals(phoneList, partyInput.phoneList) &&
        Objects.equals(ecommList, partyInput.ecommList) &&
        Objects.equals(tax, partyInput.tax) &&
        Objects.equals(personalId, partyInput.personalId) &&
        Objects.equals(bank, partyInput.bank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyIndex, partyMst, name, address, phoneList, ecommList, tax, personalId, bank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyInput {\n");
    
    sb.append("    partyIndex: ").append(toIndentedString(partyIndex)).append("\n");
    sb.append("    partyMst: ").append(toIndentedString(partyMst)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phoneList: ").append(toIndentedString(phoneList)).append("\n");
    sb.append("    ecommList: ").append(toIndentedString(ecommList)).append("\n");
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
    sb.append("    personalId: ").append(toIndentedString(personalId)).append("\n");
    sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
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

