/**
 *
 */
package com.amway.integration.cis.dms.partypreferenceservice.impl;

import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.data.CommonRequestFieldsData;
import com.amway.core.dms.data.PreferenceListResponse;
import com.amway.integration.cis.dms.partypreferenceservice.mock.impl.MockGetPreferenceListService;


/**
 * @author admin
 */
public class DefaultGetPreferenceListServiceTest extends ServicelayerTransactionalTest
{

	@Resource(name = "mockGetPreferenceListService")
	private MockGetPreferenceListService defaultGetPreferenceListService;

	private CommonRequestFieldsData commonRequestFieldData;


	@Before
	public void setUp()
	{
		commonRequestFieldData = new CommonRequestFieldsData();
		commonRequestFieldData.setAboNum("7001002605");
		commonRequestFieldData.setSalesPlanAff("170");
		commonRequestFieldData.setPartyId("151058196");
	}


	@Test
	public void shouldGetDmsPreferenceListTest()
	{
		final PreferenceListResponse responce = defaultGetPreferenceListService.process(commonRequestFieldData);
		Assert.assertTrue(responce.getReturnCd() == 1);
	}

}
