/**
 *
 */
package com.amway.core.facades.order.validators;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;


/**
 * Validator to check the payments done for the order to be placed.
 *
 * @author arjunduggal
 *
 */
public interface AmwayPlaceOrderPaymentsValidator
{

	/**
	 * Method to validate the cart payments.
	 *
	 * @param paymentTransactionsMap
	 *           the cart payment transaction map
	 * @param cartModel
	 *           the cart
	 * @param errors
	 *           the errors
	 * @return isValid - if the payments are valid or not
	 */
	boolean validate(final Map<PaymentModeModel, List<PaymentTransactionModel>> paymentTransactionsMap, final CartModel cartModel,
			final Errors errors);
}
