package com.amway.apac.facades.customeraccount.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.customer.services.AmwayApacCustomerAccountService;
import com.amway.apac.facades.customeraccount.AmwayApacOrderFacade;
import com.amway.core.facades.order.impl.DefaultAmwayOrderFacade;


/**
 * Default implementation of {@link AmwayApacOrderFacade}
 *
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacOrderFacade extends DefaultAmwayOrderFacade implements AmwayApacOrderFacade
{

	private AmwayApacCustomerAccountService amwayApacCustomerAccountService;
	private List<String> orderHistoryTypeOptions;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getCustomerOrderCounts()
	{
		return getAmwayApacCustomerAccountService().getOrdersCount();

	}

	/**
	 * @return the amwayApacCustomerAccountService
	 */
	public AmwayApacCustomerAccountService getAmwayApacCustomerAccountService()
	{
		return amwayApacCustomerAccountService;
	}

	/**
	 * @param amwayApacCustomerAccountService
	 *           the amwayApacCustomerAccountService to set
	 */
	@Required
	public void setAmwayApacCustomerAccountService(final AmwayApacCustomerAccountService amwayApacCustomerAccountService)
	{
		this.amwayApacCustomerAccountService = amwayApacCustomerAccountService;
	}

	@Override
	public List<String> getOrderHistoryDateOptions()
	{
		final List<String> orderDateOptions = new ArrayList<>(15);
		LocalDate currentDate = LocalDate.now();
		for (int i = 0; i < 15; i++)
		{
			currentDate = currentDate.minusMonths(1);
			orderDateOptions.add(DateTimeFormatter.ofPattern("yyyy-MM").format(currentDate));
		}
		return orderDateOptions;
	}

	/**
	 * @return the orderHistoryTypeOptions
	 */
	@Override
	public List<String> getOrderHistoryTypeOptions()
	{
		return orderHistoryTypeOptions;
	}

	/**
	 * @param orderHistoryTypeOptions
	 *           the orderHistoryTypeOptions to set
	 */
	@Required
	public void setOrderHistoryTypeOptions(final List<String> orderHistoryTypeOptions)
	{
		this.orderHistoryTypeOptions = orderHistoryTypeOptions;
	}
}
