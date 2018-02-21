package com.amway.core.order.cancel.denialstrategies.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.ordercancel.OrderCancelDenialReason;
import de.hybris.platform.ordercancel.impl.denialstrategies.SentToWarehouseDenialStrategy;
import de.hybris.platform.ordercancel.model.OrderCancelConfigModel;
import de.hybris.platform.servicelayer.user.UserService;


/**
 * Amway sent to warehouse denial strategy
 */
public class AmwaySentToWarehouseDenialStrategy extends SentToWarehouseDenialStrategy
{
	private UserService userService;

	/**
	 * @return userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * To get cancel denial reason.
	 *
	 * @param configuration
	 * @param order
	 * @param requester
	 * @param partialCancel
	 * @param partialEntryCancel
	 */
	@Override
	public OrderCancelDenialReason getCancelDenialReason(final OrderCancelConfigModel configuration, final OrderModel order,
			final PrincipalModel requester, final boolean partialCancel, final boolean partialEntryCancel)
	{
		if (getUserService().getAdminUser().equals(requester))
		{
			return null;
		}
		else
		{
			return super.getCancelDenialReason(configuration, order, requester, partialCancel, partialEntryCancel);
		}
	}
}
