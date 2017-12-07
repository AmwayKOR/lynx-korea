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

import de.hybris.platform.couponservices.services.CouponService;

import com.amway.apac.coupon.data.AmwayCouponCreationParameter;


public interface AmwayApacCouponService extends CouponService
{

	/**
	 * Creates coupon in database on the basis of passed parameter.
	 */
	public void createAmwayCoupon(AmwayCouponCreationParameter couponCreationParameter);
}
