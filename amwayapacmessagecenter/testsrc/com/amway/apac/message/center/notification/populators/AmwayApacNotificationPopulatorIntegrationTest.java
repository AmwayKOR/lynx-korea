/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.apac.message.center.notification.populators;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.message.center.model.AmwayNotificationModel;
import com.amway.apac.message.center.notification.AmwayApacNotificationData;
import com.amway.apac.message.center.notification.services.AmwayApacNotificationService;


/**
 * @author avnishalaugh
 *
 */
@IntegrationTest
public class AmwayApacNotificationPopulatorIntegrationTest extends ServicelayerTransactionalTest
{
	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private UserService userService;
	@Resource(name = "modelService")
	private ModelService modelService;

	@Resource(name = "amwayApacNotificationService")
	private AmwayApacNotificationService amwayApacNotificationService;

	/**
	 * The populator for test cases
	 */
	@Resource(name = "amwayApacNotificationPopulator")
	private AmwayApacNotificationPopulator amwayApacNotificationPopulator;


	/**
	 * Notification Long-Description string constant
	 */
	private static final String LONG_DESC = "This notification tests for the diamond and above classification level";

	/**
	 * Notification Short-Description string constant
	 */
	private static final String SHORT_DESC = "Diamond Test Notification";

	/**
	 * Notification code string constant
	 */
	private static final String CODE = "diamond_notification";

	/**
	 * AmwayApacNotificationData Data mock
	 */
	private AmwayApacNotificationData amwayApacNotificationData;

	@Before
	public void prepare() throws ImpExException
	{
		amwayApacNotificationData = new AmwayApacNotificationData();

		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));
	}

	/**
	 * Test for method
	 * {@link AmwayApacNotificationPopulator#populate(AmwayNotificationModel, AmwayApacNotificationData)}. Testing with
	 * null source
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullSource()
	{
		amwayApacNotificationPopulator.populate(null, amwayApacNotificationData);
	}

	/**
	 * Test for method
	 * {@link AmwayApacNotificationPopulator#populate(AmwayNotificationModel, AmwayApacNotificationData)}. Testing with
	 * null target
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullTarget()
	{

		final AmwayNotificationModel amwayNotificationModel = amwayApacNotificationService.getNotificationByCode(CODE);
		amwayApacNotificationPopulator.populate(amwayNotificationModel, null);
	}

	/**
	 * Test for method
	 * {@link AmwayApacNotificationPopulator#populate(AmwayNotificationModel, AmwayApacNotificationData)}. Testing with
	 * null target
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullSourceTarget()
	{
		amwayApacNotificationPopulator.populate(null, null);
	}

	/**
	 * Test for method {@link AmwayApacNotificationPopulator#populate(AmwayNotificationModel, AmwayApacNotificationData)}
	 */
	@Test
	public void testPopulate()
	{
		final AmwayNotificationModel amwayNotificationModel = amwayApacNotificationService.getNotificationByCode(CODE);
		amwayApacNotificationPopulator.populate(amwayNotificationModel, amwayApacNotificationData);

		Assert.assertNotNull(amwayApacNotificationData.getLongDescription());
		Assert.assertEquals(amwayApacNotificationData.getLongDescription(), LONG_DESC);

		Assert.assertNotNull(amwayApacNotificationData.getShortDescription());
		Assert.assertEquals(amwayApacNotificationData.getShortDescription(), SHORT_DESC);

		Assert.assertNotNull(amwayApacNotificationData.getCode());
		Assert.assertEquals(amwayApacNotificationData.getCode(), CODE);

	}

}
