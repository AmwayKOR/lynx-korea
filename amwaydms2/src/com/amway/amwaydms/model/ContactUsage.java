package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class ContactUsage   {
  
  private String contactPointPurposeCd = null;
  private Boolean primaryFlag = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=CNPTU'>Reference to contact point purpose codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=CNPTU'>Reference to contact point purpose codes</a>")
  @JsonProperty("contactPointPurposeCd")
  public String getContactPointPurposeCd() {
    return contactPointPurposeCd;
  }
  public void setContactPointPurposeCd(String contactPointPurposeCd) {
    this.contactPointPurposeCd = contactPointPurposeCd;
  }

  
  /**
   * primaryFlag
   **/
  
  @ApiModelProperty(required = true, value = "primaryFlag")
  @JsonProperty("primaryFlag")
  public Boolean getPrimaryFlag() {
    return primaryFlag;
  }
  public void setPrimaryFlag(Boolean primaryFlag) {
    this.primaryFlag = primaryFlag;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactUsage contactUsage = (ContactUsage) o;
    return Objects.equals(contactPointPurposeCd, contactUsage.contactPointPurposeCd) &&
        Objects.equals(primaryFlag, contactUsage.primaryFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactPointPurposeCd, primaryFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactUsage {\n");
    
    sb.append("    contactPointPurposeCd: ").append(toIndentedString(contactPointPurposeCd)).append("\n");
    sb.append("    primaryFlag: ").append(toIndentedString(primaryFlag)).append("\n");
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

