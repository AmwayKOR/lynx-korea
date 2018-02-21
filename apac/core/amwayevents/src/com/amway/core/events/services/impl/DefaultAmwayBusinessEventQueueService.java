package com.amway.core.events.services.impl;

import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.tx.TransactionBody;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.Assert;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.dao.EventQueueDao;
import com.amway.core.events.listener.AmwayBusinessEventsProcessing;
import com.amway.core.events.model.AmwayEventQueueEntryModel;
import com.amway.core.events.services.AmwayBusinessEventQueueService;


/**
 * Default implementation.
 * It stores events in database.
 */
public class DefaultAmwayBusinessEventQueueService implements AmwayBusinessEventQueueService
{
	private ModelService modelService;

	private EventQueueDao queueDao;

	private Converter<AmwayBusinessEvent, AmwayEventQueueEntryModel> eventToQueueEntryConverter;

	private Converter<AmwayEventQueueEntryModel, AmwayBusinessEvent> queueEntryToEventConverter;

	private CronJobService cronJobService;

	private AmwayBusinessEventsProcessing processing;

	/**
	 * {@inheritDoc}
	 * <p>Implementation creates a new {@link AmwayEventQueueEntryModel} object and saves it using model service.</p>
	 * <p>It also populates {@link AmwayEventQueueEntryModel#getCode()} field using {@link UUID#randomUUID()}.</p>
	 */
	@Override
	public void queueEvent(AmwayBusinessEvent e)
	{
		Assert.notNull(e, "Event must not be null");
		Assert.notNull(e.getTargetSystem(), "Event target must not be null");
		Assert.notNull(e.getName(), "Event name must not be null");
		AmwayEventQueueEntryModel item = getModelService().create(AmwayEventQueueEntryModel.class);
		getEventToQueueEntryConverter().convert(e, item);
		item.setCode(UUID.randomUUID().toString());
		getModelService().save(item);
	}

	/**
	 * {@inheritDoc}
	 * Implementation requests specified numer of events using {@link #queueDao}.
	 */
	@Override
	public List<AmwayBusinessEvent> getUnexportedEvents(Integer limit, String target)
	{
		List<AmwayEventQueueEntryModel> list = queueDao.getUnexportedEvents(limit, target);
		List<AmwayBusinessEvent> events = getQueueEntryToEventConverter().convertAll(list);
		return events;
	}

	/**
	 * {@inheritDoc}
	 * Implementation removes objects using model service.
	 */
	@Override
	public void purgeOldEvents(Date date)
	{
		List<AmwayEventQueueEntryModel> olderEvents = queueDao.getOutdatedEvents(date);
		modelService.removeAll(olderEvents);
	}

	/**
	 * {@inheritDoc}
	 * In the current implementation it is only possible to change following fields:
	 * <ul>
	 *    <li>{@link AmwayBusinessEvent#getRetrievalTime()}</li>
	 *    <li>{@link AmwayBusinessEvent#getMeta()}</li>
	 * </ul>
	 */
	@Override
	public void setEventProperties(String code, AmwayBusinessEvent properties) throws IllegalArgumentException
	{
		setEventProperties(code, properties, false);
	}

	@Override
	public void setEventProperties(String code, AmwayBusinessEvent properties, boolean ignoreNullValues)
			throws IllegalArgumentException
	{
		AmwayEventQueueEntryModel e = queueDao.getEventByCode(code);
		if (e == null)
		{
			throw new IllegalArgumentException("Can't find event with code " + code);
		}

		if (properties.getRetrievalTime() != null || !ignoreNullValues)
		{
			e.setRetrievalTime(properties.getRetrievalTime());
		}

		if (properties.getMeta() != null)
		{
			Map<String, String> mergedMeta = new HashMap<>(e.getMeta());
			mergedMeta.putAll(properties.getMeta());
			e.setMeta(mergedMeta);
		}
		else if (!ignoreNullValues)
		{
			e.setMeta(null);
		}

		modelService.save(e);
	}

	/**
	 * {@inheritDoc}
	 * Implementation is just a shortcut to {@link #confirmExported(Collection)}.
	 */
	@Override
	public void confirmExported(String code) throws Exception
	{
		this.confirmExported(Collections.singleton(code));
	}

	/**
	 * {@inheritDoc}
	 * <p>Implementation sets {@link AmwayEventQueueEntryModel#getRetrievalTime()} fields to the current date.</p>
	 * <p>It also fires post-processing activity for each exported event via {@link AmwayBusinessEventsProcessing#afterSend(AmwayBusinessEvent)}.</p>
	 */
	@Override
	public void confirmExported(Collection<String> codes) throws Exception
	{
		for (String code : codes)
		{
			Transaction.current().execute(new TransactionBody()
			{
				public Object execute()
				{
					AmwayEventQueueEntryModel queueEntry = queueDao.getEventByCode(code);
					queueEntry.setRetrievalTime(new Date());
					modelService.save(queueEntry);
					AmwayBusinessEvent event = getQueueEntryToEventConverter().convert(queueEntry);
					getProcessing().afterSend(event);
					return null;
				}
			});
		}
	}

	/**
	 * {@inheritDoc}
	 * Implementaton uses {@link #cronJobService} to trigger configured job.
	 */
	@Override
	public void sendPostponedEvents()
	{
		List<String> jobNames = getProcessing().getInternalProcessingJobNames();
		for (String jobName : jobNames)
		{
			CronJobModel cronJob = cronJobService.getCronJob(jobName);
			if (!cronJobService.isRunning(cronJob))
			{
				cronJobService.performCronJob(cronJob);
			}
		}

	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(ModelService modelService)
	{
		this.modelService = modelService;
	}

	public EventQueueDao getQueueDao()
	{
		return queueDao;
	}

	public void setQueueDao(EventQueueDao queueDao)
	{
		this.queueDao = queueDao;
	}

	public Converter<AmwayBusinessEvent, AmwayEventQueueEntryModel> getEventToQueueEntryConverter()
	{
		return eventToQueueEntryConverter;
	}

	public void setEventToQueueEntryConverter(Converter<AmwayBusinessEvent, AmwayEventQueueEntryModel> eventToQueueEntryConverter)
	{
		this.eventToQueueEntryConverter = eventToQueueEntryConverter;
	}

	public Converter<AmwayEventQueueEntryModel, AmwayBusinessEvent> getQueueEntryToEventConverter()
	{
		return queueEntryToEventConverter;
	}

	public void setQueueEntryToEventConverter(Converter<AmwayEventQueueEntryModel, AmwayBusinessEvent> queueEntryToEventConverter)
	{
		this.queueEntryToEventConverter = queueEntryToEventConverter;
	}

	public AmwayBusinessEventsProcessing getProcessing()
	{
		return processing;
	}

	public void setProcessing(AmwayBusinessEventsProcessing processing)
	{
		this.processing = processing;
	}

	public CronJobService getCronJobService()
	{
		return cronJobService;
	}

	public void setCronJobService(CronJobService cronJobService)
	{
		this.cronJobService = cronJobService;
	}
}
