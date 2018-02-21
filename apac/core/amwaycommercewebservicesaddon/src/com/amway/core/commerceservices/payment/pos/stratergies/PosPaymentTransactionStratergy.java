package com.amway.core.commerceservices.payment.pos.stratergies;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import com.amway.core.cart.data.PosPaymentInfoData;


/**
 * This will be used by occ for POS orders.
 * 
 */
public interface PosPaymentTransactionStratergy
{
	/**
	 * 
	 * This will save the transaction entries for the infoData provided. No Validation will be performed for the order
	 * total or the amount provided against each transactions.
	 * 
	 * <b>Make sure to use it only against trusted clients.<b>
	 * 
	 * @param customerModel
	 * @param orderModel
	 * @param paymentInfoData
	 * @return PaymentTransactionModel
	 */
	PaymentTransactionModel savePaymentTransaction(CustomerModel customerModel, AbstractOrderModel orderModel,
			PosPaymentInfoData paymentInfoData);

}
