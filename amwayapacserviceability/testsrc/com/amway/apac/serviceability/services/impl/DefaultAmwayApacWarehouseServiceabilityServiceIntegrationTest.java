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


@IntegrationTest
public class DefaultAmwayApacWarehouseServiceabilityServiceIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_SITE = "amwayapac";
	private final String postalCode = "8803";
	private final String postalCodeZero = "0000";
	private final String postalCodeWithNoWarehouse = "8800";
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

	@Test
	public void testGetServiceableWareHouse()
	{
		final WarehouseModel warehouse = amwayApacWarehouseServiceabilityService.getServiceableWareHouse(postalCode, baseSite);
		Assert.assertNotNull("Warehouse is null", warehouse);
		Assert.assertFalse("Warehouse does not have stock", warehouse.getStockLevels().isEmpty());
	}

	/**
	 * GetServiceableWareHouse with postal code
	 */
	@Test
	public void testGetServiceableWareHouseWithPostalCodeZero()
	{
		final WarehouseModel warehouse = amwayApacWarehouseServiceabilityService.getServiceableWareHouse(postalCodeZero, baseSite);
		Assert.assertNull("Warehouse is not null", warehouse);
	}

	@Test
	public void testIsPostalCodeServiceableForCurrentBaseSite()
	{
		final Boolean isServiceable = amwayApacWarehouseServiceabilityService.isPostalCodeServiceableForCurrentBaseSite(postalCode);
		Assert.assertTrue("Warehouse is not Serviceable", isServiceable.booleanValue());
	}

	/**
	 * Test IsPostalCodeServiceableForCurrentBaseSite without a serviceable warehouse
	 */
	@Test
	public void testIsPostalCodeServiceableForCurrentBaseSiteWithNoServiceableWarehouse()
	{
		final Boolean isServiceable = amwayApacWarehouseServiceabilityService
				.isPostalCodeServiceableForCurrentBaseSite(postalCodeWithNoWarehouse);
		Assert.assertFalse("Warehouse is Serviceable", isServiceable.booleanValue());
	}
}
