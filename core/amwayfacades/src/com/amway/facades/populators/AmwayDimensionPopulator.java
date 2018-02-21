package com.amway.facades.populators;

import com.amway.core.model.AmwayDimensionDescriptorModel;
import com.amway.core.model.AmwayDimensionModel;
import com.amway.facades.product.data.AmwayDimensionData;
import com.amway.facades.product.data.AmwayDimensionDescriptorData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;


/**
 * @author Aryadna_Birchanka
 */
public class AmwayDimensionPopulator implements Populator<AmwayDimensionModel, AmwayDimensionData>
{
	private Converter<AmwayDimensionDescriptorModel, AmwayDimensionDescriptorData> amwayDimensionDescriptorConverter;

	public Converter<AmwayDimensionDescriptorModel, AmwayDimensionDescriptorData> getAmwayDimensionDescriptorConverter()
	{
		return amwayDimensionDescriptorConverter;
	}

	@Required
	public void setAmwayDimensionDescriptorConverter(
			Converter<AmwayDimensionDescriptorModel, AmwayDimensionDescriptorData> amwayDimensionDescriptorConverter)
	{
		this.amwayDimensionDescriptorConverter = amwayDimensionDescriptorConverter;
	}

	@Override
	public void populate(AmwayDimensionModel amwayDimensionModel, AmwayDimensionData amwayDimensionData) throws ConversionException
	{
		if (amwayDimensionModel.getHeight() != null)
		{
			amwayDimensionData.setHeight(getAmwayDimensionDescriptorConverter().convert(amwayDimensionModel.getHeight()));
		}
		if (amwayDimensionModel.getLength() != null)
		{
			amwayDimensionData.setLength(getAmwayDimensionDescriptorConverter().convert(amwayDimensionModel.getLength()));
		}
		if (amwayDimensionModel.getCount() != null)
		{
			amwayDimensionData.setCount(getAmwayDimensionDescriptorConverter().convert(amwayDimensionModel.getCount()));
		}
		if (amwayDimensionModel.getWeight() != null)
		{
			amwayDimensionData.setWeight(getAmwayDimensionDescriptorConverter().convert(amwayDimensionModel.getWeight()));
		}
		if (amwayDimensionModel.getWidth() != null)
		{
			amwayDimensionData.setWidth(getAmwayDimensionDescriptorConverter().convert(amwayDimensionModel.getWidth()));
		}
		if (amwayDimensionModel.getVolume() != null)
		{
			amwayDimensionData.setVolume(getAmwayDimensionDescriptorConverter().convert(amwayDimensionModel.getVolume()));
		}
	}
}
