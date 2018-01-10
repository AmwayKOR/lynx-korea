package com.amway.core.user.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.mockito.BDDMockito.given;


@IntegrationTest
public class DefaultAmwayUserServiceIntegrationTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultAmwayUserService defaultAmwayUserService;

	private static final String PARTY_ID = "340028";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");

	}

	@Test
	public void testGetCustomerByPartyId()
	{
		final CustomerModel testCustomer = defaultAmwayUserService.getCustomerByPartyId(PARTY_ID);
		Assert.assertNotNull(testCustomer);
		Assert.assertEquals(PARTY_ID, testCustomer.getCustomerID());

	}

}
