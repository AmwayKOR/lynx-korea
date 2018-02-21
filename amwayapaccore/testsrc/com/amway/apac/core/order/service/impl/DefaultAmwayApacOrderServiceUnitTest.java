package com.amway.apac.core.order.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


/**
 * Test class for Junit test cases for {@DefaultAmwayApacOrderService}
 */
@UnitTest
public class DefaultAmwayApacOrderServiceUnitTest
{
	@InjectMocks
	private final DefaultAmwayApacOrderService amwayApacOrderService = new DefaultAmwayApacOrderService();

	private OrderModel order;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		order = Mockito.mock(OrderModel.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsOrderPaymentCapturedWithNullOrder()
	{
		amwayApacOrderService.isOrderPaymentCaptured(null);
	}

	@Test
	public void testIsOrderPaymentCapturedWithNullTransactions()
	{
		Mockito.doReturn(null).when(order).getPaymentTransactions();
		Assert.assertEquals(Boolean.FALSE, Boolean.valueOf(amwayApacOrderService.isOrderPaymentCaptured(order)));
	}

	@Test
	public void testIsOrderPaymentCapturedWithNullTransactionEntries()
	{
		final PaymentTransactionModel transaction = Mockito.mock(PaymentTransactionModel.class);
		final List<PaymentTransactionModel> transactions = Arrays.asList(transaction);

		Mockito.doReturn(null).when(transaction).getEntries();
		Mockito.doReturn(transactions).when(order).getPaymentTransactions();
		Assert.assertEquals(Boolean.FALSE, Boolean.valueOf(amwayApacOrderService.isOrderPaymentCaptured(order)));
	}
}
