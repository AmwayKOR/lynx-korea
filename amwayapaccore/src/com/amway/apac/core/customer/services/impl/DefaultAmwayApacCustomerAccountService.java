package com.amway.apac.core.customer.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.customer.daos.AmwayApacCustomerAccountDao;
import com.amway.apac.core.customer.services.AmwayApacCustomerAccountService;
import com.amway.core.customer.service.impl.DefaultAmwayCustomerAccountService;


/**
 * Default implementation of {@link AmwayApacCustomerAccountService}
 *
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacCustomerAccountService extends DefaultAmwayCustomerAccountService
		implements AmwayApacCustomerAccountService
{
	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacCustomerAccountService.class);

	private AmwayApacCustomerAccountDao amwayApacCustomerAccountDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getOrdersCount()
	{
		final UserModel currentUser = getUserService().getCurrentUser();
		final BaseStoreModel baseStore = getBaseStoreService().getCurrentBaseStore();
		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Fetching number of orders for user [")
					.append(currentUser.getUid()).append("]").toString());
		}

		final Integer result = getAmwayApacCustomerAccountDao().findOrderCountsForUser(currentUser, baseStore);

		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(TWO_HUNDRED_INT).append("Found ").append(result).append(" orders for user [")
					.append(currentUser.getUid()).append("]").toString());
		}

		return result;
	}

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
	@Required
	public void setAmwayApacCustomerAccountDao(final AmwayApacCustomerAccountDao amwayApacCustomerAccountDao)
	{
		this.amwayApacCustomerAccountDao = amwayApacCustomerAccountDao;
	}

}
