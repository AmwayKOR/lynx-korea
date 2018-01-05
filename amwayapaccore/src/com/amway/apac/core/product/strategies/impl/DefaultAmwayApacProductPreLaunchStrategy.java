package com.amway.apac.core.product.strategies.impl;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.Date;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.account.service.AmwayApacAccountClassificationService;
import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.apac.core.model.AmwayPreLaunchConfigModel;
import com.amway.apac.core.product.AmwayPreLaunchResponse;
import com.amway.apac.core.product.AmwayProductPreLaunchStatus;
import com.amway.apac.core.product.strategies.AmwayApacProductPreLaunchStrategy;


/**
 * Default implementation for the {@link AmwayApacProductPreLaunchStrategy}.
 *
 * @author Shubham Goyal
 */
public class DefaultAmwayApacProductPreLaunchStrategy implements AmwayApacProductPreLaunchStrategy
{
	private AmwayApacAccountClassificationService amwayApacAccountClassificationService;

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
						response.setAllowedQuantity(getOrderableQuantityForCurrentUserInPreLaunch(preLaunchConfig));
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
	protected Integer getOrderableQuantityForCurrentUserInPreLaunch(final AmwayPreLaunchConfigModel preLaunchConfig)
	{
		return preLaunchConfig.getMaxShoppingCount();
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

}
