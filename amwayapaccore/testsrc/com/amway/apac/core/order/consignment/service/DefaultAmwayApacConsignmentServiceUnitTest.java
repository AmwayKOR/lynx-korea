/**
 *
 */
package com.amway.apac.core.order.consignment.service;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.amway.apac.core.order.consignment.service.impl.DefaultAmwayApacConsignmentService;


/**
 * Test class for Junit test cases for {@DefaultAmwayApacConsignmentService}
 */
@UnitTest
public class DefaultAmwayApacConsignmentServiceUnitTest
{
	@InjectMocks
	@Spy
	private final DefaultAmwayApacConsignmentService amwayApacConsignmentService = new DefaultAmwayApacConsignmentService();

	@Mock
	private WarehouseService warehouseService;

	@Mock
	private BusinessProcessService businessProcessService;

	@Mock
	private ModelService modelService;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateConsignmentsWithNullOrder() throws ConsignmentCreationException
	{
		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(null);
		Assert.assertNotNull(consignments);
		Assert.assertEquals(0, consignments.size());
	}

	@Test
	public void testCreateConsignmentsWithOrder() throws ConsignmentCreationException
	{
		final OrderModel order = Mockito.mock(OrderModel.class);
		final OrderEntryModel orderEntry = Mockito.mock(OrderEntryModel.class);
		final WarehouseModel warehouse = Mockito.mock(WarehouseModel.class);
		final ConsignmentModel consignment = Mockito.mock(ConsignmentModel.class);
		final ConsignmentProcessModel consignmentProcess = Mockito.mock(ConsignmentProcessModel.class);

		final List<OrderEntryModel> orderEntries = new ArrayList<>();
		final List<WarehouseModel> warehouses = new ArrayList<>();

		orderEntries.add(orderEntry);
		Mockito.doReturn(orderEntries).when(order).getEntries();

		warehouses.add(warehouse);
		Mockito.doReturn(warehouses).when(warehouseService).getWarehouses(orderEntries);

		Mockito.doReturn(consignmentProcess).when(businessProcessService).createProcess(Mockito.anyString(), Mockito.anyString());
		Mockito.doNothing().when(modelService).save(consignment);
		Mockito.doReturn(InStockStatus.BACKORDER).when(orderEntry).getDispositionCode();
		Mockito.doReturn("TEST_ORDER_CODE").when(order).getCode();
		Mockito.doReturn(consignment).when(amwayApacConsignmentService).createConsignment(Mockito.any(AbstractOrderModel.class),
				Mockito.anyString(), Mockito.anyList());
		Mockito.doReturn("TEST_CONSIGNMENT_CODE").when(consignment).getCode();
		Mockito.doReturn(order).when(consignment).getOrder();

		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		Assert.assertNotNull(consignments);
		Assert.assertEquals(1, consignments.size());
	}

	@Test
	public void testCreateConsignmentsWithOrderNoEntries() throws ConsignmentCreationException
	{
		final OrderModel order = Mockito.mock(OrderModel.class);
		final OrderEntryModel orderEntry = Mockito.mock(OrderEntryModel.class);
		final WarehouseModel warehouse = Mockito.mock(WarehouseModel.class);
		final ConsignmentModel consignment = Mockito.mock(ConsignmentModel.class);
		final ConsignmentProcessModel consignmentProcess = Mockito.mock(ConsignmentProcessModel.class);

		final List<OrderEntryModel> orderEntries = new ArrayList<>();
		final List<WarehouseModel> warehouses = new ArrayList<>();

		Mockito.doReturn(orderEntries).when(order).getEntries();

		warehouses.add(warehouse);
		Mockito.doReturn(warehouses).when(warehouseService).getWarehouses(orderEntries);

		Mockito.doReturn(consignmentProcess).when(businessProcessService).createProcess(Mockito.anyString(), Mockito.anyString());
		Mockito.doNothing().when(modelService).save(consignment);
		Mockito.doReturn(InStockStatus.BACKORDER).when(orderEntry).getDispositionCode();
		Mockito.doReturn("TEST_ORDER_CODE").when(order).getCode();
		Mockito.doReturn(consignment).when(amwayApacConsignmentService).createConsignment(Mockito.any(AbstractOrderModel.class),
				Mockito.anyString(), Mockito.anyList());
		Mockito.doReturn("TEST_CONSIGNMENT_CODE").when(consignment).getCode();
		Mockito.doReturn(order).when(consignment).getOrder();

		final List<ConsignmentModel> consignments = amwayApacConsignmentService.createConsignments(order);
		Assert.assertNotNull(consignments);
		Assert.assertEquals(0, consignments.size());
	}

}
