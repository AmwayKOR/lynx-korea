package com.amway.amwayfinance.facades.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.servicelayer.event.EventService;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.amwayfinance.facades.AmwayOrderExternalFinanceChangesFacade;
import com.amway.amwayfinance.order.dto.PaymentEvent;
import com.amway.amwayfinance.services.AmwayOrderPaymentConfirmationValidationStrategy;
import com.amway.amwayfinance.services.AmwayTransactionInfoService;
import com.amway.core.event.orderprocessing.AmwayPaymentConfirmationEvent;


/**
 * Default implementation for external order update operations.
 */
public class DefaultAmwayOrderExternalFinanceChangesFacade implements AmwayOrderExternalFinanceChangesFacade
{
	private static final Logger LOG = LogManager.getLogger(DefaultAmwayOrderExternalFinanceChangesFacade.class);

	private Converter<PaymentEvent, PaymentTransactionEntryModel> payEventToPayTransactionEntryConverter;

	private BaseStoreService baseStoreService;

	private CustomerAccountService customerAccountService;

	private AmwayTransactionInfoService transactionInfoService;

	private EventService eventService;

	private AmwayOrderPaymentConfirmationValidationStrategy validationStrategy;

	/**
	 * {@inheritDoc}
	 * <p>Implementation creates payment transactions in the specified order.</p>
	 */
	@Override
	public void applyExternalPayment(final String baseStoreId, final String orderCode, final PaymentEvent paymentEvent)
	{
		LOG.info("External payment event for order {} received.", orderCode);
		final OrderModel order = findOrderByCode(baseStoreId, orderCode);

		if (!validationStrategy.validate(order))
		{
			throw new IllegalArgumentException("Current order is a suborder and is not eligible for payment confirmation.");
		}

		final PaymentTransactionEntryModel entry = getPayEventToPayTransactionEntryConverter().convert(paymentEvent);

		String transactionId = paymentEvent.getTransactionId();
		if (StringUtils.isEmpty(transactionId))
		{
			//as transaction id is optional, we check whether it is empty and assign a generated value
			transactionId = UUID.randomUUID().toString();
			paymentEvent.setTransactionId(transactionId);
			entry.setCode(transactionId);
		}

		getTransactionInfoService().createExternalCaptureTransactionEntry(order, transactionId, paymentEvent.getPaymentType(),
				entry);
	}

	/**
	 * {@inheritDoc}
	 * <p>Implementation notifies order processes by emitting event for each order's process.</p>
	 */
	@Override
	public void notifyAboutExternalPayment(final String baseStoreId, final String orderCode)
	{
		final OrderModel order = findOrderByCode(baseStoreId, orderCode);
		final AmwayPaymentConfirmationEvent event = new AmwayPaymentConfirmationEvent(order);
		getEventService().publishEvent(event);
	}

	private OrderModel findOrderByCode(final String baseStoreId, final String orderCode)
	{
		final BaseStoreModel baseStore = getBaseStoreService().getBaseStoreForUid(baseStoreId);
		return getCustomerAccountService().getOrderForCode(orderCode, baseStore);
	}

	public Converter<PaymentEvent, PaymentTransactionEntryModel> getPayEventToPayTransactionEntryConverter()
	{
		return payEventToPayTransactionEntryConverter;
	}

	@Required
	public void setPayEventToPayTransactionEntryConverter(
			Converter<PaymentEvent, PaymentTransactionEntryModel> payEventToPayTransactionEntryConverter)
	{
		this.payEventToPayTransactionEntryConverter = payEventToPayTransactionEntryConverter;
	}

	public AmwayTransactionInfoService getTransactionInfoService()
	{
		return transactionInfoService;
	}

	@Required
	public void setTransactionInfoService(AmwayTransactionInfoService transactionInfoService)
	{
		this.transactionInfoService = transactionInfoService;
	}

	public EventService getEventService()
	{
		return eventService;
	}

	@Required
	public void setEventService(EventService eventService)
	{
		this.eventService = eventService;
	}

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	public CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	@Required
	public void setCustomerAccountService(CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	public AmwayOrderPaymentConfirmationValidationStrategy getValidationStrategy()
	{
		return validationStrategy;
	}

	@Required
	public void setValidationStrategy(AmwayOrderPaymentConfirmationValidationStrategy validationStrategy)
	{
		this.validationStrategy = validationStrategy;
	}
}
