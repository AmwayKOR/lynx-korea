/**
 *
 */
package com.amway.core.customer.dao;

import de.hybris.platform.commerceservices.customer.dao.CustomerAccountDao;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;

import com.amway.core.enums.AmwayCartType;

import java.util.Optional;


/**
 * Interface for Amway customer account
 */
public interface AmwayCustomerAccountDao extends CustomerAccountDao
{
	/**
	 * Finds an order by customer,store and type.
	 *
	 * @param paramCustomerModel
	 * @param pageableData
	 * @param paramBaseStoreModel
	 * @param type
	 * @return SearchPageData
	 */
	public SearchPageData<OrderModel> findOrderByCustomerAndStoreAndType(CustomerModel paramCustomerModel,
			PageableData pageableData, BaseStoreModel paramBaseStoreModel, AmwayCartType type);

	/**
	 * Finds an order by code,customer,store and type.
	 *
	 * @param currentUser
	 * @param code
	 * @param store
	 * @param type
	 * @return OrderModel
	 */
	public OrderModel findOrderByCodeAndCustomerAndStoreAndType(CustomerModel currentUser, String code, BaseStoreModel store,
			AmwayCartType type);

	/**
	 * Finds credit card payment info by customer and code.
	 *
	 * @param customerModel
	 * @param code
	 * @return CreditCardPaymentInfoModel
	 */
	public Optional<CreditCardPaymentInfoModel> findCreditCardPaymentInfoByCustomerAndCode(CustomerModel customerModel, String code);

}
