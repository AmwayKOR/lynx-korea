package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Address;
import com.amway.amwaydms.model.BankInput;
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





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class SimplePartyInput   {
  
  private PartyMasterInput partyMst = null;
  private PartyName name = null;
  private Address address = null;
  private Phone phone = null;
  private Ecomm ecomm = null;
  private Tax tax = null;
  private PersonalId personalId = null;
  private BankInput bank = null;

  
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
  public Address getAddress() {
    return address;
  }
  public void setAddress(Address address) {
    this.address = address;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phone")
  public Phone getPhone() {
    return phone;
  }
  public void setPhone(Phone phone) {
    this.phone = phone;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ecomm")
  public Ecomm getEcomm() {
    return ecomm;
  }
  public void setEcomm(Ecomm ecomm) {
    this.ecomm = ecomm;
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
   **/
  
  @ApiModelProperty(value = "")
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
    SimplePartyInput simplePartyInput = (SimplePartyInput) o;
    return Objects.equals(partyMst, simplePartyInput.partyMst) &&
        Objects.equals(name, simplePartyInput.name) &&
        Objects.equals(address, simplePartyInput.address) &&
        Objects.equals(phone, simplePartyInput.phone) &&
        Objects.equals(ecomm, simplePartyInput.ecomm) &&
        Objects.equals(tax, simplePartyInput.tax) &&
        Objects.equals(personalId, simplePartyInput.personalId) &&
        Objects.equals(bank, simplePartyInput.bank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyMst, name, address, phone, ecomm, tax, personalId, bank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimplePartyInput {\n");
    
    sb.append("    partyMst: ").append(toIndentedString(partyMst)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    ecomm: ").append(toIndentedString(ecomm)).append("\n");
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

