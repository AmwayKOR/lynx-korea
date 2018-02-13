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
import org.springframework.beans.factory.annotation.Required;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;

import com.amway.apac.backoffice.renderers.util.ApacAttributeWithLabelRendererUtil;
import com.amway.apac.core.model.AmwayBackOrderModel;
import com.amway.apac.label.impl.AmwayApacDateLabelProvider;
import com.hybris.cockpitng.core.config.impl.jaxb.editorarea.CustomSection;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.type.DataType;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.hybris.cockpitng.widgets.common.WidgetComponentRenderer;


/**
 * Renderer class for rendering backorder details view in backoffice.
 *
 */
public class BackOrderDetailsRenderer implements WidgetComponentRenderer<Component, CustomSection, Object>
{

	private static final Logger LOG = Logger.getLogger(BackOrderDetailsRenderer.class);

	private AmwayApacDateLabelProvider amwayApacDateLabelProvider;



	/**
	 * @param amwayApacDateLabelProvider
	 *           the amwayApacDateLabelProvider to set
	 */
	@Required
	public void setAmwayApacDateLabelProvider(final AmwayApacDateLabelProvider amwayApacDateLabelProvider)
	{
		this.amwayApacDateLabelProvider = amwayApacDateLabelProvider;
	}

	/**
	 * Renders detail view for backorder in backoffice
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
		prepareBackOrderDetails(backOrderModel, parent);

	}

	/**
	 * @param AmwayBackOrderModel
	 * @param Component
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
		attributeMap.put(itemsLabel2, "null".equalsIgnoreCase(itemsAttributeValue2) ? "" : itemsAttributeValue2);

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
		final String itemsAttributeValue6 = String.valueOf(amwayApacDateLabelProvider.getLabel(backOrderModel.getCreationtime()));
		attributeMap.put(itemsLabel6, itemsAttributeValue6);


		final Date today = new Date();

		final String itemsLabel8 = Labels.getLabel("amway.backorder.section.details.fullfillperiod");
		final String itemsAttributeValue8 = String.valueOf(
				TimeUnit.MILLISECONDS.toDays(backOrderModel.getReleaseByDate().getTime() - backOrderModel.getCreationtime().getTime())
						+ 1);
		attributeMap.put(itemsLabel8, itemsAttributeValue8);

		final String itemsLabel9 = Labels.getLabel("amway.backorder.section.details.age");
		final String itemsAttributeValue9 = String
				.valueOf(TimeUnit.MILLISECONDS.toDays(today.getTime() - backOrderModel.getCreationtime().getTime()));
		attributeMap.put(itemsLabel9, itemsAttributeValue9);

		final String itemsLabel10 = Labels.getLabel("amway.backorder.section.details.paymentRecieved");
		final String itemsAttributeValue10 = String.valueOf(null != backOrderModel.getOriginalOrder().getPaymentStatus()
				? backOrderModel.getOriginalOrder().getPaymentStatus() : "");
		attributeMap.put(itemsLabel10, itemsAttributeValue10);

		final String itemsLabel11 = Labels.getLabel("amway.backorder.section.details.releaseBydate");
		final String itemsAttributeValue11 = String.valueOf(amwayApacDateLabelProvider.getLabel(backOrderModel.getReleaseByDate()));
		attributeMap.put(itemsLabel11, itemsAttributeValue11);

		ApacAttributeWithLabelRendererUtil.createAttributeWithLabel(parent, attributeMap, 3);

	}

}
