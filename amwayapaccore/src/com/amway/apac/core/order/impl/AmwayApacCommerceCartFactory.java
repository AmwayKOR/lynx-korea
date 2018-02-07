package com.amway.apac.core.order.impl;

import de.hybris.platform.commerceservices.order.strategies.CommerceCartMetadataUpdateStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartMetadataParameter;
import de.hybris.platform.commerceservices.util.CommerceCartMetadataParameterUtils;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.serviceability.services.AmwayApacWarehouseServiceabilityService;
import com.amway.core.commerceservices.order.impl.AmwayCommerceCartFactory;


/**
 * Overriding {@link AmwayCommerceCartFactory} to implement APAC specific business logic.
 *
 * @author Shubham Goyal
 */
public class AmwayApacCommerceCartFactory extends AmwayCommerceCartFactory
{
	private AmwayApacWarehouseServiceabilityService amwayApacWarehouseServiceabilityService;

	private CommerceCartMetadataUpdateStrategy commerceCartMetadataUpdateStrategy;

	/**
	 * Overriding to set the {@link WarehouseModel} and {@link AddressModel} in the {@link CartModel}
	 */
	@Override
	public CartModel createCart()
	{
		final CartModel cart = super.createCart();
		final CommerceCartMetadataParameter commerceCartMetadataParameter = CommerceCartMetadataParameterUtils.parameterBuilder()
				.cart(cart).enableHooks(true).build();
		commerceCartMetadataUpdateStrategy.updateCartMetadata(commerceCartMetadataParameter);
		return cart;
	}

	/**
	 * @return the amwayApacWarehouseServiceabilityService
	 */
	public AmwayApacWarehouseServiceabilityService getAmwayApacWarehouseServiceabilityService()
	{
		return amwayApacWarehouseServiceabilityService;
	}

	/**
	 * @param amwayApacWarehouseServiceabilityService
	 *           the amwayApacWarehouseServiceabilityService to set
	 */
	@Required
	public void setAmwayApacWarehouseServiceabilityService(
			final AmwayApacWarehouseServiceabilityService amwayApacWarehouseServiceabilityService)
	{
		this.amwayApacWarehouseServiceabilityService = amwayApacWarehouseServiceabilityService;
	}

	/**
	 * @return the commerceCartMetadataUpdateStrategy
	 */
	public CommerceCartMetadataUpdateStrategy getCommerceCartMetadataUpdateStrategy()
	{
		return commerceCartMetadataUpdateStrategy;
	}

	/**
	 * @param commerceCartMetadataUpdateStrategy
	 *           the commerceCartMetadataUpdateStrategy to set
	 */
	@Required
	public void setCommerceCartMetadataUpdateStrategy(final CommerceCartMetadataUpdateStrategy commerceCartMetadataUpdateStrategy)
	{
		this.commerceCartMetadataUpdateStrategy = commerceCartMetadataUpdateStrategy;
	}


}
