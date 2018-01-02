package com.amway.apac.core.checkout.services.impl;

import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.checkout.services.AmwayApacCommerceCheckoutService;
import com.amway.apac.core.checkout.services.AmwayApacCommerceWarehouseStrategy;
import com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService;


/**
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCommerceCheckoutService extends DefaultAmwayCommerceCheckoutService
		implements AmwayApacCommerceCheckoutService
{
	private AmwayApacCommerceWarehouseStrategy amwayApacCommerceWarehouseStrategy;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setWarehouse(final CommerceCheckoutParameter parameter)
	{
		return getAmwayApacCommerceWarehouseStrategy().setWarehouse(parameter);
	}

	/**
	 * @return the amwayApacCommerceWarehouseStrategy
	 */
	public AmwayApacCommerceWarehouseStrategy getAmwayApacCommerceWarehouseStrategy()
	{
		return amwayApacCommerceWarehouseStrategy;
	}

	/**
	 * @param amwayApacCommerceWarehouseStrategy
	 *           the amwayApacCommerceWarehouseStrategy to set
	 */
	@Required
	public void setAmwayApacCommerceWarehouseStrategy(final AmwayApacCommerceWarehouseStrategy amwayApacCommerceWarehouseStrategy)
	{
		this.amwayApacCommerceWarehouseStrategy = amwayApacCommerceWarehouseStrategy;
	}
}
