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
package com.amway.apac.deliveryslot.orders;

import de.hybris.platform.commerceservices.order.CommerceCartModificationStatus;


/**
 * Status values for the CommerceCartModification statusCode.
 *
 * @author Ashish Sabal
 *
 */
public interface AmwayApacCommerceCartModificationStatus extends CommerceCartModificationStatus
{
	/**
	 * All delivery slot related errors
	 */
	String NO_DELIVERY_SLOT_EMPTY_ERROR = "noDeliverySlotEmptyError";

	String NO_DELIVERY_SLOT_ERROR = "noDeliverySlotError";

	String INVALID_DELIVERY_SLOT_ERROR = "invalidDeliverySlotError";
}
