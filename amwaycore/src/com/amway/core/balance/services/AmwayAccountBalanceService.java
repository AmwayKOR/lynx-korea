/**
 *
 */
package com.amway.core.balance.services;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;

import java.math.BigDecimal;

import com.amway.core.data.CommonResponseFieldsData;
import com.amway.core.dms.data.GetBalanceResponseData;


/**
 * Interface for Amway Account Balance Service
 */
public interface AmwayAccountBalanceService
{
	/**
	 * To Create the Credit Account Balance
	 *
	 * @param paymentTransactionEntryModel
	 * @param balanceTypeCd
	 * @param referenceNumber
	 * @return CommonResponseFieldsData
	 */
	public CommonResponseFieldsData creditAccountBalance(final PaymentTransactionEntryModel paymentTransactionEntryModel,
														 final String balanceTypeCd, final String referenceNumber);


	/**
	 * To debit the Account Balance
	 *
	 * @param orderModel
	 * @param amount
	 * @param balanceTypeCd
	 * @return CommonResponseFieldsData
	 */
	public CommonResponseFieldsData debitAccountBalance(final AbstractOrderModel orderModel, final BigDecimal amount,
														final String balanceTypeCd);


	/**
	 * To get the Account Balance
	 *
	 * @param orderModel
	 * @return GetBalanceResponseData
	 */
	public GetBalanceResponseData getAccountBalance(final AbstractOrderModel orderModel);
}
