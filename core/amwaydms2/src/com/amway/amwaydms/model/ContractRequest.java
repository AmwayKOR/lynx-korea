package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.AccountContractDetailInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class ContractRequest   {
  
  private AccountContractDetailInput contract = null;

  
  /**
   * Contract Information
   **/
  
  @ApiModelProperty(required = true, value = "Contract Information")
  @JsonProperty("contract")
  public AccountContractDetailInput getContract() {
    return contract;
  }
  public void setContract(AccountContractDetailInput contract) {
    this.contract = contract;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContractRequest contractRequest = (ContractRequest) o;
    return Objects.equals(contract, contractRequest.contract);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contract);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContractRequest {\n");
    
    sb.append("    contract: ").append(toIndentedString(contract)).append("\n");
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

