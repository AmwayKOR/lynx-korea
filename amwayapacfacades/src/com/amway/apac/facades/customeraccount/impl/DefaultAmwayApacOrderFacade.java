package com.amway.apac.facades.customeraccount.impl;

import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.store.BaseStoreModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.customer.services.AmwayApacCustomerAccountService;
import com.amway.apac.facades.constants.AmwayapacFacadesConstants;
import com.amway.apac.facades.customeraccount.AmwayApacOrderFacade;
import com.amway.core.facades.order.impl.DefaultAmwayOrderFacade;

import reactor.util.StringUtils;


/**
 * Default implementation of {@link AmwayApacOrderFacade}
 *
 * @author Aaron Yong
 *
 */
public class DefaultAmwayApacOrderFacade extends DefaultAmwayOrderFacade implements AmwayApacOrderFacade
{

	private AmwayApacCustomerAccountService amwayApacCustomerAccountService;
	private List<String> orderHistoryTypeOptions;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getCustomerOrderCounts()
	{
		return getAmwayApacCustomerAccountService().getOrdersCount();

	}

	@Override
	public List<String> getOrderHistoryDateOptions()
	{
		final List<String> orderDateOptions = new ArrayList<>(15);
		LocalDate currentDate = LocalDate.now();
		for (int i = 0; i < 15; i++)
		{
			currentDate = currentDate.minusMonths(1);
			orderDateOptions.add(DateTimeFormatter.ofPattern("yyyy-MM").format(currentDate));
		}
		return orderDateOptions;
	}

	@Override
	public SearchPageData<OrderHistoryData> getPagedOrderHistoryByFilter(final PageableData pageableData, final String date,
			final String type)
	{
		final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
		final BaseStoreModel currentBaseStore = getBaseStoreService().getCurrentBaseStore();
		final LocalDate dateTo = getEndDate(date);
		final LocalDate dateFrom = getStartDate(date);


		final SearchPageData<OrderModel> orderResults = getAmwayApacCustomerAccountService().getOrderListByFilter(currentCustomer,
				currentBaseStore, dateFrom, dateTo, type, pageableData);

		return convertPageData(orderResults, getOrderHistoryConverter());
	}

	private LocalDate getStartDate(final String date)
	{
		if (StringUtils.isEmpty(date) || AmwayapacFacadesConstants.LAST_THIRTY_DAYS.equals(date))
		{
			return LocalDate.now().minusDays(30);
		}
		else
		{
			return LocalDate.parse(date + AmwayapacFacadesConstants.FIRST_DAY_OF_MONTH,
					DateTimeFormatter.ofPattern(AmwayapacFacadesConstants.ORDER_DATE_FORMAT_PATTERN));
		}
	}

	private LocalDate getEndDate(final String date)
	{
		if (StringUtils.isEmpty(date) || AmwayapacFacadesConstants.LAST_THIRTY_DAYS.equals(date))
		{
			return LocalDate.now();
		}
		else
		{
			return LocalDate.parse(date + AmwayapacFacadesConstants.LAST_DAY_OF_MONTH,
					DateTimeFormatter.ofPattern(AmwayapacFacadesConstants.ORDER_DATE_FORMAT_PATTERN));
		}
	}

	/**
	 * @return the amwayApacCustomerAccountService
	 */
	public AmwayApacCustomerAccountService getAmwayApacCustomerAccountService()
	{
		return amwayApacCustomerAccountService;
	}

	/**
	 * @param amwayApacCustomerAccountService
	 *           the amwayApacCustomerAccountService to set
	 */
	@Required
	public void setAmwayApacCustomerAccountService(final AmwayApacCustomerAccountService amwayApacCustomerAccountService)
	{
		this.amwayApacCustomerAccountService = amwayApacCustomerAccountService;
	}

	/**
	 * @return the orderHistoryTypeOptions
	 */
	@Override
	public List<String> getOrderHistoryTypeOptions()
	{
		return orderHistoryTypeOptions;
	}

	/**
	 * @param orderHistoryTypeOptions
	 *           the orderHistoryTypeOptions to set
	 */
	@Required
	public void setOrderHistoryTypeOptions(final List<String> orderHistoryTypeOptions)
	{
		this.orderHistoryTypeOptions = orderHistoryTypeOptions;
	}
}
