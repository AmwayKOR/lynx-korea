/**
 *
 */
package com.amway.integration.cis.dms.partypreferenceservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.PartyPreferenceData;
import com.amway.integration.cis.dms.partypreferenceservice.mock.impl.MockUpdatePartyPreferenceService;


/**
 * @author admin
 */
public class DefaultUpdatePartyPreferenceServiceTest extends ServicelayerTransactionalTest
{
	@Resource(name = "mockUpdatePartyPreferenceService")
	private MockUpdatePartyPreferenceService defaultUpdatePartyPreferenceService;

	private PartyPreferenceData partyPreferenceData;


	@Before
	public void setUp()
	{
		partyPreferenceData = new PartyPreferenceData();
		partyPreferenceData.setAboNum("7001002605");
		partyPreferenceData.setSalesPlanAff("170");
		partyPreferenceData.setPartyId("151058196");

		partyPreferenceData.setPreferenceId("Confidentiality");
		partyPreferenceData.setIsTrueFlag("0");
		partyPreferenceData.setPreferenceNote("1");
		partyPreferenceData.setPreferenceValueCd("Private");
		partyPreferenceData.setPreferenceValueName("Private");
		partyPreferenceData.setPreferenceListId("Private");
	}


	@Test
	public void shouldGetDmsUpdatePartyPreferenceTest()
	{
		final CommonResponseFieldsData response = defaultUpdatePartyPreferenceService.process(partyPreferenceData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}


}
