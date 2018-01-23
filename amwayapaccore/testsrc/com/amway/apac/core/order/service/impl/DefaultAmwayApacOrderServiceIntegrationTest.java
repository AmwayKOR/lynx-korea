package com.amway.apac.core.order.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.order.dao.CommerceOrderDao;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.order.service.AmwayApacOrderService;


/**
 * Test class for {@DefaultAmwayApacOrderService}
 */
@IntegrationTest
public class DefaultAmwayApacOrderServiceIntegrationTest extends ServicelayerTest
{
	private static final String TEST_ORDER_1 = "testOrder1";
	private static final String TEST_ORDER_2 = "testOrder2";
	private static final String TEST_ORDER_3 = "testOrder3";
	private static final String TEST_ORDER_4 = "testOrder4";

	@Resource(name = "orderService")
	private AmwayApacOrderService amwayApacOrderService;

	@Resource(name = "commerceOrderDao")
	private CommerceOrderDao commerceOrderDao;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testOrderService.impex", "UTF-8");
	}

	@Test
	public void testIsOrderPaymentCapturedTrue()
	{
		Assert.assertEquals(Boolean.TRUE,
				Boolean.valueOf(amwayApacOrderService.isOrderPaymentCaptured(getOrderByCode(TEST_ORDER_1))));
	}

	@Test
	public void testIsOrderPaymentCapturedFalse()
	{
		Assert.assertEquals(Boolean.FALSE,
				Boolean.valueOf(amwayApacOrderService.isOrderPaymentCaptured(getOrderByCode(TEST_ORDER_2))));
	}

	@Test
	public void testIsOrderPaymentCapturedWithZeroTransactions()
	{
		Assert.assertEquals(Boolean.FALSE,
				Boolean.valueOf(amwayApacOrderService.isOrderPaymentCaptured(getOrderByCode(TEST_ORDER_3))));
	}

	@Test
	public void testIsOrderPaymentCapturedWithZeroTransactionEntries()
	{
		Assert.assertEquals(Boolean.FALSE,
				Boolean.valueOf(amwayApacOrderService.isOrderPaymentCaptured(getOrderByCode(TEST_ORDER_4))));
	}

	/**
	 * Gets order by code
	 *
	 * @return order
	 */
	private OrderModel getOrderByCode(final String orderCode)
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put(OrderModel.CODE, orderCode);
		final List<OrderModel> orderList = commerceOrderDao.find(parameters);
		return orderList.get(0);
	}

}
