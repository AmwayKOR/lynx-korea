package com.amway.amwaydms.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class Image   {
  
  private Long partyId = null;
  private String imageScrTypCd = null;
  private String imageUsageTypCd = null;
  private String imageRefUrl = null;
  private String imageStatusCd = null;
  private String mimeType = null;
  private String imageRejectReasonCd = null;
  private String imageRejectReasonText = null;

  
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Image image = (Image) o;
    return Objects.equals(partyId, image.partyId) &&
        Objects.equals(imageScrTypCd, image.imageScrTypCd) &&
        Objects.equals(imageUsageTypCd, image.imageUsageTypCd) &&
        Objects.equals(imageRefUrl, image.imageRefUrl) &&
        Objects.equals(imageStatusCd, image.imageStatusCd) &&
        Objects.equals(mimeType, image.mimeType) &&
        Objects.equals(imageRejectReasonCd, image.imageRejectReasonCd) &&
        Objects.equals(imageRejectReasonText, image.imageRejectReasonText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyId, imageScrTypCd, imageUsageTypCd, imageRefUrl, imageStatusCd, mimeType, imageRejectReasonCd, imageRejectReasonText);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Image {\n");
    
    sb.append("    partyId: ").append(toIndentedString(partyId)).append("\n");
    sb.append("    imageScrTypCd: ").append(toIndentedString(imageScrTypCd)).append("\n");
    sb.append("    imageUsageTypCd: ").append(toIndentedString(imageUsageTypCd)).append("\n");
    sb.append("    imageRefUrl: ").append(toIndentedString(imageRefUrl)).append("\n");
    sb.append("    imageStatusCd: ").append(toIndentedString(imageStatusCd)).append("\n");
    sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
    sb.append("    imageRejectReasonCd: ").append(toIndentedString(imageRejectReasonCd)).append("\n");
    sb.append("    imageRejectReasonText: ").append(toIndentedString(imageRejectReasonText)).append("\n");
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

