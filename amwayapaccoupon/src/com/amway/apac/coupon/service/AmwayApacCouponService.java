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
import de.hybris.platform.couponservices.services.CouponService;

import java.util.Date;

import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.core.model.AmwayAccountModel;


public interface AmwayApacCouponService extends CouponService
{

	/**
	 * Creates AmwayCoupon for customer.
	 */
	AmwayCouponModel createAmwayCoupon(String redeemableCouponCode, Date startDate, int validityInDays, CMSSiteModel site,
			CustomerModel customer);

	/**
	 * Creates AmwayCoupon for amway-account.
	 */
	AmwayCouponModel createAmwayCoupon(String redeemableCouponCode, Date startDate, int validityInDays, CMSSiteModel site,
			AmwayAccountModel account);

	/**
	 * generates unique coupon code for AmwayCoupon
	 */
	String generateCodeForAmwayCoupon(AmwayCouponModel amwayCoupon);
}
