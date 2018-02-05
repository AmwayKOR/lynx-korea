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

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.couponservices.model.SingleCodeCouponModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.apac.coupon.services.AmwayApacCouponService;
import com.amway.core.model.AmwayAccountModel;


/**
 * @author tusharsharma
 */

@UnitTest
public class AmwayCouponPrepareInterceptorUnitTest
{

	private final String SINGLE_CODE_COUPON_NAME = "test coupon";
	private final String SINGLE_CODE_COUPON_ID = "couponTest";
	private final String STORE_AFFILIATE_NUMBER = "999";
	private final String STORE_UID = "defaultStore";
	private final String CUSTOMER_UID = "abc";
	private final String ACCOUNT_NUMBER = "10000000000";
	private final Date couponStartDate = new Date();
	private final Date couponEndDate = DateUtils.addDays(couponStartDate, 30);

	@InjectMocks
	private final AmwayCouponPrepareInterceptor interceptor = new AmwayCouponPrepareInterceptor();
	@Mock
	private InterceptorContext ctx;
	@Mock
	private AmwayApacCouponService couponService;

	private AmwayCouponModel couponModel;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		final AmwayAccountModel account = new AmwayAccountModel();
		account.setCode(ACCOUNT_NUMBER);

		final CustomerModel customer = new CustomerModel();
		customer.setUid(CUSTOMER_UID);

		final BaseStoreModel store = new BaseStoreModel();
		store.setUid(STORE_UID);
		store.setAffiliateNumber(STORE_AFFILIATE_NUMBER);

		final SingleCodeCouponModel redemptionCoupon = new SingleCodeCouponModel();
		redemptionCoupon.setCouponId(SINGLE_CODE_COUPON_NAME);
		redemptionCoupon.setName(SINGLE_CODE_COUPON_ID, Locale.ENGLISH);

		couponModel = new AmwayCouponModel();
		couponModel.setAccount(account);
		couponModel.setCustomer(customer);
		couponModel.setStore(store);
		couponModel.setRedemptionCoupon(redemptionCoupon);
		couponModel.setStatus(AmwayCouponStatus.NEW);
		couponModel.setStartDate(couponStartDate);
		couponModel.setEndDate(couponEndDate);
	}

	/**
	 * Test method for
	 * {@link com.amway.apac.coupon.interceptor.impl.AmwayCouponPrepareInterceptor.onPrepare(AmwayCouponModel,
	 * InterceptorContext)}
	 *
	 * @throws InterceptorException
	 */
	@Test
	public void testOnPrepareWhenCodeIsNull() throws InterceptorException
	{
		given(interceptor.getCouponCode(couponModel)).willReturn("123456789");
		interceptor.onPrepare(couponModel, ctx);
		Assert.assertEquals("123456789", couponModel.getCode());
	}

	/**
	 * Test method for
	 * {@link com.amway.apac.coupon.interceptor.impl.AmwayCouponPrepareInterceptor.onPrepare(AmwayCouponModel,
	 * InterceptorContext)}
	 *
	 * @throws InterceptorException
	 */
	@Test
	public void testOnPrepareWhenStoreChanges() throws InterceptorException
	{
		couponModel.setCode("123456789");
		given(Boolean.valueOf(ctx.isModified(couponModel, AmwayCouponModel.STORE))).willReturn(Boolean.TRUE);
		given(interceptor.getCouponCode(couponModel)).willReturn("987654321");
		interceptor.onPrepare(couponModel, ctx);

		Assert.assertEquals("987654321", couponModel.getCode());
	}
}
