package com.amway.apac.core.customer.daos;

import de.hybris.platform.core.model.user.UserModel;

import com.amway.core.customer.dao.AmwayCustomerAccountDao;


/**
 * Extending {@link AmwayCustomerAccountDao} to add more order functionalities
 *
 * @author Aaron Yong
 *
 */
public interface AmwayApacCustomerAccountDao extends AmwayCustomerAccountDao
{
	Integer findOrderCountsForUser(final UserModel user);

}
