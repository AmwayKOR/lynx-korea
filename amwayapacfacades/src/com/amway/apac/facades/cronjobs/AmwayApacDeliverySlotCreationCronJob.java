/**
 *
 */
package com.amway.apac.facades.cronjobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.core.model.AmwayDeliverySlotConfigModel;
import com.amway.apac.facades.deliveryslot.AmwayApacDeliverySlotCreationFacade;


/**
 * This is a Cron Job to create a new entry on the basis of delivery date & ordering day for
 * {@link AmwayDeliverySlotAvailabilityModel} from {@link AmwayDeliverySlotConfigModel}.
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacDeliverySlotCreationCronJob extends AbstractJobPerformable<CronJobModel>
{
	private static final Logger LOG = Logger.getLogger(AmwayApacDeliverySlotCreationCronJob.class);

	private AmwayApacDeliverySlotCreationFacade deliverySlotFacade;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.
	 * CronJobModel)
	 */
	@Override
	public PerformResult perform(final CronJobModel arg0)
	{
		PerformResult result = null;

		// Create data and persist, result will be 1 for success.
		getDeliverySlotFacade().createDeliverySlotData(getOrderingDate());

		result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);

		return result;
	}

	/**
	 * Get ordering date by adding 1, assumption : Cron Job is running 1 days prior to ordering date. Ex. : Delivery Data
	 * for ordering date : 12/10/2017 will be prepared on 11/10/2017 i.e. Cron job is running on 11/10/2017
	 *
	 * @return {@link LocalDate}
	 */
	public LocalDate getOrderingDate()
	{
		final LocalDate orderingDate = LocalDate.now().plusDays(1L);
		LOG.debug("Preparing data for date : " + orderingDate);

		return orderingDate;
	}

	/**
	 * @return the deliverySlotFacade
	 */
	public AmwayApacDeliverySlotCreationFacade getDeliverySlotFacade()
	{
		return deliverySlotFacade;
	}

	/**
	 * @param deliverySlotFacade
	 *           the deliverySlotFacade to set
	 */
	@Required
	public void setDeliverySlotFacade(final AmwayApacDeliverySlotCreationFacade deliverySlotFacade)
	{
		this.deliverySlotFacade = deliverySlotFacade;
	}
}
