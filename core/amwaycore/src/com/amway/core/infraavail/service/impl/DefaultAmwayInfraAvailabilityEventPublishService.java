/**
 *
 */
package com.amway.core.infraavail.service.impl;

import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.definition.NoSuchProcessDefinitionException;
import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.processengine.model.BusinessProcessModel;
import de.hybris.platform.processengine.model.ProcessTaskModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.task.TaskConditionModel;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.infraavail.service.AmwayInfraAvailabilityEventPublishService;
import com.amway.core.taskcondition.dao.AmwayTaskConditionDao;



/**
 * Interface dedicated for getting infra waiting state events and publish event
 */
public class DefaultAmwayInfraAvailabilityEventPublishService extends AbstractBusinessService
		implements AmwayInfraAvailabilityEventPublishService
{
	private final Logger LOG = Logger.getLogger(DefaultAmwayInfraAvailabilityEventPublishService.class);
	private BusinessProcessService businessProcessService;
	private AmwayTaskConditionDao amwayTaskConditionDao;

	/**
	 * @return businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	/**
	 * @param businessProcessService the businessProcessService to set
	 */
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return amwayTaskConditionDao
	 */
	public AmwayTaskConditionDao getAmwayTaskConditionDao()
	{
		return amwayTaskConditionDao;
	}

	/**
	 * @param amwayTaskConditionDao the amwayTaskConditionDao to set
	 */
	public void setAmwayTaskConditionDao(final AmwayTaskConditionDao amwayTaskConditionDao)
	{
		this.amwayTaskConditionDao = amwayTaskConditionDao;
	}

	/**
	 * Get Tasks which are waiting for event with code %infraAvailCode
	 *
	 * @param infraAvailCode
	 */
	@Override
	public List<TaskConditionModel> getWaitingEvents(final String infraAvailCode)
	{
		return getAmwayTaskConditionDao().findWaitingLikeTasks(infraAvailCode);
	}

	/**
	 * Method to retrieve and publish events for tasks in waiting state for %infraAvailCode
	 *
	 * @param infraAvailCode
	 */
	@Override
	public void publishWaitingEvents(final String infraAvailCode)
	{
		final List<TaskConditionModel> taskConditionModels = getWaitingEvents(infraAvailCode);
		if (CollectionUtils.isNotEmpty(taskConditionModels))
		{
			publishEvents(taskConditionModels);
		}
	}

	/**
	 * Method to publish corresponding events for the task conditions
	 */
	@Override
	public void publishEvents(final List<TaskConditionModel> taskConditionModels)
	{
		for (final TaskConditionModel taskConditionModel : taskConditionModels)
		{
			try
			{
				final BusinessProcessModel businessProcessModel = (taskConditionModel.getTask() instanceof ProcessTaskModel) ?
						((ProcessTaskModel) taskConditionModel.getTask()).getProcess() :
						null;
				if (businessProcessModel != null && ProcessState.WAITING.equals(businessProcessModel.getProcessState()))
				{
					LOG.info("Resuming wait event " + taskConditionModel.getUniqueID());
					getBusinessProcessService().triggerEvent(taskConditionModel.getUniqueID());
				}
			}
			catch (final SystemException | NoSuchProcessDefinitionException exception)
			{
				LOG.error(
						"System Exception in resuming wait event " + taskConditionModel.getUniqueID() + "  - Removing task condition",
						exception);
				getModelService().remove(taskConditionModel);
			}
			catch (final Exception exception)
			{
				LOG.error("Exception in resuming wait eveent " + taskConditionModel.getUniqueID(), exception);

			}
		}
	}
}
