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
package com.amway.apac.coupon.interceptor.impl;

import de.hybris.platform.servicelayer.exceptions.ModelSavingException;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.apac.coupon.services.AmwayApacCouponService;


/**
 * Interceptor implementation for populating code.
 *
 * @author tusharsharma
 */
public class AmwayCouponPrepareInterceptor implements PrepareInterceptor<AmwayCouponModel>
{
	private AmwayApacCouponService couponService;

	/**
	 *
	 * @param amwayCoupon
	 * @param context
	 * @throws InterceptorException
	 */
	@Override
	public void onPrepare(final AmwayCouponModel amwayCoupon, final InterceptorContext context) throws InterceptorException
	{
		populateAmwayCouponCode(amwayCoupon, context);
	}

	protected void populateAmwayCouponCode(final AmwayCouponModel amwayCoupon, final InterceptorContext context)
	{
		if (null != amwayCoupon.getStore())
		{
			if (StringUtils.isEmpty(amwayCoupon.getCode()) || context.isModified(amwayCoupon, AmwayCouponModel.STORE))
			{
				amwayCoupon.setCode(getCouponCode(amwayCoupon));
			}
		}
		else
		{
			throw new ModelSavingException("Please provide store.");
		}
	}

	protected String getCouponCode(final AmwayCouponModel amwayCoupon)
	{
		return couponService.generateCodeForAmwayCoupon(amwayCoupon);
	}

	/**
	 * @param couponService
	 *           the couponService to set
	 */
	@Required
	public void setCouponService(final AmwayApacCouponService couponService)
	{
		this.couponService = couponService;
	}
}
