package com.amway.core.util;

import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.site.BaseSiteService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.amway.core.bonusperiod.services.AmwayBonusPeriodService;
import com.amway.core.exceptions.AmwayServiceException;
import com.amway.core.model.AmwayBonusPeriodModel;
import com.amway.core.model.AmwayOrderPeriodModel;
import com.amway.core.orderperiod.services.AmwayOrderPeriodService;


/**
 * Default Implementation
 *
 * @author ashishalishetty
 */
public class AmwayPeriodHelper
{
	private static Logger LOG = Logger.getLogger(AmwayPeriodHelper.class);

	private static final String AMWAY_PERIOD_FMT = "yyyyMM";
	private static ApplicationContext context = null;
	private static BaseSiteService baseSiteService = null;
	private static AmwayBonusPeriodService<AmwayBonusPeriodModel, AbstractOrderModel> amwayBonusPeriodService = null;
	private static AmwayOrderPeriodService<AmwayOrderPeriodModel, AbstractOrderModel> amwayOrderPeriodService = null;

	static
	{
		context = Registry.getApplicationContext();
		baseSiteService = (BaseSiteService) context.getBean("baseSiteService");
		amwayBonusPeriodService = (AmwayBonusPeriodService) context.getBean("bonusPeriodService");
		amwayOrderPeriodService = (AmwayOrderPeriodService) context.getBean("orderPeriodService");
	}


	/**
	 * To get current active bonus period
	 *
	 * @return bonusperiod
	 */
	public static String getCurrentActiveBonusPeriod()
	{
		final Date currentDate = AmwayDateHelper.getTimeForSiteTimeZone();
		final List<AmwayBonusPeriodModel> bonusPeriods = amwayBonusPeriodService.findAllActiveBonusPeriodsForSite();

		for (final AmwayBonusPeriodModel bonusPeriod : bonusPeriods)
		{
			if (currentDate.compareTo(bonusPeriod.getStartDate()) >= 0 && currentDate.compareTo(bonusPeriod.getEndDate()) <= 0)
			{
				return bonusPeriod.getCode();
			}
		}
		throw new AmwayServiceException("no active bonus period found for time :" + currentDate);
	}

	/**
	 * To get current active bonus period
	 *
	 * @return bonusperiod
	 */
	public static AmwayBonusPeriodModel getCurrentActiveBonusPeriodModel()
	{
		final Date currentDate = AmwayDateHelper.getTimeForSiteTimeZone();
		final List<AmwayBonusPeriodModel> bonusPeriods = amwayBonusPeriodService.findAllActiveBonusPeriodsForSite();

		for (final AmwayBonusPeriodModel bonusPeriod : bonusPeriods)
		{
			if (currentDate.compareTo(bonusPeriod.getStartDate()) >= 0 && currentDate.compareTo(bonusPeriod.getEndDate()) <= 0)
			{
				return bonusPeriod;
			}
		}
		throw new AmwayServiceException("no active bonus period found for time :" + currentDate);
	}

	/**
	 * To get current active bonus period
	 *
	 * @return orderperiod
	 */
	public static AmwayOrderPeriodModel getCurrentActiveOrderPeriodModel()
	{
		final Date currentDate = AmwayDateHelper.getTimeForSiteTimeZone();
		final List<AmwayOrderPeriodModel> orderPeriods = amwayOrderPeriodService.findAllActiveOrderPeriodsForSite();

		for (final AmwayOrderPeriodModel orderPeriod : orderPeriods)
		{
			if (currentDate.compareTo(orderPeriod.getStartDate()) >= 0 && currentDate.compareTo(orderPeriod.getEndDate()) <= 0)
			{
				return orderPeriod;
			}
		}
		throw new AmwayServiceException("no active order period found for time :" + currentDate);
	}

	/**
	 * To get bonus period before.
	 *
	 * @param monthsBefore
	 * @return currentActiveBonusPeriod
	 */
	public static String getBonusPeriodBefore(final int monthsBefore)
	{
		final String currentActiveBonusPeriod = getCurrentActiveBonusPeriod();
		try
		{
			final DateFormat sdf = new SimpleDateFormat(AMWAY_PERIOD_FMT);
			final Date currentActiveBonusPeriodDate = sdf.parse(currentActiveBonusPeriod);
			final Calendar cal = AmwayDateHelper.getCalendarForSiteTimeZone();
			cal.setTime(currentActiveBonusPeriodDate);
			cal.add(Calendar.MONTH, -monthsBefore);
			return DateUtils.formatDate(cal.getTime(), AMWAY_PERIOD_FMT);
		}
		catch (final ParseException e)
		{
			LOG.error(e.getMessage(), e);
		}
		return currentActiveBonusPeriod;
	}

}
