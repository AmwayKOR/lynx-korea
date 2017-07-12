/**
 *
 */
package com.amway.core.order.provider.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import org.apache.commons.lang.StringUtils;


/**
 * To retrieve the default VolumeABO for the order, if all the entries have the same volume ABO.
 */
public class OrderVolumeAboAttributeProvider implements DynamicAttributeHandler<String, AbstractOrderModel>
{

	/**
	 * To get the volumeABO for the order.
	 *
	 * @param abstractOrder
	 */
	@Override
	public String get(final AbstractOrderModel abstractOrder)
	{
		String orderAccount = null;
		for (final AbstractOrderEntryModel entry : abstractOrder.getEntries())
		{
			if (entry.getVolumeAbo() != null)
			{
				if (orderAccount != null && !orderAccount.equals(entry.getVolumeAbo()))
				{
					return null;
				}
				else
				{
					orderAccount = entry.getVolumeAbo();
				}
			}
		}

		return StringUtils.isNotBlank(orderAccount) ? orderAccount : abstractOrder.getAccount().getCode();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void set(final AbstractOrderModel arg0, final String arg1)
	{
		throw new UnsupportedOperationException("The attribute is readonly");
	}

}
