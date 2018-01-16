package com.amway.apac.core.backorder.service.impl;

import de.hybris.platform.basecommerce.enums.InStockStatus;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.ConsignmentProcessModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.processengine.enums.ProcessState;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.warehousing.inventoryevent.service.InventoryEventService;
import de.hybris.platform.warehousing.model.AllocationEventModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.backorder.dao.AmwayApacBackOrderDao;
import com.amway.apac.core.backorder.service.AmwayApacBackOrderService;
import com.amway.apac.core.backorder.strategies.AmwayApacBackOrderSelectionStrategy;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.core.stock.strategies.impl.AmwayApacCommerceAvailabilityCalculationStrategy;


/**
 * Default implementation for AmwayApacBackOrderService
 *
 * @author ankushbhatia
 *
 */
public class DefaultAmwayApacBackOrderService implements AmwayApacBackOrderService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayApacBackOrderService.class);

	private static final String BACK_ORDER_RELEASE_EVENT_CODE = "WaitForRelease";
	private static final String CONSIGNMENT_BACKORDER_PROCESS = "consignment-backorder-process";
	private static final String EXPIRED = "EXPIRED";
	private BusinessProcessService businessProcessService;
	private ModelService modelService;
	private AmwayApacBackOrderDao amwayApacBackOrderDao;
	private AmwayApacBackOrderSelectionStrategy amwayApacBackOrderSelectionStrategy;
	private AmwayApacCommerceAvailabilityCalculationStrategy commerceAvailabilityCalculationStrategy;
	private InventoryEventService inventoryEventService;



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean releaseBackOrdersForStockLevels(final List<StockLevelModel> stockLevels)
	{
		ServicesUtil.validateParameterNotNull(stockLevels, "Parameter StockLevels cannot be null!");
		boolean releaseBackOrdersForStocks = false;
		final Map<StockLevelModel, List<AmwayBackOrderModel>> backOrderMap = getAmwayApacBackOrderSelectionStrategy()
				.getBackOrdersForRelease(stockLevels);
		if (MapUtils.isNotEmpty(backOrderMap))
		{
			for (final Entry<StockLevelModel, List<AmwayBackOrderModel>> entry : backOrderMap.entrySet())
			{
				// release all the backOrders for particular stocks
				releaseBackOrders(entry.getValue(), entry.getKey());
			}
			releaseBackOrdersForStocks = true;
		}
		return releaseBackOrdersForStocks;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean releaseBackOrdersForBaseStore(final BaseStoreModel baseStore)
	{
		ServicesUtil.validateParameterNotNull(baseStore, "Base store cannot be null!");
		boolean releaseBackOrdersForStocks = false;
		final Map<StockLevelModel, List<AmwayBackOrderModel>> backOrderMap = getAmwayApacBackOrderSelectionStrategy()
				.getBackOrdersForRelease(baseStore);
		if (MapUtils.isNotEmpty(backOrderMap))
		{
			for (final Entry<StockLevelModel, List<AmwayBackOrderModel>> entry : backOrderMap.entrySet())
			{
				// release all the backOrders for particular stocks
				releaseBackOrders(entry.getValue(), entry.getKey());
			}
			releaseBackOrdersForStocks = true;
		}
		return releaseBackOrdersForStocks;
	}

	/**
	 * This method is used to release AmwayBackOrders for the stockLevels.
	 *
	 * @param amwayBackOrders
	 *           list of AmwayBackOrders for release.
	 * @param stockLevel
	 *           Stock level which belong to amwayBackOrders.
	 *
	 */
	protected void releaseBackOrders(final List<AmwayBackOrderModel> amwayBackOrders, final StockLevelModel stockLevel)
	{
		ServicesUtil.validateParameterNotNull(stockLevel, "StockLevel cannot be null!");
		ServicesUtil.validateParameterNotNull(amwayBackOrders, "List of BackOrders cannot be null!");

		if (isStockAvailableForRelease(stockLevel))
		{
			Long available = commerceAvailabilityCalculationStrategy.calculateAvailability(Arrays.asList(stockLevel));
			int maxBoReleaseLimit = stockLevel.getMaxBoReleaseLimit();
			for (final AmwayBackOrderModel amwayBackOrder : amwayBackOrders)
			{
				final long requestedQty = getRequestedQuantity(amwayBackOrder, stockLevel);
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


	/**
	 * Check if valid AmwayBackOrder or not.
	 *
	 * @param stockLevel
	 * @param amwayBackOrder
	 * @return boolean
	 */
	private boolean isValidBackOrder(final StockLevelModel stockLevel, final AmwayBackOrderModel amwayBackOrder)
	{
		return stockLevel.getProductCode().equals(amwayBackOrder.getProduct().getCode())
				&& stockLevel.getWarehouse().equals(amwayBackOrder.getWarehouse());
	}


	/**
	 * Evaluates and returns the quantity that is present in the back order
	 *
	 * @param amwayBackOrder
	 * @param stockLevel
	 * @return long - requested AmwayBackOrder qty
	 */
	private long getRequestedQuantity(final AmwayBackOrderModel amwayBackOrder, final StockLevelModel stockLevel)
	{
		long requestedQty = 0;
		if (isValidBackOrder(stockLevel, amwayBackOrder))
		{
			for (final ConsignmentEntryModel consignmentEntry : amwayBackOrder.getConsignment().getConsignmentEntries())
			{
				final Collection<AllocationEventModel> allocationEvents = inventoryEventService
						.getAllocationEventsForOrderEntry((OrderEntryModel) consignmentEntry.getOrderEntry());
				requestedQty = allocationEvents
						.stream()
						.filter(
								allocationEvent -> allocationEvent.getConsignmentEntry().getConsignment()
										.equals(consignmentEntry.getConsignment())).mapToLong(AllocationEventModel::getQuantity).sum();
			}
		}
		return requestedQty;
	}


	/**
	 * Checks if the stock level has correct status to be able to release back orders
	 *
	 * @param stockLevel
	 * @return boolean
	 */
	private boolean isStockAvailableForRelease(final StockLevelModel stockLevel)
	{
		return !InStockStatus.BACKORDER.equals(stockLevel.getInStockStatus())
				|| !InStockStatus.FORCEOUTOFSTOCK.equals(stockLevel.getInStockStatus())
				|| !InStockStatus.TEMPORARYNOTAVAILABLE.equals(stockLevel.getInStockStatus())
				|| !InStockStatus.NOTYETAVAILABLE.equals(stockLevel.getInStockStatus())
				|| !InStockStatus.NOLONGERAVAILABLE.equals(stockLevel.getInStockStatus());
	}

	/**
	 * Used to trigger Consignment process wait node(Triggers the waitForRelease event)
	 *
	 * @param consignment
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
						// Trigger the business process event which releases the backorder
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
	 * {@inheritDoc}
	 *
	 */
	@Override
	public boolean expireBackOrders(final List<AmwayBackOrderModel> backOrders)
	{
		ServicesUtil.validateParameterNotNull(backOrders, "Parameter backOrders cannot be null!");
		boolean backorderexpired = false;
		for (final AmwayBackOrderModel amwayBackOrder : backOrders)
		{
			amwayBackOrder.setStatus(AmwayBackOrderStatus.valueOf(EXPIRED));
			modelService.save(amwayBackOrder);
			modelService.refresh(amwayBackOrder);
			backorderexpired = true;
		}
		return backorderexpired;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AmwayBackOrderModel getBackOrderByConsignment(final ConsignmentModel consignmentModel)
	{
		return amwayApacBackOrderDao.getBackOrdersByConsignment(consignmentModel);
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
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
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

	/**
	 * @param commerceAvailabilityCalculationStrategy
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

	/**
	 * @param amwayApacBackOrderDao
	 *           the amwayApacBackOrderDao to set
	 */
	@Required
	public void setAmwayApacBackOrderDao(final AmwayApacBackOrderDao amwayApacBackOrderDao)
	{
		this.amwayApacBackOrderDao = amwayApacBackOrderDao;
	}

}
