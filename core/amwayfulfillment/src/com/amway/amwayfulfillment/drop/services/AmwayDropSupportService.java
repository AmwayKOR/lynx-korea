package com.amway.amwayfulfillment.drop.services;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.cronjob.model.CronJobModel;

import java.util.List;


/**
 * Service contains 'drop process' related functionality.
 */
public interface AmwayDropSupportService
{
	/**
	 * Get the list of orders for the specified drop process job.
	 *
	 * @param dropJob
	 * 		configured drop cron job.
	 * @return list of orders for a particular job.
	 */
	List<OrderModel> getOrdersForJob(CronJobModel dropJob);

	/**
	 * Calculate whether the order is shippable or not.
	 *
	 * @param order
	 * 		order mode.
	 * @return true - the order is shippable.
	 */
	boolean calculateDroppableFlag(OrderModel order);

	/**
	 * Perform drop for the specified orders and drop job.
	 *
	 * @param orders
	 * 		list of orders to drop.
	 * @param jobModel
	 * 		job representing the drop process.
	 */
	void doDrop(List<OrderModel> orders, CronJobModel jobModel);
}
