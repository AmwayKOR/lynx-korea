package com.amway.apac.core.warehousing.strategies.impl;

import de.hybris.platform.commerceservices.stock.strategies.WarehouseSelectionStrategy;
import de.hybris.platform.order.CartService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.warehousing.atp.strategy.PickupWarehouseSelectionStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


/**
 * Warehouse selection strategy implementation for APAC
 *
 * @author Parvesh Goyal
 *
 */
public class DefaultAmwayApacWarehouseSelectionStrategy implements PickupWarehouseSelectionStrategy, WarehouseSelectionStrategy
{
	private CartService cartService;
	private BaseStoreService baseStoreService;

	/**
	 * Warehouse selection strategy given the base store.
	 */
	@Override
	public List<WarehouseModel> getWarehousesForBaseStore(final BaseStoreModel baseStore)
	{
		if (getCartService().hasSessionCart())
		{
			final WarehouseModel cartWarehouse = getCartService().getSessionCart().getWarehouse();
			if (baseStore.getWarehouses().contains(cartWarehouse))
			{
				return Collections.singletonList(cartWarehouse);
			}
		}
		return Collections.emptyList();
	}

	/**
	 * Warehouse selection strategy given the point of service.
	 */
	@Override
	public Collection<WarehouseModel> getWarehouses(final PointOfServiceModel paramPointOfServiceModel)
	{
		if (getCartService().hasSessionCart())
		{
			final WarehouseModel cartWarehouse = getCartService().getSessionCart().getWarehouse();
			if (getBaseStoreService().getCurrentBaseStore().getWarehouses().contains(cartWarehouse))
			{
				return Collections.singletonList(cartWarehouse);
			}
		}
		return Collections.emptyList();
	}

	/**
	 * @return the cartService
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * @param cartService
	 *           the cartService to set
	 */
	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

}
