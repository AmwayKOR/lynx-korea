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
package com.amway.apac.deliveryslot.daos;

import de.hybris.platform.basecommerce.enums.WeekDay;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.Date;
import java.util.List;

import com.amway.apac.deliveryslot.model.AmwayDeliverySlotAvailabilityModel;


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
