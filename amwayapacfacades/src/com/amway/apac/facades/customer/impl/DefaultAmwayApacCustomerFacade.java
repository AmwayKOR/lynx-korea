package com.amway.apac.facades.customer.impl;

import de.hybris.platform.commercefacades.user.converters.populator.AddressPopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.service.AmwayApacAccountService;
import com.amway.apac.facades.customer.AmwayApacCustomerFacade;
import com.amway.core.facades.customer.impl.DefaultAmwayCustomerFacade;
import com.amway.core.model.AmwayAccountModel;


/**
 * Overriding {@link DefaultAmwayCustomerFacade} to update for APAC.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCustomerFacade extends DefaultAmwayCustomerFacade implements AmwayApacCustomerFacade
{
	private AmwayApacAccountService amwayApacAccountService;
	private AddressPopulator addressPopulator;

	/**
	 * Overriding OOTB implementation to set the customer account and user price group in session.
	 */
	@Override
	public void loginSuccess()
	{
		super.loginSuccess();
		getAmwayAccountCommerceService().setCurrentAccount(getCurrentUser());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AddressData getRegisteredAddressForCurrentVolumeAbo()
	{
		AddressData addressData = null;
		if (getCartService().hasSessionCart())
		{
			final List<AmwayAccountModel> amwayAccountsFound = getAmwayApacAccountService().getAmwayAccount(
					getCartService().getSessionCart().getVolumeAmwayAccount(),
					getCartService().getSessionCart().getStore().getAffiliateNumber());

			if (CollectionUtils.isNotEmpty(amwayAccountsFound))
			{
				final AmwayAccountModel amwayAccount = amwayAccountsFound.iterator().next();
				if (amwayAccount.getRegisteredAddress() != null)
				{
					addressData = new AddressData();
					getAddressPopulator().populate(amwayAccount.getRegisteredAddress(), addressData);
				}
			}
		}
		return addressData;
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
	 * @return the addressPopulator
	 */
	public AddressPopulator getAddressPopulator()
	{
		return addressPopulator;
	}

	/**
	 * @param addressPopulator
	 *           the addressPopulator to set
	 */
	@Required
	public void setAddressPopulator(final AddressPopulator addressPopulator)
	{
		this.addressPopulator = addressPopulator;
	}
}
