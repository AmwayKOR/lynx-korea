package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AboImage   {
  
  private Long partyId = null;
  private String imageScrTypCd = null;
  private String imageUsageTypCd = null;
  private String imageRefUrl = null;
  private String imageStatusCd = null;
  private String mimeType = null;
  private String imageRejectReasonCd = null;
  private String imageRejectReasonText = null;
  private Integer salesPlanAff = null;
  private Long aboNum = null;
  private String cntryCd = null;

  
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
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=IMSCD'>Reference to Image source type codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=IMSCD'>Reference to Image source type codes</a>")
  @JsonProperty("imageScrTypCd")
  public String getImageScrTypCd() {
    return imageScrTypCd;
  }
  public void setImageScrTypCd(String imageScrTypCd) {
    this.imageScrTypCd = imageScrTypCd;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=IMUCD'>Reference to Image usage type codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=IMUCD'>Reference to Image usage type codes</a>")
  @JsonProperty("imageUsageTypCd")
  public String getImageUsageTypCd() {
    return imageUsageTypCd;
  }
  public void setImageUsageTypCd(String imageUsageTypCd) {
    this.imageUsageTypCd = imageUsageTypCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("imageRefUrl")
  public String getImageRefUrl() {
    return imageRefUrl;
  }
  public void setImageRefUrl(String imageRefUrl) {
    this.imageRefUrl = imageRefUrl;
  }

  
  /**
   * <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=IMSTC'>Reference to Image status codes</a>
   **/
  
  @ApiModelProperty(value = "<a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=IMSTC'>Reference to Image status codes</a>")
  @JsonProperty("imageStatusCd")
  public String getImageStatusCd() {
    return imageStatusCd;
  }
  public void setImageStatusCd(String imageStatusCd) {
    this.imageStatusCd = imageStatusCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("mimeType")
  public String getMimeType() {
    return mimeType;
  }
  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("imageRejectReasonCd")
  public String getImageRejectReasonCd() {
    return imageRejectReasonCd;
  }
  public void setImageRejectReasonCd(String imageRejectReasonCd) {
    this.imageRejectReasonCd = imageRejectReasonCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("imageRejectReasonText")
  public String getImageRejectReasonText() {
    return imageRejectReasonText;
  }
  public void setImageRejectReasonText(String imageRejectReasonText) {
    this.imageRejectReasonText = imageRejectReasonText;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("salesPlanAff")
  public Integer getSalesPlanAff() {
    return salesPlanAff;
  }
  public void setSalesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("aboNum")
  public Long getAboNum() {
    return aboNum;
  }
  public void setAboNum(Long aboNum) {
    this.aboNum = aboNum;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AboImage aboImage = (AboImage) o;
    return Objects.equals(partyId, aboImage.partyId) &&
        Objects.equals(imageScrTypCd, aboImage.imageScrTypCd) &&
        Objects.equals(imageUsageTypCd, aboImage.imageUsageTypCd) &&
        Objects.equals(imageRefUrl, aboImage.imageRefUrl) &&
        Objects.equals(imageStatusCd, aboImage.imageStatusCd) &&
        Objects.equals(mimeType, aboImage.mimeType) &&
        Objects.equals(imageRejectReasonCd, aboImage.imageRejectReasonCd) &&
        Objects.equals(imageRejectReasonText, aboImage.imageRejectReasonText) &&
        Objects.equals(salesPlanAff, aboImage.salesPlanAff) &&
        Objects.equals(aboNum, aboImage.aboNum) &&
        Objects.equals(cntryCd, aboImage.cntryCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, imageScrTypCd, imageUsageTypCd, imageRefUrl, imageStatusCd, mimeType, imageRejectReasonCd, imageRejectReasonText, salesPlanAff, aboNum, cntryCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AboImage {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    imageScrTypCd: ").append(toIndentedString(imageScrTypCd)).append("\n");
    sb.append("    imageUsageTypCd: ").append(toIndentedString(imageUsageTypCd)).append("\n");
    sb.append("    imageRefUrl: ").append(toIndentedString(imageRefUrl)).append("\n");
    sb.append("    imageStatusCd: ").append(toIndentedString(imageStatusCd)).append("\n");
    sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
    sb.append("    imageRejectReasonCd: ").append(toIndentedString(imageRejectReasonCd)).append("\n");
    sb.append("    imageRejectReasonText: ").append(toIndentedString(imageRejectReasonText)).append("\n");
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
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

