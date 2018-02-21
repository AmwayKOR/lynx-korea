/**
 *
 */
package com.amway.core.payment.service;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;


/**
 * Service around the {@link PaymentInfoModel}. for various payment related services
 */
public interface AmwayPaymentService extends PaymentService
{
	/**
	 * @param returnRequestModel
	 * @return PaymentTransactionEntryModel
	 * @throws AdapterException
	 */
	PaymentTransactionEntryModel refundFollowOn(ReturnRequestModel returnRequestModel) throws AdapterException;

	/**
	 * amount adjustment on cart total.
	 *
	 * @param cart
	 * @return
	 */
	boolean adjustPaymentAmount(final CartModel cart);
}
