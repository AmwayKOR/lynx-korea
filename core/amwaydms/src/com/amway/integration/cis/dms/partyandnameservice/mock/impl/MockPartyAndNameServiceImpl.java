/**
 *
 */
package com.amway.integration.cis.dms.partyandnameservice.mock.impl;

import java.util.Date;

import com.amway.core.dms.party.data.AccountInfoData;
import com.amway.core.dms.party.data.PartyData;
import com.amway.core.dms.service.DmsService;


/**
 * Mock Service for party details.
 */
public class MockPartyAndNameServiceImpl implements DmsService<AccountInfoData, PartyData>
{
	@Override
	public PartyData process(final AccountInfoData accountInfo)
	{
		final PartyData partyDataDetails = new PartyData();
		partyDataDetails.setDateOfBirth(new Date());
		partyDataDetails.setGender("Male");
		partyDataDetails.setMaritalStatus("Single");
		partyDataDetails.setTaxId("abc123");
		return partyDataDetails;

	}

}
