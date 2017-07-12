package com.amway.core.payment.service;

import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.jalo.order.payment.PaymentInfo;
import de.hybris.platform.order.PaymentInfoService;

import com.amway.core.model.AmwayAccountModel;


/**
 * Extends {@link PaymentInfoService} with additional operations around the {@link PaymentInfoModel}.
 */
public interface AmwayPaymentInfoService extends PaymentInfoService
{
	/**
	 * Generates payment verification reference number in {@link PaymentInfo}.
	 *
	 * @param paymentInfo
	 *           the target payment info
	 * @param account
	 *           customer account
	 */
	void generateVerificationNumberForPaymentInfo(PaymentInfoModel paymentInfo, AmwayAccountModel account);
}
