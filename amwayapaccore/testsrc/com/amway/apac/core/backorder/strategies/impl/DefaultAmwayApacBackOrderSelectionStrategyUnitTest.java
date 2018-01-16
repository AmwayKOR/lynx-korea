package com.amway.apac.core.backorder.strategies.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.amway.apac.core.backorder.dao.AmwayApacBackOrderDao;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.core.order.service.AmwayApacOrderService;


/**
 * Test class for {@link DefaultAmwayApacBackOrderSelectionStrategy}.
 *
 * @author ankushbhatia
 */
public class DefaultAmwayApacBackOrderSelectionStrategyUnitTest
{
	@InjectMocks
	@Spy
	private final DefaultAmwayApacBackOrderSelectionStrategy amwayApacBackOrderSelectionStrategy = new DefaultAmwayApacBackOrderSelectionStrategy();
	@Mock
	private AmwayApacBackOrderDao amwayApacBackOrderDao;
	@Mock
	private StockService stockService;
	@Mock
	private AmwayApacOrderService amwayApacOrderService;

	private AmwayBackOrderModel amwayBackOrder;

	/**
	 * SetUp method for class
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		final OrderModel order = Mockito.mock(OrderModel.class);
		final WarehouseModel warehouse = Mockito.mock(WarehouseModel.class);
		final ConsignmentModel consignment = Mockito.mock(ConsignmentModel.class);
		final BaseStoreModel baseStore = Mockito.mock(BaseStoreModel.class);
		amwayBackOrder = Mockito.mock(AmwayBackOrderModel.class);
		amwayBackOrder.setOriginalOrder(order);
		amwayBackOrder.setConsignment(consignment);
		amwayBackOrder.setWarehouse(warehouse);
		amwayBackOrder.setBaseStore(baseStore);
	}

	@Test
	public void testGetBackOrdersForReleaseWhenBaseStoreIsNull()
	{
		try
		{
			final BaseStoreModel baseStore = null;
			amwayApacBackOrderSelectionStrategy.getBackOrdersForRelease(baseStore);
			Assert.fail("Should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException iae)
		{
			//expected OK
		}
	}

	@Test
	public void testGetBackOrdersForReleaseWithBaseStore()
	{
		//given
		Mockito.doReturn(Collections.singletonList(amwayBackOrder)).when(
				amwayApacBackOrderDao.getBackOrders(AmwayBackOrderStatus.ACTIVE, null, null, null));
		Mockito.doReturn(Boolean.TRUE).when(
				Boolean.valueOf(amwayApacOrderService.isOrderPaymentCaptured(amwayBackOrder.getOriginalOrder())));

		//when
		final BaseStoreModel baseStore = Mockito.mock(BaseStoreModel.class);
		final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = amwayApacBackOrderSelectionStrategy
				.getBackOrdersForRelease(baseStore);

		//then
		Assert.assertNotNull(amwayBackOrdersMap);
		Assert.assertEquals(0, amwayBackOrdersMap.size());
	}


	@Test
	public void testGetBackOrdersForReleaseWhenStockLevelsIsNull()
	{
		try
		{
			final List<StockLevelModel> stockLevels = null;
			amwayApacBackOrderSelectionStrategy.getBackOrdersForRelease(stockLevels);
			Assert.fail("Should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException iae)
		{
			//expected OK
		}
	}


	@Test
	public void testGetBackOrdersForReleaseWhenStockLevelsIsEmpty()
	{
		//given
		final List<StockLevelModel> stockLevels = new ArrayList<>();

		//when
		final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = amwayApacBackOrderSelectionStrategy
				.getBackOrdersForRelease(stockLevels);

		//then
		Assert.assertNotNull(amwayBackOrdersMap);
		Assert.assertEquals(0, amwayBackOrdersMap.size());
	}

	@Test
	public void testGetBackOrdersForReleaseWithStockLevels()
	{
		//given
		Mockito.doReturn(Collections.singletonList(amwayBackOrder)).when(
				amwayApacBackOrderDao.getBackOrders(AmwayBackOrderStatus.ACTIVE, null, null, null));
		Mockito.doReturn(Boolean.TRUE).when(
				Boolean.valueOf(amwayApacOrderService.isOrderPaymentCaptured(amwayBackOrder.getOriginalOrder())));
		//when
		final List<StockLevelModel> stockLevels = new ArrayList<>();
		final StockLevelModel stocklevel = Mockito.mock(StockLevelModel.class);
		stockLevels.add(stocklevel);
		final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = amwayApacBackOrderSelectionStrategy
				.getBackOrdersForRelease(stockLevels);

		//then
		Assert.assertNotNull(amwayBackOrdersMap);
		Assert.assertEquals(1, amwayBackOrdersMap.size());
	}


	@Test
	public void testgetBackOrdersForExpiringWhenStatusIsNull()
	{
		try
		{
			amwayApacBackOrderSelectionStrategy.getBackOrdersForExpiring(null, new Date());
			Assert.fail("Should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException iae)
		{
			//expected OK
		}
	}

	@Test
	public void testgetBackOrdersForExpiring()
	{
		//given
		Mockito.doReturn(Collections.singletonList(amwayBackOrder)).when(
				amwayApacBackOrderDao.getBackOrdersByStatusAndDate(AmwayBackOrderStatus.ACTIVE, new Date()));

		//when
		final List<AmwayBackOrderModel> amwayBackOrders = amwayApacBackOrderSelectionStrategy.getBackOrdersForExpiring(
				AmwayBackOrderStatus.ACTIVE, new Date());

		//then
		Assert.assertEquals(1, amwayBackOrders.size());
	}
}
