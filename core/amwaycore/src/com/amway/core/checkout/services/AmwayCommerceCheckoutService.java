package com.amway.core.checkout.services;

import de.hybris.platform.commerceservices.order.CommerceCheckoutService;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;

import java.math.BigDecimal;

import com.amway.core.model.AmwayMonetaryPaymentInfoModel;

/**
 * Interface for Amway Commerce Checkout Service
 */
public interface AmwayCommerceCheckoutService extends CommerceCheckoutService
{
	/**
	 * creates and sets the arCreditPaymentInfo to cart
	 *
	 * @param customerModel
	 * @param arAmount
	 * @param cartModel
	 * @return createARCreditPaymentInfo
	 */
	AmwayMonetaryPaymentInfoModel createARCreditPaymentInfo(CustomerModel customerModel, double arAmount, CartModel cartModel);

	/**
	 * creates and sets the arCreditPaymentInfo to OrderModel
	 *
	 * @param customerModel
	 * @param cartModel
	 * @return createARCreditPaymentInfo
	 */
	AmwayMonetaryPaymentInfoModel createARCreditPaymentInfo(CustomerModel customerModel, AbstractOrderModel cartModel);

	/**
	 * this method deducts the AR credit from account balance add transaction entry to cart
	 *
	 * @param cartModel
	 * @param amount
	 * @param balanceTypeCd
	 * @param info
	 * @return PaymentTransactionEntryModel
	 * @throws BusinessException
	 */
	public PaymentTransactionEntryModel authorizeAccountBalance(final CartModel cartModel, final BigDecimal amount,
			final String balanceTypeCd, final PaymentInfoModel info) throws BusinessException;



	/**
	 * this method do the authorization of account balance.
	 *
	 * @param cartModel
	 * @param amount
	 * @param balanceTypeCd
	 * @return paymentTransactionEntryModel
	 */
	PaymentTransactionEntryModel authAccountBalance(AbstractOrderModel cartModel, BigDecimal amount, final String balanceTypeCd)
			throws BusinessException;



	/**
	 * check for the restriction blocks on the account before place order
	 *
	 * @param cart
	 * @return boolean
	 */
	boolean isPlaceOrderAllowed(CartModel cart);


	/**
	 * @param amount
	 * @param cartModel
	 * @param paymentInfo
	 * @param installMent
	 * @param secondCCinstallMent
	 */
	void adjustAmountInInfo(String amount, CartModel cartModel, PaymentInfoModel paymentInfo, String installMent,
			final String secondCCinstallMent);

	/**
	 * this method do the authorization of account balance if entered credit is valid or not
	 *
	 * @param cartModel
	 * @param amount
	 * @param balanceTypeCd
	 * @param info
	 * @return PaymentTransactionEntryModel
	 * @throws BusinessException
	 */
	PaymentTransactionEntryModel authorizeAccountBalance(AbstractOrderModel cartModel, BigDecimal amount, String balanceTypeCd,
			PaymentInfoModel info) throws BusinessException;

	//TODO : method to unblock credit


}
