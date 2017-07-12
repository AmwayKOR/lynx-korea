package com.amway.amwayfulfillment.test.shipment.junit;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.DeliveryStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Collections;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.amwayfulfillment.constants.AmwayfulfillmentConstants;
import com.amway.amwayfulfillment.exceptions.shipment.AmwayIgnoredConsignmentException;
import com.amway.amwayfulfillment.order.ShippingEvent;
import com.amway.amwayfulfillment.exceptions.shipment.AmwayShipmentConfirmationException;
import com.amway.amwayfulfillment.facades.shipment.impl.DefaultAmwayShipmentConfirmationFacade;
import com.amway.amwayfulfillment.order.data.AmwayConsignmentCreationInfo;
import com.amway.amwayfulfillment.services.shipment.AmwayConsignmentValidationStrategy;
import com.amway.amwayfulfillment.services.shipment.AmwayExternalConsignmentService;
import com.amway.core.event.orderprocessing.AmwayShipmentConfirmationEvent;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


/**
 * Junit test for {@link DefaultAmwayShipmentConfirmationFacade}
 */
@UnitTest
public class AmwayShipmentConfirmationFacadeTest
{

	private OrderModel orderModel;
	private ConsignmentModel consignmentModel;
	private AbstractOrderEntryModel orderEntryModel;
	private AbstractOrderEntryModel consignmentOrderEntryModel;
	private ProductModel consignmentProductModel;
	private ProductModel orderProductModel;
	private ConsignmentEntryModel consignmentEntryModel;
	private WarehouseModel warehouseModel;

	@Mock
	private BaseStoreService baseStoreService;
	@Mock
	private CustomerAccountService customerAccountService;
	@Mock
	private EventService eventService;
	@Mock
	private AmwayExternalConsignmentService amwayExternalConsignmentService;
	@Mock
	private WarehouseService warehouseService;
	@Mock
	private AmwayConsignmentValidationStrategy consignmentValidationStrategy;


	@InjectMocks
	private DefaultAmwayShipmentConfirmationFacade defaultAmwayShipmentConfirmationFacade = new DefaultAmwayShipmentConfirmationFacade();

	@Before
	public void setUp() throws AmwayShipmentConfirmationException
	{
		MockitoAnnotations.initMocks(this);
		BaseStoreModel baseStoreModel = mock(BaseStoreModel.class);
		orderModel = mock(OrderModel.class);
		consignmentModel = mock(ConsignmentModel.class);
		orderEntryModel = mock(AbstractOrderEntryModel.class);
		consignmentOrderEntryModel = mock(AbstractOrderEntryModel.class);
		consignmentEntryModel = mock(ConsignmentEntryModel.class);
		consignmentProductModel = mock(ProductModel.class);
		orderProductModel = mock(ProductModel.class);
		warehouseModel = mock(WarehouseModel.class);

		when(baseStoreService.getBaseStoreForUid(any(String.class))).thenReturn(baseStoreModel);
		when(customerAccountService.getOrderForCode(any(String.class), eq(baseStoreModel))).thenReturn(orderModel);
		when(warehouseService.getWarehouseForCode(any(String.class))).thenReturn(warehouseModel);

		defaultAmwayShipmentConfirmationFacade.setAllowedDeliveryStatuses(Sets.newHashSet(DeliveryStatus.IN_PROGRESS));
	}

	@Test
	public void testShipmentConfirmationTriggered() throws AmwayShipmentConfirmationException
	{
		when(orderModel.getConsignments()).thenReturn(new HashSet<>());
		when(orderModel.getEntries()).thenReturn(Collections.singletonList(orderEntryModel));
		when(orderModel.getDeliveryStatus()).thenReturn(DeliveryStatus.IN_PROGRESS);
		when(orderEntryModel.getProduct()).thenReturn(orderProductModel);

		when(amwayExternalConsignmentService.createOrderConsignments(any(ShippingEvent.class), eq(orderModel), eq(warehouseModel), any(ConsignmentStatus.class)))
				.thenReturn(Collections.singletonList(createSuccessInfo()));

		when(Boolean.valueOf(consignmentValidationStrategy.validate(any()))).thenReturn(Boolean.TRUE);

		defaultAmwayShipmentConfirmationFacade.confirmShipment(new ShippingEvent(), "", "");

		verify(eventService).publishEvent(any(AmwayShipmentConfirmationEvent.class));
	}

