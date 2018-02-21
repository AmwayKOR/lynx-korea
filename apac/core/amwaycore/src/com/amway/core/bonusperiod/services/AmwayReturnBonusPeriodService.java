package com.amway.core.bonusperiod.services;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import com.amway.core.model.AmwayBonusPeriodModel;


/**
 * Interface dedicated for assigning of {@link AmwayBonusPeriodModel} to {@link AbstractOrderModel}
 *
 * @param <bp>
 * @param <ret>
 */
public interface AmwayReturnBonusPeriodService<bp extends AmwayBonusPeriodModel, ret extends ReturnRequestModel>
{
	/**
	 * Assigns a active bonus period available for the base store associated to the site for which the order is placed.
	 *
	 * @param returnRequest         order for which the bonus period has to be associated
	 * @param returnRequest
	 * @throws UnknownIdentifierException if no bonus period is available for the give site. *
	 */
	void assignBonusPeriod(ret returnRequest) throws BusinessException;

	/**
	 * Assigns a active bonus period available for the base store associated to the site which is specified as the parameter.
	 *
	 * @param returnRequest         order for which the bonus period has to be associated
	 * @param returnSite            site where the return should be processed
	 *
	 * @throws UnknownIdentifierException if no bonus period is available for the give site. *
	 */
	void assignBonusPeriod(ret returnRequest, BaseSiteModel returnSite) throws BusinessException;

	/**
	 * Assigns the provided bonus period available for the base store associated to the site for which the order is
	 * placed.
	 *
	 * @param returnRequest         order for which the bonus period has to be associated
	 * @param bonusPeriod   bonus period that has to be associated to the order
	 * @param returnRequest
	 * @throws IllegalArgumentException if the provided bonus period is not active for the given site. *
	 */
	void assignBonusPeriod(ret returnRequest, bp bonusPeriod) throws BusinessException;

	/**
	 * Find all active bonus periods for base site.
	 *
	 * @return AmwayBonusPeriodModel
	 */
	List<AmwayBonusPeriodModel> findAllActiveBonusPeriodsForSite();
}
