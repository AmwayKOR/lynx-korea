package com.amway.amwayfulfillment.drop.strategies;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.model.CronJobModel;

import java.util.List;

import com.amway.core.events.beans.AmwayBusinessEvent;


/**
 * Drop process strategy interface.
 */
public interface AmwayDropProcessStrategy
{
	List<OrderModel> getOrdersForJob(CronJobModel dropJob);

	boolean isDroppableProduct(ProductModel productModel);

	OrderStatus getOrderStatusAfterDrop(OrderModel order);

	AmwayBusinessEvent generateEvent(List<OrderModel> droppableOrders, CronJobModel dropJob);
}
