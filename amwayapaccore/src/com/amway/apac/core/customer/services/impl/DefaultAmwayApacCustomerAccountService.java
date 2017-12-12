package com.amway.apac.core.customer.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.TWO_HUNDRED_INT;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.store.BaseStoreModel;

import java.time.LocalDate;

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
	 * {@inheritDoc}
	 */
	@Override
	public SearchPageData<OrderModel> getOrderListByFilter(final CustomerModel customerModel, final BaseStoreModel store,
			final LocalDate datefrom, final LocalDate dateto, final String type, final PageableData pageableData)
	{
		validateParameterNotNull(customerModel, "Customer model cannot be null");
		validateParameterNotNull(store, "Store must not be null");
		validateParameterNotNull(pageableData, "PageableData must not be null");
		return getAmwayApacCustomerAccountDao().findOrdersByCustomerAndStoreAndFilterByDateAndType(customerModel, store, datefrom,
				dateto, type, pageableData);
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
