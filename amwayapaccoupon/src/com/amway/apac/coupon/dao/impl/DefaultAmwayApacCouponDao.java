/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2017 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.apac.coupon.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.amway.apac.coupon.dao.AmwayApacCouponDao;
import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.core.model.AmwayAccountModel;


/**
 * Implementation class containing API's related to AmwayCoupons for retrieval/modifications/generation
 *
 * @author kanwarpreetkaur
 */
public class DefaultAmwayApacCouponDao implements AmwayApacCouponDao
{
	private GenericDao<AmwayCouponModel> amwayCouponGenericDao;
	private FlexibleSearchService flexibleSearchService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayCouponModel findAmwayCouponByCode(final String couponCode)
	{
		final Map<String, Object> parameters = new HashMap<>();
		parameters.put("code", couponCode);
		final List<AmwayCouponModel> amwayCouponModels = amwayCouponGenericDao.find(parameters);
		ServicesUtil.validateIfSingleResult(amwayCouponModels, "No coupon with given code [" + couponCode + "] was found",
				"More than one coupon with given code [" + couponCode + "] was found");
		return amwayCouponModels.iterator().next();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayCouponModel> getAmwayCouponsForAbo(final CustomerModel customer, final List<AmwayCouponStatus> couponStatuses,
			final boolean filterByDate)
	{
		ServicesUtil.validateParameterNotNull(customer, "customer cannot be null");
		final AmwayAccountModel amwayAccountModel = customer.getAccounts().iterator().next();
		final Map<String, Object> params = new HashMap<>();

		final StringBuilder selectAmwayCouponQuery = new StringBuilder(100).append("SELECT {").append(Item.PK).append("} ")
				.append("FROM   { ").append(AmwayCouponModel._TYPECODE).append(" as coupon } ");

		buildSelectRestrictions(customer, couponStatuses, filterByDate, amwayAccountModel, params, selectAmwayCouponQuery);
		final SearchResult<AmwayCouponModel> result = getFlexibleSearchService()
				.search(new FlexibleSearchQuery(selectAmwayCouponQuery.toString(), params));
		return result.getResult();
	}

	/**
	 * build query search restrictions based on status/date range/customer and account
	 *
	 * @param customer
	 * @param couponStatuses
	 * @param filterByDate
	 * @param amwayAccountModel
	 * @param params
	 * @param selectAmwayCouponQuery
	 */
	private void buildSelectRestrictions(final CustomerModel customer, final List<AmwayCouponStatus> couponStatuses,
			final boolean filterByDate, final AmwayAccountModel amwayAccountModel, final Map<String, Object> params,
			final StringBuilder selectAmwayCouponQuery)
	{
		final String accountRestriction = " Where ({coupon." + AmwayCouponModel.ACCOUNT + "} = ?amwayAccount OR {coupon."
				+ AmwayCouponModel.CUSTOMER + "}=?customer)";

		selectAmwayCouponQuery.append(accountRestriction);

		params.put("amwayAccount", amwayAccountModel);
		params.put("customer", customer);

		if (CollectionUtils.isNotEmpty(couponStatuses))
		{
			final String statusRestriction = " AND {coupon.status} IN (?statuses)";
			selectAmwayCouponQuery.append(statusRestriction);
			params.put("statuses", couponStatuses);
		}

		if (filterByDate)

		{
			final String dateRestriction = " AND {coupon." + AmwayCouponModel.STARTDATE + " } <= CURDATE() AND { coupon. "
					+ AmwayCouponModel.ENDDATE + "} > CURDATE()";
			selectAmwayCouponQuery.append(dateRestriction);
		}
	}


	/**
	 * getter for AmwayCouponGenericDao
	 *
	 * @return GenericDao
	 */

	public GenericDao<AmwayCouponModel> getAmwayCouponGenericDao()
	{
		return amwayCouponGenericDao;
	}

	/**
	 * setter for AmwayCouponGenericDao
	 *
	 * @param amwayCouponGenericDao
	 */
	public void setAmwayCouponGenericDao(final GenericDao<AmwayCouponModel> amwayCouponGenericDao)
	{
		this.amwayCouponGenericDao = amwayCouponGenericDao;
	}

	/**
	 * getter for flexibleSearchService
	 *
	 * @return flexibleSearchService
	 */
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * setter for flexibleSearchService
	 *
	 * @param flexibleSearchService
	 */
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}




}
