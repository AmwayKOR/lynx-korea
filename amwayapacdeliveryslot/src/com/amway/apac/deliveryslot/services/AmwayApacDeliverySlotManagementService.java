/**
 *
 */
package com.amway.apac.deliveryslot.services;

import de.hybris.platform.basecommerce.enums.WeekDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.apac.deliveryslot.model.AmwayDeliverySlotConfigModel;


/**
 * @author Ashish Sabal
 *
 */
public interface AmwayApacDeliverySlotManagementService
{
	/**
	 * @param orderingDay
	 * @return List of {@link AmwayDeliverySlotConfigModel}
	 */
	List<AmwayDeliverySlotConfigModel> getDeliverySlotByOrderDay(final DayOfWeek orderingDay);


	/**
	 * @param applicableDeliverySlots
	 * @param orderingDate
	 */
	void createDeliverySlotForDate(final List<AmwayDeliverySlotConfigModel> applicableDeliverySlots, final LocalDate orderingDate);

	/**
	 * @param orderingDate
	 * @param deliveryDay
	 * @return
	 */
	LocalDate getDeliveryDate(final LocalDate orderingDate, final WeekDay deliveryDay);


	/**
	 * @param deliveryDate
	 * @param slotTime
	 * @return
	 */
	List<AmwayDeliverySlotAvailabilityModel> getNextDeliverySlotByDeliveryDateAndSlot(LocalDate deliveryDate, String slotTime);

	/**
	 * @param slotConfigModel
	 * @param slotModels
	 */
	void updateInfoInSlotsAvailabilityModels(AmwayDeliverySlotConfigModel slotConfigModel,
			List<AmwayDeliverySlotAvailabilityModel> slotModels);


	/**
	 *
	 */
	void createDeliverySlotData(LocalDate orderingDate);
}
