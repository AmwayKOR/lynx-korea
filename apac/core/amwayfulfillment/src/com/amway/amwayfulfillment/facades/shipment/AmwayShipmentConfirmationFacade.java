package com.amway.amwayfulfillment.facades.shipment;

import java.util.List;

import com.amway.amwayfulfillment.exceptions.shipment.AmwayIgnoredConsignmentException;
import com.amway.amwayfulfillment.exceptions.shipment.AmwayModelNotFoundException;
import com.amway.amwayfulfillment.order.ShippingEvent;
import com.amway.amwayfulfillment.order.data.AmwayConsignmentCreationInfo;


/**
 * Facade level interface for handling shipment confirmation
 */
public interface AmwayShipmentConfirmationFacade
{

	/**
	 * Method first will split shipping event to order consignments.
	 * And then will fire order process shipment confirmation action.
	 *
	 * @param shippingEvent
	 * 		shipping event that comes via request
	 * @param baseStoreId
	 * 		base store id code to search orders in
	 * @param orderCode
	 * 		order code
	 * @return
	 * 		list of objects with information added during creation of consignments
	 * @throws AmwayModelNotFoundException
	 * 		in case of missing order, warehouse, etc.
	 * @throws AmwayIgnoredConsignmentException
	 * 		in case of malformed shipping event or
	 */
	List<AmwayConsignmentCreationInfo> confirmShipment(ShippingEvent shippingEvent, String baseStoreId, String orderCode)
			throws AmwayModelNotFoundException, AmwayIgnoredConsignmentException;

}
