/**
 *
 */
package com.amway.apac.core.order.service.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import java.util.Objects;

import com.amway.apac.core.order.service.AmwayApacOrderService;
import com.amway.core.order.services.impl.DefaultAmwayOrderService;


/**
 * Default implementation to @AmwayApacOrderService
 */
public class DefaultAmwayApacOrderService extends DefaultAmwayOrderService implements AmwayApacOrderService
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOrderPaymentCaptured(final OrderModel order)
	{
		return order.getPaymentTransactions().stream().anyMatch(
				transaction -> transaction.getEntries().stream().anyMatch(entry -> isPaymentTransactionEntryCaptured(entry)));
	}

	/**
	 * Checks if the transaction entry payment status is captured
	 * 
	 * @param entry
	 * @return boolean
	 */
	private boolean isPaymentTransactionEntryCaptured(final PaymentTransactionEntryModel entry)
	{
		return Objects.isNull(entry.getVersionID()) && PaymentTransactionType.CAPTURE.equals(entry.getType());
	}
}
