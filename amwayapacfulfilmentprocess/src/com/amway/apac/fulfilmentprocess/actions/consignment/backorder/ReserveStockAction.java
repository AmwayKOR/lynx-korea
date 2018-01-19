package com.amway.apac.fulfilmentprocess.actions.consignment.backorder;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.stock.StockService;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;


/**
 * This class is used to define the Reserve Stock step which is a part of the consignment process
 */
public class ReserveStockAction extends AbstractSimpleDecisionAction<ConsignmentProcessModel>
{

	private static final Logger LOG = Logger.getLogger(ReserveStockAction.class);

	private InventoryEventService inventoryEventService;
	private StockService stockService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.processengine.spring.Action#getTransitions()
	 */
	@Override
	public Set<String> getTransitions()
	{
		return Transition.getStringValues();
	}

	/**
	 * @param inventoryEventService
	 *           the inventoryEventService to set
	 */
	public void setInventoryEventService(final InventoryEventService inventoryEventService)
	{
		this.inventoryEventService = inventoryEventService;
	}

	/**
	 * @param stockService
	 *           the stockService to set
	 */
	public void setStockService(final StockService stockService)
	{
		this.stockService = stockService;
	}

	/**
	 * Reserves stock in the form of allocation events with the consignment and release stock from the 'reserve' field
	 */
	@Override
	public Transition executeAction(final ConsignmentProcessModel process)
	{

		final ConsignmentModel consignment = process.getConsignment();
		if (consignment != null)
		{
			consignment.getConsignmentEntries().stream().filter(entry -> Objects.nonNull(entry.getOrderEntry()))
					.forEach(consignmentEntry -> {
						final AbstractOrderEntryModel orderEntry = consignmentEntry.getOrderEntry();
						stockService.release(orderEntry.getProduct(), consignment.getWarehouse(),
								consignmentEntry.getQuantity().intValue(), orderEntry.getProduct().getCode());
					});
			final Collection<AllocationEventModel> allocationEvents = inventoryEventService.createAllocationEvents(consignment);
			LOG.info(String.format("Created [%s] Allocation Event(s) for consignment : [%s] of order : [%s]",
					Integer.valueOf(allocationEvents.size()), consignment.getCode(), consignment.getOrder().getCode()));
			return Transition.OK;
		}
		LOG.error("Process has no consignment" + process.getCode());
		return Transition.NOK;
	}
}
