package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AddressInput;
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
public class ProspectRequest   {
  
  private String cntryCd = null;
  private String aboNum = null;
  private PartyMasterInput partyMst = null;
  private List<PartyName> personNameList = new ArrayList<PartyName>();
  private List<AddressInput> addressList = new ArrayList<AddressInput>();
  private List<PhoneInput> phoneList = new ArrayList<PhoneInput>();
  private List<EcommInput> ecommList = new ArrayList<EcommInput>();
  private Tax tax = null;
  private PersonalId personalId = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("aboNum")
  public String getAboNum() {
    return aboNum;
  }
  public void setAboNum(String aboNum) {
    this.aboNum = aboNum;
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
  @JsonProperty("personNameList")
  public List<PartyName> getPersonNameList() {
    return personNameList;
  }
  public void setPersonNameList(List<PartyName> personNameList) {
    this.personNameList = personNameList;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProspectRequest prospectRequest = (ProspectRequest) o;
    return Objects.equals(cntryCd, prospectRequest.cntryCd) &&
        Objects.equals(aboNum, prospectRequest.aboNum) &&
        Objects.equals(partyMst, prospectRequest.partyMst) &&
        Objects.equals(personNameList, prospectRequest.personNameList) &&
        Objects.equals(addressList, prospectRequest.addressList) &&
        Objects.equals(phoneList, prospectRequest.phoneList) &&
        Objects.equals(ecommList, prospectRequest.ecommList) &&
        Objects.equals(tax, prospectRequest.tax) &&
        Objects.equals(personalId, prospectRequest.personalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cntryCd, aboNum, partyMst, personNameList, addressList, phoneList, ecommList, tax, personalId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProspectRequest {\n");
    
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    partyMst: ").append(toIndentedString(partyMst)).append("\n");
    sb.append("    personNameList: ").append(toIndentedString(personNameList)).append("\n");
    sb.append("    addressList: ").append(toIndentedString(addressList)).append("\n");
    sb.append("    phoneList: ").append(toIndentedString(phoneList)).append("\n");
    sb.append("    ecommList: ").append(toIndentedString(ecommList)).append("\n");
    sb.append("    tax: ").append(toIndentedString(tax)).append("\n");
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

