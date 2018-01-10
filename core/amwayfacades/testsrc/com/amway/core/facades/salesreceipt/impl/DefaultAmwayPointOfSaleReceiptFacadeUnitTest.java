package com.amway.core.facades.salesreceipt.impl;

import com.amway.core.facades.customer.AmwayCustomerFacade;
import com.amway.core.model.AmwayBatchModel;
import com.amway.core.model.AmwayTerminalModel;
import com.amway.core.order.services.AmwayOrderService;
import com.amway.facades.data.AmwayPointOfSaleReceiptData;
import com.amway.facades.data.AmwayAccountData;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.storelocator.data.PointOfServiceData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.PrincipalData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import com.amway.facades.cart.data.PaymentDetailsData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@UnitTest
public class DefaultAmwayPointOfSaleReceiptFacadeUnitTest
{
	private static final String ORDERID = "100001";

	@InjectMocks
	private final DefaultAmwayPointOfSaleReceiptFacade salesReceiptFacade = new DefaultAmwayPointOfSaleReceiptFacade();

	@Mock
	private AmwayOrderService amwayOrderService;
	@Mock
	private AmwayCustomerFacade amwayCustomerFacade;
	@Mock
	private Converter<PointOfServiceModel, PointOfServiceData> pointOfServiceConverter;
	@Mock
	private Converter<OrderModel, OrderData> orderConverter;
	@Mock
	private Converter<PrincipalModel, PrincipalData> principalConverter;
	@Mock
	private Converter<CustomerModel, CustomerData> customerConverter;
	@Mock
	private Converter<AddressModel, AddressData> addressConverter;
	@Mock
	private Converter<AbstractOrderModel, PaymentDetailsData> amwayPaymentDetailsConverter;
	@Mock
	private OrderModel orderModel;
	@Mock
	private UserModel employeeModel;
	@Mock
	private PointOfServiceModel pointOfServiceModel;
	@Mock
	private OrderData orderData;
	@Mock
	private PrincipalData employeeData;
	@Mock
	private PointOfServiceData pointOfServiceData;
	@Mock
	private AmwayAccountData accountData;
	@Mock
	private CustomerModel customer;
	@Mock
	private CustomerData customerData;
	@Mock
	private AddressModel addressModel;
	@Mock
	private AddressData addressData;
	@Mock
	private AmwayBatchModel amwayBatchModel;
	@Mock
	private AmwayTerminalModel amwayTerminalModel;
	@Mock
	private PaymentDetailsData paymentDetailsData;

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		given(orderModel.getUser()).willReturn(customer);
		given(customer.getDefaultPaymentAddress()).willReturn(addressModel);
		given(customer.getDefaultShipmentAddress()).willReturn(addressModel);
		given(orderModel.getEmployee()).willReturn(employeeModel);
		given(orderModel.getBatch()).willReturn(amwayBatchModel);
		given(amwayBatchModel.getTerminal()).willReturn(amwayTerminalModel);
		given(amwayTerminalModel.getPointOfService()).willReturn(pointOfServiceModel);
	}

	@Test
	public void testSalesReceiptWhenOrderIsPlaced()
	{
		given(amwayOrderService.getOrderDetailsForCode(ORDERID)).willReturn(orderModel);
		given(principalConverter.convert(employeeModel)).willReturn(employeeData);
		given(pointOfServiceConverter.convert(pointOfServiceModel)).willReturn(pointOfServiceData);
		given(customerConverter.convert(customer)).willReturn(customerData);
		given(addressConverter.convert(addressModel)).willReturn(addressData);
		given(orderConverter.convert(orderModel)).willReturn(orderData);
		given(amwayPaymentDetailsConverter.convert(orderModel)).willReturn(paymentDetailsData);
		given(amwayCustomerFacade.getCurrentAccount()).willReturn(accountData);

		AmwayPointOfSaleReceiptData salesReceiptData = salesReceiptFacade.getSalesReceipt(ORDERID);

		assertNotNull(salesReceiptData);

		verify(amwayOrderService, times(1)).getOrderDetailsForCode(ORDERID);
	}

	@Test(expected = ModelNotFoundException.class)
	public void testSalesReceiptWhenOrderIsNotPlaced()
	{
		given(amwayOrderService.getOrderDetailsForCode(ORDERID)).willThrow(ModelNotFoundException.class);

		AmwayPointOfSaleReceiptData salesReceiptData = salesReceiptFacade.getSalesReceipt(ORDERID);

		assertNotNull(salesReceiptData);

		verify(amwayOrderService, times(1)).getOrderDetailsForCode(ORDERID);
	}
}
