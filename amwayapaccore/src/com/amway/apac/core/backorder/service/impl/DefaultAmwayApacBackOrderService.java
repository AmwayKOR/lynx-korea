/**
 *
 */
package com.amway.apac.core.backorder.service.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderSelectionStrategy;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.core.stock.strategies.impl.AmwayApacCommerceAvailabilityCalculationStrategy;


/**
 * Default implementation for AmwayApacBackOrderReleaseStrategy
 *
 * @author ankushbhatia
 *
 */
public class DefaultAmwayApacBackOrderService implements AmwayApacBackOrderService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacBackOrderService.class);

	private static final String BACK_ORDER_RELEASE_EVENT_CODE = "WaitForRelease";
	private static final String CONSIGNMENT_BACKORDER_PROCESS = "consignment-backorder-process";

	private BusinessProcessService businessProcessService;
	private AmwayApacBackOrderSelectionStrategy amwayApacBackOrderSelectionStrategy;
	private AmwayApacCommerceAvailabilityCalculationStrategy commerceAvailabilityCalculationStrategy;
	private InventoryEventService inventoryEventService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void releaseBackOrders(final List<AmwayBackOrderModel> amwayBackOrders, final StockLevelModel stockLevel)
	{
		if (CollectionUtils.isNotEmpty(amwayBackOrders) && Objects.nonNull(stockLevel)
				&& !InStockStatus.BACKORDER.equals(stockLevel.getInStockStatus()))
		{
			Long available = commerceAvailabilityCalculationStrategy.calculateAvailability(Arrays.asList(stockLevel));
			if (Objects.nonNull(available))
			{
				int maxBoReleaseLimit = stockLevel.getMaxBoReleaseLimit();
				for (final AmwayBackOrderModel amwayBackOrder : amwayBackOrders)
				{
					long requestedQty = 0;
					if (stockLevel.getProduct().equals(amwayBackOrder.getProduct())
							&& stockLevel.getWarehouse().equals(amwayBackOrder.getWarehouse()))
					{
						for (final ConsignmentEntryModel consignmentEntry : amwayBackOrder.getConsignment().getConsignmentEntries())
						{
							final Collection<AllocationEventModel> allocationEvents = inventoryEventService
									.getAllocationEventsForOrderEntry((OrderEntryModel) consignmentEntry.getOrderEntry());
							requestedQty = allocationEvents
									.stream()
									.filter(
											allocationEvent -> allocationEvent.getConsignmentEntry().getConsignment()
													.equals(consignmentEntry.getConsignment())).mapToLong(AllocationEventModel::getQuantity)
									.sum();
						}
					}
					if (requestedQty <= available.longValue() && requestedQty <= maxBoReleaseLimit)
					{
						available = Long.valueOf(available.longValue() - requestedQty);
						maxBoReleaseLimit -= requestedQty;
						triggerConsignmentProcess(amwayBackOrder.getConsignment());
					}

					if (available.longValue() == 0 || maxBoReleaseLimit == 0)
					{
						break;
					}
				}
			}
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void releaseBackOrdersForStocks(final List<StockLevelModel> stockLevels)
	{
		final Map<StockLevelModel, List<AmwayBackOrderModel>> backOrderMap = getAmwayApacBackOrderSelectionStrategy()
				.getBackOrdersForRelease(stockLevels);
		for (final Entry<StockLevelModel, List<AmwayBackOrderModel>> entry : backOrderMap.entrySet())
		{
			//release all the backOrders for particular stocks
			releaseBackOrders(entry.getValue(), entry.getKey());
		}

	}




	/**
	 * Used to trigger Consignment process wait node
	 *
	 * @param ConsignmentModel
	 */
	private void triggerConsignmentProcess(final ConsignmentModel consignment)
	{
		if (Objects.nonNull(consignment))
		{
			try
			{
				for (final Iterator<ConsignmentProcessModel> iterator = consignment.getConsignmentProcesses().iterator(); iterator
						.hasNext();)
				{
					final ConsignmentProcessModel process = iterator.next();
					if (ProcessState.WAITING.equals(process.getProcessState())
							&& CONSIGNMENT_BACKORDER_PROCESS.equals(process.getProcessDefinitionName()))
					{
						final String eventCode = (new StringBuilder(String.valueOf(process.getConsignment().getCode()))).append("_")
								.append(BACK_ORDER_RELEASE_EVENT_CODE).toString();
						//Trigger the business process event which release AmwayBackOrder
						getBusinessProcessService().triggerEvent(eventCode);
						LOG.info("BackOrder consignment triggered successfully for code : " + consignment.getCode());
					}
				}
			}
			catch (final Exception e)
			{
				logDebugInfo(e, "BackOrder consignment process not triggered successfully for code : " + consignment.getCode());
			}
		}

	}


	/**
	 * To print log in debug mode
	 *
	 * @param exc
	 * @param message
	 */
	protected void logDebugInfo(final Exception exc, final String message)
	{
		if (Objects.nonNull(exc) && LOG.isDebugEnabled())
		{
			LOG.debug(message, exc);
		}
	}

	/**
	 * @return the businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	/**
	 * @param businessProcessService
	 *           the businessProcessService to set
	 */
	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the amwayApacBackOrderSelectionStrategy
	 */
	public AmwayApacBackOrderSelectionStrategy getAmwayApacBackOrderSelectionStrategy()
	{
		return amwayApacBackOrderSelectionStrategy;
	}

	/**
	 * @param amwayApacBackOrderSelectionStrategy
	 *           the amwayApacBackOrderSelectionStrategy to set
	 */
	@Required
	public void setAmwayApacBackOrderSelectionStrategy(
			final AmwayApacBackOrderSelectionStrategy amwayApacBackOrderSelectionStrategy)
	{
		this.amwayApacBackOrderSelectionStrategy = amwayApacBackOrderSelectionStrategy;
	}

	/**
	 * @param commerceAvailabilityCalculationStrategy
	 *           the commerceAvailabilityCalculationStrategy to set
	 */
	@Required
	public void setCommerceAvailabilityCalculationStrategy(
			final AmwayApacCommerceAvailabilityCalculationStrategy commerceAvailabilityCalculationStrategy)
	{
		this.commerceAvailabilityCalculationStrategy = commerceAvailabilityCalculationStrategy;
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
}
