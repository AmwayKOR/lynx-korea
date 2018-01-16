package com.amway.apac.core.product.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.service.AmwayApacAccountClassificationService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.model.AmwayPreLaunchConfigModel;
import com.amway.apac.core.product.AmwayPreLaunchResponse;
import com.amway.apac.core.product.AmwayProductPreLaunchStatus;
import com.amway.apac.core.product.services.AmwayApacProductService;
import com.amway.apac.core.product.strategies.AmwayApacProductPreLaunchStrategy;
import com.amway.core.model.AmwayAccountModel;
import com.amway.core.service.AmwayAccountCommerceService;


/**
 * Default implementation for the {@link AmwayApacProductPreLaunchStrategy}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacProductPreLaunchStrategy implements AmwayApacProductPreLaunchStrategy
{
	private AmwayApacAccountClassificationService amwayApacAccountClassificationService;
	private AmwayApacProductService amwayApacProductService;
	private AmwayAccountCommerceService amwayAccountCommerceService;
	private BaseStoreService baseStoreService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayPreLaunchResponse getProductPrelaunchStatusForCurrentUser(final ProductModel product)
	{
		final AmwayPreLaunchConfigModel preLaunchConfig = product.getPreLaunchConfig();
		AmwayProductPreLaunchStatus preLaunchStatus = AmwayProductPreLaunchStatus.NOT_IN_PRELAUNCH;
		Integer allowedQuantity = product.getMaxOrderQuantity();

		if (null != preLaunchConfig)
		{
			final Date currentDate = Calendar.getInstance().getTime();
			if (currentDate.before(preLaunchConfig.getStartDate()))
			{
				preLaunchStatus = AmwayProductPreLaunchStatus.NOT_YET_LAUNCHED;
				allowedQuantity = AmwayapacCoreConstants.ZERO_INTEGER;
			}
			else if ((currentDate.after(preLaunchConfig.getStartDate())) && (currentDate.before(preLaunchConfig.getEndDate())))
			{
				preLaunchStatus = AmwayProductPreLaunchStatus.IN_PRE_LAUNCH_PERIOD;
				allowedQuantity = AmwayapacCoreConstants.ZERO_INTEGER;
				if (getAmwayApacAccountClassificationService().checkUserClassification(preLaunchConfig.getClassification()))
				{
					if (preLaunchConfig.getMaxShoppingCount().intValue() < 0)
					{
						allowedQuantity = product.getMaxOrderQuantity();
					}
					else
					{
						allowedQuantity = getOrderableQuantityForCurrentUserInPreLaunch(product);
					}
				}
			}
		}

		return createPreLaunchResponse(preLaunchStatus, allowedQuantity);
	}

	/**
	 * Creates and returns {@link AmwayPreLaunchResponse} for given preLaunchStatus and allowedQuantity.
	 *
	 * @param preLaunchStatus
	 * @param allowedQuantity
	 * @return AmwayPreLaunchResponse
	 */
	protected AmwayPreLaunchResponse createPreLaunchResponse(final AmwayProductPreLaunchStatus preLaunchStatus,
			final Integer allowedQuantity)
	{
		final AmwayPreLaunchResponse response = new AmwayPreLaunchResponse();
		response.setPreLaunchStatus(preLaunchStatus);
		response.setAllowedQuantity(allowedQuantity);
		return response;
	}

	/**
	 * Returns the current orderable quantity for given preLaunchConfig and current user evaluated by comparing the max
	 * count of preLaunchConfig and the count of units already ordered.
	 *
	 * @param product
	 *           Prelaunch product
	 * @return Orderable quantity for the product
	 */
	protected Integer getOrderableQuantityForCurrentUserInPreLaunch(final ProductModel product)
	{
		final AmwayAccountModel currentAccount = getAmwayAccountCommerceService().getCurrentAccount();
		final int usedQuantity = getAmwayApacProductService().getUsedQuantityForPrelaunch(currentAccount.getCode(),
				product.getCode(), getBaseStoreService().getCurrentBaseStore());

		return Integer.valueOf(product.getPreLaunchConfig().getMaxShoppingCount().intValue() - usedQuantity);
	}

	/**
	 * @return the amwayApacAccountClassificationService
	 */
	public AmwayApacAccountClassificationService getAmwayApacAccountClassificationService()
	{
		return amwayApacAccountClassificationService;
	}

	/**
	 * @param amwayApacAccountClassificationService
	 *           the amwayApacAccountClassificationService to set
	 */
	@Required
	public void setAmwayApacAccountClassificationService(
			final AmwayApacAccountClassificationService amwayApacAccountClassificationService)
	{
		this.amwayApacAccountClassificationService = amwayApacAccountClassificationService;
	}

	/**
	 * @return the amwayApacProductService
	 */
	public AmwayApacProductService getAmwayApacProductService()
	{
		return amwayApacProductService;
	}

	/**
	 * @param amwayApacProductService
	 *           the amwayApacProductService to set
	 */
	@Required
	public void setAmwayApacProductService(final AmwayApacProductService amwayApacProductService)
	{
		this.amwayApacProductService = amwayApacProductService;
	}

	/**
	 * @return the amwayAccountCommerceService
	 */
	public AmwayAccountCommerceService getAmwayAccountCommerceService()
	{
		return amwayAccountCommerceService;
	}

	/**
	 * @param amwayAccountCommerceService
	 *           the amwayAccountCommerceService to set
	 */
	@Required
	public void setAmwayAccountCommerceService(final AmwayAccountCommerceService amwayAccountCommerceService)
	{
		this.amwayAccountCommerceService = amwayAccountCommerceService;
	}

	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

}
