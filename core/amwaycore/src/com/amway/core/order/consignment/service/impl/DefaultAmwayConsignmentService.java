/**
 *
 */
package com.amway.core.order.consignment.service.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.ordersplitting.ConsignmentCreationException;
import de.hybris.platform.ordersplitting.impl.DefaultConsignmentService;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.amway.core.order.consignment.service.AmwayConsignmentService;


/**
 * Service implementation for extended consignment features
 */
public class DefaultAmwayConsignmentService extends DefaultConsignmentService implements AmwayConsignmentService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayConsignmentService.class);

	/**
	 * Creation of consignments on order.
	 *
	 * @param order
	 * @param code
	 * @param orderEntries
	 * @return ConsignmentModel
	 * @throws ConsignmentCreationException
	 */
	@Override
	public ConsignmentModel createConsignment(final AbstractOrderModel order, final String code,
			final List<AbstractOrderEntryModel> orderEntries) throws ConsignmentCreationException
	{
		if (CollectionUtils.isEmpty(orderEntries))
		{
			LOG.warn("Consignment information cannot be created for empty order lines");
			return null;
		}
		final ConsignmentModel consignmentModel = super.createConsignment(order, code, orderEntries);
		for (final ConsignmentEntryModel consignmentEntryModel : consignmentModel.getConsignmentEntries())
		{
			consignmentEntryModel.setShippedQuantity(consignmentEntryModel.getQuantity());
		}
		final AbstractOrderEntryModel referenceEntryModel = orderEntries.get(0);
		consignmentModel.setShippingDate(new Date());
		consignmentModel.setDeliveryMode(order.getDeliveryMode());
		consignmentModel.setDeliveryPointOfService(referenceEntryModel.getDeliveryPointOfService());
		consignmentModel.setWarehouse(referenceEntryModel.getWareHouse());
		return consignmentModel;
	}
}
