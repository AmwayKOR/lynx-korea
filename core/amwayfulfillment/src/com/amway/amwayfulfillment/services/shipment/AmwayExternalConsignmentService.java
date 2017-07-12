package com.amway.amwayfulfillment.services.shipment;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;

import java.util.List;

import com.amway.amwayfulfillment.order.data.AmwayConsignmentCreationInfo;
import com.amway.amwayfulfillment.order.ShippingEvent;


/**
 * Interface class containing methods related to creating order consignments from external sources
 */
public interface AmwayExternalConsignmentService
{

	/**
	 * Method will split shipping confirmation event to order consignments
	 *
	 * @param shippingEvent
	 * 		object holding shipping event data
	 * @param order
	 * 		order model to save splitted into consignments
	 * @param warehouse
	 * 		warehouse model, which is going to be associated with consignments
	 * @param initialStatus
	 * 		status for the consignments, which are going to be created
	 * @return
	 * 		list of objects with information added during creation of consignments
	 */
	List<AmwayConsignmentCreationInfo> createOrderConsignments(ShippingEvent shippingEvent, OrderModel order,
			WarehouseModel warehouse, ConsignmentStatus initialStatus);

}
