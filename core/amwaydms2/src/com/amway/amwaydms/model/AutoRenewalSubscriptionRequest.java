package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.BankInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class AutoRenewalSubscriptionRequest   {
  
  private Boolean signedUpFlg = null;
  private BankInput bank = null;

  
  /**
   * Indicates auto renewal subscription
   **/
  
  @ApiModelProperty(required = true, value = "Indicates auto renewal subscription")
  @JsonProperty("signedUpFlg")
  public Boolean getSignedUpFlg() {
    return signedUpFlg;
  }
  public void setSignedUpFlg(Boolean signedUpFlg) {
    this.signedUpFlg = signedUpFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("bank")
  public BankInput getBank() {
    return bank;
  }
  public void setBank(BankInput bank) {
    this.bank = bank;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AutoRenewalSubscriptionRequest autoRenewalSubscriptionRequest = (AutoRenewalSubscriptionRequest) o;
    return Objects.equals(signedUpFlg, autoRenewalSubscriptionRequest.signedUpFlg) &&
        Objects.equals(bank, autoRenewalSubscriptionRequest.bank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signedUpFlg, bank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AutoRenewalSubscriptionRequest {\n");
    
    sb.append("    signedUpFlg: ").append(toIndentedString(signedUpFlg)).append("\n");
    sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
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

