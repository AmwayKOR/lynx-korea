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
 * TerritoryResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class TerritoryResponse   {
  @SerializedName("serverName")
  private String serverName = null;

  @SerializedName("errorMessage")
  private ErrorMessage errorMessage = null;

  @SerializedName("cntryTerritryList")
  private List<CountryTerritory> cntryTerritryList = new ArrayList<CountryTerritory>();

  public TerritoryResponse serverName(String serverName) {
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

  public TerritoryResponse errorMessage(ErrorMessage errorMessage) {
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

  public TerritoryResponse cntryTerritryList(List<CountryTerritory> cntryTerritryList) {
    this.cntryTerritryList = cntryTerritryList;
    return this;
  }

  public TerritoryResponse addCntryTerritryListItem(CountryTerritory cntryTerritryListItem) {
    this.cntryTerritryList.add(cntryTerritryListItem);
    return this;
  }

   /**
   * Get cntryTerritryList
   * @return cntryTerritryList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<CountryTerritory> getCntryTerritryList() {
    return cntryTerritryList;
  }

  public void setCntryTerritryList(List<CountryTerritory> cntryTerritryList) {
    this.cntryTerritryList = cntryTerritryList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerritoryResponse territoryResponse = (TerritoryResponse) o;
    return Objects.equals(this.serverName, territoryResponse.serverName) &&
        Objects.equals(this.errorMessage, territoryResponse.errorMessage) &&
        Objects.equals(this.cntryTerritryList, territoryResponse.cntryTerritryList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, cntryTerritryList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerritoryResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    cntryTerritryList: ").append(toIndentedString(cntryTerritryList)).append("\n");
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

