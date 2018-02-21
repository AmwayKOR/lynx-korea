package com.amway.core.ordercancel.services.impl;

import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.ordercancel.impl.DefaultOrderCancelService;
import de.hybris.platform.ordercancel.model.OrderCancelRecordEntryModel;
import de.hybris.platform.ordercancel.model.OrderCancelRecordModel;
import de.hybris.platform.ordermodify.model.OrderModificationRecordEntryModel;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.amway.core.ordercancel.services.AmwayOrderCancelService;


/**
 * Default implementation
 */
public class DefaultAmwayOrderCancelService extends DefaultOrderCancelService implements AmwayOrderCancelService
{
	private final Logger LOG = Logger.getLogger(DefaultAmwayOrderCancelService.class);

	/**
	 * To retrieve the snapshot of cancelled order.
	 *
	 * @param orderModel
	 */
	@Override
	public OrderModel getOriginalSnapshopt(final OrderModel orderModel)
	{
		try
		{
			final OrderCancelRecordModel cancelRecordModel = getCancelRecordForOrder(orderModel);
			if (cancelRecordModel != null)
			{
				final Collection<OrderModificationRecordEntryModel> modificationRecordEntries = cancelRecordModel
						.getModificationRecordEntries();
				for (final OrderModificationRecordEntryModel modificationRecordEntryModel : modificationRecordEntries)
				{
					if (modificationRecordEntryModel instanceof OrderCancelRecordEntryModel
							&& modificationRecordEntryModel.getOriginalVersion() != null)
					{
						return modificationRecordEntryModel.getOriginalVersion().getPreviousOrderVersion();
					}
				}
			}
		}
		catch (final Exception e)
		{
			LOG.error("Error in retrieving snapshot of cancelled order " + orderModel.getCode(), e);
		}

		return null;
	}
}
