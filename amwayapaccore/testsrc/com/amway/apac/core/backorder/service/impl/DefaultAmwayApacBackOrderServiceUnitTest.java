/**
 *
 */
package com.amway.apac.core.backorder.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderSelectionStrategy;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.core.stock.strategies.impl.AmwayApacCommerceAvailabilityCalculationStrategy;


/**
 * Test class for {@link DefaultAmwayApacBackOrderService}.
 *
 * @author ankushbhatia
 */
@UnitTest
public class DefaultAmwayApacBackOrderServiceUnitTest
{
	@InjectMocks
	@Spy
	private final DefaultAmwayApacBackOrderService amwayApacBackOrderService = new DefaultAmwayApacBackOrderService();
	@Mock
	private BusinessProcessService businessProcessService;
	@Mock
	private ModelService modelService;
	@Mock
	private AmwayApacBackOrderDao amwayApacBackOrderDao;
	@Mock
	private AmwayApacBackOrderSelectionStrategy amwayApacBackOrderSelectionStrategy;
	@Mock
	private AmwayApacCommerceAvailabilityCalculationStrategy commerceAvailabilityCalculationStrategy;
	@Mock
	private InventoryEventService inventoryEventService;

	private final List<AmwayBackOrderModel> amwayBackOrders = new ArrayList<>();

	private final List<StockLevelModel> stockLevels = new ArrayList<>();

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
		amwayBackOrders.add(amwayBackOrder);
		final StockLevelModel stocklevel = Mockito.mock(StockLevelModel.class);
		stockLevels.add(stocklevel);
	}

	@Test
	public void testReleaseBackOrdersForStockLevelsWhenNull()
	{
		try
		{
			final List<StockLevelModel> stockLevels = null;
			amwayApacBackOrderService.releaseBackOrdersForStockLevels(stockLevels);
			Assert.fail("Should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException iae)
		{
			//expected OK
		}
	}

	@Test
	public void testReleaseBackOrdersForStockLevelsWhenEmpty()
	{
		final List<StockLevelModel> stockLevels = new ArrayList<>();
		Assert.assertFalse(amwayApacBackOrderService.releaseBackOrdersForStockLevels(stockLevels));
	}

	@Test
	public void testReleaseBackOrdersForStockLevels()
	{
		//given
		final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = new HashMap<>();
		amwayBackOrdersMap.put(stockLevels.get(0), amwayBackOrders);
		Mockito.doReturn(amwayBackOrdersMap).when(amwayApacBackOrderSelectionStrategy.getBackOrdersForRelease(stockLevels));

		//then
		Assert.assertTrue(amwayApacBackOrderService.releaseBackOrdersForStockLevels(stockLevels));
	}

	@Test
	public void testReleaseBackOrdersForBaseStoreWhenNull()
	{
		try
		{
			final BaseStoreModel baseStore = null;
			amwayApacBackOrderService.releaseBackOrdersForBaseStore(baseStore);
			Assert.fail("Should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException iae)
		{
			//expected OK
		}
	}

	@Test
	public void testReleaseBackOrdersForBaseStore()
	{
		//given
		final Map<StockLevelModel, List<AmwayBackOrderModel>> amwayBackOrdersMap = new HashMap<>();
		amwayBackOrdersMap.put(stockLevels.get(0), amwayBackOrders);
		final BaseStoreModel baseStore = Mockito.mock(BaseStoreModel.class);
		Mockito.doReturn(amwayBackOrdersMap).when(amwayApacBackOrderSelectionStrategy.getBackOrdersForRelease(baseStore));

		//then
		Assert.assertTrue(amwayApacBackOrderService.releaseBackOrdersForBaseStore(baseStore));
	}

	@Test
	public void testExpireBackOrdersWhenBackOrdersIsNull()
	{
		try
		{
			amwayApacBackOrderService.expireBackOrders(null);
			Assert.fail("Should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException iae)
		{
			//expected OK
		}
	}

	@Test
	public void testExpireBackOrdersWhenBackOrdersIsEmpty()
	{
		final List<AmwayBackOrderModel> amwayBackOrders = new ArrayList<>();
		Assert.assertFalse(amwayApacBackOrderService.expireBackOrders(amwayBackOrders));
	}

	@Test
	public void testExpireBackOrders()
	{
		Mockito.doNothing().when(modelService).save(amwayBackOrder);
		//then
		Assert.assertTrue(amwayApacBackOrderService.expireBackOrders(amwayBackOrders));
	}


	@Test
	public void testGetBackOrderByConsignmentWhenNull()
	{
		try
		{
			amwayApacBackOrderService.getBackOrderByConsignment(null);
			Assert.fail("Should throw IllegalArgumentException");
		}
		catch (final IllegalArgumentException iae)
		{
			//expected OK
		}
	}

	@Test
	public void testGetBackOrderByConsignment()
	{
		//given
		final ConsignmentModel consignment = Mockito.mock(ConsignmentModel.class);
		Mockito.doReturn(amwayBackOrder).when(amwayApacBackOrderDao.getBackOrdersByConsignment(consignment));

		//then
		Assert.assertNotNull(amwayApacBackOrderService.getBackOrderByConsignment(consignment));
	}
}
