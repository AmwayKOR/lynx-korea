package com.amway.core.events.listener;

import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.tx.Transaction;
import de.hybris.platform.tx.TransactionBody;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import com.amway.core.events.beans.AmwayBusinessEvent;
import com.amway.core.events.services.AmwayBusinessEventQueueService;


/**
 * Listener of {@link AmwayBusinessEvent}.
 * It performs additional pre-processing and puts the event into a queue.
 */
public class AmwayBusinessEventsListener extends AbstractEventListener<AmwayBusinessEvent>
{
	private static final Logger LOG = Logger.getLogger(AmwayBusinessEventsListener.class.getName());

	private AmwayBusinessEventQueueService queueService;

	private AmwayBusinessEventsProcessing processing;

	@Override
	protected void onEvent(AmwayBusinessEvent event)
	{
		Assert.notNull(event, "Event must not be null");
		try
		{
			Transaction.current().execute(new TransactionBody()
			{
				public Object execute()
				{
					processing.beforeSend(event);
					queueService.queueEvent(event);
					return null;
				}
			});
		}
		catch (Exception ex)
		{
			//standard hybris event listener doesn't allow to throw exceptions, we log it as an error:
			LOG.error("Error processing event " + event.getName(), ex);
		}
	}

	public AmwayBusinessEventQueueService getQueueService()
	{
		return queueService;
	}

	public void setQueueService(AmwayBusinessEventQueueService queueService)
	{
		this.queueService = queueService;
	}

	public AmwayBusinessEventsProcessing getProcessing()
	{
		return processing;
	}

	public void setProcessing(AmwayBusinessEventsProcessing processing)
	{
		this.processing = processing;
	}
}
