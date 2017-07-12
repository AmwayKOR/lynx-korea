package com.amway.facades.populators;

import com.amway.core.model.AmwayDimensionDescriptorModel;
import com.amway.core.model.AmwayUnitModel;
import com.amway.facades.product.data.AmwayDimensionDescriptorData;
import com.amway.facades.product.data.AmwayUnitData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;


/**
 * @author Aryadna_Birchanka
 */
public class AmwayDimensionDescriptorPopulator implements Populator<AmwayDimensionDescriptorModel, AmwayDimensionDescriptorData>
{
	private Converter<AmwayUnitModel, AmwayUnitData> amwayUnitConverter;

	public Converter<AmwayUnitModel, AmwayUnitData> getAmwayUnitConverter()
	{
		return amwayUnitConverter;
	}

	@Required
	public void setAmwayUnitConverter(Converter<AmwayUnitModel, AmwayUnitData> amwayUnitConverter)
	{
		this.amwayUnitConverter = amwayUnitConverter;
	}

	@Override
	public void populate(AmwayDimensionDescriptorModel amwayDimensionDescriptorModel,
			AmwayDimensionDescriptorData amwayDimensionDescriptorData) throws ConversionException
	{
		amwayDimensionDescriptorData.setValue(amwayDimensionDescriptorModel.getValue());
		if (amwayDimensionDescriptorModel.getUnit() != null)
		{
			amwayDimensionDescriptorData.setUnit(getAmwayUnitConverter().convert(amwayDimensionDescriptorModel.getUnit()));
		}
	}
}
