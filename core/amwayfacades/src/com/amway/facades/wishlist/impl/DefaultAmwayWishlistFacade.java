package com.amway.facades.wishlist.impl;

import de.hybris.platform.converters.Converters;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;
import org.apache.log4j.Logger;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.amway.core.wishlist.services.AmwayWishlistService;
import com.amway.facades.product.data.WishlistData;
import com.amway.facades.wishlist.AmwayWishlistFacade;
import com.codahale.metrics.annotation.Timed;

import javax.annotation.Resource;


/**
 * Default implementation of {@link AmwayWishlistFacade} interface.
 *
 * @author Viktar_Yarmalovich.
 */
public class DefaultAmwayWishlistFacade implements AmwayWishlistFacade
{
	private static final Integer DEFAULT_WISHLIST_ENTRY_QUANTITY = Integer.valueOf(1);
	private static Logger LOG = Logger.getLogger(DefaultAmwayWishlistFacade.class);

	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "productService")
	private ProductService productService;
	
	@Resource(name = "modelService")
	private ModelService modelService;
	
	// TODO check bean definitions @Resource(name = "wishlistService")
	// private Wishlist2Service wishlistService;
	private AmwayWishlistService wishlistService;
	
	@Resource(name = "wishlistConverter")
	private Converter<Wishlist2Model, WishlistData> wishlistConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData addToWishList(final String productCode, final Integer desired)
	{
		final UserModel userModel = userService.getCurrentUser();

		final ProductModel productModel = productService.getProductForCode(productCode);

		if (!wishlistService.hasDefaultWishlist(userModel))
		{
			modelService.save(wishlistService.createDefaultWishlist(userModel, "WishList", "This is default wishlist"));
		}

		final Wishlist2Model wishList = wishlistService.getDefaultWishlist(userModel);
		try
		{
			final Wishlist2EntryModel wishlist2EntryModel = wishlistService.getWishlistEntryForProduct(productModel, wishList);
			if (wishlist2EntryModel == null)
			{
				wishlistService.addWishlistEntry(userModel, productModel, desired, Wishlist2EntryPriority.HIGH, "Must have");
			}
		}
		catch (final Exception e)
		{
			LOG.warn("Exception while getting/setting wishlist entry for product " + productCode +
						".  " + e.getMessage() + "  Retrying to add new wishlist entry.", e);
			wishlistService.addWishlistEntry(userModel, productModel, desired, Wishlist2EntryPriority.HIGH, "Must have");
			//
		}
		return getWishlistConverter().convert(wishList);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData getWishList()
	{
		Wishlist2Model wishList = new Wishlist2Model();
		final UserModel userModel = userService.getCurrentUser();

		if (!wishlistService.hasDefaultWishlist(userModel))
		{
			wishList = wishlistService.createDefaultWishlist(userModel, "WishList", "This is default wishlist");
		}
		else
		{
			wishList = wishlistService.getDefaultWishlist(userModel);
		}

		return getWishlistConverter().convert(wishList);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData getWishlistByUid(final String uid)
	{
		return getWishlistConverter().convert(wishlistService.getWishlistByUid(uid));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData removeFromWishList(final String productCode)
	{
		Wishlist2Model wishList = null;
		final UserModel userModel = userService.getCurrentUser();

		if (!getWishlistService().hasDefaultWishlist(userModel))
		{
			wishList = getWishlistService().createDefaultWishlist(userModel, "WishList", "This is default wishlist");
		}
		else
		{
			wishList = getWishlistService().getDefaultWishlist(userModel);
		}

		if (null != wishList && CollectionUtils.isNotEmpty(wishList.getEntries()))
		{
			for (final Wishlist2EntryModel entry : wishList.getEntries())
			{
				if (entry.getProduct().getCode().equals(productCode))
				{
					getWishlistService().removeWishlistEntry(wishList, entry);
				}
			}
		}
		return getWishlistConverter().convert(wishList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData removeFromWishList(final String uid, final String productCode)
	{
		final Wishlist2Model wishList = getWishlistService().getWishlistByUid(uid);
		if (null != wishList && CollectionUtils.isNotEmpty(wishList.getEntries()))
		{
			for (final Wishlist2EntryModel entry : wishList.getEntries())
			{
				if (entry.getProduct().getCode().equals(productCode))
				{
					getWishlistService().removeWishlistEntry(wishList, entry);
				}
			}
		}
		return getWishlistConverter().convert(wishList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData removeProductFromFavoriteWishList(final String productCode)
	{
		final Wishlist2Model wishList = getWishlistService().getFavoriteWishlist();
		if (null != wishList && CollectionUtils.isNotEmpty(wishList.getEntries()))
		{
			for (final Wishlist2EntryModel entry : wishList.getEntries())
			{
				if (entry.getProduct().getCode().equals(productCode))
				{
					getWishlistService().removeWishlistEntry(wishList, entry);
				}
			}
		}
		return getWishlistConverter().convert(wishList);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addProductToWishlist(final String productCode)
	{
		Assert.hasLength(productCode, "Product code must not be null!");

		final ProductModel productModel = productService.getProductForCode(productCode);
		final Wishlist2Model wishlist2Model = wishlistService.getFavoriteWishlist();


		if ((wishlist2Model == null) || (!getWishlistService().hasWishlistEntryForProduct(productModel, wishlist2Model)))
		{
			getWishlistService().addWishlistEntry(productModel, DEFAULT_WISHLIST_ENTRY_QUANTITY, Wishlist2EntryPriority.HIGH,
					"Must have");
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Timed(name = "getAllWishlists")
	public List<WishlistData> getAllWishlists()
	{
		return Converters.convertAll(getWishlistService().getWishlists(), wishlistConverter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData createWishlist(final String wishlistName)
	{
		WishlistData wishlist = null;
		if (null == getWishlistService().getWishlistByName(wishlistName))
		{
			final Wishlist2Model wishlistCreated = getWishlistService().createWishlist(wishlistName, StringUtils.EMPTY);
			wishlist = getWishlistConverter().convert(wishlistCreated);
		}
		return wishlist;
	}




	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData updateWishlistName(final String uid, final String newName)
	{
		WishlistData wishlist = null;
		if (null == getWishlistService().getWishlistByName(newName))
		{
			Wishlist2Model wishlistModel = getWishlistService().getWishlistByUid(uid);
			wishlistModel = getWishlistService().updateWishlistName(wishlistModel, newName);
			wishlist = getWishlistConverter().convert(wishlistModel);
		}
		return wishlist;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteWishlist(final String uid)
	{
		return (getWishlistService().deleteWishlist(uid));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WishlistData setFavoriteWishlist(final String uid)
	{
		final Wishlist2Model wishlist = getWishlistService().getWishlistByUid(uid);
		final Wishlist2Model wishlist2Model = getWishlistService().saveAsFavoriteWishlist(wishlist);
		return getWishlistConverter().convert(wishlist2Model);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return
	 */
	@Override
	public WishlistData getFavoriteWishlist()
	{
		return getWishlistConverter().convert(getWishlistService().getOrCreateFavoriteWishlist());
	}

	public UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public ProductService getProductService()
	{
		return productService;
	}

	@Required
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	public Converter<Wishlist2Model, WishlistData> getWishlistConverter()
	{
		return wishlistConverter;
	}

	@Required
	public void setWishlistConverter(final Converter<Wishlist2Model, WishlistData> wishlistConverter)
	{
		this.wishlistConverter = wishlistConverter;
	}

	public AmwayWishlistService getWishlistService()
	{
		return wishlistService;
	}

	@Required
	public void setWishlistService(final AmwayWishlistService wishlistService)
	{
		this.wishlistService = wishlistService;
	}


}
