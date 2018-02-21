package com.amway.amwayfulfillment.test.drop;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.amway.amwayfulfillment.drop.AmwayDropProcessHelper;


@UnitTest
public class AmwayDropProcessHelperJunitTest
{
	@Test
	public void testGetOrderCodes()
	{
		TestableDefaultAmwayDropProcessStrategy strategy = new TestableDefaultAmwayDropProcessStrategy();
		List<OrderModel> orders = strategy.generateOrders(2);
		List<String> orderCodes = AmwayDropProcessHelper.getOrderCodes(orders);
		Assert.assertEquals(2, orderCodes.size());
		Assert.assertTrue(orderCodes.contains("1"));
		Assert.assertTrue(orderCodes.contains("2"));
	}

	@Test
	public void testGetOrderCodesWithNullParam()
	{
		List<String> orderCodes = AmwayDropProcessHelper.getOrderCodes(null);
		Assert.assertNotNull(orderCodes);
		Assert.assertTrue(orderCodes.isEmpty());
	}
}
