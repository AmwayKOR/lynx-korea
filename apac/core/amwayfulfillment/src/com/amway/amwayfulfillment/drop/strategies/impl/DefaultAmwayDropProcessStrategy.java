package com.amway.amwayfulfillment.drop.strategies.impl;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.util.Config;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.amway.amwayfulfillment.drop.AmwayDropProcessHelper;
import com.amway.amwayfulfillment.drop.strategies.AmwayDropProcessStrategy;
import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.util.AmwayBusinessEventBuilder;


/**
 * <p>Default drop process strategy implementation.</p>
 * <p>Consider the following features of the default implementation:
 * <ul>
 * <li>All products consider shippable</li>
 * </ul>
 * </p>
 */
public abstract class DefaultAmwayDropProcessStrategy implements AmwayDropProcessStrategy
{
	/**
	 * Extension parameter - maximum order count per event.
	 */
	private static final String PARAM_DROP_MAX_ORDERS = "amwayfulfilmentprocess.drop.maxOrders";

	private int dropMaxOrders;

	public DefaultAmwayDropProcessStrategy()
	{
		dropMaxOrders = getMaxDropOrdersCnt();
	}

	protected int getMaxDropOrdersCnt()
	{
		return NumberUtils.toInt(Config.getParameter(PARAM_DROP_MAX_ORDERS));
	}

	protected int getMaxOrders()
	{
		return dropMaxOrders;
	}

	@Override
	public abstract List<OrderModel> getOrdersForJob(final CronJobModel dropJob);

	@Override
	public boolean isDroppableProduct(final ProductModel productModel)
	{
		return true;
	}

	@Override
	public abstract OrderStatus getOrderStatusAfterDrop(final OrderModel order);

	/**
	 * Event format is not country specific.
	 *
	 * @param droppableOrders
	 * 		orders to be dropped.
	 * @param dropJob
	 * 		related cron job.
	 * @return event to be published to external systems.
	 */
	@Override
	public AmwayBusinessEvent generateEvent(final List<OrderModel> droppableOrders, final CronJobModel dropJob)
	{
		final List<String> entityIds = AmwayDropProcessHelper.getOrderCodes(droppableOrders);
		final String countryCode = getCountryCodeForEvent(droppableOrders, dropJob);
		// @formatter:off
		return new AmwayBusinessEventBuilder()
				.setEventName(AmwayBusinessEventBuilder.EventName.READY_FOR_DROP)
				.setEntityId(StringUtils.join(entityIds, ","))
				.setEntityName(AmwayBusinessEventBuilder.EntityName.ORDER_LIST)
				.setTrigger(AmwayBusinessEventBuilder.Trigger.CRON_JOB)
				.setCountryCode(countryCode).setTargetSystem(AmwayBusinessEventBuilder.TargetSystem.WM)
				.build();
		// @formatter:on
	}

	/**
	 * Method return country code for the specific list of shippable order and drop job.
	 *
	 * @param droppableOrders
	 * 		orders to be dropped.
	 * @param dropJob
	 * 		related cron job.
	 * @return country code for the generating event.
	 */
	protected abstract String getCountryCodeForEvent(final List<OrderModel> droppableOrders, final CronJobModel dropJob);
}
