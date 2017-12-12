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
	 * Featches the number of orders for the current user in current base store.
	 *
	 * @return number of orders found.
	 */
	Integer getOrdersCount();

	SearchPageData<OrderModel> getOrderListByFilter(final CustomerModel customerModel, final BaseStoreModel store,
			final LocalDate datefrom, final LocalDate dateto, final String type, final PageableData pageableData);
}
