/**
 *
 */
package com.amway.apac.backoffice.renderers;

import de.hybris.platform.ordersplitting.model.ConsignmentModel;

import java.util.LinkedHashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;

import com.amway.apac.core.model.AmwayBackOrderModel;
import com.hybris.cockpitng.core.config.impl.jaxb.editorarea.CustomSection;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.dataaccess.facades.type.DataType;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.hybris.cockpitng.widgets.common.WidgetComponentRenderer;

import backoffice.src.com.amway.apac.backoffice.renderers.util.ApacAttributeWithLabelRendererUtil;


/**
 *
 */
public class BackOrderEssentialsRenderer implements WidgetComponentRenderer<Component, CustomSection, Object>
{

	/**
	 * Renders Essentials view for backorder in backoffice
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
		prepareBackOrderEssentials(backOrderModel, parent);

	}

	/**
	 * @param backOrderModel
	 * @param parent
	 */
	private void prepareBackOrderEssentials(final AmwayBackOrderModel backOrderModel, final Component parent)
	{
		final Map<String, Object> attributeMap = new LinkedHashMap();
		final ConsignmentModel consignment = backOrderModel.getConsignment();

		final String itemsLabel1 = Labels.getLabel("amway.backorder.section.essentials.consignment");
		final String itemsAttributeValue1 = String.valueOf(consignment.getCode() != null ? consignment.getCode() : "");
		attributeMap.put(itemsLabel1, itemsAttributeValue1);

		final String itemsLabel2 = Labels.getLabel("amway.backorder.section.essentials.status");
		final String itemsAttributeValue2 = String.valueOf(backOrderModel.getStatus());
		attributeMap.put(itemsLabel2, itemsAttributeValue2);

		final String itemsLabel3 = Labels.getLabel("amway.backorder.section.essentials.originalorder");
		final String itemsAttributeValue3 = String
				.valueOf(backOrderModel.getOriginalOrder() != null ? backOrderModel.getOriginalOrder().getCode() : "");
		attributeMap.put(itemsLabel3, itemsAttributeValue3);
		final String itemsLabel4 = Labels.getLabel("amway.backorder.section.essentials.orderingABO");
		final String itemsAttributeValue4 = String
				.valueOf(backOrderModel.getOrderingAbo() != null ? backOrderModel.getOrderingAbo().getUid() : "");
		attributeMap.put(itemsLabel4, itemsAttributeValue4);
		ApacAttributeWithLabelRendererUtil.createAttributeWithLabel(parent, attributeMap, 3);

	}

}
