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
 * SearchEcomm
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class SearchEcomm   {
  @SerializedName("ecommAddr")
  private String ecommAddr = null;

  @SerializedName("statusCd")
  private String statusCd = null;

  public SearchEcomm ecommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
    return this;
  }

   /**
   * Get ecommAddr
   * @return ecommAddr
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getEcommAddr() {
    return ecommAddr;
  }

  public void setEcommAddr(String ecommAddr) {
    this.ecommAddr = ecommAddr;
  }

  public SearchEcomm statusCd(String statusCd) {
    this.statusCd = statusCd;
    return this;
  }

   /**
   * Get statusCd
   * @return statusCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStatusCd() {
    return statusCd;
  }

  public void setStatusCd(String statusCd) {
    this.statusCd = statusCd;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SearchEcomm searchEcomm = (SearchEcomm) o;
    return Objects.equals(this.ecommAddr, searchEcomm.ecommAddr) &&
        Objects.equals(this.statusCd, searchEcomm.statusCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ecommAddr, statusCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchEcomm {\n");
    
    sb.append("    ecommAddr: ").append(toIndentedString(ecommAddr)).append("\n");
    sb.append("    statusCd: ").append(toIndentedString(statusCd)).append("\n");
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
