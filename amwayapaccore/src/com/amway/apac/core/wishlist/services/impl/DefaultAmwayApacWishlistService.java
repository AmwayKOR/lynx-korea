package com.amway.apac.core.wishlist.services.impl;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.wishlist2.model.Wishlist2Model;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.amway.apac.core.wishlist.daos.AmwayApacWishlistDao;
import com.amway.apac.core.wishlist.services.AmwayApacWishllistService;
import com.amway.core.wishlist.services.impl.AmwayWishlistServiceImpl;


/**
 * Default implementation of {@link AmwayApacWishllistService}
 *
 * @author Parvesh Goyal
 *
 */
public class DefaultAmwayApacWishlistService extends AmwayWishlistServiceImpl implements AmwayApacWishllistService
{
	private AmwayApacWishlistDao amwayApacWishlistDao;

	/**
	 * Logger instance to record events at class level
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAmwayApacWishlistService.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Wishlist2Model> getWishlists(final String sortField, final String sortOrder)
	{
		final UserModel currentUser = getCurrentUser();
		final BaseSiteModel currentSite = getBaseSiteService().getCurrentBaseSite();

		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(200).append("Fetching wishlists for user [").append(currentUser.getUid())
					.append("] for site [").append(currentSite.getUid()).append("].").toString());
		}

		final List<Wishlist2Model> wishlistsFound = getAmwayApacWishlistDao().findWishlistsForUserAndSite(currentUser, currentSite,
				sortField, sortOrder);

		if (LOGGER.isInfoEnabled())
		{
			LOGGER.info(new StringBuilder(200).append("Found ").append(CollectionUtils.size(wishlistsFound))
					.append(" wishlists for user [").append(currentUser.getUid()).append("] for site [").append(currentSite.getUid())
					.append("].").toString());
		}

		return wishlistsFound;
	}

	/**
	 * @return the amwayApacWishlistDao
	 */
	public AmwayApacWishlistDao getAmwayApacWishlistDao()
	{
		return amwayApacWishlistDao;
	}

	/**
	 * @param amwayApacWishlistDao
	 *           the amwayApacWishlistDao to set
	 */
	@Required
	public void setAmwayApacWishlistDao(final AmwayApacWishlistDao amwayApacWishlistDao)
	{
		this.amwayApacWishlistDao = amwayApacWishlistDao;
	}
}
