package com.amway.amwayfinance.service;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.payment.TransactionInfoService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;


/**
 * Extension of a standard interface to add external payment modification functionality.
 */
public interface AmwayTransactionInfoService extends TransactionInfoService
{
	/**
	 * Ignored payment status.
	 */
	String IGNORED_PAYMENT_TRANSACTION_STATUS = "IGNORED_PAYMENT_TRANSACTION_STATUS";

	/**
	 * <p>Adjust order payment transactions with the received payment.</p>
	 *
	 * @param order order model to change.
	 * @param transactionId payment transaction id as specified by external system.
	 * @param paymentType transaction payment type, which is mapped to transaction#paymentProvider
	 * @param entry transaction entry.
	 * @throws IllegalArgumentException in case of wrong order status.
	 */
	void createExternalCaptureTransactionEntry(AbstractOrderModel order,
			String transactionId, String paymentType, PaymentTransactionEntryModel entry);

	/**
	 * Method shows that a particular external transaction entry has been ignored and should not be considered
	 * on calculations.
	 *
	 * @param entry payment entry.
	 * @return true if the transaction entry must be ignored.
	 */
	boolean isTransactionEntryIgnored(PaymentTransactionEntryModel entry);
}
