package com.amway.amwayfulfillment.test.drop;

import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.model.CronJobModel;

import java.util.ArrayList;
import java.util.List;

import com.amway.amwayfulfillment.drop.strategies.impl.DefaultAmwayDropProcessStrategy;


/**
 * Testing mock for amway drop process strategy.
 */
class TestableDefaultAmwayDropProcessStrategy extends DefaultAmwayDropProcessStrategy
{
	public static final String SHIPPABLE = "shippable";
	List<OrderModel> ordersForJob;

	List<OrderModel> droppableOrders;

	OrderStatus orderStatusAfterDrop;

	public TestableDefaultAmwayDropProcessStrategy()
	{
		droppableOrders = new ArrayList<>();
	}

	public TestableDefaultAmwayDropProcessStrategy(List<OrderModel> ordersForJob, OrderStatus orderStatusAfterDrop)
	{
		this.ordersForJob = ordersForJob;
		this.orderStatusAfterDrop = orderStatusAfterDrop;
	}

	@Override
	public List<OrderModel> getOrdersForJob(CronJobModel dropJob)
	{
		return ordersForJob;
	}

	@Override
	public OrderStatus getOrderStatusAfterDrop(OrderModel order)
	{
		return null;
	}

	@Override
	protected String getCountryCodeForEvent(List<OrderModel> droppableOrders, CronJobModel dropJob)
	{
		return DefaultAmwayDropProcessStrategyJunitTest.SW;
	}

	@Override
	protected int getMaxDropOrdersCnt()
	{
		return 10;
	}

	public void setOrdersForJob(List<OrderModel> ordersForJob)
	{
		this.ordersForJob = ordersForJob;
	}

	public void setOrderStatusAfterDrop(OrderStatus orderStatusAfterDrop)
	{
		this.orderStatusAfterDrop = orderStatusAfterDrop;
	}

	public List<OrderModel> generateOrders(int cnt, boolean shippable) {
		List<OrderModel> res = generateOrders(cnt);
		for (OrderModel o: res) {
			ArrayList<AbstractOrderEntryModel> entries = new ArrayList<>(1);
			o.setEntries(entries);
			OrderEntryModel e = new OrderEntryModel();
			entries.add(e);
			ProductModel productModel = new ProductModel();
			productModel.setCode(shippable ? SHIPPABLE : null);
			e.setProduct(productModel);
		}
		droppableOrders.addAll(res);
		return res;
	}

	public List<OrderModel> generateOrders(int cnt) {
		List<OrderModel> res = new ArrayList<OrderModel>(cnt);
		for (int i =0; i < cnt; i++) {
			OrderModel o = new OrderModel();
			o.setCode(Integer.toString(i + 1));
			o.setStatus(OrderStatus.CREATED);
			res.add(o);
		}
		return res;
	}

	@Override
	public boolean isDroppableProduct(ProductModel productModel)
	{
		return SHIPPABLE.equals(productModel.getCode());
	}
}
