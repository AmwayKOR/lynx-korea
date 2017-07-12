package com.amway.core.returns.services.impl;

import de.hybris.platform.basecommerce.enums.RefundReason;
import de.hybris.platform.basecommerce.enums.ReturnAction;
import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.commerceservices.enums.SalesApplication;
import de.hybris.platform.core.CoreAlgorithms;
import de.hybris.platform.core.enums.DeliveryStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.StockLevelModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.returns.impl.DefaultReturnService;
import de.hybris.platform.returns.model.RefundEntryModel;
import de.hybris.platform.returns.model.ReplacementOrderEntryModel;
import de.hybris.platform.returns.model.ReplacementOrderModel;
import de.hybris.platform.returns.model.ReturnEntryModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.stock.exception.InsufficientStockLevelException;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.amway.core.enums.AmwayCartType;
import com.amway.core.returns.dao.AmwayReturnRequestDao;
import com.amway.core.returns.services.AmwayReturnService;
import com.amway.core.stock.service.AmwayCommerceStockService;
import com.amway.core.stock.service.AmwayStockService;

@Deprecated
public class DefaultAmwayReturnService extends DefaultReturnService implements AmwayReturnService
{
	private static final Logger LOG = Logger.getLogger(DefaultAmwayReturnService.class);
	private BusinessProcessService businessProcessService;
	private AmwayStockService amwayStockService;
	private AmwayCommerceStockService amwayCommerceStockService;
	private AmwayReturnRequestDao amwayReturnRequestDao;

	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	public AmwayStockService getAmwayStockService()
	{
		return amwayStockService;
	}

	public void setAmwayStockService(final AmwayStockService amwayStockService)
	{
		this.amwayStockService = amwayStockService;
	}

	public AmwayCommerceStockService getAmwayCommerceStockService()
	{
		return amwayCommerceStockService;
	}

	public void setAmwayCommerceStockService(final AmwayCommerceStockService amwayCommerceStockService)
	{
		this.amwayCommerceStockService = amwayCommerceStockService;
	}

	public AmwayReturnRequestDao getAmwayReturnRequestDao()
	{
		return amwayReturnRequestDao;
	}

	public void setAmwayReturnRequestDao(final AmwayReturnRequestDao amwayReturnRequestDao)
	{
		this.amwayReturnRequestDao = amwayReturnRequestDao;
	}

	@Override
	@Deprecated
	public boolean updateReturnEntryStatus(final ReturnEntryModel returnEntryModel, final ReturnStatus returnStatus)
	{
		ServicesUtil.validateParameterNotNull(returnEntryModel, "Return Entry cannot be null");
		ServicesUtil.validateParameterNotNull(returnStatus, "returnStatus cannot be null");
		if (returnEntryModel.getStatus() != returnStatus)
		{
			LOG.info("Return Entry status updated from " + returnEntryModel.getStatus() + " to " + returnStatus);
			returnEntryModel.setStatus(returnStatus);
			returnEntryModel.setReachedDate(new Date());
			if (returnEntryModel.getStatus().equals(ReturnStatus.CANCELED))
			{
				returnEntryModel.setExpectedQuantity(Long.valueOf(0));
				returnEntryModel.setReceivedQuantity(Long.valueOf(0));
			}
			else if (returnEntryModel.getStatus().equals(ReturnStatus.RECEIVED) || returnEntryModel.getStatus()
					.equals(ReturnStatus.DAMAGED))
			{
				returnEntryModel.setReceivedQuantity(returnEntryModel.getExpectedQuantity());
			}
			else
			{
				returnEntryModel.setReceivedQuantity(Long.valueOf(0));
			}
			getModelService().save(returnEntryModel);
			getModelService().refresh(returnEntryModel);

			if (returnEntryModel.getStatus().equals(ReturnStatus.RECEIVED))
			{
				//TO-DO deprecated from HE-369
				//adjustStock(returnEntryModel);
			}
			processReturnUpdate(returnEntryModel.getReturnRequest());
		}
		return true;
	}
	//TO-DO deprecated from HE-369
	/* protected void adjustStock(final ReturnEntryModel returnEntryModel)
	{
		LOG.info("Increasing stock for " + returnEntryModel.getOrderEntry().getProduct().getCode() + " by " + returnEntryModel
				.getReceivedQuantity().intValue());
		getAmwayStockService().adjust(returnEntryModel);
	} */

