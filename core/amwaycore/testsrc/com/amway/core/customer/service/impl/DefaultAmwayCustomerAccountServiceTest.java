/**
 *
 */
package com.amway.core.customer.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.enums.AmwayCartType;


@IntegrationTest
public class DefaultAmwayCustomerAccountServiceTest extends ServicelayerTransactionalTest
{
	@Resource
	private DefaultAmwayCustomerAccountService defaultAmwayCustomerAccountService;
	@Resource
	private UserService userService;
	@Resource
	private BaseStoreService baseStoreService;
	private CustomerModel customer;
	private BaseStoreModel store;
	private static final String CUSTOMER_UID = "amway_party_test_1";
	private static final String BASE_STORE_UID = "defaultstore";
	private static final String ORDER_CODE = "certainDay01_date_01";
	public static final int MAX_PAGE_LIMIT = 100;

	public static enum ShowMode
	{
		Page, All
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwaycore/test/common.csv", "windows-1252");
		importCsv("/amwaycore/test/siteTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/productTestData.csv", "windows-1252");
		importCsv("/amwaycore/test/orderTestData.csv", "windows-1252");
		customer = (CustomerModel) userService.getUserForUID(CUSTOMER_UID);
		store = baseStoreService.getBaseStoreForUid(BASE_STORE_UID);

	}

	/**
	 * Test method for
	 * {@link com.amway.core.customer.service.impl.DefaultAmwayCustomerAccountService#getOrders(de.hybris.platform.core.model.user.CustomerModel, de.hybris.platform.store.BaseStoreModel, de.hybris.platform.commerceservices.search.pagedata.PageableData, com.amway.core.enums.AmwayCartType)}
	 * .
	 */
	@Test
	public void testGetOrders()
	{
		final PageableData pageableData = createPageableData(0, 30, null, ShowMode.Page);
		final SearchPageData<OrderModel> searchData = defaultAmwayCustomerAccountService
				.getOrders(customer, store, pageableData, AmwayCartType.WEB);
		Assert.assertNotNull(searchData);
		Assert.assertTrue(CollectionUtils.isNotEmpty(searchData.getResults()));
		Assert.assertEquals(4, CollectionUtils.size(searchData.getResults()));
	}

	/**
	 * Test method for
	 * {@link com.amway.core.customer.service.impl.DefaultAmwayCustomerAccountService#getOrderForCodeAndType(de.hybris.platform.core.model.user.CustomerModel, java.lang.String, de.hybris.platform.store.BaseStoreModel, com.amway.core.enums.AmwayCartType)}
	 * .
	 */
	@Test
	public void testGetOrderForCodeAndType()
	{
		final OrderModel order = defaultAmwayCustomerAccountService
				.getOrderForCodeAndType(customer, ORDER_CODE, store, AmwayCartType.WEB);
		Assert.assertNotNull(order);
		Assert.assertEquals(ORDER_CODE, order.getCode());
	}

	/**
	 * @param pageNumber
	 * @param pageSize
	 * @param sortCode
	 * @param showMode
	 * @return PageableData
	 */
	protected PageableData createPageableData(final int pageNumber, final int pageSize, final String sortCode,
			final ShowMode showMode)
	{
		final PageableData pageableData = new PageableData();
		pageableData.setCurrentPage(pageNumber);
		pageableData.setSort(sortCode);

		if (ShowMode.All == showMode)
		{
			pageableData.setPageSize(MAX_PAGE_LIMIT);
		}
		else
		{
			pageableData.setPageSize(pageSize);
		}
		return pageableData;
	}

}
