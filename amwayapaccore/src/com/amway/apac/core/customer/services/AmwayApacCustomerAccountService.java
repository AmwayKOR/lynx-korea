package com.amway.apac.core.customer.services;

import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;

import java.time.LocalDate;

import com.amway.core.customer.service.AmwayCustomerAccountService;


/**
 * Extending {@link AmwayCustomerAccountService} to add more order functionalities
 *
 * @author Aaron Yong
 *
 */
public interface AmwayApacCustomerAccountService extends AmwayCustomerAccountService
{
	/**
	 * Fetches the number of orders for the current user in current base store.
	 *
	 * @return number of orders found.
	 */
	Integer getOrdersCount();

	/**
	 * Returns orders list for given customer, basestore, date limits and order type.
	 *
	 * @param customerModel
	 *           Customer for whom, orders need to be searched.
	 * @param store
	 *           Base Store
	 * @param datefrom
	 *           From Date
	 * @param dateto
	 *           To Date
	 * @param type
	 *           Order Type
	 * @param pageableData
	 * @return OrderModels as per given restrictions.
	 * @throws IllegalArgumentException
	 *            if customerModel, store or pageableData is null.
	 */
	SearchPageData<OrderModel> getOrderListByFilter(final CustomerModel customerModel, final BaseStoreModel store,
			final LocalDate datefrom, final LocalDate dateto, final String type, final PageableData pageableData);
}
