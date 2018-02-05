/**
 *
 */
package com.amway.apac.core.stock.services.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.stock.services.AmwayApacCommerceStockService;
import com.amway.core.stock.service.impl.DefaultAmwayCommerceStockService;


/**
 *
 * Class used to override and implement methods of commerce stock stock service
 */
public class DefaultAmwayApacCommerceStockService extends DefaultAmwayCommerceStockService
		implements AmwayApacCommerceStockService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAmwayCommerceStockService.class);
	private InventoryEventService inventoryEventService;
	private ModelService modelService;

	/**
	 * Overding amwaycore implementation to continue the checkout flow.
	 */
	@Override
	public void reserve(final AbstractOrderModel abstractOrderModel)
	{
		for (final AbstractOrderEntryModel abstractOrderEntryModel : abstractOrderModel.getEntries())
		{
			try
			{
				final WarehouseModel warehouse = abstractOrderEntryModel.getWareHouse();

				getAmwayStockService().reserve(abstractOrderEntryModel.getProduct(), warehouse,
						abstractOrderEntryModel.getQuantity().intValue(), abstractOrderEntryModel.getSkuVersion());
			}
			catch (final InsufficientStockLevelException e)
			{
				LOG.error("Error in reserving stock for skuId " + abstractOrderEntryModel.getSkuVersion() + " on order "
						+ abstractOrderModel.getCode(), e);
			}
		}
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
