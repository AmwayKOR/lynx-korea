package com.amway.core.bonusperiod.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.exceptions.BusinessException;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.amway.core.bonusperiod.services.AmwayReturnBonusPeriodService;
import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayBonusPeriodModel;
import com.amway.core.orderperiod.dao.AmwayBonusPeriodDao;


/**
 * Default Implementation for Amway Return Bonus Period
 *
 * @param <bp>
 * @param <ret>
 */
public class DefaultAmwayReturnBonusPeriodService<bp extends AmwayBonusPeriodModel, ret extends ReturnRequestModel>
		extends AbstractBusinessService implements AmwayReturnBonusPeriodService<bp, ret>
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayReturnBonusPeriodService.class);
	private AmwayBonusPeriodDao amwayBonusPeriodDao;
	private CMSSiteService cmsSiteService;

	/**
	 * {@link #assignBonusPeriod(de.hybris.platform.returns.model.ReturnRequestModel)}
	 */
	@Override
	public void assignBonusPeriod(final ret returnRequest) throws BusinessException
	{
		if (isPeriodSet(returnRequest))
		{
			return;
		}
		final AmwayBonusPeriodModel currentBonusPeriod = getCurrentBonusPeriod(returnRequest);
		if (currentBonusPeriod == null)
		{
			throw new BusinessException("There is no active period for order \"" + returnRequest.getOrder().getCode() + "\"");
		}
		assignPeriod(returnRequest, (bp) currentBonusPeriod);
	}

	/**
	 * {@link #assignBonusPeriod(de.hybris.platform.returns.model.ReturnRequestModel, com.amway.core.model.AmwayBonusPeriodModel)}
	 */
	@Override
	public void assignBonusPeriod(final ret returnRequest, final bp bonusPeriod) throws BusinessException
	{
		if (isPeriodSet(returnRequest))
		{
			return;
		}
		validateParameterNotNullStandardMessage("order", returnRequest);
		validateParameterNotNullStandardMessage("bonus period", bonusPeriod);
		if (!AmwayPeriodTypeEnum.ACTIVE.equals(bonusPeriod.getStatus()))
		{
			throw new IllegalArgumentException("Invalid bonus period. Should be active");
		}
		if (!isInsidePeriod(returnRequest, bonusPeriod))
		{
			throw new BusinessException(
					"Order \"" + returnRequest.getOrder().getCode() + "\" date is outside the passed bonus period");
		}
		assignPeriod(returnRequest, bonusPeriod);
	}

	private void assignPeriod(final ret returnRequest, final bp bonusPeriod) throws BusinessException
	{
		if (!afterCutoffDate(returnRequest, bonusPeriod))
		{
			returnRequest.setReturnBonusPeriod(bonusPeriod);
		}
		else
		{
			final AmwayBonusPeriodModel nextBonusPeriod = getNextBonusPeriod(returnRequest, bonusPeriod);
			if (nextBonusPeriod == null)
			{
				throw new BusinessException(
						"The next available bonus period does not exists for order \"" + returnRequest.getOrder().getCode() + "\"");
			}
			returnRequest.setReturnBonusPeriod(nextBonusPeriod);
		}
		getModelService().save(returnRequest);
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

	private List<AmwayBonusPeriodModel> findAllActiveBonusPeriodsForSite(final BaseSiteModel site)
	{
		return getAmwayBonusPeriodDao().findActiveBonusPeriods(Arrays.asList(site));
	}

	/**
	 * @return amwayBonusPeriodDao
	 */
	public AmwayBonusPeriodDao getAmwayBonusPeriodDao()
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

	/**
	 * @return cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
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
	private boolean isPeriodSet(final ret returnRequest)
	{
		if (returnRequest.getReturnBonusPeriod() != null)
		{
			LOG.warn("Order has already been assigned a bonus period.");
			return true;
		}
		return false;
	}

	private boolean isInsidePeriod(final ret returnRequest, final bp bonusPeriod)
	{
		return returnRequest.getDate().compareTo(bonusPeriod.getStartDate()) >= 0
				&& returnRequest.getDate().compareTo(bonusPeriod.getEndDate()) <= 0;
	}

	private boolean afterCutoffDate(final ret returnRequest, final bp bonusPeriod)
	{
		//ATODO: include cutoffdate?
		return returnRequest.getConsolidateDate().compareTo(bonusPeriod.getCutoffDate()) > 0;
	}

	private bp getCurrentBonusPeriod(final ret returnRequest)
	{
		for (final AmwayBonusPeriodModel bonusPeriod : getBonusPeriods(returnRequest))
		{
			if (isInsidePeriod(returnRequest, (bp) bonusPeriod))
			{
				return (bp) bonusPeriod;
			}
		}
		return null;
	}

	private bp getNextBonusPeriod(final ret returnRequest, final bp bonusPeriod)
	{
		final Iterator iterator = getBonusPeriods(returnRequest).iterator();
		while (iterator.hasNext())
		{
			if (bonusPeriod.equals(iterator.next()) && iterator.hasNext())
			{
				return (bp) iterator.next();
			}
		}
		return null;
	}

	private List<AmwayBonusPeriodModel> getBonusPeriods(final ret returnRequest)
	{
		validateParameterNotNullStandardMessage("returns", returnRequest);
		final BaseSiteModel site = returnRequest.getOrder().getSite();
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
				return bonusPeriod1.getStartDate().compareTo(bonusPeriod2.getStartDate());
			}
		});
		return sortedPeriods;
	}

}
