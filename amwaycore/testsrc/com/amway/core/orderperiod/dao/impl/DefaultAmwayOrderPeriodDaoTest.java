/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *  
 */
package com.amway.core.orderperiod.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.basecommerce.util.BaseCommerceBaseTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.site.impl.DefaultBaseSiteService;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import com.amway.core.model.AmwayOrderPeriodModel;


@IntegrationTest
public class DefaultAmwayOrderPeriodDaoTest extends BaseCommerceBaseTest
{

	@Resource
	private DefaultAmwayOrderPeriodDao defaultAmwayOrderPeriodDao;

	@Resource
	private DefaultBaseSiteService defaultBaseSiteService;


	/**
	 * see ACC-6734
	 *
	 * @throws ImpExException
	 */
	@Test
	public void shouldGetOrderPeriods() throws ImpExException
	{
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/orderPeriodDaoTestData.csv", "windows-1252");
		final BaseSiteModel site = defaultBaseSiteService.getBaseSiteForUID("storetemplate");
		Assert.assertNotNull(site);
		final List<AmwayOrderPeriodModel> orderPeriods = defaultAmwayOrderPeriodDao.findActiveOrderPeriods(Arrays.asList(site));
		Assert.assertTrue(CollectionUtils.isNotEmpty(orderPeriods));
	}
}
