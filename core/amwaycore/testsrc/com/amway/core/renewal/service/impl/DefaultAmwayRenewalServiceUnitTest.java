package com.amway.core.renewal.service.impl;

import com.amway.core.payment.service.AmwayPaymentService;
import com.amway.core.renewal.dao.AmwayRenewalDao;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.commerceservices.service.data.CommerceOrderResult;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.strategies.ordercloning.CloneAbstractOrderStrategy;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@UnitTest
public class DefaultAmwayRenewalServiceUnitTest
{
	private static final String ORDER_CODE = "ORDER_CODE";

	@Mock
	private CloneAbstractOrderStrategy cloneAbstractOrderStrategy;
	@Mock
	private CommerceCheckoutService commerceCheckoutService;
	@Mock
	private AmwayPaymentService amwayPaymentService;
	@Mock
	private DeliveryService deliveryService;
	@Mock
	private AmwayRenewalDao amwayRenewalDao;
	@Mock
	private ModelService modelService;
	@Mock
	private KeyGenerator keyGenerator;
	@Mock
	private SessionService sessionService;

	@Mock
	private PaymentTransactionEntryModel authorisationTransactionEntry;
	@Mock
	private PaymentTransactionEntryModel captureTransactionEntry;
	@Mock
	private PaymentTransactionModel orderTransaction;
	@Spy
	private OrderModel orderModel;
	@Mock
	private CartModel cartModel;
	@Mock
	private CommerceOrderResult placeResult;
	@Mock
	private CreditCardPaymentInfoModel orderPaymentInfo;
	@Mock
	private UserModel user;

	@InjectMocks
	private DefaultAmwayRenewalService renewalService;

	@Before
	public void setup() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		when(authorisationTransactionEntry.getTransactionStatus()).thenReturn("ACCEPTED");
		when(captureTransactionEntry.getTransactionStatus()).thenReturn("ACCEPTED");
		when(amwayPaymentService.capture(orderTransaction)).thenReturn(captureTransactionEntry);
		when(commerceCheckoutService.placeOrder(any(CommerceCheckoutParameter.class))).thenReturn(placeResult);
		when(commerceCheckoutService.authorizePayment(any(CommerceCheckoutParameter.class)))
				.thenReturn(authorisationTransactionEntry);
		when(placeResult.getOrder()).thenReturn(orderModel);
		when(orderModel.getCode()).thenReturn(ORDER_CODE);
		when(orderModel.getPaymentInfo()).thenReturn(orderPaymentInfo);
		when(cartModel.getPaymentInfo()).thenReturn(orderPaymentInfo);

		when(orderTransaction.getEntries()).thenReturn(Collections.singletonList(authorisationTransactionEntry));
		when(orderModel.getPaymentTransactions()).thenReturn(Collections.singletonList(orderTransaction));
	}

	@Test
	public void shouldReturnNullIfOrderPlaceFailed() throws InvalidCartException
	{
		when(placeResult.getOrder()).thenReturn(null);
		assertNull(renewalService.placeRenewal(cartModel));
	}

	@Test
	public void shouldRetrieveTheLastRenewalOrderForCustomer()
	{
		when(amwayRenewalDao.getLastRenewalOrder(user)).thenReturn(Optional.of(orderModel));
		assertEquals(orderModel, renewalService.getLastRenewalOrder(user));
	}

}
