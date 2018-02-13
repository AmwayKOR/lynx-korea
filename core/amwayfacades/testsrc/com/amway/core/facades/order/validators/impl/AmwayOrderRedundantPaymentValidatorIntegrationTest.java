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
 * Integration tests for AmwayOrderRedundantPaymentValidatorIntegrationTest
 */
@IntegrationTest
public class AmwayOrderRedundantPaymentValidatorIntegrationTest extends AbstractOrderPaymentsIntegrationTest
{
	@Resource
	private AmwayOrderRedundantPaymentValidator amwayOrderRedundantPaymentValidator;

	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
	}

	@Test
	public void testValidateForSuccess()
	{
		amwayCashPaymentInfoData.setAmount(cart.getTotalPrice() + 10);
		transactionList.add(amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCashPaymentInfoData));
		paymentTransactionsMap.put(paymentModeModel, transactionList);
		amwayOrderRedundantPaymentValidator.validate(paymentTransactionsMap, cart, errors);
		Assert.assertTrue("Validation fails.", !errors.hasErrors());
	}

	@Test
	public void testValidateIfRedundantPayment()
	{
		amwayCashPaymentInfoData.setAmount(1D);
		transactionList.add(amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCashPaymentInfoData));

		amwayCashPaymentInfoData.setAmount(cart.getTotalPrice() + 10);
		transactionList.add(amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCashPaymentInfoData));
		paymentTransactionsMap.put(paymentModeModel, transactionList);
		amwayOrderRedundantPaymentValidator.validate(paymentTransactionsMap, cart, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}
}
