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
 * Integration tests for AmwayPaymentAmountValidator
 */
@IntegrationTest
public class AmwayPaymentAmountValidatorIntegrationTest extends AbstractPaymentValidatorIntegrationTest
{
	@Resource
	private AmwayPaymentAmountValidator amwayPaymentAmountValidator;

	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
	}


	@Test
	public void testValidateForSuccess()
	{
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CASH_PAYMENT_MODE);
		amwayPaymentAmountValidator.validate(cart, amwayCashPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", !errors.hasErrors());
	}


	@Test
	public void testValidateForError()
	{
		amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCashPaymentInfoData);
		final Map<String, List<AmwayPaymentModeData>> paymentModeCombinations = getSupportedPaymentModesCombinations(cart,
				amwayProfileData, false, CASH_PAYMENT_MODE);
		amwayPaymentAmountValidator.validate(cart, amwayCashPaymentInfoData, paymentModeCombinations, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}


}
