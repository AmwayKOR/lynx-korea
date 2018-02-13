/**
 *
 */
package com.amway.core.facades.order.validators.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Integration tests for AmwayOrderCompletePaymentValidator
 */
@IntegrationTest
public class AmwayOrderCompletePaymentValidatorIntegrationTest extends AbstractOrderPaymentsIntegrationTest
{

	@Resource
	AmwayOrderCompletePaymentValidator amwayOrderCompletePaymentValidator;

	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
	}

	@Test
	public void testValidateForSuccess()
	{
		amwayCashPaymentInfoData.setAmount(cart.getTotalPrice());
		transactionList.add(amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCashPaymentInfoData));
		paymentTransactionsMap.put(paymentModeModel, transactionList);
		amwayOrderCompletePaymentValidator.validate(paymentTransactionsMap, cart, errors);
		Assert.assertTrue("Validation fails.", !errors.hasErrors());
	}

	@Test
	public void testValidateIfIncompletePayment()
	{
		amwayCashPaymentInfoData.setAmount(cart.getTotalPrice() - 1);
		transactionList.add(amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCashPaymentInfoData));
		paymentTransactionsMap.put(paymentModeModel, transactionList);
		amwayOrderCompletePaymentValidator.validate(paymentTransactionsMap, cart, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}

}