	protected void processReturnUpdate(final ReturnRequestModel returnRequestModel)
	{
		ServicesUtil.validateParameterNotNull(returnRequestModel, "ReturnRequestModel cannot be null");
		boolean allReturnItemsUpdated = true;
		for (final ReturnEntryModel returnEntryModel : returnRequestModel.getReturnEntries())
		{
			if (returnEntryModel.getStatus().equals(ReturnStatus.WAIT))
			{
				allReturnItemsUpdated = false;
				break;
			}
		}

		if (allReturnItemsUpdated)
		{
			LOG.info("Got status for all return items of " + returnRequestModel.getRMA() + " resuming business process");
			getBusinessProcessService().triggerEvent(returnRequestModel.getCode() + "_RETURNORDER_RECEIVED");
		}
	}

	@Override
	public ReplacementOrderModel createReplacementOrder(final ReturnRequestModel returnRequestModel)
	{
		final OrderModel originalOrder = returnRequestModel.getOrder();
		final ReplacementOrderModel replacementOrderModel = super.createReplacementOrder(returnRequestModel);
		replacementOrderModel.setLanguage(returnRequestModel.getOrder().getLanguage());
		replacementOrderModel.setAccount(returnRequestModel.getOrder().getAccount());
		replacementOrderModel.setStore(returnRequestModel.getOrder().getStore());
		replacementOrderModel.setSite(returnRequestModel.getOrder().getSite());
		replacementOrderModel.setSalesApplication(returnRequestModel.getOrder().getSalesApplication());
		replacementOrderModel.setType(AmwayCartType.REPLACEMENT);
		replacementOrderModel.setDeliveryMode(originalOrder.getDeliveryMode());
		replacementOrderModel.setDate(new Date());
		replacementOrderModel.setSalesApplication(SalesApplication.CALLCENTER);
		getModelService().save(replacementOrderModel);
		getModelService().save(returnRequestModel);
		return replacementOrderModel;
	}

	//TO-DO deprecated from HE-369
	/*protected Long setStockLevel(final AbstractOrderEntryModel cartEntryModel)
	{
		final ProductModel product = cartEntryModel.getProduct();
		StockLevelModel stockLevelModel = null;
		long stockLevel = 0;

		try
		{
			if (cartEntryModel.getDeliveryPointOfService() != null)
			{
				stockLevelModel = getAmwayCommerceStockService()
						.getStockLevel(product, cartEntryModel.getDeliveryPointOfService(), cartEntryModel.getOrder(),
								cartEntryModel.getQuantity().longValue(), false);
			}
			else
			{
				stockLevelModel = getAmwayCommerceStockService()
						.getStockLevel(product, cartEntryModel.getOrder().getStore(), cartEntryModel.getOrder(),
								cartEntryModel.getQuantity().longValue(), false);
			}
		}
		catch (final InsufficientStockLevelException e)
		{
			LOG.error("No stock available", e);
			return Long.valueOf(stockLevel);
		}

		if (stockLevelModel != null && StringUtils.isNotEmpty(stockLevelModel.getSkuId()) && stockLevelModel.getWarehouse() != null)
		{
			stockLevel = (stockLevelModel.getAvailable() - stockLevelModel.getReserved());
			if (stockLevel > 0 && (!stockLevelModel.getSkuId().equals(cartEntryModel.getSkuVersion()) || !stockLevelModel
					.getWarehouse().equals(cartEntryModel.getWareHouse())))
			{
				LOG.info("Setting skuId as " + stockLevelModel.getSkuId() + " and warehouse as " + stockLevelModel.getWarehouse()
						.getCode() + " on entry " + cartEntryModel.getProduct().getCode());
				cartEntryModel.setSkuVersion(stockLevelModel.getSkuId());
				cartEntryModel.setWareHouse(stockLevelModel.getWarehouse());
				getModelService().save(cartEntryModel);
			}
		}

		return Long.valueOf(stockLevel);
	} */

