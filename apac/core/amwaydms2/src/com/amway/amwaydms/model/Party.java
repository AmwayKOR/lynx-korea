package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Address;
import com.amway.amwaydms.model.BankAccount;
import com.amway.amwaydms.model.Ecomm;
import com.amway.amwaydms.model.MissingInfoDetail;
import com.amway.amwaydms.model.PartyMaster;
import com.amway.amwaydms.model.PartyName;
import com.amway.amwaydms.model.PersonalId;
import com.amway.amwaydms.model.Phone;
import com.amway.amwaydms.model.Preference;
import com.amway.amwaydms.model.Subscription;
import com.amway.amwaydms.model.Tax;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Party   {
  
  private PartyMaster partyMst = null;
  private List<PartyName> nameList = new ArrayList<PartyName>();
  private List<Address> addressList = new ArrayList<Address>();
  private List<Phone> phoneList = new ArrayList<Phone>();
  private List<Ecomm> ecommList = new ArrayList<Ecomm>();
  private List<Tax> taxList = new ArrayList<Tax>();
  private List<PersonalId> personalIdList = new ArrayList<PersonalId>();
  private List<Preference> preferenceList = new ArrayList<Preference>();
  private List<MissingInfoDetail> missingInfoList = new ArrayList<MissingInfoDetail>();
  private List<BankAccount> bankAccountDetailList = new ArrayList<BankAccount>();
  private List<Subscription> subscriptionList = new ArrayList<Subscription>();

  
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
  @JsonProperty("personalIdList")
  public List<PersonalId> getPersonalIdList() {
    return personalIdList;
  }
  public void setPersonalIdList(List<PersonalId> personalIdList) {
    this.personalIdList = personalIdList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("preferenceList")
  public List<Preference> getPreferenceList() {
    return preferenceList;
  }
  public void setPreferenceList(List<Preference> preferenceList) {
    this.preferenceList = preferenceList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("missingInfoList")
  public List<MissingInfoDetail> getMissingInfoList() {
    return missingInfoList;
  }
  public void setMissingInfoList(List<MissingInfoDetail> missingInfoList) {
    this.missingInfoList = missingInfoList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankAccountDetailList")
  public List<BankAccount> getBankAccountDetailList() {
    return bankAccountDetailList;
  }
  public void setBankAccountDetailList(List<BankAccount> bankAccountDetailList) {
    this.bankAccountDetailList = bankAccountDetailList;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("subscriptionList")
  public List<Subscription> getSubscriptionList() {
    return subscriptionList;
  }
  public void setSubscriptionList(List<Subscription> subscriptionList) {
    this.subscriptionList = subscriptionList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Party party = (Party) o;
    return Objects.equals(partyMst, party.partyMst) &&
        Objects.equals(nameList, party.nameList) &&
        Objects.equals(addressList, party.addressList) &&
        Objects.equals(phoneList, party.phoneList) &&
        Objects.equals(ecommList, party.ecommList) &&
        Objects.equals(taxList, party.taxList) &&
        Objects.equals(personalIdList, party.personalIdList) &&
        Objects.equals(preferenceList, party.preferenceList) &&
        Objects.equals(missingInfoList, party.missingInfoList) &&
        Objects.equals(bankAccountDetailList, party.bankAccountDetailList) &&
        Objects.equals(subscriptionList, party.subscriptionList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyMst, nameList, addressList, phoneList, ecommList, taxList, personalIdList, preferenceList, missingInfoList, bankAccountDetailList, subscriptionList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Party {\n");
    
    sb.append("    partyMst: ").append(toIndentedString(partyMst)).append("\n");
    sb.append("    nameList: ").append(toIndentedString(nameList)).append("\n");
    sb.append("    addressList: ").append(toIndentedString(addressList)).append("\n");
    sb.append("    phoneList: ").append(toIndentedString(phoneList)).append("\n");
    sb.append("    ecommList: ").append(toIndentedString(ecommList)).append("\n");
    sb.append("    taxList: ").append(toIndentedString(taxList)).append("\n");
    sb.append("    personalIdList: ").append(toIndentedString(personalIdList)).append("\n");
    sb.append("    preferenceList: ").append(toIndentedString(preferenceList)).append("\n");
    sb.append("    missingInfoList: ").append(toIndentedString(missingInfoList)).append("\n");
    sb.append("    bankAccountDetailList: ").append(toIndentedString(bankAccountDetailList)).append("\n");
    sb.append("    subscriptionList: ").append(toIndentedString(subscriptionList)).append("\n");
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

