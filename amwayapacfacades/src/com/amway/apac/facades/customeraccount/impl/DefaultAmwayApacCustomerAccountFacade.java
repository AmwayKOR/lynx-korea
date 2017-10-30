/**
 *
 */
package com.amway.apac.facades.customeraccount.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.core.customer.services.AmwayApacCustomerAccountService;
import com.amway.apac.facades.customeraccount.AmwayApacCustomerAccountFacade;
import com.amway.core.facades.order.impl.DefaultAmwayOrderFacade;


/**
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacCustomerAccountFacade extends DefaultAmwayOrderFacade implements AmwayApacCustomerAccountFacade
{
	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacCustomerAccountFacade.class);

	private AmwayApacCustomerAccountService amwayApacCustomerAccountService;

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
	public void setAmwayApacCustomerAccountService(final AmwayApacCustomerAccountService amwayApacCustomerAccountService)
	{
		this.amwayApacCustomerAccountService = amwayApacCustomerAccountService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.facades.customeraccount.AmwayApacCustomerAccountFacade#getCustomerOrderCounts()
	 */
	@Override
	public Integer getCustomerOrderCounts()
	{
		// YTODO Auto-generated method stub
		return getAmwayApacCustomerAccountService().getOrdersCount();

	}
}
