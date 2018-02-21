package com.amway.apac.facades.customer.impl;

import de.hybris.platform.commercefacades.user.converters.populator.AddressPopulator;
import de.hybris.platform.commercefacades.user.data.AddressData;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.services.AmwayApacAccountService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.facades.customer.AmwayApacCustomerFacade;
import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.facades.customer.impl.DefaultAmwayCustomerFacade;
import com.amway.core.model.AmwayAccountModel;


/**
 * Overriding {@link DefaultAmwayCustomerFacade} to update for APAC and default implementation for
 * {@link AmwayApacCustomerFacade}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacCustomerFacade extends DefaultAmwayCustomerFacade implements AmwayApacCustomerFacade
{
	private AmwayApacAccountService amwayApacAccountService;
	private AddressPopulator addressPopulator;

	/**
	 * Overriding OOTB implementation to set the customer account , user price group and user classification level in
	 * session.
	 */
	@Override
	public void loginSuccess()
	{
		getCartService().getSessionCart();
		super.loginSuccess();
		getAmwayAccountCommerceService().setCurrentAccount(getCurrentUser());
		final AmwayAccountModel currentAccount = getSessionService().getAttribute(AmwaycoreConstants.SessionVariables.ACCOUNT);
		getSessionService().setAttribute(AmwayapacCoreConstants.ACCOUNT_CLASSIFICATION_CODE,
				getAmwayApacAccountService().getClassificationForAccount(currentAccount).toString());
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
			final AmwayAccountModel amwayAccount = getAmwayApacAccountService().getAmwayAccount(
					getCartService().getSessionCart().getVolumeAmwayAccount(),
					getCartService().getSessionCart().getStore().getAffiliateNumber());
			if (amwayAccount.getRegisteredAddress() != null)
			{
				addressData = new AddressData();
				getAddressPopulator().populate(amwayAccount.getRegisteredAddress(), addressData);
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
