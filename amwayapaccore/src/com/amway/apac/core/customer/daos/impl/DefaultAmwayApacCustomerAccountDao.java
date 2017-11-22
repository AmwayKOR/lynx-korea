package com.amway.apac.core.customer.daos.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;

import com.amway.apac.core.customer.daos.AmwayApacCustomerAccountDao;
import com.amway.core.customer.dao.impl.DefaultAmwayCustomerAccountDao;


/**
 * Default implementation of {@link AmwayApacCustomerAccountDao}
 *
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacCustomerAccountDao extends DefaultAmwayCustomerAccountDao implements AmwayApacCustomerAccountDao
{
	/**
	 * Query to search for order count based on user.
	 */
	private static final String FIND_ORDERCOUNT_FOR_CUSTOMER = new StringBuilder(TWO_HUNDRED_INT).append("SELECT COUNT({o.")
			.append(OrderModel.PK).append("}) FROM {").append(OrderModel._TYPECODE).append(" as o} WHERE {o.")
			.append(OrderModel.USER).append("} = ?").append(OrderModel.USER).toString();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer findOrderCountsForUser(final UserModel user)
	{
		validateParameterNotNull(user, "User must not be null!");

		final FlexibleSearchQuery fQuery = new FlexibleSearchQuery(FIND_ORDERCOUNT_FOR_CUSTOMER);
		fQuery.addQueryParameter(OrderModel.USER, user);

		fQuery.setResultClassList(Collections.singletonList(Integer.class));

		final SearchResult<Integer> searchResult = search(fQuery);

		return searchResult.getResult().get(0);

	}

}
