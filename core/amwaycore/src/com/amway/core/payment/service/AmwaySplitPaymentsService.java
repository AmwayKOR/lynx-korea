/**
 *
 */
package com.amway.core.payment.service;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import com.amway.core.cart.data.AmwayPaymentInfoData;

/**
 * Service to perform the split payment operations.
 *
 * @author mohit2496
 *
 */
public interface AmwaySplitPaymentsService {

	/**
	 *
	 * This will save the payment transaction and transaction entries for the
	 * infoData provided. No Validation will be performed for the order total or
	 * the amount provided against each transactions.
	 *
	 * <b>Make sure to use it only against trusted clients.<b>
	 *
	 * @param userModel
	 *            the user
	 * @param orderModel
	 *            the abstract order for the payment
	 * @param paymentInfoData
	 *            the payment info data to save
	 * @return the payment transaction model
	 */
	PaymentTransactionModel savePaymentTransaction(UserModel userModel, AbstractOrderModel orderModel,
			AmwayPaymentInfoData paymentInfoData);

	/**
	 *
	 * This will save the overpay return payment transaction and transaction
	 * entries for the infoData provided. No Validation will be performed for
	 * the order total or the amount provided against each transactions.
	 *
	 * <b>Make sure to use it only against trusted clients.<b>
	 *
	 * @param userModel
	 *            the user
	 * @param orderModel
	 *            the abstract order for the payment
	 * @param paymentInfoData
	 *            the payment info data to save
	 * @return the payment transaction model
	 */
	PaymentTransactionModel saveOverpayReturnPaymentTransaction(final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData);

}
