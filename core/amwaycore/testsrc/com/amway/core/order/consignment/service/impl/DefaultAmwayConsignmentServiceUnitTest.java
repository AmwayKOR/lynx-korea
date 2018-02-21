/**
 *
 */
package com.amway.core.order.consignment.service.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import junit.framework.Assert;


@UnitTest
public class DefaultAmwayConsignmentServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayConsignmentService defaultAmwayConsignmentService = new DefaultAmwayConsignmentService();
	@Mock
	private ModelService modelService;
	@Mock
	private WarehouseService warehouseService;

	private static final String CONSIGNMENT_CODE1 = "consignment_01";
	private static final String CONSIGNMENT_CODE2 = "consignment_02";

	private OrderModel order1;

	private AbstractOrderEntryModel entry1;
	private AddressModel deliveryAddress;
	private DeliveryModeModel deliveryMode;
	private WarehouseModel warehouse1;
	private PointOfServiceModel posModel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		when(modelService.create(ConsignmentModel.class)).thenReturn(new ConsignmentModel());
		when(modelService.create(ConsignmentEntryModel.class)).thenReturn(new ConsignmentEntryModel());
		deliveryAddress = Mockito.mock(AddressModel.class);
		deliveryMode = Mockito.mock(DeliveryModeModel.class);
		warehouse1 = Mockito.mock(WarehouseModel.class);
		posModel = Mockito.mock(PointOfServiceModel.class);

		entry1 = new AbstractOrderEntryModel();
		entry1.setQuantity(Long.valueOf(1));
		entry1.setDeliveryMode(deliveryMode);
		entry1.setWareHouse(warehouse1);
		entry1.setDeliveryPointOfService(posModel);

		order1 = new OrderModel();
		order1.setDeliveryAddress(deliveryAddress);
		order1.setEntries(Arrays.asList(entry1));
		order1.setDeliveryMode(deliveryMode);


	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.consignment.service.impl.DefaultAmwayConsignmentService#createConsignment(de.hybris.platform.core.model.order.AbstractOrderModel, java.lang.String, java.util.List)}
	 * .
	 *
	 * @throws ConsignmentCreationException
	 */
	@Test
	public void testCreateConsignment() throws ConsignmentCreationException
	{
		when(warehouseService.getWarehouses(order1.getEntries())).thenReturn(Arrays.asList(warehouse1));
		final ConsignmentModel consignment = defaultAmwayConsignmentService
				.createConsignment(order1, CONSIGNMENT_CODE1, order1.getEntries());
		Assert.assertNotNull(consignment);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.consignment.service.impl.DefaultAmwayConsignmentService#createConsignment(de.hybris.platform.core.model.order.AbstractOrderModel, java.lang.String, java.util.List)}
	 * .
	 *
	 * @throws ConsignmentCreationException
	 */
	@Test(expected = ConsignmentCreationException.class)
	public void testCreateConsignmentForEmptyWarehose() throws ConsignmentCreationException
	{
		when(warehouseService.getWarehouses(order1.getEntries())).thenReturn(new ArrayList<WarehouseModel>());
		final ConsignmentModel consignment = defaultAmwayConsignmentService
				.createConsignment(order1, CONSIGNMENT_CODE2, order1.getEntries());
		Assert.assertNotNull(consignment);
	}

}
