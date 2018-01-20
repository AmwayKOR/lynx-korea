/**
 *
 */
package com.amway.apac.core.stock.service.impl;

import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.stock.service.AmwayApacCommerceStockService;
import com.amway.core.stock.service.impl.DefaultAmwayCommerceStockService;


/**
 *
 * Class used to override and implement methods of commerce stock stock service
 */
public class DefaultAmwayApacCommerceStockService extends DefaultAmwayCommerceStockService
		implements AmwayApacCommerceStockService
{
	private InventoryEventService inventoryEventService;
	private ModelService modelService;

	/**
	 * Overding amwaycore implementation to continue the checkout flow.
	 */
	@Override
	public void reserve(final AbstractOrderModel abstractOrderModel)
	{
		// TODO implement stock reserve logic here
	}

	/**
	 * {@inheritDoc}
	 *
	 * This API release inventory by removing allocation events attached to the order entry
	 */
	@Override
	public void releaseAllocationEvents(final OrderEntryModel orderEntry)
	{
		ServicesUtil.validateParameterNotNull(orderEntry, "OrderEntry cannot be null!");
		//release any allocation event attached to any of the order entries for this order(instanceOf as the allocation events are only attached to order entries and not cart entries)
		final Collection<AllocationEventModel> allocationEvents = inventoryEventService
				.getAllocationEventsForOrderEntry(orderEntry);
		if (CollectionUtils.isNotEmpty(allocationEvents))
		{
			modelService.removeAll(allocationEvents);
		}
	}

	/**
	 * @param inventoryEventService
	 *           the inventoryEventService to set
	 */
	@Required
	public void setInventoryEventService(final InventoryEventService inventoryEventService)
	{
		this.inventoryEventService = inventoryEventService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
