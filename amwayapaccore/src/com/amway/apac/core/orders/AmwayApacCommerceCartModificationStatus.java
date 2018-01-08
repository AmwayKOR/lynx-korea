/**
 *
 */
package com.amway.apac.core.orders;

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
