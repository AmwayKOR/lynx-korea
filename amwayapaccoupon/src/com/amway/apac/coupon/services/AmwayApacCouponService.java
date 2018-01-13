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
package com.amway.apac.coupon.services;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.couponservices.services.CouponService;

import java.util.List;

import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.apac.coupon.service.data.AmwayCouponCreationParameter;


/**
 * Interface containing API's related to AmwayCoupons for retrieval/modifications/generation
 *
 * @author kanwarpreetkaur
 */
public interface AmwayApacCouponService extends CouponService
{
	/**
	 * Creates AmwayCoupon.
	 */
	List<AmwayCouponModel> createAmwayCoupon(final AmwayCouponCreationParameter... couponCreationParam);

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
	 * get Amway coupons for customer, particular status and within date range
	 *
	 * @param customer
	 * @param couponStatuses
	 * @param showActive
	 * @return AmwayCouponModel
	 */
	List<AmwayCouponModel> getAmwayCouponsForAbo(CustomerModel customer, List<AmwayCouponStatus> couponStatuses,
			boolean showActive);

	/**
	 * Returns list of expired coupons.
	 *
	 * @return
	 */
	List<AmwayCouponModel> getExpiredAmwayCoupons();
}
