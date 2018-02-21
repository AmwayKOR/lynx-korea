/**
 *
 */
package com.amway.core.facades.payment.validators.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.core.order.data.AmwayPaymentModeData;


/**
 * Integration tests for AmwayOverpayPaymentValidator
 */
@IntegrationTest
public class AmwayOverpayPaymentValidatorIntegrationTest extends AbstractPaymentValidatorIntegrationTest
{
	@Resource
	private AmwayOverpayPaymentValidator amwayOverpayPaymentValidator;

	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
	}

	@Test
	public void testValidateForSuccess()
	{
		amwayCashPaymentInfoData.setAmount(cart.getTotalPrice() + 1);
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CASH_PAYMENT_MODE);
		amwayOverpayPaymentValidator.validate(cart, amwayCashPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", !errors.hasErrors());
	}

	@Test
	public void testValidateIfOverpaymentDoneforNonOverpayMode()
	{
		amwayCCPaymentInfoData.setAmount(cart.getTotalPrice() + 1);
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CREDIT_CARD_PAYMENT_MODE);
		amwayOverpayPaymentValidator.validate(cart, amwayCCPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}

	@Test
	public void testValidateIfOverpaymentDoneforAboveThreshold()
	{
		amwayCashPaymentInfoData.setAmount(cart.getTotalPrice() + 1000);
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CASH_PAYMENT_MODE);
		amwayOverpayPaymentValidator.validate(cart, amwayCashPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}

}
