/**
 *
 */
package com.amway.core.order.transaction.dao.impl;

import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.order.transaction.dao.AmwayPaymentTransactionDao;


/**
 * Default implementation
 */
public class DefaultAmwayPaymentTransactionDaoImpl extends DefaultGenericDao<PaymentTransactionModel>
		implements AmwayPaymentTransactionDao
{
	Logger LOG = Logger.getLogger(DefaultAmwayPaymentTransactionDaoImpl.class);

	/**
	 * DefaultGenericDao is only usable when typecode is set.
	 */
	public DefaultAmwayPaymentTransactionDaoImpl()
	{
		super(PaymentTransactionModel._TYPECODE);
	}

	/**
	 * To find the payment transaction by code.
	 *
	 * @param code
	 */
	@Override
	public PaymentTransactionModel findPaymentTransactionByCode(final String code)
	{
		PaymentTransactionModel trans = null;
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(PaymentTransactionModel.CODE, code);
		final List<PaymentTransactionModel> transactions = find(params);
		if (CollectionUtils.isEmpty(transactions))
		{
			LOG.warn("There is no PaymentTransaction with code: " + code);
		}
		else if (transactions.size() > 1)
		{
			LOG.error("There can only be one PaymentTransaction for one order, and only one PaymentTransaction with code: " + code);
		}
		else
		{
			trans = transactions.get(0);
		}
		return trans;
	}
}
