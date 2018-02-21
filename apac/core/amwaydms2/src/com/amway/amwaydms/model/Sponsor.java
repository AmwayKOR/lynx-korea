package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Sponsor   {
  
  private String sponsorName = null;
  private String sponsorABONo = null;
  private String sponsorPhone = null;
  private String sponsorEmail = null;

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sponsorName")
  public String getSponsorName() {
    return sponsorName;
  }
  public void setSponsorName(String sponsorName) {
    this.sponsorName = sponsorName;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sponsorABONo")
  public String getSponsorABONo() {
    return sponsorABONo;
  }
  public void setSponsorABONo(String sponsorABONo) {
    this.sponsorABONo = sponsorABONo;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sponsorPhone")
  public String getSponsorPhone() {
    return sponsorPhone;
  }
  public void setSponsorPhone(String sponsorPhone) {
    this.sponsorPhone = sponsorPhone;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("sponsorEmail")
  public String getSponsorEmail() {
    return sponsorEmail;
  }
  public void setSponsorEmail(String sponsorEmail) {
    this.sponsorEmail = sponsorEmail;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sponsor sponsor = (Sponsor) o;
    return Objects.equals(sponsorName, sponsor.sponsorName) &&
        Objects.equals(sponsorABONo, sponsor.sponsorABONo) &&
        Objects.equals(sponsorPhone, sponsor.sponsorPhone) &&
        Objects.equals(sponsorEmail, sponsor.sponsorEmail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sponsorName, sponsorABONo, sponsorPhone, sponsorEmail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sponsor {\n");
    
    sb.append("    sponsorName: ").append(toIndentedString(sponsorName)).append("\n");
    sb.append("    sponsorABONo: ").append(toIndentedString(sponsorABONo)).append("\n");
    sb.append("    sponsorPhone: ").append(toIndentedString(sponsorPhone)).append("\n");
    sb.append("    sponsorEmail: ").append(toIndentedString(sponsorEmail)).append("\n");
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

