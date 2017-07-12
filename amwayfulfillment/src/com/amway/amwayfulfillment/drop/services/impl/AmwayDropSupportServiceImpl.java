package com.amway.amwayfulfillment.drop.services.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.amwayfulfillment.drop.AmwayDropProcessHelper;
import com.amway.amwayfulfillment.drop.services.AmwayDropSupportService;
import com.amway.amwayfulfillment.drop.strategies.AmwayDropProcessStrategy;
import com.amway.amwayfulfillment.drop.events.AmwayDropConfirmationEvent;
import com.amway.core.events.beans.AmwayBusinessEvent;


/**
 * Default drop support service implementation.
 */
public class AmwayDropSupportServiceImpl implements AmwayDropSupportService
{
	private static final Logger LOG = LoggerFactory.getLogger(AmwayDropSupportServiceImpl.class);

	private AmwayDropProcessStrategy dropProcessStrategy;

	private ModelService modelService;

	private EventService eventService;

	/**
	 * {@inheritDoc}
	 * Implementation delegates execution to {@link #dropProcessStrategy}.
	 */
	@Override
	public List<OrderModel> getOrdersForJob(CronJobModel dropJob)
	{
		return dropProcessStrategy.getOrdersForJob(dropJob);
	}

	private List<OrderModel> markOrdersReadyForDrop(final Collection<OrderModel> orders)
	{
		for (OrderModel order : orders)
		{
			final AmwayDropConfirmationEvent event = new AmwayDropConfirmationEvent(order);
			getEventService().publishEvent(event);
		}

		// @formatter:off
		final List<OrderModel> shippable = orders.stream()
				.filter(this::calculateDroppableFlag)
				.collect(Collectors.toList());
		// @formatter:on

		orders.forEach(o -> o.setStatus(dropProcessStrategy.getOrderStatusAfterDrop(o)));
		modelService.saveAll(orders);
		return shippable;
	}

	public void generateDropEvents(final List<OrderModel> droppableOrders, final CronJobModel dropJob)
	{
		final List<String> shippingCodes = AmwayDropProcessHelper.getOrderCodes(droppableOrders);
		final String entityIds = StringUtils.join(shippingCodes, ",");
		LOG.info("Perform drop. Orders: {}", entityIds);

		final AmwayBusinessEvent event = dropProcessStrategy.generateEvent(droppableOrders, dropJob);

		eventService.publishEvent(event);
		LOG.info("Drop event sent. Orders: {}", StringUtils.join(shippingCodes, ", "));
	}

	@Override
	public boolean calculateDroppableFlag(final OrderModel order)
	{
		return Optional.ofNullable(order.getEntries()) //
				.orElse(Collections.emptyList()).stream() //
				.map(AbstractOrderEntryModel::getProduct) //
				.filter(dropProcessStrategy::isDroppableProduct) //
				.findFirst() //
				.isPresent();
	}

	@Override
	public void doDrop(final List<OrderModel> orders, final CronJobModel jobModel)
	{
		final List<OrderModel> shippingOrders = markOrdersReadyForDrop(orders);
		if (!shippingOrders.isEmpty())
		{
			generateDropEvents(shippingOrders, jobModel);
		}
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	public EventService getEventService()
	{
		return eventService;
	}

	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}

	public AmwayDropProcessStrategy getDropProcessStrategy()
	{
		return dropProcessStrategy;
	}

	public void setDropProcessStrategy(AmwayDropProcessStrategy dropProcessStrategy)
	{
		this.dropProcessStrategy = dropProcessStrategy;
	}
}
