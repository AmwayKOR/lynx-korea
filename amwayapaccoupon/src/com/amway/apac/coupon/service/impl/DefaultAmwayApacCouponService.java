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

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.apac.coupon.service.AmwayApacCouponService;
import com.amway.core.model.AmwayAccountModel;


public class DefaultAmwayApacCouponService extends DefaultCouponService implements AmwayApacCouponService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAmwayApacCouponService.class);

	private static final String DEFAULT_APAC_COUPON_KEY_GENERATOR = "defaultApacCouponKeyGenerator";

	Map<String, KeyGenerator> couponCodeGeneratorMap;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayCouponModel createAmwayCoupon(final String redeemableCouponCode, final Date startDate, final int validityInDays,
			final CMSSiteModel site, final CustomerModel customer)
	{
		ServicesUtil.validateParameterNotNull(site, "site should not be null!");
		ServicesUtil.validateParameterNotNull(customer, "customer should not be null!");

		final Optional<AbstractCouponModel> optionalAmwaycoupon = getCouponForCode(redeemableCouponCode);
		if (!optionalAmwaycoupon.isPresent())
		{
			return null;
		}
		final AmwayCouponModel amwayCouponModel = createAmwayCoupon(startDate, validityInDays, site, optionalAmwaycoupon.get());
		amwayCouponModel.setCustomer(customer);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Saving new  Amway-Coupon with redeemable coupon[ {0} ] for customer[ {1} ]", redeemableCouponCode,
					customer.getUid());
		}
		getModelService().save(amwayCouponModel);
		LOG.info(MessageFormat.format(
				"New  Amway-Coupon created. AmwayCoupon code[ {0} ], Redeemable coupon[ {1} ] for Customer[ {2} ]",
				amwayCouponModel.getCode(), redeemableCouponCode, customer.getUid()));
		return amwayCouponModel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayCouponModel createAmwayCoupon(final String redeemableCouponCode, final Date startDate, final int validityInDays,
			final CMSSiteModel site, final AmwayAccountModel account)
	{
		ServicesUtil.validateParameterNotNull(site, "site should not be null!");
		ServicesUtil.validateParameterNotNull(account, "Amway-account should not be null!");

		final Optional<AbstractCouponModel> optionalAmwaycoupon = getCouponForCode(redeemableCouponCode);
		if (!optionalAmwaycoupon.isPresent())
		{
			return null;
		}

		final AmwayCouponModel amwayCouponModel = createAmwayCoupon(startDate, validityInDays, site, optionalAmwaycoupon.get());
		amwayCouponModel.setAccount(account);
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Saving new  Amway-Coupon with redeemable coupon[ {0} ] for account[ {1} ]", redeemableCouponCode,
					account.getPk());
		}
		getModelService().save(amwayCouponModel);
		LOG.info(MessageFormat.format(
				"New  Amway-Coupon created. AmwayCoupon code[ {0} ], Redeemable coupon[ {1} ] for Account[ {2} ]",
				amwayCouponModel.getCode(), redeemableCouponCode, account.getCode()));
		return amwayCouponModel;
	}

	/**
	 * Creates and initializes AmwayCoupon.
	 */
	private AmwayCouponModel createAmwayCoupon(final Date startDate, final int validityInDays, final CMSSiteModel site,
			final AbstractCouponModel coupon)
	{
		final AmwayCouponModel counponModel = getModelService().create(AmwayCouponModel.class);

		counponModel.setRedemptionCoupon(coupon);
		counponModel.setSite(site);
		counponModel.setStartDate(new Date());
		counponModel.setEndDate(DateUtils.addDays(new Date(), validityInDays));

		if (Objects.nonNull(startDate))
		{
			counponModel.setStartDate(startDate);
			counponModel.setEndDate(DateUtils.addDays(startDate, validityInDays));
		}
		return counponModel;
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


}
