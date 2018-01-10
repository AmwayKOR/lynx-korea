package com.amway.core.payment.strategies.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.model.AmwayCashPaymentInfoModel;

/**
 * Implementation of {@link AbstractAmwayCreatePaymentInfoStrategy}. Creates the
 * Cash payment info.
 *
 * @author mohit2496
 */
@AmwayBean(docs="https://jira.amway.com:8444/display/HC/Payment+customization")
public class AmwayCreateCashPaymentInfoStrategy extends AbstractAmwayCreatePaymentInfoStrategy {
	private static final Logger LOG = Logger.getLogger(AmwayCreateCashPaymentInfoStrategy.class);

	@Override
	public PaymentInfoModel createPaymentInfoEntry(final PaymentTransactionModel transaction, final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData) {
		final AmwayCashPaymentInfoModel cashPaymentInfoModel = getModelService()
				.create(AmwayCashPaymentInfoModel.class);
		cashPaymentInfoModel.setUser(userModel);
		cashPaymentInfoModel.setCode(orderModel.getCode() + UUID.randomUUID().toString());
		cashPaymentInfoModel.setBillingAddress(orderModel.getPaymentAddress());
		getModelService().save(cashPaymentInfoModel);
		return cashPaymentInfoModel;

	}

}
