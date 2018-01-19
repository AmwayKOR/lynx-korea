package com.amway.apac.core.user.services;

import de.hybris.platform.core.model.user.UserModel;
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
	 * @return
	 */
	UserModel getUserForUIDAndAmwayAccount(String id);
}
