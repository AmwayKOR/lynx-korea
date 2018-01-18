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
package com.amway.apac.coupon.services.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.couponservices.model.AbstractCouponModel;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.apac.coupon.service.data.AmwayCouponCreationParameter;
import com.amway.apac.coupon.services.AmwayApacCouponService;
import com.amway.core.account.service.AmwayAccountService;
import com.amway.core.model.AmwayAccountModel;


/**
 * Test class
 */
@IntegrationTest
public class DefaultAmwayApacCouponServiceIntegrationTest extends ServicelayerTransactionalTest
{

	@Resource(name = "couponService")
	AmwayApacCouponService amwayApacCouponService;
	@Resource
	private AmwayAccountService amwayAccountService;
	@Resource
	private UserService userService;
	@Resource
	private BaseStoreService baseStoreService;


	private final static String ACCOUNT_CODE = "3108051595";
	private static final String CUSTOMER_UID = "amway_party_test_1";
	private static final String COUPON_CODE = "amway_test_single_code_coupon";
	private static final String AMWAY_COUPON_CODE = "20000000000";
	private static final String BASE_STORE_UID = "defaultstore";
	private AmwayCouponCreationParameter couponCreationParam;

	@Before
	public void setUp() throws ImpExException
	{
		importCsv("/amwayapaccoupon/test/common.csv", "windows-1252");
		importCsv("/amwayapaccoupon/test/amwayCouponTestData.csv", "windows-1252");

		final Optional<AbstractCouponModel> optionalCoupon = amwayApacCouponService.getCouponForCode(COUPON_CODE);
		final Date startDate = new Date();
		final Date endDate = DateUtils.addDays(startDate, 30);
		final BaseStoreModel store = baseStoreService.getBaseStoreForUid(BASE_STORE_UID);
		final CustomerModel customer = (CustomerModel) userService.getUserForUID(CUSTOMER_UID);
		final AmwayAccountModel account = amwayAccountService.findAccount(ACCOUNT_CODE);
		couponCreationParam = new AmwayCouponCreationParameter();
		couponCreationParam.setAmwayAccount(account);
		couponCreationParam.setCustomer(customer);
		couponCreationParam.setStore(store);
		couponCreationParam.setRedemptionCoupon(optionalCoupon.get());
		couponCreationParam.setStartDate(startDate);
		couponCreationParam.setEndDate(endDate);
	}

	@Test
	public void testCreateAmwayCouponForCustomer()
	{
		couponCreationParam.setAmwayAccount(null);
		final List<AmwayCouponModel> couponList = amwayApacCouponService.createAmwayCoupon(couponCreationParam);
		final AmwayCouponModel coupon = couponList.get(0);
		Assert.assertEquals(coupon.getCustomer(), couponCreationParam.getCustomer());
	}

	@Test
	public void testCreateAmwayCouponForAmwayAccount()
	{
		couponCreationParam.setCustomer(null);
		final List<AmwayCouponModel> couponList = amwayApacCouponService.createAmwayCoupon(couponCreationParam);
		final AmwayCouponModel coupon = couponList.get(0);
		Assert.assertEquals(coupon.getAccount(), couponCreationParam.getAmwayAccount());
	}

	@Test
	public void testCreateAmwayCouponForAmwayAccountAndCustomer()
	{
		final List<AmwayCouponModel> couponList = amwayApacCouponService.createAmwayCoupon(couponCreationParam);
		final AmwayCouponModel coupon = couponList.get(0);
		Assert.assertEquals(coupon.getCustomer(), couponCreationParam.getCustomer());
		Assert.assertEquals(coupon.getAccount(), couponCreationParam.getAmwayAccount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateAmwayCouponWithNoOwner()
	{
		couponCreationParam.setAmwayAccount(null);
		couponCreationParam.setCustomer(null);
		amwayApacCouponService.createAmwayCoupon(couponCreationParam);
	}

	@Test
	public void testGetAmwayCouponForCode()
	{
		final AmwayCouponModel couponModel = amwayApacCouponService.getAmwayCouponForCode(AMWAY_COUPON_CODE);
		Assert.assertEquals(AMWAY_COUPON_CODE, couponModel.getCode());
	}

	@Test
	public void testGetExpiredAmwayCoupons()
	{
		final List<AmwayCouponModel> couponList = amwayApacCouponService.getExpiredAmwayCoupons();
		final AmwayCouponModel coupon = couponList.get(0);
		Assert.assertTrue(new Date().after(coupon.getEndDate()));
		Assert.assertEquals("3108051595", coupon.getAccount().getCode());
	}

	@Test
	public void testGenerateCodeForAmwayCoupon()
	{
		final List<AmwayCouponModel> couponList = amwayApacCouponService.createAmwayCoupon(couponCreationParam);
		final String code = amwayApacCouponService.generateCodeForAmwayCoupon(couponList.get(0));
		Assert.assertNotNull(code);
		Assert.assertTrue(code.length() == 10);
	}

	@Test
	public void testActiveAmwayCouponsForAbo()
	{
		final List<AmwayCouponStatus> couponStatuses = Arrays.asList(AmwayCouponStatus.NEW, AmwayCouponStatus.REISSUED);
		final CustomerModel customer = (CustomerModel) userService.getUserForUID(CUSTOMER_UID);
		final List<AmwayCouponModel> couponList = amwayApacCouponService.getAmwayCouponsForAbo(customer, couponStatuses, true);

		final Optional<AmwayCouponModel> optionalAmwayCoupon = couponList.stream()
				.filter(x -> !couponStatuses.contains(x.getStatus())).findAny();

		Assert.assertFalse(optionalAmwayCoupon.isPresent());
	}

	@Test
	public void testGetAllAmwayCouponsForAbo()
	{
		final CustomerModel customer = (CustomerModel) userService.getUserForUID(CUSTOMER_UID);
		final List<AmwayCouponModel> couponList = amwayApacCouponService.getAmwayCouponsForAbo(customer, null, false);

		final Optional<AmwayCouponModel> optionalAmwayCoupon = couponList.stream()
				.filter(x -> StringUtils.equals(AMWAY_COUPON_CODE, x.getCode())).findAny();

		Assert.assertTrue(optionalAmwayCoupon.isPresent());
	}
}
