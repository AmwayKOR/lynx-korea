/**
 *
 */

package com.amway.core.order.transaction.dao;

import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;


/**
 * Data access to {@link PaymentTransactionModel}
 */
public interface AmwayPaymentTransactionDao extends GenericDao<PaymentTransactionModel>
{
	/**
	 * To find the payment transaction by code.
	 *
	 * @param code
	 * @return PaymentTransactionModel
	 */
	PaymentTransactionModel findPaymentTransactionByCode(String code);
}
