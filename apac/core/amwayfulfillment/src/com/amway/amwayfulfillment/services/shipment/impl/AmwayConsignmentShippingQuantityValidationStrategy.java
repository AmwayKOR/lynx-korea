package com.amway.amwayfulfillment.services.shipment.impl;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import com.amway.amwayfulfillment.services.shipment.AmwayConsignmentValidationStrategy;

/**
 * Default implementation of {@link AmwayConsignmentValidationStrategy}
 */
public class AmwayConsignmentShippingQuantityValidationStrategy implements AmwayConsignmentValidationStrategy
{
	/**
	 * @param orderEntry
	 * 	order entry model
	 * @return
	 * 	true if actualShippedQuantity > 0 and actualShippedQuantity == orderedQuantity
	 */
	@Override
	public boolean validate(final AbstractOrderEntryModel orderEntry)
	{
		final long actualShippedQuantity = getActualShippedQuantity(orderEntry);

		final long orderedQuantity = orderEntry.getQuantity().longValue();

		return actualShippedQuantity == orderedQuantity;
	}

	protected long getActualShippedQuantity(final AbstractOrderEntryModel orderEntry)
	{
		// @formatter:off
		return orderEntry.getConsignmentEntries().stream()
				.filter(ce -> ConsignmentStatus.SHIPPED.equals(getConsignmentStatus(ce)))
				.mapToLong(ConsignmentEntryModel::getShippedQuantity)
				.sum();
		// @formatter:on
	}

	private ConsignmentStatus getConsignmentStatus(final ConsignmentEntryModel consignmentEntry)
	{
		final ConsignmentModel consignment = consignmentEntry.getConsignment();
		return consignment != null ? consignment.getStatus() : null;
	}
}
