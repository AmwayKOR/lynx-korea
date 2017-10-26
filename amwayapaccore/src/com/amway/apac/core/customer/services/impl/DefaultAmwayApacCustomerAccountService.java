/**
 *
 */
package com.amway.apac.core.customer.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.core.customer.daos.AmwayApacCustomerAccountDao;
import com.amway.apac.core.customer.services.AmwayApacCustomerAccountService;
import com.amway.core.customer.service.impl.DefaultAmwayCustomerAccountService;


/**
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacCustomerAccountService extends DefaultAmwayCustomerAccountService
		implements AmwayApacCustomerAccountService
{
	private AmwayApacCustomerAccountDao amwayApacCustomerAccountDao;


	/**
	 * @return the amwayApacCustomerAccountDao
	 */
	public AmwayApacCustomerAccountDao getAmwayApacCustomerAccountDao()
	{
		return amwayApacCustomerAccountDao;
	}

	/**
	 * @param amwayApacCustomerAccountDao
	 *           the amwayApacCustomerAccountDao to set
	 */
	public void setAmwayApacCustomerAccountDao(final AmwayApacCustomerAccountDao amwayApacCustomerAccountDao)
	{
		this.amwayApacCustomerAccountDao = amwayApacCustomerAccountDao;
	}

	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacCustomerAccountService.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.core.customer.services.AmwayApacCustomerAccountService#getOrdersCount()
	 */
	@Override
	public CustomerModel getOrdersCount()
	{
		// YTODO Auto-generated method stub

		final UserModel currentUser = getUserService().getCurrentUser();
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Fetching order counts for user [").append(currentUser.getUid())
					.toString());
		}

		final List<CustomerModel> results = getAmwayApacCustomerAccountDao().findOrderCountsForUser(currentUser);

		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Found ").append(CollectionUtils.size(results))
					.append(" order counts for user [").append(currentUser.getUid()).toString());
		}

		return results.iterator().next();
	}

}
