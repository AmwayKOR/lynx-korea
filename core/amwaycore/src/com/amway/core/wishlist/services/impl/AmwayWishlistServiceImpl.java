/**
 *
 */
package com.amway.core.wishlist.services.impl;


import static org.springframework.util.Assert.hasLength;
import static org.springframework.util.Assert.notNull;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.exceptions.SystemException;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.wishlist2.enums.Wishlist2EntryPriority;
import de.hybris.platform.wishlist2.impl.DefaultWishlist2Service;
import de.hybris.platform.wishlist2.model.Wishlist2EntryModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.core.constants.AmwaycoreConstants;
import com.amway.core.wishlist.daos.AmwayWishlistDao;
import com.amway.core.wishlist.services.AmwayWishlistService;


/**
 * Wish list service implementation extending {@link DefaultWishlist2Service}.
 *
 * @author Parvesh Goyal
 */
public class AmwayWishlistServiceImpl extends DefaultWishlist2Service implements AmwayWishlistService
{
	private static final Logger LOG = LoggerFactory.getLogger(AmwayWishlistServiceImpl.class);

	private static final char WISHIST_UID_SEPARATOR = '_';

	private BaseSiteService baseSiteService;
	private AmwayWishlistDao amwayWishlistDao;

	/**
	 * {@inheritDoc}
	 *
	 * Overriding the OOTB behavior to add a check for current site as well when finding wish lists for the given
	 * customer.
	 */
	@Override
	public List<Wishlist2Model> getWishlists(final UserModel user)
	{
		return getAmwayWishlistDao().findWishlistsForUserAndSite(user, getBaseSiteService().getCurrentBaseSite());
	}

	/**
	 * {@inheritDoc}
	 *
	 * Overriding the OOTB behavior to set the site as well when creating a wish list for the given customer.
	 */
	@Override
	public Wishlist2Model createDefaultWishlist(final UserModel user, final String name, final String description)
	{
		return createDefaultWishlist(user, name, description, getBaseSiteService().getCurrentBaseSite());
	}

