package com.amway.core.bonusperiod.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.returns.model.ReturnOrderModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.amway.core.bonusperiod.services.AmwayBonusPeriodService;
import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayBonusPeriodModel;
import com.amway.core.orderperiod.dao.AmwayBonusPeriodDao;


/**
 * Default Implementation for Amway Bonus Period.
 *
 * @param <bp>
 * @param <o>
 */
public class DefaultAmwayBonusPeriodService<bp extends AmwayBonusPeriodModel, o extends AbstractOrderModel>
		extends AbstractBusinessService implements AmwayBonusPeriodService<bp, o>
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayBonusPeriodService.class);
	private AmwayBonusPeriodDao amwayBonusPeriodDao;
	private CMSSiteService cmsSiteService;
	private UserService userService;

	/**
	 * {@link #assignBonusPeriod(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 *
	 * @throws UnknownIdentifierException if no bonus period is available for the give site. *
	 */
	@Override
	public void assignBonusPeriod(final o order) throws BusinessException
	{
		assignBonusPeriod(order, false);
	}

	/**
	 * {@link #assignBonusPeriod(de.hybris.platform.core.model.order.AbstractOrderModel, boolean)}
	 *
	 * @throws UnknownIdentifierException if no bonus period is available for the give site. *
	 */
	@Override
	public void assignBonusPeriod(final o order, final boolean forceReset) throws BusinessException
	{
		if (!forceReset && isPeriodSet(order))
		{
			return;
		}
		final AmwayBonusPeriodModel currentBonusPeriod = getCurrentBonusPeriod(order);
		if (currentBonusPeriod == null)
		{
			throw new BusinessException("There is no active period for order \"" + order.getCode() + "\"");
		}
		assignPeriod(order, (bp) currentBonusPeriod);
	}

	/**
	 * {@link #assignPeriod(de.hybris.platform.core.model.order.AbstractOrderModel, com.amway.core.model.AmwayBonusPeriodModel)}
	 *
	 * @throws IllegalArgumentException if the provided bonus period is not active for the given site. *
	 */
	@Override
	public void assignBonusPeriod(final o order, final bp bonusPeriod) throws BusinessException
	{
		if (isPeriodSet(order))
		{
			return;
		}
		validateParameterNotNullStandardMessage("order", order);
		validateParameterNotNullStandardMessage("bonus period", bonusPeriod);
		if (!AmwayPeriodTypeEnum.ACTIVE.equals(bonusPeriod.getStatus()))
		{
			throw new IllegalArgumentException("Invalid bonus period. Should be active");
		}
		if (!isOrderDateInsidePeriod(order, bonusPeriod))
		{
			throw new BusinessException("Order \"" + order.getCode() + "\" date is outside the passed bonus period");
		}
		assignPeriod(order, bonusPeriod);
	}

	private void assignPeriod(final o order, final bp bonusPeriod) throws BusinessException
	{
		AmwayBonusPeriodModel periodToSet = bonusPeriod;
		if (order instanceof ReturnOrderModel && isPeriodAfterCutOffDate(order, bonusPeriod))
		{
			periodToSet = getNextBonusPeriod(order, bonusPeriod);
			if (periodToSet == null)
			{
				throw new BusinessException("The next available bonus period does not exists for order \"" + order.getCode() + "\"");
			}
		}
		order.setBonusPeriod(periodToSet);
		getModelService().save(order);
	}

	/**
	 * {@link #findAllActiveBonusPeriodsForSite()}
	 */
	@Override
	public List<AmwayBonusPeriodModel> findAllActiveBonusPeriodsForSite()
	{
		final BaseSiteModel site = getCmsSiteService().getCurrentSite();
		return findAllActiveBonusPeriodsForSite(site);
	}

	/**
	 * {@link #findAllActiveBonusPeriodsForSite(de.hybris.platform.basecommerce.model.site.BaseSiteModel)}
	 *
	 * @param site
	 * @return AmwayBonusPeriodDao
	 */
	private List<AmwayBonusPeriodModel> findAllActiveBonusPeriodsForSite(final BaseSiteModel site)
	{
		return getAmwayBonusPeriodDao().findActiveBonusPeriods(Arrays.asList(site));
	}

	/**
	 * {@link #findAllBonusPeriodsForSite()}
	 *
	 * @return AmwayBonusPeriodDao
	 */
	@Override
	public List<AmwayBonusPeriodModel> findAllBonusPeriodsForSite()
	{
		return getAmwayBonusPeriodDao().findBonusPeriods();
	}


	/**
	 * {@link #findAllBonusPeriodsForSiteByPeriod(de.hybris.platform.commerceservices.search.pagedata.PageableData, java.util.List)}
	 *
	 * @param pageableData
	 * @param dates
	 * @return AmwayBonusPeriodDao
	 */
	public SearchPageData<AmwayBonusPeriodModel> findAllBonusPeriodsForSiteByPeriod(final PageableData pageableData,
			final List<Date> dates)
	{
		return getAmwayBonusPeriodDao().findAllBonusPeriodsForSiteByPeriod(pageableData, dates, getUserService().getCurrentUser());
	}


	protected AmwayBonusPeriodDao getAmwayBonusPeriodDao()
	{
		return amwayBonusPeriodDao;
	}

	/**
	 * @param amwayBonusPeriodDao the amwayBonusPeriodDao to set
	 */
	public void setAmwayBonusPeriodDao(final AmwayBonusPeriodDao amwayBonusPeriodDao)
	{
		this.amwayBonusPeriodDao = amwayBonusPeriodDao;
	}

	protected CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	/**
	 * @param cmsSiteService the cmsSiteService to set
	 */
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/*
	 * Check if order has already been assigned to a bonus period. This can happen from cscokpit.
	 */
	private boolean isPeriodSet(final o order)
	{
		if (order.getBonusPeriod() != null)
		{
			LOG.warn("Order has already been assigned a bonus period.");
			return true;
		}
		return false;
	}

	private boolean isOrderDateInsidePeriod(final o order, final bp bonusPeriod)
	{
		//comparing the current date instead of the order date
		final Date currentDate = Calendar.getInstance(TimeZone.getTimeZone(order.getSite().getTimeZone())).getTime();
		return currentDate.compareTo(bonusPeriod.getStartDate()) >= 0 && currentDate.compareTo(bonusPeriod.getEndDate()) <= 0;
	}

	private boolean isPeriodAfterCutOffDate(final o order, final bp bonusPeriod)
	{
		//comparing the current date instead of the order date
		final Date currentDate = Calendar.getInstance(TimeZone.getTimeZone(order.getSite().getTimeZone())).getTime();
		return currentDate.compareTo(bonusPeriod.getCutoffDate()) > 0;
	}

	private bp getCurrentBonusPeriod(final o order)
	{
		for (final AmwayBonusPeriodModel bonusPeriod : getBonusPeriods(order))
		{
			if (isOrderDateInsidePeriod(order, (bp) bonusPeriod))
			{
				return (bp) bonusPeriod;
			}
		}
		return null;
	}

	private bp getNextBonusPeriod(final o order, final bp bonusPeriod)
	{
		final Iterator iterator = getBonusPeriods(order).iterator();
		while (iterator.hasNext())
		{
			if (bonusPeriod.equals(iterator.next()) && iterator.hasNext())
			{
				return (bp) iterator.next();
			}
		}
		return null;
	}

	private List<AmwayBonusPeriodModel> getBonusPeriods(final o order)
	{
		validateParameterNotNullStandardMessage("order", order);
		final BaseSiteModel site = order.getSite();
		validateParameterNotNullStandardMessage("site", site);

		final List<AmwayBonusPeriodModel> bonusPeriods = findAllActiveBonusPeriodsForSite(site);
		ServicesUtil.validateIfAnyResult(bonusPeriods, "no valid bonus periods found for this site");
		final List<AmwayBonusPeriodModel> sortedPeriods = new ArrayList<>(bonusPeriods);
		Collections.sort(sortedPeriods, new Comparator<AmwayBonusPeriodModel>()
		{
			@Override
			public int compare(final AmwayBonusPeriodModel bonusPeriod1, final AmwayBonusPeriodModel bonusPeriod2)
			{
				//ATODO: I need to sort bonusPeriods because I think the order is not guaranteed by getAmwayBonusPeriodDao().findActiveBonusPeriods(..)
				//ATODO: My comparator logic is just to compare start dates, would it be OK for now?
				//
				return bonusPeriod1.getStartDate().compareTo(bonusPeriod2.getStartDate());
			}
		});
		return sortedPeriods;
	}

	/**
	 * @return userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}


}
