/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.amway.apac.coupon.service;

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.couponservices.model.AbstractCouponModel;
import de.hybris.platform.couponservices.services.CouponService;

import java.util.Date;
import java.util.List;

import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.core.model.AmwayAccountModel;


/**
 * Interface containing API's related to AmwayCoupons for retrieval/modifications/generation
 *
 * @author kanwarpreetkaur
 */
public interface AmwayApacCouponService extends CouponService
{
	/**
	 * Creates AmwayCoupon.
	 *
	 * @param couponModel
	 * @param startDate
	 * @param endDate
	 * @param site
	 * @param customer
	 * @param account
	 *
	 * @return AmwayCouponModel
	 */
	AmwayCouponModel createAmwayCoupon(final AbstractCouponModel couponModel, final Date startDate, final Date endDate,
			final CMSSiteModel site, final CustomerModel customer, final AmwayAccountModel account);

	/**
	 * generates unique coupon code for AmwayCoupon
	 *
	 * @param amwayCoupon
	 * @return generated coupon code
	 */
	String generateCodeForAmwayCoupon(AmwayCouponModel amwayCoupon);

	/**
	 * get Amway Coupon on the basis of unique coupon code
	 *
	 * @param couponCode
	 */
	AmwayCouponModel getAmwayCouponForCode(String couponCode);

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
