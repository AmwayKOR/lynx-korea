/**
 *
 */
package com.amway.core.facades.order.validators.impl;

import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


/**
 * Integration tests for AmwayOrderNonOverpayMethodsPaymentValidator
 */
@IntegrationTest
public class AmwayOrderNonOverpayMethodsPaymentValidatorIntegrationTest extends AbstractOrderPaymentsIntegrationTest
{

	@Resource
	AmwayOrderNonOverpayMethodsPaymentValidator amwayOrderNonOverpayMethodsPaymentValidator;

	@Override
	@Before
	public void prepare() throws Exception
	{
		super.prepare();
	}

	@Test
	public void testValidateForSuccess()
	{
		amwayCCPaymentInfoData.setAmount(cart.getTotalPrice());
		transactionList.add(amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCCPaymentInfoData));
		paymentTransactionsMap.put(paymentModeModel, transactionList);
		amwayOrderNonOverpayMethodsPaymentValidator.validate(paymentTransactionsMap, cart, errors);
		Assert.assertTrue("Validation fails.", !errors.hasErrors());
	}

	@Test
	public void testValidateIfOverPaymentByNonOverPayMethod()
	{
		amwayCashPaymentInfoData.setAmount(1D);
		transactionList.add(amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCashPaymentInfoData));
		paymentTransactionsMap.put(paymentModeModel, transactionList);

		final PaymentModeModel ccPaymentModeModel = Mockito.mock(PaymentModeModel.class);
		when(ccPaymentModeModel.getCode()).thenReturn("creditCard");
		when(ccPaymentModeModel.getAllowOverpay()).thenReturn(Boolean.FALSE);
		when(ccPaymentModeModel.getName()).thenReturn("creditCard");
		amwayCCPaymentInfoData.setAmount(cart.getTotalPrice() - 1);
		transactionList.add(amwaySplitPaymentsService.savePaymentTransaction(cart.getUser(), cart, amwayCCPaymentInfoData));
		paymentTransactionsMap.put(ccPaymentModeModel, transactionList);

		//Edit cart after payment - remove cart entries so that payment becomes over payment
		final CommerceCartParameter parameter = new CommerceCartParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cart);
		commerceCartService.removeAllEntries(parameter);

		amwayOrderNonOverpayMethodsPaymentValidator.validate(paymentTransactionsMap, cart, errors);
		Assert.assertTrue("Validation fails.", errors.hasErrors());
	}


}
