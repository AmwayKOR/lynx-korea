package com.amway.core.payment.strategies.impl;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.basecommerce.strategies.BaseStoreSelectorStrategy;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentModeModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.model.AmwayPaymentConfigModel;
import com.amway.core.order.data.AmwayPaymentModeData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.payment.service.AmwaySplitPaymentsService;
import com.amway.core.payment.strategies.AmwayAdjustOverpaymentStrategy;
import com.amway.core.payment.strategies.AmwayCreatePaymentInfoDataStrategy;
import com.amway.core.util.AmwayCartHelper;

/**
 * * The DefaultAmwayAdjustOverpaymentStrategy class. Default Implementation of
 * the {@link AmwayAdjustOverpaymentStrategy} interface.
 *
 * @author arjunduggal
 *
 */
@AmwayBean(docs="https://jira.amway.com:8444/display/HC/Payment+customization")
public class DefaultAmwayAdjustOverpaymentStrategy implements AmwayAdjustOverpaymentStrategy {

	private static final Logger LOG = Logger.getLogger(DefaultAmwayAdjustOverpaymentStrategy.class);

	private static final String NO_OVERPAY_RETURN_PAYMENT_MODE_FOUND_ERROR = "No Overpay Return payment mode found for order : %s";

	private AmwayPaymentModeService amwayPaymentModeService;

	private AmwaySplitPaymentsService amwaySplitPaymentsService;

	private BaseSiteService baseSiteService;
	
	private BaseStoreSelectorStrategy baseStoreSelectorStrategy;

	private Map<String, Map<String, AmwayCreatePaymentInfoDataStrategy>> createPaymentInfoDataStrategiesMap;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void adjustOverpayments(final OrderModel order) {
		if (PaymentStatus.OVERPAID.equals(order.getPaymentStatus())) {
			final double overpaidAmount = Math.abs(AmwayCartHelper.getBalanceAmount(order).doubleValue());
			adjustOverpaidPaymentTransaction(order, overpaidAmount);
		}
	}

	/**
	 * Method to adjust the Overpaid payment transaction
	 *
	 * @param order
	 * @param overpaidAmount
	 */
	private void adjustOverpaidPaymentTransaction(final AbstractOrderModel order, final double overpaidAmount) {
		final List<PaymentTransactionModel> paymentTransactionList = order.getPaymentTransactions();
		boolean foundAllowedOverpaymentMode = false;
		if (CollectionUtils.isNotEmpty(paymentTransactionList)) {
			for (final PaymentTransactionModel paymentTransaction : paymentTransactionList) {
				final PaymentModeModel paymentMode = paymentTransaction.getPaymentMode();
				if (Boolean.TRUE.equals(paymentMode.getAllowOverpay())) {
					foundAllowedOverpaymentMode = true;
					createOverpayReturnPayment(order, paymentMode, overpaidAmount);
					break;
				}
			}
			if (!foundAllowedOverpaymentMode) {
				LOG.error("No Overpayment allowed payment mode found in the transaction list for the Overpaid Order : "
						+ order.getCode());
			}

		}
	}

	/**
	 * Method to create the overpay return payment
	 *
	 * @param paymentMode
	 * @param overpaidAmount
	 */
	private void createOverpayReturnPayment(final AbstractOrderModel order, final PaymentModeModel paymentMode,
			final double overpaidAmount) {
		PaymentModeModel overpayRefundPaymentMode = getOverpayReturnPaymentMode(order);
		if (overpayRefundPaymentMode == null) {
			LOG.error(String.format(NO_OVERPAY_RETURN_PAYMENT_MODE_FOUND_ERROR, order.getCode()));
		} else {
			final String paymentModeCode = overpayRefundPaymentMode.getCode();
			final AmwayPaymentInfoData paymentInfoData = getAmwayPaymentInfoData(overpaidAmount, paymentModeCode);
			if (paymentInfoData != null) {
				amwaySplitPaymentsService.saveOverpayReturnPaymentTransaction(order.getUser(), order, paymentInfoData);
			}
		}

	}

	/**
	 * Method to get the amway payment info data
	 *
	 * @param amount
	 *            the amount
	 * @param paymentModeCode
	 *            the payment mode code
	 * @return paymentInfoData - the amway payment info data
	 */
	private AmwayPaymentInfoData getAmwayPaymentInfoData(final double amount, final String paymentModeCode) {
		AmwayPaymentInfoData paymentInfoData = null;
		final BaseSiteModel baseSite = baseSiteService.getCurrentBaseSite();
		if (baseSite != null) {
			final String baseSiteUid = baseSite.getUid();
			if (null == createPaymentInfoDataStrategiesMap.get(baseSiteUid)
					|| null == createPaymentInfoDataStrategiesMap.get(baseSiteUid).get(paymentModeCode)) {
				LOG.error(String.format(
						"No Create Payment Info Data Strategy found for payment mode : %s and Base site : %s.",
						paymentModeCode, baseSiteUid));
			} else {
				paymentInfoData = createPaymentInfoDataStrategiesMap.get(baseSiteUid).get(paymentModeCode)
						.createPaymentInfoData(amwayPaymentModeService.getPaymentModeForCode(paymentModeCode), amount);
			}
		}

		return paymentInfoData;

	}

