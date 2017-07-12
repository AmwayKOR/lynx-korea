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
import com.amway.integration.cis.dms.ecommservice.mock.impl.MockDeletePartyEcommService;
import com.hybris.cis.common.utils.StringUtils;


public class DefaultDeletePartyEcommServiceTest extends ServicelayerTest
{

	@Resource(name = "mockDeletePartyEcommService")
	private MockDeletePartyEcommService defaultDeletePartyEcommService;

	private AddUpdatePartyEcommRequestData requestData;

	@Before
	public void setUp()
	{
		requestData = new AddUpdatePartyEcommRequestData();
		final PartyEcommRequestData partyEcommData = new PartyEcommRequestData();

		partyEcommData.setPartyId("171956");
		partyEcommData.setEcommAddr("comu@comu.com.br");
		partyEcommData.setEcommTypeCd("Email");
		partyEcommData.setEcommName(StringUtils.EMPTY);
		partyEcommData.setEcommTypeName(StringUtils.EMPTY);
		partyEcommData.setStatusCd("Valid");
		partyEcommData.setAllowForLogIn("N");
		partyEcommData.setContactId("1203314");
		partyEcommData.setContactPointName("BusinessEmail");
		partyEcommData.setContactPointTypeCd("BusinessEmail");

		requestData.setPartyEcommData(Collections.singletonList(partyEcommData));

	}

	@Test
	public void shouldUpdatePartyEcomm()
	{
		final CommonResponseFieldsData response = defaultDeletePartyEcommService.process(requestData);
		Assert.assertTrue(response.getReturnCd() == 1);
	}

}
