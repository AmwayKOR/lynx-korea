package com.amway.apac.deliveryslot.cronjobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;
import com.amway.apac.deliveryslot.services.AmwayApacDeliverySlotManagementService;


/**
 * This is a Cron Job to create a new entry on the basis of delivery date & ordering day for
 * {@link AmwayDeliverySlotAvailabilityModel} from {@link AmwayDeliverySlotConfigModel}.
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacDeliverySlotCreationCronJob extends AbstractJobPerformable<CronJobModel>
{
	/** The LOGGER Constant. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AmwayApacDeliverySlotCreationCronJob.class);

	/** The amway apac delivery slot management service. */
	private AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService;

	/**
	 * Cronjob default perform method to create delivery slot data.
	 *
	 * @param cronjob
	 *           the cronjob
	 * @return the perform result
	 */
	@Override
	public PerformResult perform(final CronJobModel cronjob)
	{
		// Create slot data and persist in DB
		getAmwayApacDeliverySlotManagementService().createDeliverySlotData(getOrderingDate());

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
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
		LOGGER.debug(new StringBuilder(50).append("Preparing data for date : ").append(orderingDate).toString());

		return orderingDate;
	}

	/**
	 * Gets the amway apac delivery slot management service.
	 *
	 * @return the amwayApacDeliverySlotManagementService
	 */
	public AmwayApacDeliverySlotManagementService getAmwayApacDeliverySlotManagementService()
	{
		return amwayApacDeliverySlotManagementService;
	}

	/**
	 * Sets the amway apac delivery slot management service.
	 *
	 * @param amwayApacDeliverySlotManagementService
	 *           the amwayApacDeliverySlotManagementService to set
	 */
	@Required
	public void setAmwayApacDeliverySlotManagementService(
			final AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService)
	{
		this.amwayApacDeliverySlotManagementService = amwayApacDeliverySlotManagementService;
	}
}
