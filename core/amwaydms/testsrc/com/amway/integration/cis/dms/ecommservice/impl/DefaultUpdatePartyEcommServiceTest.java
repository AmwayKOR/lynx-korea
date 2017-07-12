/**
 *
 */
package com.amway.integration.cis.dms.ecommservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.AddUpdatePartyEcommRequestData;
import com.amway.core.dms.data.PartyEcommRequestData;
import com.amway.core.dms.data.UsageRequestData;
import com.amway.integration.cis.dms.ecommservice.mock.impl.MockUpdatePartyEcommService;
import com.hybris.cis.common.utils.StringUtils;


public class DefaultUpdatePartyEcommServiceTest extends ServicelayerTest
{

	private static final String PARTY_ID = "171956";

	@Resource(name = "mockUpdatePartyEcommService")
	private MockUpdatePartyEcommService defaultUpdatePartyEcommService;

	private AddUpdatePartyEcommRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new AddUpdatePartyEcommRequestData();
		final PartyEcommRequestData partyEcommData = new PartyEcommRequestData();

		partyEcommData.setPartyId(PARTY_ID);
		partyEcommData.setEcommAddr("comu@comu.com.br");
		partyEcommData.setEcommTypeCd("Email");
		partyEcommData.setEcommName(StringUtils.EMPTY);
		partyEcommData.setEcommTypeName(StringUtils.EMPTY);
		partyEcommData.setStatusCd("Valid");
		partyEcommData.setAllowForLogIn("N");
		partyEcommData.setContactId("1203314");
		partyEcommData.setContactPointName("BusinessEmail");
		partyEcommData.setContactPointTypeCd("BusinessEmail");

		final UsageRequestData usageData = new UsageRequestData();
		usageData.setContactPointPurposeCd("GeneralPurpose");
		usageData.setPrimaryFlag("Y");
		partyEcommData.setUsageData(Collections.singletonList(usageData));

		requestData.setPartyEcommData(Collections.singletonList(partyEcommData));
	}

	@Test
	public void shouldUpdatePartyEcomm()
	{
		final CommonResponseFieldsData response = defaultUpdatePartyEcommService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}

