package com.amway.apac.facades.customer;

import de.hybris.platform.commercefacades.user.data.AddressData;

import com.amway.core.facades.customer.AmwayCustomerFacade;


/**
 * Facade layer for customer related operations.
 *
 * @author Shubham Goyal
 */
public interface AmwayApacCustomerFacade extends AmwayCustomerFacade
{

	/**
	 * Returns the registered address of user in session.
	 *
	 * @return addressData Registered Address of user in session
	 */
	AddressData getRegisteredAddressForCurrentVolumeAbo();
}
