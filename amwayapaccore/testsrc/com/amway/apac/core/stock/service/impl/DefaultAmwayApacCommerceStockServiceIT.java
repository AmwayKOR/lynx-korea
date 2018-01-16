/**
 *
 */
package com.amway.apac.core.stock.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.order.dao.CommerceOrderDao;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.stock.impl.StockLevelDao;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.core.stock.service.AmwayApacCommerceStockService;


/**
 * Integration Test class for {@DefaultAmwayApacCommerceStockService}
 */
@IntegrationTest
public class DefaultAmwayApacCommerceStockServiceIT extends ServicelayerTransactionalTest
{
	private static final String TEST_ORDER_1 = "testOrder1";
	private static final String TEST_ORDER_2 = "testOrder2";

	@Resource(name = "amwayApacCommerceStockService")
	private AmwayApacCommerceStockService amwayApacCommerceStockService;

	@Resource(name = "commerceOrderDao")
	private CommerceOrderDao commerceOrderDao;

	@Resource
	private StockLevelDao stockLevelDao;

	@Resource
	private WarehouseService warehouseService;

	@Resource
	private ProductService productService;

	@Resource(name = "inventoryEventService")
	private InventoryEventService inventoryEventService;

	/**
	 * @throws de.hybris.platform.impex.jalo.ImpExException
	 */
	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccore/test/testCommerceStockService.impex", "UTF-8");
	}

	/**
	 * Tests that allocation events are released for given condition
	 */
	@Test
	public void testReleaseAllocationEventForOrder()
	{
		final OrderModel order = getOrderByCode(TEST_ORDER_1);
		final List<AllocationEventModel> allocationEvents = new ArrayList<>();
		order.getEntries().forEach(entry -> {
			amwayApacCommerceStockService.releaseAllocationEvents((OrderEntryModel) entry);
			allocationEvents.addAll(inventoryEventService.getAllocationEventsForOrderEntry((OrderEntryModel) entry));
		});

		Assert.assertEquals(0, allocationEvents.size());
	}

	@Test
	public void testReleaseAllocationEventsForAllEntries()
	{

		final StockLevelModel stock = stockLevelDao.findStockLevel("testProduct1",
				warehouseService.getWarehouseForCode("testWarehosue1"));
		final OrderModel order = getOrderByCode(TEST_ORDER_2);

		final Collection<AllocationEventModel> orderEntryAllocationEvents = new ArrayList<>();
		order.getEntries().forEach(entry -> {
			orderEntryAllocationEvents.addAll(inventoryEventService.getAllocationEventsForOrderEntry((OrderEntryModel) entry));
			amwayApacCommerceStockService.releaseAllocationEvents((OrderEntryModel) entry);
		});
		final Collection<AllocationEventModel> allAllocationEvents = inventoryEventService.getInventoryEventsForStockLevel(stock,
				AllocationEventModel.class);
		Assert.assertEquals(Boolean.FALSE, Boolean.valueOf(allAllocationEvents.containsAll(orderEntryAllocationEvents)));
	}


	/**
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
