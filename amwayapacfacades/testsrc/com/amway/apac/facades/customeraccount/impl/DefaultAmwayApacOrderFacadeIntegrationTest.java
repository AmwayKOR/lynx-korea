/**
 *
 */
package com.amway.apac.facades.customeraccount.impl;

import static org.junit.Assert.assertNotNull;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Aaron Yong
 *
 */
@IntegrationTest
public class DefaultAmwayApacOrderFacadeIntegrationTest extends ServicelayerTransactionalTest
{

	private static final String TEST_BASESITE_UID = "testSite";

	@Resource
	private DefaultAmwayApacOrderFacade defaultAmwayApacOrderFacade;
	@Resource
	private BaseSiteService baseSiteService;
	@Resource
	private UserService userService;
	@Resource
	private BaseStoreService baseStoreService;
	@Resource
	private CatalogVersionService catalogVersionService;
	@Resource
	private CartService cartService;

	private BaseStoreModel baseStore;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceCart.csv", "utf-8");
		baseSiteService.setCurrentBaseSite(baseSiteService.getBaseSiteForUID(TEST_BASESITE_UID), false);

		final CatalogVersionModel catalogVersionModel = catalogVersionService.getCatalogVersion("testCatalog", "Online");
		assertNotNull(catalogVersionModel);
		catalogVersionService.setSessionCatalogVersions(Collections.singletonList(catalogVersionModel));

		final UserModel user = userService.getUserForUID("ahertz");
		userService.setCurrentUser(user);
		cartService.setSessionCart(user.getCarts().iterator().next());

		baseStore = baseStoreService.getBaseStoreForUid("testStore");
	}

	@Test
	public void testGetCustomerOrderCounts()
	{
		Assert.assertEquals(new Integer(0), defaultAmwayApacOrderFacade.getCustomerOrderCounts());
	}

	@Test
	public void testGetPagedOrderHistoryByFilterAndSearch()
	{
		//		given(orderHistoryConverter.convert(orderModel)).willReturn(orderHistoryData);
		//		final CustomerModel customerModel = new CustomerModel();
		//		given(userService.getCurrentUser()).willReturn(customerModel);
		//		final BaseStoreModel baseStoreModel = new BaseStoreModel();
		//		baseStoreModel.setUid("baseStoreModel");
		//		given(baseStoreService.getCurrentBaseStore()).willReturn(baseStoreModel);
		//		given(customerAccountService.getOrderList(customerModel, baseStoreModel, null)).willReturn(Collections.EMPTY_LIST);
		//
		final PageableData pageabledata = new PageableData();

		Assert.assertEquals(Collections.EMPTY_LIST.size(),
				defaultAmwayApacOrderFacade.getPagedOrderHistoryByFilterAndSearch(pageabledata, null, null).getResults().size());

	}

}
