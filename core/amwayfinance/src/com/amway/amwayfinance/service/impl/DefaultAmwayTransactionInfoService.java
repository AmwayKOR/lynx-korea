package com.amway.amwayfinance.service.impl;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.c2l.CurrencyModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.payment.dto.TransactionStatus;
import de.hybris.platform.payment.impl.DefaultTransactionInfoService;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.core.enums.AmwayPaymentModeGroupType;
import com.amway.core.model.payment.AmwayPaymentModeGroupModel;
import com.amway.amwayfinance.service.AmwayTransactionInfoService;
import com.google.common.collect.Lists;


/**
 * <p>Default implementation of {@link AmwayTransactionInfoService} interface.</p>
 * <p>The primary goal of extending the class is to add an external payment events processing.</p>
 */
public class DefaultAmwayTransactionInfoService extends DefaultTransactionInfoService implements AmwayTransactionInfoService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAmwayTransactionInfoService.class);

	private ModelService modelService;
	private Set<AmwayPaymentModeGroupType> allowedPaymentTypes;
	private Set<OrderStatus> allowedOrderStatuses;
	/**
	 * Set of allowed payment statuses. If the order payment status is not in the list, the transaction will be qualified
	 * as ignored.
	 */
	private Set<PaymentStatus> allowedPaymentStatuses;

	/**
	 * {@inheritDoc}
	 * <p>Implementation:</p>
	 * <ol>
	 * <li>verifies parameters (e.g. event currency must be equal to the order's one)</li>
	 * <li>creates new {@link PaymentTransactionModel}</li>
	 * <li>adds passed in {@link PaymentTransactionEntryModel} into the previously created {@link PaymentTransactionModel}</li>
	 * <li>puts payment transaction to the order and saves it</li>
	 * </ol>
	 */
	@Override
	public void createExternalCaptureTransactionEntry(final AbstractOrderModel order, final String transactionId,
			final String paymentType, final PaymentTransactionEntryModel entry)
	{
		verifyParameters(order, transactionId, paymentType, entry);

		if (!order.getCurrency().equals(entry.getCurrency()))
		{
			LOG.warn("External payment event: event currency({}) for transaction id = '{}' doesn't match the order's one ({}). "
							+ "Order code = '{}'", entry.getCurrency().getIsocode(), transactionId, order.getCurrency().getIsocode(),
					order.getCode());
			throw new IllegalArgumentException(
					"External payment event currency doesn't match the order's currency. Can't process the transaction.");
		}

		final AmwayPaymentModeGroupModel group = order.getPaymentMode().getGroup();
		if (group != null && !isPaymentTypeAllowed(group.getType()))
		{
			createTransactionEntryModel(order, transactionId, paymentType, entry, IGNORED_PAYMENT_TRANSACTION_STATUS);
			throw new IllegalArgumentException("External payment event: only offline payment methods are valid for that call.");
		}

		final PaymentStatus paymentStatus = order.getPaymentStatus();
		if (! isPaymentStatusAllowed(paymentStatus)) {
			createTransactionEntryModel(order, transactionId, paymentType, entry, IGNORED_PAYMENT_TRANSACTION_STATUS);
			throw new IllegalArgumentException("External payment event: order payment status should be one of: "
					+ StringUtils.join(allowedPaymentStatuses, ","));
		}
		if (!isOrderStatusAllowed(order.getStatus()))
		{
			createTransactionEntryModel(order, transactionId, paymentType, entry, IGNORED_PAYMENT_TRANSACTION_STATUS);
			throw new IllegalArgumentException("External payment event: incorrect order status.");
		}

		// @formatter:off
		final Optional<PaymentTransactionEntryModel> existingTransaction = order.getPaymentTransactions()
				.stream()
				.map(PaymentTransactionModel::getEntries)
				.flatMap(List::stream)
				.filter(e -> transactionId.equals(e.getCode()))
				.findFirst();
		// @formatter:on

		if (existingTransaction.isPresent())
		{
			createTransactionEntryModel(order, transactionId, paymentType, entry, IGNORED_PAYMENT_TRANSACTION_STATUS);
			throw new IllegalArgumentException(
					"External payment event: payment transaction with the given id already exists - " + transactionId);
		}

		createTransactionEntryModel(order, transactionId, paymentType, entry, entry.getTransactionStatus());
	}

	private boolean isPaymentStatusAllowed(PaymentStatus paymentStatus)
	{
		return CollectionUtils.isEmpty(getAllowedPaymentStatuses()) || getAllowedPaymentStatuses().contains(paymentStatus);
	}

	private boolean isPaymentTypeAllowed(final AmwayPaymentModeGroupType type)
	{
		return CollectionUtils.isEmpty(getAllowedPaymentTypes()) || getAllowedPaymentTypes().contains(type);
	}

	private boolean isOrderStatusAllowed(final OrderStatus orderStatus)
	{
		return CollectionUtils.isEmpty(getAllowedOrderStatuses()) || getAllowedOrderStatuses().contains(orderStatus);
	}

	private void verifyParameters(AbstractOrderModel order, String transactionId, String paymentType,
			PaymentTransactionEntryModel entry)
	{
		ServicesUtil.validateParameterNotNull(order, "Order must not be null.");
		ServicesUtil.validateParameterNotNull(order.getCurrency(), "Order currency must not be null.");
		ServicesUtil.validateParameterNotNull(entry.getCurrency(), "PaymentTransactionEntry currency must not be null.");
		ServicesUtil.validateParameterNotNull(transactionId, "PaymentEvent#transactionId must not be null.");
		ServicesUtil.validateParameterNotNull(order.getPaymentMode(), "order#paymentMode must not be null.");
		ServicesUtil.validateParameterNotNull(order.getPaymentMode().getGroup(), "order#paymentMode#group must not be null.");

		if (StringUtils.isEmpty(entry.getTransactionStatus()))
		{
			throw new IllegalArgumentException("Transaction status is missing.");
		}
		if (entry.getAmount() == null)
		{
			throw new IllegalArgumentException("Transaction amount is missing.");
		}
		if (BigDecimal.ZERO.compareTo(entry.getAmount()) > 0)
		{
			throw new IllegalArgumentException("Transaction amount must be a positive number or 0.");
		}
		if (entry.getTime() == null)
		{
			throw new IllegalArgumentException("Transaction date is missing.");
		}
		if (StringUtils.isEmpty(paymentType))
		{
			throw new IllegalArgumentException("Transaction payment type is missing.");
		}
	}

	private void createTransactionEntryModel(final AbstractOrderModel order, final String transactionId, final String paymentType,
			final PaymentTransactionEntryModel entry, final String status)
	{
		//always create new transaction for each payment
		final PaymentTransactionModel transactionModel = createTransactionModel(order, transactionId, paymentType,
				entry.getCurrency(), entry.getAmount());
		entry.setTransactionStatus(status);
		transactionModel.setEntries(Lists.newArrayList(entry));
		modelService.saveAll(transactionModel, order);
	}

	private PaymentTransactionModel createTransactionModel(final AbstractOrderModel order, final String transactionId,
			final String paymentType, final CurrencyModel currency, final BigDecimal amount)
	{
		final PaymentTransactionModel transactionModel = modelService.create(PaymentTransactionModel.class);
		transactionModel.setPlannedAmount(amount);
		transactionModel.setCode(transactionId);
		transactionModel.setPaymentProvider(paymentType);
		transactionModel.setOrder(order);
		transactionModel.setCurrency(currency);

		final List<PaymentTransactionModel> transactions = new ArrayList<>(order.getPaymentTransactions());
		transactions.add(transactionModel);
		order.setPaymentTransactions(transactions);

		return transactionModel;
	}

	/**
	 * {@inheritDoc}
	 * Implementation just checks {@link PaymentTransactionEntryModel#getTransactionStatus()}
	 * for {@link AmwayTransactionInfoService#IGNORED_PAYMENT_TRANSACTION_STATUS}.
	 */
	@Override
	public boolean isTransactionEntryIgnored(final PaymentTransactionEntryModel entry)
	{
		if (entry == null)
		{
			return false;
		}
		final String transactionStatus = entry.getTransactionStatus();
		return IGNORED_PAYMENT_TRANSACTION_STATUS.equals(transactionStatus)
				|| TransactionStatus.ERROR.name().equals(transactionStatus)
				|| TransactionStatus.REJECTED.name().equals(transactionStatus);
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	public Set<AmwayPaymentModeGroupType> getAllowedPaymentTypes()
	{
		return allowedPaymentTypes;
	}

	public void setAllowedPaymentTypes(Set<AmwayPaymentModeGroupType> allowedPaymentTypes)
	{
		this.allowedPaymentTypes = allowedPaymentTypes;
	}

	public Set<OrderStatus> getAllowedOrderStatuses()
	{
		return allowedOrderStatuses;
	}

	public void setAllowedOrderStatuses(Set<OrderStatus> allowedOrderStatuses)
	{
		this.allowedOrderStatuses = allowedOrderStatuses;
	}

	public Set<PaymentStatus> getAllowedPaymentStatuses()
	{
		return allowedPaymentStatuses;
	}

	public void setAllowedPaymentStatuses(Set<PaymentStatus> allowedPaymentStatuses)
	{
		this.allowedPaymentStatuses = allowedPaymentStatuses;
	}
}
