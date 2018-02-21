package com.amway.amwaydms.model;

import java.util.Objects;
import com.amway.amwaydms.model.BankInput;
import com.amway.amwaydms.model.FormerAccountInput;
import com.amway.amwaydms.model.RegistrationSponsor;
import com.amway.amwaydms.model.SimplePartyInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2017-08-21T07:46:22.253-04:00")
public class RegistrationSinglePartyRequest   {
  
  private String clientIpAddress = null;
  private String clientApplicationId = null;
  private String clientChannelCd = null;
  private String processTypeCd = null;
  private Long aboNum = null;
  private String cntryCd = null;
  private String lglEnttyType = null;
  private String accountSubTypeCd = null;
  private String accountTypeCd = null;
  private Boolean contractAcceptFlg = null;
  private Boolean authorizeEmail = null;
  private String languageCd = null;
  private RegistrationSponsor sponsor = null;
  private FormerAccountInput formerAccount = null;
  private Integer primaryPartyIndex = null;
  private String aboEntryDate = null;
  private BankInput bank = null;
  private SimplePartyInput party = null;

  
  /**
   * Browser's IP Address
   **/
  
  @ApiModelProperty(required = true, value = "Browser's IP Address")
  @JsonProperty("clientIpAddress")
  public String getClientIpAddress() {
    return clientIpAddress;
  }
  public void setClientIpAddress(String clientIpAddress) {
    this.clientIpAddress = clientIpAddress;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("clientApplicationId")
  public String getClientApplicationId() {
    return clientApplicationId;
  }
  public void setClientApplicationId(String clientApplicationId) {
    this.clientApplicationId = clientApplicationId;
  }

  
  /**
   * Registration Source Channel. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SRCHN'>link</a> for valid codes
   **/
  
  @ApiModelProperty(required = true, value = "Registration Source Channel. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=SRCHN'>link</a> for valid codes")
  @JsonProperty("clientChannelCd")
  public String getClientChannelCd() {
    return clientChannelCd;
  }
  public void setClientChannelCd(String clientChannelCd) {
    this.clientChannelCd = clientChannelCd;
  }

  
  /**
   * MAGIC DMS Process Type. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PROTY'>link</a> for valid codes
   **/
  
  @ApiModelProperty(required = true, value = "MAGIC DMS Process Type. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=PROTY'>link</a> for valid codes")
  @JsonProperty("processTypeCd")
  public String getProcessTypeCd() {
    return processTypeCd;
  }
  public void setProcessTypeCd(String processTypeCd) {
    this.processTypeCd = processTypeCd;
  }

  
  /**
   * ABO number
   **/
  
  @ApiModelProperty(required = true, value = "ABO number")
  @JsonProperty("aboNum")
  public Long getAboNum() {
    return aboNum;
  }
  public void setAboNum(Long aboNum) {
    this.aboNum = aboNum;
  }

  
  /**
   * ISO Country codes.
   **/
  
  @ApiModelProperty(required = true, value = "ISO Country codes.")
  @JsonProperty("cntryCd")
  public String getCntryCd() {
    return cntryCd;
  }
  public void setCntryCd(String cntryCd) {
    this.cntryCd = cntryCd;
  }

  
  /**
   * Legal Entity. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=LGETY'>link</a> for valid codes
   **/
  
  @ApiModelProperty(required = true, value = "Legal Entity. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=LGETY'>link</a> for valid codes")
  @JsonProperty("lglEnttyType")
  public String getLglEnttyType() {
    return lglEnttyType;
  }
  public void setLglEnttyType(String lglEnttyType) {
    this.lglEnttyType = lglEnttyType;
  }

  
  /**
   * Account Sub-type. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ACCST'>link</a> for valid codes
   **/
  
  @ApiModelProperty(required = true, value = "Account Sub-type. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ACCST'>link</a> for valid codes")
  @JsonProperty("accountSubTypeCd")
  public String getAccountSubTypeCd() {
    return accountSubTypeCd;
  }
  public void setAccountSubTypeCd(String accountSubTypeCd) {
    this.accountSubTypeCd = accountSubTypeCd;
  }

  
  /**
   * Account Type. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ACTTY'>link</a> for valid codes
   **/
  
  @ApiModelProperty(required = true, value = "Account Type. Please see this <a href='/DMS_Service_Web/DomainCodeLookUp.jsp?code=ACTTY'>link</a> for valid codes")
  @JsonProperty("accountTypeCd")
  public String getAccountTypeCd() {
    return accountTypeCd;
  }
  public void setAccountTypeCd(String accountTypeCd) {
    this.accountTypeCd = accountTypeCd;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("contractAcceptFlg")
  public Boolean getContractAcceptFlg() {
    return contractAcceptFlg;
  }
  public void setContractAcceptFlg(Boolean contractAcceptFlg) {
    this.contractAcceptFlg = contractAcceptFlg;
  }

  
  /**
   **/
  
  @ApiModelProperty(value = "")
  @JsonProperty("authorizeEmail")
  public Boolean getAuthorizeEmail() {
    return authorizeEmail;
  }
  public void setAuthorizeEmail(Boolean authorizeEmail) {
    this.authorizeEmail = authorizeEmail;
  }

  
  /**
   * Official ISO language code
   **/
  
  @ApiModelProperty(value = "Official ISO language code")
  @JsonProperty("languageCd")
  public String getLanguageCd() {
    return languageCd;
  }
  public void setLanguageCd(String languageCd) {
    this.languageCd = languageCd;
  }

  
  /**
   * Sponsor information
   **/
  
  @ApiModelProperty(required = true, value = "Sponsor information")
  @JsonProperty("sponsor")
  public RegistrationSponsor getSponsor() {
    return sponsor;
  }
  public void setSponsor(RegistrationSponsor sponsor) {
    this.sponsor = sponsor;
  }

  
  /**
   * Former Amway Account
   **/
  
  @ApiModelProperty(value = "Former Amway Account")
  @JsonProperty("formerAccount")
  public FormerAccountInput getFormerAccount() {
    return formerAccount;
  }
  public void setFormerAccount(FormerAccountInput formerAccount) {
    this.formerAccount = formerAccount;
  }

  
  /**
   * Primary Party Index Number
   **/
  
  @ApiModelProperty(value = "Primary Party Index Number")
  @JsonProperty("primaryPartyIndex")
  public Integer getPrimaryPartyIndex() {
    return primaryPartyIndex;
  }
  public void setPrimaryPartyIndex(Integer primaryPartyIndex) {
    this.primaryPartyIndex = primaryPartyIndex;
  }

  
  /**
   * Account entry date
   **/
  
  @ApiModelProperty(value = "Account entry date")
  @JsonProperty("aboEntryDate")
  public String getAboEntryDate() {
    return aboEntryDate;
  }
  public void setAboEntryDate(String aboEntryDate) {
    this.aboEntryDate = aboEntryDate;
  }

  
  /**
   * BankAccount
   **/
  
  @ApiModelProperty(value = "BankAccount")
  @JsonProperty("bank")
  public BankInput getBank() {
    return bank;
  }
  public void setBank(BankInput bank) {
    this.bank = bank;
  }

  
  /**
   * Party information
   **/
  
  @ApiModelProperty(required = true, value = "Party information")
  @JsonProperty("party")
  public SimplePartyInput getParty() {
    return party;
  }
  public void setParty(SimplePartyInput party) {
    this.party = party;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationSinglePartyRequest registrationSinglePartyRequest = (RegistrationSinglePartyRequest) o;
    return Objects.equals(clientIpAddress, registrationSinglePartyRequest.clientIpAddress) &&
        Objects.equals(clientApplicationId, registrationSinglePartyRequest.clientApplicationId) &&
        Objects.equals(clientChannelCd, registrationSinglePartyRequest.clientChannelCd) &&
        Objects.equals(processTypeCd, registrationSinglePartyRequest.processTypeCd) &&
        Objects.equals(aboNum, registrationSinglePartyRequest.aboNum) &&
        Objects.equals(cntryCd, registrationSinglePartyRequest.cntryCd) &&
        Objects.equals(lglEnttyType, registrationSinglePartyRequest.lglEnttyType) &&
        Objects.equals(accountSubTypeCd, registrationSinglePartyRequest.accountSubTypeCd) &&
        Objects.equals(accountTypeCd, registrationSinglePartyRequest.accountTypeCd) &&
        Objects.equals(contractAcceptFlg, registrationSinglePartyRequest.contractAcceptFlg) &&
        Objects.equals(authorizeEmail, registrationSinglePartyRequest.authorizeEmail) &&
        Objects.equals(languageCd, registrationSinglePartyRequest.languageCd) &&
        Objects.equals(sponsor, registrationSinglePartyRequest.sponsor) &&
        Objects.equals(formerAccount, registrationSinglePartyRequest.formerAccount) &&
        Objects.equals(primaryPartyIndex, registrationSinglePartyRequest.primaryPartyIndex) &&
        Objects.equals(aboEntryDate, registrationSinglePartyRequest.aboEntryDate) &&
        Objects.equals(bank, registrationSinglePartyRequest.bank) &&
        Objects.equals(party, registrationSinglePartyRequest.party);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientIpAddress, clientApplicationId, clientChannelCd, processTypeCd, aboNum, cntryCd, lglEnttyType, accountSubTypeCd, accountTypeCd, contractAcceptFlg, authorizeEmail, languageCd, sponsor, formerAccount, primaryPartyIndex, aboEntryDate, bank, party);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrationSinglePartyRequest {\n");
    
    sb.append("    clientIpAddress: ").append(toIndentedString(clientIpAddress)).append("\n");
    sb.append("    clientApplicationId: ").append(toIndentedString(clientApplicationId)).append("\n");
    sb.append("    clientChannelCd: ").append(toIndentedString(clientChannelCd)).append("\n");
    sb.append("    processTypeCd: ").append(toIndentedString(processTypeCd)).append("\n");
    sb.append("    aboNum: ").append(toIndentedString(aboNum)).append("\n");
    sb.append("    cntryCd: ").append(toIndentedString(cntryCd)).append("\n");
    sb.append("    lglEnttyType: ").append(toIndentedString(lglEnttyType)).append("\n");
    sb.append("    accountSubTypeCd: ").append(toIndentedString(accountSubTypeCd)).append("\n");
    sb.append("    accountTypeCd: ").append(toIndentedString(accountTypeCd)).append("\n");
    sb.append("    contractAcceptFlg: ").append(toIndentedString(contractAcceptFlg)).append("\n");
    sb.append("    authorizeEmail: ").append(toIndentedString(authorizeEmail)).append("\n");
    sb.append("    languageCd: ").append(toIndentedString(languageCd)).append("\n");
    sb.append("    sponsor: ").append(toIndentedString(sponsor)).append("\n");
    sb.append("    formerAccount: ").append(toIndentedString(formerAccount)).append("\n");
    sb.append("    primaryPartyIndex: ").append(toIndentedString(primaryPartyIndex)).append("\n");
    sb.append("    aboEntryDate: ").append(toIndentedString(aboEntryDate)).append("\n");
    sb.append("    bank: ").append(toIndentedString(bank)).append("\n");
    sb.append("    party: ").append(toIndentedString(party)).append("\n");
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

