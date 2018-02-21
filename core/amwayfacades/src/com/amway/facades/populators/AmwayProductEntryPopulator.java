package com.amway.facades.populators;

import com.amway.core.model.AmwayDimensionModel;
import com.amway.facades.product.data.AmwayDimensionData;
import de.hybris.platform.commercefacades.order.data.DeliveryModeData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.delivery.DeliveryModeModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;


/**
 * Populates product's data for cart with Amway specific information
 *
 * @author Aryadna_Birchanka
 */
public class AmwayProductEntryPopulator implements Populator<ProductModel, ProductData>
{
	private Converter<AmwayDimensionModel, AmwayDimensionData> dimensionConverter;

	@Autowired
	private Converter<DeliveryModeModel, DeliveryModeData> deliveryModeConverter;

	public Converter<AmwayDimensionModel, AmwayDimensionData> getDimensionConverter()
	{
		return dimensionConverter;
	}

	@Required
	public void setDimensionConverter(Converter<AmwayDimensionModel, AmwayDimensionData> dimensionConverter)
	{
		this.dimensionConverter = dimensionConverter;
	}

	@Override
	public void populate(ProductModel productModel, ProductData productData) throws ConversionException
	{
		if (productModel.getDimensions() != null)
		{
			productData.setDimensions(getDimensionConverter().convert(productModel.getDimensions()));
		}
		productData.setAlias(productModel.getAlias());
		List<DeliveryModeData> deliveryModeData = new ArrayList();
		if (!productModel.getDeliveryModes().isEmpty())
		{
			for (DeliveryModeModel deliveryMode : productModel.getDeliveryModes())
			{
				deliveryModeData.add(deliveryModeConverter.convert(deliveryMode));
			}
		}
		productData.setDeliveryModes(deliveryModeData);

	}
}
