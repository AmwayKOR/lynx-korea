/**
 *
 */
package com.amway.core.facades.payment.impl;

import com.amway.core.balance.services.AmwayAccountBalanceService;
import com.amway.core.dms.data.GetBalanceResponseData;
import com.amway.core.facades.order.validators.impl.AbstractOrderPaymentsIntegrationTest;
import com.amway.facades.cart.data.PaymentDetailsData;
import com.amway.facades.data.CapturePaymentInfoData;
import de.hybris.bootstrap.annotations.IntegrationTest;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static org.mockito.Mockito.when;


/**
 *
 */
@IntegrationTest
//TODO: to be updated for AR payments
public class DefaultAmwayPaymentFacadeIntegrationTest extends AbstractOrderPaymentsIntegrationTest
{

	public static final String SESSION_CART_PARAMETER_NAME = "cart";

	@Resource
	private DefaultAmwayPaymentFacade amwayPaymentFacade;

	@Mock
	private CapturePaymentInfoData capturePaymentInfoData;
	@Mock
	private AmwayAccountBalanceService amwayAccountBalanceService;
	@Mock
	private GetBalanceResponseData balanceResponseData;

	@Before
	@Override
	public void prepare() throws Exception
	{
		super.prepare();
		MockitoAnnotations.initMocks(this);
		sessionService.setAttribute(SESSION_CART_PARAMETER_NAME, cart);
		amwayPaymentFacade.setAmwayAccountBalanceService(amwayAccountBalanceService);
		when(amwayAccountBalanceService.getAccountBalance(cart)).thenReturn(balanceResponseData);
		when(balanceResponseData.getAccountBalance()).thenReturn(null);
		when(capturePaymentInfoData.getCashPaymentInfo()).thenReturn(amwayCashPaymentInfoData);
		when(capturePaymentInfoData.getCcPaymentInfo()).thenReturn(amwayCCPaymentInfoData);
	}

	@Test
	public void testCapturePaymentForSuccess()
	{
		final double amount = 10D;
		amwayCashPaymentInfoData.setAmount(amount);
		amwayPaymentFacade.capturePayment(capturePaymentInfoData, errors);
		Assert.assertTrue("Error in capture payment", !errors.hasErrors());
		checkifPaymentSuccessful(amount);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCapturePaymentIfPaymentInfoDataIsNull()
	{
		amwayPaymentFacade.capturePayment(null, errors);
	}

	@Test
	public void testValidatePayment()
	{
		amwayCashPaymentInfoData.setAmount(10D);
		amwayPaymentFacade.validatePayment(cart, amwayCashPaymentInfoData, errors);
		Assert.assertTrue("Validation failed", !errors.hasErrors());
	}

	@Test
	public void testValidatePaymentIfErrorOccurs()
	{
		amwayCCPaymentInfoData.setAmount(cart.getTotalPrice() + 1);
		amwayPaymentFacade.validatePayment(cart, amwayCCPaymentInfoData, errors);
		Assert.assertTrue("Validation failed", errors.hasErrors());
	}

	@Test
	public void testGetCartPaymentInfoDetails()
	{
		final double amount = 10D;
		amwayCashPaymentInfoData.setAmount(amount);
		amwayPaymentFacade.capturePayment(capturePaymentInfoData, errors);
		Assert.assertTrue("Error in capture payment", !errors.hasErrors());
		final PaymentDetailsData paymentDetailsData = amwayPaymentFacade.getCartPaymentInfoDetails(cart);
		Assert.assertNotNull(paymentDetailsData);
	}


	private void checkifPaymentSuccessful(final Double amount)
	{
		Assert.assertTrue(CollectionUtils.isNotEmpty(cart.getPaymentTransactions()));
		Assert.assertTrue(CollectionUtils.isNotEmpty(cart.getPaymentTransactions().get(0).getEntries()));
		Assert.assertTrue(CollectionUtils.isNotEmpty(cart.getPaymentInfos()));
		Assert.assertEquals(cart.getPaymentTransactions().get(0).getEntries().get(0).getAmount(), BigDecimal.valueOf(amount));
	}

}
