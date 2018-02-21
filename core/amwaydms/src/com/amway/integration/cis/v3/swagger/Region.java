/**
 * Magic DMS RESTful Services
 * No descripton provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: v3
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.amway.integration.cis.v3.swagger;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Region
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class Region   {
  @SerializedName("stateCd")
  private String stateCd = null;

  @SerializedName("stateNm")
  private String stateNm = null;

  @SerializedName("geoRgnCd")
  private String geoRgnCd = null;

  @SerializedName("salesRgnCd")
  private String salesRgnCd = null;

  public Region stateCd(String stateCd) {
    this.stateCd = stateCd;
    return this;
  }

   /**
   * Get stateCd
   * @return stateCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStateCd() {
    return stateCd;
  }

  public void setStateCd(String stateCd) {
    this.stateCd = stateCd;
  }

  public Region stateNm(String stateNm) {
    this.stateNm = stateNm;
    return this;
  }

   /**
   * Get stateNm
   * @return stateNm
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStateNm() {
    return stateNm;
  }

  public void setStateNm(String stateNm) {
    this.stateNm = stateNm;
  }

  public Region geoRgnCd(String geoRgnCd) {
    this.geoRgnCd = geoRgnCd;
    return this;
  }

   /**
   * Get geoRgnCd
   * @return geoRgnCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getGeoRgnCd() {
    return geoRgnCd;
  }

  public void setGeoRgnCd(String geoRgnCd) {
    this.geoRgnCd = geoRgnCd;
  }

  public Region salesRgnCd(String salesRgnCd) {
    this.salesRgnCd = salesRgnCd;
    return this;
  }

   /**
   * Get salesRgnCd
   * @return salesRgnCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getSalesRgnCd() {
    return salesRgnCd;
  }

  public void setSalesRgnCd(String salesRgnCd) {
    this.salesRgnCd = salesRgnCd;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Region region = (Region) o;
    return Objects.equals(this.stateCd, region.stateCd) &&
        Objects.equals(this.stateNm, region.stateNm) &&
        Objects.equals(this.geoRgnCd, region.geoRgnCd) &&
        Objects.equals(this.salesRgnCd, region.salesRgnCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateCd, stateNm, geoRgnCd, salesRgnCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Region {\n");
    
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    stateNm: ").append(toIndentedString(stateNm)).append("\n");
    sb.append("    geoRgnCd: ").append(toIndentedString(geoRgnCd)).append("\n");
    sb.append("    salesRgnCd: ").append(toIndentedString(salesRgnCd)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

