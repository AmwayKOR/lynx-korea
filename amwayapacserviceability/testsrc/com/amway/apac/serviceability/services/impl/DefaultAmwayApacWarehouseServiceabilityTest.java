package com.amway.apac.serviceability.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.site.BaseSiteService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.serviceability.services.AmwayApacWarehouseServiceabilityService;


/**
 * 
 * @author Shubham Goyal
 */

@IntegrationTest
public class DefaultAmwayApacWarehouseServiceabilityTest extends ServicelayerTransactionalTest
{
	private static final String TEST_SITE = "testSite";
	private static final String POSTAL_CODE = "151001";
	private static final String NON_SERVICEABLE_POSTALCODE = "8800";
	private BaseSiteModel baseSite;

	@Resource(name = "amwayApacWarehouseServiceabilityService")
	private AmwayApacWarehouseServiceabilityService amwayApacWarehouseServiceabilityService;

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

	/**
	 * @throws ImpExException
	 */
	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapacserviceability/test/testWarehouseServiceabilityService.impex", "utf-8");
		baseSite = baseSiteService.getBaseSiteForUID(TEST_SITE);
		baseSiteService.setCurrentBaseSite(baseSite, true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetServiceableWareHouseForNullBasesite()
	{
		amwayApacWarehouseServiceabilityService.getServiceableWareHouse(POSTAL_CODE, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetServiceableWareHouseForNullPostcode()
	{
		amwayApacWarehouseServiceabilityService.getServiceableWareHouse(null, baseSite);
	}

	@Test
	public void testGetServiceableWareHouse()
	{
		final WarehouseModel warehouse = amwayApacWarehouseServiceabilityService.getServiceableWareHouse(POSTAL_CODE, baseSite);
		Assert.assertNotNull(warehouse);
		Assert.assertEquals("Warehouse South", warehouse.getName());
	}

	@Test
	public void testGetServiceableWareHouseWithNoServiceableWarehouse()
	{
		final WarehouseModel warehouse = amwayApacWarehouseServiceabilityService.getServiceableWareHouse(NON_SERVICEABLE_POSTALCODE,
				baseSite);
		Assert.assertNull(warehouse);
	}

	@Test
	public void testIsPostalCodeServiceableForCurrentBaseSite()
	{
		final Boolean isServiceable = amwayApacWarehouseServiceabilityService
				.isPostalCodeServiceableForCurrentBaseSite(POSTAL_CODE);
		Assert.assertTrue(isServiceable.booleanValue());
	}

	@Test
	public void testIsPostalCodeServiceableForCurrentBaseSiteWithNoServiceableWarehouse()
	{
		final Boolean isServiceable = amwayApacWarehouseServiceabilityService
				.isPostalCodeServiceableForCurrentBaseSite(NON_SERVICEABLE_POSTALCODE);
		Assert.assertFalse(isServiceable.booleanValue());
	}
}
