/**
 *
 */
package com.amway.apac.core.strategies;

import de.hybris.platform.commerceservices.strategies.DeliveryAddressesLookupStrategy;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author navishsharma <br>
 *         Interface to define new methods to the {@link DeliveryAddressesLookupStrategy }
 */
public interface AmwayApacDeliveryAddressesLookupStrategy extends DeliveryAddressesLookupStrategy
{

	/**
	 * Gets the list of delivery addresses for a customer
	 *
	 * @param customer
	 *           the customer for which delivery address is being evaluated
	 * @return delivery address for the customer.
	 */
	AddressModel getDeliveryAddressForCustomer(CustomerModel customer);
}
