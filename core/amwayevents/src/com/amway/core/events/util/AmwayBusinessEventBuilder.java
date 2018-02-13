package com.amway.core.events.util;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.util.Assert;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.constants.AmwayeventsConstants;
import com.amway.core.model.AmwayAccountModel;


/**
 * Helper class for amway events instantiation.
 */
public class AmwayBusinessEventBuilder
{

	private static final String FAIL_MESSAGE = "Assertion failed, field '%1$s' shouldn't be null: ";
	private static final String FAIL_MESSAGE_ENTITY_ID = String.format(FAIL_MESSAGE, "entityId");
	private static final String FAIL_MESSAGE_ENTITY_NAME = String.format(FAIL_MESSAGE, "entityName");
	private static final String FAIL_MESSAGE_NAME = String.format(FAIL_MESSAGE, "name");
	private static final String FAIL_MESSAGE_TARGET_SYSTEM = String.format(FAIL_MESSAGE, "targetSystem");
	private static final String FAIL_MESSAGE_TRIGGER = String.format(FAIL_MESSAGE, "trigger");


	public interface IEventName
	{
		String getName();
	}

	public enum EventName implements IEventName
	{
		ADJUST_BALANCE("AdjustBalance"),
		READY_FOR_DROP("Ready for Fulfillment"),
		READY_FOR_EBS("Ready For EBS"),
		INVOICE_READY("Invoice Ready"),
		MDMS_ASYNC_CALL("MdmsAsyncCall"),
		DELIVERYWARE_ASYNC_CALL("DeliveryWareAsyncCall"),
		SAFERPAY_ORDER_CANCEL("SaferpayOrderCancel"),
		ORDER_CANCELLED("OrderCancelled"),
		RETURN_PLACED("ReturnPlaced"),
		RETURN_INVOICE_CREATED("ReturnInvoiceCreated"),
		;

		public final String value;

		EventName(final String value)
		{
			this.value = value;
		}

		@Override
		public String getName()
		{
			return value;
		}
	}

	public interface ITrigger
	{
		String getName();
	}

	public enum Trigger implements ITrigger
	{
		CUSTOMER("Customer"),
		CRON_JOB("cronjob"),
		MDMS_SERVICE("MdmsService"),
		CSA("CSA")
		;
		public final String value;

		Trigger(final String value)
		{
			this.value = value;
		}

		@Override
		public String getName()
		{
			return value;
		}
	}

	public interface IEntityName {
		String getName();
	}

	public enum EntityName implements IEntityName
	{
		ORDER("Order"),
		ORDER_LIST("orders"),
		RETURN_REQUEST("Return"),
		PRODUCT_TNA("ProductTna"),
		N_A("NotApplicable")
		;
		public final String value;

		EntityName(final String value)
		{
			this.value = value;
		}

		@Override
		public String getName()
		{
			return value;
		}
	}

	public enum TargetSystem
	{
		INTERNAL_CRON(AmwayeventsConstants.TARGET_INTERNAL_CRON),
		WM(AmwayeventsConstants.TARGET_WM),
		MDMS("Mdms"),
		DELIVERY_WARE("DeliveryWare"),
		SAFERPAY("Saferpay");

		public final String value;

		TargetSystem(final String value)
		{
			this.value = value;
		}
	}


	private final AmwayBusinessEvent event;

	public AmwayBusinessEventBuilder()
	{
		event = new AmwayBusinessEvent();
		defaultInit();
	}

	/**
	 * Filling event fields with info, consumed from Order if possible
	 * <ul>
	 * <li>entityId</li>
	 * <li>entityName</li>
	 * <li>countryCode</li>
	 * <li>accountId</li>
	 * </ul>
	 *
	 * @param abstractOrderModel
	 */
	public AmwayBusinessEventBuilder(final AbstractOrderModel abstractOrderModel)
	{
		event = new AmwayBusinessEvent();
		defaultInit();
		consumeOrderInfo(abstractOrderModel, null);

		setEntityId(abstractOrderModel.getCode());
		setEntityName(EntityName.ORDER);
	}

