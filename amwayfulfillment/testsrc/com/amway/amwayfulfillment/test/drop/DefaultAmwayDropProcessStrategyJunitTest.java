package com.amway.amwayfulfillment.test.drop;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.util.AmwayBusinessEventBuilder;


/**
 * Test for defalt drop process strategy.
 */
@UnitTest
public class DefaultAmwayDropProcessStrategyJunitTest
{

	public static final String SW = "SW";

	@Test
	public void testGenerateEvent() {
		TestableDefaultAmwayDropProcessStrategy strategy = new TestableDefaultAmwayDropProcessStrategy();
		List<OrderModel> orders = strategy.generateOrders(2);

		AmwayBusinessEvent event = strategy.generateEvent(orders, null);
		Assert.assertEquals(AmwayBusinessEventBuilder.EventName.READY_FOR_DROP.value, event.getName());
		List<String> ids = Arrays.asList(event.getEntityID().split(","));
		Assert.assertEquals(2, ids.size());
		Assert.assertTrue(ids.contains("1"));
		Assert.assertTrue(ids.contains("2"));
		Assert.assertEquals(AmwayBusinessEventBuilder.EntityName.ORDER_LIST.value, event.getEntityName());
		Assert.assertEquals(AmwayBusinessEventBuilder.Trigger.CRON_JOB.value, event.getTriggeredBy());
		Assert.assertEquals(SW, event.getCountryCode());
		Assert.assertEquals(AmwayBusinessEventBuilder.TargetSystem.WM.value, event.getTargetSystem());
	}

}
