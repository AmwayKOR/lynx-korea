package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BlockPrivilege   {
  
  private String blockPrivTypeId = null;
  private String effectiveDate = null;
  private String expirationDate = null;
  private Boolean blockFlag = null;

  
  /**
   * Block or Privilege Type.  Please see <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BLPRI'>Block or Privilege codes</a>
   **/
  
  @ApiModelProperty(required = true, value = "Block or Privilege Type.  Please see <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BLPRI'>Block or Privilege codes</a>")
  @JsonProperty("blockPrivTypeId")
  public String getBlockPrivTypeId() {
    return blockPrivTypeId;
  }
  public void setBlockPrivTypeId(String blockPrivTypeId) {
    this.blockPrivTypeId = blockPrivTypeId;
  }

  
  /**
   * Effective date time in UTC such '2016-12-29T14:53:00-02:00'
   **/
  
  @ApiModelProperty(required = true, value = "Effective date time in UTC such '2016-12-29T14:53:00-02:00'")
  @JsonProperty("effectiveDate")
  public String getEffectiveDate() {
    return effectiveDate;
  }
  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  
  /**
   * Effective date time in UTC such '2016-12-29T14:53:00-02:00'
   **/
  
  @ApiModelProperty(required = true, value = "Effective date time in UTC such '2016-12-29T14:53:00-02:00'")
  @JsonProperty("expirationDate")
  public String getExpirationDate() {
    return expirationDate;
  }
  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("blockFlag")
  public Boolean getBlockFlag() {
    return blockFlag;
  }
  public void setBlockFlag(Boolean blockFlag) {
    this.blockFlag = blockFlag;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlockPrivilege blockPrivilege = (BlockPrivilege) o;
    return Objects.equals(blockPrivTypeId, blockPrivilege.blockPrivTypeId) &&
        Objects.equals(effectiveDate, blockPrivilege.effectiveDate) &&
        Objects.equals(expirationDate, blockPrivilege.expirationDate) &&
        Objects.equals(blockFlag, blockPrivilege.blockFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockPrivTypeId, effectiveDate, expirationDate, blockFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlockPrivilege {\n");
    
    sb.append("    blockPrivTypeId: ").append(toIndentedString(blockPrivTypeId)).append("\n");
    sb.append("    effectiveDate: ").append(toIndentedString(effectiveDate)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
    sb.append("    blockFlag: ").append(toIndentedString(blockFlag)).append("\n");
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

