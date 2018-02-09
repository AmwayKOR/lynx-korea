/**
 *
 */
package com.amway.apac.core.order.strategies.impl;

import de.hybris.platform.commerceservices.order.strategies.impl.DefaultCommerceCartMetadataUpdateStrategy;
import de.hybris.platform.commerceservices.service.data.CommerceCartMetadataParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.DeliveryModeService;
import de.hybris.platform.ordersplitting.WarehouseService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;


/**
 * Strategy Class to extend and add functionality for cart metadata update
 */
public class AmwayApacCommerceCartMetadataUpdateStrategy extends DefaultCommerceCartMetadataUpdateStrategy
{

	private DeliveryModeService deliveryModeService;

	private WarehouseService warehouseService;

	/**
	 * This method is overidden to update the additional parameters defined at APAC level in the cart model
	 */
	@Override
	protected boolean doMetadataUpdate(final CommerceCartMetadataParameter parameter)
	{
		boolean shouldSaveCart = super.doMetadataUpdate(parameter);
		final CartModel cart = parameter.getCart();
		if (null != parameter.getDeliveryAddress())
		{
			cart.setDeliveryAddress(parameter.getDeliveryAddress());
			shouldSaveCart = true;
		}
		if (StringUtils.isNotEmpty(parameter.getDeliveryMode()))
		{
			cart.setDeliveryMode(deliveryModeService.getDeliveryModeForCode(parameter.getDeliveryMode()));
			shouldSaveCart = true;
		}
		if (StringUtils.isNotEmpty(parameter.getWarehouseCode()))
		{
			cart.setWarehouse(warehouseService.getWarehouseForCode(parameter.getWarehouseCode()));
			shouldSaveCart = true;
		}
		if (StringUtils.isNotEmpty(parameter.getVolumeAmwayAccount()))
		{
			cart.setVolumeAmwayAccount(parameter.getVolumeAmwayAccount());
			shouldSaveCart = true;
		}
		if (null != parameter.getOrderType())
		{
			cart.setOrderType(parameter.getOrderType());
			shouldSaveCart = true;
		}
		if (null != parameter.getAmwayCartType())
		{
			cart.setType(parameter.getAmwayCartType());
			shouldSaveCart = true;
		}
		return shouldSaveCart;

	}

	/**
	 * @return the deliveryModeService
	 */
	public DeliveryModeService getDeliveryModeService()
	{
		return deliveryModeService;
	}

	/**
	 * @param deliveryModeService
	 *           the deliveryModeService to set
	 */
	@Required
	public void setDeliveryModeService(final DeliveryModeService deliveryModeService)
	{
		this.deliveryModeService = deliveryModeService;
	}

	/**
	 * @return the warehouseService
	 */
	public WarehouseService getWarehouseService()
	{
		return warehouseService;
	}

	/**
	 * @param warehouseService
	 *           the warehouseService to set
	 */
	@Required
	public void setWarehouseService(final WarehouseService warehouseService)
	{
		this.warehouseService = warehouseService;
	}


}
