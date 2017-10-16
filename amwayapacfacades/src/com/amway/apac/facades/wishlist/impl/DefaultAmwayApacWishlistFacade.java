package com.amway.apac.facades.wishlist.impl;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.apac.core.wishlist.services.AmwayApacWishllistService;
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

	private AmwayApacWishllistService amwayApacWishlistService;
	private Converter<Wishlist2Model, WishlistData> amwayApacWishlistBasicConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WishlistData> getAllWishlistsWithBasicData(final String sortField, final String sortOrder)
	{
		return Converters.convertAll(getAmwayApacWishlistService().getWishlists(sortField, sortOrder),
				getAmwayApacWishlistBasicConverter());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData getWishlistByUidForCurrentUser(final String uid)
	{
		Assert.hasLength(uid, "Parameter uid can not be null or empty.");

		return getWishlistConverter().convert(getAmwayApacWishlistService().getWishlistByUidForCurrentUser(uid));
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

	/**
	 * @return the amwayApacWishlistService
	 */
	public AmwayApacWishllistService getAmwayApacWishlistService()
	{
		return amwayApacWishlistService;
	}

	/**
	 * @param amwayApacWishlistService
	 *           the amwayApacWishlistService to set
	 */
	@Required
	public void setAmwayApacWishlistService(final AmwayApacWishllistService amwayApacWishlistService)
	{
		this.amwayApacWishlistService = amwayApacWishlistService;
	}

}
