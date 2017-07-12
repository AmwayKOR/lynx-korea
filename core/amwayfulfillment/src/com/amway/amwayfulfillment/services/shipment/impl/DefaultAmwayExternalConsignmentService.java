package com.amway.amwayfulfillment.services.shipment.impl;

import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.amway.amwayfulfillment.constants.AmwayfulfillmentConstants;
import com.amway.amwayfulfillment.order.PackageEntry;
import com.amway.amwayfulfillment.order.SerialNumber;
import com.amway.amwayfulfillment.order.ShippingEvent;
import com.amway.amwayfulfillment.order.ShippingPackage;
import com.amway.amwayfulfillment.model.ConsignmentSerialNumberModel;
import com.amway.amwayfulfillment.order.data.AmwayConsignmentCreationInfo;
import com.amway.amwayfulfillment.services.shipment.AmwayExternalConsignmentService;


/**
 * Default implementation of {@link AmwayExternalConsignmentService}
 */
public class DefaultAmwayExternalConsignmentService implements AmwayExternalConsignmentService
{
	private ModelService modelService;

	@Override
	public List<AmwayConsignmentCreationInfo> createOrderConsignments(final ShippingEvent shippingEvent, final OrderModel order,
			final WarehouseModel warehouse, final ConsignmentStatus initialStatus)
	{
		final List<AmwayConsignmentCreationInfo> modifications = new ArrayList<>();

		final ConsignmentModel consignment = createConsignment(order, warehouse, initialStatus);
		populateConsignment(shippingEvent, consignment);

		final Set<ConsignmentModel> consignments = new HashSet<>(order.getConsignments());
		final Set<ConsignmentEntryModel> consignmentEntries = new HashSet<>();
		for (final ShippingPackage shippingPackage : shippingEvent.getPackages())
		{
			for (final PackageEntry packageEntry : shippingPackage.getEntries())
			{
				// @formatter:off
				final Optional<AbstractOrderEntryModel> orderEntry = order.getEntries().stream()
						.filter(oe -> oe.getProduct().getCode().equalsIgnoreCase(packageEntry.getProductCode()))
						.findFirst();
				// @formatter:on

				if (!orderEntry.isPresent())
				{
					final AmwayConsignmentCreationInfo consignmentModification = new AmwayConsignmentCreationInfo();
					consignmentModification.setErrorCode(AmwayfulfillmentConstants.PRODUCT_MISSING_ERROR);
					consignmentModification.setStatusMessage(String.format(AmwayfulfillmentConstants.PRODUCT_MISSING_MSG,
							order.getCode(), packageEntry.getProductCode()));
					modifications.add(consignmentModification);
				}
				else
				{
					final ConsignmentEntryModel consignmentEntry = createConsignmentEntry(shippingPackage, packageEntry, consignment,
							orderEntry.get());

					final AmwayConsignmentCreationInfo consignmentModification = new AmwayConsignmentCreationInfo();
					consignmentModification.setErrorCode(AmwayfulfillmentConstants.SUCCESS);
					modifications.add(consignmentModification);

					consignmentEntries.add(consignmentEntry);
				}
			}
		}

		if (consignmentEntries.isEmpty())
		{
			return modifications;
		}

		consignment.setConsignmentEntries(consignmentEntries);

		consignments.add(consignment);
		getModelService().saveAll(consignment, order);

		return modifications;
	}

	private void populateConsignment(final ShippingEvent shippingEvent, final ConsignmentModel consignment)
	{
		consignment.setCarrier(shippingEvent.getCarrier());
		consignment.setTrackingID(shippingEvent.getTrackingId());
		consignment.setTrackingLink(shippingEvent.getTrackingLink());
		consignment.setShippingDate(shippingEvent.getShippingDate());
	}

	private ConsignmentModel createConsignment(final OrderModel order, final WarehouseModel warehouse, final ConsignmentStatus initialStatus)
	{
		final ConsignmentModel consignment = getModelService().create(ConsignmentModel.class);
		final String consignmentCode = "c" + order.getCode();
		consignment.setCode(consignmentCode);
		consignment.setOrder(order);
		consignment.setShippingAddress(order.getDeliveryAddress());
		consignment.setDeliveryMode(order.getDeliveryMode());

		consignment.setStatus(initialStatus);
		consignment.setWarehouse(warehouse);

		return consignment;
	}

	private ConsignmentEntryModel createConsignmentEntry(final ShippingPackage shippingPackage, final PackageEntry packageEntry,
			final ConsignmentModel consignment, final AbstractOrderEntryModel orderEntry)
	{
		final ConsignmentEntryModel consignmentEntry = getModelService().create(ConsignmentEntryModel.class);
		consignmentEntry.setConsignment(consignment);
		consignmentEntry.setPackageId(shippingPackage.getPackageId());
		consignmentEntry.setPackageSeq(shippingPackage.getPackageSeq());
		consignmentEntry.setLot(packageEntry.getLot());
		consignmentEntry.setVersion(packageEntry.getVersion());

		// @formatter:off
		final List<ConsignmentSerialNumberModel> serialNumbers = new ArrayList<>();
		CollectionUtils.emptyIfNull(packageEntry.getSerialNumbers()).stream()
				.filter(sn -> StringUtils.isNotBlank(sn.getSerialNumber()))
				.forEach(sn -> serialNumbers.add(createConsignmentEntrySerialNumber(sn)));
		consignmentEntry.setSerialNumbers(serialNumbers);
		// @formatter:on

		consignmentEntry.setOrderEntry(orderEntry);

		final Long quantity = Long.valueOf(packageEntry.getShippedQuantity());
		consignmentEntry.setQuantity(quantity);
		consignmentEntry.setShippedQuantity(quantity);

		return consignmentEntry;
	}

	private ConsignmentSerialNumberModel createConsignmentEntrySerialNumber(final SerialNumber serialNumber)
	{
		final ConsignmentSerialNumberModel serialNumberModel = getModelService().create(ConsignmentSerialNumberModel.class);
		serialNumberModel.setSerialNumber(serialNumber.getSerialNumber());
		getModelService().save(serialNumberModel);
		return serialNumberModel;
	}


	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

}
