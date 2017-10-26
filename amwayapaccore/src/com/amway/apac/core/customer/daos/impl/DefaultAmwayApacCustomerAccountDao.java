/**
 *
 */
package com.amway.apac.core.customer.daos.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import com.amway.apac.core.customer.daos.AmwayApacCustomerAccountDao;
import com.amway.core.customer.dao.impl.DefaultAmwayCustomerAccountDao;


/**
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacCustomerAccountDao extends DefaultAmwayCustomerAccountDao implements AmwayApacCustomerAccountDao
{


	/**
	 * Query to search for order count based on user.
	 */
	private static final String FIND_ORDERCOUNT_FOR_CUSTOMER = new StringBuilder(TWO_HUNDRED_INT).append("SELECT {w.")
			.append(CustomerModel.PK).append("} FROM {").append(CustomerModel._TYPECODE).append("} WHERE {u.")
			.append(CustomerModel.PK).append("} = ?").append(CustomerModel.CUSTOMERID).toString();

	@Override
	public List<CustomerModel> findOrderCountsForUser(final UserModel user)
	{
		validateParameterNotNull(user, "User must not be null!");

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_ORDERCOUNT_FOR_CUSTOMER);
		fQuery.addQueryParameter(CustomerModel.CUSTOMERID, user.getUid());

		final SearchResult<CustomerModel> result = search(fQuery);
		return result.getResult();
	}

}
