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

import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.couponservices.model.AbstractCouponModel;
import de.hybris.platform.couponservices.services.impl.DefaultCouponService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.coupon.dao.AmwayApacCouponDao;
import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.apac.coupon.service.AmwayApacCouponService;
import com.amway.core.model.AmwayAccountModel;


/**
 * Amway Coupon Service for generating/retrieving/modifying Amway coupons
 *
 * @author kanwarpreetkaur
 */
public class DefaultAmwayApacCouponService extends DefaultCouponService implements AmwayApacCouponService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAmwayApacCouponService.class);

	private static final String DEFAULT_APAC_COUPON_KEY_GENERATOR = "defaultApacCouponKeyGenerator";

	private AmwayApacCouponDao amwayApacCouponDao;
	Map<String, KeyGenerator> couponCodeGeneratorMap;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayCouponModel createAmwayCoupon(final String redeemableCouponCode, final Date startDate, final Date endDate,
			final CMSSiteModel site, final CustomerModel customer)
	{
		ServicesUtil.validateParameterNotNull(startDate, "coupon start date should not be null!");
		ServicesUtil.validateParameterNotNull(endDate, "coupon end date should not be null!");
		ServicesUtil.validateParameterNotNull(site, "site should not be null!");
		ServicesUtil.validateParameterNotNull(customer, "customer should not be null!");

		AmwayCouponModel amwayCouponModel = null;
		final Optional<AbstractCouponModel> optionalAmwaycoupon = getCouponForCode(redeemableCouponCode);
		if (optionalAmwaycoupon.isPresent())
		{
			amwayCouponModel = createAmwayCoupon(startDate, endDate, site, optionalAmwaycoupon.get());
			amwayCouponModel.setCustomer(customer);
			LOG.info(new StringBuilder("Created new  Amway-Coupon with redeemable coupon[").append(redeemableCouponCode)
					.append("] for customer[").append(customer.getUid()).append("].").toString());
		}
		return amwayCouponModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayCouponModel createAmwayCoupon(final String redeemableCouponCode, final Date startDate, final Date endDate,
			final CMSSiteModel site, final AmwayAccountModel account)
	{
		ServicesUtil.validateParameterNotNull(startDate, "coupon start date should not be null!");
		ServicesUtil.validateParameterNotNull(endDate, "coupon end date should not be null!");
		ServicesUtil.validateParameterNotNull(site, "site should not be null!");
		ServicesUtil.validateParameterNotNull(account, "Amway-account should not be null!");

		AmwayCouponModel amwayCouponModel = null;
		final Optional<AbstractCouponModel> optionalAmwaycoupon = getCouponForCode(redeemableCouponCode);
		if (optionalAmwaycoupon.isPresent())
		{
			amwayCouponModel = createAmwayCoupon(startDate, endDate, site, optionalAmwaycoupon.get());
			amwayCouponModel.setAccount(account);
			LOG.info(new StringBuilder("Created new  Amway-Coupon with redeemable coupon[").append(redeemableCouponCode)
					.append("] for account[").append(account.getCode()).append("].").toString());
		}
		return amwayCouponModel;
	}

	/**
	 * Creates and initializes AmwayCoupon.
	 */
	private AmwayCouponModel createAmwayCoupon(final Date startDate, final Date endDate, final CMSSiteModel site,
			final AbstractCouponModel coupon)
	{
		final AmwayCouponModel counponModel = getModelService().create(AmwayCouponModel.class);

		counponModel.setRedemptionCoupon(coupon);
		counponModel.setSite(site);
		counponModel.setStartDate(startDate);
		counponModel.setEndDate(endDate);
		return counponModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayCouponModel getAmwayCouponForCode(final String couponCode)
	{
		return amwayApacCouponDao.findAmwayCouponByCode(couponCode);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generateCodeForAmwayCoupon(final AmwayCouponModel amwayCoupon)
	{
		return (String) getCouponCodeGeneratorMap().get(DEFAULT_APAC_COUPON_KEY_GENERATOR).generate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayCouponModel> getAmwayCouponsForAbo(final CustomerModel customer, final List<AmwayCouponStatus> couponStatuses,
			final boolean filterByDate)
	{
		return amwayApacCouponDao.getAmwayCouponsForAbo(customer, couponStatuses, filterByDate);
	}

	/**
	 * @return the couponCodeGeneratorMap
	 */
	public Map<String, KeyGenerator> getCouponCodeGeneratorMap()
	{
		return couponCodeGeneratorMap;
	}

	/**
	 * @param couponCodeGeneratorMap
	 *           the couponCodeGeneratorMap to set
	 */
	@Required
	public void setCouponCodeGeneratorMap(final Map<String, KeyGenerator> couponCodeGeneratorMap)
	{
		this.couponCodeGeneratorMap = couponCodeGeneratorMap;
	}

	/**
	 * getter for AmwayApacCouponDao
	 *
	 * @return AmwayApacCouponDao
	 */
	public AmwayApacCouponDao getAmwayApacCouponDao()
	{
		return amwayApacCouponDao;
	}

	/**
	 * setter for AmwayApacCouponDao
	 */
	public void setAmwayApacCouponDao(final AmwayApacCouponDao amwayApacCouponDao)
	{
		this.amwayApacCouponDao = amwayApacCouponDao;
	}


}
