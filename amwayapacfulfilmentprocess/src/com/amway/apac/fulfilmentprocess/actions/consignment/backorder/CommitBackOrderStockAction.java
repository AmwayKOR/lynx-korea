package com.amway.apac.fulfilmentprocess.actions.consignment.backorder;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.core.stock.service.AmwayApacStockService;


/**
 * This class is used to define the Commit Stock of back orders which is a part of the consignment process
 */
public class CommitBackOrderStockAction extends AbstractSimpleDecisionAction<ConsignmentProcessModel>
{
	private static final Logger LOG = Logger.getLogger(CommitBackOrderStockAction.class);

	private AmwayApacStockService amwayApacStockService;
	private InventoryEventService inventoryEventService;
	private AmwayApacBackOrderService amwayApacBackOrderService;

	/**
	 * Commits stock by removing the allocation events with the consignment and committing stock in the 'available' field
	 *
	 */
	@Override
	public Transition executeAction(final ConsignmentProcessModel process)
	{
		final ConsignmentModel consignment = process.getConsignment();
		if (consignment != null)
		{
			consignment
					.getConsignmentEntries()
					.stream()
					.filter(entry -> Objects.nonNull(entry.getOrderEntry()))
					.forEach(
							consignmentEntry -> {
								final AbstractOrderEntryModel orderEntry = consignmentEntry.getOrderEntry();
								if (orderEntry instanceof OrderEntryModel)
								{
									final Collection<AllocationEventModel> allocationEvents = inventoryEventService
											.getAllocationEventsForOrderEntry((OrderEntryModel) orderEntry);
									LOG.info(String.format("Removing [%s] Allocation Event(s) for consignment : [%s] of order : [%s]",
											Integer.valueOf(allocationEvents.size()), consignment.getCode(), consignment.getOrder()
													.getCode()));
									final long allocatedQuantity = allocationEvents
											.stream()
											.filter(
													allocationEvent -> allocationEvent.getConsignmentEntry().getConsignment()
															.equals(consignmentEntry.getConsignment()))
											.mapToLong(AllocationEventModel::getQuantity).sum();
									getModelService().removeAll(allocationEvents);
									amwayApacStockService.updateAvailableAmount(orderEntry.getProduct(), consignment.getWarehouse(),
											(int) allocatedQuantity);
								}
							});
			updateBackOrderStatus(consignment, AmwayBackOrderStatus.RELEASED);
			return Transition.OK;
		}
		LOG.error("Process has no consignment" + process.getCode());
		return Transition.NOK;
	}

	/**
	 * Updates the status of back order model to release
	 *
	 * @param consignment
	 * @param status
	 *
	 */
	private void updateBackOrderStatus(final ConsignmentModel consignment, final AmwayBackOrderStatus status)
	{
		final AmwayBackOrderModel backOrderModel = amwayApacBackOrderService.getBackOrderByConsignment(consignment);
		if (Objects.nonNull(backOrderModel))
		{
			backOrderModel.setStatus(status);
			getModelService().save(backOrderModel);
		}
	}

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
	 * @param amwayApacStockService
	 *           the amwayApacStockService to set
	 */
	public void setAmwayApacStockService(final AmwayApacStockService amwayApacStockService)
	{
		this.amwayApacStockService = amwayApacStockService;
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
	 * @param amwayApacBackOrderService
	 *           the amwayApacBackOrderService to set
	 */
	@Required
	public void setAmwayApacBackOrderService(final AmwayApacBackOrderService amwayApacBackOrderService)
	{
		this.amwayApacBackOrderService = amwayApacBackOrderService;
	}

}
