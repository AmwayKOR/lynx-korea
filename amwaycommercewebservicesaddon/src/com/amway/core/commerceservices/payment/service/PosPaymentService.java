package com.amway.core.commerceservices.payment.service;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;

import com.amway.core.cart.data.PlaceOrderCartInfoData;


/**
 * 
 * Interface for pos payment service
 * 
 */
public interface PosPaymentService
{
	/**
	 * capture payment from credit card,  cash.
	 * 
	 * @param orderModel
	 * @param cartInfoData
	 */
	void capturePayment(final AbstractOrderModel orderModel, PlaceOrderCartInfoData cartInfoData);

	/**
	 * validate if the payment infos total equals order total.
	 * 
	 * @param cartModel
	 * @param cartInfoData
	 * @return if validate amount is 0 then true otherwise false.
	 */
	boolean validateOrderAmount(final CartModel cartModel, final PlaceOrderCartInfoData cartInfoData);
}
