package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class TaxUpdate   {
  
  private String expirationDate = null;
  private String taxId = null;
  private String cntryCd = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("expirationDate")
  public String getExpirationDate() {
    return expirationDate;
  }
  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("taxId")
  public String getTaxId() {
    return taxId;
  }
  public void setTaxId(String taxId) {
    this.taxId = taxId;
  }

  
  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaxUpdate taxUpdate = (TaxUpdate) o;
    return Objects.equals(expirationDate, taxUpdate.expirationDate) &&
        Objects.equals(taxId, taxUpdate.taxId) &&
        Objects.equals(cntryCd, taxUpdate.cntryCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(expirationDate, taxId, cntryCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaxUpdate {\n");
    
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    taxId: ").append(toIndentedString(taxId)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
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

