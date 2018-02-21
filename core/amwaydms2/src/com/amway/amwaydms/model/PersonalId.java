package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PersonalId   {
  
  private String personalIdTypeCd = null;
  private String personalId = null;
  private String expirationDate = null;
  private String effectiveDate = null;
  private String countryPersonalId = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PRIDT'>Reference to Personal Type Code</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PRIDT'>Reference to Personal Type Code</a>")
  @JsonProperty("personalIdTypeCd")
  public String getPersonalIdTypeCd() {
    return personalIdTypeCd;
  }
  public void setPersonalIdTypeCd(String personalIdTypeCd) {
    this.personalIdTypeCd = personalIdTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("personalId")
  public String getPersonalId() {
    return personalId;
  }
  public void setPersonalId(String personalId) {
    this.personalId = personalId;
  }

  
  /**
   * Date time in UTC such '2016-12-29T14:53:00-02:00'
   **/
  
  @ApiModelProperty(value = "Date time in UTC such '2016-12-29T14:53:00-02:00'")
  @JsonProperty("expirationDate")
  public String getExpirationDate() {
    return expirationDate;
  }
  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  
  /**
   * Date time in UTC such '2016-12-29T14:53:00-02:00'
   **/
  
  @ApiModelProperty(value = "Date time in UTC such '2016-12-29T14:53:00-02:00'")
  @JsonProperty("effectiveDate")
  public String getEffectiveDate() {
    return effectiveDate;
  }
  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("countryPersonalId")
  public String getCountryPersonalId() {
    return countryPersonalId;
  }
  public void setCountryPersonalId(String countryPersonalId) {
    this.countryPersonalId = countryPersonalId;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalId personalId = (PersonalId) o;
    return Objects.equals(personalIdTypeCd, personalId.personalIdTypeCd) &&
        Objects.equals(personalId, personalId.personalId) &&
        Objects.equals(expirationDate, personalId.expirationDate) &&
        Objects.equals(effectiveDate, personalId.effectiveDate) &&
        Objects.equals(countryPersonalId, personalId.countryPersonalId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personalIdTypeCd, personalId, expirationDate, effectiveDate, countryPersonalId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalId {\n");
    
    sb.append("    personalIdTypeCd: ").append(toIndentedString(personalIdTypeCd)).append("\n");
    sb.append("    personalId: ").append(toIndentedString(personalId)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    effectiveDate: ").append(toIndentedString(effectiveDate)).append("\n");
    sb.append("    countryPersonalId: ").append(toIndentedString(countryPersonalId)).append("\n");
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

