package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Currency   {
  
  private String isoCurcyCd = null;
  private String curcyName = null;
  private String curcyDesc = null;
  private Boolean dsplyDescFlg = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("isoCurcyCd")
  public String getIsoCurcyCd() {
    return isoCurcyCd;
  }
  public void setIsoCurcyCd(String isoCurcyCd) {
    this.isoCurcyCd = isoCurcyCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("curcyName")
  public String getCurcyName() {
    return curcyName;
  }
  public void setCurcyName(String curcyName) {
    this.curcyName = curcyName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("curcyDesc")
  public String getCurcyDesc() {
    return curcyDesc;
  }
  public void setCurcyDesc(String curcyDesc) {
    this.curcyDesc = curcyDesc;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("dsplyDescFlg")
  public Boolean getDsplyDescFlg() {
    return dsplyDescFlg;
  }
  public void setDsplyDescFlg(Boolean dsplyDescFlg) {
    this.dsplyDescFlg = dsplyDescFlg;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Currency currency = (Currency) o;
    return Objects.equals(isoCurcyCd, currency.isoCurcyCd) &&
        Objects.equals(curcyName, currency.curcyName) &&
        Objects.equals(curcyDesc, currency.curcyDesc) &&
        Objects.equals(dsplyDescFlg, currency.dsplyDescFlg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isoCurcyCd, curcyName, curcyDesc, dsplyDescFlg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Currency {\n");
    
    sb.append("    isoCurcyCd: ").append(toIndentedString(isoCurcyCd)).append("\n");
    sb.append("    curcyName: ").append(toIndentedString(curcyName)).append("\n");
    sb.append("    curcyDesc: ").append(toIndentedString(curcyDesc)).append("\n");
    sb.append("    dsplyDescFlg: ").append(toIndentedString(dsplyDescFlg)).append("\n");
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

