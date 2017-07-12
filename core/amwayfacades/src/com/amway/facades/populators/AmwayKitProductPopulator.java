package com.amway.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.HashSet;
import java.util.Set;

import com.amway.core.model.AmwayKitEntryProductModel;
import com.amway.core.model.AmwayKitProductModel;
import com.amway.facades.product.data.AmwayKitEntryProductData;


/**
 * @author Anatoli_Levakou
 */
public class AmwayKitProductPopulator implements Populator<ProductModel, ProductData>
{
	private Converter<ProductModel, ProductData> productConverter;

	@Override
	public void populate(final ProductModel source, final ProductData target) throws ConversionException
	{
		if (source instanceof AmwayKitProductModel)
		{
			final AmwayKitProductModel kitModel = (AmwayKitProductModel) source;
			final Set<AmwayKitEntryProductData> entryData = new HashSet<AmwayKitEntryProductData>();

			for (final AmwayKitEntryProductModel entryModel : kitModel.getKitEntry())
			{
				final AmwayKitEntryProductData kitData = new AmwayKitEntryProductData();
				kitData.setConfiguredQty(entryModel.getConfiguredQty());
				kitData.setEntry(getProductConverter().convert(entryModel.getEntry()));
				entryData.add(kitData);
			}
			target.setKitEntry(entryData);
		}
		else
		{
			target.setAlias(source.getAlias());
		}
	}

	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

}
