package com.amway.apac.deliveryslot.daos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;


/**
 * DAO Interface for delivery slot data management.
 *
 * @author Ashish Sabal
 */
public interface AmwayApacDeliverySlotManagementDao
{
	/**
	 * Gets the delivery slot by order day.
	 *
	 * @param orderingDay
	 *           the ordering day
	 * @return List of {@link AmwayDeliverySlotConfigModel}
	 *
	 * @throws IllegalArgumentException
	 */
	List<AmwayDeliverySlotConfigModel> getDeliverySlotByOrderDay(final DayOfWeek orderingDay);

	/**
	 * Gets the next delivery slot by delivery date and slot.
	 *
	 * @param deliveryDate
	 *           the delivery date
	 * @param slot
	 *           the slot
	 * @return the next delivery slot by delivery date and slot
	 *
	 * @throws IllegalArgumentException
	 */
	List<AmwayDeliverySlotAvailabilityModel> getNextDeliverySlotByDeliveryDateAndSlot(final LocalDate deliveryDate,
			final String slot);
}
