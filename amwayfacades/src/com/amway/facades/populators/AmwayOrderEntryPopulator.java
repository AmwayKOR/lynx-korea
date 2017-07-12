package com.amway.facades.populators;

import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.amway.core.util.AmwayCustomerHelper;
import com.amway.facades.order.data.AmwayValueData;


/**
 * @author Aryadna_Birchanka
 */
public class AmwayOrderEntryPopulator implements Populator<AbstractOrderEntryModel, OrderEntryData>
{
	private UserService userService;

	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	@Override
	public void populate(final AbstractOrderEntryModel abstractOrderEntryModel, final OrderEntryData orderEntryData)
			throws ConversionException
	{
		if (AmwayCustomerHelper.isABOCustomer())
		{
			final AmwayValueData valueData = new AmwayValueData();
			valueData.setBusinessVolume(abstractOrderEntryModel.getBusinessVolume());
			valueData.setPointValue(abstractOrderEntryModel.getPointValue());

			orderEntryData.setValue(valueData);
			if (abstractOrderEntryModel.getRetailBasePrice() != null && abstractOrderEntryModel.getQuantity() != null)
			{
				final PriceData retailPriceData = new PriceData();
				retailPriceData.setValue(BigDecimal.valueOf(
						abstractOrderEntryModel.getRetailBasePrice() * abstractOrderEntryModel.getQuantity()).setScale(2,
						RoundingMode.HALF_UP));
				retailPriceData.setCurrencyIso(abstractOrderEntryModel.getOrder().getCurrency().getIsocode());
				retailPriceData.setFormattedValue(abstractOrderEntryModel.getOrder().getCurrency().getSymbol()
						+ abstractOrderEntryModel.getRetailBasePrice() * abstractOrderEntryModel.getQuantity());
				orderEntryData.setRetailPrice(retailPriceData);
			}
		}

		if (abstractOrderEntryModel.getPickupDateTime() != null)
		{
			orderEntryData.setPickupDateTime(abstractOrderEntryModel.getPickupDateTime());
		}
	}
}
