package com.amway.apac.core.user.services;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.user.UserService;


/**
 * User service interface
 *
 * @author Ashish Sabal
 *
 */
public interface AmwayApacUserService extends UserService
{

	/**
	 * @param id
	 *           UID of the customer
	 * @return matching user
	 * @throws UnknownIdentifierException
	 *            if no matching users found
	 * @throws AmbiguousIdentifierException
	 *            if more than one matching users found
	 */
	UserModel getUserForUIDForCurrentAffiliate(String id);
}
