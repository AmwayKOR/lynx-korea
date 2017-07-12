package com.amway.core.orderperiod.services;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import com.amway.core.model.AmwayOrderPeriodModel;


/**
 * Interface dedicated for assigning of {@link AmwayOrderPeriodModel} to {@link AbstractOrderModel}
 *
 * @param <op>
 * @param <o>
 */
public interface AmwayOrderPeriodService<op extends AmwayOrderPeriodModel, o extends AbstractOrderModel>
{
	/**
	 * Assigns a active order period available for the base store associated to the site for which the order is placed.
	 *
	 * @param order order for which the order period has to be associated
	 * @throws UnknownIdentifierException if no bonus period is available for the give site. *
	 */
	void assignOrderPeriod(o order) throws BusinessException;

	/**
	 * Assigns the provided order period available for the base store associated to the site for which the order is
	 * placed.
	 *
	 * @param order       order for which the order period has to be associated
	 * @param orderPeriod order period that has to be associated to the order
	 * @throws IllegalArgumentException if the provided order period is not active for the given site. *
	 */
	void assignOrderPeriod(o order, op orderPeriod) throws BusinessException;

	/**
	 * To find all active order period for base site.
	 *
	 * @return List<AmwayOrderPeriodModel>
	 */
	List<AmwayOrderPeriodModel> findAllActiveOrderPeriodsForSite();

	/**
	 * To find all order periods for base site.
	 *
	 * @return List<AmwayOrderPeriodModel>
	 */
	List<AmwayOrderPeriodModel> findAllOrderPeriodsForSite();
}
