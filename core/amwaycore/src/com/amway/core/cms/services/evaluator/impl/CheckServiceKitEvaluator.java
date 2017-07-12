package com.amway.core.cms.services.evaluator.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;

import org.apache.log4j.Logger;


/**
 * check service kit should be added in the cart
 */
public class CheckServiceKitEvaluator
{
	private static final Logger LOG = Logger.getLogger(CheckServiceKitEvaluator.class);
	private String serviceKitCode;

	/**
	 * To evaluate the service kit present in cart or not.
	 *
	 * @param cartModel
	 * @return boolean
	 */
	public boolean evaluate(final CartModel cartModel)
	{

		for (final AbstractOrderEntryModel cartEntry : cartModel.getEntries())
		{
			if (serviceKitCode.equals(cartEntry.getProduct().getCode()))
			{
				LOG.debug("servicekit with code : " + serviceKitCode + " is present in the cart.");
				return true;
			}
		}
		LOG.debug("servicekit with code : " + serviceKitCode + " is not present in the cart.");
		return false;
	}

	/**
	 * @return the serviceKitCode
	 */
	public String getServiceKitCode()
	{
		return serviceKitCode;
	}

	/**
	 * @param serviceKitCode the serviceKitCode to set
	 */
	public void setServiceKitCode(final String serviceKitCode)
	{
		this.serviceKitCode = serviceKitCode;
	}
}
