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
 * CountryRegion
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-05-10T10:19:16.797+03:00")
public class CountryRegion   {
  @SerializedName("cntryCd")
  private String cntryCd = null;

  @SerializedName("regionList")
  private List<Region> regionList = new ArrayList<Region>();

  public CountryRegion cntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
    return this;
  }

   /**
   * Get cntryCd
   * @return cntryCd
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getCntryCd() {
    return cntryCd;
  }

  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  public CountryRegion regionList(List<Region> regionList) {
    this.regionList = regionList;
    return this;
  }

  public CountryRegion addRegionListItem(Region regionListItem) {
    this.regionList.add(regionListItem);
    return this;
  }

   /**
   * Get regionList
   * @return regionList
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<Region> getRegionList() {
    return regionList;
  }

  public void setRegionList(List<Region> regionList) {
    this.regionList = regionList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CountryRegion countryRegion = (CountryRegion) o;
    return Objects.equals(this.cntryCd, countryRegion.cntryCd) &&
        Objects.equals(this.regionList, countryRegion.regionList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cntryCd, regionList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CountryRegion {\n");
    
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    regionList: ").append(toIndentedString(regionList)).append("\n");
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
