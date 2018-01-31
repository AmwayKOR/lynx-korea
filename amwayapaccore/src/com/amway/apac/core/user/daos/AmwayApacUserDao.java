package com.amway.apac.core.user.daos;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.daos.UserDao;

import java.util.List;


/**
 * User DAO Interface
 *
 * @author Ashish Sabal
 *
 */
public interface AmwayApacUserDao extends UserDao
{

	/**
	 * Gets the users for uid and abo ID.
	 *
	 * @param id
	 *           the id
	 * @param affiliacteNo
	 *           the affiliate no
	 * @return the users for uid and abo ID
	 */
	List<UserModel> getUsersForUIDandAffiliateCode(String id, String affiliacteNo);

}
