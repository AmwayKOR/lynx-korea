package com.amway.apac.core.checkout.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.checkout.services.AmwayApacCommerceCheckoutService;
import com.amway.apac.core.checkout.services.AmwayApacCommerceWarehouseStrategy;
import com.amway.core.checkout.services.impl.DefaultAmwayCommerceCheckoutService;


/**
 * Overriding {@link DefaultAmwayCommerceCheckoutService} to implement APAC specific business logic. Default
 * implementation for {@link AmwayApacCommerceCheckoutService}.
 *
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
		validateParameterNotNullStandardMessage("parameter", parameter);

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