	protected void reserveReplacementOrderEntryStock(final ReplacementOrderEntryModel entry) throws InsufficientStockLevelException
	{
		getAmwayStockService()
				.reserve(entry.getProduct(), entry.getWareHouse(), entry.getQuantity().intValue(), entry.getSkuVersion());
	}

	@Override
	public boolean isReturnPossible(final OrderModel orderModel)
	{
		return StringUtils.isBlank(orderModel.getVersionID())
				// && orderModel.getLynxTax() != null
				&& DeliveryStatus.SHIPPED.equals(orderModel.getDeliveryStatus()) && !isReturnPeriodExpired(orderModel) && MapUtils
				.isNotEmpty(getAllReturnableEntries(orderModel));
	}

	@Override
	public Date getLatestShipDate(final OrderModel orderModel)
	{
		Date latestConsignmentDate = null;
		for (final ConsignmentModel consignmentModel : orderModel.getConsignments())
		{
			if (latestConsignmentDate == null)
			{
				latestConsignmentDate = consignmentModel.getShippingDate();
			}
			if (latestConsignmentDate != null && consignmentModel.getShippingDate() != null && consignmentModel.getShippingDate()
					.after(latestConsignmentDate))
			{
				latestConsignmentDate = consignmentModel.getShippingDate();
			}
		}
		return latestConsignmentDate;
	}

	@Override
	public boolean isReturnPeriodExpired(final OrderModel orderModel)
	{
		final Date currentDate = new Date();
		final Date latestShipDate = getLatestShipDate(orderModel);
		final int configuredReturnDays = orderModel.getSite().getReturnPeriod();
		if (configuredReturnDays < 1 || latestShipDate == null)
		{
			LOG.info(
					"Please check return configuration. Returndays: " + configuredReturnDays + " latestShipDate: " + latestShipDate);
			return false;
		}
		else
		{
			final long diffInMilis = currentDate.getTime() - latestShipDate.getTime();
			final long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
			LOG.info("Is after return period ? " + (diffInDays > configuredReturnDays));
			return diffInDays > configuredReturnDays;
		}
	}

	private double getUnitReturnPrice(final AbstractOrderEntryModel entry)
	{
		return entry.getProRatedPrice().doubleValue() / entry.getQuantity().longValue();
	}

	@Override
	public RefundEntryModel createRefund(final ReturnRequestModel request, final AbstractOrderEntryModel entry, final String notes,
			final Long expectedQuantity, final ReturnAction action, final RefundReason reason)
	{
		final RefundEntryModel returnsEntry = (RefundEntryModel) getModelService().create(RefundEntryModel.class);
		returnsEntry.setOrderEntry(entry);
		returnsEntry.setAction(action);
		returnsEntry.setNotes(notes);
		returnsEntry.setReason(reason);
		returnsEntry.setReturnRequest(request);
		returnsEntry.setExpectedQuantity(expectedQuantity);
		returnsEntry.setStatus(ReturnStatus.WAIT);
		//		returnsEntry.setAmount(BigDecimal.valueOf(entry.getUnitReturnPrice().doubleValue() * expectedQuantity.doubleValue()));
		returnsEntry.setAmount(BigDecimal.valueOf(getUnitReturnPrice(entry) * expectedQuantity.doubleValue()));
		getModelService().save(returnsEntry);
		return returnsEntry;
	}

