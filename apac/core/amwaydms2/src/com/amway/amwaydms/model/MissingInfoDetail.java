package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class MissingInfoDetail   {
  
  private Long partyId = null;
  private String missingInfoId = null;
  private String isoCntryCd = null;
  private String dueDate = null;
  private String receiveDate = null;
  private String status = null;
  private String receiveUser = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("partyId")
  public Long getPartyId() {
    return partyId;
  }
  public void setPartyId(Long partyId) {
    this.partyId = partyId;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=MISIN'>Reference to Missing Info Object Id</a>
   **/
  
  @ApiModelProperty(required = true, value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=MISIN'>Reference to Missing Info Object Id</a>")
  @JsonProperty("missingInfoId")
  public String getMissingInfoId() {
    return missingInfoId;
  }
  public void setMissingInfoId(String missingInfoId) {
    this.missingInfoId = missingInfoId;
  }

  
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
  @JsonProperty("dueDate")
  public String getDueDate() {
    return dueDate;
  }
  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("receiveDate")
  public String getReceiveDate() {
    return receiveDate;
  }
  public void setReceiveDate(String receiveDate) {
    this.receiveDate = receiveDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("receiveUser")
  public String getReceiveUser() {
    return receiveUser;
  }
  public void setReceiveUser(String receiveUser) {
    this.receiveUser = receiveUser;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MissingInfoDetail missingInfoDetail = (MissingInfoDetail) o;
    return Objects.equals(partyId, missingInfoDetail.partyId) &&
        Objects.equals(missingInfoId, missingInfoDetail.missingInfoId) &&
        Objects.equals(isoCntryCd, missingInfoDetail.isoCntryCd) &&
        Objects.equals(dueDate, missingInfoDetail.dueDate) &&
        Objects.equals(receiveDate, missingInfoDetail.receiveDate) &&
        Objects.equals(status, missingInfoDetail.status) &&
        Objects.equals(receiveUser, missingInfoDetail.receiveUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, missingInfoId, isoCntryCd, dueDate, receiveDate, status, receiveUser);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MissingInfoDetail {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    missingInfoId: ").append(toIndentedString(missingInfoId)).append("\n");
    sb.append("    isoCntryCd: ").append(toIndentedString(isoCntryCd)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    receiveDate: ").append(toIndentedString(receiveDate)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    receiveUser: ").append(toIndentedString(receiveUser)).append("\n");
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

