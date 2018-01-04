package com.amway.apac.facades.wishlist.populators;


import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;

import com.amway.apac.core.constants.AmwayapacCoreConstants;
import com.amway.facades.product.data.WishlistEntryData;


/**
 * Populator to populate wishlist data with basic values.
 *
 * @author avnishalaugh
 *
 */
public class AmwayApacWishlistEntryBasicPopulator implements Populator<Wishlist2EntryModel, WishlistEntryData>
{
	private Converter<ProductModel, ProductData> productConverter;

	@Override
	public void populate(final Wishlist2EntryModel source, final WishlistEntryData target) throws ConversionException
	{
		validateParameterNotNullStandardMessage(AmwayapacCoreConstants.SOURCE_STRING, source);
		validateParameterNotNullStandardMessage(AmwayapacCoreConstants.TARGET_STRING, target);

		target.setAddedDate(source.getAddedDate());
		target.setProduct(getProductConverter().convert(source.getProduct()));
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
