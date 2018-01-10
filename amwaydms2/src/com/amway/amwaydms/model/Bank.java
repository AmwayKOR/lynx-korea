package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.Branch;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Bank   {
  
  private String bankId = null;
  private String bankName = null;
  private String bankTypeCd = null;
  private String salesPlanAff = null;
  private String amwayCntryCd = null;
  private String actFlag = null;
  private String addrStreet = null;
  private String addrLineTwo = null;
  private String addrLineThree = null;
  private String addrLineFour = null;
  private String cityName = null;
  private String postalCd = null;
  private String stateCd = null;
  private String phoneCntryCd = null;
  private String phoneAreaCd = null;
  private String phoneLocalNum = null;
  private String phoneExtNum = null;
  private List<Branch> branchList = new ArrayList<Branch>();

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankId")
  public String getBankId() {
    return bankId;
  }
  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankName")
  public String getBankName() {
    return bankName;
  }
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bankTypeCd")
  public String getBankTypeCd() {
    return bankTypeCd;
  }
  public void setBankTypeCd(String bankTypeCd) {
    this.bankTypeCd = bankTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanAff")
  public String getSalesPlanAff() {
    return salesPlanAff;
  }
  public void setSalesPlanAff(String salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("amwayCntryCd")
  public String getAmwayCntryCd() {
    return amwayCntryCd;
  }
  public void setAmwayCntryCd(String amwayCntryCd) {
    this.amwayCntryCd = amwayCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("actFlag")
  public String getActFlag() {
    return actFlag;
  }
  public void setActFlag(String actFlag) {
    this.actFlag = actFlag;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addrStreet")
  public String getAddrStreet() {
    return addrStreet;
  }
  public void setAddrStreet(String addrStreet) {
    this.addrStreet = addrStreet;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addrLineTwo")
  public String getAddrLineTwo() {
    return addrLineTwo;
  }
  public void setAddrLineTwo(String addrLineTwo) {
    this.addrLineTwo = addrLineTwo;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addrLineThree")
  public String getAddrLineThree() {
    return addrLineThree;
  }
  public void setAddrLineThree(String addrLineThree) {
    this.addrLineThree = addrLineThree;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("addrLineFour")
  public String getAddrLineFour() {
    return addrLineFour;
  }
  public void setAddrLineFour(String addrLineFour) {
    this.addrLineFour = addrLineFour;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cityName")
  public String getCityName() {
    return cityName;
  }
  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("postalCd")
  public String getPostalCd() {
    return postalCd;
  }
  public void setPostalCd(String postalCd) {
    this.postalCd = postalCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("stateCd")
  public String getStateCd() {
    return stateCd;
  }
  public void setStateCd(String stateCd) {
    this.stateCd = stateCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneCntryCd")
  public String getPhoneCntryCd() {
    return phoneCntryCd;
  }
  public void setPhoneCntryCd(String phoneCntryCd) {
    this.phoneCntryCd = phoneCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneAreaCd")
  public String getPhoneAreaCd() {
    return phoneAreaCd;
  }
  public void setPhoneAreaCd(String phoneAreaCd) {
    this.phoneAreaCd = phoneAreaCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneLocalNum")
  public String getPhoneLocalNum() {
    return phoneLocalNum;
  }
  public void setPhoneLocalNum(String phoneLocalNum) {
    this.phoneLocalNum = phoneLocalNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("phoneExtNum")
  public String getPhoneExtNum() {
    return phoneExtNum;
  }
  public void setPhoneExtNum(String phoneExtNum) {
    this.phoneExtNum = phoneExtNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("branchList")
  public List<Branch> getBranchList() {
    return branchList;
  }
  public void setBranchList(List<Branch> branchList) {
    this.branchList = branchList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bank bank = (Bank) o;
    return Objects.equals(bankId, bank.bankId) &&
        Objects.equals(bankName, bank.bankName) &&
        Objects.equals(bankTypeCd, bank.bankTypeCd) &&
        Objects.equals(salesPlanAff, bank.salesPlanAff) &&
        Objects.equals(amwayCntryCd, bank.amwayCntryCd) &&
        Objects.equals(actFlag, bank.actFlag) &&
        Objects.equals(addrStreet, bank.addrStreet) &&
        Objects.equals(addrLineTwo, bank.addrLineTwo) &&
        Objects.equals(addrLineThree, bank.addrLineThree) &&
        Objects.equals(addrLineFour, bank.addrLineFour) &&
        Objects.equals(cityName, bank.cityName) &&
        Objects.equals(postalCd, bank.postalCd) &&
        Objects.equals(stateCd, bank.stateCd) &&
        Objects.equals(phoneCntryCd, bank.phoneCntryCd) &&
        Objects.equals(phoneAreaCd, bank.phoneAreaCd) &&
        Objects.equals(phoneLocalNum, bank.phoneLocalNum) &&
        Objects.equals(phoneExtNum, bank.phoneExtNum) &&
        Objects.equals(branchList, bank.branchList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bankId, bankName, bankTypeCd, salesPlanAff, amwayCntryCd, actFlag, addrStreet, addrLineTwo, addrLineThree, addrLineFour, cityName, postalCd, stateCd, phoneCntryCd, phoneAreaCd, phoneLocalNum, phoneExtNum, branchList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Bank {\n");
    
    sb.append("    bankId: ").append(toIndentedString(bankId)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    bankTypeCd: ").append(toIndentedString(bankTypeCd)).append("\n");
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    amwayCntryCd: ").append(toIndentedString(amwayCntryCd)).append("\n");
    sb.append("    actFlag: ").append(toIndentedString(actFlag)).append("\n");
    sb.append("    addrStreet: ").append(toIndentedString(addrStreet)).append("\n");
    sb.append("    addrLineTwo: ").append(toIndentedString(addrLineTwo)).append("\n");
    sb.append("    addrLineThree: ").append(toIndentedString(addrLineThree)).append("\n");
    sb.append("    addrLineFour: ").append(toIndentedString(addrLineFour)).append("\n");
    sb.append("    cityName: ").append(toIndentedString(cityName)).append("\n");
    sb.append("    postalCd: ").append(toIndentedString(postalCd)).append("\n");
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    phoneCntryCd: ").append(toIndentedString(phoneCntryCd)).append("\n");
    sb.append("    phoneAreaCd: ").append(toIndentedString(phoneAreaCd)).append("\n");
    sb.append("    phoneLocalNum: ").append(toIndentedString(phoneLocalNum)).append("\n");
    sb.append("    phoneExtNum: ").append(toIndentedString(phoneExtNum)).append("\n");
    sb.append("    branchList: ").append(toIndentedString(branchList)).append("\n");
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

