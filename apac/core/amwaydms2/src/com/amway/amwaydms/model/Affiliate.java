package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Affiliate   {
  
  private Integer salesPlanAff = null;
  private String salesPlanAffName = null;
  private String salesPlanAffDesc = null;
  private Boolean multiCurrencyFlag = null;
  private String defaultCntryCd = null;
  private String defaultCompanyCd = null;
  private String defaultCurrencyCd = null;
  private String affTimeZone = null;
  private String expirationCd = null;
  private String defaultExpirationDate = null;
  private Long affAliasAboNum = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanAff")
  public Integer getSalesPlanAff() {
    return salesPlanAff;
  }
  public void setSalesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanAffName")
  public String getSalesPlanAffName() {
    return salesPlanAffName;
  }
  public void setSalesPlanAffName(String salesPlanAffName) {
    this.salesPlanAffName = salesPlanAffName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanAffDesc")
  public String getSalesPlanAffDesc() {
    return salesPlanAffDesc;
  }
  public void setSalesPlanAffDesc(String salesPlanAffDesc) {
    this.salesPlanAffDesc = salesPlanAffDesc;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("multiCurrencyFlag")
  public Boolean getMultiCurrencyFlag() {
    return multiCurrencyFlag;
  }
  public void setMultiCurrencyFlag(Boolean multiCurrencyFlag) {
    this.multiCurrencyFlag = multiCurrencyFlag;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("defaultCntryCd")
  public String getDefaultCntryCd() {
    return defaultCntryCd;
  }
  public void setDefaultCntryCd(String defaultCntryCd) {
    this.defaultCntryCd = defaultCntryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("defaultCompanyCd")
  public String getDefaultCompanyCd() {
    return defaultCompanyCd;
  }
  public void setDefaultCompanyCd(String defaultCompanyCd) {
    this.defaultCompanyCd = defaultCompanyCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("defaultCurrencyCd")
  public String getDefaultCurrencyCd() {
    return defaultCurrencyCd;
  }
  public void setDefaultCurrencyCd(String defaultCurrencyCd) {
    this.defaultCurrencyCd = defaultCurrencyCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("affTimeZone")
  public String getAffTimeZone() {
    return affTimeZone;
  }
  public void setAffTimeZone(String affTimeZone) {
    this.affTimeZone = affTimeZone;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("expirationCd")
  public String getExpirationCd() {
    return expirationCd;
  }
  public void setExpirationCd(String expirationCd) {
    this.expirationCd = expirationCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("defaultExpirationDate")
  public String getDefaultExpirationDate() {
    return defaultExpirationDate;
  }
  public void setDefaultExpirationDate(String defaultExpirationDate) {
    this.defaultExpirationDate = defaultExpirationDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("affAliasAboNum")
  public Long getAffAliasAboNum() {
    return affAliasAboNum;
  }
  public void setAffAliasAboNum(Long affAliasAboNum) {
    this.affAliasAboNum = affAliasAboNum;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Affiliate affiliate = (Affiliate) o;
    return Objects.equals(salesPlanAff, affiliate.salesPlanAff) &&
        Objects.equals(salesPlanAffName, affiliate.salesPlanAffName) &&
        Objects.equals(salesPlanAffDesc, affiliate.salesPlanAffDesc) &&
        Objects.equals(multiCurrencyFlag, affiliate.multiCurrencyFlag) &&
        Objects.equals(defaultCntryCd, affiliate.defaultCntryCd) &&
        Objects.equals(defaultCompanyCd, affiliate.defaultCompanyCd) &&
        Objects.equals(defaultCurrencyCd, affiliate.defaultCurrencyCd) &&
        Objects.equals(affTimeZone, affiliate.affTimeZone) &&
        Objects.equals(expirationCd, affiliate.expirationCd) &&
        Objects.equals(defaultExpirationDate, affiliate.defaultExpirationDate) &&
        Objects.equals(affAliasAboNum, affiliate.affAliasAboNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(salesPlanAff, salesPlanAffName, salesPlanAffDesc, multiCurrencyFlag, defaultCntryCd, defaultCompanyCd, defaultCurrencyCd, affTimeZone, expirationCd, defaultExpirationDate, affAliasAboNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Affiliate {\n");
    
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    salesPlanAffName: ").append(toIndentedString(salesPlanAffName)).append("\n");
    sb.append("    salesPlanAffDesc: ").append(toIndentedString(salesPlanAffDesc)).append("\n");
    sb.append("    multiCurrencyFlag: ").append(toIndentedString(multiCurrencyFlag)).append("\n");
    sb.append("    defaultCntryCd: ").append(toIndentedString(defaultCntryCd)).append("\n");
    sb.append("    defaultCompanyCd: ").append(toIndentedString(defaultCompanyCd)).append("\n");
    sb.append("    defaultCurrencyCd: ").append(toIndentedString(defaultCurrencyCd)).append("\n");
    sb.append("    affTimeZone: ").append(toIndentedString(affTimeZone)).append("\n");
    sb.append("    expirationCd: ").append(toIndentedString(expirationCd)).append("\n");
    sb.append("    defaultExpirationDate: ").append(toIndentedString(defaultExpirationDate)).append("\n");
    sb.append("    affAliasAboNum: ").append(toIndentedString(affAliasAboNum)).append("\n");
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

