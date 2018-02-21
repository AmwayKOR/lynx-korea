package com.amway.core.orderperiod.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.core.model.order.AbstractOrderModel;
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
import com.amway.core.los.data.OrderPeriodCloseResultData;
import com.amway.core.los.service.LosService;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.core.orderperiod.dao.AmwayOrderPeriodDao;
import com.amway.core.orderperiod.services.AmwayOrderPeriodService;


/**
 * Default Implementation for amway order period service.
 *
 * @param <op>
 * @param <o>
 */
public class DefaultAmwayOrderPeriodService<op extends AmwayOrderPeriodModel, o extends AbstractOrderModel>
		extends AbstractBusinessService implements AmwayOrderPeriodService<op, o>
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayOrderPeriodService.class);
	private AmwayOrderPeriodDao amwayOrderPeriodDao;
	private CMSSiteService cmsSiteService;
	private LosService<AmwayOrderPeriodModel, OrderPeriodCloseResultData> orderPeriodClosedService;

	/**
	 * Assigns a active order period available for the base store associated to the site for which the order is placed.
	 * <p/>
	 * {@link #assignOrderPeriod(de.hybris.platform.core.model.order.AbstractOrderModel)}
	 */
	@Override
	public void assignOrderPeriod(final o order) throws BusinessException
	{
		if (isPeriodSet(order))
		{
			return;
		}
		final AmwayOrderPeriodModel currentOrderPeriod = getCurrentOrderPeriod(order);
		if (currentOrderPeriod == null)
		{
			throw new BusinessException("There is no active order period for the given order \"" + order.getCode() + "\"");
		}
		assignPeriod(order, (op) currentOrderPeriod);
	}

	/**
	 * Assigns the provided order period available for the base store associated to the site for which the order is
	 * placed.
	 * <p/>
	 * {@link #assignOrderPeriod(de.hybris.platform.core.model.order.AbstractOrderModel, com.amway.core.model.AmwayOrderPeriodModel)}
	 */
	@Override
	public void assignOrderPeriod(final o order, final op orderPeriod) throws BusinessException
	{
		if (isPeriodSet(order))
		{
			return;
		}
		validateParameterNotNullStandardMessage("order", order);
		validateParameterNotNullStandardMessage("order period", orderPeriod);
		if (!AmwayPeriodTypeEnum.ACTIVE.equals(orderPeriod.getStatus()))
		{
			throw new IllegalArgumentException("Invalid order period. Should be active");
		}
		if (!isInsidePeriod(order, orderPeriod))
		{
			throw new BusinessException("Order \"" + order.getCode() + "\" date is outside order period");
		}
		assignPeriod(order, orderPeriod);
	}

	private void assignPeriod(final o order, final op orderPeriod)
	{
		order.setOrderPeriod(orderPeriod);
		getModelService().save(order);
	}

	/**
	 * To find all active order period for base site.
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
	 * To find all order periods for base site.
	 *
	 * @return List<AmwayOrderPeriodModel>
	 */
	@Override
	public List<AmwayOrderPeriodModel> findAllOrderPeriodsForSite()
	{
		return amwayOrderPeriodDao.findOrderPeriods();
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
	private boolean isPeriodSet(final o order)
	{
		if (order.getOrderPeriod() != null)
		{
			LOG.warn("Order has already been assigned a order period.");
			return true;
		}
		return false;
	}

	private boolean isInsidePeriod(final o order, final op orderPeriod)
	{
		return order.getDate().compareTo(orderPeriod.getStartDate()) >= 0
				&& order.getDate().compareTo(orderPeriod.getEndDate()) <= 0;
	}

	private List<AmwayOrderPeriodModel> getOrderPeriods(final o order)
	{
		validateParameterNotNullStandardMessage("order", order);
		final BaseSiteModel site = order.getSite();
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

	private op getCurrentOrderPeriod(final o order)
	{
		for (final AmwayOrderPeriodModel orderPeriod : getOrderPeriods(order))
		{
			if (isInsidePeriod(order, (op) orderPeriod))
			{
				return (op) orderPeriod;
			}
		}
		return null;
	}

	/**
	 * @param orderPeriod
	 */
	public void sendClosedOrderPeriodToMagic(final AmwayOrderPeriodModel orderPeriod)
	{
		final OrderPeriodCloseResultData orderPeriodClosedResultData = getOrderPeriodClosedService().process(orderPeriod);
		LOG.info("Closed order period decision : " + orderPeriodClosedResultData.getDecision());
		LOG.info("Closed order period return message : " + orderPeriodClosedResultData.getReturnMessage());
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

	/**
	 * @return orderPeriodClosedService
	 */
	public LosService<AmwayOrderPeriodModel, OrderPeriodCloseResultData> getOrderPeriodClosedService()
	{
		return orderPeriodClosedService;
	}

	/**
	 * @param orderPeriodClosedService the orderPeriodClosedService to set
	 */
	public void setOrderPeriodClosedService(
			final LosService<AmwayOrderPeriodModel, OrderPeriodCloseResultData> orderPeriodClosedService)
	{
		this.orderPeriodClosedService = orderPeriodClosedService;
	}


}
