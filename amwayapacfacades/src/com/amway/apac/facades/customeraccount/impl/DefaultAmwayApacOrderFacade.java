package com.amway.apac.facades.customeraccount.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacOrderFacade.class);

	private AmwayApacCustomerAccountService amwayApacCustomerAccountService;

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
}
