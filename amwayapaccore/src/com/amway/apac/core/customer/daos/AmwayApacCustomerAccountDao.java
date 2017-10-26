/**
 *
 */
package com.amway.apac.core.customer.daos;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.List;

import com.amway.core.customer.dao.AmwayCustomerAccountDao;


/**
 * @author Aaron Yong
 *
 */
public interface AmwayApacCustomerAccountDao extends AmwayCustomerAccountDao
{
	List<CustomerModel> findOrderCountsForUser(final UserModel user);

}
