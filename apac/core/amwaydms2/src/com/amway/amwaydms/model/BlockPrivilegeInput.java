package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BlockPrivilegeInput   {
  
  private String blockPrivTypeId = null;
  private String effectiveDate = null;
  private String expirationDate = null;

  
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BlockPrivilegeInput blockPrivilegeInput = (BlockPrivilegeInput) o;
    return Objects.equals(blockPrivTypeId, blockPrivilegeInput.blockPrivTypeId) &&
        Objects.equals(effectiveDate, blockPrivilegeInput.effectiveDate) &&
        Objects.equals(expirationDate, blockPrivilegeInput.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockPrivTypeId, effectiveDate, expirationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BlockPrivilegeInput {\n");
    
    sb.append("    blockPrivTypeId: ").append(toIndentedString(blockPrivTypeId)).append("\n");
    sb.append("    effectiveDate: ").append(toIndentedString(effectiveDate)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
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

