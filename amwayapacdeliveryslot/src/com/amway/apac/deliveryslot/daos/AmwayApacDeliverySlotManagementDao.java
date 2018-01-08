/**
 *
 */
package com.amway.apac.deliveryslot.daos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;


/**
 * @author Ashish Sabal
 *
 */
public interface AmwayApacDeliverySlotManagementDao
{
	/**
	 * @param orderingDay
	 * @return List of {@link AmwayDeliverySlotConfigModel}
	 */
	List<AmwayDeliverySlotConfigModel> getDeliverySlotByOrderDay(final DayOfWeek orderingDay);

	/**
	 * @param deliveryDate
	 * @param slot
	 * @return
	 */
	List<AmwayDeliverySlotAvailabilityModel> getNextDeliverySlotByDeliveryDateAndSlot(final LocalDate deliveryDate,
			final String slot);
}
