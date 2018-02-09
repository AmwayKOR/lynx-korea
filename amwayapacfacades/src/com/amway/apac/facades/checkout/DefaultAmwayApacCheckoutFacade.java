package com.amway.apac.facades.checkout;

import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commerceservices.service.data.CommerceCheckoutParameter;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.site.BaseSiteService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.services.AmwayApacAccountService;
import com.amway.apac.core.checkout.services.AmwayApacCommerceCheckoutService;
import com.amway.apac.facades.customer.AmwayApacCustomerFacade;
import com.amway.apac.serviceability.services.AmwayApacWarehouseServiceabilityService;
import com.amway.core.model.AmwayAccountModel;
import com.amway.facades.checkout.impl.DefaultAmwayCheckoutFacade;


/**
 * Overriding {@link DefaultAmwayCheckoutFacade} implementation for APAC specific requirements.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCheckoutFacade extends DefaultAmwayCheckoutFacade
{
	private AmwayApacWarehouseServiceabilityService amwayApacWarehouseServiceabilityService;
	private BaseSiteService baseSiteService;
	private AmwayApacCustomerFacade amwayApacCustomerFacade;
	private AmwayApacAccountService amwayApacAccountService;
	private AmwayApacCommerceCheckoutService amwayApacCommerceCheckoutService;

	/**
	 * {@inheritDoc} Overriding OOTB implementation to set the warehouse in cart when address is changed.
	 */
	@Override
	public boolean setDeliveryAddress(final AddressData addressData)
	{
		boolean deliveryAddressChanged = false;
		final CartModel cartModel = getCart();

		if (cartModel != null)
		{
			String postalCode = StringUtils.EMPTY;
			AddressModel addressModel = null;
			if (addressData != null)
			{
				if (isRegisteredAddress(addressData))
				{
					final AmwayAccountModel amwayAccount = getAmwayApacAccountService().getAmwayAccount(
							getCartService().getSessionCart().getVolumeAmwayAccount(), cartModel.getStore().getAffiliateNumber());
					addressModel = amwayAccount.getRegisteredAddress();
				}
				else
				{
					addressModel = createDeliveryAddressModel(addressData, cartModel);
				}
				postalCode = addressData.getPostalCode();
			}
			setDeliveryAddress(cartModel, addressModel, false);
			cartModel.setCalculated(Boolean.FALSE);
			setCartWarehouse(cartModel, postalCode);
			deliveryAddressChanged = true;
		}
		return deliveryAddressChanged;
	}

	/**
	 * Updates the delivery address in cart
	 *
	 * @param cartModel
	 *           CartModel in which address need to be set.
	 * @param addressModel
	 *           Address that need to be set
	 * @param setAddressAsDefaultShippingAddress
	 * @return true is the action is successful.
	 */
	protected boolean setDeliveryAddress(final CartModel cartModel, final AddressModel addressModel,
			final boolean setAddressAsDefaultShippingAddress)
	{
		if ((null != addressModel) && (getModelService().isNew(addressModel)))
		{
			addressModel.setOwner(cartModel);
			getModelService().save(cartModel);
		}
		final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cartModel);
		parameter.setAddress(addressModel);
		parameter.setIsDeliveryAddress(setAddressAsDefaultShippingAddress);
		return getCommerceCheckoutService().setDeliveryAddress(parameter);

	}

	/**
	 * Returns true if passed address is registered address for user in session.
	 *
	 * @param cartCheckoutDeliveryAddress
	 * @return true is the passed address is the registered address for current user.
	 */
	protected boolean isRegisteredAddress(final AddressData cartCheckoutDeliveryAddress)
	{
		final AddressData registeredAddress = getAmwayApacCustomerFacade().getRegisteredAddressForCurrentVolumeAbo();
		return (null != cartCheckoutDeliveryAddress) && (null != registeredAddress)
				&& (registeredAddress.getId().equals(cartCheckoutDeliveryAddress.getId()));
	}

	/**
	 * Updates the warehouse evaluated on the basis of postalCode given for cart.
	 *
	 * @param cartModel
	 *           CartModel in which warehouse need to be set
	 * @param postalCode
	 *           Postal Code against which warehouse need to be searched
	 * @return true if the action is successful
	 */
	protected boolean setCartWarehouse(final CartModel cartModel, final String postalCode)
	{
		final CommerceCheckoutParameter parameter = new CommerceCheckoutParameter();
		parameter.setEnableHooks(true);
		parameter.setCart(cartModel);
		parameter.setWarehouse(getAmwayApacWarehouseServiceabilityService().getServiceableWareHouse(postalCode,
				getBaseSiteService().getCurrentBaseSite()));
		return getAmwayApacCommerceCheckoutService().setWarehouse(parameter);

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
	 * @return the baseSiteService
	 */
	@Override
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	@Override
	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the amwayApacCustomerFacade
	 */
	public AmwayApacCustomerFacade getAmwayApacCustomerFacade()
	{
		return amwayApacCustomerFacade;
	}

	/**
	 * @param amwayApacCustomerFacade
	 *           the amwayApacCustomerFacade to set
	 */
	@Required
	public void setAmwayApacCustomerFacade(final AmwayApacCustomerFacade amwayApacCustomerFacade)
	{
		this.amwayApacCustomerFacade = amwayApacCustomerFacade;
	}

	/**
	 * @return the amwayApacAccountService
	 */
	public AmwayApacAccountService getAmwayApacAccountService()
	{
		return amwayApacAccountService;
	}

	/**
	 * @param amwayApacAccountService
	 *           the amwayApacAccountService to set
	 */
	@Required
	public void setAmwayApacAccountService(final AmwayApacAccountService amwayApacAccountService)
	{
		this.amwayApacAccountService = amwayApacAccountService;
	}

	/**
	 * @return the amwayApacCommerceCheckoutService
	 */
	public AmwayApacCommerceCheckoutService getAmwayApacCommerceCheckoutService()
	{
		return amwayApacCommerceCheckoutService;
	}

	/**
	 * @param amwayApacCommerceCheckoutService
	 *           the amwayApacCommerceCheckoutService to set
	 */
	public void setAmwayApacCommerceCheckoutService(final AmwayApacCommerceCheckoutService amwayApacCommerceCheckoutService)
	{
		this.amwayApacCommerceCheckoutService = amwayApacCommerceCheckoutService;
	}


}