	@Override
	@Deprecated
	public void addReplaceOrderEntries(final ReplacementOrderModel order, final List<ReturnEntryModel> replacements)
	{
		double orderTotal = 0.0d;
		double subTotal = 0.0d;
		double totalBV = 0.0d;
		double totalPV = 0.0d;
		int entryNumber = 0;
		for (final ReturnEntryModel replacement : replacements)
		{
			if (replacement.getAction().equals(ReturnAction.HOLD))
			{
				LOG.warn("Skipping 'replacement order entry' creation, because assigned 'replacement' instance is still on 'HOLD'");
				break;
			}

			if (replacement.getReceivedQuantity().longValue() > 0l)
			{
				final ReplacementOrderEntryModel entry = (ReplacementOrderEntryModel) getModelService()
						.create(ReplacementOrderEntryModel.class);
				final AbstractOrderEntryModel originalOrderEntryModel = replacement.getOrderEntry();
				entry.setDeliveryPointOfService(originalOrderEntryModel.getDeliveryPointOfService());
				entry.setProduct(originalOrderEntryModel.getProduct());
				entry.setOrder(order);
				entry.setEntryNumber(Integer.valueOf(entryNumber++));
				entry.setQuantity(replacement.getExpectedQuantity());
				entry.setUnit(originalOrderEntryModel.getUnit());
				entry.setVolumeAbo(originalOrderEntryModel.getVolumeAbo());
				entry.setGiveAway(originalOrderEntryModel.getGiveAway());
				final double entryBV =
						(originalOrderEntryModel.getBusinessVolume() / originalOrderEntryModel.getQuantity().longValue()) * replacement
								.getReceivedQuantity().longValue();
				entry.setBusinessVolume(CoreAlgorithms.round(entryBV, 2));
				final double entryPV =
						(originalOrderEntryModel.getPointValue() / originalOrderEntryModel.getQuantity().longValue()) * replacement
								.getReceivedQuantity().longValue();
				entry.setPointValue(CoreAlgorithms.round(entryPV, 2));
				entry.setRetailBasePrice(
						Double.valueOf(CoreAlgorithms.round(originalOrderEntryModel.getRetailBasePrice().doubleValue(), 2)));
				entry.setUnitReturnPrice(originalOrderEntryModel.getUnitReturnPrice());
				entry.setBasePrice(originalOrderEntryModel.getBasePrice());
				entry.setAboBasePrice(originalOrderEntryModel.getAboBasePrice());
				entry.setTotalPrice(Double.valueOf(CoreAlgorithms
						.round(originalOrderEntryModel.getBasePrice().doubleValue() * replacement.getReceivedQuantity().longValue(),
								2)));
				entry.setProRatedPrice(Double.valueOf(CoreAlgorithms
						.round(entry.getUnitReturnPrice().doubleValue() * replacement.getReceivedQuantity().longValue(), 2)));
				getModelService().save(entry);
				orderTotal = orderTotal + entry.getProRatedPrice().doubleValue();
				subTotal = subTotal + entry.getTotalPrice().doubleValue();
				totalBV = totalBV + entryBV;
				totalPV = totalPV + entryPV;
				getModelService().save(entry);
				getModelService().refresh(entry);
				try
				{
					//TO-DO deprecated from HE-369
					//setStockLevel(entry);
					reserveReplacementOrderEntryStock(entry);
				}
				catch (final InsufficientStockLevelException exception)
				{
					LOG.error("Invalid stock for return entry " + entry.getProduct().getCode(), exception);
				}
			}
		}
		order.setBusinessVolume(CoreAlgorithms.round(totalBV, 2));
		order.setPointValue(CoreAlgorithms.round(totalPV, 2));
		order.setTotalPrice(Double.valueOf(CoreAlgorithms.round(orderTotal, 2)));
		order.setSubtotal(Double.valueOf(CoreAlgorithms.round(subTotal, 2)));
		getModelService().save(order);
		getModelService().refresh(order);
	}

	@Override
	public ReturnRequestModel getReturnRequest(final ReplacementOrderModel replacementOrderModel)
	{
		ServicesUtil.validateParameterNotNull(replacementOrderModel, "ReplacementOrder cannot be null");
		return getAmwayReturnRequestDao().getReturnRequest(replacementOrderModel);
	}
}
