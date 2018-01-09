package com.amway.apac.core.product.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.PRE_LAUNCH_PROMOTION;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static java.lang.String.format;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.enums.PaymentType;
import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.model.AmwayUserPromotionCountModel;
import com.amway.apac.core.product.daos.AmwayApacProductDao;
import com.amway.apac.core.product.services.AmwayApacProductService;


/**
 * Default implementation for {@link AmwayApacProductService}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacProductService extends DefaultProductService implements AmwayApacProductService
{
	private AmwayApacProductDao amwayApacProductDao;
	private GenericDao<AmwayUserPromotionCountModel> amwayUserPromotionCountDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayPaymentOptionModel getAllPaymentOptionForOmsCode(final String omsCode, final CatalogVersionModel catalogVersion)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("omsCode", omsCode);
		ServicesUtil.validateParameterNotNullStandardMessage("catalogVersion", catalogVersion);

		final List<AmwayPaymentOptionModel> paymentOptions = amwayApacProductDao.getAllAmwayPaymentOptionFromOmsCode(omsCode,
				catalogVersion);

		validateIfSingleResult(paymentOptions, format("omsCode '%s' not found!", omsCode),
				format("omsCode '%s' is not unique, %d products found!", omsCode, Integer.valueOf(0)));

		return paymentOptions.iterator().next();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayPaymentOptionModel getPaymentOptionForOmsCode(final String omsCode, final CatalogVersionModel catalogVersion)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("omsCode", omsCode);
		ServicesUtil.validateParameterNotNullStandardMessage("catalogVersion", catalogVersion);

		final List<AmwayPaymentOptionModel> paymentOptions = amwayApacProductDao.getAmwayPaymentOptionFromOmsCode(omsCode,
				catalogVersion);

		validateIfSingleResult(paymentOptions, format("omsCode '%s' not found!", omsCode),
				format("omsCode '%s' is not unique, %d products found!", omsCode, Integer.valueOf(0)));

		return paymentOptions.get(0);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkIfPIFIsActive(final ProductModel productModel)
	{
		boolean isActive = Boolean.FALSE.booleanValue();
		if (CollectionUtils.isNotEmpty(productModel.getPaymentOption()))
		{
			for (final AmwayPaymentOptionModel paymentOption : productModel.getPaymentOption())
			{
				if ((paymentOption.getActive().booleanValue()) && (PaymentType.PIF.equals(paymentOption.getType()))
						&& (isPaymentOptionCurrentlyAvailable(paymentOption)))
				{
					isActive = Boolean.TRUE.booleanValue();
				}
			}
		}
		return isActive;
	}

	/**
	 * Checks if the payment option is currently available i.e. if start and end date are present for the payment option
	 * then current date should lie in start and end date.
	 *
	 * @param paymentOption
	 * @return boolean for payment option availability
	 */
	protected boolean isPaymentOptionCurrentlyAvailable(final AmwayPaymentOptionModel paymentOption)
	{
		final Date currentDate = new Date();
		return (Objects.isNull(paymentOption.getStartDate()) && Objects.isNull(paymentOption.getEndDate()))
				|| (currentDate.after(paymentOption.getStartDate()) && currentDate.before(paymentOption.getEndDate()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getUsedQuantityForPrelaunch(final String userId, final String productCode)
	{
		ServicesUtil.validateParameterNotNullStandardMessage("userId", userId);
		ServicesUtil.validateParameterNotNullStandardMessage("productCode", productCode);

		int usedQuantity = 0;

		final Map<String, Object> attributes = new HashMap<>();
		attributes.put(AmwayUserPromotionCountModel.USERID, userId);
		attributes.put(AmwayUserPromotionCountModel.PRODUCTCODE, productCode);
		attributes.put(AmwayUserPromotionCountModel.PROMOTIONCODE, PRE_LAUNCH_PROMOTION);

		final List<AmwayUserPromotionCountModel> promotionCountModelList = getAmwayUserPromotionCountDao().find(attributes);

		if (CollectionUtils.isNotEmpty(promotionCountModelList))
		{
			usedQuantity = promotionCountModelList.iterator().next().getCurrentCount().intValue();
		}
		return usedQuantity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePreLaunchProductCount(final Map<String, Integer> productCodeToCount, final String amwayAccountCode)
	{
		final List<String> preLaunchProducts = new ArrayList<>(productCodeToCount.keySet());
		final List<AmwayUserPromotionCountModel> userSavedCountList = getAmwayApacProductDao()
				.getPromotionRuleCountByUserAndProduct(amwayAccountCode, preLaunchProducts, PRE_LAUNCH_PROMOTION);
		final List<AmwayUserPromotionCountModel> newUserPromotionCountList = new ArrayList<>();
		for (final Entry<String, Integer> entry : productCodeToCount.entrySet())
		{
			final AmwayUserPromotionCountModel existingUserPromotionCount = userSavedCountList.stream()
					.filter(x -> entry.getKey().equalsIgnoreCase(x.getProductCode())).findAny().orElse(null);
			if (existingUserPromotionCount != null)
			{
				final int currentCount = existingUserPromotionCount.getCurrentCount().intValue();
				final int cartCount = entry.getValue().intValue();
				final int newCount = currentCount + cartCount;
				existingUserPromotionCount.setCurrentCount(Integer.valueOf(newCount)); // NO NEED FOR MAX CHECK
				newUserPromotionCountList.add(existingUserPromotionCount);
			}
			else
			{
				final AmwayUserPromotionCountModel newUserPromotionCount = getModelService()
						.create(AmwayUserPromotionCountModel.class);
				newUserPromotionCount.setUserId(amwayAccountCode);
				newUserPromotionCount.setPromotionCode(PRE_LAUNCH_PROMOTION);
				newUserPromotionCount.setProductCode(entry.getKey());
				newUserPromotionCount.setCurrentCount(entry.getValue());
				newUserPromotionCountList.add(newUserPromotionCount);
			}
		}

		getModelService().saveAll(newUserPromotionCountList);
	}

	@Override
	public Map<String, Integer> getPreLaunchConfigProducts(final AbstractOrderModel orderModel)
	{
		Assert.assertNotNull("Order is null !", orderModel);
		Assert.assertNotNull("Order Entry NULL !", orderModel.getEntries());

		final Map<String, Integer> productCodeToCount = new HashMap<>();
		for (final AbstractOrderEntryModel tempOrderEntry : orderModel.getEntries())
		{
			if (tempOrderEntry.getProduct().getPreLaunchConfig() != null)
			{
				productCodeToCount.put(tempOrderEntry.getProduct().getCode(),
						Integer.valueOf(tempOrderEntry.getQuantity().intValue()));
			}
		}

		return productCodeToCount;
	}

	/**
	 * @return the amwayApacProductDao
	 */
	public AmwayApacProductDao getAmwayApacProductDao()
	{
		return amwayApacProductDao;
	}

	/**
	 * @param amwayApacProductDao
	 *           the amwayApacProductDao to set
	 */
	@Required
	public void setAmwayApacProductDao(final AmwayApacProductDao amwayApacProductDao)
	{
		this.amwayApacProductDao = amwayApacProductDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.product.services.AmwayApacProductService#validateOmsCode(java.lang.String[], int[])
	 */
	@Override
	public boolean validateOmsCode(final String[] splitOmsCode, final int[] omsparams)
	{
		return (null != splitOmsCode) && (splitOmsCode.length == omsparams[0])
				&& (StringUtils.isNotBlank(splitOmsCode[omsparams[1]])) && (StringUtils.isNotBlank(splitOmsCode[omsparams[2]]));
	}

	/**
	 * @return the amwayUserPromotionCountDao
	 */
	public GenericDao<AmwayUserPromotionCountModel> getAmwayUserPromotionCountDao()
	{
		return amwayUserPromotionCountDao;
	}

	/**
	 * @param amwayUserPromotionCountDao
	 *           the amwayUserPromotionCountDao to set
	 */
	@Required
	public void setAmwayUserPromotionCountDao(final GenericDao<AmwayUserPromotionCountModel> amwayUserPromotionCountDao)
	{
		this.amwayUserPromotionCountDao = amwayUserPromotionCountDao;
	}
}
