/**
 *
 */
package com.amway.apac.core.deliveryslot.daos;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.Date;
import java.util.List;

import com.amway.apac.core.model.AmwayDeliverySlotAvailabilityModel;


/**
 * @author Ashish Sabal
 *
 */
public interface AmwayApacDeliverySlotDao
{
	/**
	 * * Finds the delivery slots available
	 *
	 * @return list of delivery slots found
	 */
	List<AmwayDeliverySlotAvailabilityModel> getDeliverySlotsAvailability(WarehouseModel warehouse, Date deliveryDate);

	/**
	 * * Finds delivery slot
	 *
	 * @return delivery slot
	 */
	AmwayDeliverySlotAvailabilityModel getDeliverySlot(WarehouseModel warehouse, Date deliveryDate, String slotTime);

	/**
	 * * Get delivery Day
	 *
	 * @return delivery day
	 */
	WeekDay getDeliveryDay(WarehouseModel warehouse, WeekDay orderingDay, Date orderingTime);
}
