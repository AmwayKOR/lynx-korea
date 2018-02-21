package com.amway.integration.cis.dms.partyandnameservice.mock.impl;

import com.amway.core.account.service.PartyProfileAndNameService;
import com.amway.core.dms.data.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Mock Service for party profile information.
 */
public class MockPartyProfileAndNameServiceImpl implements PartyProfileAndNameService
{
	@Override
	public PartyNameDetailResponseData getPartyProfileAndNameService(
			final PartyProfileAndNameInputRequestData partyInputRequestData)
	{
		final PartyNameDetailResponseData out = new PartyNameDetailResponseData();
		final PartyMasterNameDtlListData dtlListData = new PartyMasterNameDtlListData();
		final PartyPersonalDetailsRequestData partyMstData = new PartyPersonalDetailsRequestData();
		partyMstData.setBirthdate("22/10/2015");
		partyMstData.setBirthCountryCd("Brazil");
		partyMstData.setGenderCd("Male");
		partyMstData.setLanguageCd("Pt");
		partyMstData.setMaritalStatusCd("Single");
		partyMstData.setPartyId("12345");
		partyMstData.setPartyTypeCd("54321");
		partyMstData.setPrimaryOnAccount("test@abo.com");
		partyMstData.setUserId("517");
		partyMstData.setUserPasswd("99999999999");
		partyMstData.setUserPin("9876");
		partyMstData.setStatusCd("Active");
		dtlListData.setPartyMstData(partyMstData);

		final PartyNameDetailsRequestData nameListData = new PartyNameDetailsRequestData();
		nameListData.setLanguageCd("Brazil");
		nameListData.setPartyId("12345");
		nameListData.setPersonNameTypeCd("ABO user");
		nameListData.setPreferredName("new user");
		final LatinNameData latinNameData = new LatinNameData();
		final List<PartyNameDetailsRequestData> partyNameList = new ArrayList<>();
		latinNameData.setFamilyName("Family abo");
		latinNameData.setGivenName("test user");
		latinNameData.setMiddleName("john");
		latinNameData.setSuffix("demo");
		latinNameData.setTitle("Mr.");
		nameListData.setLatinNameData(latinNameData);


		final LocaleNameData localeNameData = new LocaleNameData();
		localeNameData.setFamilyName("locale family");
		localeNameData.setGivenName("locale name");
		localeNameData.setMiddleName("locale middle");
		localeNameData.setSuffix("Locale demo");
		localeNameData.setTitle("Mr..");
		nameListData.setLocaleNameData(localeNameData);
		partyNameList.add(nameListData);
		dtlListData.setPartyNameList(partyNameList);
		out.setReturnCd(1);
		out.setReturnMessage("Success");

		out.setPartyMasterNameDtlList(dtlListData);
		return out;
	}
}
