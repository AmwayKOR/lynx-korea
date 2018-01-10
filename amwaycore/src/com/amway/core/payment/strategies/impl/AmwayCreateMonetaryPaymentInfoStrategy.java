/**
 *
 */
package com.amway.core.payment.strategies.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.model.AmwayMonetaryPaymentInfoModel;

/**
 * Implementation of {@link AbstractAmwayCreatePaymentInfoStrategy}. Creates the
 * monetary payment info data.
 *
 * @author mohit2496
 */
@AmwayBean(docs="https://jira.amway.com:8444/display/HC/Payment+customization")
public class AmwayCreateMonetaryPaymentInfoStrategy extends AbstractAmwayCreatePaymentInfoStrategy {
	private static final Logger LOG = Logger.getLogger(AmwayCreateCashPaymentInfoStrategy.class);

	@Override
	public PaymentInfoModel createPaymentInfoEntry(final PaymentTransactionModel transaction, final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData) {
		final AmwayMonetaryPaymentInfoModel amwayMonetaryPaymentInfo = getModelService()
				.create(AmwayMonetaryPaymentInfoModel.class);
		amwayMonetaryPaymentInfo.setUser(userModel);
		amwayMonetaryPaymentInfo.setCode(orderModel.getCode() + UUID.randomUUID().toString());
		amwayMonetaryPaymentInfo.setBillingAddress(orderModel.getPaymentAddress());
		// amwayMonetaryPaymentInfo.set
		getModelService().save(amwayMonetaryPaymentInfo);
		return amwayMonetaryPaymentInfo;

	}

}
