/**
 *
 */
package com.amway.core.payment.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.cart.data.AmwayCCPaymentInfoData;
import com.amway.core.cart.data.AmwayCashPaymentInfoData;
import com.amway.core.order.data.PaymentModeData;


/**
 * Integration test for AmwaySplitPaymentsServiceImpl
 */
//TODO: To be updated for AR payments
@IntegrationTest
public class AmwaySplitPaymentsServiceImplIntegrationTest extends AbstractPaymentServiceIntegrationTest
{

	private static final Logger LOG = Logger.getLogger(AmwaySplitPaymentsServiceImplIntegrationTest.class);
	protected static final String CASH_PAYMENT_MODE = "cash";
	protected static final String CREDIT_CARD_PAYMENT_MODE = "creditCard";
	protected static final Double CURRENT_PAYMENT_AMOUNT = 500d;

	@Resource
	private AmwaySplitPaymentsServiceImpl amwaySplitPaymentsService;

	protected AmwayCashPaymentInfoData amwayCashPaymentInfoData;

	protected PaymentModeData cashPaymentModeData;

	protected AmwayCCPaymentInfoData amwayCCPaymentInfoData;

	protected PaymentModeData ccPaymentModeData;


	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
		setUpUserOrdersData();
	}

	@Test
	public void testSavePaymentTransactionIfCashPaymentForResult()
	{
		final PaymentTransactionModel paymentTransactionResult = amwaySplitPaymentsService
				.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, amwayCashPaymentInfoData);
		checkifPaymentSuccessful(paymentTransactionResult, cartWithEntries, amwayCashPaymentInfoData.getAmount());
	}

	@Test
	public void testSavePaymentTransactionIfCCPaymentForResult()
	{
		final PaymentTransactionModel paymentTransactionResult = amwaySplitPaymentsService
				.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, amwayCCPaymentInfoData);
		checkifPaymentSuccessful(paymentTransactionResult, cartWithEntries, amwayCCPaymentInfoData.getAmount());
	}

	@Test
	public void testSavePaymentTransactionForPaymentStatusIfFullPayment()
	{
		amwayCashPaymentInfoData.setAmount(cartWithEntries.getTotalPrice());
		amwaySplitPaymentsService.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, amwayCashPaymentInfoData);
		Assert.assertEquals(cartWithEntries.getPaymentStatus(), PaymentStatus.PAID);
	}

	@Test
	public void testSavePaymentTransactionForPaymentStatusIfPartialPayment()
	{
		amwayCashPaymentInfoData.setAmount(cartWithEntries.getTotalPrice() - 1);
		amwaySplitPaymentsService.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, amwayCashPaymentInfoData);
		Assert.assertEquals(cartWithEntries.getPaymentStatus(), PaymentStatus.PARTPAID);
	}

	@Test
	public void testSavePaymentTransactionForPaymentStatusIfOverPayment()
	{
		amwayCashPaymentInfoData.setAmount(cartWithEntries.getTotalPrice() + 1);
		amwaySplitPaymentsService.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, amwayCashPaymentInfoData);
		Assert.assertEquals(cartWithEntries.getPaymentStatus(), PaymentStatus.OVERPAID);
	}

	@Test
	public void testSaveOverpayReturnTransactionIfCashPaymentForResult()
	{
		final double overPaymentAmount = 1;
		final OrderModel order = getOrder(CUSTOMR_UID1, PaymentStatus.PARTPAID);
		Assert.assertNotNull(order);

		// Create an overpayment transaction for the order
		amwayCashPaymentInfoData.setAmount(order.getTotalPrice() + overPaymentAmount);
		amwaySplitPaymentsService.savePaymentTransaction(order.getUser(), order, amwayCashPaymentInfoData);

		//Create Overpay return transaction
		amwayCashPaymentInfoData.setAmount(overPaymentAmount);
		final PaymentTransactionModel paymentTransactionResult = amwaySplitPaymentsService
				.saveOverpayReturnPaymentTransaction(order.getUser(), order, amwayCashPaymentInfoData);
		checkifOverpayReturnPaymentSuccessful(paymentTransactionResult, order, overPaymentAmount);
	}


	@Test
	public void testSaveOverpayReturnTransactionIfCCPaymentForResult()
	{
		final double overPaymentAmount = 1;
		final OrderModel order = getOrder(CUSTOMR_UID1, PaymentStatus.PARTPAID);
		Assert.assertNotNull(order);

		// Create an overpayment transaction for the order
		amwayCCPaymentInfoData.setAmount(order.getTotalPrice() + overPaymentAmount);
		amwaySplitPaymentsService.savePaymentTransaction(order.getUser(), order, amwayCCPaymentInfoData);

		//Create Overpay return transaction
		amwayCCPaymentInfoData.setAmount(overPaymentAmount);
		final PaymentTransactionModel paymentTransactionResult = amwaySplitPaymentsService
				.saveOverpayReturnPaymentTransaction(order.getUser(), order, amwayCCPaymentInfoData);
		checkifOverpayReturnPaymentSuccessful(paymentTransactionResult, order, overPaymentAmount);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testSavePaymentTransactionIfUserIsNull()
	{
		amwaySplitPaymentsService.savePaymentTransaction(null, cartWithEntries, amwayCashPaymentInfoData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSavePaymentTransactionIfCartIsNull()
	{
		amwaySplitPaymentsService.savePaymentTransaction(cartWithEntries.getUser(), null, amwayCashPaymentInfoData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSavePaymentTransactionIfPaymentInfoIsNull()
	{
		amwaySplitPaymentsService.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSavePaymentTransactionIfPaymentTransactionIdIsNull()
	{
		amwayCashPaymentInfoData.setTransactionid(null);
		amwaySplitPaymentsService.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, amwayCashPaymentInfoData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSavePaymentTransactionIfPaymentAmountIsNull()
	{
		amwayCashPaymentInfoData.setAmount(null);
		amwaySplitPaymentsService.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, amwayCashPaymentInfoData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveOverpayReturnTransactionIfUserIsNull()
	{
		amwaySplitPaymentsService.saveOverpayReturnPaymentTransaction(null, cartWithEntries, amwayCashPaymentInfoData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveOverpayReturnTransactionIfCartIsNull()
	{
		amwaySplitPaymentsService.saveOverpayReturnPaymentTransaction(cartWithEntries.getUser(), null, amwayCashPaymentInfoData);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveOverpayReturnTransactionIfPaymentInfoIsNull()
	{
		amwaySplitPaymentsService.saveOverpayReturnPaymentTransaction(cartWithEntries.getUser(), cartWithEntries, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSaveOverpayReturnTransactionIfPaymentAmountIsNull()
	{
		amwayCashPaymentInfoData.setAmount(null);
		amwaySplitPaymentsService.saveOverpayReturnPaymentTransaction(cartWithEntries.getUser(), cartWithEntries,
				amwayCashPaymentInfoData);
	}

	private void checkifPaymentSuccessful(final PaymentTransactionModel paymentTransactionResult, final AbstractOrderModel cart,
			final Double amount)
	{
		Assert.assertNotNull(paymentTransactionResult);
		Assert.assertTrue(CollectionUtils.isNotEmpty(cart.getPaymentTransactions()));
		Assert.assertTrue(CollectionUtils.isNotEmpty(cart.getPaymentTransactions().get(0).getEntries()));
		Assert.assertTrue(CollectionUtils.isNotEmpty(cart.getPaymentInfos()));
		Assert.assertTrue(cart.getPaymentTransactions().contains(paymentTransactionResult));
		Assert.assertEquals(paymentTransactionResult.getEntries().get(0).getAmount(), BigDecimal.valueOf(amount));
	}

	private void checkifOverpayReturnPaymentSuccessful(final PaymentTransactionModel paymentTransactionResult,
			final OrderModel order, final Double amount)
	{
		Assert.assertNotNull(paymentTransactionResult);
		Assert.assertTrue(CollectionUtils.isNotEmpty(order.getRefundTransactions()));
		Assert.assertTrue(CollectionUtils.isNotEmpty(order.getRefundTransactions().get(0).getEntries()));
		Assert.assertTrue(CollectionUtils.isNotEmpty(order.getPaymentInfos()));
		Assert.assertTrue(order.getRefundTransactions().contains(paymentTransactionResult));
		Assert.assertEquals(paymentTransactionResult.getEntries().get(0).getAmount(), BigDecimal.valueOf(amount));
		Assert.assertEquals(order.getPaymentStatus(), PaymentStatus.PAID);
	}

	private void setUpUserOrdersData() throws ImpExException
	{
		importCsv(BASE_RESOURCE_PATH + "/orderTestData.csv", "utf-8");
	}

	@Override
	protected void setUpPaymentsData() throws ImpExException
	{
		super.setUpPaymentsData();
		cashPaymentModeData = new PaymentModeData();
		cashPaymentModeData.setCode(CASH_PAYMENT_MODE);
		cashPaymentModeData.setActive(true);
		amwayCashPaymentInfoData = new AmwayCashPaymentInfoData();
		final String randomId = "123456789";
		amwayCashPaymentInfoData.setAmount(CURRENT_PAYMENT_AMOUNT);
		amwayCashPaymentInfoData.setPaymentMode(cashPaymentModeData);
		amwayCashPaymentInfoData.setTransactionid(randomId);
		ccPaymentModeData = new PaymentModeData();
		ccPaymentModeData.setCode(CREDIT_CARD_PAYMENT_MODE);
		ccPaymentModeData.setActive(true);
		amwayCCPaymentInfoData = new AmwayCCPaymentInfoData();
		amwayCCPaymentInfoData.setAmount(CURRENT_PAYMENT_AMOUNT);
		amwayCCPaymentInfoData.setPaymentMode(ccPaymentModeData);
		amwayCCPaymentInfoData.setMaskedcardnumber(randomId);
		amwayCCPaymentInfoData.setTransactionid(randomId);
		amwayCCPaymentInfoData.setCardtype("VISA");
	}

}
