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
 * LOAResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class LOAResponse   {
  @SerializedName("serverName")
  private String serverName = null;

  @SerializedName("errorMessage")
  private ErrorMessage errorMessage = null;

  @SerializedName("lineOfAffiliationList")
  private List<LineOfAffiliation> lineOfAffiliationList = new ArrayList<LineOfAffiliation>();

  public LOAResponse serverName(String serverName) {
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

  public LOAResponse errorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp'>Reference to DMS error codes and meanings</a>
   * @return errorMessage
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp'>Reference to DMS error codes and meanings</a>")
  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }

  public LOAResponse lineOfAffiliationList(List<LineOfAffiliation> lineOfAffiliationList) {
    this.lineOfAffiliationList = lineOfAffiliationList;
    return this;
  }

  public LOAResponse addLineOfAffiliationListItem(LineOfAffiliation lineOfAffiliationListItem) {
    this.lineOfAffiliationList.add(lineOfAffiliationListItem);
    return this;
  }

   /**
   * Get lineOfAffiliationList
   * @return lineOfAffiliationList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<LineOfAffiliation> getLineOfAffiliationList() {
    return lineOfAffiliationList;
  }

  public void setLineOfAffiliationList(List<LineOfAffiliation> lineOfAffiliationList) {
    this.lineOfAffiliationList = lineOfAffiliationList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LOAResponse lOAResponse = (LOAResponse) o;
    return Objects.equals(this.serverName, lOAResponse.serverName) &&
        Objects.equals(this.errorMessage, lOAResponse.errorMessage) &&
        Objects.equals(this.lineOfAffiliationList, lOAResponse.lineOfAffiliationList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, errorMessage, lineOfAffiliationList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LOAResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("    lineOfAffiliationList: ").append(toIndentedString(lineOfAffiliationList)).append("\n");
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

