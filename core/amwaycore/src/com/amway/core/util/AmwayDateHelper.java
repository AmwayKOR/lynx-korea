package com.amway.core.util;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.Registry;
import de.hybris.platform.site.BaseSiteService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;


/**
 * Default Impplementation
 */
public class AmwayDateHelper
{
	private static Logger LOG = Logger.getLogger(AmwayDateHelper.class);

	public static final String TIME_FMT_LOS = "MMddYYYY";

	private static ApplicationContext context = null;
	private static BaseSiteService baseSiteService = null;

	static
	{
		context = Registry.getApplicationContext();
		baseSiteService = (BaseSiteService) context.getBean("baseSiteService");
	}


	/**
	 * To get time for site time zone
	 *
	 * @return Date
	 */
	public static Date getTimeForSiteTimeZone()
	{
		final Date currentDate = getTimeForSiteTimeZone(baseSiteService.getCurrentBaseSite());
		return currentDate;
	}

	/**
	 * To get time for site time zone
	 *
	 * @param baseSite
	 * @return Date
	 */
	public static Date getTimeForSiteTimeZone(final BaseSiteModel baseSite)
	{
		final Date currentDate = Calendar.getInstance(TimeZone.getTimeZone(baseSite.getTimeZone())).getTime();
		return currentDate;
	}

	/**
	 * To get calender
	 *
	 * @return Calendar
	 */
	public static Calendar getCalendarForSiteTimeZone()
	{
		return getCalendarForSiteTimeZone(baseSiteService.getCurrentBaseSite());
	}

	/**
	 * To get calender fot site time zone
	 *
	 * @param site
	 * @return Calendar
	 */
	public static Calendar getCalendarForSiteTimeZone(final BaseSiteModel site)
	{
		return Calendar.getInstance(TimeZone.getTimeZone(site.getTimeZone()));
	}

	/**
	 * To get date format.
	 *
	 * @param format
	 * @return dateformat
	 */
	public static String getTimeForSiteTimeZone(final String format)
	{
		final SimpleDateFormat formatter = new SimpleDateFormat(format);
		final Date currentDate = Calendar.getInstance(TimeZone.getTimeZone(baseSiteService.getCurrentBaseSite().getTimeZone()))
				.getTime();
		return formatter.format(currentDate);
	}

	/**
	 * @param site
	 * @param format
	 * @return dateformat
	 */
	public static String getTimeForSiteTimeZone(final BaseSiteModel site, final String format)
	{
		final SimpleDateFormat formatter = new SimpleDateFormat(format);
		final Date currentDate = getCalendarForSiteTimeZone(site).getTime();
		return formatter.format(currentDate);
	}
}
