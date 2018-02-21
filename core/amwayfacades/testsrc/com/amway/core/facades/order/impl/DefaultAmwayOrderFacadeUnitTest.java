package com.amway.core.facades.order.impl;

import static org.mockito.BDDMockito.given;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import com.amway.core.customer.service.AmwayCustomerAccountService;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.returns.services.AmwayReturnService;
import com.amway.core.service.AmwayAccountCommerceService;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.enums.ExportStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.ordercancel.OrderCancelException;
import de.hybris.platform.ordercancel.OrderCancelRequest;
import de.hybris.platform.ordercancel.OrderCancelService;
import de.hybris.platform.ordercancel.model.OrderCancelRecordEntryModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;


@UnitTest
public class DefaultAmwayOrderFacadeUnitTest
{

	@Mock
	private AmwayCustomerAccountService amwayCustomerAccountService;
	@Mock
	private OrderCancelService orderCancelService;
	@Mock
	private AmwayAccountCommerceService amwayAccountCommerceService;
	@Mock
	private EventService eventService;
	@Mock
	private AmwayReturnService amwayReturnService;
	@Mock
	private BaseStoreService baseStoreServic;
	@Mock
	private UserService userService;

	@Mock
	private Converter<OrderModel, OrderData> orderConverter;

	@Mock
	private CheckoutCustomerStrategy checkoutCustomerStrategy;

	@InjectMocks
	private DefaultAmwayOrderFacade defaultAmwayOrderFacade = new DefaultAmwayOrderFacade();

	private final static String ORDER_CODE = "10000";
	private final static String ORDER_CODE1 = "20000";
	private final static String AMWAYCART_TYPE = "web";
	private final static String AMWAYCART_TYPE1 = "pos";
	private BaseStoreModel store;
	private CustomerModel customer;
	private OrderModel order;
	private OrderCancelRequest orderCancelRequest;
	private OrderCancelRecordEntryModel orderRequestRecord;
	private OrderModel nullOrder;
	private OrderData orderData;
	private AbstractOrderEntryModel entry1, entry2;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		store = Mockito.mock(BaseStoreModel.class);
		customer = Mockito.mock(CustomerModel.class);
		order = new OrderModel();
		order.setExportStatus(ExportStatus.NOTEXPORTED);
		order.setCode(ORDER_CODE);
		orderCancelRequest = Mockito.mock(OrderCancelRequest.class);
		orderRequestRecord = new OrderCancelRecordEntryModel();
		orderRequestRecord.setCode("12345634");
		nullOrder = null;
		orderData = new OrderData();
		orderData.setCode(ORDER_CODE);
		entry1 = new AbstractOrderEntryModel();
		entry1.setEntryNumber(0);
		entry1.setQuantity(Long.valueOf(1));

		entry2 = new AbstractOrderEntryModel();
		entry2.setEntryNumber(0);
		entry2.setQuantity(Long.valueOf(1));

		order.setEntries(Arrays.asList(entry1, entry2));

