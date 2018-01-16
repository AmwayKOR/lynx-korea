/**
 *
 */
package com.amway.amwayapacbackoffice.renderers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.hybris.platform.core.enums.PaymentStatus;
import de.hybris.platform.core.model.c2l.CountryModel;
import de.hybris.platform.core.model.c2l.RegionModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;
import de.hybris.platform.ordersplitting.model.WarehouseModel;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.util.TaxValue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;

import com.amway.apac.backoffice.renderers.BackOrderShippingAddressRenderer;
import com.amway.apac.backoffice.renderers.util.ApacAttributeWithLabelRendererUtil;
import com.amway.apac.core.enums.AmwayBackOrderStatus;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.label.impl.AmwayApacDateLabelProvider;
import com.amway.lynxcore.enums.PaymentType;
import com.hybris.cockpitng.core.config.impl.jaxb.editorarea.CustomSection;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.type.DataType;
import com.hybris.cockpitng.engine.WidgetInstanceManager;


/**
 * @author deepankarshukla
 *
 */
public class BackOrderShippingAddressRendererTest
{

	@InjectMocks
	private BackOrderShippingAddressRenderer backOrderShippingAddressRenderer;

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
	@Mock
	private AmwayBackOrderStatus amwayBackOrderStatus;
	@Mock
	private AddressModel addressModel;
	@Mock
	private RegionModel regionModel;
	@Mock
	private CountryModel countryModel;


	@Spy
	private final ApacAttributeWithLabelRendererUtil util = new ApacAttributeWithLabelRendererUtil();

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		when(widgetInstanceManager.getModel()).thenReturn(widgetModel);
		Mockito.doReturn(amwayBackOrderModel).when(widgetModel.getValue("currentObject", AmwayBackOrderModel.class));
		parent = new Window();
		consignmentModel.setCode("Consignment1");
		when(amwayBackOrderModel.getConsignment()).thenReturn(consignmentModel);
		addressModel.setLine1("House no1");
		addressModel.setBuilding("");
		addressModel.setStreetnumber("");

		addressModel.setLine2("second floor");
		addressModel.setAppartment("");
		addressModel.setStreetname("");

		addressModel.setLine3("opp mall");
		addressModel.setLandMark("");

		addressModel.setPostalcode("112001");

		addressModel.setDistrict("newyork");
		addressModel.setTown("newyork");
		regionModel.setName("Houton");
		regionModel.setIsocode("HO");
		countryModel.setName("India");
		countryModel.setIsocode("IN");
		addressModel.setRegion(regionModel);
		addressModel.setCountry(countryModel);
		when(consignmentModel.getShippingAddress()).thenReturn(addressModel);


	}

	@Test
	public void testRenderWithPickUpMode()
	{
		backOrderShippingAddressRenderer.render(parent, configuration, data, dataType, widgetInstanceManager);
		assertNotNull("Parent children is null", parent.getChildren());
	}
}
