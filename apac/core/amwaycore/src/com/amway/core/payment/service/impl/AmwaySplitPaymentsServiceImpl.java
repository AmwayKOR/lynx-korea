/**
 *
 */
package com.amway.core.payment.service.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static org.apache.commons.lang.StringUtils.isNotEmpty;

import com.amway.core.annotations.AmwayBean;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.dto.TransactionStatusDetails;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.amway.core.cart.data.AmwayPaymentInfoData;
import com.amway.core.payment.service.AmwayPaymentModeService;
import com.amway.core.payment.service.AmwaySplitPaymentsService;
import com.amway.core.payment.strategies.AmwayCreatePaymentInfoStrategy;
import com.amway.core.util.AmwayCartHelper;

/**
 *
 * Implementation for {@link AmwaySplitPaymentsService}.
 *
 * @author mohit2496
 *
 */
@AmwayBean(docs="https://jira.amway.com:8444/display/HC/Payment+customization")
public class AmwaySplitPaymentsServiceImpl implements AmwaySplitPaymentsService {

	private static final Logger LOG = Logger.getLogger(AmwaySplitPaymentsServiceImpl.class);

	private ModelService modelService;

	private ConfigurationService configurationService;

	private Map<String, Map<String, AmwayCreatePaymentInfoStrategy>> createPaymentInfoStrategiesMap;

	private BaseSiteService baseSiteService;

	private AmwayPaymentModeService amwayPaymentModeService;

