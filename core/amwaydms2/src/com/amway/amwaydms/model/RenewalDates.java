package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class RenewalDates   {
  
  private String isoCntryCd = null;
  private String accountSubTypeCd = null;
  private String earlyStartDate = null;
  private String ontimeStartDate = null;
  private String lateStartDate = null;
  private String renewalEndDate = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("isoCntryCd")
  public String getIsoCntryCd() {
    return isoCntryCd;
  }
  public void setIsoCntryCd(String isoCntryCd) {
    this.isoCntryCd = isoCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("accountSubTypeCd")
  public String getAccountSubTypeCd() {
    return accountSubTypeCd;
  }
  public void setAccountSubTypeCd(String accountSubTypeCd) {
    this.accountSubTypeCd = accountSubTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("earlyStartDate")
  public String getEarlyStartDate() {
    return earlyStartDate;
  }
  public void setEarlyStartDate(String earlyStartDate) {
    this.earlyStartDate = earlyStartDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("ontimeStartDate")
  public String getOntimeStartDate() {
    return ontimeStartDate;
  }
  public void setOntimeStartDate(String ontimeStartDate) {
    this.ontimeStartDate = ontimeStartDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("lateStartDate")
  public String getLateStartDate() {
    return lateStartDate;
  }
  public void setLateStartDate(String lateStartDate) {
    this.lateStartDate = lateStartDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("renewalEndDate")
  public String getRenewalEndDate() {
    return renewalEndDate;
  }
  public void setRenewalEndDate(String renewalEndDate) {
    this.renewalEndDate = renewalEndDate;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RenewalDates renewalDates = (RenewalDates) o;
    return Objects.equals(isoCntryCd, renewalDates.isoCntryCd) &&
        Objects.equals(accountSubTypeCd, renewalDates.accountSubTypeCd) &&
        Objects.equals(earlyStartDate, renewalDates.earlyStartDate) &&
        Objects.equals(ontimeStartDate, renewalDates.ontimeStartDate) &&
        Objects.equals(lateStartDate, renewalDates.lateStartDate) &&
        Objects.equals(renewalEndDate, renewalDates.renewalEndDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isoCntryCd, accountSubTypeCd, earlyStartDate, ontimeStartDate, lateStartDate, renewalEndDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RenewalDates {\n");
    
    sb.append("    isoCntryCd: ").append(toIndentedString(isoCntryCd)).append("\n");
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
    sb.append("    earlyStartDate: ").append(toIndentedString(earlyStartDate)).append("\n");
    sb.append("    ontimeStartDate: ").append(toIndentedString(ontimeStartDate)).append("\n");
    sb.append("    lateStartDate: ").append(toIndentedString(lateStartDate)).append("\n");
    sb.append("    renewalEndDate: ").append(toIndentedString(renewalEndDate)).append("\n");
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

