package com.amway.amwayfulfillment.test.drop;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.amwayfulfillment.drop.services.impl.AmwayDropSupportServiceImpl;
import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.util.AmwayBusinessEventBuilder;


@UnitTest
public class AmwayDropSupportServiceImplJunitTest
{
	TestableDefaultAmwayDropProcessStrategy dropProcessStrategy;

	AmwayDropSupportServiceImpl service;

	@Mock
	ModelService modelService;

	@Mock
	EventService eventService;

	ProductModel droppable = new ProductModel();
	ProductModel nonDroppable = new ProductModel();

	class TestDroppableFlagStrategy extends TestableDefaultAmwayDropProcessStrategy {
		@Override
		public boolean isDroppableProduct(ProductModel productModel)
		{
			return productModel == droppable; //yes, compare by reference
		}
	}

	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		dropProcessStrategy = new TestableDefaultAmwayDropProcessStrategy();
		dropProcessStrategy.setOrderStatusAfterDrop(OrderStatus.READY);
		service = new AmwayDropSupportServiceImpl();
		service.setDropProcessStrategy(dropProcessStrategy);
		service.setModelService(modelService);
		service.setEventService(eventService);
	}

	@Test
	public void testDoDrop()
	{
		List<OrderModel> orders = dropProcessStrategy.generateOrders(2, true);
		service.doDrop(orders, null);
		//make sure we've published events
		ArgumentCaptor<AmwayBusinessEvent> eventCaptor = ArgumentCaptor.forClass(AmwayBusinessEvent.class);
		verify(eventService, times(3)).publishEvent(eventCaptor.capture());
		//make sure order status changed accordingly
		orders.stream().forEach(o -> Assert.assertEquals(dropProcessStrategy.getOrderStatusAfterDrop(o), o.getStatus()));
		//make sure drop event generated
		AmwayBusinessEvent be = eventCaptor.getAllValues().get(2);
		Assert.assertEquals("1,2",  be.getEntityID());
		Assert.assertEquals(AmwayBusinessEventBuilder.EventName.READY_FOR_DROP.value, be.getName());
	}

	@Test
	public void testShippableFlagTrue() {
		service.setDropProcessStrategy(new TestDroppableFlagStrategy());

		OrderModel droppableOrder = new OrderModel();
		ArrayList<AbstractOrderEntryModel> entries = new ArrayList<>();
		OrderEntryModel entryDroppable = new OrderEntryModel();
		entryDroppable.setProduct(droppable);
		entries.add(entryDroppable);
		OrderEntryModel entryNonDroppable = new OrderEntryModel();
		entryNonDroppable.setProduct(nonDroppable);
		entries.add(entryNonDroppable);
		droppableOrder.setEntries(entries);

		Assert.assertTrue(service.calculateDroppableFlag(droppableOrder));
	}

	@Test
	public void testShippableFlagFalse() {
		service.setDropProcessStrategy(new TestDroppableFlagStrategy());

		OrderModel nonDroppableOrder = new OrderModel();
		ArrayList<AbstractOrderEntryModel> entries = new ArrayList<>();
		OrderEntryModel entryNonDroppable = new OrderEntryModel();
		entryNonDroppable.setProduct(nonDroppable);
		entries.add(entryNonDroppable);
		nonDroppableOrder.setEntries(entries);

		Assert.assertFalse(service.calculateDroppableFlag(nonDroppableOrder));
	}

}
