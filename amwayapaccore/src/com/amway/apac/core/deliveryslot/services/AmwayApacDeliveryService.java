/**
 *
 */
package com.amway.apac.core.deliveryslot.services;

import de.hybris.platform.commerceservices.delivery.DeliveryService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.Date;
import java.util.List;

import com.amway.apac.core.model.AmwayDeliverySlotAvailabilityModel;
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
	 * Finds the delivery date as per time when user has requested to confirm the order
	 *
	 * @return delivery date.
	 */
	Date getDeliveryDate(WarehouseModel warehouse);

	/**
	 * Reserve the selected delivery slot for this order.
	 *
	 * @return consumed Count
	 */
	Integer reserve(AmwayDeliverySlotAvailabilityModel deliverySlot) throws AmwayServiceException;

	/**
	 * Release the selected delivery slot
	 *
	 * @return consumed Count
	 */
	Integer release(AmwayDeliverySlotAvailabilityModel deliverySlot) throws AmwayServiceException;

	/**
	 * Set delivery slot on the basis of slotTime, deliveryDate and slotTime.
	 *
	 * @param warehouse
	 * @param deliveryDate
	 * @param slotTime
	 *
	 * @return true if successfully set the delivery slot, otherwise false
	 */
	boolean setDeliverySlot(WarehouseModel warehouse, Date deliveryDate, String slotTime);

	/**
	 * @param warehouse
	 * @param deliveryDate
	 * @param slotTime
	 * @return
	 */
	AmwayDeliverySlotAvailabilityModel getDeliverySlot(WarehouseModel warehouse, Date deliveryDate, String slotTime);
}
