/**
 *
 */
package com.amway.core.facades.order.validators.impl;

import static org.mockito.Mockito.when;

import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;

import com.amway.core.facades.payment.validators.impl.AbstractPaymentValidatorIntegrationTest;


/**
 *
 */
public abstract class AbstractOrderPaymentsIntegrationTest extends AbstractPaymentValidatorIntegrationTest
{
	protected Map<PaymentModeModel, List<PaymentTransactionModel>> paymentTransactionsMap;
	protected PaymentModeModel paymentModeModel;
	protected List<PaymentTransactionModel> transactionList;

	@Override
	public void prepare() throws Exception
	{
		super.prepare();
		paymentTransactionsMap = new HashMap<PaymentModeModel, List<PaymentTransactionModel>>();
		paymentModeModel = Mockito.mock(PaymentModeModel.class);
		when(paymentModeModel.getCode()).thenReturn("cash");
		when(paymentModeModel.getAllowOverpay()).thenReturn(Boolean.TRUE);
		when(paymentModeModel.getName()).thenReturn("cash");
		transactionList = new ArrayList<PaymentTransactionModel>();
	}
}
