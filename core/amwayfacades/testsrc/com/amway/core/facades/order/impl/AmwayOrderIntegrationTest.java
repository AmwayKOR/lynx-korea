/**
 * 
 */
package com.amway.core.facades.order.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.apache.log4j.Logger;

import com.amway.core.facades.order.AmwayOrderFacade;
import com.amway.facades.enumData.AmwayEnumDataFacade;
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
public class AmwayOrderIntegrationTest extends ServicelayerTransactionalTest
{
	private static final Logger LOG = Logger.getLogger(AmwayOrderIntegrationTest.class);
	
	private static final String DEFAULT_DATE="Date";
	
	private static final String TEST_ORDER_CODE = "testOrder1";
	private static final String DATE_TO="2017-09-14 13:18:05";
	private static final String DATE_FROM="2017-09-13 13:18:05";
	private static final String ORDERING_ABO_ID="amway_user_test_1";
	private static final String ORDERING_ABO_NAME="testUser1";
	
	private static final String TEST_ORDER_CODE_2 = "testOrder2";
	private static final String DATE_TO_2="2017-09-14 13:18:05";
	private static final String DATE_FROM_2="2017-09-13 13:18:05";
	private static final String ORDERING_ABO_ID_2="amway_user_test_2";
	private static final String ORDERING_ABO_NAME_2="testUser2";
	
	private static final String SALES_CHANNEL="Web";
	private static final boolean SORTORDERASC=false;
	
	protected static final String BASE_RESOURCE_PATH = "/amwayfacades/test/order";
	
	AmwayOrderSearchParameters amwayOrderSearchParameters;
	
	@Resource
	private AmwayEnumDataFacade amwayEnumDataFacade;
	
	@Resource(name = "amwayOrderFacade")
	private AmwayOrderFacade amwayOrderFacade;
	
	@Before
	public void setUp() throws Exception
	{
		setUpBasicData();
		setUpProductData();
		setUpUserData();
		setUpOrderData();
		MockitoAnnotations.initMocks(this);
	}
	
	public void setUpBasicData() throws Exception
	{
		LOG.info("Creating Basic test data ..");
		importCsv(BASE_RESOURCE_PATH+"/orderBasicTestData.csv", "utf-8");
	}
	
	public void setUpProductData() throws Exception
	{
		LOG.info("Creating test data for Product ..");
		importCsv(BASE_RESOURCE_PATH+"/orderProductTestData.csv", "utf-8");
	}
	
	public void setUpUserData() throws Exception
	{
		LOG.info("Creating test data for User ..");
		importCsv(BASE_RESOURCE_PATH+"/orderUserTestData.csv", "utf-8");
	}
	
	public void setUpOrderData() throws Exception
	{
		LOG.info("Creating test data for Order ..");
		importCsv(BASE_RESOURCE_PATH+"/orderListTestData.csv", "utf-8");
	}
	
	@Test
	public void testSalesApplication()
	{
		LOG.info("##### Test cases starts #####");
		List<EnumData> enumData = amwayEnumDataFacade.getEnumValuesByClass(SalesApplication.class);
		LOG.info("Testing if SalesApplication is not null..");
		Assert.assertTrue(CollectionUtils.isNotEmpty(enumData));
	}
	
	@Test
	public void testOrderList_Date()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderDateFrom(DATE_TO);
		amwayOrderSearchParameters.setOrderDateTo(DATE_FROM);
		
