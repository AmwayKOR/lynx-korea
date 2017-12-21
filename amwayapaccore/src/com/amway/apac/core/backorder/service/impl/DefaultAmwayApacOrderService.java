/**
 *
 */
package com.amway.apac.core.backorder.service.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.Objects;

import com.amway.apac.core.backorder.service.AmwayApacOrderService;
import com.amway.core.order.services.impl.DefaultAmwayOrderService;


/**
 * Default implementation to @AmwayApacOrderService
 */
public class DefaultAmwayApacOrderService extends DefaultAmwayOrderService implements AmwayApacOrderService
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.amway.apac.core.backorder.service.AmwayApacOrderService#isOrderPaymentCaptured(de.hybris.platform.core.model
	 * .order.OrderModel)
	 */
	@Override
	public boolean isOrderPaymentCaptured(final OrderModel order)
	{
		boolean isOrderPaymentCaptured = false;
		for (final PaymentTransactionModel transaction : order.getPaymentTransactions())
		{
			for (final PaymentTransactionEntryModel entry : transaction.getEntries())
			{
				if (Objects.isNull(entry.getVersionID()) && PaymentTransactionType.CAPTURE.equals(entry.getType()))
				{
					isOrderPaymentCaptured = true;
					break;
				}
			}
		}
		return isOrderPaymentCaptured;
	}
}