	@Test
	public void testShipmentConfirmationTriggeredWithWrongQuantity() throws AmwayShipmentConfirmationException
	{
		when(orderModel.getConsignments()).thenReturn(new HashSet<>());
		when(orderModel.getEntries()).thenReturn(Collections.singletonList(orderEntryModel));
		when(orderModel.getDeliveryStatus()).thenReturn(DeliveryStatus.IN_PROGRESS);
		when(orderEntryModel.getProduct()).thenReturn(orderProductModel);

		when(amwayExternalConsignmentService.createOrderConsignments(any(ShippingEvent.class), eq(orderModel), eq(warehouseModel), any(ConsignmentStatus.class)))
				.thenReturn(Lists.newArrayList(createSuccessInfo()));

		when(Boolean.valueOf(consignmentValidationStrategy.validate(any()))).thenReturn(Boolean.FALSE);

		defaultAmwayShipmentConfirmationFacade.confirmShipment(new ShippingEvent(), "", "");

		verify(eventService).publishEvent(any(AmwayShipmentConfirmationEvent.class));
	}

	@Test(expected = AmwayIgnoredConsignmentException.class)
	public void testConfirmationNotTriggeredWithAlreadyShippedConsignments() throws AmwayShipmentConfirmationException
	{
		when(orderModel.getConsignments()).thenReturn(Collections.singleton(consignmentModel));
		when(orderModel.getEntries()).thenReturn(Collections.singletonList(orderEntryModel));
		when(orderModel.getDeliveryStatus()).thenReturn(DeliveryStatus.IN_PROGRESS);
		when(consignmentModel.getStatus()).thenReturn(ConsignmentStatus.SHIPPED);
		when(consignmentModel.getConsignmentEntries()).thenReturn(Collections.singleton(consignmentEntryModel));
		when(orderEntryModel.getProduct()).thenReturn(orderProductModel);
		when(consignmentOrderEntryModel.getProduct()).thenReturn(consignmentProductModel);
		when(consignmentEntryModel.getOrderEntry()).thenReturn(consignmentOrderEntryModel);

		when(amwayExternalConsignmentService.createOrderConsignments(any(ShippingEvent.class), eq(orderModel), eq(warehouseModel), any(ConsignmentStatus.class)))
				.thenReturn(Lists.newArrayList(createSuccessInfo()));

		defaultAmwayShipmentConfirmationFacade.confirmShipment(new ShippingEvent(), "", "");

		verify(eventService, never()).publishEvent(any());
	}

	@Test
	public void testConfirmationNotTriggeredIfConsignmentWereNotCreated() throws AmwayShipmentConfirmationException
	{
		when(orderModel.getConsignments()).thenReturn(Collections.singleton(consignmentModel));
		when(orderModel.getEntries()).thenReturn(Collections.singletonList(orderEntryModel));
		when(orderModel.getDeliveryStatus()).thenReturn(DeliveryStatus.PENDING);
		when(consignmentModel.getStatus()).thenReturn(ConsignmentStatus.IGNORE);
		when(consignmentEntryModel.getOrderEntry()).thenReturn(consignmentOrderEntryModel);

		defaultAmwayShipmentConfirmationFacade.confirmShipment(new ShippingEvent(), "", "");

		verify(eventService, never()).publishEvent(any());
	}

	private AmwayConsignmentCreationInfo createSuccessInfo()
	{
		return createInfo(AmwayfulfillmentConstants.SUCCESS);
	}

	private AmwayConsignmentCreationInfo createInfo(String status)
	{
		final AmwayConsignmentCreationInfo consignmentModification = new AmwayConsignmentCreationInfo();
		consignmentModification.setErrorCode(status);
		return consignmentModification;
	}

}
