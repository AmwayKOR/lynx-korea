package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class UsageData   {
  
  private String contactPointPurposeCd = null;
  private String primaryFlag = null;
  private String careOf = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("contactPointPurposeCd")
  public String getContactPointPurposeCd() {
    return contactPointPurposeCd;
  }
  public void setContactPointPurposeCd(String contactPointPurposeCd) {
    this.contactPointPurposeCd = contactPointPurposeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("primaryFlag")
  public String getPrimaryFlag() {
    return primaryFlag;
  }
  public void setPrimaryFlag(String primaryFlag) {
    this.primaryFlag = primaryFlag;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("careOf")
  public String getCareOf() {
    return careOf;
  }
  public void setCareOf(String careOf) {
    this.careOf = careOf;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UsageData usageData = (UsageData) o;
    return Objects.equals(contactPointPurposeCd, usageData.contactPointPurposeCd) &&
        Objects.equals(primaryFlag, usageData.primaryFlag) &&
        Objects.equals(careOf, usageData.careOf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactPointPurposeCd, primaryFlag, careOf);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UsageData {\n");
    
    sb.append("    contactPointPurposeCd: ").append(toIndentedString(contactPointPurposeCd)).append("\n");
    sb.append("    primaryFlag: ").append(toIndentedString(primaryFlag)).append("\n");
    sb.append("    careOf: ").append(toIndentedString(careOf)).append("\n");
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

