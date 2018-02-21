/**
 *
 */
package com.amway.core.bonusperiod.services;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.List;

import com.amway.core.model.AmwayBonusPeriodModel;


/**
 * Interface dedicated for assigning of {@link AmwayBonusPeriodModel} to {@link AbstractOrderModel}
 *
 * @param <bp>
 * @param <o>
 */
public interface AmwayBonusPeriodService<bp extends AmwayBonusPeriodModel, o extends AbstractOrderModel>
{
	/**
	 * Assigns a active bonus period available for the base store associated to the site for which the order is placed.
	 *
	 * @param order order for which the bonus period has to be associated
	 * @throws UnknownIdentifierException if no bonus period is available for the give site. *
	 */
	void assignBonusPeriod(o order) throws BusinessException;

	/**
	 * Assigns a active bonus period available for the base store associated to the site for which the order is placed.
	 *
	 * @param order      order for which the bonus period has to be associated
	 * @param forceReset
	 * @throws UnknownIdentifierException if no bonus period is available for the give site. *
	 */
	void assignBonusPeriod(o order, boolean forceReset) throws BusinessException;

	/**
	 * Assigns the provided bonus period available for the base store associated to the site for which the order is
	 * placed.
	 *
	 * @param order       order for which the bonus period has to be associated
	 * @param bonusPeriod bonus period that has to be associated to the order
	 * @throws IllegalArgumentException if the provided bonus period is not active for the given site. *
	 */
	void assignBonusPeriod(o order, bp bonusPeriod) throws BusinessException;

	/**
	 * Find all active bonus periods for the base site.
	 *
	 * @return AmwayBonusPeriodModel
	 */
	List<AmwayBonusPeriodModel> findAllActiveBonusPeriodsForSite();

	/**
	 * Final all bonus periods for the base site.
	 *
	 * @return AmwayBonusPeriodModel
	 */
	List<AmwayBonusPeriodModel> findAllBonusPeriodsForSite();
}
