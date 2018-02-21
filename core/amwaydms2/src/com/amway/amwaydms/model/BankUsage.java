package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class BankUsage   {
  
  private String acctUseCode = null;
  private String useFlag = null;

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BAACU'>Reference to Bank Account Usage Codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=BAACU'>Reference to Bank Account Usage Codes</a>")
  @JsonProperty("acctUseCode")
  public String getAcctUseCode() {
    return acctUseCode;
  }
  public void setAcctUseCode(String acctUseCode) {
    this.acctUseCode = acctUseCode;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("useFlag")
  public String getUseFlag() {
    return useFlag;
  }
  public void setUseFlag(String useFlag) {
    this.useFlag = useFlag;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankUsage bankUsage = (BankUsage) o;
    return Objects.equals(acctUseCode, bankUsage.acctUseCode) &&
        Objects.equals(useFlag, bankUsage.useFlag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acctUseCode, useFlag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankUsage {\n");
    
    sb.append("    acctUseCode: ").append(toIndentedString(acctUseCode)).append("\n");
    sb.append("    useFlag: ").append(toIndentedString(useFlag)).append("\n");
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

