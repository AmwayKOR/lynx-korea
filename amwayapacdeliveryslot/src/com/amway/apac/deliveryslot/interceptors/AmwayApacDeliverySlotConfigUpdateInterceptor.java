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
package com.amway.apac.deliveryslot.interceptors;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;
import com.amway.apac.deliveryslot.services.AmwayApacDeliverySlotManagementService;


/**
 * {@link AmwayApacDeliverySlotConfigUpdateInterceptor} is to update the {@link AmwayDeliverySlotAvailabilityModel} on
 * save.For ex. if user changed the value of a Config, it updates next all availability models that exits in DB.
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacDeliverySlotConfigUpdateInterceptor implements PrepareInterceptor<AmwayDeliverySlotConfigModel>
{
	private static final Logger LOG = Logger.getLogger(AmwayApacDeliverySlotConfigUpdateInterceptor.class);

	private ModelService modelService;
	private AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.interceptor.PrepareInterceptor#onPrepare(java.lang.Object,
	 * de.hybris.platform.servicelayer.interceptor.InterceptorContext)
	 */
	@Override
	public void onPrepare(final AmwayDeliverySlotConfigModel slotConfigModel, final InterceptorContext arg1)
			throws InterceptorException
	{
		Preconditions.checkArgument(Objects.nonNull(slotConfigModel), "Config model cannot be NULL here.");

		if (Objects.nonNull(slotConfigModel.getDeliveryDay()) && Objects.nonNull(slotConfigModel.getSlotTime()))
		{
			final LocalDate currentDate = LocalDate.now();
			final LocalDate deliveryDate = getAmwayApacDeliverySlotManagementService().getDeliveryDate(currentDate,
					slotConfigModel.getDeliveryDay());
			LOG.info("Modification of Slot data for  Delivery Date : " + deliveryDate + " started ...");

			// Fetch slot models from slot management service
			final List<AmwayDeliverySlotAvailabilityModel> slotModels = getAmwayApacDeliverySlotManagementService()
					.getNextDeliverySlotByDeliveryDateAndSlot(deliveryDate, slotConfigModel.getSlotTime());

			getAmwayApacDeliverySlotManagementService().updateInfoInSlotsAvailabilityModels(slotConfigModel, slotModels);
		}
		else
		{
			throw new InterceptorException("Delivery Day or Slot Time is NULL ");
		}
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
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
