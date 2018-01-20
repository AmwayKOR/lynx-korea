/**
 *
 */
package com.amway.amwayapacbackoffice.renderers;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.ordersplitting.model.ConsignmentEntryModel;
import de.hybris.platform.util.TaxValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Window;

import com.amway.apac.backoffice.renderers.BackOrderConsignmentEntriesRenderer;
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
public class BackOrderConsignmentEntriesRendererTest
{

	@InjectMocks
	private BackOrderConsignmentEntriesRenderer backOrderConsignmentEntriesRenderer;

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

	@Spy
	private final ApacAttributeWithLabelRendererUtil util = new ApacAttributeWithLabelRendererUtil();

	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		when(widgetInstanceManager.getModel()).thenReturn(widgetModel);
		Mockito.doReturn(amwayBackOrderModel).when(widgetModel.getValue("currentObject", AmwayBackOrderModel.class));
		parent = new Window();
		final Set<ConsignmentEntryModel> abstractOrderEntries = new HashSet<>();
		abstractOrderEntries.add(consignmentEntryModel);
		when(amwayBackOrderModel.getConsignment().getConsignmentEntries()).thenReturn(abstractOrderEntries);
		orderEntryModel.setEntryNumber(new Integer(0));
		orderEntryModel.setAliasCode("Product1");
		productModel.setName("product1");
		orderEntryModel.setBasePrice(new Double(15.00));
		orderEntryModel.setQuantity(new Long(1));
		orderEntryModel.setBusinessVolume(1.00);
		orderEntryModel.setPointValue(1.00);
		orderEntryModel.setTotalPrice(new Double(17.00));
		final Collection<TaxValue> taxValues = new HashSet<>();
		taxValues.add(taxValue);
		when(orderEntryModel.getTaxValues()).thenReturn(taxValues);
		when(amwayBackOrderModel.getOriginalOrder()).thenReturn(orderModel);
		when(orderModel.getPaymentType()).thenReturn(paymentType.ZIPP);
		when(orderEntryModel.getProduct()).thenReturn(productModel);
		when(consignmentEntryModel.getOrderEntry()).thenReturn(orderEntryModel);
	}

	@Test
	public void testRenderWithPickUpMode()
	{
		backOrderConsignmentEntriesRenderer.render(parent, configuration, data, dataType, widgetInstanceManager);
		assertNotNull("Parent children is null", parent.getChildren());
	}
}
