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
 * LeadsAccountDetails
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class LeadsAccountDetails   {
  @SerializedName("salesPlanAff")
  private Integer salesPlanAff = null;

  @SerializedName("aboNum")
  private Long aboNum = null;

  @SerializedName("address")
  private Address address = null;

  public LeadsAccountDetails salesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
    return this;
  }

   /**
   * Get salesPlanAff
   * @return salesPlanAff
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getSalesPlanAff() {
    return salesPlanAff;
  }

  public void setSalesPlanAff(Integer salesPlanAff) {
    this.salesPlanAff = salesPlanAff;
  }

  public LeadsAccountDetails aboNum(Long aboNum) {
    this.aboNum = aboNum;
    return this;
  }

   /**
   * Get aboNum
   * @return aboNum
  **/
  @ApiModelProperty(example = "null", value = "")
  public Long getAboNum() {
    return aboNum;
  }

  public void setAboNum(Long aboNum) {
    this.aboNum = aboNum;
  }

  public LeadsAccountDetails address(Address address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(example = "null", value = "")
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeadsAccountDetails leadsAccountDetails = (LeadsAccountDetails) o;
    return Objects.equals(this.salesPlanAff, leadsAccountDetails.salesPlanAff) &&
        Objects.equals(this.aboNum, leadsAccountDetails.aboNum) &&
        Objects.equals(this.address, leadsAccountDetails.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(salesPlanAff, aboNum, address);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LeadsAccountDetails {\n");
    
    sb.append("    salesPlanAff: ").append(toIndentedString(salesPlanAff)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
