package com.amway.core.order.services.impl;


import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.impl.DefaultOrderService;
import de.hybris.platform.ordercancel.dao.OrderCancelDao;

import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.amway.core.enums.AmwayCartType;
import com.amway.core.order.dao.AmwayOrderDao;
import com.amway.core.order.services.AmwayOrderService;


/**
 * Default implementation
 */
public class DefaultAmwayOrderService extends DefaultOrderService implements AmwayOrderService
{
	private final Logger LOG = Logger.getLogger(DefaultAmwayOrderService.class.getName());
	private FlexibleSearchService searchService;
	private OrderCancelDao orderCancelDao;
	private AmwayOrderDao amwayOrderDao;

	/**
	 * @return orderCancelDao
	 */
	public OrderCancelDao getOrderCancelDao()
	{
		return orderCancelDao;
	}

	/**
	 * @param orderCancelDao the orderCancelDao to set
	 */
	public void setOrderCancelDao(final OrderCancelDao orderCancelDao)
	{
		this.orderCancelDao = orderCancelDao;
	}

	/**
	 * @return searchService
	 */
	public FlexibleSearchService getSearchService()
	{
		return searchService;
	}

	/**
	 * @param searchService the searchService to set
	 */
	public void setSearchService(final FlexibleSearchService searchService)
	{
		this.searchService = searchService;
	}

	/**
	 * @return amwayOrderDao
	 */
	public AmwayOrderDao getAmwayOrderDao()
	{
		return amwayOrderDao;
	}

	/**
	 * @param amwayOrderDao the amwayOrderDao to set
	 */
	public void setAmwayOrderDao(final AmwayOrderDao amwayOrderDao)
	{
		this.amwayOrderDao = amwayOrderDao;
	}




}
