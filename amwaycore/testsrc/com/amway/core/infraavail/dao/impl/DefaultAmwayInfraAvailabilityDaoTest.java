/**
 *
 */
package com.amway.core.infraavail.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.store.BaseStoreModel;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.model.AmwayInfraAvailabilityModel;


@IntegrationTest
public class DefaultAmwayInfraAvailabilityDaoTest extends ServicelayerTransactionalTest
{

	@Resource
	private DefaultAmwayInfraAvailabilityDao defaultAmwayInfraAvailabilityDao;
	private static final String INFRAAVAILABILITY_CODE = "lynx_dms_services_test";
	private static final String BASE_STORE_UID = "defaultstore";
	private BaseStoreModel store;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/amwayInfraAvailabilityTestData.csv", "windows-1252");

	}

	/**
	 * Test method for
	 * {@link com.amway.core.infraavail.dao.impl.DefaultAmwayInfraAvailabilityDao#findInfraAvail(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testFindInfraAvail()
	{
		final List<AmwayInfraAvailabilityModel> infraModelList = defaultAmwayInfraAvailabilityDao
				.findInfraAvail(INFRAAVAILABILITY_CODE, BASE_STORE_UID);
		Assert.assertNotNull(infraModelList);
		Assert.assertEquals(1, CollectionUtils.size(infraModelList));
	}

}
