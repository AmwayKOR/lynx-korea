package com.amway.apac.facades.wishlist.impl;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.facades.wishlist.AmwayApacWishlistFacade;
import com.amway.facades.product.data.WishlistData;
import com.amway.facades.wishlist.impl.DefaultAmwayWishlistFacade;


/**
 * Default implementation of {@link AmwayApacWishlistFacade}
 *
 * @author Parvesh Goyal
 *
 */
public class DefaultAmwayApacWishlistFacade extends DefaultAmwayWishlistFacade implements AmwayApacWishlistFacade
{

	private Converter<Wishlist2Model, WishlistData> amwayApacWishlistBasicConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WishlistData> getAllWishlistsWithBasicData()
	{
		return Converters.convertAll(getWishlistService().getWishlists(), getAmwayApacWishlistBasicConverter());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createFavoriteWishlistIfNeeded()
	{
		getWishlistService().getOrCreateFavoriteWishlist();
	}


	/**
	 * @return the amwayApacWishlistBasicConverter
	 */
	public Converter<Wishlist2Model, WishlistData> getAmwayApacWishlistBasicConverter()
	{
		return amwayApacWishlistBasicConverter;
	}

	/**
	 * @param amwayApacWishlistBasicConverter
	 *           the amwayApacWishlistBasicConverter to set
	 */
	@Required
	public void setAmwayApacWishlistBasicConverter(final Converter<Wishlist2Model, WishlistData> amwayApacWishlistBasicConverter)
	{
		this.amwayApacWishlistBasicConverter = amwayApacWishlistBasicConverter;
	}

}
