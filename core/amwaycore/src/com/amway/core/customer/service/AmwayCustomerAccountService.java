/**
 *
 */
package com.amway.core.customer.service;

import com.amway.core.los.data.LosAccountDetailResponseData;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;
import com.amway.core.los.data.LosAccountResponseData;
import com.amway.core.enums.AmwayCartType;


/**
 * Interface for Amway customer account.
 */
public interface AmwayCustomerAccountService extends CustomerAccountService
{
	/**
	 * To get the orders.
	 *
	 * @param customerModel
	 * @param store
	 * @param pageableData
	 * @param type
	 * @return SearchPageData
	 */
	public SearchPageData<OrderModel> getOrders(CustomerModel customerModel, BaseStoreModel store, PageableData pageableData,
			AmwayCartType type);

	/**
	 * To get the order for code and type.
	 *
	 * @param currentUser
	 * @param code
	 * @param store
	 * @param type
	 * @return OrderModel
	 */
	public OrderModel getOrderForCodeAndType(CustomerModel currentUser, String code, BaseStoreModel store, AmwayCartType type);

	public LosAccountDetailResponseData getBusinessInfo();

}
