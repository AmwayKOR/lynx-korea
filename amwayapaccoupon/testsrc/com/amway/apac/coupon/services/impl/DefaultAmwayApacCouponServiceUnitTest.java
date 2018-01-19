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

import static org.mockito.BDDMockito.given;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.couponservices.model.SingleCodeCouponModel;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.store.BaseStoreModel;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.amway.apac.coupon.dao.AmwayApacCouponDao;
import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.apac.coupon.service.data.AmwayCouponCreationParameter;
import com.amway.core.model.AmwayAccountModel;


/**
 * Unit Test class
 *
 * @author tusharsharma
 */
@UnitTest
public class DefaultAmwayApacCouponServiceUnitTest
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
	DefaultAmwayApacCouponService amwayApacCouponService = new DefaultAmwayApacCouponService();
	@Mock
	private AmwayApacCouponDao amwayApacCouponDao;
	@Mock
	private Map<String, KeyGenerator> couponCodeGeneratorMap;
	@Mock
	private ModelService modelService;
	@Mock
	private KeyGenerator mockKeyGenerator;

	private AmwayCouponCreationParameter couponCreationParam;
	private AmwayCouponModel amwayCoupon;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void prepare() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		final AmwayAccountModel account = new AmwayAccountModel();
		account.setCode(ACCOUNT_NUMBER);

		final CustomerModel customer = new CustomerModel();
		customer.setUid(CUSTOMER_UID);

		final BaseStoreModel store = new BaseStoreModel();
		store.setUid(STORE_UID);
		store.setAffiliateNumber(STORE_AFFILIATE_NUMBER);

		final SingleCodeCouponModel coupon = new SingleCodeCouponModel();
		coupon.setCouponId(SINGLE_CODE_COUPON_ID);
		coupon.setName(SINGLE_CODE_COUPON_NAME, Locale.ENGLISH);

		initializeCouponCreationParam(account, customer, store, coupon);
		initializeAmwayCoupon(account, customer, store);

		couponCodeGeneratorMap = new LinkedHashMap<>(1);
		couponCodeGeneratorMap.put("defaultApacCouponKeyGenerator", mockKeyGenerator);
		given(mockKeyGenerator.generate()).willReturn("1111111111");
		amwayApacCouponService.setCouponCodeGeneratorMap(couponCodeGeneratorMap);
	}

	private void initializeAmwayCoupon(final AmwayAccountModel account, final CustomerModel customer, final BaseStoreModel store)
	{
		amwayCoupon = new AmwayCouponModel();
		amwayCoupon.setAccount(account);
		amwayCoupon.setCustomer(customer);
		amwayCoupon.setCode("1111111111");
		amwayCoupon.setStatus(AmwayCouponStatus.NEW);
		amwayCoupon.setStore(store);
		amwayCoupon.setStartDate(couponStartDate);
		amwayCoupon.setEndDate(couponEndDate);
	}

	private void initializeCouponCreationParam(final AmwayAccountModel account, final CustomerModel customer,
			final BaseStoreModel store, final SingleCodeCouponModel coupon)
	{
		couponCreationParam = new AmwayCouponCreationParameter();
		couponCreationParam.setAmwayAccount(account);
		couponCreationParam.setCustomer(customer);
		couponCreationParam.setStore(store);
		couponCreationParam.setRedemptionCoupon(coupon);
		couponCreationParam.setStartDate(couponStartDate);
		couponCreationParam.setEndDate(couponEndDate);
	}

	@Test
	public void testCreateAmwayCoupon()
	{
		given(modelService.create(AmwayCouponModel.class)).willReturn(new AmwayCouponModel());
		Mockito.doNothing().when(modelService).saveAll(Mockito.anyCollection());
		final List<AmwayCouponModel> couponList = amwayApacCouponService.createAmwayCoupon(couponCreationParam);
		Assert.assertTrue(CollectionUtils.isNotEmpty(couponList));

	}

	@Test
	public void testGetAmwayCouponForCode()
	{
		final String couponCode = amwayCoupon.getCode();
		given(amwayApacCouponDao.findAmwayCouponByCode(couponCode)).willReturn(amwayCoupon);
		final AmwayCouponModel couponModel = amwayApacCouponService.getAmwayCouponForCode(couponCode);
		Assert.assertNotNull(couponModel);
	}

	@Test
	public void testGetExpiredAmwayCoupons()
	{
		given(amwayApacCouponDao.getExpiredAmwayCoupons()).willReturn(Arrays.asList(amwayCoupon));
		final List<AmwayCouponModel> couponList = amwayApacCouponService.getExpiredAmwayCoupons();
		Assert.assertTrue(CollectionUtils.isNotEmpty(couponList));
	}

	@Test
	public void testGenerateCodeForAmwayCoupon()
	{
		final String code = amwayApacCouponService.generateCodeForAmwayCoupon(new AmwayCouponModel());
		Assert.assertEquals("1111111111", code);
	}

	@Test
	public void testGetAmwayCouponsForAbo()
	{
		final CustomerModel customer = couponCreationParam.getCustomer();
		final List<AmwayCouponStatus> couponStatuses = Arrays.asList(AmwayCouponStatus.NEW, AmwayCouponStatus.REISSUED);
		final boolean showActive = false;
		given(amwayApacCouponDao.getAmwayCouponsForAbo(customer, couponStatuses, showActive))
				.willReturn(Arrays.asList(amwayCoupon));
		final List<AmwayCouponModel> couponList = amwayApacCouponService.getAmwayCouponsForAbo(customer, couponStatuses,
				showActive);
		Assert.assertTrue(CollectionUtils.isNotEmpty(couponList));
	}
}