		given((CustomerModel) userService.getCurrentUser()).willReturn(customer);
		given(baseStoreServic.getCurrentBaseStore()).willReturn(store);
	}

	//	@Test
	//	public void testCancelOrder() throws OrderCancelException {
	//
	//		given((CustomerModel)userService.getCurrentUser()).willReturn(customer);
	//		given(baseStoreServic.getCurrentBaseStore()).willReturn(store);
	//		given(amwayCustomerAccountService.getOrderForCodeAndType(customer, ORDER_CODE, store, AmwayCartType.valueOf(AMWAYCART_TYPE))).willReturn(order);
	////		given(new OrderCancelRequest(order)).willReturn(orderCancelRequest);
	//		given(orderCancelService.requestOrderCancel(orderCancelRequest, customer)).willReturn(orderRequestRecord);
	//		Assert.assertTrue(defaultAmwayOrderFacade.cancelOrder(ORDER_CODE, AmwayCartType.valueOf(AMWAYCART_TYPE)));
	//	}
	// it is for either order is null or order export status is Exported
	@Test(expected = OrderCancelException.class)
	public void testCancelOrderForNullOrExportedOrder() throws OrderCancelException
	{
		order.setExportStatus(ExportStatus.EXPORTED);
		given((CustomerModel) userService.getCurrentUser()).willReturn(customer);
		given(baseStoreServic.getCurrentBaseStore()).willReturn(store);
		given(amwayCustomerAccountService
				.getOrderForCodeAndType(customer, ORDER_CODE, store, AmwayCartType.valueOf(AMWAYCART_TYPE))).willReturn(order);
		given(orderCancelService.requestOrderCancel(orderCancelRequest, customer)).willReturn(orderRequestRecord);
		Assert.assertTrue(defaultAmwayOrderFacade.cancelOrder(ORDER_CODE, AmwayCartType.valueOf(AMWAYCART_TYPE)));

		given(amwayCustomerAccountService
				.getOrderForCodeAndType(customer, ORDER_CODE1, store, AmwayCartType.valueOf(AMWAYCART_TYPE1))).willReturn(nullOrder);
		given(orderCancelService.requestOrderCancel(orderCancelRequest, customer)).willReturn(orderRequestRecord);
		Assert.assertTrue(defaultAmwayOrderFacade.cancelOrder(ORDER_CODE, AmwayCartType.valueOf(AMWAYCART_TYPE)));

	}

	// if OrderCancelRecordEntryModel is null
	@Test(expected = NullPointerException.class)
	public void testCancelOrderForNullCancelRequestEntry() throws OrderCancelException
	{
		order.setExportStatus(ExportStatus.NOTEXPORTED);
		given(amwayCustomerAccountService
				.getOrderForCodeAndType(customer, ORDER_CODE, store, AmwayCartType.valueOf(AMWAYCART_TYPE))).willReturn(order);
		given(orderCancelService.requestOrderCancel(orderCancelRequest, customer)).willReturn(null);
		Assert.assertFalse(defaultAmwayOrderFacade.cancelOrder(ORDER_CODE, AmwayCartType.valueOf(AMWAYCART_TYPE)));
	}

	@Test
	public void getOrderDetailsForCodeTest()
	{
		given(checkoutCustomerStrategy.isAnonymousCheckout()).willReturn(Boolean.FALSE);
		given(baseStoreServic.getCurrentBaseStore()).willReturn(store);
		given(amwayCustomerAccountService.getOrderForCode(customer, ORDER_CODE, store)).willReturn(order);
		assertNotNull("Should not be null", order);
		given(orderConverter.convert(order)).willReturn(orderData);
		OrderData data = defaultAmwayOrderFacade.getOrderDetailsForCode(ORDER_CODE);
		assertEquals("Order code should be same", data.getCode(), ORDER_CODE);

	}

	@Test
	public void getOrderDetailsForCodeForAnonymousCustomerTest()
	{
		given(checkoutCustomerStrategy.isAnonymousCheckout()).willReturn(Boolean.TRUE);
		given(baseStoreServic.getCurrentBaseStore()).willReturn(store);
		given(amwayCustomerAccountService.getOrderDetailsForGUID(ORDER_CODE, store)).willReturn(order);
		assertNotNull("Should not be null", order);
		given(orderConverter.convert(order)).willReturn(orderData);
		OrderData data = defaultAmwayOrderFacade.getOrderDetailsForCode(ORDER_CODE);
		assertEquals("Order code should be same", data.getCode(), ORDER_CODE);

	}

	@Test(expected = UnknownIdentifierException.class)
	public void getOrderDetailsForCodeNonExistingOrderTest()
	{
		given(checkoutCustomerStrategy.isAnonymousCheckout()).willReturn(Boolean.FALSE);
		given(baseStoreServic.getCurrentBaseStore()).willReturn(store);
		given(amwayCustomerAccountService
				.getOrderForCodeAndType(customer, ORDER_CODE, store, AmwayCartType.valueOf(AMWAYCART_TYPE))).willReturn(order);
		assertNotNull("Should not be null", order);
		given(orderConverter.convert(order)).willReturn(orderData);
		OrderData data = defaultAmwayOrderFacade.getOrderDetailsForCode(ORDER_CODE);
		assertEquals("Order code should be same", data.getCode(), ORDER_CODE);

	}

	@Test
	public void sendOrderReturnNotificationTest()
	{
		given(baseStoreServic.getCurrentBaseStore()).willReturn(store);
		given(amwayCustomerAccountService
				.getOrderForCodeAndType(customer, ORDER_CODE, store, AmwayCartType.valueOf(AMWAYCART_TYPE))).willReturn(order);
		assertNotNull("Should not be null", order);
		given(amwayReturnService.isReturnPossible(order)).willReturn(Boolean.TRUE);
		assertTrue(defaultAmwayOrderFacade.sendOrderReturnNotification(ORDER_CODE, AmwayCartType.valueOf(AMWAYCART_TYPE)));
	}
}
