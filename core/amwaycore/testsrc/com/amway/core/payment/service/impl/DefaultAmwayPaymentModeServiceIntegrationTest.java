/**
 *
 */
package com.amway.core.payment.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.cart.data.AmwayCashPaymentInfoData;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.order.data.PaymentModeData;


/**
 *
 */
@SuppressWarnings("deprecation")
@IntegrationTest
public class DefaultAmwayPaymentModeServiceIntegrationTest extends AbstractPaymentServiceIntegrationTest
{

	private static final Logger LOG = Logger.getLogger(DefaultAmwayPaymentModeServiceIntegrationTest.class);
	
	@Resource
	private AmwaySplitPaymentsServiceImpl amwaySplitPaymentsService;

	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
	}

	// TODO : to be updated for AR payments.
	@Test
	public void testGetSupportedPaymentModesCombinationWithProfileDataWithResult()
	{
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = amwayPaymentModeService
				.getSupportedPaymentModesCombination(cartWithEntries, amwayProfileData, true, CURRENT_PAYMENT_MODE);
		Assert.assertNotNull("No supported payment mode combinations found.", paymentModeCombinations);
		Assert.assertTrue("No supported payment mode combinations found.", paymentModeCombinations.size() > 0);
	}

	// TODO : to be updated for AR payments.
	@Test
	public void testGetSupportedPaymentModesCombinationWithProfileDataIfEmptyResult()
	{
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = amwayPaymentModeService
				.getSupportedPaymentModesCombination(emptyCart, amwayProfileData, true, CURRENT_PAYMENT_MODE);
		Assert.assertNotNull("Invalid supported payment mode combinations found.", paymentModeCombinations);
		Assert.assertTrue("Invalid supported payment mode combinations combinations found.", paymentModeCombinations.size() == 0);

	}

	// TODO : to be updated for AR payments.
	@Test
	public void testGetSupportedPaymentModesCombinationWithProfileDataIfCartPaymentsDone()
	{
		final PaymentModeData cashPaymentModeData = new PaymentModeData();
		final AmwayCashPaymentInfoData amwayCashPaymentInfoData = new AmwayCashPaymentInfoData();
		final String randomId = "123456789";		
		cashPaymentModeData.setCode(CURRENT_PAYMENT_MODE);
		cashPaymentModeData.setActive(true);
		amwayCashPaymentInfoData.setAmount(2D);
		amwayCashPaymentInfoData.setPaymentMode(cashPaymentModeData);
		amwayCashPaymentInfoData.setTransactionid(randomId);
		amwaySplitPaymentsService.savePaymentTransaction(cartWithEntries.getUser(), cartWithEntries, amwayCashPaymentInfoData);
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = amwayPaymentModeService
				.getSupportedPaymentModesCombination(cartWithEntries, amwayProfileData, false, CURRENT_PAYMENT_MODE);
		Assert.assertNotNull("Invalid supported payment mode combinations combinations found.", paymentModeCombinations);
		Assert.assertTrue("Invalid supported payment mode combinations found.", paymentModeCombinations.size() == 0);
	}

	// TODO : to be updated for AR payments.
	@Test
	public void testGetSupportedPaymentModesCombinationWithLimitsWithResult()
	{
		final Map<String, BigDecimal> payModeLimits = new HashMap<>();
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = amwayPaymentModeService
				.getSupportedPaymentModesCombination(cartWithEntries, payModeLimits, true, CURRENT_PAYMENT_MODE);
		Assert.assertNotNull("No supported payment mode combinations found.", paymentModeCombinations);
		Assert.assertTrue("No supported payment mode combinations found.", paymentModeCombinations.size() > 0);
	}

	// TODO : to be updated for AR payments.
	@Test
	public void testGetSupportedPaymentModesCombinationWithLimitsForModeLimitCheck()
	{
		final Map<String, BigDecimal> payModeLimits = new HashMap<>();
		payModeLimits.put("cash", BigDecimal.ZERO);
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = amwayPaymentModeService
				.getSupportedPaymentModesCombination(cartWithEntries, payModeLimits, true, CURRENT_PAYMENT_MODE);

		Assert.assertNotNull("Invalid supported payment mode combinations found.", paymentModeCombinations);
		Assert.assertTrue("Invalid supported payment mode combinations found.", paymentModeCombinations.size() == 0);
	}

	// TODO : to be updated for AR payments.
	@Test
	public void testGetSupportedPaymentModesCombinationWithLimitsIfEmptyResult()
	{
		final Map<String, BigDecimal> payModeLimits = new HashMap<>();
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = amwayPaymentModeService
				.getSupportedPaymentModesCombination(emptyCart, payModeLimits, true, CURRENT_PAYMENT_MODE);
		Assert.assertNotNull("Invalid supported payment mode combinations found.", paymentModeCombinations);
		Assert.assertTrue("Invalid supported payment mode combinations found.", paymentModeCombinations.size() == 0);

	}

	// TODO : to be updated for AR payments.
	@Test
	public void testGetSupportedPaymentModesCombinationWithCartIfSuccess()
	{
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = amwayPaymentModeService
				.getSupportedPaymentModesCombination(cartWithEntries);
		Assert.assertNotNull("No supported payment mode combinations found.", paymentModeCombinations);
		Assert.assertTrue("No supported payment mode combinations found.", paymentModeCombinations.size() > 0);
	}

	// TODO : to be updated for AR payments.
	@Test
	public void testGetSupportedPaymentModesCombinationWithCartIfEmptyResult()
	{
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = amwayPaymentModeService
				.getSupportedPaymentModesCombination(emptyCart);
		Assert.assertNotNull("Invalid supported payment mode combinations found.", paymentModeCombinations);
		Assert.assertTrue("Invalid supported payment mode combinations found.", paymentModeCombinations.size() == 0);
	}


}
