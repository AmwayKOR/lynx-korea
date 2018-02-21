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

	/** Error if No empty delivery slot found. */
	String NO_DELIVERY_SLOT_EMPTY_ERROR = "noDeliverySlotEmptyError";

	/** Error if No delivery slot found. */
	String NO_DELIVERY_SLOT_ERROR = "noDeliverySlotError";

	/** Error for Invalid delivery slot. */
	String INVALID_DELIVERY_SLOT_ERROR = "invalidDeliverySlotError";
}
