package com.amway.apac.core.checkout.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.CART_MODEL;
import static com.amway.apac.core.constants.AmwayapacCoreConstants.COMMERCE_CHECKOUT_PARAMETER;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.order.CommerceCartCalculationStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartParameter;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.checkout.services.AmwayApacCommerceWarehouseStrategy;


/**
 * Default implementation for {@link AmwayApacCommerceWarehouseStrategy}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCommerceWarehouseStrategy implements AmwayApacCommerceWarehouseStrategy
{
	private ModelService modelService;
	private CommerceCartCalculationStrategy commerceCartCalculationStrategy;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setWarehouse(final CommerceCheckoutParameter parameter)
	{
		validateParameterNotNullStandardMessage(COMMERCE_CHECKOUT_PARAMETER, parameter);
		final CartModel cartModel = parameter.getCart();
		final WarehouseModel warehouse = parameter.getWarehouse();

		validateParameterNotNullStandardMessage(CART_MODEL, cartModel);

		cartModel.setWarehouse(warehouse);
		cartModel.setCalculated(Boolean.FALSE);

		final List<ItemModel> itemsToSave = new ArrayList<>();
		itemsToSave.add(cartModel);

		// setting the warehouse for each order entry
		if (CollectionUtils.isNotEmpty(cartModel.getEntries()))
		{
			for (final AbstractOrderEntryModel entry : cartModel.getEntries())
			{
				entry.setWareHouse(warehouse);
			}
			itemsToSave.addAll(cartModel.getEntries());
		}

		getModelService().saveAll(itemsToSave);
		getModelService().refresh(cartModel);

		final CommerceCartParameter commerceCartParameter = new CommerceCartParameter();
		commerceCartParameter.setEnableHooks(true);
		commerceCartParameter.setCart(cartModel);
		getCommerceCartCalculationStrategy().calculateCart(commerceCartParameter);

		return true;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the commerceCartCalculationStrategy
	 */
	public CommerceCartCalculationStrategy getCommerceCartCalculationStrategy()
	{
		return commerceCartCalculationStrategy;
	}

	/**
	 * @param commerceCartCalculationStrategy
	 *           the commerceCartCalculationStrategy to set
	 */
	@Required
	public void setCommerceCartCalculationStrategy(final CommerceCartCalculationStrategy commerceCartCalculationStrategy)
	{
		this.commerceCartCalculationStrategy = commerceCartCalculationStrategy;
	}
}
