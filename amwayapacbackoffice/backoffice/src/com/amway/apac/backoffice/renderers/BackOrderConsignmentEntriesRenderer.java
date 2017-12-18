/**
 *
 */
package com.amway.apac.backoffice.renderers;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.util.TaxValue;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;

import com.amway.amwayapacbackoffice.data.BackOrderEntryData;
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
public class BackOrderConsignmentEntriesRenderer implements WidgetComponentRenderer<Component, CustomSection, Object>
{
	private static final String HYPHEN = "-";

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
		prepareBackOrderConsignmentEntries(backOrderModel, parent);

	}

	/**
	 * @param backOrderModel
	 * @param parent
	 */
	private void prepareBackOrderConsignmentEntries(final AmwayBackOrderModel backOrderModel, final Component parent)
	{
		final List<BackOrderEntryData> orderEntriesDataList = new ArrayList<>();

		final List<AbstractOrderEntryModel> abstractOrderEntries = backOrderModel.getOriginalOrder().getEntries();

		abstractOrderEntries.forEach(abstractOrderEntry -> {
			final OrderEntryModel orderEntryData = (OrderEntryModel) abstractOrderEntry;
			final BackOrderEntryData backOrderEntryData = new BackOrderEntryData();
			backOrderEntryData.setEntry(String.valueOf(orderEntryData.getEntryNumber().intValue() + 1));
			backOrderEntryData.setItemSKU(String.valueOf(orderEntryData.getAliasCode()));
			backOrderEntryData.setItem(String.valueOf(orderEntryData.getProduct().getName()));
			backOrderEntryData.setQuantity(String.valueOf(orderEntryData.getQuantity()));
			backOrderEntryData.setPrice(String.valueOf(orderEntryData.getBasePrice()));
			backOrderEntryData.setTax(String.valueOf(calculateEntryTax(orderEntryData)));
			backOrderEntryData.setTotalPrice(String.valueOf(abstractOrderEntry.getTotalPrice()));
			backOrderEntryData.setBV(String.valueOf(abstractOrderEntry.getBusinessVolume()));
			backOrderEntryData.setPV(String.valueOf(abstractOrderEntry.getPointValue()));
			backOrderEntryData.setPaymentType(String.valueOf(backOrderModel.getOriginalOrder().getPaymentType()));
			orderEntriesDataList.add(backOrderEntryData);
		});
		ApacAttributeWithLabelRendererUtil.createView(parent, getHeaderList(), getParametersList(), orderEntriesDataList,
				BackOrderEntryData.class, HYPHEN);


	}

	private double calculateEntryTax(final OrderEntryModel orderEntry)
	{
		double totalTax = 0;
		for (final TaxValue tempTaxValue : orderEntry.getTaxValues())
		{
			if (Double.valueOf(tempTaxValue.getValue()).compareTo(Double.valueOf(0.0)) < 0)
			{
				totalTax += tempTaxValue.getValue() * orderEntry.getQuantity().doubleValue() * -1.0D;
			}
		}
		return totalTax;

	}

	private List<String> getHeaderList()
	{
		final List<String> headerList = new ArrayList<>();
		headerList.add("amway.backorder.section.consignmentEntries.entry");
		headerList.add("amway.backorder.section.consignmentEntries.itemsku");
		headerList.add("amway.backorder.section.consignmentEntries.item");
		headerList.add("amway.backorder.section.consignmentEntries.quantity");
		headerList.add("amway.backorder.section.consignmentEntries.itemprice");
		headerList.add("amway.backorder.section.consignmentEntries.itemtax");
		headerList.add("amway.backorder.section.consignmentEntries.totalprice");
		headerList.add("amway.backorder.section.consignmentEntries.pv");
		headerList.add("amway.backorder.section.consignmentEntries.bv");
		headerList.add("amway.backorder.section.consignmentEntries.paymenttype");
		return headerList;
	}

	private List<String> getParametersList()
	{
		final List<String> paramList = new ArrayList<>();
		paramList.add("entry");
		paramList.add("itemSKU");
		paramList.add("item");
		paramList.add("quantity");
		paramList.add("price");
		paramList.add("tax");
		paramList.add("totalPrice");
		paramList.add("PV");
		paramList.add("BV");
		paramList.add("paymentType");
		return paramList;
	}

}
