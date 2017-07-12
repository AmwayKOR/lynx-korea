package com.amway.core.orderperiod.services.impl;

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
import java.util.List;

import org.apache.log4j.Logger;

import com.amway.core.enums.AmwayPeriodTypeEnum;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.core.orderperiod.dao.AmwayOrderPeriodDao;
import com.amway.core.orderperiod.services.AmwayReturnOrderPeriodService;


/**
 * Default Implementation
 *
 * @param <op>
 * @param <ret>
 */
public class DefaultAmwayReturnOrderPeriodService<op extends AmwayOrderPeriodModel, ret extends ReturnRequestModel>
		extends AbstractBusinessService implements AmwayReturnOrderPeriodService<op, ret>
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayReturnOrderPeriodService.class);
	private AmwayOrderPeriodDao amwayOrderPeriodDao;
	private CMSSiteService cmsSiteService;

	/**
	 * Assigns a active order period available for the base store associated to the site for which the order is placed.
	 *
	 * @param returnRequest
	 * @throws BusinessException
	 */
	@Override
	public void assignOrderPeriod(final ret returnRequest) throws BusinessException
	{
		if (isPeriodSet(returnRequest))
		{
			return;
		}
		final AmwayOrderPeriodModel currentOrderPeriod = getCurrentOrderPeriod(returnRequest);
		if (currentOrderPeriod == null)
		{
			throw new BusinessException(
					"There is no active order period for the given order \"" + returnRequest.getOrder().getOrderName() + "\"");
		}
		assignPeriod(returnRequest, (op) currentOrderPeriod);
	}

	/**
	 * Assigns the provided order period available for the base store associated to the site for which the order is
	 * placed.
	 *
	 * @param returnRequest
	 * @param orderPeriod
	 * @throws BusinessException
	 */
	@Override
	public void assignOrderPeriod(final ret returnRequest, final op orderPeriod) throws BusinessException
	{
		if (isPeriodSet(returnRequest))
		{
			return;
		}
		validateParameterNotNullStandardMessage("order", returnRequest);
		validateParameterNotNullStandardMessage("order period", orderPeriod);
		if (!AmwayPeriodTypeEnum.ACTIVE.equals(orderPeriod.getStatus()))
		{
			throw new IllegalArgumentException("Invalid order period. Should be active");
		}
		if (!isInsidePeriod(returnRequest, orderPeriod))
		{
			throw new BusinessException("Order \"" + returnRequest.getOrder().getOrderName() + "\" date is outside order period");
		}
		assignPeriod(returnRequest, orderPeriod);
	}

	private void assignPeriod(final ret returnRequest, final op orderPeriod)
	{
		returnRequest.setReturnPeriod(orderPeriod);
		getModelService().save(returnRequest);
	}

	/**
	 * to find all active order period for base site.
	 *
	 * @return List<AmwayOrderPeriodModel>
	 */
	@Override
	public List<AmwayOrderPeriodModel> findAllActiveOrderPeriodsForSite()
	{
		final BaseSiteModel site = getCmsSiteService().getCurrentSite();
		return findAllActiveOrderPeriodsForSite(site);
	}

	private List<AmwayOrderPeriodModel> findAllActiveOrderPeriodsForSite(final BaseSiteModel site)
	{
		return amwayOrderPeriodDao.findActiveOrderPeriods(Arrays.asList(site));
	}

	/**
	 * @return the cmsSiteService
	 */
	public CMSSiteService getCmsSiteService()
	{
		return cmsSiteService;
	}

	/**
	 * Check if order has already been assigned to a order period. This can happen from cscokpit.
	 */
	private boolean isPeriodSet(final ret returnRequest)
	{
		if (returnRequest.getReturnPeriod() != null)
		{
			LOG.warn("Order has already been assigned a order period.");
			return true;
		}
		return false;
	}

	private boolean isInsidePeriod(final ret returnRequest, final op orderPeriod)
	{
		return returnRequest.getDate().compareTo(orderPeriod.getStartDate()) >= 0
				&& returnRequest.getDate().compareTo(orderPeriod.getEndDate()) <= 0;
	}

	private List<AmwayOrderPeriodModel> getOrderPeriods(final ret returnRequest)
	{
		validateParameterNotNullStandardMessage("order", returnRequest);
		final BaseSiteModel site = returnRequest.getOrder().getSite();
		validateParameterNotNullStandardMessage("site", site);

		final List<AmwayOrderPeriodModel> orderPeriods = findAllActiveOrderPeriodsForSite(site);
		ServicesUtil.validateIfAnyResult(orderPeriods, "no valid order periods found for this site");
		final List<AmwayOrderPeriodModel> sortedPeriods = new ArrayList<>(orderPeriods);
		Collections.sort(sortedPeriods, new Comparator<AmwayOrderPeriodModel>()
		{
			@Override
			public int compare(final AmwayOrderPeriodModel orderPeriod1, final AmwayOrderPeriodModel orderPeriod2)
			{
				//ATODO: comparator logic? (the same question as for bonus periods)
				return orderPeriod1.getStartDate().compareTo(orderPeriod2.getStartDate());
			}
		});
		return sortedPeriods;
	}

	private op getCurrentOrderPeriod(final ret returnRequest)
	{
		for (final AmwayOrderPeriodModel orderPeriod : getOrderPeriods(returnRequest))
		{
			if (isInsidePeriod(returnRequest, (op) orderPeriod))
			{
				return (op) orderPeriod;
			}
		}
		return null;
	}

	/**
	 * @param cmsSiteService the cmsSiteService to set
	 */
	public void setCmsSiteService(final CMSSiteService cmsSiteService)
	{
		this.cmsSiteService = cmsSiteService;
	}

	/**
	 * @return amwayOrderPeriodDao
	 */
	public AmwayOrderPeriodDao getAmwayOrderPeriodDao()
	{
		return amwayOrderPeriodDao;
	}

	/**
	 * @param amwayOrderPeriodDao the amwayOrderPeriodDao to set
	 */
	public void setAmwayOrderPeriodDao(final AmwayOrderPeriodDao amwayOrderPeriodDao)
	{
		this.amwayOrderPeriodDao = amwayOrderPeriodDao;
	}
}
