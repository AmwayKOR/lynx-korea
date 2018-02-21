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
 * AccountSearchResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class AccountSearchResponse   {
  @SerializedName("serverName")
  private String serverName = null;

  @SerializedName("accountList")
  private List<SearchAccount> accountList = new ArrayList<SearchAccount>();

  @SerializedName("errorMessage")
  private ErrorMessage errorMessage = null;

  public AccountSearchResponse serverName(String serverName) {
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

  public AccountSearchResponse accountList(List<SearchAccount> accountList) {
    this.accountList = accountList;
    return this;
  }

  public AccountSearchResponse addAccountListItem(SearchAccount accountListItem) {
    this.accountList.add(accountListItem);
    return this;
  }

   /**
   * Get accountList
   * @return accountList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<SearchAccount> getAccountList() {
    return accountList;
  }

  public void setAccountList(List<SearchAccount> accountList) {
    this.accountList = accountList;
  }

  public AccountSearchResponse errorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }

   /**
   * <a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=account'>Reference to DMS error codes and meanings</a>
   * @return errorMessage
  **/
  @ApiModelProperty(example = "null", value = "<a href='/DMS_Service_Web/ErrorCodeDesc.jsp?objectCd=account'>Reference to DMS error codes and meanings</a>")
  public ErrorMessage getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(ErrorMessage errorMessage) {
    this.errorMessage = errorMessage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountSearchResponse accountSearchResponse = (AccountSearchResponse) o;
    return Objects.equals(this.serverName, accountSearchResponse.serverName) &&
        Objects.equals(this.accountList, accountSearchResponse.accountList) &&
        Objects.equals(this.errorMessage, accountSearchResponse.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serverName, accountList, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountSearchResponse {\n");
    
    sb.append("    serverName: ").append(toIndentedString(serverName)).append("\n");
    sb.append("    accountList: ").append(toIndentedString(accountList)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
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

