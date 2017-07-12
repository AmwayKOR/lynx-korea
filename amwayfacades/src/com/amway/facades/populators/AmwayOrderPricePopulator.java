package com.amway.facades.populators;

import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;
import de.hybris.platform.commercefacades.order.data.DeliveryOrderEntryGroupData;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Required;


/**
 * @author Aryadna_Birchanka
 */
public class AmwayOrderPricePopulator implements Populator<AbstractOrderModel, OrderData>
{
	private UserService userService;

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public void populate(AbstractOrderModel orderModel, OrderData orderData) throws ConversionException
	{
		if (AmwayCustomerHelper.isABOCustomer())
		{
			for (AbstractOrderEntryModel entry : orderModel.getEntries())
			{
				for (OrderEntryData entryData : orderData.getEntries())
				{
					if (entryData.getProduct().getCode().equalsIgnoreCase(entry.getProduct().getCode()))
					{
						entryData.setTotalPrice(getTotalsWithAmwayValue(entryData));
					}
				}
			}
			for (DeliveryOrderEntryGroupData groupData : orderData.getDeliveryOrderGroups())
			{
				for (OrderEntryData orderEntryGroupData : groupData.getEntries())
				{
					orderEntryGroupData.setTotalPrice(getTotalsWithAmwayValue(orderEntryGroupData));
				}
			}
			for (OrderEntryData unconsignedEntryData : orderData.getUnconsignedEntries())
			{
				unconsignedEntryData.setTotalPrice(getTotalsWithAmwayValue(unconsignedEntryData));
			}
		}
	}

	protected PriceData getTotalsWithAmwayValue(OrderEntryData entryData)
	{
		PriceData entryTotalPrice = entryData.getTotalPrice();
		AmwayValueData valueData = entryData.getValue();
		entryTotalPrice.setAmwayValue(valueData);
		return entryTotalPrice;
	}
}
