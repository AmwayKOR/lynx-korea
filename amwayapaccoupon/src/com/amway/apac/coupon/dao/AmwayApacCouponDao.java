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
package com.amway.apac.coupon.dao;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;


/**
 * Interface containing API's related to AmwayCoupons for retrieval/modifications/generation
 *
 * @author kanwarpreetkaur
 */
public interface AmwayApacCouponDao
{

	/**
	 * get Amway Coupon on the basis of unique coupon code
	 *
	 * @param couponCode
	 */
	AmwayCouponModel findAmwayCouponByCode(final String couponCode);

	/**
	 * get Amway coupons for customer, particular status and withing date range
	 *
	 * @param customer
	 * @param couponStatuses
	 * @param filterByDate
	 * @return AmwayCouponModel
	 */
	List<AmwayCouponModel> getAmwayCouponsForAbo(CustomerModel customer, List<AmwayCouponStatus> couponStatuses,
			boolean filterByDate);


}
