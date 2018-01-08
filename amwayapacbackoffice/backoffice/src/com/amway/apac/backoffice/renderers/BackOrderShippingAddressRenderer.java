/**
 *
 */
package com.amway.apac.backoffice.renderers;

import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;

import com.amway.apac.backoffice.renderers.util.ApacAttributeWithLabelRendererUtil;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.hybris.cockpitng.core.config.impl.jaxb.editorarea.CustomSection;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.type.DataType;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.hybris.cockpitng.widgets.common.WidgetComponentRenderer;


/**
 *
 */
public class BackOrderShippingAddressRenderer implements WidgetComponentRenderer<Component, CustomSection, Object>
{
	private static final Logger LOG = Logger.getLogger(BackOrderShippingAddressRenderer.class);

	/**
	 * Renders ShippingAddress view for backorder in backoffice
	 *
	 * @param CustomSection
	 * @param Component
	 * @param Object
	 * @param dataType
	 */
	@Override
	public void render(final Component parent, final CustomSection configuration, final Object data, final DataType dataType,
			final WidgetInstanceManager widgetInstanceManager)
	{
		final WidgetModel model = widgetInstanceManager.getModel();
		final AmwayBackOrderModel backOrderModel = (AmwayBackOrderModel) model.getValue("currentObject",
				(Class) AmwayBackOrderModel.class);
		prepareBackOrderShippingDetails(backOrderModel, parent);

	}

	/**
	 * @param backOrderModel
	 * @param parent
	 */
	private void prepareBackOrderShippingDetails(final AmwayBackOrderModel backOrderModel, final Component parent)
	{
		final Map<String, Object> attributeMap = new LinkedHashMap();
		final ConsignmentModel consignment = backOrderModel.getConsignment();
		if (null != consignment && null != consignment.getShippingAddress())
		{
			final AddressModel shippingaddress = consignment.getShippingAddress();
			final String itemsLabel1 = Labels.getLabel("amway.backorder.section.shippingaddress.field1");
			final String itemsvalue1 = shippingaddress.getLine1() + " " + shippingaddress.getBuilding() + " "
					+ shippingaddress.getStreetnumber();
			attributeMap.put(itemsLabel1, itemsvalue1.replace("null", ""));
			final String itemsLabel2 = Labels.getLabel("amway.backorder.section.shippingaddress.field2");
			final String itemsvalue2 = shippingaddress.getLine2() + " " + shippingaddress.getAppartment() + " "
					+ shippingaddress.getStreetname();
			attributeMap.put(itemsLabel2, itemsvalue2.replace("null", ""));
			final String itemsLabel3 = Labels.getLabel("amway.backorder.section.shippingaddress.field3");
			final String itemsvalue3 = shippingaddress.getLine3() + " " + shippingaddress.getLandMark();
			attributeMap.put(itemsLabel3, itemsvalue3.replace("null", ""));
			final String itemsLabel4 = Labels.getLabel("amway.backorder.section.shippingaddress.postalcode");
			final String itemsvalue4 = shippingaddress.getPostalcode();
			attributeMap.put(itemsLabel4, itemsvalue4.replace("null", ""));
			final String itemsLabel5 = Labels.getLabel("amway.backorder.section.shippingaddress.city");
			final String itemsvalue5 = shippingaddress.getDistrict() + "" + shippingaddress.getTown();
			attributeMap.put(itemsLabel5, itemsvalue5.replace("null", ""));
			final String itemsLabel6 = Labels.getLabel("amway.backorder.section.shippingaddress.stateprovince");
			final String itemsvalue6 = shippingaddress.getRegion().getName() + " " + shippingaddress.getRegion().getIsocode();
			attributeMap.put(itemsLabel6, itemsvalue6.replace("null", ""));
			final String itemsLabel7 = Labels.getLabel("amway.backorder.section.shippingaddress.country");
			final String itemsvalue7 = shippingaddress.getCountry().getName() + " " + shippingaddress.getCountry().getCountryCode();
			attributeMap.put(itemsLabel7, itemsvalue7.replace("null", ""));
		}
		else
		{
			LOG.info("No shipping Address found for the backorder " + backOrderModel.getConsignment().getCode());
		}

		ApacAttributeWithLabelRendererUtil.createAttributeWithLabel(parent, attributeMap, 3);

	}

}
