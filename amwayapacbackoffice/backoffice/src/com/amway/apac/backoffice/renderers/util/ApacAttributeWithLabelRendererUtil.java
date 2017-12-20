/**
 *
 */
package com.amway.apac.backoffice.renderers.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import com.hybris.cockpitng.util.UITools;





/**
 * This util class is used to create and render a attribute with label and table view when needed
 *
 */
public class ApacAttributeWithLabelRendererUtil
{
	private static final Logger LOG = Logger.getLogger(ApacAttributeWithLabelRendererUtil.class);

	private static final String STYLE_FONT_BOLD = "font-weight: bold;";
	private static final String STYLE_TEXT_ALIGN_CENTER_AND_BORDER = "border: 1px solid #f2f2f2; text-align: center";
	private static final String STYLE_TEXT_WRAP = "text-overflow: unset; white-space: normal; word-break: break-all; word-wrap: normal;";
	public static final String STYLE_CELL_BACKGROUND = "background:#eaedf1";


	private static final String SCLASS_CELL = "yw-editorarea-tabbox-tabpanels-tabpanel-groupbox-attrcell";
	private static final String SCLASS_CELL_LABEL = "yw-editorarea-tabbox-tabpanels-tabpanel-groupbox-attrcell-label";
	public static final String SCLASS_CELL_LABEL_CONTAINER = "yw-editorarea-label-container";

	/**
	 * Create a text field with lable where the field is non editable
	 * @param parent
	 *           (Component)
	 * @param labelWithAttributeValueMap
	 *           (Map) {key : labelValue (Value of label),value : attributeValue (Value of attribute)}
	 * @param noOfColumns
	 *           (No, of columns in a single row,will automatically create a new row if needed)
	 */
	public static void createAttributeWithLabel(final Component parent, final Map<String, Object> labelWithAttributeValueMap,
			final int noOfColumns)
	{
		if (MapUtils.isNotEmpty(labelWithAttributeValueMap))
		{
			int index = 0;
			Hbox hbox = new Hbox();
			hbox.setParent(parent);

			for (final Entry<String, Object> entry : labelWithAttributeValueMap.entrySet())
			{
				hbox = createNewRowIfNeeded(hbox, parent, noOfColumns, index);
				final Cell cell = new Cell();
				hbox.appendChild(cell);
				cell.setSclass(SCLASS_CELL);

				final Div labelCtr = new Div();
				UITools.modifySClass(labelCtr, SCLASS_CELL_LABEL_CONTAINER, true);

				final Label label = new Label(entry.getKey());
				label.setTooltiptext(label.getValue());
				UITools.modifySClass(label, SCLASS_CELL_LABEL, true);
				labelCtr.appendChild(label);
				cell.appendChild(labelCtr);
				Textbox textBox = new Textbox();
				if (null != entry.getValue())
				{
					textBox = new Textbox(entry.getValue().toString());
					textBox.setTooltiptext(entry.getValue().toString());
				}
				textBox.setReadonly(true);
				cell.appendChild(textBox);
				index++;
			}
		}

	}

	private static Hbox createNewRowIfNeeded(final Hbox hbox, final Component parent, final int noOfColumns,
			final int currentCellIndex)
	{
		Hbox result = hbox;
		if (hbox == null || currentCellIndex % noOfColumns == 0)
		{
			result = new Hbox();
			result.setParent(parent);
		}
		return result;
	}

	/**
	 * Creates the row in the table view with headers
	 *
	 * @param parent
	 *           (Component)
	 * @param List<String>
	 *           headerLabelList List of headers for the table to be created
	 * @param List<String>
	 *           parameters to be extrated fron the dto
	 * @param List<?>
	 *           list of dto from which value of the parameters will be extracted
	 * @param String
	 *           Value in case their is null value in the dto
	 *
	 */

	public static void createView(final Component parent, final List<String> headerLabelList, final List<String> paramlist,
			final List<?> dataList, final Class listType, final String valueForNull)
	{
		final Listbox listBox = new Listbox();

		//Create header.
		listBox.appendChild(createListHeader(headerLabelList));

		if (CollectionUtils.isNotEmpty(dataList))
		{
			for (final Object obj : dataList)
			{
				if (listType.isInstance(obj))
				{
					//create and append data row.
					listBox.appendChild(createDataListRow(listType.cast(obj), paramlist, valueForNull));
				}
			}
		}
		parent.appendChild(listBox);
	}

	/**
	 * Creates header row.
	 */
	private static Listhead createListHeader(final List<String> headerList)
	{
		final Listhead listHead = new Listhead();

		for (final String headerKey : headerList)
		{
			listHead.appendChild(getListHeader(headerKey));
		}
		return listHead;
	}

	/**
	 * Creates data row.
	 */
	private static Component createDataListRow(final Object cast, final List<String> paramlist, final String valueForNull)
	{
		final Listitem listItem = new Listitem();
		final Class c = cast.getClass();

		for (final String param : paramlist)
		{
			try
			{
				final Object ret = new PropertyDescriptor(param, c).getReadMethod().invoke(cast);
				listItem.appendChild(getListCell(String.valueOf(ret != null ? ret : valueForNull)));
			}
			catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| IntrospectionException e)
			{
				listItem.appendChild(getListCell(String.valueOf(valueForNull)));
				LOG.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return listItem;
	}

	private static Listheader getListHeader(final String key)
	{
		final Listheader listHeader = new Listheader();
		listHeader.appendChild(createLabel(Labels.getLabel(key), true));
		listHeader.setStyle(STYLE_TEXT_ALIGN_CENTER_AND_BORDER);
		return listHeader;
	}

	private static Listcell getListCell(final String labelValue)
	{
		final Listcell listCell = new Listcell();
		listCell.appendChild(createLabel(labelValue, false));
		listCell.setStyle(STYLE_TEXT_ALIGN_CENTER_AND_BORDER);
		return listCell;
	}

	private static Label createLabel(final String value, final boolean bold)
	{
		final Label label = new Label(value);
		final StringBuilder builder = new StringBuilder(STYLE_TEXT_WRAP);
		if (bold)
		{
			builder.append(STYLE_FONT_BOLD);
		}
		label.setStyle(builder.toString());
		return label;
	}
}
