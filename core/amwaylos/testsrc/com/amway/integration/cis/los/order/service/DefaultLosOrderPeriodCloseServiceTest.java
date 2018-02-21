/**
 *
 */
package com.amway.integration.cis.los.order.service;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.los.data.OrderPeriodCloseResultData;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.integration.cis.los.orderbonusservice.mock.impl.MockOrderPeriodClosedService;


@UnitTest
public class DefaultLosOrderPeriodCloseServiceTest extends ServicelayerTest
{

	private static final String AFFlI_NO = "170";

	private static final String BR = "BR";

	private static final String ERROR = "ERROR";

	@Resource(name = "mockOrderPeriodClosedService")
	private MockOrderPeriodClosedService defaultLosOrderPeriodCloseService;

	private AmwayOrderPeriodModel amwayOrderPeriodModel;
	private BaseStoreModel baseStoreModel;
	private BaseSiteModel baseSiteModel;
	private CountryModel countryModel;

	@Before
	public void setUp()
	{
		amwayOrderPeriodModel = new AmwayOrderPeriodModel();

		countryModel = new CountryModel();
		countryModel.setIsocode(BR);

		baseSiteModel = new BaseSiteModel();
		baseSiteModel.setDefaultCountry(countryModel);

		baseStoreModel = new BaseStoreModel();
		baseStoreModel.setAffiliateNumber(AFFlI_NO);
		baseStoreModel.setCmsSites(Collections.singletonList(baseSiteModel));
		amwayOrderPeriodModel.setStore(baseStoreModel);
		amwayOrderPeriodModel.setCode("201601");
	}

	@Test
	public void shouldCloseLosOrderPeriodTest()
	{
		final OrderPeriodCloseResultData responce = defaultLosOrderPeriodCloseService.process(amwayOrderPeriodModel);
		assertNotNull(responce);
		Assert.assertFalse(ERROR.equals(responce.getDecision()));
	}

}
