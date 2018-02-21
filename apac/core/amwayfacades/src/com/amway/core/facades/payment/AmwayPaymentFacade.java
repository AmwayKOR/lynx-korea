/**
 *
 */
package com.amway.core.facades.payment;

import de.hybris.platform.acceleratorfacades.payment.PaymentFacade;
import de.hybris.platform.core.model.order.AbstractOrderModel;

import org.springframework.validation.Errors;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.facades.cart.data.PaymentDetailsData;
import com.amway.facades.data.CapturePaymentInfoData;


/**
 * Facade to perform the cart payment operations.
 *
 * @author mohit2496
 *
 */
public interface AmwayPaymentFacade extends PaymentFacade
{


	/**
	 * Method to capture a payment made at POS system
	 *
	 * @param capturePaymentInfoData
	 * @param errors
	 */
	void capturePayment(CapturePaymentInfoData capturePaymentInfoData, Errors errors);


	/**
	 * Method to validate the payment
	 *
	 * @param orderModel
	 * @param posPaymentInfoData
	 * @param errors
	 */
	void validatePayment(AbstractOrderModel orderModel, AmwayPaymentInfoData posPaymentInfoData, Errors errors);


	/**
	 * Method to get the payment details
	 *
	 * @param orderModel
	 *           the abstract order model
	 * @return PaymentDetailsData the payment details data
	 */
	PaymentDetailsData getCartPaymentInfoDetails(final AbstractOrderModel orderModel);

}