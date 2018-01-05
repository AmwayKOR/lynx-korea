/**
 *
 */
package com.amway.apac.facades.deliveryslot.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.deliveryslot.services.AmwayApacDeliverySlotManagementService;
import com.amway.apac.core.model.AmwayDeliverySlotConfigModel;
import com.amway.apac.facades.deliveryslot.AmwayApacDeliverySlotCreationFacade;


/**
 * Default implementation of {@link AmwayApacDeliverySlotCreationFacade}
 *
 * @author Ashish Sabal
 *
 */
public class AmwayApacDeliverySlotCreationFacadeImpl implements AmwayApacDeliverySlotCreationFacade
{
	private static final Logger LOG = Logger.getLogger(AmwayApacDeliverySlotCreationFacadeImpl.class);

	private AmwayApacDeliverySlotManagementService amwayApacDeliverySlotManagementService;


	/*
	 * (non-Javadoc)
	 *
	 * @see com.amway.apac.facades.deliveryslot.AmwayApacDeliverySlotCreationFacade#createDeliverySlotData(java.time.
	 * LocalDate)
	 */
	@Override
	public void createDeliverySlotData(final LocalDate orderingDate)
	{
		final DayOfWeek orderingDay = orderingDate.getDayOfWeek();
		LOG.debug("Week Of Day : " + orderingDay);

		// If ordering day is SAT or SUN, data already exists in SYSTEM
		if ((!orderingDay.equals(DayOfWeek.SATURDAY)) && (!orderingDay.equals(DayOfWeek.SUNDAY)))
		{
			// 1. Get delivery date from ordering date & day.
			final List<AmwayDeliverySlotConfigModel> deliverySlotModels = getAmwayApacDeliverySlotManagementService()
					.getDeliverySlotByOrderDay(orderingDay);

			// 2. Get newDeliverySlots for ordering date
			getAmwayApacDeliverySlotManagementService().createDeliverySlotForDate(deliverySlotModels, orderingDate);
		}
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
