package com.amway.core.model;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Aryadna_Birchanka
 */
public class PickupDateTimeAttribute extends AbstractDynamicAttributeHandler<Date, AbstractOrderModel>
{
	public PickupDateTimeAttribute()
	{
	}

	@Override
	public Date get(AbstractOrderModel model)
	{
		Date pickupDate = null;
		List<Date> dateList = new ArrayList<>();

		for (AbstractOrderEntryModel entry : model.getEntries())
		{
			if (entry.getDeliveryPointOfService() != null)
			{
				dateList.add(entry.getPickupDateTime());
			}
		}
		if (!dateList.isEmpty())
		{
			return dateList.get(0);
		}
		return pickupDate;
	}

	@Override
	public void set(AbstractOrderModel model, Date date)
	{
		if (model != null)
		{
			model.setPickupDateTime(date);
		}
	}
}
