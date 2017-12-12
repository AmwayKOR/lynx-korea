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
 * Interface to extend features of consignment service
 */
public interface AmwayApacConsignmentService extends AmwayConsignmentService
{


	/**
	 * Method to create multiple consignments for an order
	 *
	 * @param order
	 * @return List of consignments
	 * @throws ConsignmentCreationException
	 */
	List<ConsignmentModel> createConsignments(AbstractOrderModel order) throws ConsignmentCreationException;

}
