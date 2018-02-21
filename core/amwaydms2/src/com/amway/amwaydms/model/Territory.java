package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Territory   {
  
  private String territryCd = null;
  private String territryNm = null;
  private String territryDesc = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("territryCd")
  public String getTerritryCd() {
    return territryCd;
  }
  public void setTerritryCd(String territryCd) {
    this.territryCd = territryCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("territryNm")
  public String getTerritryNm() {
    return territryNm;
  }
  public void setTerritryNm(String territryNm) {
    this.territryNm = territryNm;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("territryDesc")
  public String getTerritryDesc() {
    return territryDesc;
  }
  public void setTerritryDesc(String territryDesc) {
    this.territryDesc = territryDesc;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Territory territory = (Territory) o;
    return Objects.equals(territryCd, territory.territryCd) &&
        Objects.equals(territryNm, territory.territryNm) &&
        Objects.equals(territryDesc, territory.territryDesc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(territryCd, territryNm, territryDesc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Territory {\n");
    
    sb.append("    territryCd: ").append(toIndentedString(territryCd)).append("\n");
    sb.append("    territryNm: ").append(toIndentedString(territryNm)).append("\n");
    sb.append("    territryDesc: ").append(toIndentedString(territryDesc)).append("\n");
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

