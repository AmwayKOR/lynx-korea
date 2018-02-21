/**
 *
 */
package com.amway.core.order.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordercancel.model.OrderCancelRecordModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


@IntegrationTest
public class DefaultAmwayOrderServiceTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultAmwayOrderService defaultAmwayOrderService;
	@Resource
	private CustomerAccountService customerAccountService;
	@Resource
	private BaseStoreService baseStoreService;
	@Resource
	private SessionService sessionService;
	@Resource
	private BaseSiteService baseSiteService;
	private static final String ORDER_CODE = "certainDay01_date_01";
	private static final String CANCELED_ORDER_CODE = "certainDay03_date_02";
	private static final String STORE_UID = "defaultstore";
	private static final String SITE_UID = "storetemplate";
	private BaseStoreModel store;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/productTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/orderTestData.csv", "windows-1252");
		store = baseStoreService.getBaseStoreForUid(STORE_UID);
		sessionService.setAttribute("currentSite", baseSiteService.getBaseSiteForUID(SITE_UID));
	}


}
