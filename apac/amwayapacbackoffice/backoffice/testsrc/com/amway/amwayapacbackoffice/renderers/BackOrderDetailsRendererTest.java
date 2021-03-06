/**
 *
 */
package com.amway.amwayapacbackoffice.renderers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.util.TaxValue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;

import com.amway.apac.backoffice.renderers.BackOrderDetailsRenderer;
import com.amway.apac.backoffice.renderers.util.ApacAttributeWithLabelRendererUtil;
import com.amway.apac.core.enums.PaymentType;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.label.impl.AmwayApacDateLabelProvider;
import com.hybris.cockpitng.core.config.impl.jaxb.editorarea.CustomSection;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.type.DataType;
import com.hybris.cockpitng.engine.WidgetInstanceManager;


/**
 * @author deepankarshukla
 *
 */
@UnitTest
public class BackOrderDetailsRendererTest
{

	@InjectMocks
	private BackOrderDetailsRenderer backOrderDetailsRenderer;

	private Component parent;

	@Mock
	private CustomSection configuration;

	@Mock
	private Object data;

	@Mock
	private DataType dataType;

	@Mock
	private WidgetInstanceManager widgetInstanceManager;

	@Mock
	private WidgetModel widgetModel;

	@Mock
	private AmwayApacDateLabelProvider dateLabelProvider;

	@Mock
	private AmwayBackOrderModel amwayBackOrderModel;
	@Mock
	private ConsignmentEntryModel consignmentEntryModel;
	@Mock
	private OrderEntryModel orderEntryModel;
	@Mock
	private ProductModel productModel;
	@Mock
	private PaymentType paymentType;
	@Mock
	private OrderModel orderModel;
	@Mock
	private TaxValue taxValue;
	@Mock
	private ConsignmentModel consignmentModel;
	@Mock
	private DeliveryModeModel deliveryModeModel;
	@Mock
	private WarehouseModel warehouseModel;
	@Mock
	private PointOfServiceModel pointOfServiceModel;
	@Mock
	private PaymentStatus paymentStatus;


	@Spy
	private final ApacAttributeWithLabelRendererUtil util = new ApacAttributeWithLabelRendererUtil();

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 60);
		when(widgetInstanceManager.getModel()).thenReturn(widgetModel);
		amwayBackOrderModel.setReleaseByDate(cal.getTime());
		Mockito.doReturn(amwayBackOrderModel).when(widgetModel.getValue("currentObject", AmwayBackOrderModel.class));
		parent = new Window();
		when(amwayBackOrderModel.getConsignment()).thenReturn(consignmentModel);
		deliveryModeModel.setCode("PICKUP");
		when(consignmentModel.getDeliveryMode()).thenReturn(deliveryModeModel);
		when(amwayBackOrderModel.getCreationtime()).thenReturn(cal.getTime());
		cal.add(Calendar.DATE, 300);
		consignmentModel.setShippingDate(cal.getTime());
		warehouseModel.setName("eastWarehouse");
		when(amwayBackOrderModel.getWarehouse()).thenReturn(warehouseModel);
		pointOfServiceModel.setName("POS1");
		when(consignmentModel.getDeliveryPointOfService()).thenReturn(pointOfServiceModel);
		when(amwayBackOrderModel.getOriginalOrder().getVolumeAbo()).thenReturn("VolumeABO1");
		when(amwayBackOrderModel.getOriginalOrder().getPaymentStatus()).thenReturn(paymentStatus.PAID);

	}

	@Test
	public void testRenderWithPickUpMode()
	{
		backOrderDetailsRenderer.render(parent, configuration, data, dataType, widgetInstanceManager);
		assertNotNull("Parent children is null", parent.getChildren());
	}
}
