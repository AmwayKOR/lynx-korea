package com.amway.apac.core.commerceservices.order.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.serviceability.services.AmwayApacWarehouseServiceabilityService;
import com.amway.core.commerceservices.order.impl.AmwayCommerceCartFactory;
import com.amway.core.model.AmwayAccountModel;


/**
 * To set the {@link WarehouseModel} and {@link AddressModel} to the {@link CartModel}
 */
public class AmwayApacCommerceCartFactory extends AmwayCommerceCartFactory
{
	private AmwayApacWarehouseServiceabilityService amwayApacWarehouseServiceabilityService;

	@Override
	protected CartModel createCartInternal()
	{
		final CartModel cart = super.createCartInternal();
		final AmwayAccountModel currentAccount = getAmwayAccountCommerceService().getCurrentAccount();
		if (null != currentAccount)
		{
			final AddressModel registeredAddress = currentAccount.getRegisteredAddress();
			final BaseSiteModel currentBaseSite = getBaseSiteService().getCurrentBaseSite();
			cart.setDeliveryAddress(registeredAddress);
			if ((null != registeredAddress)
					&& (StringUtils.isNotBlank(registeredAddress.getPostalcode()) && (null != currentBaseSite)))
			{
				cart.setWarehouse(amwayApacWarehouseServiceabilityService.getServiceableWareHouse(registeredAddress.getPostalcode(),
						currentBaseSite));
			}
			cart.setVolumeAmwayAccount(currentAccount.getCode());
		}
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
}
