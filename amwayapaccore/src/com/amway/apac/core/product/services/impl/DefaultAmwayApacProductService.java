package com.amway.apac.core.product.services.impl;

import static com.amway.apac.core.constants.AmwayapacCoreConstants.PRE_LAUNCH_PROMOTION;
import static com.amway.apac.core.model.AmwayPaymentOptionModel.ALIASCODE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.PRODUCTCODE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.PROMOTIONCODE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.STORE;
import static com.amway.apac.core.model.AmwayUserPromotionCountModel.USERID;
import static de.hybris.platform.core.model.product.ProductModel.CATALOGVERSION;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.store.BaseStoreModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.fest.util.Collections;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.enums.PaymentType;
import com.amway.apac.core.model.AmwayPaymentOptionModel;
import com.amway.apac.core.model.AmwayUserPromotionCountModel;
import com.amway.apac.core.product.daos.AmwayApacProductDao;
import com.amway.apac.core.product.services.AmwayApacProductService;
import com.amway.core.enums.AmwayKitProductType;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.model.AmwayKitProductModel;


/**
 * Default implementation for {@link AmwayApacProductService}
 *
 * @author Ashish Sabal
 *
 */
public class DefaultAmwayApacProductService extends DefaultProductService implements AmwayApacProductService
{

	/** The Constant String PRODUCT. */
	private static final String PRODUCT = "Product";

	/** The Constant String PRODUCT_CODE_MAP. */
	private static final String PRODUCT_CODE_MAP = "Product Code Map";

	/** The Constant String ORDER. */
	private static final String ORDER = "Order";

	/** The Constant String KIT_TYPE. */
	private static final String KIT_TYPE = "Kit Type";

	/** The Constant String OMS_PARAMS. */
	private static final String OMS_PARAMS = "OMS parameters";

	/** The amway apac product dao. */
	private AmwayApacProductDao amwayApacProductDao;

	/** The amway user promotion count dao. */
	private GenericDao<AmwayUserPromotionCountModel> amwayUserPromotionCountDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayPaymentOptionModel getAllPaymentOptionForOmsCode(final String omsCode, final CatalogVersionModel catalogVersion)
	{
		validateParameterNotNullStandardMessage(ALIASCODE, omsCode);
		validateParameterNotNullStandardMessage(CATALOGVERSION, catalogVersion);

		final List<AmwayPaymentOptionModel> paymentOptions = getAmwayApacProductDao().getAllAmwayPaymentOptionFromOmsCode(omsCode,
				catalogVersion);

		validateIfSingleResult(paymentOptions,
				new StringBuilder(200).append("omsCode '").append(omsCode).append("' not found!").toString(),
				new StringBuilder(200).append("omsCode '").append(omsCode).append("' is not unique, ")
						.append(AmwayapacCoreConstants.ZERO_INT).append(" products found!").toString());

		return paymentOptions.iterator().next();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayPaymentOptionModel getPaymentOptionForOmsCode(final String omsCode, final CatalogVersionModel catalogVersion)
	{
		validateParameterNotNullStandardMessage(ALIASCODE, omsCode);
		validateParameterNotNullStandardMessage(CATALOGVERSION, catalogVersion);

		final List<AmwayPaymentOptionModel> paymentOptions = getAmwayApacProductDao().getAmwayPaymentOptionFromOmsCode(omsCode,
				catalogVersion);

		validateIfSingleResult(paymentOptions,
				new StringBuilder(200).append("omsCode '").append(omsCode).append("' not found!").toString(),
				new StringBuilder(200).append("omsCode '").append(omsCode).append("' is not unique, ")
						.append(AmwayapacCoreConstants.ZERO_INT).append(" products found!").toString());

		return paymentOptions.get(AmwayapacCoreConstants.ZERO_INT.intValue());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkIfPIFIsActive(final ProductModel productModel)
	{
		validateParameterNotNullStandardMessage(PRODUCT, productModel);

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
	public int getUsedQuantityForPrelaunch(final String userId, final String productCode, final BaseStoreModel store)
	{
		validateParameterNotNullStandardMessage(USERID, userId);
		validateParameterNotNullStandardMessage(PRODUCTCODE, productCode);
		validateParameterNotNullStandardMessage(STORE, store);

		int usedQuantity = 0;

		final Map<String, Object> attributes = new HashMap<>();
		attributes.put(USERID, userId);
		attributes.put(PRODUCTCODE, productCode);
		attributes.put(PROMOTIONCODE, PRE_LAUNCH_PROMOTION);
		attributes.put(STORE, store);

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
	public void updatePreLaunchProductCount(final Map<String, Integer> productCodeToCount, final AbstractOrderModel order)
	{
		validateParameterNotNullStandardMessage(PRODUCT_CODE_MAP, productCodeToCount);
		validateParameterNotNullStandardMessage(ORDER, order);

		final String amwayAccountCode = getNormalizedAmwayAccountCode(order.getAccount());
		final List<String> preLaunchProducts = new ArrayList<>(productCodeToCount.keySet());
		final List<AmwayUserPromotionCountModel> userSavedCountList = getAmwayApacProductDao()
				.getPromotionRuleCountByUserAndProduct(amwayAccountCode, preLaunchProducts, PRE_LAUNCH_PROMOTION, order.getStore());
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
				newUserPromotionCount.setStore(order.getStore());
				newUserPromotionCountList.add(newUserPromotionCount);
			}
		}

		getModelService().saveAll(newUserPromotionCountList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> getPreLaunchConfigProducts(final AbstractOrderModel orderModel)
	{
		validateParameterNotNullStandardMessage(ORDER, orderModel);

		final Map<String, Integer> productCodeToCount = new HashMap<>();

		if (!(Collections.isEmpty(orderModel.getEntries())))
		{
			for (final AbstractOrderEntryModel tempOrderEntry : orderModel.getEntries())
			{
				if (tempOrderEntry.getProduct().getPreLaunchConfig() != null)
				{
					productCodeToCount.put(tempOrderEntry.getProduct().getCode(),
							Integer.valueOf(tempOrderEntry.getQuantity().intValue()));
				}
			}
		}

		return productCodeToCount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkKitProductByType(final ProductModel product, final AmwayKitProductType kitProductType)
	{
		validateParameterNotNullStandardMessage(PRODUCT, product);
		validateParameterNotNullStandardMessage(KIT_TYPE, kitProductType);

		if ((product instanceof AmwayKitProductModel) && (kitProductType.equals(((AmwayKitProductModel) product).getType())))
		{
			return Boolean.TRUE.booleanValue();
		}
		return Boolean.FALSE.booleanValue();
	}

	/**
	 * Get Amway Account Code with affiliate number and Amway Account
	 *
	 * @param amwayAccount
	 * @return Affiliate and Amway account number string
	 */
	protected String getNormalizedAmwayAccountCode(final AmwayAccountModel amwayAccount)
	{
		return amwayAccount.getControllingAffiliate().getAffiliateNumber() + "-" + amwayAccount.getCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validateOmsCode(final String[] splitOmsCode, final int[] omsparams)
	{
		validateParameterNotNullStandardMessage(OMS_PARAMS, omsparams);

		return (null != splitOmsCode) && (splitOmsCode.length == omsparams[0])
				&& (StringUtils.isNotBlank(splitOmsCode[omsparams[1]])) && (StringUtils.isNotBlank(splitOmsCode[omsparams[2]]));
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
