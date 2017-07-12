/**
 *
 */
package com.amway.core.orderperiod.dao;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;

import java.util.Collection;
import java.util.List;

import com.amway.core.model.AmwayOrderPeriodModel;



/**
 * Data access to {@link com.amway.core.model.AmwayOrderPeriodModel}
 */
public interface AmwayOrderPeriodDao
{
	/**
	 * find all active order periods for the given list of sites
	 *
	 * @param sites the sites to look order periods for
	 * @return the list of order periods
	 */
	List<AmwayOrderPeriodModel> findActiveOrderPeriods(Collection<BaseSiteModel> sites);

	/**
	 * To find order periods.
	 *
	 * @return the list of order periods.
	 */
	List<AmwayOrderPeriodModel> findOrderPeriods();
}
