package com.amway.amwayfulfillment.test.shipment.junit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.basecommerce.enums.ConsignmentStatus;
import de.hybris.platform.core.enums.DeliveryStatus;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.amway.amwayfulfillment.constants.AmwayfulfillmentConstants;
import com.amway.amwayfulfillment.order.PackageEntry;
import com.amway.amwayfulfillment.order.SerialNumber;
import com.amway.amwayfulfillment.order.ShippingEvent;
import com.amway.amwayfulfillment.order.ShippingPackage;
import com.amway.amwayfulfillment.exceptions.shipment.AmwayShipmentConfirmationException;
import com.amway.amwayfulfillment.order.data.AmwayConsignmentCreationInfo;
import com.amway.amwayfulfillment.services.shipment.impl.DefaultAmwayExternalConsignmentService;
import com.amway.core.model.AmwayProductSerialNumberModel;


/**
 * Junit test for {@link DefaultAmwayExternalConsignmentService}
 */
@UnitTest
public class AmwayExternalConsignmentServiceTest
{

	private static final String DEFAULT_WH = "warehouse_sweden_custom_2";
	private static final String WRONG_WH = "some_wh";
	private static final String PRODUCT_1_CODE = "119802";

	private boolean createWrongProduct = false;
	private boolean createWrongWH = false;

	private OrderModel orderModel;
	private WarehouseModel warehouseModel;

	@Mock
	private ModelService modelService;

	@InjectMocks
	DefaultAmwayExternalConsignmentService defaultAmwayExternalConsignmentService = new DefaultAmwayExternalConsignmentService();

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		warehouseModel = mock(WarehouseModel.class);

		orderModel = mock(OrderModel.class);
		AbstractOrderEntryModel orderEntryModel = mock(AbstractOrderEntryModel.class);
		ProductModel productModel = mock(ProductModel.class);

		when(modelService.create(ConsignmentModel.class)).thenReturn(new ConsignmentModel());
		when(modelService.create(ConsignmentEntryModel.class)).thenReturn(new ConsignmentEntryModel());
		when(modelService.create(AmwayProductSerialNumberModel.class)).thenReturn(new AmwayProductSerialNumberModel());
		when(orderModel.getEntries()).thenReturn(Collections.singletonList(orderEntryModel));
		when(orderModel.getDeliveryStatus()).thenReturn(DeliveryStatus.IN_PROGRESS);
		when(orderEntryModel.getProduct()).thenReturn(productModel);
		when(productModel.getCode()).thenReturn(PRODUCT_1_CODE);
	}

	@Test
	public void testConsignmentsCreationFromShippingEvent() throws AmwayShipmentConfirmationException
	{
		List<AmwayConsignmentCreationInfo> infos = defaultAmwayExternalConsignmentService
				.createOrderConsignments(getTestShippingEvent(), orderModel, warehouseModel, ConsignmentStatus.IGNORE);

		long count = infos.stream()
				.filter(i -> AmwayfulfillmentConstants.SUCCESS.equals(i.getErrorCode()))
				.count();

		Assert.assertEquals(1, count);
	}

	private ShippingEvent getTestShippingEvent()
	{
		ShippingEvent shippingEvent = new ShippingEvent();
		shippingEvent.setWarehouseId(createWrongWH ? WRONG_WH : DEFAULT_WH);
		shippingEvent.setCarrier("DHL");
		shippingEvent.setTrackingId("00000530050104593676");
		shippingEvent.setTrackingLink("http://dhl.pl/tracking/00000530050104593676");
		shippingEvent.setShippingDate(new Date());
		List<ShippingPackage> packages = new ArrayList<>();
		packages.add(getTestShippingPackage());
		shippingEvent.setPackages(packages);
		return shippingEvent;
	}

	private ShippingPackage getTestShippingPackage()
	{
		ShippingPackage shippingPackage = new ShippingPackage();
		shippingPackage.setPackageId("123");
		shippingPackage.setPackageSeq("1");
		List<PackageEntry> packages = new ArrayList<>();
		packages.add(getTestPackageEntry("2016245XVDA", "1", createWrongProduct ? "111" : PRODUCT_1_CODE, "2"));
		shippingPackage.setEntries(packages);
		return shippingPackage;
	}

	private PackageEntry getTestPackageEntry(String lot, String version, String productCode, String shippedQty)
	{
		PackageEntry packageEntry = new PackageEntry();
		packageEntry.setLot(lot);
		packageEntry.setVersion(version);
		packageEntry.setProductCode(productCode);
		packageEntry.setShippedQuantity(shippedQty);
		SerialNumber serialNumber = new SerialNumber();
		serialNumber.setSerialNumber("012345");
		packageEntry.setSerialNumbers(Collections.singletonList(serialNumber));
		return packageEntry;
	}

}
