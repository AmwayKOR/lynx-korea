/**
 *
 */
package com.amway.apac.core.stock.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.apac.core.stock.service.AmwayApacCommerceStockService;


/**
 * Test class to test methods of AmwayApacCommerceStockService
 */
public class DefaultAmwayApacCommerceStockServiceUnitTest
{
	@InjectMocks
	private final AmwayApacCommerceStockService amwayApacCommerceStockService = new DefaultAmwayApacCommerceStockService();

	@Mock
	private InventoryEventService inventoryEventService;

	@Mock
	private ModelService modelService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testReleaseAllocationEvents()
	{
		final AllocationEventModel allocationEventModel = Mockito.mock(AllocationEventModel.class);
		final OrderEntryModel orderEntry = Mockito.mock(OrderEntryModel.class);
		final List allocationEvents = Arrays.asList(allocationEventModel);
		Mockito.doReturn(allocationEvents).when(inventoryEventService)
				.getAllocationEventsForOrderEntry(Mockito.any(OrderEntryModel.class));
		amwayApacCommerceStockService.releaseAllocationEvents(orderEntry);
		verify(modelService, times(1)).removeAll(allocationEvents);
	}

	@Test
	public void testReleaseAllocationEventsWithNoEvents()
	{
		final AllocationEventModel allocationEventModel = Mockito.mock(AllocationEventModel.class);
		final OrderEntryModel orderEntry = Mockito.mock(OrderEntryModel.class);
		final List allocationEvents = Arrays.asList(allocationEventModel);
		Mockito.doReturn(Collections.EMPTY_LIST).when(inventoryEventService)
				.getAllocationEventsForOrderEntry(Mockito.any(OrderEntryModel.class));
		amwayApacCommerceStockService.releaseAllocationEvents(orderEntry);
		verify(modelService, times(0)).removeAll(allocationEvents);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testReleaseAllocationEventsWithNull()
	{
		amwayApacCommerceStockService.releaseAllocationEvents(null);
	}

}
