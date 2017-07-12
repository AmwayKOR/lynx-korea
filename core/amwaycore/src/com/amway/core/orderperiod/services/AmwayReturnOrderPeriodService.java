package com.amway.core.orderperiod.services;

import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import com.amway.core.model.AmwayOrderPeriodModel;


/**
 * Interface for Return order period service.
 *
 * @param <op>
 * @param <ret>
 */
public interface AmwayReturnOrderPeriodService<op extends AmwayOrderPeriodModel, ret extends ReturnRequestModel>
{
	/**
	 * Assigns a active order period available for the base store associated to the site for which the order is placed.
	 *
	 * @param order         order for which the order period has to be associated
	 * @param returnRequest
	 * @throws UnknownIdentifierException if no bonus period is available for the give site. *
	 */
	void assignOrderPeriod(ret returnRequest) throws BusinessException;

	/**
	 * Assigns the provided order period available for the base store associated to the site for which the order is
	 * placed.
	 *
	 * @param order         order for which the order period has to be associated
	 * @param orderPeriod   order period that has to be associated to the order
	 * @param returnRequest
	 * @throws IllegalArgumentException if the provided order period is not active for the given site. *
	 */
	void assignOrderPeriod(ret returnRequest, op orderPeriod) throws BusinessException;

	/**
	 * to find all active order period for base site.
	 *
	 * @return List<AmwayOrderPeriodModel>
	 */
	List<AmwayOrderPeriodModel> findAllActiveOrderPeriodsForSite();
}
