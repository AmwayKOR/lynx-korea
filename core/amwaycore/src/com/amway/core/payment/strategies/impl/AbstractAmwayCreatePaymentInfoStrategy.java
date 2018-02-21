/**
 *
 */
package com.amway.core.payment.strategies.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.LinkedHashSet;
import java.util.Set;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.payment.strategies.AmwayCreatePaymentInfoStrategy;

/**
 * Abstract Implementation of {@link AmwayCreatePaymentInfoStrategy}
 *
 * @author mohit2496
 */
public abstract class AbstractAmwayCreatePaymentInfoStrategy implements AmwayCreatePaymentInfoStrategy {

	private ModelService modelService;

	private ConfigurationService configurationService;

	private AmwayPaymentModeService amwayPaymentModeService;

	/**
	 * Method to create a payment info model for given payment transaction and
	 * payment info data for a pos payment transaction
	 *
	 * @param transaction
	 * @param userModel
	 * @param orderModel
	 * @param paymentInfoData
	 */
	@Override
	public void createPaymentInfo(final PaymentTransactionModel transaction, final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData) {

		final PaymentInfoModel paymentInfoModel = createPaymentInfoEntry(transaction, userModel, orderModel,
				paymentInfoData);
		orderModel.setPaymentInfo(paymentInfoModel);

		final Set<PaymentInfoModel> paymentInfoList = new LinkedHashSet<PaymentInfoModel>(orderModel.getPaymentInfos());
		paymentInfoList.add(paymentInfoModel);
		orderModel.setPaymentInfos(paymentInfoList);
		orderModel.setPaymentInfo(paymentInfoModel);
		getModelService().save(orderModel);

		transaction.setInfo(paymentInfoModel);
		transaction.setPaymentMode(amwayPaymentModeService.getPaymentModeForCode(paymentInfoData.getPaymentMode().getCode()));
		getModelService().save(transaction);

	}

	protected abstract PaymentInfoModel createPaymentInfoEntry(final PaymentTransactionModel transaction,
			final UserModel userModel, final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData);

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(final ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public void setAmwayPaymentModeService(final AmwayPaymentModeService amwayPaymentModeService) {
		this.amwayPaymentModeService = amwayPaymentModeService;
	}

	public AmwayPaymentModeService getAmwayPaymentModeService() {
		return amwayPaymentModeService;
	}
}
