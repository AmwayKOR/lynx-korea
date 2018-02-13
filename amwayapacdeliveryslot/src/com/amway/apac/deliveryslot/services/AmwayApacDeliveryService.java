package com.amway.apac.deliveryslot.services;

import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.Date;
import java.util.List;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;
import com.amway.core.exceptions.AmwayServiceException;


/**
 * This service contains the functionalities related to the order delivery over and above those in OOTB interface
 * {@link DeliveryService}
 *
 * @author Ashish Sabal
 *
 */
public interface AmwayApacDeliveryService extends DeliveryService
{
	/**
	 * Finds the delivery slots available.
	 *
	 * @return list of delivery slots found.
	 */
	List<AmwayDeliverySlotAvailabilityModel> getDeliverySlotsAvailability();

	/**
	 * Finds the delivery date as per time when user has requested to confirm the order.
	 *
	 * @param warehouse
	 *           the warehouse
	 * @return delivery date.
	 *
	 * @throws IllegalArgumentException
	 *            if warehouse is null
	 */
	Date getDeliveryDate(final WarehouseModel warehouse);

	/**
	 * Reserve the selected delivery slot for this order.
	 *
	 * @return consumed Count
	 *
	 */
	Integer reserve(final AmwayDeliverySlotAvailabilityModel deliverySlot) throws AmwayServiceException;

	/**
	 * Release the selected delivery slot
	 *
	 * @return consumed Count
	 * @throws AmwayServiceException
	 * @throws IllegalArgumentException
	 *            if deliverySlot is null
	 */
	Integer release(final AmwayDeliverySlotAvailabilityModel deliverySlot) throws AmwayServiceException;

	/**
	 * Set delivery slot on the basis of warehouse, deliveryDate and slotTime.
	 *
	 * @param warehouse
	 * @param deliveryDate
	 * @param slotTime
	 *
	 * @return true if successfully set the delivery slot, otherwise false
	 * @throws IllegalArgumentException
	 *            if warehouse, deliveryDate or slotTime is null
	 */
	boolean setDeliverySlot(final WarehouseModel warehouse, final Date deliveryDate, final String slotTime);

	/**
	 * Gets the delivery slot.
	 *
	 * @param warehouse
	 *           the warehouse
	 * @param deliveryDate
	 *           the delivery date
	 * @param slotTime
	 *           the slot time
	 * @return the delivery slot
	 *
	 * @throws IllegalArgumentException
	 *            if warehouse, deliveryDate or slotTime is null
	 */
	AmwayDeliverySlotAvailabilityModel getDeliverySlot(final WarehouseModel warehouse, final Date deliveryDate,
			final String slotTime);
}
