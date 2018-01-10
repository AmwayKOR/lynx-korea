package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Tax   {
  
  private String taxTypeCd = null;
  private String expirationDate = null;
  private String taxId = null;
  private String cntryCd = null;
  private String partyId = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=TAXTY'>Reference to Tax Type Code</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=TAXTY'>Reference to Tax Type Code</a>")
  @JsonProperty("taxTypeCd")
  public String getTaxTypeCd() {
    return taxTypeCd;
  }
  public void setTaxTypeCd(String taxTypeCd) {
    this.taxTypeCd = taxTypeCd;
  }

  
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

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyId")
  public String getPartyId() {
    return partyId;
  }
  public void setPartyId(String partyId) {
    this.partyId = partyId;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tax tax = (Tax) o;
    return Objects.equals(taxTypeCd, tax.taxTypeCd) &&
        Objects.equals(expirationDate, tax.expirationDate) &&
        Objects.equals(taxId, tax.taxId) &&
        Objects.equals(cntryCd, tax.cntryCd) &&
        Objects.equals(partyId, tax.partyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(taxTypeCd, expirationDate, taxId, cntryCd, partyId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tax {\n");
    
    sb.append("    taxTypeCd: ").append(toIndentedString(taxTypeCd)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    taxId: ").append(toIndentedString(taxId)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
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

