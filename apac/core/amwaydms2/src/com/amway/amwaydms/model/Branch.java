package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Branch   {
  
  private String branchId = null;
  private String branchName = null;
  private String salesPlanAff = null;
  private String actFlag = null;
  private String branchTypeCd = null;
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

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("branchId")
  public String getBranchId() {
    return branchId;
  }
  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("branchName")
  public String getBranchName() {
    return branchName;
  }
  public void setBranchName(String branchName) {
    this.branchName = branchName;
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
  @JsonProperty("branchTypeCd")
  public String getBranchTypeCd() {
    return branchTypeCd;
  }
  public void setBranchTypeCd(String branchTypeCd) {
    this.branchTypeCd = branchTypeCd;
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Branch branch = (Branch) o;
    return Objects.equals(branchId, branch.branchId) &&
        Objects.equals(branchName, branch.branchName) &&
        Objects.equals(salesPlanAff, branch.salesPlanAff) &&
        Objects.equals(actFlag, branch.actFlag) &&
        Objects.equals(branchTypeCd, branch.branchTypeCd) &&
        Objects.equals(addrStreet, branch.addrStreet) &&
        Objects.equals(addrLineTwo, branch.addrLineTwo) &&
        Objects.equals(addrLineThree, branch.addrLineThree) &&
        Objects.equals(addrLineFour, branch.addrLineFour) &&
        Objects.equals(cityName, branch.cityName) &&
        Objects.equals(postalCd, branch.postalCd) &&
        Objects.equals(stateCd, branch.stateCd) &&
        Objects.equals(phoneCntryCd, branch.phoneCntryCd) &&
        Objects.equals(phoneAreaCd, branch.phoneAreaCd) &&
        Objects.equals(phoneLocalNum, branch.phoneLocalNum) &&
        Objects.equals(phoneExtNum, branch.phoneExtNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(branchId, branchName, salesPlanAff, actFlag, branchTypeCd, addrStreet, addrLineTwo, addrLineThree, addrLineFour, cityName, postalCd, stateCd, phoneCntryCd, phoneAreaCd, phoneLocalNum, phoneExtNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Branch {\n");
    
    sb.append("    branchId: ").append(toIndentedString(branchId)).append("\n");
    sb.append("    branchName: ").append(toIndentedString(branchName)).append("\n");
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    actFlag: ").append(toIndentedString(actFlag)).append("\n");
    sb.append("    branchTypeCd: ").append(toIndentedString(branchTypeCd)).append("\n");
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

