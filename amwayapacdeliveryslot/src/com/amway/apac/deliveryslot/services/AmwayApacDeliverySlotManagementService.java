package com.amway.apac.deliveryslot.services;

import de.hybris.platform.basecommerce.enums.WeekDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;


/**
 * Delivery slot management service interface
 * 
 * @author Ashish Sabal
 *
 */
public interface AmwayApacDeliverySlotManagementService
{
	/**
	 * @param orderingDay
	 * @return List of {@link AmwayDeliverySlotConfigModel}
	 *
	 * @throws IllegalArgumentException
	 */
	List<AmwayDeliverySlotConfigModel> getDeliverySlotByOrderDay(final DayOfWeek orderingDay);


	/**
	 * @param applicableDeliverySlots
	 * @param orderingDate
	 *
	 * @throws IllegalArgumentException
	 */
	void createDeliverySlotForDate(final List<AmwayDeliverySlotConfigModel> applicableDeliverySlots, final LocalDate orderingDate);

	/**
	 * Gets the delivery date.
	 *
	 * @param orderingDate
	 *           the ordering date
	 * @param deliveryDay
	 *           the delivery day
	 * @return the delivery date
	 *
	 * @throws IllegalArgumentException
	 */
	LocalDate getDeliveryDate(final LocalDate orderingDate, final WeekDay deliveryDay);


	/**
	 * Gets the next delivery slot by delivery date and slot.
	 *
	 * @param deliveryDate
	 *           the delivery date
	 * @param slotTime
	 *           the slot time
	 * @return the next delivery slot by delivery date and slot
	 *
	 * @throws IllegalArgumentException
	 */
	List<AmwayDeliverySlotAvailabilityModel> getNextDeliverySlotByDeliveryDateAndSlot(final LocalDate deliveryDate,
			final String slotTime);

	/**
	 * Update availability model details from config model
	 *
	 * @param slotConfigModel
	 * @param slotModels
	 *
	 * @throws IllegalArgumentException
	 */
	void updateInfoInSlotsAvailabilityModels(final AmwayDeliverySlotConfigModel slotConfigModel,
			final List<AmwayDeliverySlotAvailabilityModel> slotModels);


	/**
	 * Creates the delivery slot data.
	 *
	 * @param orderingDate
	 *           the ordering date
	 *
	 * @throws IllegalArgumentException
	 */
	void createDeliverySlotData(final LocalDate orderingDate);
}
