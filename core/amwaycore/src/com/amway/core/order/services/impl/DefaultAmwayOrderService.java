package com.amway.core.order.services.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.strategies.CheckoutCustomerStrategy;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.order.impl.DefaultOrderService;
import de.hybris.platform.ordercancel.dao.OrderCancelDao;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;


import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

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
	private static final String ORDER_NOT_FOUND_FOR_USER_AND_BASE_STORE = "Order with guid %s not found for current user in current BaseStore";

	private UserService userService;
	private CustomerAccountService customerAccountService;
	private BaseStoreService baseStoreService;
	private CheckoutCustomerStrategy checkoutCustomerStrategy;

	/**
	 * This method is borrowed from a method in hybris' DefaultOrderFacade. But instead of returning a DTO, it returns the
	 * OrderModel object itself.  If its not an anonymous checkout, it will get the current user and current base store, and
	 * fetch the order matching the order code parameter.
	 *
	 * @param 	orderCode The code for the order (ie order id)
	 * @return	OrderModel object
	 * @throws  UnknownIdentifierException order id/code is not found
	 * @since 	AmwayCore3.6
	 */
	@Override
	public OrderModel getOrderDetailsForCode(final String orderCode)
	{
		final BaseStoreModel baseStoreModel = getBaseStoreService().getCurrentBaseStore();

		OrderModel orderModel = null;
		if (getCheckoutCustomerStrategy().isAnonymousCheckout())
		{
			orderModel = getCustomerAccountService().getOrderDetailsForGUID(orderCode, baseStoreModel);
		}
		else
		{
			try
			{
				orderModel = getCustomerAccountService().getOrderForCode((CustomerModel) getUserService().getCurrentUser(), orderCode,
						baseStoreModel);
			}
			catch (final ModelNotFoundException e)
			{
				throw new UnknownIdentifierException(String.format(ORDER_NOT_FOUND_FOR_USER_AND_BASE_STORE, orderCode));
			}
		}

		if (orderModel == null)
		{
			throw new UnknownIdentifierException(String.format(ORDER_NOT_FOUND_FOR_USER_AND_BASE_STORE, orderCode));
		}
		return orderModel;
	}

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

	protected BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	@Required
	public void setBaseStoreService(final BaseStoreService service)
	{
		this.baseStoreService = service;
	}

	protected CheckoutCustomerStrategy getCheckoutCustomerStrategy()
	{
		return checkoutCustomerStrategy;
	}

	@Required
	public void setCheckoutCustomerStrategy(final CheckoutCustomerStrategy checkoutCustomerStrategy)
	{
		this.checkoutCustomerStrategy = checkoutCustomerStrategy;
	}

	protected <T extends CustomerAccountService> T getCustomerAccountService()
	{
		return (T) customerAccountService;
	}

	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}




}