	/**
	 * Method to get the the supported payment mode for Overpay Return from
	 * Transaction list
	 *
	 * @param order
	 * @return overpayReturnPaymentMode
	 */
	private PaymentModeModel getOverpayReturnPaymentMode(final AbstractOrderModel order) {
		PaymentModeModel overpayReturnPaymentMode = getSupportedOverpayReturnPaymentMode(order);
		if (overpayReturnPaymentMode == null) {
			overpayReturnPaymentMode = getFallbackReturnPaymentMode();
		}
		return overpayReturnPaymentMode;
	}

	private PaymentModeModel getFallbackReturnPaymentMode() {
		PaymentModeModel overpayReturnPaymentMode = null;
		BaseStoreModel baseStore = baseStoreSelectorStrategy.getCurrentBaseStore();
		if (baseStore != null) {
			overpayReturnPaymentMode = baseStore.getDefaultReturnPaymentMode();
		}
		return overpayReturnPaymentMode;
	}

	/**
	 * Method to get the supported overpay return payment mode.
	 *
	 * @return supported overpay return payment mode
	 */
	private PaymentModeModel getSupportedOverpayReturnPaymentMode(final AbstractOrderModel order) {
		final Map<String, List<AmwayPaymentModeData>> supportedPaymentModeCombinations = amwayPaymentModeService.getSupportedPaymentModesCombination(order);
		PaymentModeModel overpayReturnPaymentMode = null;
		final HashMap<String, Integer> selectedPaymentModesForCart = amwayPaymentModeService.getSelectedPaymentModesForCart(order);
		final int usedPaymentModesSize = MapUtils.isNotEmpty(selectedPaymentModesForCart) ? selectedPaymentModesForCart.size() : 0;
		
		if (MapUtils.isNotEmpty(supportedPaymentModeCombinations))
		{
			for (final String config : supportedPaymentModeCombinations.keySet())
			{
				if (supportedPaymentModeCombinations.get(config).size() == usedPaymentModesSize)
				{
					AmwayPaymentConfigModel paymentConfig = amwayPaymentModeService.getPaymentConfigForCode(config);
					if (paymentConfig != null && CollectionUtils.isNotEmpty(paymentConfig.getReturnPaymentModes()))
					{
						overpayReturnPaymentMode = paymentConfig.getReturnPaymentModes().iterator().next();
						break;
					}
				}
			}
		}
		return overpayReturnPaymentMode;
	}

	public AmwayPaymentModeService getAmwayPaymentModeService() {
		return amwayPaymentModeService;
	}

	@Required
	public void setAmwayPaymentModeService(final AmwayPaymentModeService amwayPaymentModeService) {
		this.amwayPaymentModeService = amwayPaymentModeService;
	}

	public AmwaySplitPaymentsService getAmwaySplitPaymentsService() {
		return amwaySplitPaymentsService;
	}

	@Required
	public void setAmwaySplitPaymentsService(final AmwaySplitPaymentsService amwaySplitPaymentsService) {
		this.amwaySplitPaymentsService = amwaySplitPaymentsService;
	}

	public BaseSiteService getBaseSiteService() {
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService) {
		this.baseSiteService = baseSiteService;
	}

	public BaseStoreSelectorStrategy getBaseStoreSelectorStrategy() {
		return baseStoreSelectorStrategy;
	}

	@Required
	public void setBaseStoreSelectorStrategy(final BaseStoreSelectorStrategy baseStoreSelectorStrategy) {
		this.baseStoreSelectorStrategy = baseStoreSelectorStrategy;
	}

	public Map<String, Map<String, AmwayCreatePaymentInfoDataStrategy>> getCreatePaymentInfoDataStrategiesMap() {
		return createPaymentInfoDataStrategiesMap;
	}

	@Required
	public void setCreatePaymentInfoDataStrategiesMap(
			final Map<String, Map<String, AmwayCreatePaymentInfoDataStrategy>> createPaymentInfoDataStrategiesMap) {
		this.createPaymentInfoDataStrategiesMap = createPaymentInfoDataStrategiesMap;
	}

}