	@Override
	public PaymentTransactionModel savePaymentTransaction(final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData) {
		validateParameterNotNull(userModel, "userModel cannot be null");
		validateParameterNotNull(orderModel, "orderModel cannot be null");
		validateParameterNotNull(paymentInfoData, "paymentInfoData cannot be null");
		checkArgument(isNotEmpty(paymentInfoData.getTransactionid()), "Transaction id for payment info cannot not be empty");
		validateParameterNotNull(paymentInfoData.getAmount(), "Amount for payment info cannot be null");
		checkArgument(paymentInfoData.getAmount().doubleValue() > 0, "Amount for payment info should be greater than zero");
		return savePaymentTransaction(userModel, orderModel, paymentInfoData, PaymentTransactionType.EXTERNAL_CAPTURE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentTransactionModel saveOverpayReturnPaymentTransaction(final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData) {
		validateParameterNotNull(userModel, "userModel cannot be null");
		validateParameterNotNull(orderModel, "orderModel cannot be null");
		validateParameterNotNull(paymentInfoData, "paymentInfoData cannot be null");
		validateParameterNotNull(paymentInfoData.getAmount(), "Amount for payment info cannot be null");
		checkArgument(paymentInfoData.getAmount().doubleValue() > 0, "Amount for payment info should be greater than zero");
		return savePaymentTransaction(userModel, orderModel, paymentInfoData, PaymentTransactionType.OVERPAY_RETURN);
	}

	/**
	 * Method to save the payment transaction and transaction entries for the
	 * infoData and transaction type provided. No Validation will be performed
	 * for the order total or the amount provided against each transactions.
	 *
	 * @param userModel
	 *            the user
	 * @param orderModel
	 *            the abstract order for the payment
	 * @param paymentInfoData
	 *            the payment info data to save
	 * @param paymentTransactionType
	 *            the payment transaction type
	 * @return the payment transaction
	 */
	private PaymentTransactionModel savePaymentTransaction(final UserModel userModel,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData,
			final PaymentTransactionType paymentTransactionType) {
		final String code = (userModel.getUid() + "_" + UUID.randomUUID());
		final PaymentStatus newPaymentStatus = getNewPaymentStatus(orderModel, paymentInfoData);
		
		final PaymentTransactionModel transaction = createPaymentTransaction(code, orderModel, paymentInfoData,
				paymentTransactionType);
		createPaymentTransactionEntry(code, transaction, orderModel, paymentInfoData, paymentTransactionType);

		orderModel.setPaymentStatus(newPaymentStatus);
		getModelService().save(orderModel);

		// create payment info using the appropriate strategy
		final String paymentModeTypeKey = amwayPaymentModeService
				.getPaymentModeForCode(paymentInfoData.getPaymentMode().getCode()).getPaymentInfoType().getCode();
		if (null != createPaymentInfoStrategiesMap.get(baseSiteService.getCurrentBaseSite().getUid())
				&& null != createPaymentInfoStrategiesMap.get(baseSiteService.getCurrentBaseSite().getUid())
						.get(paymentModeTypeKey)) {
			createPaymentInfoStrategiesMap.get(baseSiteService.getCurrentBaseSite().getUid()).get(paymentModeTypeKey)
					.createPaymentInfo(transaction, userModel, orderModel, paymentInfoData);
		}

		// create payment details
		// TODO: check to remove explicit cast to cart model
		if (orderModel instanceof CartModel) {
			savePaymentDetailsMap((CartModel) orderModel, paymentInfoData, transaction);
		}
		getModelService().save(orderModel);

		return transaction;
	}

	private void savePaymentDetailsMap(final CartModel cartModel, final AmwayPaymentInfoData paymentInfoData,
			final PaymentTransactionModel transaction) {
		final Map<String, Object> paymentDetails = AmwayCartHelper.getPaymentDetails(cartModel);
		final Map<String, String> paymentInfoDetails = new HashMap<String, String>();
		paymentInfoDetails.put("amount", String.valueOf(paymentInfoData.getAmount()));
		paymentDetails.put(transaction.getInfo().getPk().toString(), paymentInfoDetails);

		cartModel.setPaymentDetails(paymentDetails);
	}

	/**
	 * Method to get the payment status based on the total paid amount and order
	 * total.
	 *
	 * @return the payment status
	 */
	protected PaymentStatus getNewPaymentStatus(final AbstractOrderModel orderModel,
			final AmwayPaymentInfoData paymentInfoData) {
		BigDecimal totalPaidAmount = BigDecimal.ZERO;
		final BigDecimal prevPaidAmount = BigDecimal
				.valueOf(AmwayCartHelper.getTotalPaidAmount(orderModel).doubleValue());
		if (PaymentStatus.OVERPAID.equals(orderModel.getPaymentStatus())) {
			totalPaidAmount = prevPaidAmount.subtract(BigDecimal.valueOf(paymentInfoData.getAmount().doubleValue()));
		} else {
			totalPaidAmount = prevPaidAmount.add(BigDecimal.valueOf(paymentInfoData.getAmount().doubleValue()));
		}
		return getPaymentStatusFromAmountDifference(
				totalPaidAmount.compareTo(AmwayCartHelper.getCartTotalPayablePrice(orderModel)));
	}

	/**
	 * Method to get the payment status from the amount difference.
	 *
	 * @param amountDifference
	 *            the amount difference
	 * @return the payment status
	 */
	private PaymentStatus getPaymentStatusFromAmountDifference(final int amountDifference) {
		PaymentStatus paymentStatus = PaymentStatus.NOTPAID;
		switch (amountDifference) {
		case 0: {
			paymentStatus = PaymentStatus.PAID;
			break;
		}
		case 1: {
			paymentStatus = PaymentStatus.OVERPAID;
			break;
		}
		case -1: {
			paymentStatus = PaymentStatus.PARTPAID;
		}
		}
		return paymentStatus;
	}

	/**
	 * Method to create a payment transaction model for given transaction code,
	 * order and payment info data
	 *
	 * @param code
	 * @param orderModel
	 * @param paymentInfoData
	 * @param paymentTransactionType
	 * @return PaymentTransactionModel
	 */
	protected PaymentTransactionModel createPaymentTransaction(final String code, final AbstractOrderModel orderModel,
			final AmwayPaymentInfoData paymentInfoData, final PaymentTransactionType paymentTransactionType) {
		final PaymentTransactionModel transaction = getModelService().create(PaymentTransactionModel.class);

		transaction.setCode(code);
		if (PaymentTransactionType.OVERPAY_RETURN.equals(paymentTransactionType)) {
			transaction.setTransactionOwner(orderModel);
		} else {
			transaction.setOrder(orderModel);
		}
		transaction.setCurrency(orderModel.getCurrency());
		transaction.setPlannedAmount(BigDecimal.valueOf(paymentInfoData.getAmount().doubleValue()));
		transaction.setRequestId(paymentInfoData.getTransactionid());
		transaction.setRequestToken(paymentInfoData.getRequesttoken());
		transaction.setReceiptNo(paymentInfoData.getReferenceNumber());
		transaction.setPaymentProvider(
				getConfigurationService().getConfiguration().getString("lynx.pos.paymentprovider", "microsega"));
		getModelService().save(transaction);
		return transaction;

	}

	/**
	 * Method to create a payment transaction entry for a given code, payment
	 * transaction, order/cart and payment info
	 *
	 * @param code
	 * @param transaction
	 * @param orderModel
	 * @param paymentInfoData
	 */
	protected void createPaymentTransactionEntry(final String code, final PaymentTransactionModel transaction,
			final AbstractOrderModel orderModel, final AmwayPaymentInfoData paymentInfoData,
			final PaymentTransactionType paymentTransactionType) {
		final PaymentTransactionEntryModel transactionEntry = getModelService()
				.create(PaymentTransactionEntryModel.class);
		transactionEntry.setCode(code);
		transactionEntry.setType(paymentTransactionType);
		transactionEntry.setRequestId(paymentInfoData.getTransactionid());
		transactionEntry.setRequestToken(paymentInfoData.getRequesttoken());
		transactionEntry.setTime(new Date());
		transactionEntry.setPaymentTransaction(transaction);
		transactionEntry.setTransactionStatus(TransactionStatus.ACCEPTED.name());
		transactionEntry.setTransactionStatusDetails(TransactionStatusDetails.SUCCESFULL.name());
		transactionEntry.setCurrency(orderModel.getCurrency());
		transactionEntry.setAmount(BigDecimal.valueOf(paymentInfoData.getAmount().doubleValue()));
		getModelService().save(transactionEntry);
	}

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

	public AmwayPaymentModeService getAmwayPaymentModeService() {
		return amwayPaymentModeService;
	}

	public void setAmwayPaymentModeService(final AmwayPaymentModeService amwayPaymentModeService) {
		this.amwayPaymentModeService = amwayPaymentModeService;
	}

	public BaseSiteService getBaseSiteService() {
		return baseSiteService;
	}

	public void setBaseSiteService(final BaseSiteService baseSiteService) {
		this.baseSiteService = baseSiteService;
	}

	public Map<String, Map<String, AmwayCreatePaymentInfoStrategy>> getCreatePaymentInfoStrategiesMap() {
		return createPaymentInfoStrategiesMap;
	}

	public void setCreatePaymentInfoStrategiesMap(
			final Map<String, Map<String, AmwayCreatePaymentInfoStrategy>> createPaymentInfoStrategiesMap) {
		this.createPaymentInfoStrategiesMap = createPaymentInfoStrategiesMap;
	}

}
