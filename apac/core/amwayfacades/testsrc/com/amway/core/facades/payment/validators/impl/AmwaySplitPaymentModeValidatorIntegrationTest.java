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
 * Integration tests for AmwaySplitPaymentModeValidator
 */
@IntegrationTest
public class AmwaySplitPaymentModeValidatorIntegrationTest extends AbstractPaymentValidatorIntegrationTest
{
	@Resource
	private AmwaySplitPaymentModeValidator amwaySplitPaymentModeValidator;

	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
	}

	@Test
	public void testValidateIfFullPaymentForSuccess()
	{
		amwayCashPaymentInfoData.setAmount(cart.getTotalPrice());
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CASH_PAYMENT_MODE);
		amwaySplitPaymentModeValidator.validate(cart, amwayCashPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", !errors.hasErrors());
	}

	@Test
	public void testValidateIfPartialPaymentForSuccess()
	{
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CASH_PAYMENT_MODE);
		amwaySplitPaymentModeValidator.validate(cart, amwayCashPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", !errors.hasErrors());
	}


	@Test
	public void testValidateIfFullPaymentAndAllModesNotUsed()
	{
		final Double firstPaymentAmount = 20D;
		final Double secondPaymentAmount = cart.getTotalPrice() - firstPaymentAmount;
		amwayCCPaymentInfoData.setAmount(firstPaymentAmount);
		//Do a CC payemnt
		amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCCPaymentInfoData);

		//Get payment mode combinations if we are doing a new CC payment
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CREDIT_CARD_PAYMENT_MODE);

		//Validate the new CC payment
		amwayCCPaymentInfoData.setAmount(secondPaymentAmount);
		amwaySplitPaymentModeValidator.validate(cart, amwayCCPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}

	@Test
	public void testValidateIfPartialPaymentAndRepeatableCountExceeds()
	{
		amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCashPaymentInfoData);
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CASH_PAYMENT_MODE);
		amwaySplitPaymentModeValidator.validate(cart, amwayCashPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}

	@Test
	public void testValidateIfPartialAndLastPayment()
	{
		final Double firstPaymentAmount = 1D;
		final Double secondPaymentAmount = cart.getTotalPrice() - (firstPaymentAmount * 5) - 10;
		amwayCCPaymentInfoData.setAmount(firstPaymentAmount);
		//Do 5 CC payemnt
		amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCCPaymentInfoData);
		amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCCPaymentInfoData);
		amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCCPaymentInfoData);
		amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCCPaymentInfoData);
		amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCCPaymentInfoData);

		//Get payment mode combinations if we are doing a new cash payment
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CASH_PAYMENT_MODE);

		//Validate the new Cash payment
		amwayCashPaymentInfoData.setAmount(secondPaymentAmount);
		amwaySplitPaymentModeValidator.validate(cart, amwayCashPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}

}
