/**
 *
 */
package com.amway.facades.populators;

import de.hybris.platform.commercefacades.order.converters.populator.AbstractOrderPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import com.amway.facades.data.AmwayAccountData;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.amway.core.model.AmwayAccountModel;


/**
 * Populate AbstractOrder's data with Amway specific information
 */
public class AmwayAbstractOrderPopulator extends AbstractOrderPopulator<AbstractOrderModel, AbstractOrderData>
{

	private Converter<AmwayAccountModel, AmwayAccountData> amwayAccountConverter;

	@Override
	public void populate(AbstractOrderModel abstractOrderModel, AbstractOrderData abstractOrderData) throws ConversionException
	{
		AmwayAccountData amwayAccountData = new AmwayAccountData();
		if (abstractOrderModel.getAccount() != null)
		{
			getAmwayAccountConverter().convert(abstractOrderModel.getAccount(), amwayAccountData);
		}
		abstractOrderData.setAccount(amwayAccountData);
	}

	public Converter<AmwayAccountModel, AmwayAccountData> getAmwayAccountConverter()
	{
		return amwayAccountConverter;
	}

	public void setAmwayAccountConverter(final Converter<AmwayAccountModel, AmwayAccountData> amwayAccountConverter)
	{
		this.amwayAccountConverter = amwayAccountConverter;
	}
}