	public AmwayBusinessEventBuilder(final ReturnRequestModel returnRequestModel)
	{
		event = new AmwayBusinessEvent();
		defaultInit();
		consumeOrderInfo(returnRequestModel.getOrder(), returnRequestModel);

		setEntityId(returnRequestModel.getCode());
		setEntityName(EntityName.RETURN_REQUEST);
	}

	protected void defaultInit()
	{
		event.setGenerationTime(new Date());
	}

	protected void consumeOrderInfo(final AbstractOrderModel orderModel, ReturnRequestModel returnRequestModel)
	{
		if (orderModel != null)
		{
			// @formatter:off
			Optional.ofNullable(orderModel.getSite())
					.map(BaseSiteModel::getDefaultCountry)
					.map(CountryModel::getIsocode)
					.ifPresent(this::setCountryCode);

			Optional.ofNullable(orderModel.getUser())
					.filter(user -> user instanceof CustomerModel)
					.map(user -> (CustomerModel) user)
					.map(CustomerModel::getAccounts)
					.filter(accounts -> !accounts.isEmpty())
					.map(accounts -> accounts.iterator().next())
					.map(AmwayAccountModel::getCode)
					.ifPresent(this::setAccountId);
			// @formatter:on
		}
		else if (returnRequestModel != null)
		{
			// @formatter:off
			Optional.ofNullable(returnRequestModel.getSite())
					.map(BaseSiteModel::getDefaultCountry)
					.map(CountryModel::getIsocode)
					.ifPresent(this::setCountryCode);

			Optional.ofNullable(returnRequestModel.getAccount())
					.map(AmwayAccountModel::getCode)
					.ifPresent(this::setAccountId);
		// @formatter:on
		}
	}

	public AmwayBusinessEventBuilder setGenerationTime(final Date dateTime)
	{
		event.setGenerationTime(dateTime);
		return this;
	}

	public AmwayBusinessEventBuilder setEntityId(final String entityId)
	{
		event.setEntityID(entityId);
		return this;
	}

	public AmwayBusinessEventBuilder setEntityName(final IEntityName entityName)
	{
		event.setEntityName(entityName.getName());
		return this;
	}

	public AmwayBusinessEventBuilder setEventName(final IEventName eventName)
	{
		event.setName(eventName.getName());
		return this;
	}

	public AmwayBusinessEventBuilder setTargetSystem(final TargetSystem targetSystem)
	{
		event.setTargetSystem(targetSystem.value);
		return this;
	}

	public AmwayBusinessEventBuilder setCountryCode(final String countryCode)
	{
		event.setCountryCode(countryCode);
		return this;
	}

	public AmwayBusinessEventBuilder setAccountId(final String accountId)
	{
		event.setAccountID(accountId);
		return this;
	}

	public AmwayBusinessEventBuilder setTrigger(final ITrigger trigger)
	{
		event.setTriggeredBy(trigger.getName());
		return this;
	}

	public AmwayBusinessEventBuilder addMetaEntry(final String key, final String value)
	{
		if (event.getMeta() == null)
		{
			event.setMeta(new HashMap<>());
		}
		event.getMeta().put(key, value);
		return this;
	}

	public AmwayBusinessEvent build()
	{
		Assert.notNull(event.getEntityID(), FAIL_MESSAGE_ENTITY_ID);
		Assert.notNull(event.getEntityName(), FAIL_MESSAGE_ENTITY_NAME);
		Assert.notNull(event.getName(), FAIL_MESSAGE_NAME);
		Assert.notNull(event.getTargetSystem(), FAIL_MESSAGE_TARGET_SYSTEM);
		Assert.notNull(event.getTriggeredBy(), FAIL_MESSAGE_TRIGGER);

		return event;
	}

}
