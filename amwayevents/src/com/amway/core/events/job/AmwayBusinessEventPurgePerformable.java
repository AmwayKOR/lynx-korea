package com.amway.core.events.job;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.amway.core.events.services.AmwayBusinessEventQueueService;


/**
 * Purges old events.
 */
public class AmwayBusinessEventPurgePerformable extends AbstractJobPerformable<CronJobModel>
{

	private static final Logger LOG = Logger.getLogger(AmwayBusinessEventPurgePerformable.class.getName());
	/**
	 * Purge events period parameter name.
	 */
	public static final String AMWAYEVENTS_PURGE_EVENTS_PERIOD_DAYS = "amwayevents.purgeEventsPeriodDays";

	private static int DEFAULT_PURGE_PERIOD_DAYS = 14;

	@Autowired
	private AmwayBusinessEventQueueService eventQueueService;

	@Override
	public PerformResult perform(CronJobModel amwayBusinessEventsPurgeJobModel)
	{
		String period = Config.getParameter(AMWAYEVENTS_PURGE_EVENTS_PERIOD_DAYS);
		int days;
		if (StringUtils.isEmpty(period))
		{
			days = DEFAULT_PURGE_PERIOD_DAYS;
		}
		else
		{
			days = Integer.parseInt(period);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, -days);

		LOG.info("Purge old events. Period = " + period);
		eventQueueService.purgeOldEvents(c.getTime());

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}


}
