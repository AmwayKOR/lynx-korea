package com.amway.amwayfinance.facades;

import com.amway.amwayfinance.order.dto.PaymentEvent;


/**
 * Facade which is used to apply external changes to an order.
 */
public interface AmwayOrderExternalFinanceChangesFacade
{
	/**
	 * Create payment transaction for the specified payment event and notify order p.
	 *
	 * @param baseStoreId base store uid.
	 * @param orderCode order code.
	 * @param paymentEvent payment information.
	 */
	void applyExternalPayment(String baseStoreId, String orderCode, PaymentEvent paymentEvent);

	/**
	 * <p>Notify all order's processes about external payment event. The method is commonly
	 * called after {@link #applyExternalPayment(String, String, PaymentEvent)}.</p>
	 *
	 * @param baseStoreId base store uid.
	 * @param orderCode order for which external payment received.
	 * @see {@link #applyExternalPayment(String, String, PaymentEvent)}
	 */
	void notifyAboutExternalPayment(String baseStoreId, String orderCode);
}
