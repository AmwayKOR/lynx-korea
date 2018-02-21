package com.amway.amwayfulfillment.services.shipment;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;


/**
 * Validation strategy for consignments
 */
public interface AmwayConsignmentValidationStrategy
{
	boolean validate(AbstractOrderEntryModel orderEntry);
}
