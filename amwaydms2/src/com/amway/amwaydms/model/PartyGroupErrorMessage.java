package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.GroupErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.*;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class PartyGroupErrorMessage   {
  
  private Integer partyIndex = null;
  private List<GroupErrorMessage> groupErrorMessageList = new ArrayList<GroupErrorMessage>();

  
  /**
   * The index of the original list.  Zero is used for the Account Level error
   **/
  
  @ApiModelProperty(value = "The index of the original list.  Zero is used for the Account Level error")
  @JsonProperty("partyIndex")
  public Integer getPartyIndex() {
    return partyIndex;
  }
  public void setPartyIndex(Integer partyIndex) {
    this.partyIndex = partyIndex;
  }

  
  /**
   * field level errors group by object type such as Address, Tax, etc.
   **/
  
  @ApiModelProperty(value = "field level errors group by object type such as Address, Tax, etc.")
  @JsonProperty("groupErrorMessageList")
  public List<GroupErrorMessage> getGroupErrorMessageList() {
    return groupErrorMessageList;
  }
  public void setGroupErrorMessageList(List<GroupErrorMessage> groupErrorMessageList) {
    this.groupErrorMessageList = groupErrorMessageList;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PartyGroupErrorMessage partyGroupErrorMessage = (PartyGroupErrorMessage) o;
    return Objects.equals(partyIndex, partyGroupErrorMessage.partyIndex) &&
        Objects.equals(groupErrorMessageList, partyGroupErrorMessage.groupErrorMessageList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partyIndex, groupErrorMessageList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyGroupErrorMessage {\n");
    
    sb.append("    partyIndex: ").append(toIndentedString(partyIndex)).append("\n");
    sb.append("    groupErrorMessageList: ").append(toIndentedString(groupErrorMessageList)).append("\n");
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

