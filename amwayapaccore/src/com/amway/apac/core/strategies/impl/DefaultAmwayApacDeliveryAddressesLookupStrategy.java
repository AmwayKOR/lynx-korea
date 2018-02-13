/**
 *
 */
package com.amway.apac.core.strategies.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.strategies.impl.DefaultDeliveryAddressesLookupStrategy;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.services.AmwayApacAccountService;
import com.amway.apac.core.strategies.AmwayApacDeliveryAddressesLookupStrategy;
import com.amway.core.model.AmwayAccountModel;


/**
 *
 */
public class DefaultAmwayApacDeliveryAddressesLookupStrategy extends DefaultDeliveryAddressesLookupStrategy
		implements AmwayApacDeliveryAddressesLookupStrategy
{

	private AmwayApacAccountService amwayApacAccountService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AddressModel getDeliveryAddressForCustomer(final CustomerModel customer)
	{
		validateParameterNotNull(customer, "Customer must not be null");
		AddressModel address = null;
		final AmwayAccountModel amwayAccount = amwayApacAccountService.getAmwayAccount(customer);
		if (null != amwayAccount)
		{
			address = amwayAccount.getRegisteredAddress();
		}
		return address;
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




}
