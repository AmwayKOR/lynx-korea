package com.amway.core.payment.strategies;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import com.amway.core.cart.data.AmwayPaymentInfoData;


/**
 * Strategy to create Payment Info's for POS orders.
 *
 */
public interface AmwayCreatePaymentInfoStrategy
{

	/**
	 * Method to create a payment info model for given payment transaction and payment info data for a pos payment
	 * transaction
	 *
	 * @param transaction
	 * @param userModel
	 * @param orderModel
	 * @param paymentInfoData
	 */
	void createPaymentInfo(final PaymentTransactionModel transaction, final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData);


}
