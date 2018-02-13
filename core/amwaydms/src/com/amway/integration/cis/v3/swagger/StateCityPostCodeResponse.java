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

import java.util.ArrayList;
import java.util.List;


/**
 * StateCityPostCodeResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class StateCityPostCodeResponse   {
  @SerializedName("serverName")
  private String serverName = null;

  @SerializedName("errorMessage")
  private ErrorMessage errorMessage = null;

  @SerializedName("stateCtyPostalcdList")
  private List<CountryStateCity> stateCtyPostalcdList = new ArrayList<CountryStateCity>();

  public StateCityPostCodeResponse serverName(String serverName) {
    this.serverName = serverName;
    return this;
  }

   /**
   * Get serverName
   * @return serverName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getServerName() {
    return serverName;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  public StateCityPostCodeResponse errorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

   /**
   * Get errorMessage
   * @return errorMessage
  **/
  @ApiModelProperty(example = "null", value = "")
  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  public StateCityPostCodeResponse stateCtyPostalcdList(List<CountryStateCity> stateCtyPostalcdList) {
    this.stateCtyPostalcdList = stateCtyPostalcdList;
    return this;
  }

  public StateCityPostCodeResponse addStateCtyPostalcdListItem(CountryStateCity stateCtyPostalcdListItem) {
    this.stateCtyPostalcdList.add(stateCtyPostalcdListItem);
    return this;
  }

   /**
   * Get stateCtyPostalcdList
   * @return stateCtyPostalcdList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<CountryStateCity> getStateCtyPostalcdList() {
    return stateCtyPostalcdList;
  }

  public void setStateCtyPostalcdList(List<CountryStateCity> stateCtyPostalcdList) {
    this.stateCtyPostalcdList = stateCtyPostalcdList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StateCityPostCodeResponse stateCityPostCodeResponse = (StateCityPostCodeResponse) o;
    return Objects.equals(this.serverName, stateCityPostCodeResponse.serverName) &&
        Objects.equals(this.errorMessage, stateCityPostCodeResponse.errorMessage) &&
        Objects.equals(this.stateCtyPostalcdList, stateCityPostCodeResponse.stateCtyPostalcdList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, stateCtyPostalcdList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StateCityPostCodeResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    stateCtyPostalcdList: ").append(toIndentedString(stateCtyPostalcdList)).append("\n");
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
