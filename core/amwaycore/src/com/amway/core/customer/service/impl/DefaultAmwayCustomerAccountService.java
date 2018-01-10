/**
 *
 */
package com.amway.core.customer.service.impl;

import com.amway.core.service.AmwayAccountCommerceService;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.CreditCardPaymentInfoModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;

import com.amway.core.customer.dao.AmwayCustomerAccountDao;
import com.amway.core.customer.service.AmwayCustomerAccountService;
import com.amway.core.enums.AmwayCartType;
import com.amway.core.los.data.LosAccountDetailResponseData;

import java.util.Optional;

/**
 * Implementation for to get the ordes of amway customer account.
 */
public class DefaultAmwayCustomerAccountService extends DefaultCustomerAccountService implements AmwayCustomerAccountService
{
	private AmwayCustomerAccountDao amwayCustomerAccountDao;
	private AmwayAccountCommerceService amwayAccountCommerceService;

	/**
	 * {@link #getOrders(de.hybris.platform.core.model.user.CustomerModel, de.hybris.platform.store.BaseStoreModel, de.hybris.platform.commerceservices.search.pagedata.PageableData, com.amway.core.enums.AmwayCartType)}
	 */
	@Override
	public SearchPageData<OrderModel> getOrders(final CustomerModel customerModel, final BaseStoreModel store,
			final PageableData pageableData, final AmwayCartType type)
	{
		ServicesUtil.validateParameterNotNull(customerModel, "Customer model cannot be null");
		ServicesUtil.validateParameterNotNull(store, "Store must not be null");
		ServicesUtil.validateParameterNotNull(pageableData, "PageableData must not be null");
		return amwayCustomerAccountDao.findOrderByCustomerAndStoreAndType(customerModel, pageableData, store, type);
	}

	/**
	 * {@link #getOrderForCodeAndType(de.hybris.platform.core.model.user.CustomerModel, java.lang.String, de.hybris.platform.store.BaseStoreModel, com.amway.core.enums.AmwayCartType)}
	 */
	@Override
	public OrderModel getOrderForCodeAndType(final CustomerModel currentUser, final String code, final BaseStoreModel store,
			final AmwayCartType type)
	{
		ServicesUtil.validateParameterNotNull(currentUser, "Customer model cannot be null");
		ServicesUtil.validateParameterNotNull(store, "Store must not be null");
		ServicesUtil.validateParameterNotNull(code, "Order code must not be null");
		return amwayCustomerAccountDao.findOrderByCodeAndCustomerAndStoreAndType(currentUser, code, store, type);
	}

	@Override
	public Optional<CreditCardPaymentInfoModel> findCreditCardPaymentInfoByCustomerAndCode(CustomerModel user, String paymentCode)
	{
		return amwayCustomerAccountDao.findCreditCardPaymentInfoByCustomerAndCode(user, paymentCode);
	}

	/**
	 * @return amwayCustomerAccountDao
	 */
	public AmwayCustomerAccountDao getAmwayCustomerAccountDao()
	{
		return amwayCustomerAccountDao;
	}

	/**
	 * @param amwayCustomerAccountDao the amwayCustomerAccountDao to set
	 */
	public void setAmwayCustomerAccountDao(final AmwayCustomerAccountDao amwayCustomerAccountDao)
	{
		this.amwayCustomerAccountDao = amwayCustomerAccountDao;
	}


	public LosAccountDetailResponseData getBusinessInfo()
	{
		return getAmwayAccountCommerceService().getBusinessInfo();
	}

	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

}
