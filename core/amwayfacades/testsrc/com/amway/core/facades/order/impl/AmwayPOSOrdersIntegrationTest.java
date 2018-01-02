/**
 * 
 */
package com.amway.core.facades.order.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.order.dao.CommerceOrderDao;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.ServicelayerTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.core.facades.order.AmwayOrderFacade;
import com.amway.core.facades.order.impl.DefaultAmwayOrderFacade;
import com.amway.facades.enumData.AmwayEnumDataFacade;
import com.amway.lynxfacades.PromotionDTO;
import com.amway.core.data.AmwayOrderSearchParameters;
import de.hybris.platform.cmsfacades.data.EnumData;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commerceservices.enums.SalesApplication;
/**
 * Integration Test.
 */
@IntegrationTest
public class AmwayPOSOrdersIntegrationTest extends ServicelayerTest
{
	private static final String TEST_ORDER_CODE = "amwayTestOrderCode";
	private static final String DEFAULT_DATE="Date";
	
	@InjectMocks
	private DefaultAmwayOrderFacade defaultAmwayOrderFacade = new DefaultAmwayOrderFacade();
	
	@InjectMocks
	AmwayOrderSearchParameters amwayOrderSearchParameters;
	
	@Resource
	CommerceOrderDao commerceOrderDao;
	
	@Resource
	private AmwayEnumDataFacade amwayEnumDataFacade;
	
	@Resource(name = "amwayOrderFacade")
	private AmwayOrderFacade amwayOrderFacade;
	
	@Before
	public void setUp() throws Exception
	{
		createCoreData();
		createDefaultCatalog();
		importCsv("/amwayfacades/test/orderSearchViewIntegrationTestData.csv", "windows-1252");
		
		MockitoAnnotations.initMocks(this);
		initializeSearchParamMock();
	}
	
	@Test
	public void testSalesApplication()
	{
		List<EnumData> enumData = amwayEnumDataFacade.getEnumValuesByClass(SalesApplication.class);
		Assert.assertTrue(CollectionUtils.isNotEmpty(enumData));
	}
	
	@Test
	public void testOrderList()
	{
		final PageableData pageableData =createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		Assert.assertNotNull(searchPageData);
	}
	
	private void initializeSearchParamMock()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderingABOID("amway_party_test_1");
		amwayOrderSearchParameters.setOrderDateFrom("2017-08-14 13:18:05.075");
		amwayOrderSearchParameters.setOrderDateTo("2017-09-14 13:18:05.075");
		amwayOrderSearchParameters.setOrderNo("amwayTestOrderCode");
	}
	
	protected PageableData createPageableData(final int currentPage, final int pageSize, final String sort)
	{
		final PageableData pageable = new PageableData();
		pageable.setCurrentPage(currentPage);
		pageable.setPageSize(pageSize);
		pageable.setSort(sort);
		return pageable;
	}
}
