/**
 *
 */
package com.amway.apac.core.order.consignment.service;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.List;

import com.amway.core.order.consignment.service.AmwayConsignmentService;


/**
 * Interface to extend or add methods of consignment service
 */
public interface AmwayApacConsignmentService extends AmwayConsignmentService
{

	/**
	 * Method to create multiple consignments for an order.The splitting of order into consignments is done on the basis
	 * of disposition code of entries.For BackOrder entries, a seperate consignment is created and for rest all other
	 * dispsotion codes,a single consignment is created
	 *
	 * @param order
	 *           - The order for which consignments are to be created
	 * @return List of consignments created for the given order
	 * @throws ConsignmentCreationException
	 */
	List<ConsignmentModel> createConsignments(AbstractOrderModel order) throws ConsignmentCreationException;

}
