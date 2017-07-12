package com.amway.amwayfulfillment.facades.shipment.impl;

import static com.amway.amwayfulfillment.constants.AmwayfulfillmentConstants.*;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.core.enums.DeliveryStatus;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.WarehouseService;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.Assert;

import com.amway.amwayfulfillment.constants.AmwayfulfillmentConstants;
import com.amway.amwayfulfillment.exceptions.shipment.AmwayIgnoredConsignmentException;
import com.amway.amwayfulfillment.exceptions.shipment.AmwayModelNotFoundException;
import com.amway.amwayfulfillment.order.ShippingEvent;
import com.amway.amwayfulfillment.facades.shipment.AmwayShipmentConfirmationFacade;
import com.amway.amwayfulfillment.order.data.AmwayConsignmentCreationInfo;
import com.amway.amwayfulfillment.services.shipment.AmwayConsignmentValidationStrategy;
import com.amway.amwayfulfillment.services.shipment.AmwayExternalConsignmentService;
import com.amway.core.event.orderprocessing.AmwayShipmentConfirmationEvent;


/**
 * Default implementation of {@link AmwayShipmentConfirmationFacade}
 */
public class DefaultAmwayShipmentConfirmationFacade implements AmwayShipmentConfirmationFacade
{
	private BaseStoreService baseStoreService;
	private CustomerAccountService customerAccountService;
	private EventService eventService;
	private AmwayExternalConsignmentService amwayExternalConsignmentService;
	private Set<DeliveryStatus> allowedDeliveryStatuses;
	private WarehouseService warehouseService;
	private AmwayConsignmentValidationStrategy consignmentValidationStrategy;

	@Override
	public List<AmwayConsignmentCreationInfo> confirmShipment(final ShippingEvent shippingEvent, final String baseStoreId,
			final String orderCode) throws AmwayModelNotFoundException, AmwayIgnoredConsignmentException
	{
		Assert.notNull(shippingEvent, "shipping event cannot be null!");
		Assert.notNull(baseStoreId, "baseStoreId cannot be null!");
		Assert.notNull(orderCode, "order code cannot be null!");

		final BaseStoreModel baseStore = getBaseStore(baseStoreId);
		final OrderModel order = getOrder(orderCode, baseStore);
		final WarehouseModel warehouse = getWarehouse(shippingEvent.getWarehouseId());

		final ConsignmentStatus initialStatus = resolveInitialStatus(order);

		// @formatter:off
		final List<AmwayConsignmentCreationInfo> consignmentModifications = amwayExternalConsignmentService
				.createOrderConsignments(shippingEvent, order, warehouse, initialStatus);

		final Optional<AmwayConsignmentCreationInfo> shouldCheckQuantity = consignmentModifications.stream()
				.filter(cm -> AmwayfulfillmentConstants.SUCCESS.equals(cm.getErrorCode()))
				.findFirst();
		// @formatter:on

		if (!shouldCheckQuantity.isPresent())
		{
			return consignmentModifications;
		}

		if (ConsignmentStatus.IGNORE.equals(initialStatus))
		{
			throw new AmwayIgnoredConsignmentException(IGNORED_CONSIGNMENT_ERROR, IGNORED_CONSIGNMENT_MSG);
		}

		order.getEntries().stream()
				.filter(oe -> !consignmentValidationStrategy.validate(oe))
				.forEach(oe -> addModification(order, consignmentModifications));

		getEventService().publishEvent(new AmwayShipmentConfirmationEvent(order));

		return consignmentModifications;
	}

	private void addModification(final OrderModel order, final List<AmwayConsignmentCreationInfo> consignmentModifications)
	{
		final AmwayConsignmentCreationInfo consignmentModification = new AmwayConsignmentCreationInfo();
		consignmentModification.setErrorCode(AmwayfulfillmentConstants.DIFFERENT_SHIPPED_ERROR);
		consignmentModification.setStatusMessage(String.format(DIFFERENT_SHIPPED_MSG, order.getCode()));
		consignmentModifications.add(consignmentModification);
	}

	private ConsignmentStatus resolveInitialStatus(final OrderModel order)
	{

		boolean shouldIgnore = !isOrderDeliveryStatusAllowed(order.getDeliveryStatus());

		// @formatter:off
		shouldIgnore |= order.getConsignments().stream()
				.filter(c -> c.getStatus().equals(ConsignmentStatus.SHIPPED))
				.findFirst()
				.isPresent();
		// @formatter:on

		return shouldIgnore ? ConsignmentStatus.IGNORE : ConsignmentStatus.SHIPPED;
	}

	private OrderModel getOrder(final String orderCode, final BaseStoreModel baseStore) throws AmwayModelNotFoundException
	{
		try
		{
			return getCustomerAccountService().getOrderForCode(orderCode, baseStore);
		}
		catch (final SystemException ex)
		{
			throw new AmwayModelNotFoundException(ORDER_MISSING_ERROR, ORDER_MISSING_MSG, orderCode);
		}
	}

	private WarehouseModel getWarehouse(final String warehouseId) throws AmwayModelNotFoundException
	{
		try
		{
			return getWarehouseService().getWarehouseForCode(warehouseId);
		}
		catch (final SystemException ex)
		{
			throw new AmwayModelNotFoundException(WAREHOUSE_MISSING_ERROR, WAREHOUSE_MISSING_MSG, warehouseId);
		}
	}

	private BaseStoreModel getBaseStore(final String baseStoreId) throws AmwayModelNotFoundException
	{
		try
		{
			return getBaseStoreService().getBaseStoreForUid(baseStoreId);
		}
		catch (final SystemException ex)
		{
			throw new AmwayModelNotFoundException(BASE_STORE_MISSING_ERROR, BASE_STORE_MISSING_MSG, baseStoreId);
		}
	}

	private boolean isOrderDeliveryStatusAllowed(final DeliveryStatus deliveryStatus)
	{
		return CollectionUtils.isEmpty(getAllowedDeliveryStatuses()) || getAllowedDeliveryStatuses().contains(deliveryStatus);
	}

	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	public void setBaseStoreService(BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	public CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	public void setCustomerAccountService(CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	public EventService getEventService()
	{
		return eventService;
	}

	public void setEventService(EventService eventService)
	{
		this.eventService = eventService;
	}

	public AmwayExternalConsignmentService getAmwayExternalConsignmentService()
	{
		return amwayExternalConsignmentService;
	}

	public void setAmwayExternalConsignmentService(AmwayExternalConsignmentService amwayExternalConsignmentService)
	{
		this.amwayExternalConsignmentService = amwayExternalConsignmentService;
	}

	public Set<DeliveryStatus> getAllowedDeliveryStatuses()
	{
		return allowedDeliveryStatuses;
	}

	public void setAllowedDeliveryStatuses(Set<DeliveryStatus> allowedDeliveryStatuses)
	{
		this.allowedDeliveryStatuses = allowedDeliveryStatuses;
	}

	public WarehouseService getWarehouseService()
	{
		return warehouseService;
	}

	public void setWarehouseService(WarehouseService warehouseService)
	{
		this.warehouseService = warehouseService;
	}

	public AmwayConsignmentValidationStrategy getConsignmentValidationStrategy()
	{
		return consignmentValidationStrategy;
	}

	public void setConsignmentValidationStrategy(AmwayConsignmentValidationStrategy consignmentValidationStrategy)
	{
		this.consignmentValidationStrategy = consignmentValidationStrategy;
	}
}
