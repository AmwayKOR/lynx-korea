/**
 *
 */
package com.amway.core.infraavail.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.impl.DefaultBaseStoreService;
import de.hybris.platform.store.services.BaseStoreService;
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.model.AmwayInfraAvailabilityModel;


@IntegrationTest
public class DefaultAmwayInfraAvailabilityServiceTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultAmwayInfraAvailabilityService defaultAmwayInfraAvailabilityService;
	@Resource
	BaseStoreService baseStoreService;
	private static final String INFRA_AVAIL_CODE = "lynx_los_services_test";
	private static final String STORE_UID = "defaultstore";
	private BaseStoreModel store;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/amwayInfraAvailabilityTestData.csv", "windows-1252");
		store = baseStoreService.getBaseStoreForUid(STORE_UID);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.infraavail.service.impl.DefaultAmwayInfraAvailabilityService#getInfraAvailForStoreAndCode(java.lang.String, de.hybris.platform.store.BaseStoreModel)}
	 * .
	 */
	@Test
	public void testGetInfraAvailForStoreAndCode()
	{
		final AmwayInfraAvailabilityModel infraAvailabilityModel = defaultAmwayInfraAvailabilityService
				.getInfraAvailForStoreAndCode(INFRA_AVAIL_CODE, store);
		Assert.assertNotNull(infraAvailabilityModel);
		Assert.assertEquals(INFRA_AVAIL_CODE, infraAvailabilityModel.getCode());
	}

}
