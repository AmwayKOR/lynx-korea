package com.amway.apac.deliveryslot.daos;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.Date;
import java.util.List;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;


/**
 * DAO interface for fetch delivery slot data.
 *
 * @author Ashish Sabal
 */
public interface AmwayApacDeliverySlotDao
{
	/**
	 * * Gets the delivery slots list for provided delivery date
	 *
	 * @param warehouse
	 *           the warehouse
	 * @param deliveryDate
	 *           the delivery date
	 * @return the delivery slots list
	 *
	 * @throws IllegalArgumentException
	 */
	List<AmwayDeliverySlotAvailabilityModel> getDeliverySlotsAvailability(final WarehouseModel warehouse, final Date deliveryDate);

	/**
	 * * Finds delivery slot with delivery date and slot time
	 *
	 * @param warehouse
	 *           the warehouse
	 * @param deliveryDate
	 *           the delivery date
	 * @param slotTime
	 *           the slot time
	 * @return delivery slot model
	 *
	 * @throws IllegalArgumentException
	 */
	AmwayDeliverySlotAvailabilityModel getDeliverySlot(final WarehouseModel warehouse, final Date deliveryDate,
			final String slotTime);

	/**
	 * * Get delivery Day for ordering day and time
	 *
	 * @param warehouse
	 *           the warehouse
	 * @param orderingDay
	 *           the ordering day
	 * @param orderingTime
	 *           the ordering time
	 * @return delivery day
	 *
	 * @throws IllegalArgumentException
	 */
	WeekDay getDeliveryDay(final WarehouseModel warehouse, final WeekDay orderingDay, final Date orderingTime);
}
