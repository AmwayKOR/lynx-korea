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
 * StateCityPostalcode
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class StateCityPostalcode   {
  @SerializedName("stateCd")
  private String stateCd = null;

  @SerializedName("cityName")
  private String cityName = null;

  @SerializedName("fromPostalCd")
  private String fromPostalCd = null;

  @SerializedName("toPostalCd")
  private String toPostalCd = null;

  public StateCityPostalcode stateCd(String stateCd) {
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

  public StateCityPostalcode cityName(String cityName) {
    this.cityName = cityName;
    return this;
  }

   /**
   * Get cityName
   * @return cityName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public StateCityPostalcode fromPostalCd(String fromPostalCd) {
    this.fromPostalCd = fromPostalCd;
    return this;
  }

   /**
   * Get fromPostalCd
   * @return fromPostalCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getFromPostalCd() {
    return fromPostalCd;
  }

  public void setFromPostalCd(String fromPostalCd) {
    this.fromPostalCd = fromPostalCd;
  }

  public StateCityPostalcode toPostalCd(String toPostalCd) {
    this.toPostalCd = toPostalCd;
    return this;
  }

   /**
   * Get toPostalCd
   * @return toPostalCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getToPostalCd() {
    return toPostalCd;
  }

  public void setToPostalCd(String toPostalCd) {
    this.toPostalCd = toPostalCd;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StateCityPostalcode stateCityPostalcode = (StateCityPostalcode) o;
    return Objects.equals(this.stateCd, stateCityPostalcode.stateCd) &&
        Objects.equals(this.cityName, stateCityPostalcode.cityName) &&
        Objects.equals(this.fromPostalCd, stateCityPostalcode.fromPostalCd) &&
        Objects.equals(this.toPostalCd, stateCityPostalcode.toPostalCd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stateCd, cityName, fromPostalCd, toPostalCd);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StateCityPostalcode {\n");
    
    sb.append("    stateCd: ").append(toIndentedString(stateCd)).append("\n");
    sb.append("    cityName: ").append(toIndentedString(cityName)).append("\n");
    sb.append("    fromPostalCd: ").append(toIndentedString(fromPostalCd)).append("\n");
    sb.append("    toPostalCd: ").append(toIndentedString(toPostalCd)).append("\n");
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

