/**
 *
 */
package com.amway.core.order.interceptor.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.order.strategies.ordercloning.OrderPartOfMembersCloningStrategy;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.model.ItemModelContextImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



@UnitTest
public class DefaultAmwayOrderPrepareInterceptorUnitTest
{
	@InjectMocks
	private final DefaultAmwayOrderPrepareInterceptor interceptor = new DefaultAmwayOrderPrepareInterceptor();
	@Mock
	private InterceptorContext ctx;
	@Mock
	private ItemModelContextImpl itemContext;
	@Mock
	private OrderPartOfMembersCloningStrategy cloningStrategy;
	private OrderModel order;
	private PaymentInfoModel paymentInfo1, paymentInfo2;
	private PaymentInfoModel clonedPaymentInfo1, clonedPaymentInfo2;
	private PaymentTransactionModel paymentTransaction1, paymentTransaction2;
	private Set<PaymentInfoModel> paymentInfos1;
	private PaymentTransactionEntryModel paymentTransactionEntry1, paymentTransactionEntry2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		paymentInfo1 = new PaymentInfoModel();
		paymentInfo2 = new PaymentInfoModel();
		paymentInfos1 = new HashSet<>();
		paymentInfos1.add(paymentInfo1);
		paymentInfos1.add(paymentInfo2);
		paymentTransactionEntry1 = new PaymentTransactionEntryModel();
		paymentTransactionEntry1.setType(PaymentTransactionType.FRAUD_DECISION);
		paymentTransactionEntry1.setTransactionStatus("REVIEW");
		paymentTransactionEntry2 = new PaymentTransactionEntryModel();
		paymentTransactionEntry2.setType(PaymentTransactionType.FRAUD_DECISION);
		paymentTransactionEntry2.setTransactionStatus("REVIEW");

		paymentTransaction1 = new PaymentTransactionModel();
		paymentTransaction2 = new PaymentTransactionModel();
		paymentTransaction1.setInfo(paymentInfo1);
		paymentTransaction1.setEntries(Arrays.asList(paymentTransactionEntry1));
		paymentTransaction2.setInfo(paymentInfo2);
		paymentTransaction2.setEntries(Arrays.asList(paymentTransactionEntry2));

		clonedPaymentInfo1 = new PaymentInfoModel();
		clonedPaymentInfo2 = new PaymentInfoModel();
		order = new OrderModel();
		order.setPaymentInfos(paymentInfos1);
		order.setPaymentTransactions(Arrays.asList(paymentTransaction1, paymentTransaction2));

		when(Boolean.valueOf(cloningStrategy.paymentInfoNeedsCloning(paymentInfo1, order))).thenReturn(Boolean.TRUE);
		when(Boolean.valueOf(cloningStrategy.paymentInfoNeedsCloning(paymentInfo2, order))).thenReturn(Boolean.TRUE);
		when(cloningStrategy.clonePaymentInfoForOrder(paymentInfo1, order)).thenReturn(clonedPaymentInfo1);
		when(cloningStrategy.clonePaymentInfoForOrder(paymentInfo2, order)).thenReturn(clonedPaymentInfo2);
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.interceptor.impl.DefaultAmwayOrderPrepareInterceptor#onPrepare(java.lang.Object, de.hybris.platform.servicelayer.interceptor.InterceptorContext)}
	 * .
	 *
	 * @throws InterceptorException
	 */
	@Test
	public void testOnPrepareForPaymentInfos() throws InterceptorException
	{
		when(Boolean.valueOf(ctx.isModified(order, OrderModel.PAYMENTINFOS))).thenReturn(Boolean.TRUE);
		when(Boolean.valueOf(ctx.isNew(order))).thenReturn(Boolean.TRUE);
		interceptor.onPrepare(order, ctx);
		Assert.assertNotNull(order.getPaymentInfos().iterator().next());
	}

	/**
	 * Test method for
	 * {@link com.amway.core.order.interceptor.impl.DefaultAmwayOrderPrepareInterceptor#onPrepare(java.lang.Object, de.hybris.platform.servicelayer.interceptor.InterceptorContext)}
	 * .
	 *
	 * @throws InterceptorException
	 */
	@Test
	public void testOnPrepareForPaymentTransactions() throws InterceptorException
	{
		when(Boolean.valueOf(ctx.isModified(order, OrderModel.PAYMENTTRANSACTIONS))).thenReturn(Boolean.TRUE);
		interceptor.onPrepare(order, ctx);
		Assert.assertNotNull(order.getPaymentInfos().iterator().next());
	}

}
