/**
 *
 */
package com.amway.apac.backoffice.renderers;

import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
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
public class BackOrderDetailsRenderer implements WidgetComponentRenderer<Component, CustomSection, Object>
{

	private static final Logger LOG = Logger.getLogger(BackOrderDetailsRenderer.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.hybris.cockpitng.widgets.common.WidgetComponentRenderer#render(java.lang.Object, java.lang.Object,
	 * java.lang.Object, com.hybris.cockpitng.dataaccess.facades.type.DataType,
	 * com.hybris.cockpitng.engine.WidgetInstanceManager)
	 */
	@Override
	public void render(final Component parent, final CustomSection configuration, final Object data, final DataType dataType,
			final WidgetInstanceManager widgetInstanceManager)
	{
		final WidgetModel model = widgetInstanceManager.getModel();
		final AmwayBackOrderModel backOrderModel = (AmwayBackOrderModel) model.getValue("currentObject",
				(Class) AmwayBackOrderModel.class);
		prepareBackOrderDetails(backOrderModel, parent);

	}

	/**
	 * @param consignment
	 */
	private void prepareBackOrderDetails(final AmwayBackOrderModel backOrderModel, final Component parent)
	{
		final Map<String, Object> attributeMap = new LinkedHashMap();
		final ConsignmentModel consignment = backOrderModel.getConsignment();

		final String itemsLabel1 = Labels.getLabel("amway.backorder.section.details.deliverymode");
		final String itemsAttributeValue1 = String
				.valueOf(consignment.getDeliveryMode() != null ? consignment.getDeliveryMode().getCode() : "");
		attributeMap.put(itemsLabel1, itemsAttributeValue1);

		final String itemsLabel2 = Labels.getLabel("amway.backorder.section.details.shippingdate");
		final String itemsAttributeValue2 = String.valueOf(consignment.getShippingDate());
		attributeMap.put(itemsLabel2, itemsAttributeValue2);

		final String itemsLabel3 = Labels.getLabel("amway.backorder.section.details.warehouse");
		final String itemsAttributeValue3 = String.valueOf(backOrderModel.getWarehouse() != null
				? backOrderModel.getWarehouse().getCode() + " " + backOrderModel.getWarehouse().getName() : "");
		attributeMap.put(itemsLabel3, itemsAttributeValue3);

		final String itemsLabel4 = Labels.getLabel("amway.backorder.section.details.deliveryPOS");
		final String itemsAttributeValue4 = String.valueOf(consignment.getDeliveryPointOfService() != null
				? consignment.getDeliveryPointOfService() + " " + consignment.getDeliveryPointOfService().getName() : "");
		attributeMap.put(itemsLabel4, itemsAttributeValue4);
		String itemsAttributeValue5 = "";
		final String itemsLabel5 = Labels.getLabel("amway.backorder.section.details.volumeABO");
		try
		{
			final String volumeABO = backOrderModel.getOriginalOrder() != null ? backOrderModel.getOriginalOrder().getVolumeAbo()
					: "";
			itemsAttributeValue5 = String.valueOf(StringUtils.isNotEmpty(volumeABO) ? volumeABO : "");
		}
		catch (final NullPointerException e)
		{
			if (LOG.isDebugEnabled())
			{
				LOG.error("Error occured while extracting volume ABO field" + e);
			}
		}
		attributeMap.put(itemsLabel5, itemsAttributeValue5);

		final String itemsLabel6 = Labels.getLabel("amway.backorder.section.details.creationdate");
		final String itemsAttributeValue6 = String.valueOf(backOrderModel.getCreationtime());
		attributeMap.put(itemsLabel6, itemsAttributeValue6);


		final Date today = new Date();
		final String itemsLabel8 = Labels.getLabel("amway.backorder.section.details.fullfillperiod");
		final String itemsAttributeValue8 = String.valueOf(TimeUnit.MILLISECONDS
				.toDays(backOrderModel.getReleaseByDate().getTime() - backOrderModel.getCreationtime().getTime()));
		attributeMap.put(itemsLabel8, itemsAttributeValue8);

		final String itemsLabel9 = Labels.getLabel("amway.backorder.section.details.age");
		final String itemsAttributeValue9 = String
				.valueOf(TimeUnit.MILLISECONDS.toDays(today.getTime() - backOrderModel.getCreationtime().getTime()));
		attributeMap.put(itemsLabel9, itemsAttributeValue9);

		final String itemsLabel10 = Labels.getLabel("amway.backorder.section.details.paymentRecieved");
		final String itemsAttributeValue10 = String.valueOf(backOrderModel.getOriginalOrder().getPaymentStatus());
		attributeMap.put(itemsLabel10, itemsAttributeValue10);

		ApacAttributeWithLabelRendererUtil.createAttributeWithLabel(parent, attributeMap, 3);

	}

}
