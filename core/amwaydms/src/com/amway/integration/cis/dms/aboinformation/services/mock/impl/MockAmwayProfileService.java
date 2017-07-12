package com.amway.integration.cis.dms.aboinformation.services.mock.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

import com.amway.core.data.AmwayProfileRequestData;
import com.amway.core.dms.data.AmwayProfileResponseData;
import com.amway.core.dms.service.DmsService;
import com.amway.integration.cis.dms.service.impl.AbstractDmsService;
import com.amway.integration.dms.services.AccountBalance;
import com.amway.integration.dms.services.AccountDetailOutput;
import com.amway.integration.dms.services.AccountFullResponse;
import com.amway.integration.dms.services.AmwayProfileOutput;
import com.amway.integration.dms.services.GetBlockPrivDetSrvcOutput;
import com.amway.integration.dms.services.PartyDetailsOutput;
import com.amway.integration.dms.services.PartyMasterServiceObject;
import com.amway.integration.dms.services.SponsorDetailResponse;
import com.amway.integration.dms.services.SubsrciptionSvcData;


/**
 * Mock service for amway profile information.
 */
public class MockAmwayProfileService
		extends AbstractDmsService<AmwayProfileResponseData, AmwayProfileRequestData, AmwayProfileOutput>
		implements DmsService<AmwayProfileRequestData, AmwayProfileResponseData>
{

	@Override
	public AmwayProfileResponseData process(final AmwayProfileRequestData requestData)
	{
		final Object input = getInputConverter().convert(requestData);
		return extractOutput(executeEvent(input));
	}

	@Override
	protected AmwayProfileResponseData createResultObject()
	{
		return new AmwayProfileResponseData();
	}

	@Override
	protected AmwayProfileResponseData createDefaultResult()
	{
		final AmwayProfileResponseData amwayProfileResponseData = createResultObject();
		amwayProfileResponseData.setReturnCd(-1);
		amwayProfileResponseData.setReturnMessage("Failed to get amway profile information");
		return amwayProfileResponseData;
	}

	protected String convertToJAXBString(final String elementName, final String elementValue)
	{

			return StringUtils.isNotEmpty(elementValue) ? elementValue : StringUtils.EMPTY;

	}

	protected JAXBElement<PartyMasterServiceObject> convertToJAXBElement(final String requestEntity,
			final PartyMasterServiceObject responseEntity)
	{
		return new JAXBElement<PartyMasterServiceObject>(new QName(requestEntity), PartyMasterServiceObject.class, responseEntity);
	}

	protected JAXBElement<AccountFullResponse> convertToJAXBElementResponse(final String requestEntity,
			final AccountFullResponse responseEntity)
	{
		return new JAXBElement<AccountFullResponse>(new QName(requestEntity), AccountFullResponse.class, responseEntity);
	}

	protected JAXBElement<SponsorDetailResponse> convertToJAXBElementSponsorResponse(final String requestEntity,
			final SponsorDetailResponse responseEntity)
	{
		return new JAXBElement<SponsorDetailResponse>(new QName(requestEntity), SponsorDetailResponse.class, responseEntity);
	}

	@Override
	protected AmwayProfileOutput executeEvent(final Object input)
	{
		final AmwayProfileOutput response = new AmwayProfileOutput();


		/*final AccountFullResponse fullResponse = new AccountFullResponse();
		fullResponse.setAboEntryDate(convertToJAXBString("aboEntryDate", "2016-02-18T04:00:00-02:00"));
		fullResponse.setAboExpireDate(convertToJAXBString("aboExpireDate", "2026-12-31T04:00:00-02:00"));
		fullResponse.setAboNum(convertToJAXBString("aboNum", "3101005768"));
		fullResponse.setAccountCreditLimit(convertToJAXBString("accountCreditLimit", "12000"));
		fullResponse.setAccountCreditStatusCd(convertToJAXBString("accountCreditStatusCd", "AP"));
		fullResponse.setAccountId(convertToJAXBString("accountId", "64683885"));
		fullResponse.setAccountMissingInfoFlg(convertToJAXBString("accountMissingInfoFlg", "N"));
		fullResponse.setAccountName(convertToJAXBString("accountName", "Amway, Test"));
		fullResponse.setAccountSubTypeCd(convertToJAXBString("accountSubTypeCd", "BusinessOwner"));
		fullResponse.setAccountTypeCd(convertToJAXBString("accountTypeCd", "AmwayBusiness"));
		fullResponse.setBlackListFlg(convertToJAXBString("blackListFlg", "N"));
		fullResponse.setBusEntityNum(convertToJAXBString("busEntityNum", "310"));
		fullResponse.setCntryCd(convertToJAXBString("cntryCd", "BR"));
		fullResponse.setCompany(convertToJAXBString("company", null));
		fullResponse.setCurrencyCd(convertToJAXBString("currencyCd", "BRL"));
		fullResponse.setIncludeExcludeFlg(convertToJAXBString("includeExcludeFlg", "N"));
		fullResponse.setLanguageCd(convertToJAXBString("languageCd", "pt"));
		fullResponse.setLglEntityType(convertToJAXBString("lglEntityType", "NonLegalEntity"));
		fullResponse.setLoaCd(convertToJAXBString("loaCd", "01210"));
		fullResponse.setOrderCreditLimit(convertToJAXBString("orderCreditLimit", "0"));
		fullResponse.setRegdlntlPrmyBusflg(convertToJAXBString("regdlntlPrmyBusflg", "N"));
		fullResponse.setRegdlntlSpnAffNo(convertToJAXBString("regdlntlSpnAffNo", null));
		fullResponse.setRegdlntlSpnIboNo(convertToJAXBString("regdlntlSpnIboNo", null));
		fullResponse.setRegdSpnIboNo(convertToJAXBString("regdSpnIboNo", "7001002523"));
		fullResponse.setSalesPlanAff(convertToJAXBString("salesPlanAff", "170"));
		fullResponse.setSegmentCd(convertToJAXBString("segmentCd", "NonGroupLeader"));
		fullResponse.setStatusCd(convertToJAXBString("statusCd", "Valid"));
		fullResponse.setUserId(convertToJAXBString("userId", null));
		fullResponse.setUserPin(convertToJAXBString("userPin", null));

		final List<AccountFullResponse> accountFullResponseList = new ArrayList<AccountFullResponse>();
		accountFullResponseList.add(fullResponse);

		final AccountBalance accountBalance = new AccountBalance();
		accountBalance.setBalanceAmount(convertToJAXBString("balanceAmount", "50.12"));
		accountBalance.setBalanceTypeCd(convertToJAXBString("balanceTypeCd", "Credit"));
		accountBalance.setCurrencyCd(convertToJAXBString("currencyCd", "BRL"));
		accountBalance.setInstrumentId(convertToJAXBString("instrumentId", "0"));

		final List<AccountBalance> accountBalanceList = new ArrayList<AccountBalance>();
		accountBalanceList.add(accountBalance);



		final AccountDetailOutput accountDetailOutput = new AccountDetailOutput();
		accountDetailOutput.setBankAcctHolderName(convertToJAXBString("bankAcctHolderName", "sindhura vaddi"));
		accountDetailOutput.setBankAcctId(convertToJAXBString("bankAcctHolderName", "sindhura vaddi"));
		accountDetailOutput.setBankAcctName(convertToJAXBString("bankAcctName", null));
		accountDetailOutput.setBankAcctNum(convertToJAXBString("bankAcctNum", "654634364312"));
		accountDetailOutput.setBankAcctTypeCd(convertToJAXBString("bankAcctTypeCd", "Checking"));
		accountDetailOutput.setBankBranchCode(convertToJAXBString("bankBranchCode", "6565"));
		accountDetailOutput.setBankBranchName(convertToJAXBString("bankBranchName", "MATRIZ"));
		accountDetailOutput.setCurrencyCd(convertToJAXBString("currencyCd", "BRL"));
		accountDetailOutput.setEffectiveEndDate(convertToJAXBString("effectiveEndDate", null));
		accountDetailOutput.setEffectiveStartDate(convertToJAXBString("effectiveStartDate", "2016-01-05T13:06:06-02:00"));
		accountDetailOutput.setIssuingBankId(convertToJAXBString("issuingBankId", "546"));
		accountDetailOutput.setIssuingBankName(convertToJAXBString("issuingBankName", "Banco Credit Agricole Brasil S.A."));
		accountDetailOutput.setPrimaryRoutingNum(convertToJAXBString("primaryRoutingNum", null));
		accountDetailOutput.setSecondaryRoutingNum(convertToJAXBString("secondaryRoutingNum", null));


		final GetBlockPrivDetSrvcOutput blockOutput = new GetBlockPrivDetSrvcOutput();
		blockOutput.setAboNum(convertToJAXBString("aboNum", "3101005768"));
		blockOutput.setBlockPrivTypeId(convertToJAXBString("blockPrivTypeId", "BlockBonus"));
		blockOutput.setEffectiveDate(convertToJAXBString("effectiveDate", "2016-01-25T17:35:53-02:00"));
		blockOutput.setExpirationDate(convertToJAXBString("expirationDate", null));
		blockOutput.setIsBlockFlag(convertToJAXBString("isBlockFlag", "Y"));
		blockOutput.setSalesPlanAff(convertToJAXBString("salesPlanAff", "170"));

		final List<GetBlockPrivDetSrvcOutput> blockPrivDetSrvcOutputList = new ArrayList<GetBlockPrivDetSrvcOutput>();
		blockPrivDetSrvcOutputList.add(blockOutput);



		final PartyMasterServiceObject partyMasterServiceObject = new PartyMasterServiceObject();
		partyMasterServiceObject.setBirthCountryCd(convertToJAXBString("birthCountryCd", "BR"));
		partyMasterServiceObject.setBirthdate(convertToJAXBString("birthdate", "1990-02-08T00:00:00-02:00"));
		partyMasterServiceObject.setEducationTypeCd(convertToJAXBString("educationTypeCd", null));
		partyMasterServiceObject.setEthnicCd(convertToJAXBString("ethnicCd", "Latin"));
		partyMasterServiceObject.setGenderCd(convertToJAXBString("genderCd", null));
		partyMasterServiceObject.setLanguageCd(convertToJAXBString("languageCd", "pt"));
		partyMasterServiceObject.setMaritalStatusCd(convertToJAXBString("maritalStatusCd", null));
		partyMasterServiceObject.setPartyId(convertToJAXBString("partyId", "63729729"));
		partyMasterServiceObject.setPartyIsMinorFlg(convertToJAXBString("partyIsMinorFlg", "N"));
		partyMasterServiceObject.setPartyTypeCd(convertToJAXBString("partyTypeCd", "Person"));
		partyMasterServiceObject.setPrimaryOnAccount(convertToJAXBString("primaryOnAccount", "1"));
		partyMasterServiceObject.setProfessionCd(convertToJAXBString("professionCd", null));
		partyMasterServiceObject.setRelationShipToPrimaryPartyCd(convertToJAXBString("relationShipToPrimaryPartyCd", null));
		partyMasterServiceObject.setRoleCd(convertToJAXBString("roleCd", "BusinessOwner"));
		partyMasterServiceObject.setStatusCd(convertToJAXBString("statusCd", null));
		partyMasterServiceObject.setTzOffset(convertToJAXBString("tzOffset", null));
		partyMasterServiceObject.setUserId(convertToJAXBString("userId", null));
		partyMasterServiceObject.setUserPasswd(convertToJAXBString("userPasswd", null));
		partyMasterServiceObject.setUserPin(convertToJAXBString("userPin", "0"));


		final PartyDetailsOutput partyDetailsOutput = new PartyDetailsOutput();
		partyDetailsOutput.setPartyMst(partyMasterServiceObject);


		final SponsorDetailResponse sponsorDetailResponse = new SponsorDetailResponse();
		sponsorDetailResponse.setSponsorABONo(convertToJAXBString("sponsorABONo", null));
		sponsorDetailResponse.setSponsorName(convertToJAXBString("sponsorName", null));
		sponsorDetailResponse.setSponsorEmail(convertToJAXBString("sponsorEmail", null));
		sponsorDetailResponse.setSponsorPhone(convertToJAXBString("sponsorPhone", null));

		final List<SponsorDetailResponse> sponsorDetailResponseList = new ArrayList<SponsorDetailResponse>();
		sponsorDetailResponseList.add(sponsorDetailResponse);


		final SubsrciptionSvcData subsrciptionSvcData = new SubsrciptionSvcData();
		subsrciptionSvcData.setCancelCd(convertToJAXBString("cancelCd", "Y"));
		subsrciptionSvcData.setIsPublicationCd(convertToJAXBString("isPublicationCd", "N"));
		subsrciptionSvcData.setLanguageCd(convertToJAXBString("languageCd", "pt"));
		subsrciptionSvcData.setPauseCd(convertToJAXBString("pauseCd", "N"));
		subsrciptionSvcData.setRestoreCd(convertToJAXBString("restoreCd", "N"));
		subsrciptionSvcData.setSubscribeFlag(convertToJAXBString("subscribeFlag", "N"));
		subsrciptionSvcData.setSubscriptionId(convertToJAXBString("subscriptionId", "1"));
		subsrciptionSvcData.setSubscriptionName(convertToJAXBString("subscriptionName", "Amway Magazine"));

		final List<SubsrciptionSvcData> subsrciptionSvcDataList = new ArrayList<SubsrciptionSvcData>();
		subsrciptionSvcDataList.add(subsrciptionSvcData);


		response.setAccountMasterDetails(convertToJAXBElementResponse("accountFullResponseList", fullResponse));
		response.setSponsorDetails(convertToJAXBElementSponsorResponse("sponsorDetailResponseList", sponsorDetailResponse));
		response.getAccountBalance().add(accountBalance);
		response.getBankAccountDetailList().add(accountDetailOutput);
		response.getBlockPrivList().add(blockOutput);
		response.getPartyDetailList().add(partyDetailsOutput);
		response.getSubscriptionList().add(subsrciptionSvcData);*/
		response.setReturnCd(1);
		response.setReturnMessage("Success");
		return response;
		//return null;

	}
}
