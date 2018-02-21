package com.amway.apac.facades.orderhistory.populators;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.apac.facades.populators.AmwayApacOrderHistoryPopulator;


/**
 * Integration test for {@link AmwayApacOrderHistoryPopulator}
 *
 * @author Aaron Yong
 *
 */
@IntegrationTest
public class AmwayApacOrderHistoryPopulatorIntegrationTest
{
	/**
	 * The populator for test cases
	 */
	@Resource(name = "amwayApacOrderHistoryPopulator")
	private final AmwayApacOrderHistoryPopulator amwayApacOrderHistoryPopulator = new AmwayApacOrderHistoryPopulator();

	@Resource(name = "modelService")
	private ModelService modelService;

	private AbstractOrderEntryModel abstractOrderEntryModel;
	private OrderEntryData orderEntryData;
	private OrderModel orderModel;
	private OrderHistoryData orderHistoryData;

	private static final String CODE = "101111";
	private static final String STATUS = "processing";

	/**
	 * Prepares the data for the test cases
	 */
	@Before
	public void prepare()
	{
		MockitoAnnotations.initMocks(this);

		abstractOrderEntryModel = Mockito.mock(AbstractOrderEntryModel.class);

		orderModel = Mockito.mock(OrderModel.class);
		BDDMockito.when(orderModel.getCode()).thenReturn(CODE);
		BDDMockito.when(orderModel.getStatusDisplay()).thenReturn(STATUS);
		BDDMockito.when(orderModel.getTotalPrice()).thenReturn(null);

		orderEntryData = new OrderEntryData();

		orderHistoryData = new OrderHistoryData();


	}

	/**
	 * Test for method {@link AmwayApacOrderHistoryPopulator#populate(OrderModel, OrderHistoryData)}. Testing with null
	 * source
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullSource()
	{
		amwayApacOrderHistoryPopulator.populate(null, orderHistoryData);
	}

	/**
	 * Test for method {@link AmwayApacOrderHistoryPopulator#populate(OrderModel, OrderHistoryData)}. Testing with null
	 * target
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPopulateWithNullTarget()
	{
		amwayApacOrderHistoryPopulator.populate(orderModel, null);
	}

	/**
	 * Test for method {@link AmwayApacOrderHistoryPopulator#populate(OrderModel, OrderHistoryData)}. Testing with all the
	 * values present.
	 */
	@Test
	public void testPopulate()
	{

		amwayApacOrderHistoryPopulator.populate(orderModel, orderHistoryData);

		Assert.assertNotNull(orderHistoryData.getCode());
		Assert.assertEquals(orderHistoryData.getCode(), CODE);

		Assert.assertNotNull(orderHistoryData.getStatusDisplay());
		Assert.assertEquals(orderHistoryData.getStatusDisplay(), STATUS);



	}
}
