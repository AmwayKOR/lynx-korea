package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class RegistrationSponsor   {
  
  private String acctSponsorAffCd = null;
  private String acctSponsorAboNum = null;
  private String acctIntlSponsorAboNum = null;
  private String acctIntlSponsorCntryCd = null;
  private Boolean intlPrimaryBusFlg = null;
  private Boolean sponsorListFlag = null;
  private Boolean multiBusValidationOverRideFlg = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("acctSponsorAffCd")
  public String getAcctSponsorAffCd() {
    return acctSponsorAffCd;
  }
  public void setAcctSponsorAffCd(String acctSponsorAffCd) {
    this.acctSponsorAffCd = acctSponsorAffCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("acctSponsorAboNum")
  public String getAcctSponsorAboNum() {
    return acctSponsorAboNum;
  }
  public void setAcctSponsorAboNum(String acctSponsorAboNum) {
    this.acctSponsorAboNum = acctSponsorAboNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("acctIntlSponsorAboNum")
  public String getAcctIntlSponsorAboNum() {
    return acctIntlSponsorAboNum;
  }
  public void setAcctIntlSponsorAboNum(String acctIntlSponsorAboNum) {
    this.acctIntlSponsorAboNum = acctIntlSponsorAboNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("acctIntlSponsorCntryCd")
  public String getAcctIntlSponsorCntryCd() {
    return acctIntlSponsorCntryCd;
  }
  public void setAcctIntlSponsorCntryCd(String acctIntlSponsorCntryCd) {
    this.acctIntlSponsorCntryCd = acctIntlSponsorCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("intlPrimaryBusFlg")
  public Boolean getIntlPrimaryBusFlg() {
    return intlPrimaryBusFlg;
  }
  public void setIntlPrimaryBusFlg(Boolean intlPrimaryBusFlg) {
    this.intlPrimaryBusFlg = intlPrimaryBusFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sponsorListFlag")
  public Boolean getSponsorListFlag() {
    return sponsorListFlag;
  }
  public void setSponsorListFlag(Boolean sponsorListFlag) {
    this.sponsorListFlag = sponsorListFlag;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("multiBusValidationOverRideFlg")
  public Boolean getMultiBusValidationOverRideFlg() {
    return multiBusValidationOverRideFlg;
  }
  public void setMultiBusValidationOverRideFlg(Boolean multiBusValidationOverRideFlg) {
    this.multiBusValidationOverRideFlg = multiBusValidationOverRideFlg;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationSponsor registrationSponsor = (RegistrationSponsor) o;
    return Objects.equals(acctSponsorAffCd, registrationSponsor.acctSponsorAffCd) &&
        Objects.equals(acctSponsorAboNum, registrationSponsor.acctSponsorAboNum) &&
        Objects.equals(acctIntlSponsorAboNum, registrationSponsor.acctIntlSponsorAboNum) &&
        Objects.equals(acctIntlSponsorCntryCd, registrationSponsor.acctIntlSponsorCntryCd) &&
        Objects.equals(intlPrimaryBusFlg, registrationSponsor.intlPrimaryBusFlg) &&
        Objects.equals(sponsorListFlag, registrationSponsor.sponsorListFlag) &&
        Objects.equals(multiBusValidationOverRideFlg, registrationSponsor.multiBusValidationOverRideFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acctSponsorAffCd, acctSponsorAboNum, acctIntlSponsorAboNum, acctIntlSponsorCntryCd, intlPrimaryBusFlg, sponsorListFlag, multiBusValidationOverRideFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrationSponsor {\n");
    
    sb.append("    acctSponsorAffCd: ").append(toIndentedString(acctSponsorAffCd)).append("\n");
    sb.append("    acctSponsorAboNum: ").append(toIndentedString(acctSponsorAboNum)).append("\n");
    sb.append("    acctIntlSponsorAboNum: ").append(toIndentedString(acctIntlSponsorAboNum)).append("\n");
    sb.append("    acctIntlSponsorCntryCd: ").append(toIndentedString(acctIntlSponsorCntryCd)).append("\n");
    sb.append("    intlPrimaryBusFlg: ").append(toIndentedString(intlPrimaryBusFlg)).append("\n");
    sb.append("    sponsorListFlag: ").append(toIndentedString(sponsorListFlag)).append("\n");
    sb.append("    multiBusValidationOverRideFlg: ").append(toIndentedString(multiBusValidationOverRideFlg)).append("\n");
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

