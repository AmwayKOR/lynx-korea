package com.amway.core.renewal.dao.impl;

import com.amway.core.annotations.AmwayBean;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.renewal.dao.AmwayRenewalDao;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AmwayBean(ext="amwaycore",docs={"https://jira.amway.com:8444/display/HC/Auto-Renewal+Order",
		                           "https://jira.amway.com:8444/display/HC/Auto-Renewal+OCC+Endpoint"})
public class DefaultAmwayRenewalDao implements AmwayRenewalDao
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayRenewalDao.class);
	private static final String USER = "user";
	private static final String RENEWAL = "renewal";
	private static final String REGISTRATION = "registration";

	private static final String FIND_LAST_RENEWAL_ORDER = "SELECT {" + OrderModel.PK + "} FROM {" + OrderModel._TYPECODE
			+ " As o JOIN " + UserModel._TYPECODE + " AS u ON {u:pk}={o:" + OrderModel.USER + "}} WHERE ({o:"
			+ OrderModel.TYPE + "} =?" + RENEWAL + " OR " + "{o:"
			+ OrderModel.TYPE + "} =?" + REGISTRATION + ") AND {u:" + UserModel.UID + "}=?user ORDER BY {o:"
			+ OrderModel.DATE
			+ "} DESC LIMIT 1";

	private FlexibleSearchService flexibleSearchService;

	@Override
	public Optional<AbstractOrderModel> getLastRenewalOrder(UserModel user)
	{
		final Map<String, Object> params = new HashMap<>();
		params.put(USER, user.getUid());
		params.put(RENEWAL, AmwayCartType.RENEWAL);
		params.put(REGISTRATION, AmwayCartType.REGISTRATION);
		final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(FIND_LAST_RENEWAL_ORDER, params);
		List<Object> renewals = flexibleSearchService.search(searchQuery).getResult();
		AbstractOrderModel result = null;
		if (CollectionUtils.isNotEmpty(renewals))
		{
			result = (AbstractOrderModel) renewals.get(0);
		}
		else
		{
			LOGGER.warn("No renewal orders for user");
		}
		return Optional.ofNullable(result);
	}

	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}
}