	private Wishlist2Model createDefaultWishlist(final UserModel user, final String name, final String description,
			final BaseSiteModel site)
	{
		if (hasDefaultWishlist())
		{
			throw new SystemException("An default wishlist for the user <" + user.getName() + "> already exists");
		}
		return createWishlist(user, name, description, Boolean.TRUE, site, Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Overriding the OOTB behavior to set the site as well when creating a wish list for the given customer.`
	 */
	@Override
	public Wishlist2Model createWishlist(final UserModel user, final String name, final String description)
	{
		return createWishlist(user, name, description, Boolean.FALSE, getBaseSiteService().getCurrentBaseSite(), Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wishlist2Model updateWishlistName(final Wishlist2Model wishlist, final String newName)
	{
		hasLength("New name for the wish list can not be null or empty.", newName);
		notNull(wishlist, "Wishlist can noe be null.");

		wishlist.setName(newName);
		getModelService().save(wishlist);
		getModelService().refresh(wishlist);
		return wishlist;
	}

	/**
	 * Overriding the OOTB behavior to set the site as well when creating a wish list for the given customer.
	 */
	private Wishlist2Model createWishlist(final UserModel user, final String name, final String description,
			final Boolean defaultWL, final BaseSiteModel site, final Boolean favoriteWL)
	{
		final Wishlist2Model wishlist = getModelService().create(Wishlist2Model.class);
		wishlist.setUid(new StringBuilder().append(user.getUid()).append(WISHIST_UID_SEPARATOR)
				.append(getBaseSiteService().getCurrentBaseSite().getUid()).append(WISHIST_UID_SEPARATOR).append(new Date().getTime())
				.toString());
		wishlist.setName(name);
		wishlist.setDescription(description);
		wishlist.setDefault(defaultWL);
		wishlist.setSite(site);
		wishlist.setFavorite(favoriteWL);
		wishlist.setUser(user);
		if (saveWishlist(user))
		{
			getModelService().save(wishlist);
		}
		return wishlist;
	}

	@Override
	public Wishlist2Model getDefaultWishlist(final UserModel user)
	{
		return getDefaultWishlist(user, getBaseSiteService().getCurrentBaseSite());
	}

	private Wishlist2Model getDefaultWishlist(final UserModel user, final BaseSiteModel site)
	{
		final List<Wishlist2Model> wishlistsFound = getAmwayWishlistDao().findDefaultWishlistForUserAndSite(user, site);

		Wishlist2Model defaultWishlist = null;
		if (CollectionUtils.isNotEmpty(wishlistsFound))
		{
			defaultWishlist = wishlistsFound.iterator().next();

		}
		return defaultWishlist;

	}

	/**
	 * {@inheritDoc}
	 *
	 * Overriding OOTB code to include a check for site as well
	 */
	@Override
	public boolean hasDefaultWishlist(final UserModel user)
	{
		return CollectionUtils
				.isNotEmpty(getAmwayWishlistDao().findDefaultWishlistForUserAndSite(user, getBaseSiteService().getCurrentBaseSite()));
	}

	private Wishlist2Model getFavoriteWishlist(final UserModel user, final BaseSiteModel baseSite)
	{
		final List<Wishlist2Model> wishlistsFound = getAmwayWishlistDao().findFavoriteWishlistsForUserAndSite(user, baseSite);

		Wishlist2Model favoriteWishlist = null;
		if (CollectionUtils.isNotEmpty(wishlistsFound))
		{
			favoriteWishlist = wishlistsFound.iterator().next();
		}
		return favoriteWishlist;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wishlist2Model getFavoriteWishlist()
	{
		return getFavoriteWishlist(getCurrentUser(), getBaseSiteService().getCurrentBaseSite());
	}

	private Wishlist2Model getOrCreateDefaultWishlist()
	{
		Wishlist2Model defaultWishlist = getDefaultWishlist();
		if (defaultWishlist == null)
		{
			defaultWishlist = createDefaultWishlist(AmwaycoreConstants.WishLists.DEFAULT_SHOPPING_LIST_NAME, StringUtils.EMPTY);
		}
		return defaultWishlist;
	}

	private Wishlist2Model getOrCreateDefaultWishlist(final UserModel user, final BaseSiteModel site)
	{
		Wishlist2Model defaultWishlist = getDefaultWishlist(user, site);
		if (defaultWishlist == null)
		{
			defaultWishlist = createDefaultWishlist(user, AmwaycoreConstants.WishLists.DEFAULT_SHOPPING_LIST_NAME, StringUtils.EMPTY,
					site);
		}
		return defaultWishlist;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wishlist2Model getOrCreateFavoriteWishlist()
	{
		Wishlist2Model favoriteWishlist = getFavoriteWishlist();
		if (favoriteWishlist == null)
		{
			favoriteWishlist = getOrCreateDefaultWishlist();
			setFavoriteWishlist(favoriteWishlist);
		}
		return favoriteWishlist;
	}

	private Wishlist2Model getOrCreateFavoriteWishlist(final UserModel user, final BaseSiteModel site)
	{
		Wishlist2Model favoriteWishlist = getFavoriteWishlist(user, site);
		if (favoriteWishlist == null)
		{
			favoriteWishlist = getOrCreateDefaultWishlist(user, site);
			setFavoriteWishlist(favoriteWishlist);
		}
		return favoriteWishlist;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addWishlistEntry(final ProductModel product, final Integer desired, final Wishlist2EntryPriority priority,
			final String comment)
	{
		addWishlistEntry(getOrCreateFavoriteWishlist(), product, desired, priority, comment);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addWishlistEntry(final UserModel user, final ProductModel product, final Integer desired,
			final Wishlist2EntryPriority priority, final String comment)
	{
		final Wishlist2Model wishlist = getOrCreateFavoriteWishlist(user, getBaseSiteService().getCurrentBaseSite());
		addWishlistEntry(wishlist, product, desired, priority, comment);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteWishlist(final String uid)
	{
		boolean isDeleted;
		final Wishlist2Model wishlist = getWishlistByUid(uid);
		if (getDefaultWishlist().equals(wishlist))
		{
			isDeleted = false;
		}
		else
		{
			getModelService().remove(wishlist);
			isDeleted = true;
		}
		return isDeleted;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wishlist2Model saveAsFavoriteWishlist(final Wishlist2Model wishlist)
	{
		return setFavoriteWishlist(wishlist);
	}

	private Wishlist2Model setFavoriteWishlist(final Wishlist2Model wishlist)
	{
		final BaseSiteModel baseSite = getBaseSiteService().getCurrentBaseSite();
		final List<Wishlist2Model> currentFavoriteWishlists = getAmwayWishlistDao()
				.findFavoriteWishlistsForUserAndSite(getCurrentUser(), baseSite);
		if (CollectionUtils.isNotEmpty(currentFavoriteWishlists))
		{
			for (final Wishlist2Model wishlist2Model : currentFavoriteWishlists)
			{
				wishlist2Model.setFavorite(Boolean.FALSE);
			}
		}
		wishlist.setFavorite(Boolean.TRUE);

		final List<Wishlist2Model> wishlistsToSave = new ArrayList<>();
		wishlistsToSave.addAll(currentFavoriteWishlists);
		wishlistsToSave.add(wishlist);
		getModelService().saveAll(wishlistsToSave);
		getModelService().refresh(wishlist);
		return wishlist;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasWishlistEntryForProduct(final ProductModel productModel, final Wishlist2Model wishlist2Model)
	{
		final List<Wishlist2EntryModel> entries = getAmwayWishlistDao().findWishlistEntryByProduct(productModel, wishlist2Model);
		return CollectionUtils.isNotEmpty(entries);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wishlist2Model getWishlistByUid(final String uid)
	{
		hasLength("UID can not be null or empty String.", uid);
		final List<Wishlist2Model> wishlistsFound = getAmwayWishlistDao().findWishlistsForUID(uid);

		ServicesUtil.validateIfSingleResult(wishlistsFound,
				new StringBuilder(50).append("No wishlists found for uid [").append(uid).append("[.").toString(),
				new StringBuilder(50).append("More than one wish lists found for uid [").append(uid).append("[.").toString());

		return wishlistsFound.iterator().next();

	}

	public AmwayWishlistDao getAmwayWishlistDao()
	{
		return amwayWishlistDao;
	}

	@Required
	public void setAmwayWishlistDao(final AmwayWishlistDao amwayWishlistDao)
	{
		this.amwayWishlistDao = amwayWishlistDao;
	}

	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}


	private Wishlist2Model getWishlistByName(final UserModel user, final BaseSiteModel baseSite, final String name)
	{
		final List<Wishlist2Model> wishlistsFound = getAmwayWishlistDao().findWishlistByNameForUserAndSiteAndName(user, baseSite,
				name);

		Wishlist2Model WishlistByName = null;
		if (CollectionUtils.isNotEmpty(wishlistsFound))
		{
			WishlistByName = wishlistsFound.iterator().next();
		}
		return WishlistByName;
	}

	@Override
	public Wishlist2Model getWishlistByName(final String name)
	{

		return getWishlistByName(getCurrentUser(), getBaseSiteService().getCurrentBaseSite(), name);
	}

}
