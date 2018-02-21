/**
 *
 */
package com.amway.core.payment.strategies;

import com.amway.core.cart.data.AmwayCashPaymentInfoData;
import com.amway.core.order.data.PaymentModeData;
import com.amway.core.payment.service.impl.AbstractPaymentServiceIntegrationTest;
import com.amway.core.payment.service.impl.AmwaySplitPaymentsServiceImpl;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * Integration test for AmwayAdjustOverpaymentStrategy
 */
@IntegrationTest
public class AmwayAdjustOverpaymentStrategyIntegrationTest extends AbstractPaymentServiceIntegrationTest
{

	protected static final String CASH_PAYMENT_MODE = "cash";

	protected static final Double CURRENT_PAYMENT_AMOUNT = 500d;

	@Resource
	AmwayAdjustOverpaymentStrategy amwayAdjustOverpaymentStrategy;
	@Resource
	private AmwaySplitPaymentsServiceImpl amwaySplitPaymentsService;

	protected AmwayCashPaymentInfoData amwayCashPaymentInfoData;

	protected PaymentModeData cashPaymentModeData;

	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
		setUpUserOrdersData();
	}

	@Test
	public void testAdjustOverpaymentForResult()
	{
		final double overPaymentAmount = 1;
		final OrderModel order = getOrder(CUSTOMR_UID1, PaymentStatus.PARTPAID);
		Assert.assertNotNull(order);

		// Create an overpayment transaction for the order
		amwayCashPaymentInfoData.setAmount(order.getTotalPrice() + overPaymentAmount);
		amwaySplitPaymentsService.savePaymentTransaction(order.getUser(), order, amwayCashPaymentInfoData);

		//Adjust overpayments
		amwayAdjustOverpaymentStrategy.adjustOverpayments(order);

		Assert.assertTrue(CollectionUtils.isNotEmpty(order.getRefundTransactions()));
		Assert.assertTrue(CollectionUtils.isNotEmpty(order.getRefundTransactions().get(0).getEntries()));
		Assert.assertTrue(CollectionUtils.isNotEmpty(order.getPaymentInfos()));
		Assert.assertTrue(order.getPaymentInfos().size() == 2);
		Assert.assertEquals(order.getRefundTransactions().get(0).getEntries().get(0).getAmount(),
				BigDecimal.valueOf(overPaymentAmount));
		Assert.assertEquals(order.getPaymentStatus(), PaymentStatus.PAID);
	}

	@Test
	public void testAdjustOverpaymentForNonOverPaidOrder() throws BusinessException
	{
		final OrderModel overpaidOrder = getOrder(CUSTOMR_UID1, PaymentStatus.PAID);
		Assert.assertNotNull(overpaidOrder);
		//Adjust overpayments
		amwayAdjustOverpaymentStrategy.adjustOverpayments(overpaidOrder);
		Assert.assertTrue(CollectionUtils.isEmpty(overpaidOrder.getRefundTransactions()));
	}

	private void setUpUserOrdersData() throws ImpExException
	{
		importCsv(BASE_RESOURCE_PATH + "/orderTestData.csv", "utf-8");
	}

	@Override
	protected void setUpCartData() throws Exception
	{
		super.setUpCartData();
		setCurrentBaseSite();
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
	}

}