		final PageableData pageableData = createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		LOG.info("Testing if SearchPageData is not null..");
		Assert.assertNotNull(searchPageData);
		LOG.info("Testing if Order Result is not null..");
		Assert.assertNotNull(searchPageData.getResults());
	}
	
	@Test
	public void testOrderList_Date_OrderCode()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderDateFrom(DATE_TO);
		amwayOrderSearchParameters.setOrderDateTo(DATE_FROM);
		amwayOrderSearchParameters.setOrderNo(TEST_ORDER_CODE);
		
		final PageableData pageableData = createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		LOG.info("Testing if SearchPageData is not null..");
		Assert.assertNotNull(searchPageData);
		LOG.info("Testing if Order Result is not null..");
		Assert.assertNotNull(searchPageData.getResults());
	}
	
	@Test
	public void testOrderList_Date_aboId()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderDateFrom(DATE_TO);
		amwayOrderSearchParameters.setOrderDateTo(DATE_FROM);
		amwayOrderSearchParameters.setOrderingABOID(ORDERING_ABO_ID);
		
		final PageableData pageableData = createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		LOG.info("Testing if SearchPageData is not null..");
		Assert.assertNotNull(searchPageData);
		LOG.info("Testing if Order Result is not null..");
		Assert.assertNotNull(searchPageData.getResults());
	}
	
	@Test
	public void testOrderList_Date_aboName()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderDateFrom(DATE_TO);
		amwayOrderSearchParameters.setOrderDateTo(DATE_FROM);
		amwayOrderSearchParameters.setOrderingABOName(ORDERING_ABO_NAME);
		
		final PageableData pageableData = createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		LOG.info("Testing if SearchPageData is not null..");
		Assert.assertNotNull(searchPageData);
		LOG.info("Testing if Order Result is not null..");
		Assert.assertNotNull(searchPageData.getResults());
	}
	
	@Test
	public void testOrderList_Date_salesChannel()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderDateFrom(DATE_TO);
		amwayOrderSearchParameters.setOrderDateTo(DATE_FROM);
		amwayOrderSearchParameters.setSalesChannel(SALES_CHANNEL);
		
		final PageableData pageableData = createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		LOG.info("Testing if SearchPageData is not null..");
		Assert.assertNotNull(searchPageData);
		LOG.info("Testing if Order Result is not null..");
		Assert.assertNotNull(searchPageData.getResults());
	}
	
	@Test
	public void testOrderList_Date_aboName_aboID()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderDateFrom(DATE_TO_2);
		amwayOrderSearchParameters.setOrderDateTo(DATE_FROM_2);
		amwayOrderSearchParameters.setOrderingABOName(ORDERING_ABO_NAME_2);
		amwayOrderSearchParameters.setOrderingABOID(ORDERING_ABO_ID_2);
		
		final PageableData pageableData = createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		LOG.info("Testing if SearchPageData is not null..");
		Assert.assertNotNull(searchPageData);
		LOG.info("Testing if Order Result is not null..");
		Assert.assertNotNull(searchPageData.getResults());
	}
	
	@Test
	public void testOrderList_Date_aboName_aboID_OrderCode()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderDateFrom(DATE_TO_2);
		amwayOrderSearchParameters.setOrderDateTo(DATE_FROM_2);
		amwayOrderSearchParameters.setOrderingABOName(ORDERING_ABO_NAME_2);
		amwayOrderSearchParameters.setOrderingABOID(ORDERING_ABO_ID_2);
		amwayOrderSearchParameters.setOrderNo(TEST_ORDER_CODE_2);
		
		final PageableData pageableData = createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		LOG.info("Testing if SearchPageData is not null..");
		Assert.assertNotNull(searchPageData);
		LOG.info("Testing if Order Result is not null..");
		Assert.assertNotNull(searchPageData.getResults());
	}
	
	@Test
	public void testOrderList_Date_aboName_aboID_SizeCheck()
	{
		amwayOrderSearchParameters = new AmwayOrderSearchParameters();
		amwayOrderSearchParameters.setOrderDateFrom(DATE_TO_2);
		amwayOrderSearchParameters.setOrderDateTo(DATE_FROM_2);
		amwayOrderSearchParameters.setOrderingABOName(ORDERING_ABO_NAME_2);
		amwayOrderSearchParameters.setOrderingABOID(ORDERING_ABO_ID_2);
		
		final PageableData pageableData = createPageableData(0,10,DEFAULT_DATE);
		final SearchPageData<OrderHistoryData> searchPageData = amwayOrderFacade.getOrders(amwayOrderSearchParameters, pageableData);
		Assert.assertNotNull(searchPageData);
		Assert.assertNotNull(searchPageData.getResults());
		LOG.info("Testing if Order Result is having 2 records..");
		Assert.assertEquals(2,searchPageData.getResults().size());
		LOG.info("##### Test cases ends #####");
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
