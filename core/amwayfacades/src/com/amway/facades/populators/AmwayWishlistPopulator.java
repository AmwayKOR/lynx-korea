package com.amway.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.StockData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;

import com.amway.facades.product.data.WishlistData;
import com.amway.facades.product.data.WishlistEntryData;


/**
 * @author Viktar_Yarmalovich
 */
public class AmwayWishlistPopulator implements Populator<Wishlist2Model, WishlistData>
{

	private Converter<ProductModel, ProductData> productConverter;

	private Converter<ProductModel, StockData> stockConverter;

	@Override
	public void populate(final Wishlist2Model source, final WishlistData target) throws ConversionException
	{

		target.setUid(source.getUid());
		target.setDescription(source.getDescription());
		target.setIsDefault(BooleanUtils.toStringTrueFalse(source.getDefault()));
		target.setName(source.getName());
		target.setIsFavorite(BooleanUtils.isTrue(source.getFavorite()));

		if (source.getEntries() == null)
		{
			return;
		}
		final List<WishlistEntryData> targetEntires = new ArrayList<WishlistEntryData>();
		for (final Wishlist2EntryModel sourceEntry : source.getEntries())
		{
			final WishlistEntryData entry = new WishlistEntryData();
			entry.setAddedDate(sourceEntry.getAddedDate());
			entry.setComment(sourceEntry.getComment());
			entry.setDesired(sourceEntry.getDesired());
			entry.setPriority(sourceEntry.getPriority().getCode());
			entry.setReceived(sourceEntry.getReceived());
			entry.setProduct(getProductConverter().convert(sourceEntry.getProduct()));
			entry.getProduct().setStock(stockConverter.convert(sourceEntry.getProduct()));
			targetEntires.add(entry);
		}
		target.setEntries(targetEntires);
	}

	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	/**
	 * @return the stockConverter
	 */
	public Converter<ProductModel, StockData> getStockConverter()
	{
		return stockConverter;
	}

	/**
	 * @param stockConverter
	 *           the stockConverter to set
	 */
	public void setStockConverter(final Converter<ProductModel, StockData> stockConverter)
	{
		this.stockConverter = stockConverter;
	}


}
