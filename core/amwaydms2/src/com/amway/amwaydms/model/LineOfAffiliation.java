package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class LineOfAffiliation   {
  
  private String loaCd = null;
  private String loaName = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("loaCd")
  public String getLoaCd() {
    return loaCd;
  }
  public void setLoaCd(String loaCd) {
    this.loaCd = loaCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("loaName")
  public String getLoaName() {
    return loaName;
  }
  public void setLoaName(String loaName) {
    this.loaName = loaName;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LineOfAffiliation lineOfAffiliation = (LineOfAffiliation) o;
    return Objects.equals(loaCd, lineOfAffiliation.loaCd) &&
        Objects.equals(loaName, lineOfAffiliation.loaName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(loaCd, loaName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LineOfAffiliation {\n");
    
    sb.append("    loaCd: ").append(toIndentedString(loaCd)).append("\n");
    sb.append("    loaName: ").append(toIndentedString(loaName)).append("\n");
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

