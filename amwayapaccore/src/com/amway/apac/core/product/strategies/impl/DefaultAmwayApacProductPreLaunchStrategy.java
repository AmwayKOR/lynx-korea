package com.amway.apac.core.product.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.store.services.BaseStoreService;

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
		final AmwayPreLaunchResponse response = new AmwayPreLaunchResponse();
		response.setPreLaunchStatus(AmwayProductPreLaunchStatus.NOT_IN_PRELAUNCH);
		response.setAllowedQuantity(product.getMaxOrderQuantity());

		if (null != preLaunchConfig)
		{
			final Date currentDate = new Date();
			if (currentDate.before(preLaunchConfig.getStartDate()))
			{
				response.setPreLaunchStatus(AmwayProductPreLaunchStatus.NOT_YET_LAUNCHED);
				response.setAllowedQuantity(AmwayapacCoreConstants.ZERO_INT);
			}
			else if ((currentDate.after(preLaunchConfig.getStartDate())) && (currentDate.before(preLaunchConfig.getEndDate())))
			{
				response.setPreLaunchStatus(AmwayProductPreLaunchStatus.IN_PRE_LAUNCH_PERIOD);
				response.setAllowedQuantity(AmwayapacCoreConstants.ZERO_INT);
				if (getAmwayApacAccountClassificationService().checkUserClassification(preLaunchConfig.getClassification()))
				{
					if (preLaunchConfig.getMaxShoppingCount().intValue() < 0)
					{
						response.setAllowedQuantity(product.getMaxOrderQuantity());
					}
					else
					{
						response.setAllowedQuantity(getOrderableQuantityForCurrentUserInPreLaunch(product));
					}
				}
			}
		}
		return response;
	}

	/**
	 * Returns the current orderable quantity for given preLaunchConfig and current user evaluated by comparing the max
	 * count of preLaunchConfig and the count of units already ordered.
	 *
	 * @param preLaunchConfig
	 * @return
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
