package com.amway.integration.dam.cronjob;

import static com.amway.integration.dam.constants.AmwayDamConstants.ERROR_DAM_UNEXPECTED;
import static org.apache.commons.lang3.StringUtils.SPACE;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.L10NService;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.amway.integration.dam.exception.AmwayDamException;
import com.amway.integration.dam.model.AmwayDamQueueEntryModel;
import com.amway.integration.dam.service.AmwayDamEventHandler;
import com.amway.integration.dam.service.AmwayDamQueueService;
import com.amway.core.annotations.AmwayBean;

/**
 * Cron job for starting handling {@link AmwayDamQueueEntryModel} from DAM
 */
@AmwayBean(ext="amwaydamintegration",docs="https://jira.amway.com:8444/display/HC/amwaydamintegration")
public class AmwayDamEventQueueProcessJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayDamEventQueueProcessJob.class);

	@Autowired
	private AmwayDamQueueService amwayDamQueueService;
	@Autowired
	private AmwayDamEventHandler amwayDamEventHandler;

	@Autowired
	private L10NService l10NService;
	@Autowired
	private ModelService modelService;

	private Integer processedEventsLimit;

	@Override
	public PerformResult perform(CronJobModel cronJob)
	{
		LOGGER.debug("Starting perform queue events... ");

		List<AmwayDamQueueEntryModel> events = amwayDamQueueService.getEvents(processedEventsLimit);
		events.forEach(this::handleEvent);

		LOGGER.debug("Performed " + events.size() + " events.");

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	private void handleEvent(AmwayDamQueueEntryModel event)
	{
		try
		{
			amwayDamEventHandler.handleEvent(event);
			LOGGER.debug("Handling event of type = [" + event.getOperation().getCode() + "] for asset = [" + event.getPath()
				  + "] was performed successfully.");
		}
		catch (AmwayDamException ex)
		{
			handleException(ex);
		}
		catch (Exception ex)
		{
			LOGGER.error(ERROR_DAM_UNEXPECTED + SPACE + ex.getMessage(), ex);
		}
		finally
		{
			modelService.remove(event);
		}
	}

	private void handleException(AmwayDamException ex)
	{
		//@formatter:off
			String errorMessage = new StringBuilder()
				  .append(ex.getCode())
				  .append(SPACE)
				  .append(l10NService.getLocalizedString(ex.getCode(), ex.getParams()))
				  .toString();
			//@formatter:on
		LOGGER.error(errorMessage);
	}

	@Required
	public void setProcessedEventsLimit(Integer processedEventsLimit)
	{
		this.processedEventsLimit = processedEventsLimit;
	}
}
