package com.amway.apac.coupon.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.couponservices.model.AbstractCouponModel;
import de.hybris.platform.couponservices.services.impl.DefaultCouponService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.coupon.dao.AmwayApacCouponDao;
import com.amway.apac.coupon.enums.AmwayCouponStatus;
import com.amway.apac.coupon.model.AmwayCouponModel;
import com.amway.apac.coupon.service.data.AmwayCouponCreationParameter;
import com.amway.apac.coupon.services.AmwayApacCouponService;
import com.amway.core.model.AmwayAccountModel;


/**
 * Amway Coupon Service for generating/retrieving/modifying Amway coupons. Default implementation of
 * {@link AmwayApacCouponService}
 *
 * @author kanwarpreetkaur
 */
public class DefaultAmwayApacCouponService extends DefaultCouponService implements AmwayApacCouponService
{
	/**
	 * Coupon key generator.
	 */
	private static final String DEFAULT_APAC_COUPON_KEY_GENERATOR = "defaultApacCouponKeyGenerator";

	private AmwayApacCouponDao amwayApacCouponDao;
	private Map<String, KeyGenerator> couponCodeGeneratorMap;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AmwayCouponModel> createAmwayCoupon(final AmwayCouponCreationParameter... couponCreationParam)
	{
		final List<AmwayCouponModel> amwayCouponList = new ArrayList<>();
		for (final AmwayCouponCreationParameter couponCreationParameter : couponCreationParam)
		{
			validateParameterNotNull(couponCreationParameter.getRedemptionCoupon(), "Coupon should not be null!");
			validateParameterNotNull(couponCreationParameter.getStartDate(), "coupon start date should not be null!");
			validateParameterNotNull(couponCreationParameter.getEndDate(), "coupon end date should not be null!");
			validateParameterNotNull(couponCreationParameter.getStore(), "store should not be null!");
			if (Objects.isNull(couponCreationParameter.getCustomer()) && Objects.isNull(couponCreationParameter.getAmwayAccount()))
			{
				throw new IllegalArgumentException("Both account and customer should not be null!");
			}

			final AmwayCouponModel amwayCoupon = createAmwayCoupon(couponCreationParameter.getStartDate(),
					couponCreationParameter.getEndDate(), couponCreationParameter.getStore(),
					couponCreationParameter.getRedemptionCoupon(), couponCreationParameter.getCustomer(),
					couponCreationParameter.getAmwayAccount());

			amwayCouponList.add(amwayCoupon);
		}
		if (CollectionUtils.isNotEmpty(amwayCouponList))
		{
			getModelService().saveAll(amwayCouponList);
		}
		return amwayCouponList;
	}

	/**
	 * Creates and initializes AmwayCoupon.
	 */
	protected AmwayCouponModel createAmwayCoupon(final Date startDate, final Date endDate, final BaseStoreModel store,
			final AbstractCouponModel coupon, final CustomerModel customer, final AmwayAccountModel account)
	{
		final AmwayCouponModel counponModel = getModelService().create(AmwayCouponModel.class);

		counponModel.setRedemptionCoupon(coupon);
		counponModel.setStore(store);
		counponModel.setStartDate(startDate);
		counponModel.setEndDate(endDate);
		counponModel.setCustomer(customer);
		counponModel.setAccount(account);
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
	public List<AmwayCouponModel> getExpiredAmwayCoupons()
	{
		return amwayApacCouponDao.getExpiredAmwayCoupons();
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
			final boolean showActive)
	{
		return amwayApacCouponDao.getAmwayCouponsForAbo(customer, couponStatuses, showActive);
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
	@Required
	public void setAmwayApacCouponDao(final AmwayApacCouponDao amwayApacCouponDao)
	{
		this.amwayApacCouponDao = amwayApacCouponDao;
	}

}
