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
package com.amway.apac.coupon.service.impl;

import de.hybris.platform.couponservices.services.impl.DefaultCouponService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amway.apac.coupon.data.AmwayCouponCreationParameter;
import com.amway.apac.coupon.service.AmwayApacCouponService;


public class DefaultAmwayApacCouponService extends DefaultCouponService implements AmwayApacCouponService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAmwayApacCouponService.class);

	@Override
	public void createAmwayCoupon(final AmwayCouponCreationParameter couponCreationParameter)
	{
		// YTODO Implementation will be provided in child class.

	}
}
