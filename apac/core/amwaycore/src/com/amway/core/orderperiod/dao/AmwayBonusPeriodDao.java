/**
 *
 */
package com.amway.core.orderperiod.dao;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.user.UserModel;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.amway.core.model.AmwayBonusPeriodModel;



/**
 * Data access to {@link com.amway.core.model.AmwayBonusPeriodModel}
 */
public interface AmwayBonusPeriodDao
{
	/**
	 * find all active bonus periods for the given list of sites.
	 *
	 * @param sites the sites to look bonus periods for
	 * @return the list of bonus periods
	 */
	List<AmwayBonusPeriodModel> findActiveBonusPeriods(Collection<BaseSiteModel> sites);

	/**
	 * find bonus periods.
	 *
	 * @return the list of bonus periods
	 */
	List<AmwayBonusPeriodModel> findBonusPeriods();

	/**
	 * find all bonus periods for the site.
	 *
	 * @param pageableData
	 * @param dates
	 * @param userModel
	 * @return SearchPageData<AmwayBonusPeriodModel>
	 */
	public SearchPageData<AmwayBonusPeriodModel> findAllBonusPeriodsForSiteByPeriod(PageableData pageableData, List<Date> dates,
			UserModel userModel);
}
