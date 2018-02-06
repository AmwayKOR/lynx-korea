package com.amway.apac.core.user.daos.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amway.apac.core.user.daos.AmwayApacUserDao;
import com.amway.core.user.dao.impl.DefaultAmwayUserDao;


/**
 * Default implementation of {@link AmwayApacUserDao}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacUserDao extends DefaultAmwayUserDao implements AmwayApacUserDao
{
	private static final String USERS_FOR_UID_AND_ABOID_QUERY = new StringBuilder(100).append("SELECT {").append(CustomerModel.PK)
			.append("} FROM {").append(CustomerModel._TYPECODE).append("} WHERE {uid} = ?").append(CustomerModel.UID)
			.append(" OR {customerID} = ?").append(CustomerModel.CUSTOMERID).toString();

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.user.daos.AmwayApacUserDao#getUsersForUidAndAboID(java.lang.String, java.lang.String)
	 */
	@Override
	public List<UserModel> getUsersForUIDandAffiliateCode(final String id, final String affilicateCountryCode)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("id", id);

		final Map<String, Object> params = new HashMap<>();
		params.put(CustomerModel.UID, id);
		//used to check if uid is abo no than creating customer id using affilicateCountryCode and uid
		params.put(CustomerModel.CUSTOMERID, affilicateCountryCode + "-" + id + "-B");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(USERS_FOR_UID_AND_ABOID_QUERY);
		query.addQueryParameters(params);
		final SearchResult<UserModel> result = getFlexibleSearchService().search(query);

		final List resList = new ArrayList<>();
		resList.addAll(result.getResult());
		return resList;
	}

}
