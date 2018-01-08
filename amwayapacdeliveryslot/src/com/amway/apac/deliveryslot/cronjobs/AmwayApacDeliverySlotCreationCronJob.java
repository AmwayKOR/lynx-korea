/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.amway.apac.deliveryslot.cronjobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.time.LocalDate;

import org.apache.log4j.Logger;
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
	private static final Logger LOG = Logger.getLogger(AmwayApacDeliverySlotCreationCronJob.class);

	private AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService;

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
		getAmwayApacDeliverySlotManagementService().createDeliverySlotData(getOrderingDate());

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
	 * @return the amwayApacDeliverySlotManagementService
	 */
	public AmwayApacDeliverySlotManagementService getAmwayApacDeliverySlotManagementService()
	{
		return amwayApacDeliverySlotManagementService;
	}

	/**
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
