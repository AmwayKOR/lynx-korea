package com.amway.core.events.listener;

import java.util.List;

import com.amway.core.events.beans.AmwayBusinessEvent;


/**
 * <p>Class encapsulates logic about pre- and post-processing business events.</p>
 * <p>This is proper place to customize business logic related to events.</p>
 * l
 */
public class AmwayBusinessEventsProcessing
{
	/**
	 * List of processors.
	 */
	private List<EventProcessor> processors;
	/**
	 * List of job names which should handle some of the events internally.
	 * It is used to push events to external systems which doesn't work via REST WS.
	 */
	private List<String> internalProcessingJobNames;

	/**
	 * Trigger pre-processors.
	 *
	 * @param ev
	 */
	public void beforeSend(AmwayBusinessEvent ev)
	{
		// @formatter:off
		getProcessors().stream()
				.filter(p -> p.applicable(ev))
				.forEachOrdered(p -> {
					p.beforeSend(ev);
				});
		// @formatter:on
	}

	/**
	 * Trigger post-processors.
	 *
	 * @param ev
	 */
	public void afterSend(AmwayBusinessEvent ev)
	{
		// @formatter:off
		getProcessors().stream()
				.filter(p -> p.applicable(ev))
				.forEachOrdered(p -> {
					p.afterSend(ev);
				});
		// @formatter:on
	}


	public List<EventProcessor> getProcessors()
	{
		return processors;
	}

	public void setProcessors(List<EventProcessor> processors)
	{
		this.processors = processors;
	}

	public List<String> getInternalProcessingJobNames()
	{
		return internalProcessingJobNames;
	}

	public void setInternalProcessingJobNames(List<String> internalProcessingJobNames)
	{
		this.internalProcessingJobNames = internalProcessingJobNames;
	}
}
